package com.example.noloremstore.response;

import com.example.noloremstore.model.Product;

import java.util.List;

public class ProductResponse {

    private List<Product> products;


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
