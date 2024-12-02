package FruitShop;

import FruitShop.Entity.Fruit;
import FruitShop.view.*;
import FruitShop.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Objects;

public class FruitShop {
    //窗口大小
    static int vw = 600;
    static int vh = 400;

    //水果数组
    static Fruit[] fruits = new Fruit[]{
            new Fruit(1, "苹果","https://img0.baidu.com/it/u=3802269103,3841920027&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500"),
            new Fruit(2, "香蕉","https://img1.baidu.com/it/u=2863208510,1801169639&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"),
            new Fruit(3, "橘子","https://ns-strategy.cdn.bcebos.com/ns-strategy/upload/fc_big_pic/part-00554-1041.jpg"),
            new Fruit(4, "草莓","https://img2.baidu.com/it/u=2316021847,500184263&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"),
            new Fruit(5, "菠萝","https://img2.baidu.com/it/u=3081301703,2570572501&fm=253&fmt=auto&app=138&f=JPEG?w=640&h=465"),
            new Fruit(6, "山竹","https://img2.baidu.com/it/u=2774691756,2865735553&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=362"),
            new Fruit(7, "芒果","https://img0.baidu.com/it/u=1917801406,491508465&fm=253&fmt=auto&app=138&f=JPG?w=640&h=480"),
            new Fruit(8, "柠檬","https://img1.baidu.com/it/u=2619362584,4147884554&fm=253&fmt=auto&app=138&f=JPEG?w=600&h=400"),
            new Fruit(9, "葡萄","https://img1.baidu.com/it/u=2116955464,2818384859&fm=253&fmt=auto&app=138&f=JPEG?w=281&h=499"),
            new Fruit(10, "葡萄柚","https://img1.baidu.com/it/u=3137894260,3783562477&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800"),
            new Fruit(11, "西瓜","https://img1.baidu.com/it/u=4108434870,2496367536&fm=253&fmt=auto&app=138&f=JPEG?w=747&h=500"),
            new Fruit(12, "梨子","https://img1.baidu.com/it/u=4235726655,2356266912&fm=253&fmt=auto&app=120&f=JPEG?w=607&h=500"),
            new Fruit(13, "猕猴桃","https://img2.baidu.com/it/u=2063178977,1294711858&fm=253&fmt=auto&app=138&f=JPEG?w=753&h=500"),
            new Fruit(14, "石榴","https://img0.baidu.com/it/u=1201922027,250286356&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=443"),
            new Fruit(15, "枇杷","https://img2.baidu.com/it/u=4067251393,3746805917&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500")
    };
    
    // 改为 ArrayList 存储
    // static ArrayList<Fruit> fruitsList = new ArrayList<>(Arrays.asList(fruits));





    public static void main(String[] args) {
        //创建窗口
        JFrame jFrame = new JFrame();
        //设置窗口名称
        jFrame.setTitle("水果管理系统");
        //设置窗口关闭时就结束程序
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //使窗体居中
        jFrame.setLocationRelativeTo(null);
        //创建 从jFrame窗口上获取 内容面板 句柄
        Container container = jFrame.getContentPane();
        //设置背景
        container.setBackground(Color.gray);
        //设置布局
        container.setLayout(new BorderLayout());
        //不可调整窗口大小
        jFrame.setResizable(false);


        //中心
        Center center = new Center(container, 3, 2, 2, 2, vw - 150 - 50, vh - 35 - 20);
        //打包
        center.PackFruits(fruits, true);
        center.f5();


        //南
        South south = new South(container, "繁寂制作", vw, 20);


        //西
        West west = new West(container, 50, vh - 35 - 20, center);
        //为上一页添加点击事件
        west.PageUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (center.PageNum > 1) {
                    center.PageNum -= 1;
                    center.f5();
                    //刷新 当前页数/总页数
                    west.PageNum.setText(center.PageNum + " / " + center.PageSum);
                } else {
                    System.out.println("没有前面一页了");
                }
            }
        });
        //为上一页添加点击事件
        west.PageDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (center.PageNum < center.PageSum) {
                    center.PageNum += 1;
                    center.f5();
                    //刷新页数/总页数
                    west.PageNum.setText(center.PageNum + " / " + center.PageSum);
                } else {
                    System.out.println("没有后面一页了");
                }
            }
        });


        //东
        East east = new East(container, 150, vh - 35 - 20);
        /******************开始为每个卡片添加组件**********************/
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

        //监听点击
        TzBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(jTextField.getText());
                if (num >= 1 && num <= center.PageSum) {
                    center.PageNum = num;
                    center.f5();
                    //刷新页数/总页数
                    west.PageNum.setText(center.PageNum + " / " + center.PageSum);
                } else {
                    //输入框 请求焦点
                    jTextField.requestFocus();
                    System.out.println("超出限制，无法跳转!");
                }
            }
        });
        //监听焦点
        Init.addJTextFocus(new JTextField[]{jTextField});
        /*****************************************************************************************************************/
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
        //监听点击
        jButXg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(jTextId.getText());
                //判断是否可以修改
                if (id > 0 && id <= center.JPfruits.length) {
                    //分配默认值，方便判断哪些参数可以用
                    String XgUrl = "#", XgName = "#";
                    double XgPri = -1.00, XgSto = -1.00;

                    if (!Objects.equals(jTextUrl.getText(), "不修改不填") && !jTextUrl.getText().equals("")) {
                        XgUrl = jTextUrl.getText();
                    }

                    if (!Objects.equals(jTextName.getText(), "不修改不填") && !jTextName.getText().equals("")) {
                        XgName = jTextName.getText();
                    }

                    if (!Objects.equals(jTextPrice.getText(), "不修改不填") && !jTextPrice.getText().equals("")) {
                        //格式化double
                        DecimalFormat df = new DecimalFormat("######.00");
                        XgPri = Double.parseDouble(df.format(Double.parseDouble(jTextPrice.getText())));
                    }

                    if (!Objects.equals(jTextStock.getText(), "不修改不填") && !jTextStock.getText().equals("")) {
                        //格式化double
                        DecimalFormat df = new DecimalFormat("######.00");
                        XgSto = Double.parseDouble(df.format(Double.parseDouble(jTextStock.getText())));
                    }
                    //自动定义多个属性
                    center.XgJPShu(fruits, id, XgName, XgUrl, XgPri, XgSto);
                }

            }
        });
        //监听焦点
        Init.addJTextFocus(new JTextField[]{jTextId, jTextUrl, jTextName, jTextPrice, jTextStock});
        /*****************************************************************************************************************/
        JLabel jLabelTj = new JLabel("<html><body>【添加】<br><br>水果的id为：</body></html>");
        JTextField jTextTjId = new JTextField(String.valueOf(center.JPfruits.length + 1), 5);
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
        east.tj.add(jTextTjId);
        jTextTjId.setBounds(75, 38, 60, 20);
        //id 默认无法编辑，自动生成的
        jTextTjId.setEnabled(false);
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
        //监听点击
        jButTj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jTextTjName.getText().equals("* 必填") && !jTextTjName.getText().equals("")) {

                    //分配默认值，方便判断哪些参数可以用
                    String TjUrl = "#";
                    double TjPri = -1.00, TjSto = -1.00;

                    if (!Objects.equals(jTextTjUrl.getText(), "不添加不填") && !jTextTjUrl.getText().equals("")) {
                        TjUrl = jTextTjUrl.getText();
                    }

                    if (!Objects.equals(jTextTjPrice.getText(), "不添加不填") && !jTextTjPrice.getText().equals("")) {
                        //格式化double
                        DecimalFormat df = new DecimalFormat("######.00");
                        TjPri = Double.parseDouble(df.format(Double.parseDouble(jTextTjPrice.getText())));
                    }

                    if (!Objects.equals(jTextTjStock.getText(), "不添加不填") && !jTextTjStock.getText().equals("")) {
                        //格式化double
                        DecimalFormat df = new DecimalFormat("######.00");
                        TjSto = Double.parseDouble(df.format(Double.parseDouble(jTextTjStock.getText())));
                    }
                    //创建 Fruit 类（根据参数判断使用哪个构造函数）
                    Fruit fruit = TjUrl.equals("#") && TjPri > 0 && TjSto > 0
                            ? new Fruit(Integer.parseInt(jTextTjId.getText()), jTextTjName.getText(), TjUrl, TjPri, TjSto)
                            : (!TjUrl.equals("#") ? new Fruit(Integer.parseInt(jTextTjId.getText()), jTextTjName.getText(), TjUrl)
                                : new Fruit(Integer.parseInt(jTextTjId.getText()), jTextTjName.getText()));
                    //添加到面板数组
                    center.TjFruit(fruit);
                    //更新id
                    jTextTjId.setText(String.valueOf(center.JPfruits.length + 1));
                }
            }
        });
        //监听焦点
        Init.addJTextFocus(new JTextField[]{jTextTjUrl, jTextTjName, jTextTjPrice, jTextTjStock});
        /*****************************************************************************************************************/
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
        //监听点击
        Del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nu = Integer.parseInt(jDelTextField.getText());
                if (nu >= 1 && nu <= FruitShop.fruits.length) {
                    center.DelFruit(nu);
                    //刷新页数/总页数
                    west.PageNum.setText(center.PageNum + " / " + center.PageSum);
                } else {
                    //输入框 请求焦点
                    jDelTextField.requestFocus();
                    System.out.println("id超出范围，无法删除!");
                }
            }
        });
        //监听焦点
        Init.addJTextFocus(new JTextField[]{jDelTextField});
        /*********************结束为每个卡片添加组件**********************/


        //北
        North north = new North(container, vw, 35);
        //为按钮添加事件
        north.ck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("查看按钮按下");
                System.out.println("切换界面：" + east.NumFoCard(1));
            }
        });
        //为按钮添加事件
        north.xg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("修改按钮按下");
                System.out.println("切换界面：" + east.NumFoCard(2));
            }
        });
        //为按钮添加事件
        north.tj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("添加按钮按下");
                System.out.println("切换界面：" + east.NumFoCard(3));
            }
        });
        //为按钮添加事件
        north.sc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("删除按钮按下");
                System.out.println("切换界面：" + east.NumFoCard(4));
            }
        });


        //设置窗口大小
        jFrame.setSize(vw, vh);
        //设置窗口可见
        jFrame.setVisible(true);
    }
}