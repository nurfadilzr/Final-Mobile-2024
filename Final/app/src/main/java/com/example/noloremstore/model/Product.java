package com.example.noloremstore.model;

public class Product {
    private int id;
    private String title;
    private String description;
    private String price;
    private String category;
    private String image;
    private int quantity;
//    private double rating;

    public Product(){

    }
    public Product(int id, String title, String description, String price, String category, String image, int quantity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
        this.quantity = quantity;
//        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return Double.parseDouble(price);
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    public double getRating() {
//        return rating;
//    }
//
//    public void setRating(double rating) {
//        this.rating = rating;
//    }
    // constructor, settergetter
}
