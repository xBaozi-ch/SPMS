package com.atbaozi.test;

import com.atbaozi.handler.FileHandler;
import com.atbaozi.pojo.Student;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * 用于FileHandler类的测试
 * @ClassName FileHandlerTest
 * @Author xBaozi
 * @Date 2021/12/9 10:08
 * @Version 1.0
 */
public class FileHandlerTest {
    @Test
    public void Test1() {
        try {
            List<Student> students = new FileHandler().fileHandler("D:\\Test\\class1.txt");
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
