package com.example.coffee_order_app.Model.Request;

public class ValidateRequest {
    private String access_token;

    public ValidateRequest(String accessToken) {
        this.access_token = "Bearer " + accessToken;
    }
}
