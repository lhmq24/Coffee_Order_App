package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("tbl_id")
    private int id;

    @SerializedName("tbl_number")
    private int tableNumber;

    @SerializedName("tbl_status")
    private int status; // 0 = available, 1 = occupied

    // Constructor
    public Table(int id, int tableNumber) {
        this.id = id;
        this.tableNumber = tableNumber;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getStatus() {
        return status;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
