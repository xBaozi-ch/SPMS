package com.atbaozi.handler;

import com.atbaozi.pojo.Student;
import com.atbaozi.userui.ErrorDialog;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

/**
 * 用于接收用户界面传入的目录，通过文件相关操作循环处理该目录下的全部txt文件。DirectoryHandler类包含一个名为directoryHandler的方法。
 * @ClassName DirectoryHandler
 * @Description 用于接收用户界面传入的目录，通过文件相关操作循环处理该目录下的全部txt文件。
 * @Author xBaozi
 * @Date 2021/12/7 18:36
 * @Version 1.0
 */
public class DirectoryHandler {
    /**
     * CLASS_NUMBER：年级中班级的总数
     **/
    static final int CLASS_NUMBER = 4;

    /**
     * allClassStudentCount：所有班级成员的个人
     **/
    private int allClassStudentCount = 0;

    /**
     * allClassStudentScore：整个年级的成绩总和
     **/
    private double allClassStudentScore = 0.0;

    /**
     * directoryFile：存放目标目录
     **/
    private File directoryFile = null;

    /**
     * hmClassScore：用于存放班级对应的成绩
     **/
    private HashMap<String, Double> hmClassScore = null;

    /**
     * hmClassMemberNum：用于存放班级对应的成员人数
     **/
    private HashMap<String, Integer> hmClassMemberNum = null;

    /**
     * 在方法体中调用fileHandler()方法处理文件数据，调用databaseHandler将数据存入数据库。调用FileHandler类中的fileHandler方法来读取txt文件内容,并调用DatabaseHandler类中的databaseHandler方法进行各班平均分统计。
     * @Author xBaozi
     * @Description 调用FileHandler类中的fileHandler方法来读取txt文件内容,并调用DatabaseHandler类中的databaseHandler方法进行各班平均分统计。
     * @Date 18:41 2021/12/7
     * @Param [directory]
     * @return java.lang.Double
     **/
    public Double directoryHandler(String directory) {
        // 新建文件夹对象
        directoryFile = new File(directory);
        // 文件夹下的文件形成的数组
        File[] files = directoryFile.listFiles();
        if (files == null) {
            System.out.println("路劲不存在!");
            new ErrorDialog("路劲不存在!").setVisible(true);
            return null;
        }
        // 用于存放每个班级对应的平均分
        hmClassScore = new HashMap<String, Double>(CLASS_NUMBER);
        // 用于存放每个班级对应的人数
        hmClassMemberNum = new HashMap<String, Integer>(CLASS_NUMBER);

        // 利用数组进行循环初始化集合
        String[] allClass = {"软件1班", "软件2班", "软件3班", "软件4班"};
        for (int i = 0; i < CLASS_NUMBER; i++) {
            // 设置每个班的初始成绩是0
            hmClassScore.put(allClass[i], 0.0);
            // 设置每个班的初始人数是0
            hmClassMemberNum.put(allClass[i], 0);
        }
        for(File file : files) {
            try {
                // 通过逐个调用fileHandler方法获得目录下文件中的内容
                List<Student> students = new FileHandler().fileHandler(file.getAbsolutePath());
                if (students == null) {
                    new ErrorDialog("文件夹为空!").setVisible(true);
                    return null;
                }
                for (Student student : students) {
                    // 通过班级key对班级人数进行更新
                    hmClassMemberNum.put(student.getClassName(), hmClassMemberNum.get(student.getClassName()) + 1);
                    // 通过班级key对班级成绩进行更新
                    hmClassScore.put(student.getClassName(), hmClassScore.get(student.getClassName()) + student.getGrade());
                    allClassStudentCount++;
                    allClassStudentScore += student.getGrade();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                // 实例化错误信息提示弹窗，并返回null值
                new ErrorDialog("文件打开错误!").setVisible(true);
                return null;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                // 实例化错误信息提示弹窗，并返回null值
                new ErrorDialog("文件已损坏!").setVisible(true);
                return null;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                // 实例化错误信息提示弹窗，并返回null值
                new ErrorDialog("文件打开错误!").setVisible(true);
                return null;
            }
        }

        // 调用DatabaseHandler类中的databaseHandler方法将数据存入数据库
        new DatabaseHandler().databaseHandler(this);

        // 返回保留两位小数的平均值
        return Double.parseDouble(new DecimalFormat("0.00")
                .format(allClassStudentScore/allClassStudentCount));
    }

    /**
     * 返回整个年级的学生个数
     * @Author xBaozi
     * @Description 返回整个年级的学生个数
     * @Date 13:37 2021/12/9
     * @Param []
     * @return int
     **/
    public int getAllClassStudentCount() {
        return allClassStudentCount;
    }

    /**
     * 返回整个年级的总成绩
     * @Author xBaozi
     * @Description 返回整个年级的总成绩
     * @Date 13:38 2021/12/9
     * @Param []
     * @return double
     **/
    public double getAllClassStudentScore() {
        return allClassStudentScore;
    }

    /**
     * 返回年级中每个班级的班名及其班级的总成绩
     * @Author xBaozi
     * @Description 返回年级中每个班级的班名及其班级的总成绩
     * @Date 13:38 2021/12/9
     * @Param []
     * @return java.util.HashMap<java.lang.String,java.lang.Double>
     **/
    public HashMap<String, Double> getHmClassScore() {
        return hmClassScore;
    }

    /**
     * 返回年级中每个班级的班名及其班级的总人数
     * @Author xBaozi
     * @Description 返回年级中每个班级的班名及其班级的总人数
     * @Date 13:42 2021/12/9
     * @Param []
     * @return java.util.HashMap<java.lang.String,java.lang.Integer>
     **/
    public HashMap<String, Integer> getHmClassMemberNum() {
        return hmClassMemberNum;
    }
}
