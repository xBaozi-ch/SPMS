package com.atbaozi.handler;

import com.atbaozi.pojo.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于处理文件中的数据。
 * @ClassName FileHandler
 * @Description 用于处理文件中的数据。
 * @Author xBaozi
 * @Date 2021/12/7 18:37
 * @Version 1.0
 */
public class FileHandler {
    /**
     * BufferedReader: 用于读取数据
     */
    private BufferedReader bis = null;

    /**
     * fileConcent: 用于容纳学生对象
     */
    private List<Student> fileConcent = null;

    /**
     * 接收由文件目录和文件名组成的字符串参数，返回一个List泛型对象，List中存储的每一个Student对象就是一个学生实体对象，对应txt文件中一条学生记录。
     * @Author xBaozi
     * @Description 接收由文件目录和文件名组成的字符串参数，返回一个List泛型对象，
     *      List中存储的每一个Student对象就是一个学生实体对象，对应txt文件中一条学生记录。
     * @Date 18:44 2021/12/7
     * @Param [fileName] 接收由文件目录和文件名组成的字符串参数
     * @return java.util.List<com.atbaozi.pojo.Student> 返回一个List泛型对象，List中存储的每一个Student对象
     *      就是一个学生实体对象，对应txt文件中一条学生记录。
     **/
    public List<Student>fileHandler(String fileName) throws IOException {
        // 设置编码GBK，否则可能出现编码错误
        bis = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "GBK"));
        fileConcent = new ArrayList<Student>();
        try {
            // 存放文件中的每一行数据
            String read = null;
            while ((read = bis.readLine()) != null) {
                // 读取到的每一行信息根据空格进行切割并放到数组中
                String[] studentInfos = read.split(" ");
                // 容纳班级名称的临时变量
                String stuClassName = null;
                // 容纳学生名字的临时变量
                String stuName = null;
                // 容纳学生成绩的临时变量
                double stuGrade = 0;
                try {
                    // 单条记录中学生的班级
                    stuClassName = studentInfos[0];
                    // 单条记录中学生的名字
                    stuName = studentInfos[1];
                    // 单条记录中学生的分数
                    stuGrade = Double.parseDouble(studentInfos[2]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new NumberFormatException("请选择正确的文件");
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("请选择正确的文件");
                }
                // 将单条数据封装成学生对象添加到集合中
                fileConcent.add(new Student(stuClassName, stuName, stuGrade));
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("文件可能已损坏!");
        } finally {
            try {
                // 关闭文件流
                if(bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                throw new IOException("文件关闭出错!");
            }
        }
        return fileConcent;
    }
}
