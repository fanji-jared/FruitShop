package FruitShop.Entity;

import java.text.DecimalFormat;
import java.util.Random;

public class Fruit {
    //水果图片链接
    private String ImgUrl;
    //水果排列id
    private int id;
    //水果名
    private String name;
    //价格（元/斤）
    private double price;
    //库存（斤）
    private double stock;

    public Fruit(int id, String name) {
        setId(id);
        setName(name);
        Random ra = new Random();
        //使用format保留double的前两位
        DecimalFormat df = new DecimalFormat("######.00");
        setPrice(Double.parseDouble(df.format(ra.nextDouble(1.0, 21.0))));
        setStock(Double.parseDouble(df.format(ra.nextDouble(0, 101.0))));
        setImgUrl("images/default.png");
    }

    public Fruit(int id, String name, String ImgUrl) {
        setId(id);
        setName(name);
        setImgUrl(ImgUrl);
        Random ra = new Random();
        //使用format保留double的前两位
        DecimalFormat df = new DecimalFormat("######.00");
        setPrice(Double.parseDouble(df.format(ra.nextDouble(1.0, 21.0))));
        setStock(Double.parseDouble(df.format(ra.nextDouble(0, 101.0))));
    }

    public Fruit(int id, String name, String ImgUrl, double price, double stock) {
        setId(id);
        setName(name);
        setImgUrl(ImgUrl);
        setPrice(price >= 0 ? price : 0);
        setStock(stock >= 0 ? stock : 0);
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getStock() {
        return stock;
    }
}