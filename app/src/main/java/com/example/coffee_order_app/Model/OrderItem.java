package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class OrderItem {

    @SerializedName("item_id")
    private int id;

    @SerializedName("item_order_id")
    private int orderId;

    @SerializedName("item_beverage_id")
    private int beverageId;

    @SerializedName("item_quantity")
    private int quantity;

    @SerializedName("item_price")
    private float price;

    @SerializedName("item_status")
    private int status;  // 0 = Not served (default), 1 = Served

    @SerializedName("item_order_time")
    private long orderTime;

    // Constructor
    public OrderItem(int orderId, int beverageId, int quantity, float price) {
        this.orderId = orderId;
        this.beverageId = beverageId;
        this.quantity = quantity;
        this.price = price;
        this.status = 0;
        this.orderTime = System.currentTimeMillis();
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getBeverageId() {
        return beverageId;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public int getStatus() {
        return status;
    }

    public long getOrderTime() {
        return orderTime;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setBeverageId(int beverageId) {
        this.beverageId = beverageId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }
}
