package FruitShop.view;

import FruitShop.Entity.Fruit;

import javax.swing.*;
import java.awt.*;

public class FruitDetail extends JFrame {
    public Fruit fruit;
    public static JFrame jFrame;

//    水果数组示例
//    public static Fruit[] fruits = new Fruit[]{
//            new Fruit(1, "苹果", "https://img0.baidu.com/it/u=3802269103,3841920027&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500"),
//            new Fruit(2, "香蕉", "https://img1.baidu.com/it/u=2863208510,1801169639&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"),
//            new Fruit(3, "橘子", "https://ns-strategy.cdn.bcebos.com/ns-strategy/upload/fc_big_pic/part-00554-1041.jpg"),
//            new Fruit(4, "草莓", "https://img2.baidu.com/it/u=2316021847,500184263&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"),
//            new Fruit(5, "菠萝", "https://img2.baidu.com/it/u=3081301703,2570572501&fm=253&fmt=auto&app=138&f=JPEG?w=640&h=465"),
//            new Fruit(6, "山竹", "https://img2.baidu.com/it/u=2774691756,2865735553&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=362"),
//            new Fruit(7, "芒果", "https://img0.baidu.com/it/u=1917801406,491508465&fm=253&fmt=auto&app=138&f=JPG?w=640&h=480"),
//            new Fruit(8, "柠檬", "https://img1.baidu.com/it/u=2619362584,4147884554&fm=253&fmt=auto&app=138&f=JPEG?w=600&h=400"),
//            new Fruit(9, "葡萄", "https://img1.baidu.com/it/u=2116955464,2818384859&fm=253&fmt=auto&app=138&f=JPEG?w=281&h=499"),
//            new Fruit(10, "葡萄柚", "https://img1.baidu.com/it/u=3137894260,3783562477&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800"),
//            new Fruit(11, "西瓜", "https://img1.baidu.com/it/u=4108434870,2496367536&fm=253&fmt=auto&app=138&f=JPEG?w=747&h=500"),
//            new Fruit(12, "梨子", "https://img1.baidu.com/it/u=4235726655,2356266912&fm=253&fmt=auto&app=120&f=JPEG?w=607&h=500"),
//            new Fruit(13, "猕猴桃", "https://img2.baidu.com/it/u=2063178977,1294711858&fm=253&fmt=auto&app=138&f=JPEG?w=753&h=500"),
//            new Fruit(14, "石榴", "https://img0.baidu.com/it/u=1201922027,250286356&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=443"),
//            new Fruit(15, "枇杷", "https://img2.baidu.com/it/u=4067251393,3746805917&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500")
//    };

    public void loadView(Fruit fruit){
        this.fruit = fruit;
        //创建窗口
        jFrame = new JFrame();
        // 设置窗口标题
        jFrame.setTitle(fruit.getName() + " - 详细信息");
        // 设置窗口大小
        jFrame.setSize(500, 400);
        // 设置窗口居中
        jFrame.setLocationRelativeTo(null);
        // 设置子窗口的关闭操作为只关闭自己
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        // 创建内容面板
        Container container = jFrame.getContentPane();
        // 设置布局
        container.setLayout(new BorderLayout());
        // 创建标签，展示水果的名称和图片
        JLabel label = new JLabel(fruit.getName() + "<html><br><img src='" + "fruit.getImageUrl()" + "' width=200 height=200></html>");
        // 添加标签到内容面板
        container.add(label, BorderLayout.CENTER);


        // 显示一个简单的消息框
        JOptionPane.showMessageDialog(null, "这是一个简单的消息框", "提示", JOptionPane.INFORMATION_MESSAGE);

        // 显示一个确认对话框，带有“是”、“否”、“取消”按钮
        int result = JOptionPane.showConfirmDialog(null, "你确定要继续吗？", "确认", JOptionPane.YES_NO_CANCEL_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.out.println("用户选择了“是”");
        } else if (result == JOptionPane.NO_OPTION) {
            System.out.println("用户选择了“否”");
        } else if (result == JOptionPane.CANCEL_OPTION) {
            System.out.println("用户选择了“取消”");
        }

        // 设置窗口可见
        jFrame.setVisible(true);
    }
}
