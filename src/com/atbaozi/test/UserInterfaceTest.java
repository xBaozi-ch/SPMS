package com.atbaozi.test;

import com.atbaozi.userui.UserInterface;
import org.junit.Test;

/**
 * 用于测试UserInterface类
 * @ClassName userInterfaceTest
 * @Author xBaozi
 * @Date 2021/12/7 19:22
 * @Version 1.0
 */
public class UserInterfaceTest {
    @Test
    public void Test1() throws InterruptedException {
        new UserInterface("学生成绩管理系统").initInterfaceLayout();
        Thread.sleep(100000);
    }
}
