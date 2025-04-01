package com.example.coffee_order_app.Model.Request;

import com.google.gson.annotations.SerializedName;

public class updateOrderItemRequest {
    @SerializedName("ord_id")
    private int orderId;
    @SerializedName("bev_id")
    private int bevId;
    @SerializedName("item_quantity")
    private int item_quantity;

    public updateOrderItemRequest(int orderId, int bevId, int item_quantity) {
        this.orderId = orderId;
        this.bevId = bevId;
        this.item_quantity = item_quantity;
    }
}
