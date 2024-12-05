# FruitShop 项目文档

## 项目简介

FruitShop 是一个简单的水果销售管理系统，它允许商家查看、添加、更新和删除水果信息。该项目使用了Java语言和MySQL数据库。

## 目录结构

```
├── .mxDev
│   └── frameworks
├── dao
│   ├── JdbcFruit.java
│   └── JDBCUtils.java
├── Entity
│   └── Fruit.java
├── FruitShop.iml
├── FruitShop.java
├── images
├── lib
│   └── Create_FruitShop_DataSheet.sql
├── README.md
├── Test
│   └── JDBCUtilsTest.java
├── util
│   ├── Init.java
│   ├── viewEastListen.java
│   ├── viewNorth.java
│   └── viewWest.java
└── view
    ├── Center.java
    ├── East.java
    ├── FruitDetail.java
    ├── North.java
    ├── South.java
    └── West.java
```

## 主要文件

- **JdbcFruit.java**: 数据访问对象（DAO）类，用于处理与水果相关的数据库操作。
- **JDBCUtils.java**: 工具类，提供数据库连接和资源关闭的方法。
- **Fruit.java**: 实体类，表示水果对象。
- **FruitShop.java**: 主类，包含程序的入口点。
- **README.md**: 项目文档文件。

## 如何运行

1. 确保你的计算机上已安装Java和MySQL数据库。
2. 在MySQL中创建名为`FruitShop`的数据库，并运行`lib`目录下的`Create_FruitShop_DataSheet.sql`脚本来创建必要的表。
3. 将项目导入到你的IDE中（例如IntelliJ IDEA或Eclipse）。
4. 运行`FruitShop.java`类。

## 贡献

如果你有任何改进意见或想要贡献代码，请随时提交Pull Request或创建Issue。

## 许可证

本项目遵循MIT许可证。请查看LICENSE文件了解更多信息。

## 联系方式

如有任何问题或建议，请通过GitHub Issues页面与我联系。
邮箱：2967557176@qq.com
