package com.atbaozi.test;

import com.atbaozi.handler.DirectoryHandler;
import java.util.Map.Entry;
import org.junit.Test;

import java.util.HashMap;

/**
 * 用于DirectoryHandler类的测试
 * @ClassName DirectoryHandlerTest
 * @Author xBaozi
 * @Date 2021/12/8 11:50
 * @Version 1.0
 */
public class DirectoryHandlerTest {
    /**
     * 用于测试该类是否可以正常运行
     * @Author xBaozi
     * @Date 13:51 2021/12/9
     * @Param []
     * @return void
     **/
    @Test
    public void Test1() {
        System.out.println(new DirectoryHandler().directoryHandler("D:\\Test"));
    }
    /**
     * 用于测试该类中的集合
     * @Author xBaozi
     * @Date 13:51 2021/12/9
     * @Param []
     * @return void
     **/
    @Test
    public void Test2() {
        DirectoryHandler directoryHandler = new DirectoryHandler();
        System.out.println(directoryHandler.directoryHandler("D:\\Test"));
        HashMap<String, Double> hmClassScore = directoryHandler.getHmClassScore();
        HashMap<String, Integer> hmClassMemberNum = directoryHandler.getHmClassMemberNum();
        for (Entry<String, Double> entry : hmClassScore.entrySet())
        {
            System.out.println("{" + entry.getKey() + "," + entry.getValue() + "," + hmClassMemberNum.get(entry.getKey()) + "}");
        }
    }
}
