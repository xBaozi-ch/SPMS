package com.atbaozi.userui;

/**
 * 系统入口
 * @ClassName Main
 * @Description 整个系统运行的入口
 * @Author xBaozi
 * @Date 2021/12/9 19:57
 * @Version 1.0
 */
public class Main {
    public static UserInterface userInterface = null;
    public static void main(String[] args) {
        userInterface = new UserInterface("SPMS-学生成绩管理系统");
        userInterface.initInterfaceLayout();
    }
}
