package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class OrderUpdate {
    @SerializedName("update_id")
    private int updateId;
    @SerializedName("staff_id")
    private int staffId;
    @SerializedName("floor_number")
    private int floorNumber;
    @SerializedName("tbl_id")
    private int tableId;
    @SerializedName("ord_id")
    private int orderId;
    @SerializedName("update_time")
    private int updateTime;

    public OrderUpdate(int updateId, int staffId, int floorNumber,
                       int tableId, int orderId, int updateTime) {
        this.updateId = updateId;
        this.staffId = staffId;
        this.floorNumber = floorNumber;
        this.tableId = tableId;
        this.orderId = orderId;
        this.updateTime = updateTime;
    }

    //Getter

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    //Setter

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }
}
