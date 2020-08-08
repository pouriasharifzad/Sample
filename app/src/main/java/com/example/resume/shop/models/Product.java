package com.example.resume.shop.models;

public class Product {

    String title;
    int price;
    String imgresource;
    Float rating;
    int id;

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgresource() {
        return imgresource;
    }

    public void setImgresource(String imgresource) {
        this.imgresource = imgresource;
    }

    public Product(String title, int price, String imgresource) {
        this.rating=0f;
        this.title = title;
        this.price = price;
        this.imgresource = imgresource;


    }

    public Product(int id,String title, int price, String imgresource) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.imgresource = imgresource;

    }

    public Product(int id,String title, int price, String imgresource, Float rating) {
        this.title = title;
        this.price = price;
        this.imgresource = imgresource;
        this.rating = rating;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
