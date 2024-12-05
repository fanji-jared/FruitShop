/*
 Navicat Premium Data Transfer

 Source Server         : FruitShop
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : fruitshop

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 05/12/2024 18:49:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contact_info` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (2, '张三', '12345678901', '12345678901');
INSERT INTO `customer` VALUES (3, '李四', '12345678902', '12345678902');
INSERT INTO `customer` VALUES (4, '王五', '12345678903', '12345678903');
INSERT INTO `customer` VALUES (5, '赵六', '12345678904', '12345678904');
INSERT INTO `customer` VALUES (6, '钱七', '12345678905', '12345678905');
INSERT INTO `customer` VALUES (7, '孙八', '12345678906', '12345678906');
INSERT INTO `customer` VALUES (8, '周九', '12345678907', '12345678907');
INSERT INTO `customer` VALUES (9, '吴十', '12345678908', '12345678908');
INSERT INTO `customer` VALUES (10, '郑十一', '12345678909', '12345678909');
INSERT INTO `customer` VALUES (11, '王十二', '12345678910', '12345678910');

-- ----------------------------
-- Table structure for fruit
-- ----------------------------
DROP TABLE IF EXISTS `fruit`;
CREATE TABLE `fruit`  (
  `fruit_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NOT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`fruit_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fruit
-- ----------------------------
INSERT INTO `fruit` VALUES (12, '橘子', 'https://ns-strategy.cdn.bcebos.com/ns-strategy/upload/fc_big_pic/part-00554-1041.jpg', 15.68, 26);
INSERT INTO `fruit` VALUES (11, '香蕉', 'https://img1.baidu.com/it/u=2863208510,1801169639&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500', 20.99, 66);
INSERT INTO `fruit` VALUES (10, '苹果', 'https://img0.baidu.com/it/u=3802269103,3841920027&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', 12.55, 17);
INSERT INTO `fruit` VALUES (13, '草莓', 'https://img2.baidu.com/it/u=2316021847,500184263&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500', 10.76, 32);
INSERT INTO `fruit` VALUES (14, '菠萝', 'https://img2.baidu.com/it/u=3081301703,2570572501&fm=253&fmt=auto&app=138&f=JPEG?w=640&h=465', 20.51, 92);
INSERT INTO `fruit` VALUES (15, '山竹', 'https://img2.baidu.com/it/u=2774691756,2865735553&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=362', 12.54, 53);
INSERT INTO `fruit` VALUES (16, '芒果', 'https://img0.baidu.com/it/u=1917801406,491508465&fm=253&fmt=auto&app=138&f=JPG?w=640&h=480', 1.54, 42);
INSERT INTO `fruit` VALUES (17, '柠檬', 'https://img1.baidu.com/it/u=2619362584,4147884554&fm=253&fmt=auto&app=138&f=JPEG?w=600&h=400', 18.35, 26);
INSERT INTO `fruit` VALUES (18, '葡萄', 'https://img1.baidu.com/it/u=2116955464,2818384859&fm=253&fmt=auto&app=138&f=JPEG?w=281&h=499', 10.51, 14);
INSERT INTO `fruit` VALUES (19, '葡萄柚', 'https://img1.baidu.com/it/u=3137894260,3783562477&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800', 8.47, 34);
INSERT INTO `fruit` VALUES (20, '西瓜', 'https://img1.baidu.com/it/u=4108434870,2496367536&fm=253&fmt=auto&app=138&f=JPEG?w=747&h=500', 14.40, 44);
INSERT INTO `fruit` VALUES (21, '梨子', 'https://img1.baidu.com/it/u=4235726655,2356266912&fm=253&fmt=auto&app=120&f=JPEG?w=607&h=500', 18.21, 43);
INSERT INTO `fruit` VALUES (22, '猕猴桃', 'https://img2.baidu.com/it/u=2063178977,1294711858&fm=253&fmt=auto&app=138&f=JPEG?w=753&h=500', 1.40, 52);
INSERT INTO `fruit` VALUES (23, '石榴', 'https://img0.baidu.com/it/u=1201922027,250286356&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=443', 6.31, 53);
INSERT INTO `fruit` VALUES (24, '枇杷', 'https://img2.baidu.com/it/u=4067251393,3746805917&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500', 0.00, 100);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `fruit_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_date` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `fruit_id`(`fruit_id`) USING BTREE,
  INDEX `customer_id`(`customer_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (2, 18, 1, 65, '2024-12-05 13:54:10', '已发货');
INSERT INTO `orders` VALUES (3, 2, 2, 55, '2024-12-05 13:54:10', '已发货');
INSERT INTO `orders` VALUES (4, 18, 3, 1, '2024-12-05 13:54:10', '未发货');
INSERT INTO `orders` VALUES (5, 4, 4, 73, '2024-12-05 13:54:10', '已发货');
INSERT INTO `orders` VALUES (6, 5, 5, 76, '2024-12-05 13:54:10', '已发货');
INSERT INTO `orders` VALUES (7, 6, 6, 45, '2024-12-05 13:54:10', '未发货');
INSERT INTO `orders` VALUES (8, 18, 7, 69, '2024-12-05 13:54:10', '未发货');
INSERT INTO `orders` VALUES (9, 8, 8, 7, '2024-12-05 13:54:10', '已发货');
INSERT INTO `orders` VALUES (10, 18, 9, 38, '2024-12-05 13:54:10', '已发货');
INSERT INTO `orders` VALUES (11, 10, 10, 59, '2024-12-05 13:54:10', '未发货');
INSERT INTO `orders` VALUES (12, 1, 1, 44, '2024-12-05 13:55:15', '已发货');
INSERT INTO `orders` VALUES (13, 18, 2, 85, '2024-12-05 13:55:15', '已发货');
INSERT INTO `orders` VALUES (14, 3, 3, 80, '2024-12-05 13:55:15', '未发货');
INSERT INTO `orders` VALUES (15, 4, 4, 53, '2024-12-05 13:55:15', '已发货');
INSERT INTO `orders` VALUES (16, 18, 5, 20, '2024-12-05 13:55:15', '已发货');
INSERT INTO `orders` VALUES (17, 6, 6, 1, '2024-12-05 13:55:15', '未发货');
INSERT INTO `orders` VALUES (18, 18, 7, 21, '2024-12-05 13:55:15', '未发货');
INSERT INTO `orders` VALUES (19, 8, 8, 16, '2024-12-05 13:55:15', '已发货');
INSERT INTO `orders` VALUES (20, 9, 9, 10, '2024-12-05 13:55:15', '已发货');
INSERT INTO `orders` VALUES (21, 10, 10, 86, '2024-12-05 13:55:15', '未发货');

SET FOREIGN_KEY_CHECKS = 1;
