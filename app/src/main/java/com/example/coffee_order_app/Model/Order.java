package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Order {

    @SerializedName("ord_id")
    private int orderId;

    @SerializedName("tbl_id")
    private int tableId;

    @SerializedName("ord_total_price")
    private float totalPrice;

    @SerializedName("ord_status")
    private int status; // 0 = Open, 1 = Paid

    @SerializedName("ord_created_at")
    private Timestamp createdAt;

    @SerializedName("ord_paid_at")
    private Timestamp paidAt;

    // Constructor


    public Order(int orderId, int tableId, float totalPrice, int status,
                 Timestamp createdAt, Timestamp paidAt) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.paidAt = paidAt;
    }

    // Getter methods

    public int getOrderId() {
        return orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getStatus() {
        return status;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    // Setter methods

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Timestamp paidAt) {
        this.paidAt = paidAt;
    }
}
