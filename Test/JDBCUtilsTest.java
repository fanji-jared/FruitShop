package FruitShop.Test;

import FruitShop.dao.JDBCUtils;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtilsTest {
    @Test
    public void cr(){
        // 插入数据
        String insertSql = "INSERT INTO fruit (name, image_url, price, stock) VALUES (?, ?, ?, ?)";
        Object[] insertParams = {"test", "http://a.c.com/s/3244332/34", 1.21, 43};
        int rowsInserted = JDBCUtils.executeUpdate(insertSql, insertParams);
        System.out.println("Rows inserted: " + rowsInserted);
    }

    @Test
    public void gx(){
        // 更新数据
        String updateSql = "UPDATE fruit SET name = ?, email = ? WHERE id = ?";
        Object[] updateParams = {"Jane Doe", "jane.doe@example.com", 1};
        int rowsUpdated = JDBCUtils.executeUpdate(updateSql, updateParams);
        System.out.println("Rows updated: " + rowsUpdated);
    }

    @Test
    public void cx(){
        // 查询数据
        String selectSql = "SELECT * FROM fruit";
        Object[] selectParams = {};
        ResultSet rs = JDBCUtils.executeQuery(selectSql, selectParams);
        try {
            while (rs.next()) {
                int id = rs.getInt("fruit_id");
                String name = rs.getString("name");
                String imageUrl = rs.getString("image_url");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                System.out.println(id + " " + name + " " + imageUrl + " " + price + " " + stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            JDBCUtils.close(null, null, rs); // 关闭ResultSet
        }
    }

    @Test
    public void sc(){
        // 删除数据
        String deleteSql = "DELETE FROM users WHERE id = ?";
        Object[] deleteParams = {1};
        int rowsDeleted = JDBCUtils.executeUpdate(deleteSql, deleteParams);
        System.out.println("Rows deleted: " + rowsDeleted);
    }
}
