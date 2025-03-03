package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

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
    private String createdAt;

    @SerializedName("ord_paid_at")
    private String paidAt;

    // Constructor

    public Order(int orderId, int tableId, float totalPrice,
                 int status, String createdAt, String paidAt) {
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

    public float getTotalPrice() {
        return totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // Setter methods

    public String getPaidAt() {
        return paidAt != null ? paidAt : "Not Paid";
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

    public void setPaidAt(String paidAt) {
        this.paidAt = paidAt;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
