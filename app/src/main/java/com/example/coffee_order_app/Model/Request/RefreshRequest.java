package com.example.coffee_order_app.Model.Request;

public class RefreshRequest {
    private String refresh_token;

    public RefreshRequest(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getRefreshToken() {
        return refresh_token;
    }
}
