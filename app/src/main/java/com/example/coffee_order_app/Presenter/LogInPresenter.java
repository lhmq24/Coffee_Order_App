package com.example.coffee_order_app.Presenter;

import android.util.Log;

import com.example.coffee_order_app.Interface.LogInActivityInterface;
import com.example.coffee_order_app.Model.API.ApiClient;
import com.example.coffee_order_app.Model.API.ApiService;
import com.example.coffee_order_app.Model.Request.LogInRequest;
import com.example.coffee_order_app.Model.Response.LogInResponse;
import com.example.coffee_order_app.Model.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInPresenter {
    private final ApiService apiService;
    private final TokenManager token_manager;
    private LogInActivityInterface view;

    public LogInPresenter(LogInActivityInterface view) {
        apiService = ApiClient.getClient().create(ApiService.class);
        this.view = view;
        token_manager = TokenManager.getInstance(view.getContext());
    }

    public void login(String username, String password) {
        ApiClient.init(ApiClient.getClient().create(ApiService.class), () -> {
            LogInRequest request = new LogInRequest(username, password);
            Call<LogInResponse> call = apiService.login(request);

            call.enqueue(new Callback<LogInResponse>() {
                @Override
                public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        LogInResponse LogInResponse = response.body();
                        if (LogInResponse.isSuccess()) {
                            Log.d("Login", "Log In success");
                            //Saving token
                            String access_token = response.body().getAccessToken();
                            String refresh_token = response.body().getRefreshToken();
                            Log.d("Login", "Access Token: " + access_token);
                            Log.d("Login", "Refresh Token: " + refresh_token);
                            token_manager.saveAccessToken(access_token);
                            token_manager.saveRefreshToken(refresh_token);
                            view.movetoMain();
                        } else {
                            Log.e("Login", "Error: " + LogInResponse.getMessage());
                        }
                    } else {
                        Log.e("Login", "Server error");
                    }
                }

                @Override
                public void onFailure(Call<LogInResponse> call, Throwable t) {
                    Log.e("Login", "Network error: " + t.getMessage());
                }
            });
        });
    }
}
