package FruitShop.view;

import FruitShop.Entity.Fruit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        jFrame.setSize(400, 350);
        // 设置窗口居中
        jFrame.setLocationRelativeTo(null);
        // 设置子窗口的关闭操作为只关闭自己
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // 使用BorderLayout布局
        jFrame.setLayout(new BorderLayout());

        // 水果信息面板
        JPanel fruitInfoPanel = new JPanel();
        fruitInfoPanel.setLayout(new GridLayout(1, 2)); // 两列布局
        // 顶部 margin 10px
        fruitInfoPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        // 水果图片标签
        JLabel fruitImageLabel = getFruitImageLabel(fruit);

        // 水果信息面板
        JPanel fruitDetailsPanel = getFruitDetailsPanel(fruit);

        // 添加到面板
        fruitInfoPanel.add(fruitImageLabel);
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
        // 顶部 margin 10px
        scrollPane.setBorder(new EmptyBorder(10, 0, 0, 0));

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

    private JLabel getFruitImageLabel(Fruit fruit) {
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
        //设置图片大小
        image.setImage(image.getImage().getScaledInstance(150, 130, Image.SCALE_SMOOTH));
        fruitImageLabel.setIcon(image);
        //对齐方式
        fruitImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fruitImageLabel.setVerticalAlignment(SwingConstants.CENTER);

        return fruitImageLabel;
    }

    private static JPanel getFruitDetailsPanel(Fruit fruit) {
        JPanel fruitDetailsPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT); // 设置布局管理器，组件左对齐
        fruitDetailsPanel.setLayout(flowLayout);

        // 添加标签和文本字段，设置文本字段的首选宽度
        JLabel fruitIdLabel = new JLabel("水果ID：");
        JTextField fruitIdField = new JTextField(String.valueOf(fruit.getId()), 11); // 设置文本字段的列数为10，这会影响其宽度
        fruitDetailsPanel.add(fruitIdLabel);
        fruitDetailsPanel.add(fruitIdField);

        String s = "：   ";
        JLabel fruitNameLabel = new JLabel("名称" + s);
        JTextField fruitNameField = new JTextField(fruit.getName(), 11);
        fruitDetailsPanel.add(fruitNameLabel);
        fruitDetailsPanel.add(fruitNameField);

        JLabel fruitLinkLabel = new JLabel("链接" + s);
        JTextField fruitLinkField = new JTextField(fruit.getImgUrl(), 11);
        fruitDetailsPanel.add(fruitLinkLabel);
        fruitDetailsPanel.add(fruitLinkField);

        JLabel fruitPriceLabel = new JLabel("价格" + s);
        JTextField fruitPriceField = new JTextField(String.valueOf(fruit.getPrice()), 11);
        fruitDetailsPanel.add(fruitPriceLabel);
        fruitDetailsPanel.add(fruitPriceField);

        JLabel fruitStockLabel = new JLabel("库存" + s);
        JTextField fruitStockField = new JTextField(String.valueOf(fruit.getStock()), 11);
        fruitDetailsPanel.add(fruitStockLabel);
        fruitDetailsPanel.add(fruitStockField);
        
        return fruitDetailsPanel;
    }
}
