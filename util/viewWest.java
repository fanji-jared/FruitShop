package FruitShop.util;

import FruitShop.view.Center;
import FruitShop.view.West;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class viewWest {
    public static void AddListeners(West west, Center center) {
        //为上一页添加点击事件
        west.PageUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (center.PageNum > 1) {
                    center.PageNum -= 1;
                    center.f5();
                    //刷新 当前页数/总页数
                    west.PageNum.setText(center.PageNum + " / " + center.PageSum);
                } else {
                    System.out.println("没有前面一页了");
                }
            }
        });
        //为下一页添加点击事件
        west.PageDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (center.PageNum < center.PageSum) {
                    center.PageNum += 1;
                    center.f5();
                    //刷新页数/总页数
                    west.PageNum.setText(center.PageNum + " / " + center.PageSum);
                } else {
                    System.out.println("没有后面一页了");
                }
            }
        });
    }
}
