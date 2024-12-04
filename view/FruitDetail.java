package FruitShop.view;

import FruitShop.Entity.Fruit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import static FruitShop.FruitShop.localUrl;

public class FruitDetail extends JFrame {
    public Fruit fruit;
    public static JFrame jFrame;

    public void loadView(Fruit fruit) {
        this.fruit = fruit;

        //创建窗口
        jFrame = new JFrame();
        // 设置窗口标题
        jFrame.setTitle(fruit.getName() + " - 详细信息");
        // 设置窗口大小
        jFrame.setSize(600, 500);
        // 设置窗口居中
        jFrame.setLocationRelativeTo(null);
        // 设置子窗口的关闭操作为只关闭自己
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);




        // 使用BorderLayout布局
        jFrame.setLayout(new BorderLayout());

        // 水果信息面板
        JPanel fruitInfoPanel = new JPanel();
        fruitInfoPanel.setLayout(new GridLayout(1, 2)); // 两列布局

        // 水果图片
        JLabel fruitImageLabel = new JLabel();

        //添加图片
        URL img = null;
        try {
            img = new URL(fruit.getImgUrl());
        } catch (MalformedURLException e) {
            try {
                img = new URL(localUrl, fruit.getImgUrl());
            } catch (MalformedURLException ex) {
                System.out.println("未设置本地图片:" + e.getMessage());
            }
            System.out.println("未设置网络图片:" + e.getMessage());
        }
        ImageIcon image = new ImageIcon(img);
        fruitImageLabel.setIcon(image);

        fruitImageLabel.setPreferredSize(new Dimension(300, 260));
        fruitInfoPanel.add(fruitImageLabel);

        // 水果信息
        JPanel fruitDetailsPanel = new JPanel();
        fruitDetailsPanel.setLayout(new GridLayout(5, 1)); // 五行布局

        JLabel fruitIdLabel = new JLabel("水果ID:");
        fruitDetailsPanel.add(fruitIdLabel);
        JTextField fruitIdField = new JTextField("1");
        fruitIdField.setEnabled(false);
        fruitDetailsPanel.add(fruitIdField);

        JLabel fruitNameLabel = new JLabel("名称:");
        fruitDetailsPanel.add(fruitNameLabel);
        JTextField fruitNameField = new JTextField("苹果");
        fruitNameField.setEnabled(false);
        fruitDetailsPanel.add(fruitNameField);

        JLabel fruitLinkLabel = new JLabel("链接:");
        fruitDetailsPanel.add(fruitLinkLabel);
        JTextField fruitLinkField = new JTextField("http://example.com");
        fruitLinkField.setEnabled(false);
        fruitDetailsPanel.add(fruitLinkField);

        JLabel fruitPriceLabel = new JLabel("价格:");
        fruitDetailsPanel.add(fruitPriceLabel);
        JTextField fruitPriceField = new JTextField("10.00");
        fruitPriceField.setEnabled(false);
        fruitDetailsPanel.add(fruitPriceField);

        JLabel fruitStockLabel = new JLabel("库存:");
        fruitDetailsPanel.add(fruitStockLabel);
        JTextField fruitStockField = new JTextField("100");
        fruitStockField.setEnabled(false);
        fruitDetailsPanel.add(fruitStockField);

        fruitInfoPanel.add(fruitDetailsPanel);

        // 订单信息表格
        String[] columnNames = {"订单ID", "水果ID", "客户ID", "购买数量", "订单日期", "订单状态"};
        Object[][] data = {
                {"1", "1", "1001", "2", "2024-12-04", "已完成"},
                {"2", "2", "1002", "1", "2024-12-05", "待发货"}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable orderTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(orderTable);

        // 将组件添加到主窗口
        jFrame.add(fruitInfoPanel, BorderLayout.NORTH);
        jFrame.add(scrollPane, BorderLayout.CENTER);


//        // 显示一个简单的消息框
//        JOptionPane.showMessageDialog(null, "这是一个简单的消息框", "提示", JOptionPane.INFORMATION_MESSAGE);
//
//        // 显示一个确认对话框，带有“是”、“否”、“取消”按钮
//        int result = JOptionPane.showConfirmDialog(null, "你确定要继续吗？", "确认", JOptionPane.YES_NO_CANCEL_OPTION);
//        if (result == JOptionPane.YES_OPTION) {
//            System.out.println("用户选择了“是”");
//        } else if (result == JOptionPane.NO_OPTION) {
//            System.out.println("用户选择了“否”");
//        } else if (result == JOptionPane.CANCEL_OPTION) {
//            System.out.println("用户选择了“取消”");
//        }

        // 设置窗口可见
        jFrame.setVisible(true);
    }
}
