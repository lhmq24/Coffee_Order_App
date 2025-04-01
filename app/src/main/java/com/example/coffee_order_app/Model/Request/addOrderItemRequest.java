package com.example.coffee_order_app.Model.Request;

import com.google.gson.annotations.SerializedName;

public class addOrderItemRequest {
    @SerializedName("tbl_number")
    private int tableNumber;
    @SerializedName("floor_number")
    private int floorNumber;

    @SerializedName("bev_id")
    private int bevId;

    @SerializedName("bev_name")
    private String bevName;

    public addOrderItemRequest(int floorNumber, int tableNumber, int bevId, String bevName) {
        this.floorNumber = floorNumber;
        this.tableNumber = tableNumber;
        this.bevId = bevId;
        this.bevName = bevName;
    }

}
