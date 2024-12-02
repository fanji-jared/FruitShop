package FruitShop.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.MalformedURLException;
import java.net.URL;

import static FruitShop.FruitShop.localUrl;

//西部类封装
public class West {
    //西部自定义布局面板
    public JPanel jPanel;
    //【当前页/所有页】的容器 JLabel
    public JLabel PageNum;
    //【上一页】按钮 JButton
    public JButton PageUp;
    //【下一页】按钮 JButton
    public JButton PageDown;
    //内容面板
    private Container container;

    public void setContainer(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return container;
    }

    public West(Container container, int width, int height, Center center) throws MalformedURLException {
        setContainer(container);
        //自定义布局
        jPanel = new JPanel();
        //自定义布局
        jPanel.setLayout(null);

        PageUp = WestButInit("上一页","images/l.png", 0, 50, 35, 35);
        PageNumInit(center);
        PageNum.setBounds(0, 150, 50, 16);
        PageDown = WestButInit("下一页","images/r.png", 0, 200, 35, 35);
        jPanel.add(PageNum);

        //添加布局到内容面板的西部
        getContainer().add(jPanel, BorderLayout.WEST);
        //设置首选大小
        jPanel.setPreferredSize(new Dimension(width, height));
    }

    //初始化当前页数/总页数
    public void PageNumInit(Center center) {
        PageNum = new JLabel(center.PageNum + " / " + center.PageSum, SwingConstants.CENTER);
    }

    //西部按钮初始化
    JButton WestButInit(String jLabName, String imagePath, int x, int y, int w, int h) throws MalformedURLException {
        // 将URL与相对路径进行拼接
        URL iconUrl = new URL(localUrl, imagePath);
        //获取图片资源
        Icon img = new ImageIcon(iconUrl);
        JButton jButton = new JButton(img);
        //设置内容区域不填充
        jButton.setContentAreaFilled(false);
        //关闭默认边框
        jButton.setBorderPainted(false);
        //获得焦点不显示边框
        jButton.setFocusPainted(false);
        //设置控件是否透明 true表示不透明 false表示透明
        jButton.setOpaque(false);
        //添加焦点监听事件
        jButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                jButton.setBackground(Color.gray);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jButton.setBackground(Color.white);
            }
        });
        //文本容器
        JLabel jLabel = new JLabel(jLabName, SwingConstants.CENTER);
        jPanel.add(jButton);
        //添加按钮
        jPanel.add(jLabel);

        //设置默认背景颜色
        jButton.setBackground(Color.white);
        //设置默认文本颜色
        jLabel.setForeground(Color.white);
        //添加焦点监听
        jButton.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                jButton.setBackground(Color.white);
                //设置文本颜色
                jLabel.setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jLabel.setBackground(Color.black);
                //设置文本颜色
                jLabel.setForeground(Color.white);
            }
        });
        //设置位置
        jButton.setBounds(x, y, w, h);
        //设置margin
        jButton.setMargin(new Insets(0, (50 - w) / 2 + 5, 0, 0));
        jLabel.setBounds(x, y + h, 50, 16);
        return jButton;
    }
}