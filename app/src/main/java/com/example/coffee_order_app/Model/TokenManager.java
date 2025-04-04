package com.example.coffee_order_app.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class TokenManager {
    private static TokenManager instance;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    private TokenManager(Context context) {
        sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public static synchronized TokenManager getInstance(Context context) {
        if (instance == null) {
            instance = new TokenManager(context.getApplicationContext());
        }
        return instance;
    }

    public void saveAccessToken(String accessToken) {
        editor.putString("access_token", accessToken);
        editor.apply();
    }

    public void saveRefreshToken(String refreshToken) {
        editor.putString("refresh_token", refreshToken);
        editor.apply();
    }

    public String getAccessToken() {
        return sharedPreferences.getString("access_token", null);
    }

    public String getRefreshToken() {
        String token = sharedPreferences.getString("refresh_token", null);
        Log.d("TokenManager", "Retrieved refresh token: " + (token != null ? token : "NULL"));
        return token;
    }

    public void clearAccessToken() {
        editor.remove("access_token");
        editor.apply();
    }

    public void clearRefreshToken() {
        editor.remove("refresh_token");
        editor.apply();
    }

    public boolean hasAccessToken() {
        return sharedPreferences.contains("access_token");
    }
}
