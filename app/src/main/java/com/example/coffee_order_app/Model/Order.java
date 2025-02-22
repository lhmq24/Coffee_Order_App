package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("ord_id")
    private int id;

    @SerializedName("ord_table_id")
    private int tableId;

    @SerializedName("ord_total_price")
    private float totalPrice;

    @SerializedName("ord_status")
    private int status; // 0 = Open, 1 = Paid

    @SerializedName("ord_created_at")
    private long createdAt; // Timestamp in milliseconds

    // Constructor
    public Order(int tableId) {
        this.tableId = tableId;
        this.totalPrice = 0;
        this.status = 0;
        this.createdAt = System.currentTimeMillis(); // Auto-assign created time
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public int getTableId() {
        return tableId;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public float getTotalPrice() {
        return totalPrice;
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

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
