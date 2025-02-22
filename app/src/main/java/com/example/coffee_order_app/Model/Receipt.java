package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class Receipt {

    @SerializedName("rec_id")
    private int id;

    @SerializedName("rec_order_id")
    private int orderId;

    @SerializedName("rec_table_id")
    private int tableId;

    @SerializedName("rec_total_price")
    private float totalPrice;

    @SerializedName("rec_paid_time")
    private long paidTime;

    // Constructor
    public Receipt(int orderId, int tableId, float totalPrice) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.totalPrice = totalPrice;
        this.paidTime = System.currentTimeMillis();
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public long getPaidTime() {
        return paidTime;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPaidTime(long paidTime) {
        this.paidTime = paidTime;
    }
}
