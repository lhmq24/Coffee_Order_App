package com.example.coffee_order_app.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.coffee_order_app.Interface.InitActivityInterface;
import com.example.coffee_order_app.Interface.TokenExpirationCallback;
import com.example.coffee_order_app.Model.API.ApiClient;
import com.example.coffee_order_app.Model.API.ApiService;
import com.example.coffee_order_app.Model.Response.RefreshResponse;
import com.example.coffee_order_app.Model.Response.ValidateResponse;
import com.example.coffee_order_app.Model.TokenManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InitActivityPresenter {
    private final ApiService apiService;
    private TokenManager tokenManager;
    private InitActivityInterface view;

    public InitActivityPresenter(InitActivityInterface view, Context context) {
        this.view = view;
        this.tokenManager = TokenManager.getInstance(context);
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    // Check if the token is expired
    public void checkTokenExpiration(TokenExpirationCallback callback) {
        String accessToken = tokenManager.getAccessToken();
        if (accessToken == null || accessToken.isEmpty()) {
            view.moveToLogIn();
            Log.d("Init presenter", "move to Log In");
            callback.onTokenValidationResult(true); // Token is missing or invalid, treat as expired
        } else {
            isTokenExpired(accessToken, callback); // Pass callback to handle the result asynchronously
            Log.d("Init presenter", "Pass callback");
        }
    }


    // Method to check if the token is expired with API request
    private void isTokenExpired(String accessToken, TokenExpirationCallback callback) {
        String bearerToken = "Bearer " + accessToken;
        Call<ValidateResponse> call = apiService.isTokenExpired(bearerToken);

        call.enqueue(new Callback<ValidateResponse>() {
            @Override
            public void onResponse(Call<ValidateResponse> call, Response<ValidateResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("Init presenter", "Access Token status: " + response.body().getStatus());
                    boolean isExpired = !response.body().getStatus();
                    if (isExpired) {
                        Log.d("Init presenter", "Token is invalid");
                        callback.onTokenValidationResult(true); // Chỉ báo token hết hạn
                    } else {
                        Log.d("Init presenter", "Token is valid");
                        callback.onTokenValidationResult(false);
                    }
                } else {
                    callback.onTokenValidationResult(true); // If error in server response, treat token as expired
                    Log.d("Validate Token", "Server Error");
                }
            }

            @Override
            public void onFailure(Call<ValidateResponse> call, Throwable t) {
                callback.onTokenValidationResult(true); // On failure, assume token expired
                Log.d("Validate Token", "Network Error: " + t.getMessage());
            }
        });
    }

    // Method to refresh the token (use API to refresh)
    public void refresh_token() {
        // Lấy refresh token từ TokenManager
        String refreshToken = tokenManager.getRefreshToken();
        if (refreshToken == null || refreshToken.isEmpty()) {
            Log.e("Init presenter", "No refresh token available");
            tokenManager.clearAccessToken();
            tokenManager.clearRefreshToken();
            view.moveToLogIn();
            return;
        }

        Log.d("Init presenter", "Refresh token: " + refreshToken);

        // Gọi API refresh token với token trong Authorization header
        apiService.refreshToken("Bearer " + refreshToken).enqueue(new Callback<RefreshResponse>() {
            @Override
            public void onResponse(Call<RefreshResponse> call, Response<RefreshResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String newAccessToken = response.body().getAccessToken();
                    if (newAccessToken != null && !newAccessToken.isEmpty()) {
                        Log.d("Init presenter", "New access token: " + newAccessToken);
                        tokenManager.clearAccessToken();
                        tokenManager.saveAccessToken(newAccessToken);
                        view.moveToMain();
                    } else {
                        Log.e("Init presenter", "Response does not contain a new access token");
                        tokenManager.clearRefreshToken();
                        view.moveToLogIn();
                    }
                } else {
                    Log.e("Init presenter", "Failed to refresh token. Code: " + response.code());
                    try {
                        Log.e("Init presenter", "Error body: " + response.errorBody().string());
                    } catch (IOException e) {
                        Log.e("Init presenter", e.getMessage());
                    }
                    tokenManager.clearRefreshToken();
                    view.moveToLogIn();
                }
            }

            @Override
            public void onFailure(Call<RefreshResponse> call, Throwable t) {
                Log.e("Init presenter", "Network error: " + t.getMessage());
                view.moveToLogIn();
            }
        });
    }
}
