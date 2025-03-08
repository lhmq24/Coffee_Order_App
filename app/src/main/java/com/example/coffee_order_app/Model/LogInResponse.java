package com.example.coffee_order_app.Model;

public class LogInResponse {
    private boolean success;
    private String access_token;
    private String refresh_token;
    private String access_created_at;
    private String expires_in;
    private String message; // In case of errors

    public boolean isSuccess() {
        return success;
    }

    public String getAccessToken() {
        return access_token;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public String getAccessCreatedAt() {
        return access_created_at;
    }

    public String getExpiresIn() {
        return expires_in;
    }

    public String getMessage() {
        return message;
    }
}
