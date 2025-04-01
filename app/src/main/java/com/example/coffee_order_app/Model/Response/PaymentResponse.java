package com.example.coffee_order_app.Model.Response;

public class PaymentResponse {

    private boolean success;

    private String message;

    public PaymentResponse(boolean success, String message) {
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
