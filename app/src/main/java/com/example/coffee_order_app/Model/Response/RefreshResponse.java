package com.example.coffee_order_app.Model.Response;

public class RefreshResponse {
    private boolean success;
    private String access_token;
    private String access_created_at;
    private String expires_in;

    public RefreshResponse(boolean success, String access_token, String access_created_at, String expires_in) {
        this.success = success;
        this.access_token = access_token;
        this.access_created_at = access_created_at;
        this.expires_in = expires_in;
    }

    //Getter

    public boolean isSuccess() {
        return success;
    }

    public String getAccessToken() {
        return access_token;
    }

    public String getAccessCreatedTime() {
        return access_created_at;
    }

    public String getAccessExpiredTime() {
        return expires_in;
    }
}
