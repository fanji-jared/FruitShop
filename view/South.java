package FruitShop.view;

import javax.swing.*;
import java.awt.*;

//南部类封装
public class South {
    public South(Container container, String LabeText, int width, int height) {
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel jLabel = new JLabel(LabeText);
        //添加lable
        jPanel.add(jLabel);
        //添加布局到内容面板的东部
        container.add(jPanel, BorderLayout.SOUTH);
        //设置首选大小
        jPanel.setPreferredSize(new Dimension(width, height));
    }
}
