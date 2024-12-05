package FruitShop.util;

import FruitShop.Entity.Fruit;
import FruitShop.view.FruitDetail;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static FruitShop.dao.JdbcFruit.getCustomerData;

public class viewFruitDetailListen {
    // 分页 - 当前页数
    public static int currentPage = 1;
    // 分页 - offset 偏移量
    public static int offset = 0;
    // 分页 - 每一页最大行数
    public static int pageSize = 3;

    /**
     * 为表格相关控件添加监听事件
     * @param nowFruit 当前水果
     * @param LPageButton 上一页按钮
     * @param currentPageField 当前页面
     * @param totalPagesField 总页面
     * @param RPageButton 下一页按钮
     */
    public static void AddListeners(Fruit nowFruit, JButton LPageButton, JTextField currentPageField, JTextField totalPagesField, JButton RPageButton){
        // 上一页按钮
        LPageButton.addActionListener(e -> {
            // 判断是否能按下
            if (currentPage > 1){
                // 翻页
                currentPage--;
                // 刷新 翻页后的 当前页面
                currentPageField.setText(String.valueOf(currentPage));
                // 计算翻页后的 offset
                offset = (currentPage - 1) * pageSize;
                // 刷新表格控件
                FruitDetail.refScrollPane(nowFruit);
            }else{
                JOptionPane.showMessageDialog(null, "已经是第一页了", "提示", JOptionPane.WARNING_MESSAGE);
            }
        });
        // 当前页面 按下回车键时调用
        currentPageField.addActionListener(e -> {
            String text = currentPageField.getText();
            if (!text.isEmpty() && text.matches("[0-9]+")){
                // 判断输入的页数是否大于总页数
                if (Integer.parseInt(text) > Integer.parseInt(totalPagesField.getText())){
                    JOptionPane.showMessageDialog(null, "输入的页数大于总页数！", "提示", JOptionPane.WARNING_MESSAGE);
                }else{
                    // 翻页
                    currentPage = Integer.parseInt(text);
                    // 刷新 翻页后的 当前页面
                    currentPageField.setText(String.valueOf(currentPage));
                    // 计算翻页后的 offset
                    offset = (currentPage - 1) * pageSize;
                    // 刷新表格控件
                    FruitDetail.refScrollPane(nowFruit);
                }
            }else{
                JOptionPane.showMessageDialog(null, "请输入数字！", "提示", JOptionPane.WARNING_MESSAGE);
            }
        });
        // 下一页按钮
        RPageButton.addActionListener(e -> {
            // 判断是否能按下
            if (currentPage < Integer.parseInt(totalPagesField.getText())){
                // 翻页
                currentPage++;
                // 刷新 翻页后的 当前页面
                currentPageField.setText(String.valueOf(currentPage));
                // 计算翻页后的 offset
                offset = (currentPage - 1) * pageSize;
                // 刷新表格控件
                FruitDetail.refScrollPane(nowFruit);
            }else {
                JOptionPane.showMessageDialog(null, "已经是最后一页了", "提示", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    /**
     * 为表格 “客户ID” 添加点击事件
     * @param model 表格控件
     * @param table 表格控件
     */
    public static void AddListeners(DefaultTableModel model, JTable table){
    // 表格控件
    table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JTable target = (JTable) e.getSource();
            int row = target.getSelectedRow();
            int column = target.getSelectedColumn();

            if (column == 2) { // 判断是否点击了第二列
                // 获取当前行的客户ID
                int customerId = (int) model.getValueAt(row, column);
                // 查询当前行的客户信息
                Object[][] objects = getCustomerData(customerId);
                // 判断是否获取到客户信息
                if (objects != null) {
                    if (objects.length == 0) {
                        JOptionPane.showMessageDialog(null, "该客户不存在！", "提示", JOptionPane.WARNING_MESSAGE);
                    } else {
                        // 拼接客户详情 客户ID、姓名、联系方式、地址
                        String customerDetail = "客户ID：" + objects[0][0] + "\n" + "姓名：" + objects[0][1] + "\n" + "联系方式：" + objects[0][2] + "\n" + "地址：" + objects[0][3];
                        JOptionPane.showMessageDialog(null, customerDetail, "客户详情", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    });
}

}