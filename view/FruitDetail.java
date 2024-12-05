package FruitShop.view;

import FruitShop.Entity.Fruit;
import FruitShop.dao.JdbcFruit;
import FruitShop.util.viewFruitDetailListen;
import FruitShop.util.Init;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import static FruitShop.FruitShop.localUrl;

public class FruitDetail extends JFrame {
    // 当前选中的水果
    public Fruit nowFruit;
    // 主窗口
    public static JFrame jFrame;
    // 翻页部件
    public static JScrollPane scrollPane;

    public void loadView(Fruit fruit) {
        this.nowFruit = fruit;

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
        // 将组件添加到主窗口
        jFrame.add(fruitInfoPanel, BorderLayout.NORTH);

        // 订单信息表格
        scrollPane = getjScrollPane(fruit);
        // 将组件添加到主窗口
        jFrame.add(scrollPane, BorderLayout.CENTER);

        // 翻页部件
        JPanel paginaPanel = getPaginaPanel(fruit);
        // 添加到主窗口
        jFrame.add(paginaPanel, BorderLayout.SOUTH);


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

    /**
     * 根据水果对象查询并建立滚动Table组件
     * @return JLabel 水果对象
     */
    private static JScrollPane getjScrollPane(Fruit fruit) {
        // 订单信息表格
        String[] columnNames = {"订单ID", "水果ID", "客户ID", "购买数量", "订单日期", "订单状态"};
        /*
        Object[][] data = {
                {"1", "1", "1001", "2", "2024-12-04", "已完成"},
                {"2", "2", "1002", "1", "2024-12-05", "待发货"}
        };
        */
        // 查询获取到当前页的表格数据
        Object[][] data = JdbcFruit.getOrdersData(fruit, viewFruitDetailListen.offset, viewFruitDetailListen.pageSize);
        // 创建表格模型
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        // 为 "客户ID" 列的每个值设置点击事件
        viewFruitDetailListen.AddListeners(model);
        // 创建表格
        JTable orderTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        // 顶部 margin 10px
        scrollPane.setBorder(new EmptyBorder(10, 0, 0, 0));
        return scrollPane;
    }

    // 刷新表格
    public static void refScrollPane(Fruit nowFruit) {
        //删除所有控件
        scrollPane.removeAll();
        //重新添加
        scrollPane = getjScrollPane(nowFruit);
        // 将组件添加到主窗口
        jFrame.add(scrollPane, BorderLayout.CENTER);
        //对面板中的组件重新布局并绘制
        scrollPane.revalidate();
        //对本身进行重绘
        scrollPane.repaint();
    }

    /**
     * 获取翻页部件 （包含上一页、当前页、总页数、下一页）
     * @return JPanel 翻页部件
     */
    private static JPanel getPaginaPanel(Fruit nowFruit) {
        // 翻页部件
        JPanel paginaPanel = new JPanel();
        paginaPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        // 上一页按钮
        JButton LPageButton = new JButton("上一页");
        try {
            LPageButton.setIcon(new ImageIcon(new URL(localUrl + "images/l.png")));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Init.ButtonInit(LPageButton);

        // m / n 的 m，一个空的输入框，显示当前页
        JTextField currentPageField = new JTextField(3);
        // 显示默认当前页
        currentPageField.setText(String.valueOf(viewFruitDetailListen.currentPage));
        // m / n 的 /
        JLabel slashLabel = new JLabel(" / ");
        // m / n 的 n，一锁定的输入框，显示总页数
        JTextField totalPagesField = new JTextField(3);
        totalPagesField.setEditable(false);

        // 计算并设置总页数
        totalPagesField.setText(String.valueOf(JdbcFruit.getOrdersCount(nowFruit) / viewFruitDetailListen.pageSize + 1));

        // 下一页按钮
        JButton RPageButton = new JButton("下一页");
        try {
            RPageButton.setIcon(new ImageIcon(new URL(localUrl + "images/r.png")));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Init.ButtonInit(RPageButton);

        // 添加监听事件
        viewFruitDetailListen.AddListeners(nowFruit, LPageButton, currentPageField, totalPagesField, RPageButton);

        // 添加到翻页部件
        paginaPanel.add(LPageButton);
        paginaPanel.add(currentPageField);
        paginaPanel.add(slashLabel);
        paginaPanel.add(totalPagesField);
        paginaPanel.add(RPageButton);
        return paginaPanel;
    }

    //

    /**
     * 获取水果详情面板 （包含图片和详情）
     * @param fruit 水果对象
     * @return JPanel 水果详情面板
     */
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

    /**
     * 获取水果详情面板 （包含图片和详情）
     * @param fruit 水果对象
     * @return JPanel 水果详情面板
     */
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
