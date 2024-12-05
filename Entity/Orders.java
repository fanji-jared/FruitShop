package FruitShop.Entity;

/**
 * -- 创建 Orders 表
 * CREATE TABLE Orders (
 *     order_id INT AUTO_INCREMENT PRIMARY KEY,
 *     fruit_id INT NOT NULL,
 *     customer_id INT NOT NULL,
 *     quantity INT NOT NULL,
 *     order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
 *     status VARCHAR(50) NOT NULL,
 *     FOREIGN KEY (fruit_id) REFERENCES Fruit(fruit_id),
 *     FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
 * );
 */
public class Orders {
    private int orderID;
    private int  fruitID;
    private int customerID;
    private double quantity;
    private String orderDate;
    private String status;
    public Orders(int orderID, int fruitID, int customerID, double quantity, String orderDate, String status) {
        this.orderID = orderID;
        this.fruitID = fruitID;
        this.customerID = customerID;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.status = status;
    }
    public Orders() {

    }
    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public int getFruitID() {
        return fruitID;
    }
    public void setFruitID(int fruitID) {
        this.fruitID = fruitID;
    }
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Orders{" +
 "orderID=" + orderID +
 ", fruitID=" + fruitID +
 ", customerID=" + customerID +
 ", quantity=" + quantity +
 ", orderDate='" + orderDate + '\'' +
 ", status='" + status + '\'' +
 '}';
    }
}
