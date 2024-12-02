package FruitShop.view;

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
}