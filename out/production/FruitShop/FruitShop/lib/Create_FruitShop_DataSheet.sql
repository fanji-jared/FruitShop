-- 创建 Fruit 表
CREATE TABLE Fruit (
    fruit_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    image_url VARCHAR(255),
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);

-- 创建 Customer 表
CREATE TABLE Customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    contact_info VARCHAR(100) NOT NULL,
    address VARCHAR(255)
);

-- 创建 Orders 表
CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    fruit_id INT NOT NULL,
    customer_id INT NOT NULL,
    quantity INT NOT NULL,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (fruit_id) REFERENCES Fruit(fruit_id),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);