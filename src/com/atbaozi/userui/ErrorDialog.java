package com.atbaozi.userui;

import javax.swing.*;
import java.awt.*;

/**
 * 错误信息弹窗
 * @ClassName ErrorDialog
 * @Description 错误信息弹窗。用于提示用户操作出错。
 * @Author xBaozi
 * @Date 2021/12/30 16:15
 * @Version 1.0
 */
public class ErrorDialog extends JDialog{
    public ErrorDialog() {
        super(Main.userInterface, "出错了", true);
        setBounds(590, 369, 270, 138);
    }
    public ErrorDialog(String errorInfo) {
        this();
        Container container = getContentPane();
        JLabel jLabel = new JLabel("Error: " + errorInfo);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(jLabel);
        ImageIcon icon = new ImageIcon("img\\警告.png");
        // 给窗体设置图标方法
        setIconImage(icon.getImage());
    }
}
