package com.example.coffee_order_app.Presenter;

import android.util.Log;

import com.example.coffee_order_app.Model.ApiClient;
import com.example.coffee_order_app.Model.ApiService;
import com.example.coffee_order_app.Model.LogInRequest;
import com.example.coffee_order_app.Model.LogInResponse;
import com.example.coffee_order_app.View.LogInActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInPresenter {
    private final ApiService apiService;
    private LogInActivity activity;

    public LogInPresenter(LogInActivity activity) {
        apiService = ApiClient.getClient().create(ApiService.class);
        this.activity = activity;
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
                            Log.d("Login", "Access Token: " + LogInResponse.getAccessToken());
                            Log.d("Login", "Refresh Token: " + LogInResponse.getRefreshToken());
                            activity.movetoMain();
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
