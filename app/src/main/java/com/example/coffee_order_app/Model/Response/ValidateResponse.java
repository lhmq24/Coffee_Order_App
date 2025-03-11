package com.example.coffee_order_app.Model.Response;

public class ValidateResponse {
    Boolean status;
    String message;

    public ValidateResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    //Getter

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
