package com.atbaozi.userui;

import com.atbaozi.handler.DirectoryHandler;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 图形用户界面，用于接收文件存放在目录，以及整个年级的平均分显示。该图形用户界面实现了事件处理，可以响应用户的操作。
 * @ClassName userInterface
 * @author xBaozi
 * @Date 2021/12/7 18:38
 * @version 1.0
 */
public class UserInterface extends JFrame {
    /**
     * filePathChoose：文件夹所在路径的文本框
     **/
    private JTextField filePathChoose = null;

    /**
     * resultText：结果输出框
     **/
    private JTextField resultText = null;

    /**
     * 通过构造器传递窗体的标题
     * @Author xBaozi
     * @Date 19:37 2021/12/7
     * @Param [title]
     **/
    public UserInterface(String title) {
        super(title);
    }

    /**
     * 用于初始化窗体界面， 通过定义四个容器，分别容纳选择文件提示语的文本框、选择文件的组件、统计结果的的文本框和确定按钮
     * @Author xBaozi
     * @Date 19:53 2021/12/7
     * @Param []
     * @return void
     **/
    public void initInterfaceLayout() {
        // 分别定义初始界面中的四个容器
        // 用于存放选择文件提示语的文本框并左对齐
        JPanel pathTextJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        // 用于存放选择文件的组件并居中
        JPanel filePathJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        // 用于存放显示结果的文本框并左对齐
        JPanel resultTextJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        // 用于存放确认按钮并居中
        JPanel confirmButtonJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        // 定义存放选择文件提示语的文本框
        JTextField pathText = new JTextField("请输入你要解析的文件所在的路径：");
        // 设置文本框不可写
        pathText.setEditable(false);
        // 取消文本边框
        pathText.setBorder(null);

        // 定义存放选择文件的组件
        filePathChoose = new JTextField();
        filePathChoose.setPreferredSize(new Dimension(300,25));
        filePathChoose.setBorder(new MatteBorder(2, 2, 1, 1, Color.DARK_GRAY));

        // 定义打开文件夹的组件
        JButton jbtOpen = new JButton("打开文件夹");
        // 使用匿名内部类为“打开文件夹”按钮添加事件
        jbtOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 定义一个文件选择器
                JFileChooser jFileChooser = new JFileChooser();
                // 获得FileSystemView的一个实例
                FileSystemView fileSystemView = FileSystemView.getFileSystemView();
                // 获取桌面的路径并将其设置为打开文件选择器的默认路径
                jFileChooser.setCurrentDirectory(fileSystemView.getHomeDirectory());
                // 获取当前路径并将其设置为打开文件选择器的默认路径
                // jFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                // 设置只能选择文件夹
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                /*
                    若showOpenDialog中参数为null，则打开的对话框处于电脑显示屏的中央
                    若showOpenDialog中参数为this，则打开的对话框处于编写的程序屏幕中央
                    showOpenDialog调用之后会返回一个值
                        1. 返回0，代表已经选择了某个文件或文件夹
                        2. 返回1，代表选择了取消按钮或直接关闭了窗口
                        3. JFileChooser.APPROVE_OPTION为一个常量，代表着情况1
                 */
                if (jFileChooser.showOpenDialog(UserInterface.this) == JFileChooser.APPROVE_OPTION) {
                    // 获取已经选中的文件夹
                    File selectedFile = jFileChooser.getSelectedFile();
                    // 将选中的文件的绝对路径同步到文本框中
                    filePathChoose.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        // 定义存放统计结果的的文本框
        resultText = new JTextField("统计结果为：", 20);
        // 设置文本框不可写
        resultText.setEditable(false);
        // 取消文本边框
        resultText.setBorder(null);

        // 定义确定按钮
        JButton confirmButton = new JButton("确定");
        // 通过构件设置按钮的大小
        confirmButton.setPreferredSize(new Dimension(150,30));
        // 绑定点击事件进行计算
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double result = new DirectoryHandler().directoryHandler(filePathChoose.getText());
                if (result == null) {
                    resultText.setText("统计结果为：null");
                } else {
                    resultText.setText("统计结果为：" + result);
                }
                System.out.println("统计结果为：" + result);
            }
        });

        /*
            分别将四个组件添加到JPanel容器中
         */
        // 选择文件提示语
        pathTextJPanel.add(pathText);
        // 文件选择组件
        filePathJPanel.add(filePathChoose);
        // 文件夹选择器
        filePathJPanel.add(jbtOpen);
        // 统计结果文本框
        resultTextJPanel.add(resultText);
        // 确认按钮
        confirmButtonJPanel.add(confirmButton);

        // 获取一个容器并设置其布局管理器
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4, 1));

        /*
            分别将四个JPanel容器添加到container容器中
         */
        // 添加存放选择文件提示语的文本框的JPanel容器
        container.add(pathTextJPanel);
        // 添加存放选择文件的组件的JPanel容器
        container.add(filePathJPanel);
        // 添加存放显示结果的文本框的JPanel容器
        container.add(resultTextJPanel);
        // 添加存放确认按钮的JPanel容器
        container.add(confirmButtonJPanel);

        // 设置窗体大小并固定、默认关闭方式及可视化
        this.setResizable(false);
        this.setBounds(500, 300, 450, 230);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        // 设置窗体图标
        ImageIcon icon = new ImageIcon("img\\成绩管理.png");
        // 给窗体设置图标方法
        setIconImage(icon.getImage());
    }

    /**
     * 获取路径文本框组件
     * @Author xBaozi
     * @Date 23:26 2021/12/7
     * @Param []
     * @return javax.swing.JTextField
     **/
    public JTextField getFilePathChoose() {
        return filePathChoose;
    }

    /**
     * 获取结果文本框组件
     * @Author xBaozi
     * @Date 23:27 2021/12/7
     * @Param []
     * @return javax.swing.JTextField
     **/
    public JTextField getResultText() {
        return resultText;
    }

    /**
     * 设置结果文本框组件
     * @Author xBaozi
     * @Date 23:27 2021/12/7
     * @Param [resultText]
     * @return void
     **/
    public void setResultText(JTextField resultText) {
        this.resultText = resultText;
    }
}
