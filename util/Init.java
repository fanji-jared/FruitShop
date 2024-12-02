package FruitShop.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Init {
    //按钮初始化
    public static void ButtonInit(JButton j) {
        //设置内容区域不填充
        //j.setContentAreaFilled(false);
        j.setBackground(Color.white);
        //关闭默认边框
        j.setBorderPainted(false);
        //获得焦点不显示边框
        j.setFocusPainted(false);
        j.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                j.setBackground(Color.gray);
                //设置文本颜色
                j.setForeground(Color.white);
            }

            @Override
            public void focusLost(FocusEvent e) {
                j.setBackground(Color.white);
                //设置文本颜色
                j.setForeground(Color.black);
            }
        });
    }

    //JTextField 输入框焦点监听
    public static void addJTextFocus(JTextField[] jTextFields) {
        for (JTextField jTextField : jTextFields) {
            jTextField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    jTextField.selectAll();//选择所有的文本
                }

                @Override
                public void focusLost(FocusEvent e) {
                }
            });
        }
    }
}
