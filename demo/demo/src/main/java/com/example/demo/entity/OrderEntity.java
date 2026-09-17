package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 👤 USER INFO
    private String username;

    // 📦 PRODUCT SNAPSHOT (IMPORTANT FOR REAL E-COMMERCE)
    private String productName;
    private String imageUrl;
    private double price;
    private int quantity;

    // 🏠 DELIVERY INFO
    private String address;

    // 📅 ORDER TIMING
    private LocalDateTime orderDate = LocalDateTime.now();

    // 🚚 ORDER STATUS (Amazon-style lifecycle)
    private String status; 
    // PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED

    public OrderEntity() {
    }

    // 🔥 CONSTRUCTOR (OPTIONAL USE)
    public OrderEntity(String username,
                       String productName,
                       String imageUrl,
                       double price,
                       int quantity,
                       String address,
                       String status) {

        this.username = username;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.status = status;
        this.orderDate = LocalDateTime.now();
    }

    // =========================
    // GETTERS & SETTERS
    // =========================

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // 💰 CALCULATED FIELD (NO NEED TO STORE IN DB)
    public double getTotal() {
        return price * quantity;
    }
}