package com.sdsmdg.harjot.MusicDNA.models;

/**
 * Created by PC on 21/01/2018.
 */

public class Product {
    private String name;
    private String imgUrl;
    private String type;
    private String details;
    private int price;
    private int quantity;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", type='" + type + '\'' +
                ", details='" + details + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
