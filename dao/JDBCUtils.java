package FruitShop.dao;

import FruitShop.Entity.Customer;
import FruitShop.Entity.Fruit;
import FruitShop.Entity.Orders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils {
    // 数据库连接信息
    private static final String URL = "jdbc:mysql://localhost:3306/FruitShop";
    private static final String USER = "123456";
    private static final String PASSWORD = "123456";

    /**
     * 获取数据库连接
     *
     * @return Connection对象
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * 关闭资源
     *
     * @param conn 连接对象
     * @param ps   PreparedStatement对象
     * @param rs   ResultSet对象
     */
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 执行查询操作
     *
     * @param sql 查询SQL语句
     * @param params 参数数组
     * @return ResultSet结果集
     */
    public static ResultSet executeQuery(String sql, Object[] params) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return rs;
    }

    /**
     * 执行更新操作（包括插入、更新、删除）
     *
     * @param sql 更新SQL语句
     * @param params 参数数组
     * @return 影响行数
     */
    public static int executeUpdate(String sql, Object[] params) {
        Connection conn = null;
        PreparedStatement ps = null;
        int affectedRows = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            close(conn, ps, null);
        }
        return affectedRows;
    }

    // 测试
    // 导入 fruit 表 测试
    public static void importFruit(Fruit fruit) {
        String sql = "INSERT INTO fruit (name, image_url, price, stock) VALUES (?, ?, ?, ?)";
        Object[] params = {fruit.getName(), fruit.getImgUrl(), fruit.getPrice(), fruit.getStock()};
        executeUpdate(sql, params);
    }

    // 导入 customer 表 测试
    public static void importCustomer(Customer customer) {
        String sql = "INSERT INTO customer (name, contact_info, address) VALUES (?, ?, ?)";
        Object[] params = {customer.getName(), customer.getPhone(), customer.getAddress()};
        executeUpdate(sql, params);
    }

    // 导入 orders 表 测试
    public static void importOrders(Orders orders) {
        String sql = "INSERT INTO orders (fruit_id, customer_id, quantity, status) VALUES (?, ?, ?, ?)";
        Object[] params = {orders.getFruitId(), orders.getCustomerId(), orders.getQuantity(), orders.getStatus()};
        executeUpdate(sql, params);
    }
}