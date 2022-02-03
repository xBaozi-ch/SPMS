package com.atbaozi.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 用于配置数据库
 * @ClassName JdbcUtil
 * @Author xBaozi
 * @Date 2021/12/9 11:08
 * @Version 1.0
 */
public class JdbcUtil {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {
        try {
            // 读取dp.properties文件中的配置信息
            InputStream resourceAsStream = JdbcUtil.class.getClassLoader().getResourceAsStream("dp.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);

            // 获取数据库链接驱动
            driver = properties.getProperty("driver");

            // 获取数据库连接URL地址
            url = properties.getProperty("url");

            // 获取数据库连接用户名
            username = properties.getProperty("username");

            // 获取数据库连接密码
            password = properties.getProperty("password");

            // 加载数据库驱动
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象获取数据库连接对象
     * @Author xBaozi
     * @Date 16:26 2021/12/14
     * @Param []
     * @return java.sql.Connection
     **/
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 释放资源
     * @Author xBaozi
     * @Date 16:25 2021/12/14
     * @Param [connection, statement, resultSet]
     * @return void
     **/
    public static void release(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
