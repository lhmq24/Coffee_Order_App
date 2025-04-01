package com.example.coffee_order_app.Model.Response;

import com.google.gson.annotations.SerializedName;

public class deleteOrderItemResponse {
    @SerializedName("success")
    boolean success;
    @SerializedName("message")
    String message;

    public deleteOrderItemResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
