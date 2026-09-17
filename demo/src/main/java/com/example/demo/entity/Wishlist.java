package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
private Long productId;
    private String productName;

    private double price;

    public Wishlist() {
    }

    public Wishlist(String username,
                    Long productId,
                    String productName,
                    double price) {

        this.username = username;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

public Long getProductId() {
    return productId;
}

public void setProductId(Long productId) {
    this.productId = productId;
}
}