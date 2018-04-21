package com.esprit.mtdev.MusicBox.models;

import java.util.HashMap;
import java.util.List;

/**
 * Created by PC on 21/01/2018.
 */

public class Cart {
    private List<Product> products;
    private int customerId;

    public Cart() {
    }

    public Cart(List<Product> products, int customerId) {
        this.products = products;
        this.customerId = customerId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                ", customerId=" + customerId +
                '}';
    }
}
