package com.example.fullstack.persistence.entity;

import jakarta.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="order_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Orders orders;

    @JoinColumn(name="product_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Products products;

    private int quantity;

    private double price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orders=" + orders +
                ", products=" + products +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
