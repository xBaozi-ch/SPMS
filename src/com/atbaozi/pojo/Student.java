package com.atbaozi.pojo;

import java.util.Objects;

/**
 * 用于存放学生考试成绩表中的基本信息
 * @ClassName Student
 * @Description 用于存放学生考试成绩表中的基本信息
 * @Author xBaozi
 * @Date 2021/12/7 18:37
 * @Version 1.0
 */
public class Student {
    /**
     * className: 班级名称
     **/
    private String className;

    /**
     * stuName: 学生名字
     **/
    private String stuName;

    /**
     * grade: 学生成绩
     **/
    private double grade;

    /**
     * 无参构造器
     * @Author xBaozi
     * @Description 无参构造器
     * @Date 18:59 2021/12/7
     * @Param []
     **/
    public Student() {}

    /**
     * 提供有参构造器初始化成员变量
     * @Author xBaozi
     * @Description 提供有参构造器初始化成员变量
     * @Date 18:59 2021/12/7
     * @Param [className, stuName, grade]
     **/
    public Student(String className, String stuName, double grade) {
        this.className = className;
        this.stuName = stuName;
        this.grade = grade;
    }

    /**
     * 获取班级名称
     * @Author xBaozi
     * @Description 获取班级名称
     * @Date 19:00 2021/12/7
     * @Param []
     * @return java.lang.String
     **/
    public String getClassName() {
        return className;
    }

    /**
     * 设置学生所在班级名称
     * @Author xBaozi
     * @Description 设置学生所在班级名称
     * @Date 19:00 2021/12/7
     * @Param [className]
     * @return void
     **/
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 获取学生名字
     * @Author xBaozi
     * @Description 获取学生名字
     * @Date 19:00 2021/12/7
     * @Param []
     * @return java.lang.String
     **/
    public String getStuName() {
        return stuName;
    }

    /**
     * 设置学生名字
     * @Author xBaozi
     * @Description 设置学生名字
     * @Date 19:01 2021/12/7
     * @Param [stuName]
     * @return void
     **/
    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    /**
     * 获取学生成绩
     * @Author xBaozi
     * @Description 获取学生成绩
     * @Date 19:01 2021/12/7
     * @Param []
     * @return double
     **/
    public double getGrade() {
        return grade;
    }

    /**
     * 设置学生成绩
     * @Author xBaozi
     * @Description 设置学生成绩
     * @Date 19:01 2021/12/7
     * @Param [grade]
     * @return void
     **/
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * 重写equals方法
     * @Author xBaozi
     * @Description 重写equals方法
     * @Date 18:58 2021/12/7
     * @Param [o]
     * @return boolean
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Double.compare(student.grade, grade) == 0 &&
                Objects.equals(className, student.className) &&
                Objects.equals(stuName, student.stuName);
    }

    /**
     * 重写hashCode方法
     * @Author xBaozi
     * @Description 重写hashCode方法
     * @Date 18:58 2021/12/7
     * @Param []
     * @return int
     **/
    @Override
    public int hashCode() {
        return Objects.hash(className, stuName, grade);
    }

    /**
     * 重写toString方法
     * @Author xBaozi
     * @Description 重写toString方法
     * @Date 18:58 2021/12/7
     * @Param []
     * @return java.lang.String
     **/
    @Override
    public String toString() {
        return "Student{" +
                "className='" + className + '\'' +
                ", stuName='" + stuName + '\'' +
                ", grade=" + grade +
                '}';
    }
}
