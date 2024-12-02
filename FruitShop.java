package FruitShop;

import FruitShop.Entity.Fruit;
import FruitShop.util.viewNorth;
import FruitShop.util.viewWest;
import FruitShop.view.*;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class FruitShop {
    //
    public static URL localUrl = FruitShop.class.getResource("");

    //窗口大小
    public static int vw = 600;
    public static int vh = 400;

    // 水果数组
    public static Fruit[] fruits = new Fruit[]{
            new Fruit(1, "苹果", "https://img0.baidu.com/it/u=3802269103,3841920027&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500"),
            new Fruit(2, "香蕉", "https://img1.baidu.com/it/u=2863208510,1801169639&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"),
            new Fruit(3, "橘子", "https://ns-strategy.cdn.bcebos.com/ns-strategy/upload/fc_big_pic/part-00554-1041.jpg"),
            new Fruit(4, "草莓", "https://img2.baidu.com/it/u=2316021847,500184263&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"),
            new Fruit(5, "菠萝", "https://img2.baidu.com/it/u=3081301703,2570572501&fm=253&fmt=auto&app=138&f=JPEG?w=640&h=465"),
            new Fruit(6, "山竹", "https://img2.baidu.com/it/u=2774691756,2865735553&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=362"),
            new Fruit(7, "芒果", "https://img0.baidu.com/it/u=1917801406,491508465&fm=253&fmt=auto&app=138&f=JPG?w=640&h=480"),
            new Fruit(8, "柠檬", "https://img1.baidu.com/it/u=2619362584,4147884554&fm=253&fmt=auto&app=138&f=JPEG?w=600&h=400"),
            new Fruit(9, "葡萄", "https://img1.baidu.com/it/u=2116955464,2818384859&fm=253&fmt=auto&app=138&f=JPEG?w=281&h=499"),
            new Fruit(10, "葡萄柚", "https://img1.baidu.com/it/u=3137894260,3783562477&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800"),
            new Fruit(11, "西瓜", "https://img1.baidu.com/it/u=4108434870,2496367536&fm=253&fmt=auto&app=138&f=JPEG?w=747&h=500"),
            new Fruit(12, "梨子", "https://img1.baidu.com/it/u=4235726655,2356266912&fm=253&fmt=auto&app=120&f=JPEG?w=607&h=500"),
            new Fruit(13, "猕猴桃", "https://img2.baidu.com/it/u=2063178977,1294711858&fm=253&fmt=auto&app=138&f=JPEG?w=753&h=500"),
            new Fruit(14, "石榴", "https://img0.baidu.com/it/u=1201922027,250286356&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=443"),
            new Fruit(15, "枇杷", "https://img2.baidu.com/it/u=4067251393,3746805917&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500")
    };

    // 改为 ArrayList 存储
    // static ArrayList<Fruit> fruitsList = new ArrayList<>(Arrays.asList(fruits));

    public static void main(String[] args) throws MalformedURLException {
        //创建窗口
        JFrame jFrame = new JFrame();
        //设置窗口名称
        jFrame.setTitle("水果销售管理系统");
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
        South south = new South(container, "Copyright © 2024 繁寂", vw, 20);

        //西
        West west = new West(container, 50, vh - 35 - 20, center);
        //为 west 上下翻页 按钮 添加事件
        viewWest.AddListeners(west, center);

        //东
        East east = new East(container, 150, vh - 35 - 20);
        /******************开始为每个卡片添加组件*****************添加************************************************/
        East.eastCard.addCKCard(east, center, west);
        /****************************************************修改*************************************************/
        JTextField jTextXGField = East.eastCard.addXGCard(east, center);
        /****************************************************添加*************************************************/
        JTextField jTextTJField = East.eastCard.addTJCard(east, center);
        /****************************************************删除*************************************************/
        East.eastCard.addSCCard(east, center, west);
        /*********************************************结束为每个卡片添加组件*****************************************/

        //北
        North north = new North(container, vw, 35);
        //为 North 菜单 按钮 添加事件
        viewNorth.AddListeners(east, north, west, center, jTextXGField, jTextTJField);


        //设置窗口大小
        jFrame.setSize(vw, vh);
        //设置窗口可见
        jFrame.setVisible(true);
    }
}