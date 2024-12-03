package FruitShop.view;

import FruitShop.Entity.Fruit;
import FruitShop.FruitShop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
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
    //数组
    public JPanel[] JPfruits;
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
    public JPanel[] PackFruits(Fruit[] fruits, boolean state) {
        int i = 0;
        JPanel[] jPanels = new JPanel[fruits.length];
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
                    throw new RuntimeException(ex);
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
            int finalI = i;
            jPanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // 创建新的窗口，展示水果的详细信息
                    fruitDetail.loadView(fruits[finalI]);
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

    /*//【删除】指定id的水果面板
    public void removeID(int id) {
        //删除控件数组
        this.JPfruits = jpArrDel(id);
        f5();
        //更新页面总数(向上取整)
        this.PageSum = JPfruits.length / (rows * cols) + (JPfruits.length % (rows * cols) != 0 ? 1 : 0);
    }

    //【删除】数组中某一项
    private JPanel[] jpArrDel(int id) {
        JPanel[] J = JPfruits;
        JPanel[] JJ = new JPanel[J.length - 1];
        for (int i = 0; i < J.length - 1; i++) {
            if (i == id - 1) {
                JJ[i] = J[i + 1];
            } else {
                JJ[i] = J[i];
            }
        }
        return JJ;
    }*/

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

    //判断并修改相应属性
    public void XgJPShu(Fruit[] fruits, int id, String name, String imgUrl, double pri, double sto) {
        this.JPfruits = jpArrXg(PackFruits(new Fruit[]{new Fruit(id, name.equals("#") ? fruits[id - 1].getName() : name, imgUrl.equals("#") ? fruits[id - 1].getImgUrl() : imgUrl, pri < 0 ? fruits[id - 1].getPrice() : pri, sto < 0 ? fruits[id - 1].getStock() : sto)}, false)[0], id);
        f5();
    }

    //水果数组压栈
    private Fruit[] FPush(Fruit[] fruits, Fruit fruit) {
        int s = fruits.length + 1;
        Fruit[] fruits1 = Arrays.copyOf(fruits, s);
        fruits1[s - 1] = fruit;
        return fruits1;
    }

    //水果面板数组压栈
    private JPanel[] jPush(JPanel[] jPanels, JPanel jPanel) {
        int s = jPanels.length + 1;
        JPanel[] jPane = Arrays.copyOf(jPanels, s);
        jPane[s - 1] = jPanel;
        return jPane;
    }

    //添加
    public void TjFruit(Fruit fruit) {
        //更新水果数组（保证修改时的水果新鲜）
        FruitShop.fruits = FPush(FruitShop.fruits,fruit);
        //把Fruit[0]打包为JPanel[0],false表示不更新JPfruits,只拿来转换
        this.JPfruits = jPush(JPfruits, PackFruits(new Fruit[]{fruit}, false)[0]);
        f5();
    }
    //删除
    public void DelFruit(int id){
        //判断删除的id后面还有无水果，有(则为后面的重新编号，重新打包，重新刷新),无(则直接删除，重新打包，重新刷新)
        Fruit[] newFruits = Arrays.copyOf(FruitShop.fruits, FruitShop.fruits.length - 1);
        for (int i = 0; i < FruitShop.fruits.length - 1; i++) {
            if (i + 1 >= id){
                //重设设置其id
                FruitShop.fruits[i + 1].setId(i + 1);
                newFruits[i] = FruitShop.fruits[i + 1];
            }else{
                newFruits[i] = FruitShop.fruits[i];
            }
        }
        //更新水果数组（保证修改时的水果新鲜）
        FruitShop.fruits = newFruits;
        //打包,false表示不更新JPfruits等,只拿来转换
        this.JPfruits = PackFruits(newFruits, false);
        f5();
    }
}