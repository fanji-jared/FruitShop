package FruitShop.util;

import FruitShop.view.Center;
import FruitShop.view.East;
import FruitShop.view.North;
import FruitShop.view.West;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class viewNorth {
    public static void AddListeners(East east, North north, West west, Center center, JTextField jTextXGField, JTextField jTextTJField) {
        //为【查看】按钮添加事件
        north.ck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("查看按钮按下");
                System.out.println("切换界面：" + east.NumFoCard(1));
            }
        });
        //为【修改】按钮添加事件
        north.xg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("修改按钮按下");
                System.out.println("切换界面：" + east.NumFoCard(2));
                // 更新id
                jTextXGField.setText(String.valueOf(center.JPfruits.length));
//                east.f5(north, center, west, jTextXGField);
            }
        });
        //为【添加】按钮添加事件
        north.tj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("添加按钮按下");
                System.out.println("切换界面：" + east.NumFoCard(3));
                // 更新id
                jTextTJField.setText(String.valueOf(center.JPfruits.length + 1));
//                east.f5(north, center, west, jTextTJField);
            }
        });
        //为【删除】按钮添加事件
        north.sc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("删除按钮按下");
                System.out.println("切换界面：" + east.NumFoCard(4));
            }
        });
    }
}
