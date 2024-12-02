package FruitShop.view;

import FruitShop.FruitShop;
import FruitShop.util.Init;

import javax.swing.*;
import java.awt.*;

//北部类封装
public class North {
    public JPanel NorthJpanel;

    //暴露按钮，为了添加事件
    public JButton ck;
    public JButton xg;
    public JButton tj;
    public JButton sc;

    private Container container;

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public North(Container container, int width, int height) {
        setContainer(container);
        //创建按钮
        ck = new JButton("查  看");
        xg = new JButton("修  改");
        tj = new JButton("添  加");
        sc = new JButton("删  除");
        JButton[] jButtons = new JButton[]{ck, xg, tj, sc};
        NorthJpanel = new JPanel();
        //居中排列 左右和上下margin
        NorthJpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
        //初始化按钮
        NorthButInit(NorthJpanel, jButtons);
        //添加流式布局到内容面板的北部
        getContainer().add(NorthJpanel, BorderLayout.NORTH);
        //设置首选大小
        NorthJpanel.setPreferredSize(new Dimension(width, height));
    }
    //添加按钮
    private void NorthButInit(Object box, JButton[] jButton) {
        for (JButton j : jButton) {

            Init.ButtonInit(j);
            if (box instanceof JPanel Panel) {
                Panel.add(j);
            }
        }
    }
}