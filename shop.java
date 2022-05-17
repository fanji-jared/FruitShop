package FruitShop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;

public class shop {
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

    //按钮初始化
    static void ButtonInit(JButton j) {
        //设置内容区域不填充
        //j.setContentAreaFilled(false);
        j.setBackground(Color.white);
        //关闭默认边框
        j.setBorderPainted(false);
        //获得焦点不显示边框
        j.setFocusPainted(false);
        j.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                j.setBackground(Color.gray);
                //设置文本颜色
                j.setForeground(Color.white);
            }

            @Override
            public void focusLost(FocusEvent e) {
                j.setBackground(Color.white);
                //设置文本颜色
                j.setForeground(Color.black);
            }
        });
    }

    //JTextField 输入框焦点监听
    static void addJTextFocus(JTextField[] jTextFields) {
        for (JTextField jTextField : jTextFields) {
            jTextField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    jTextField.selectAll();//选择所有的文本
                }

                @Override
                public void focusLost(FocusEvent e) {
                }
            });
        }
    }

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
        /*********************开始为每个卡片添加组件**********************/
        JLabel jLabel = new JLabel("<html><body>【跳转】<br><br>请输入页数：</body></html>");
        JTextField jTextField = new JTextField("1", 5);
        JButton TzBut = new JButton("跳转");
        shop.ButtonInit(TzBut);
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
        addJTextFocus(new JTextField[]{jTextField});
        /*****************************************************************************************************************/
        JLabel jLabelXg = new JLabel("<html><body>【选择】<br><br>请输入水果id：</body></html>");
        JTextField jTextId = new JTextField("1", 5);
        JLabel jLabel1 = new JLabel("<html><body><br>【属性】<br><br>图片链接：<br><br>名字：<br><br>价格：<br><br>库存：</body></html>");
        JTextField jTextUrl = new JTextField("不修改不填", 5);
        JTextField jTextName = new JTextField("不修改不填", 5);
        JTextField jTextPrice = new JTextField("不修改不填", 5);
        JTextField jTextStock = new JTextField("不修改不填", 5);
        JButton jButXg = new JButton("确定修改");
        shop.ButtonInit(jButXg);
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
        addJTextFocus(new JTextField[]{jTextId, jTextUrl, jTextName, jTextPrice, jTextStock});
        /*****************************************************************************************************************/
        JLabel jLabelTj = new JLabel("<html><body>【添加】<br><br>水果的id为：</body></html>");
        JTextField jTextTjId = new JTextField(String.valueOf(center.JPfruits.length + 1), 5);
        JLabel jLabelTj1 = new JLabel("<html><body><br>【属性】<br><br>图片链接：<br><br>名字：<br><br>价格：<br><br>库存：</body></html>");
        JTextField jTextTjUrl = new JTextField("不添加不填", 5);
        JTextField jTextTjName = new JTextField("* 必填", 5);
        JTextField jTextTjPrice = new JTextField("不添加不填", 5);
        JTextField jTextTjStock = new JTextField("不添加不填", 5);
        JButton jButTj = new JButton("确定添加");
        shop.ButtonInit(jButTj);
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
        addJTextFocus(new JTextField[]{jTextTjUrl, jTextTjName, jTextTjPrice, jTextTjStock});
        /*****************************************************************************************************************/
        JLabel jDelLabel = new JLabel("<html><body>【删除指定水果】<br><br>请输入水果id：</body></html>");
        JTextField jDelTextField = new JTextField("1", 5);
        JButton Del = new JButton("删除");
        shop.ButtonInit(Del);
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
                if (nu >= 1 && nu <= shop.fruits.length) {
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
        addJTextFocus(new JTextField[]{jDelTextField});
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

//东部类封装
class East {
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

//南部类封装
class South {
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

//西部类封装
class West {
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

    public West(Container container, int width, int height, Center center) {
        setContainer(container);
        //自定义布局
        jPanel = new JPanel();
        //自定义布局
        jPanel.setLayout(null);

        PageUp = WestButInit("上一页", "./l.png", 0, 50, 35, 35);
        PageNumInit(center);
        PageNum.setBounds(0, 150, 50, 16);
        PageDown = WestButInit("下一页", "./r.png", 0, 200, 35, 35);
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
    JButton WestButInit(String jLabName, String jButUrl, int x, int y, int w, int h) {
        //获取图片资源
        Icon img = new ImageIcon(this.getClass().getResource(jButUrl));
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

//北部类封装
class North {
    //暴露按钮，为了添加事件
    public JButton ck;
    public JButton xg;
    public JButton tj;
    public JButton sc;

    private Container container;

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public North(Container container, int width, int height) {
        setContainer(container);
        //创建按钮
        ck = new JButton("查  看");
        xg = new JButton("修  改");
        tj = new JButton("添  加");
        sc = new JButton("删  除");
        JButton[] jButtons = new JButton[]{ck, xg, tj, sc};
        JPanel NorthJpanel = new JPanel();
        //居中排列 左右和上下margin
        NorthJpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
        //初始化按钮
        NorthButInit(NorthJpanel, jButtons);
        //添加流式布局到内容面板的北部
        getContainer().add(NorthJpanel, BorderLayout.NORTH);
        //设置首选大小
        NorthJpanel.setPreferredSize(new Dimension(width, height));
    }
    //添加按钮
    private void NorthButInit(Object box, JButton[] jButton) {
        for (JButton j : jButton) {
            shop.ButtonInit(j);
            if (box instanceof JPanel Panel) {
                Panel.add(j);
            }
        }
    }
}

//中心类封装
class Center {
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

    public Center(Container container, int rows, int cols, int hgap, int vgap, int width, int height) {
        this.rows = rows;
        this.cols = cols;
        this.width = width;
        this.height = height;
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
            String news = "<html><body>ID:" + fruit.getId() + "<br>名字:" + fruit.getName() + "<br>价格:" + fruit.getPrice() + "<br>库存:" + fruit.getStock() + "</body></html>";
            //添加图片
            URL img = null;
            try {
                img = new URL(fruit.getImgUrl());
            } catch (MalformedURLException e) {
                img = this.getClass().getResource(fruit.getImgUrl());
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
        shop.fruits = FPush(shop.fruits,fruit);
        //把Fruit[0]打包为JPanel[0],false表示不更新JPfruits,只拿来转换
        this.JPfruits = jPush(JPfruits, PackFruits(new Fruit[]{fruit}, false)[0]);
        f5();
    }
    //删除
    public void DelFruit(int id){
        //判断删除的id后面还有无水果，有(则为后面的重新编号，重新打包，重新刷新),无(则直接删除，重新打包，重新刷新)
        Fruit[] newFruits = Arrays.copyOf(shop.fruits,shop.fruits.length - 1);
        for (int i = 0;i < shop.fruits.length - 1;i++) {
            if (i + 1 >= id){
                //重设设置其id
                shop.fruits[i + 1].setId(i + 1);
                newFruits[i] = shop.fruits[i + 1];
            }else{
                newFruits[i] = shop.fruits[i];
            }
        }
        //更新水果数组（保证修改时的水果新鲜）
        shop.fruits = newFruits;
        //打包,false表示不更新JPfruits等,只拿来转换
        this.JPfruits = PackFruits(newFruits, false);
        f5();
    }
}