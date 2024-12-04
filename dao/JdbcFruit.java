package FruitShop.dao;

import FruitShop.Entity.Fruit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcFruit {
    public static ArrayList<Fruit> getFruits(){
        // 查询数据
        String selectSql = "SELECT * FROM fruit";
        Object[] selectParams = {};
        ResultSet rs = JDBCUtils.executeQuery(selectSql, selectParams);
        // 初始化水果列表
        ArrayList<Fruit> arrayList = new ArrayList<Fruit>();
        try {
            while (rs.next()) {
                int id = rs.getInt("fruit_id");
                String name = rs.getString("name");
                String imageUrl = rs.getString("image_url");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                System.out.println(id + " " + name + " " + imageUrl + " " + price + " " + stock);
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
            updateFields.add("img_url = ?");
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
            updateSqlBuilder.append(" WHERE id = ?");
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

    public static int deleteFruits(int fruitID) {
        // 删除数据
        String deleteSql = "DELETE FROM fruit WHERE id = ?";
        Object[] deleteParams = {fruitID};
        int rowsDeleted = JDBCUtils.executeUpdate(deleteSql, deleteParams);
        System.out.println("Rows deleted: " + rowsDeleted);
        return rowsDeleted;
    }
}