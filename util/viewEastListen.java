package FruitShop.util;

import FruitShop.Entity.Fruit;
import FruitShop.FruitShop;
import FruitShop.view.Center;
import FruitShop.view.West;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Objects;

import static FruitShop.FruitShop.fruits;

public class viewEastListen {
    public static void addCKClick(Center center, West west, JButton TzBut, JTextField jTextField) {
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
                    // 显示一个简单的消息框
                    JOptionPane.showMessageDialog(null, "超出限制，无法跳转！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("超出限制，无法跳转!");
                }
            }
        });
    }

    public static void addXGClick(Center center, JButton jButXg, JTextField jTextId, JTextField jTextUrl, JTextField jTextName, JTextField jTextPrice, JTextField jTextStock) {
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
                }else{
                    // 显示一个简单的消息框
                    JOptionPane.showMessageDialog(null, "id 存在问题，无法修改！", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public static void addTJClick(Center center, JButton jButTj, JTextField jTextTjName, JTextField jTextTjUrl, JTextField jTextTjPrice, JTextField jTextTjStock, JTextField JTextTJField) {
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
                    String jTextTjIdText = JTextTJField.getText();
                    String jTextTjNameText = jTextTjName.getText();
                    Fruit fruit = TjUrl.equals("#") && TjPri > 0 && TjSto > 0
                            ? new Fruit(Integer.parseInt(jTextTjIdText), jTextTjNameText, TjUrl, TjPri, TjSto)
                            : (!TjUrl.equals("#") ? new Fruit(Integer.parseInt(jTextTjIdText), jTextTjNameText, TjUrl)
                            : new Fruit(Integer.parseInt(jTextTjIdText), jTextTjNameText));

                    //添加到面板数组
                    center.TjFruit(fruit);
                    //更新id
                    JTextTJField.setText(String.valueOf(center.JPfruits.length + 1));
                    center.f5();
                }else{
                    // 显示一个简单的消息框
                    JOptionPane.showMessageDialog(null, "必填项目为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public static void addSCClick(Center center, West west, JButton Del, JTextField jDelTextField) {
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
    }
}