package com.example.coffee_order_app.Model.Request;

import com.google.gson.annotations.SerializedName;

public class PaymentRequest {
    @SerializedName("floor_number")
    private final int floorNumber;

    @SerializedName("tbl_number")
    private final int tableNumber;

    public PaymentRequest(int floorNumber, int tableNumber) {
        this.floorNumber = floorNumber;
        this.tableNumber = tableNumber;
    }
}
