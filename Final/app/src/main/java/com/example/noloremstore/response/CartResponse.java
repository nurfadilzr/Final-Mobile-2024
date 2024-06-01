package com.example.noloremstore.response;

import com.example.noloremstore.model.Cart;

import java.util.List;

public class CartResponse {
    private List<Cart> carts;

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
