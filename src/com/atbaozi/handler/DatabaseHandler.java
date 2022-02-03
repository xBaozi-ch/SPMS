package com.atbaozi.handler;

import com.atbaozi.userui.ErrorDialog;
import com.atbaozi.util.JdbcUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于将被directoryHandler()方法和databaseHandler()处理过的数据存放到数据库中
 * @ClassName DatabaseHandler
 * @Description 用于将被directoryHandler()方法和databaseHandler()处理过的数据存放到数据库中
 * @Author xBaozi
 * @Date 2021/12/7 18:36
 * @Version 1.0
 */
public class DatabaseHandler {
    /**
     * connection: 定义一个数据库连接
     */
    Connection connection = null;

    /**
     * preparedStatement: 定义一个负责执行SQL语句的preparedStatement对象
     */
    PreparedStatement preparedStatement = null;

    /**
     * resultSet: 定义一个结果集
     */
    ResultSet resultSet = null;

    /**
     * 用于处理HashMap中的数据，将其存入数据库当中
     * @Author xBaozi
     * @Description 用于处理HashMap中的数据，将其存入数据库当中
     * @Date 18:52 2021/12/7
     * @Param [hm]
     * @return void
     **/
    public void databaseHandler(DirectoryHandler directoryHandler) {
        // 定义decimalFormat对象用于浮点数格式控制
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        // 获取班级对应人数的集合
        HashMap<String, Integer> hmClassMemberNum = directoryHandler.getHmClassMemberNum();
        // 获取班级对应成绩的集合
        HashMap<String, Double> hmClassScore = directoryHandler.getHmClassScore();

        try {
            // 获取一个数据库连接
            connection = JdbcUtil.getConnection();

            // 通过for-each循环读取集合中的数据
            for (Map.Entry<String, Double> entry : hmClassScore.entrySet()) {
                // 班级名字
                String className = entry.getKey();
                // 班级人数
                int classMemberNumber = hmClassMemberNum.get(entry.getKey());
                // 班级成绩
                double classAvgScore = Double.parseDouble(decimalFormat
                        .format(entry.getValue()/classMemberNumber));

                // 控制台同步输出插入信息
                System.out.println("{" + className + "," +
                        classAvgScore + "," +
                        classMemberNumber + "}");

                // 定义要执行的SQL语句
                String sql = "INSERT INTO class_score(class_name, class_avg_score, class_menber_number) VALUES(?,?,?) ";

                // 创建一个prepareStatement对象
                preparedStatement = connection.prepareStatement(sql);
                // 为SQL语句中的第一个?设置值
                preparedStatement.setString(1, className);
                // 为SQL语句中的第二个?设置值
                preparedStatement.setDouble(2, classAvgScore);
                // 为SQL语句中的第三个?设置值
                preparedStatement.setInt(3, classMemberNumber);
                int status = 0;

                try {
                    // 执行SQL语句并返回插入的行数
                    status = preparedStatement.executeUpdate();
                } catch (MySQLIntegrityConstraintViolationException throwables) {
                    // 定义要执行的SQL语句
                    sql = "UPDATE class_score " +
                            "SET class_avg_score = ?, class_menber_number = ? " +
                            "WHERE class_name = ?";

                    // 创建一个prepareStatement对象
                    preparedStatement = connection.prepareStatement(sql);
                    // 为SQL语句中的第一个?设置值
                    preparedStatement.setDouble(1, classAvgScore);
                    // 为SQL语句中的第二个?设置值
                    preparedStatement.setInt(2, classMemberNumber);
                    // 为SQL语句中的第三个?设置值
                    preparedStatement.setString(3, className);
                    status = preparedStatement.executeUpdate();
                }

                // 判断是否更新成功
                if (status > 0) {
                    System.out.println("更新成功");
                } else {
                    System.out.println("数据出现错误！");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            new ErrorDialog("数据更新出错").setVisible(true);
        } finally {
            JdbcUtil.release(connection, preparedStatement, resultSet);
        }
    }
}
