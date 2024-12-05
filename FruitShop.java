package FruitShop;

import FruitShop.Entity.Fruit;
import FruitShop.dao.JdbcFruit;
import FruitShop.util.viewNorthListen;
import FruitShop.util.viewWestListen;
import FruitShop.view.*;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FruitShop {
    // 项目根目录
    public static URL localUrl = FruitShop.class.getResource("");

    //窗口大小
    public static int vw = 600;
    public static int vh = 400;

    // 改为 ArrayList 存储 水果
    public static ArrayList<Fruit> fruits = new ArrayList<>();

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
        //查询打包
        fruits = JdbcFruit.getFruits();
        center.PackFruits(fruits, true);
        center.f5();

        //南
        South south = new South(container, "Copyright © 2024 繁寂", vw, 20);

        //西
        West west = new West(container, 50, vh - 35 - 20, center);
        //为 west 上下翻页 按钮 添加事件
        viewWestListen.AddListeners(west, center);

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
        viewNorthListen.AddListeners(east, north, west, center, jTextXGField, jTextTJField);


        //设置窗口大小
        jFrame.setSize(vw, vh);
        //设置窗口可见
        jFrame.setVisible(true);
    }
}