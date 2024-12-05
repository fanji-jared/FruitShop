package FruitShop.Test;

import FruitShop.Entity.Customer;
import FruitShop.Entity.Fruit;
import FruitShop.Entity.Orders;
import FruitShop.dao.JDBCUtils;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.junit.Test;

import java.util.Date;
import java.util.Random;

public class importFruitsTest {
    @Test
    // 使用JdbcFriut将水果数组导入到数据库中
    public void importFruits() {
        // 水果数组
        Fruit[] fruitss = new Fruit[]{
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
        // 导入水果数组
        for (Fruit fruit : fruitss) {
            JDBCUtils.importFruit(fruit);
        }
    }

    @Test
    // 虚构数据 - 导入 customer 表
    public void importCustomer() {
        // 虚构 Customer 实体数组
        Customer[] customers = new Customer[]{
                new Customer(1, "张三", "12345678901", "12345678901"),
                new Customer(2, "李四", "12345678902", "12345678902"),
                new Customer(3, "王五", "12345678903", "12345678903"),
                new Customer(4, "赵六", "12345678904", "12345678904"),
                new Customer(5, "钱七", "12345678905", "12345678905"),
                new Customer(6, "孙八", "12345678906", "12345678906"),
                new Customer(7, "周九", "12345678907", "12345678907"),
                new Customer(8, "吴十", "12345678908", "12345678908"),
                new Customer(9, "郑十一", "12345678909", "12345678909"),
                new Customer(10, "王十二", "12345678910", "12345678910")
        };
        for (Customer customer : customers) {
            JDBCUtils.importCustomer(customer);
        }
    }

    @Test
    // 虚构数据 - 导入 order 表
    public void importOrder() {
        // 随机时间
        Random random = new Random();
        // 虚构 Orders 实体数组
        Orders[] orders = new Orders[]{
                new Orders(1, 1, 1, random.nextDouble(100.00), "2024年12月5日13:22:10", "已发货"),
                new Orders(2, 2, 2, random.nextDouble(100.00), "2024年12月5日13:22:10", "已发货"),
                new Orders(3, 3, 3, random.nextDouble(100.00), "2024年12月5日13:22:10", "未发货"),
                new Orders(4, 4, 4, random.nextDouble(100.00), "2024年12月5日13:22:10", "已发货"),
                new Orders(5, 5, 5, random.nextDouble(100.00), "2024年12月5日13:22:10", "已发货"),
                new Orders(6, 6, 6, random.nextDouble(100.00), "2024年12月5日13:22:10", "未发货"),
                new Orders(7, 7, 7, random.nextDouble(100.00), "2024年12月5日13:22:10", "未发货"),
                new Orders(8, 8, 8, random.nextDouble(100.00), "2024年12月5日13:22:10", "已发货"),
                new Orders(9, 9, 9, random.nextDouble(100.00), "2024年12月5日13:22:10", "已发货"),
                new Orders(10, 10, 10, random.nextDouble(100.00), "2024年12月5日13:22:10", "未发货")
        };
        for (Orders order : orders) {
            JDBCUtils.importOrders(order);
        }
    }
}
