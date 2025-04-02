package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Table implements Serializable {

    @SerializedName("tbl_id")
    private int id;
    @SerializedName("floor_number")
    private int floorNumber;

    @SerializedName("tbl_number")
    private int tableNumber;

    @SerializedName("tbl_capacity")
    private int tableCapacity;

    @SerializedName("tbl_status")
    private int status; // 0 = available, 1 = occupied

    @SerializedName("tbl_image")
    private String tableImage;


    // Constructor
    public Table(int id, int floorNumber, int tableNumber, int tableCapacity, int status, String tableImage) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.tableNumber = tableNumber;
        this.tableCapacity = tableCapacity;
        this.status = status;
        this.tableImage = tableImage;
    }

    // Getter methods


    public int getId() {
        return id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getTableCapacity() {
        return tableCapacity;
    }

    public int getStatus() {
        return status;
    }

    public String getTableImage() {
        return tableImage;
    }

    // Setter methods

    public void setId(int id) {
        this.id = id;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setTableImage(String tableImage) {
        this.tableImage = tableImage;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTableCapacity(int tableCapacity) {
        this.tableCapacity = tableCapacity;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
