package com.example.coffee_order_app.Model.Response;

public class ValidateResponse {
    String status;
    String message;

    public ValidateResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    //Getter

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
