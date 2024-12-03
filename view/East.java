package FruitShop.view;

import FruitShop.util.Init;
import FruitShop.util.viewEastListen;

import javax.swing.*;
import java.awt.*;

//东部类封装
public class East {
    //当前页面位置
    public int PageState;
    //布局面板
    private JPanel jPanel;
    //卡片布局管理器
    private CardLayout cardLayout;
    //定义一个流式布局管理器
    //FlowLayout f = new FlowLayout(FlowLayout.LEFT);
    //创建并暴露4个自定义布局面板
    public JPanel ck = new JPanel(null);
    public JPanel xg = new JPanel(null);
    public JPanel tj = new JPanel(null);
    public JPanel sc = new JPanel(null);
    //创建卡片数组
    public JPanel[] CardArr = new JPanel[]{ck, xg, tj, sc};

    public East(Container container, int width, int height) {
        //创建卡片布局面板
        cardLayout = new CardLayout();
        //创建一个面板
        jPanel = new JPanel(cardLayout);
        //加载卡片
        EastAddCardl(CardArr);
        //设置默认状态
        cardLayout.show(jPanel, "jp1");
        //更新状态
        PageState = 1;
        //添加布局到内容面板的东部
        container.add(jPanel, BorderLayout.EAST);
        //设置首选大小
        jPanel.setPreferredSize(new Dimension(width, height));
    }

    public void EastAddCardl(JPanel[] jp) {
        int n = 1;
        for (JPanel Jp : jp) {
            //将自定义布局添加进卡片布局
            jPanel.add(Jp, "jp" + n);
            n++;
        }
    }

    public boolean NumFoCard(int i) {
        if (i <= CardArr.length) {
            cardLayout.show(jPanel, "jp" + i);
            PageState = i;
            return true;
        } else {
            return false;
        }
    }

    /*
    //清空并再次添加后刷新
    public void f5(North north, Center center, West west, JTextField jf) {
        //删除所有控件
//        jPanel.removeAll();
        jPanel.remove(jf);

        //重新添加
        //*****************开始为每个卡片添加组件*****************添加
//        eastCard.addCKCard(this, center, west);
        //****************************************************修改
        JTextField jTextXGField = eastCard.addXGCard(this, center);
        //****************************************************添加
        JTextField jTextTJField = eastCard.addTJCard(this, center);
        //****************************************************删除
//        eastCard.addSCCard(this, center, west);
        //**************************************结束为每个卡片添加组件

        //为 North 菜单 按钮 添加事件
//        viewNorth.AddListeners(this, north, west, center, jTextXGField, jTextTJField);

        //对面板中的组件重新布局并绘制
        jPanel.revalidate();
        //对本身进行重绘
        jPanel.repaint();
    }
    */

    public static class eastCard {
        public static void addCKCard(East east, Center center, West west) {
            JLabel jLabel = new JLabel("<html><body>【跳转】<br><br>请输入页数：</body></html>");
            JTextField jTextField = new JTextField("1", 5);
            JButton TzBut = new JButton("跳转");
            Init.ButtonInit(TzBut);

            //添加与设置位置大小
            east.ck.add(jLabel);
            jLabel.setBounds(0, 0, 150, 50);
            east.ck.add(jTextField);
            jTextField.setBounds(70, 33, 60, 20);
            east.ck.add(TzBut);
            TzBut.setBounds(25, 60, 100, 30);

            //添加点击事件
            viewEastListen.addCKClick(center, west, TzBut, jTextField);
            //添加监听焦点
            Init.addJTextFocus(new JTextField[]{jTextField});
        }

        public static JTextField addXGCard(East east, Center center) {
            JLabel jLabelXg = new JLabel("<html><body>【选择】<br><br>请输入水果id：</body></html>");
            JTextField jTextId = new JTextField("1", 5);
            JLabel jLabel1 = new JLabel("<html><body><br>【属性】<br><br>图片链接：<br><br>名字：<br><br>价格：<br><br>库存：</body></html>");
            JTextField jTextUrl = new JTextField("不修改不填", 5);
            JTextField jTextName = new JTextField("不修改不填", 5);
            JTextField jTextPrice = new JTextField("不修改不填", 5);
            JTextField jTextStock = new JTextField("不修改不填", 5);
            JButton jButXg = new JButton("确定修改");
            Init.ButtonInit(jButXg);

            //添加与设置位置大小
            east.xg.add(jLabelXg);
            jLabelXg.setBounds(0, 0, 150, 60);
            east.xg.add(jTextId);
            jTextId.setBounds(80, 38, 60, 20);
            east.xg.add(jLabel1);
            jLabel1.setBounds(0, 40, 150, 200);
            east.xg.add(jTextUrl);
            jTextUrl.setBounds(60, 108, 90, 20);
            east.xg.add(jTextName);
            jTextName.setBounds(35, 140, 100, 20);
            east.xg.add(jTextPrice);
            jTextPrice.setBounds(35, 170, 100, 20);
            east.xg.add(jTextStock);
            jTextStock.setBounds(35, 205, 100, 20);
            east.xg.add(jButXg);
            jButXg.setBounds(25, 245, 100, 30);

            //添加点击事件
            viewEastListen.addXGClick(center, jButXg, jTextId, jTextUrl, jTextName, jTextPrice, jTextStock);
            //监听焦点
            Init.addJTextFocus(new JTextField[]{jTextId, jTextUrl, jTextName, jTextPrice, jTextStock});

            return jTextId;
        }

        public static JTextField addTJCard(East east, Center center) {
            JLabel jLabelTj = new JLabel("<html><body>【添加】<br><br>水果的id为：</body></html>");
            JTextField JTextTJField = new JTextField(String.valueOf(center.JPfruits.length + 1), 5);
            JLabel jLabelTj1 = new JLabel("<html><body><br>【属性】<br><br>图片链接：<br><br>名字：<br><br>价格：<br><br>库存：</body></html>");
            JTextField jTextTjUrl = new JTextField("不添加不填", 5);
            JTextField jTextTjName = new JTextField("* 必填", 5);
            JTextField jTextTjPrice = new JTextField("不添加不填", 5);
            JTextField jTextTjStock = new JTextField("不添加不填", 5);
            JButton jButTj = new JButton("确定添加");
            Init.ButtonInit(jButTj);
            //添加与设置位置大小
            east.tj.add(jLabelTj);
            jLabelTj.setBounds(0, 0, 150, 60);
            east.tj.add(JTextTJField);
            JTextTJField.setBounds(75, 38, 60, 20);
            //id 默认无法编辑，自动生成的
//            JTextTJField.setEnabled(false);
            east.tj.add(jLabelTj1);
            jLabelTj1.setBounds(0, 40, 150, 200);
            east.tj.add(jTextTjUrl);
            jTextTjUrl.setBounds(60, 108, 90, 20);
            east.tj.add(jTextTjName);
            jTextTjName.setBounds(35, 140, 100, 20);
            east.tj.add(jTextTjPrice);
            jTextTjPrice.setBounds(35, 170, 100, 20);
            east.tj.add(jTextTjStock);
            jTextTjStock.setBounds(35, 205, 100, 20);
            east.tj.add(jButTj);
            jButTj.setBounds(25, 245, 100, 30);

            //添加点击事件
            viewEastListen.addTJClick(center, jButTj, jTextTjName, jTextTjUrl, jTextTjPrice, jTextTjStock, JTextTJField);
            //监听焦点
            Init.addJTextFocus(new JTextField[]{jTextTjUrl, jTextTjName, jTextTjPrice, jTextTjStock, JTextTJField});

            return JTextTJField;
        }

        public static void addSCCard(East east, Center center, West west) {
            JLabel jDelLabel = new JLabel("<html><body>【删除指定水果】<br><br>请输入水果id：</body></html>");
            JTextField jDelTextField = new JTextField("1", 5);
            JButton Del = new JButton("删除");
            Init.ButtonInit(Del);
            //添加与设置位置大小
            east.sc.add(jDelLabel);
            jDelLabel.setBounds(0, 0, 150, 50);
            east.sc.add(jDelTextField);
            jDelTextField.setBounds(80, 33, 60, 20);
            east.sc.add(Del);
            Del.setBounds(25, 60, 100, 30);

            //添加点击事件
            viewEastListen.addSCClick(center, west, Del, jDelTextField);
            //监听焦点
            Init.addJTextFocus(new JTextField[]{jDelTextField});
        }
    }
}