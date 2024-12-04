package FruitShop.view;

import FruitShop.Entity.Fruit;
import FruitShop.FruitShop;
import FruitShop.dao.JdbcFruit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import static FruitShop.FruitShop.localUrl;

//中心类封装
public class Center {
    //暴露网格布局管理器
    public GridLayout gridLayout;
    //行数和列数
    public int rows, cols;
    //格子总大小
    public int width, height;
    //格子面板
    public JPanel GridJPanel;
    //面板数组
    public  JPanel[] JPfruits;
    //当前页码
    public int PageNum = 1;
    //总页码
    public int PageSum;
    //水果详情界面
    FruitDetail fruitDetail;

    public Center(Container container, int rows, int cols, int hgap, int vgap, int width, int height) {
        this.rows = rows;
        this.cols = cols;
        this.width = width;
        this.height = height;
        // 创建水果详情类
        fruitDetail = new FruitDetail();

        //创建网格布局（行，列，行间距，列间距）
        gridLayout = new GridLayout(rows, cols, hgap, vgap);
        GridJPanel = new JPanel(gridLayout);

        //添加布局到内容面板的东部
        container.add(GridJPanel, BorderLayout.CENTER);
        //设置首选大小
        GridJPanel.setPreferredSize(new Dimension(width, height));
    }

    //把水果包装为面板
    public JPanel[] PackFruits(ArrayList<Fruit> fruits, boolean state) {
        int i = 0;
        JPanel[] jPanels = new JPanel[fruits.size()];
        for (Fruit fruit : fruits) {
            JPanel jPanel = new JPanel();
            //自定义布局
            jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            jPanel.setBackground(Color.lightGray);
            //拼接水果信息
            String news = "<html><body>ID:" + fruit.getId() + "<br>名字:" + fruit.getName() + "<br><br>价格:" + fruit.getPrice() + "<br><br>库存:" + fruit.getStock() + "</body></html>";
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
            JLabel jLabel = new JLabel(news);
            ImageIcon image = new ImageIcon(img);
            //设置图片大小
            image.setImage(image.getImage().getScaledInstance(width / cols / 2, height / rows - 25, Image.SCALE_SMOOTH));
            //设置jLabel的图片
            jLabel.setIcon(image);
            //对齐方式
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setVerticalAlignment(SwingConstants.CENTER);
            //把标签放入面板
            jPanel.add(jLabel);

            // 为每个水果添加点击事件
            jPanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // 创建新的窗口，展示水果的详细信息
                    fruitDetail.loadView(fruit);
                }
                @Override
                public void mousePressed(MouseEvent e) {

                }
                @Override
                public void mouseReleased(MouseEvent e) {

                }
                @Override
                public void mouseEntered(MouseEvent e) {

                }
                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

            //面板压入数组
            jPanels[i] = jPanel;
            //设置首选大小
            jPanel.setPreferredSize(new Dimension(width, height));
            i++;
        }
        if (state) {
            this.JPfruits = jPanels;
            //更新页面总数(向上取整)
            this.PageSum = JPfruits.length / (rows * cols) + (JPfruits.length % (rows * cols) != 0 ? 1 : 0);
            System.out.println(PageSum);
        }
        return jPanels;
    }

    //根据 JPfruits 数组 把当前页面板绘制到网格里
    public void AddPageJPanelToGrid() {
        int i = 0;
        for (JPanel jPanel : JPfruits) {
            //判断当前页的控件
            if (i >= rows * cols * (PageNum - 1) && i < rows * cols * PageNum) {
                //System.out.println("I:" + i + "     左:" + rows * cols * (PageNum - 1) + "     右:" + rows * cols * PageNum);
                GridJPanel.add(jPanel);
            }
            i++;
        }
        //更新页面总数(向上取整)
        this.PageSum = JPfruits.length / (rows * cols) + (JPfruits.length % (rows * cols) != 0 ? 1 : 0);
        //System.out.println(PageSum);
    }

    //清空并再次添加后刷新
    public void f5() {
        //删除所有控件
        GridJPanel.removeAll();
        //重新添加
        AddPageJPanelToGrid();
        //对面板中的组件重新布局并绘制
        GridJPanel.revalidate();
        //对本身进行重绘
        GridJPanel.repaint();
    }

    //【修改】数组中某一项
    private JPanel[] jpArrXg(JPanel x, int id) {
        JPanel[] J = JPfruits;
        JPanel[] JJ = new JPanel[J.length];
        for (int i = 0; i < J.length; i++) {
            if (i == id - 1) {
                JJ[i] = x;
            } else {
                JJ[i] = J[i];
            }
        }
        return JJ;
    }
}