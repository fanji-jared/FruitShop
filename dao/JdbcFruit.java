package FruitShop.dao;

import FruitShop.Entity.Fruit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JdbcFruit {
    /**
     * 查询所有水果
     * @return 水果列表
     */
    public static ArrayList<Fruit> getFruits(){
        // 查询数据
        String selectSql = "SELECT * FROM fruit";
        Object[] selectParams = {};
        ResultSet rs = JDBCUtils.executeQuery(selectSql, selectParams);
        // 初始化水果列表
        ArrayList<Fruit> arrayList = new ArrayList<Fruit>();
        try {
            while (rs.next()) {
                // ID、名称、图片链接、价格、库存
                int id = rs.getInt("fruit_id");
                String name = rs.getString("name");
                String imageUrl = rs.getString("image_url");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");

                // 创建后添加水果
                Fruit fruit = new Fruit(id, name, imageUrl, price, stock);
                arrayList.add(fruit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            JDBCUtils.close(null, null, rs); // 关闭ResultSet
        }
        return arrayList;
    }

    /**
     * 根据ID查询水果
     * @param fruit 要查询的水果对象
     * @return 查询到的水果对象
     */
    public static int addFruits(Fruit fruit){
        // 解析参数
        String name = fruit.getName();
        String image_url = fruit.getImgUrl();
        double price = fruit.getPrice();
        int stock = fruit.getStock();

        // 插入数据
        String insertSql = "INSERT INTO fruit (name, image_url, price, stock) VALUES (?, ?, ?, ?)";
        int rowsInserted = JDBCUtils.executeUpdate(insertSql, new Object[]{name, image_url, price, stock});
        System.out.println("Rows inserted: " + rowsInserted);

        return rowsInserted;
    }

    /**
     * 根据ID修改水果属性
     * @param fruit 要修改的水果对象
     * @return 修改的行数
     */
    public static int changeFruits(Fruit fruit) {
        // 初始化更新的字段和参数列表
        List<String> updateFields = new ArrayList<>();
        List<Object> updateParams = new ArrayList<>();

        String name = fruit.getName();
        String image_url = fruit.getImgUrl();
        double price = fruit.getPrice();
        int stock = fruit.getStock();
        int id = fruit.getId();

        // 动态添加需要更新的字段和对应的参数
        if (name != null && !"#".equals(name)) {
            updateFields.add("name = ?");
            updateParams.add(name);
        }
        if (image_url != null && !"#".equals(image_url)) {
            updateFields.add("image_url = ?");
            updateParams.add(image_url);
        }
        if (price >= 0.00) {
            updateFields.add("price = ?");
            updateParams.add(price);
        }
        if (stock >= 0) {
            updateFields.add("stock = ?");
            updateParams.add(stock);
        }

        // 构建最终的 SQL 语句
        StringBuilder updateSqlBuilder = new StringBuilder("UPDATE fruit SET ");
        if (!updateFields.isEmpty()) {
            // 将所有要更新的字段用逗号分隔连接起来
            updateSqlBuilder.append(String.join(", ", updateFields));
            // 添加 WHERE 子句
            updateSqlBuilder.append(" WHERE fruit_id = ?");
            updateParams.add(id); // 添加 id 作为最后一个参数
        } else {
            // 如果没有需要更新的字段，返回 0 表示没有更新任何行
            return 0;
        }

        // 更新数据
        int rowsUpdated = JDBCUtils.executeUpdate(updateSqlBuilder.toString(), updateParams.toArray());
        System.out.println("Rows updated: " + rowsUpdated);

        return rowsUpdated;
    }

    /**
     * 根据水果id删除水果
     * @param fruitID 要删除的水果id
     * @return 删除的行数
     */
    public static int deleteFruits(int fruitID) {
        // 删除数据
        String deleteSql = "DELETE FROM fruit WHERE fruit_id = ?";
        Object[] deleteParams = {fruitID};
        int rowsDeleted = JDBCUtils.executeUpdate(deleteSql, deleteParams);
        System.out.println("Rows deleted: " + rowsDeleted);
        return rowsDeleted;
    }

    /**
     * 根据客户ID查询客户数据
     * @param customerId 客户id
     * @return Object[][]，包含客户信息的二维数组
     */
    public static Object[][] getCustomerData(int customerId) {
        // 查询数据
        String selectSql = "SELECT * FROM customer WHERE customer_id = ?";
        Object[] selectParams = {customerId};
        ResultSet rs = JDBCUtils.executeQuery(selectSql, selectParams);

        // 初始化Object[][]
        Object[][] data = new Object[][]{};
        try {
            while (rs.next()) {
                // 存储客户信息，包括客户ID、姓名、联系方式、地址
                int customer_id = rs.getInt("customer_id");
                String name = rs.getString("name");
                String contact_info = rs.getString("contact_info");
                String address = rs.getString("address");

                // 添加对象到data
                data = Arrays.copyOf(data, data.length + 1); // 扩展数组
                data[data.length - 1] = new Object[]{customer_id, name, contact_info, address};
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            JDBCUtils.close(null, null, rs); // 关闭ResultSet
        }
        return data;
    }

    /**
     * 根据水果id查询 水果订单信息
     * @param fruit 水果对象
     * @param offset 偏移量
     * @param limit 限制返回数
     * @return Object[][] ，包含订单信息的二维数组
     */
    public static Object[][] getOrdersData(Fruit fruit, int offset, int limit) {
        // 查询数据
        String selectSql = "SELECT * FROM orders WHERE fruit_id = ? LIMIT ?, ?";
        Object[] selectParams = {fruit.getId(), offset, limit};
        ResultSet rs = JDBCUtils.executeQuery(selectSql, selectParams);

        // 初始化Object[][]
        Object[][] data = new Object[][]{};
        try {
            while (rs.next()) {
                // 存储订单信息，包括订单ID、水果ID、购买数量、订单日期、订单状态
                int order_id = rs.getInt("order_id");
                int fruit_id = rs.getInt("fruit_id");
                int customer_id = rs.getInt("customer_id");
                int quantity = rs.getInt("quantity");
                Date order_date = rs.getDate("order_date");
                String status = rs.getString("status");

                // 添加对象到data
                data = Arrays.copyOf(data, data.length + 1); // 扩展数组
                data[data.length - 1] = new Object[]{order_id, fruit_id, customer_id, quantity, order_date, status};
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            JDBCUtils.close(null, null, rs); // 关闭ResultSet
        }
        return data;
    }

    /**
     * 获取水果订单数量
     * @param fruit 水果对象
     * @return 订单数量
     */
    public static int getOrdersCount(Fruit fruit) {
        // 使用 JDBCUtils 库的 executeQuery
        String countSql = "SELECT COUNT(*) FROM orders WHERE fruit_id = ?";
        Object[] countParams = {fruit.getId()};
        ResultSet rs = JDBCUtils.executeQuery(countSql, countParams);
        try {
            if (rs.next()) {
                return rs.getInt(1); // 返回查询结果的第一列的值
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}