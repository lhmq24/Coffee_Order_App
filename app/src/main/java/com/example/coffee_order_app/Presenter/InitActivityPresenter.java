package com.example.coffee_order_app.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.coffee_order_app.Interface.InitActivityInterface;
import com.example.coffee_order_app.Interface.TokenExpirationCallback;
import com.example.coffee_order_app.Model.API.ApiClient;
import com.example.coffee_order_app.Model.API.ApiService;
import com.example.coffee_order_app.Model.Request.ValidateRequest;
import com.example.coffee_order_app.Model.Response.LogInResponse;
import com.example.coffee_order_app.Model.Response.ValidateResponse;
import com.example.coffee_order_app.Model.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InitActivityPresenter {
    private final ApiService apiService;
    private TokenManager tokenManager;
    private InitActivityInterface view;

    public InitActivityPresenter(InitActivityInterface view, Context context) {
        this.view = view;
        this.tokenManager = new TokenManager(context);
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
        ValidateRequest request = new ValidateRequest(accessToken);
        Call<ValidateResponse> call = apiService.isTokenExpired(request);

        call.enqueue(new Callback<ValidateResponse>() {
            @Override
            public void onResponse(Call<ValidateResponse> call, Response<ValidateResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String status = response.body().getStatus();
                    boolean isExpired = !"Success".equals(status); // "Success" means token is valid
                    callback.onTokenValidationResult(isExpired); // Pass result back via callback
                    Log.d("Init presenter", "Token is valid");
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
    public void refreshToken() {
        String refreshToken = tokenManager.getRefreshToken();
        if (refreshToken != null) {
            // Make the API call to refresh the token
            apiService.refreshToken(refreshToken).enqueue(new Callback<LogInResponse>() {
                @Override
                public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String newAccessToken = response.body().getAccessToken();
                        tokenManager.clearAccessToken();
                        tokenManager.saveAccessToken(newAccessToken);
                        view.moveToMain();
                    } else {
                        view.moveToLogIn();
                    }
                }

                @Override
                public void onFailure(Call<LogInResponse> call, Throwable t) {
                    view.moveToLogIn();
                }
            });
        } else {
            view.moveToLogIn();
        }
    }
}
