package com.example.coffee_order_app.Model;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static String BASE_URL = "http://192.168.1.15/API/data/"; // Giá trị mặc định
    private static Retrofit retrofit;

    public static void init(ApiService apiService, Runnable callback) {
        apiService.getServerIP().enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String serverIP = response.body().get("server_ip");
                    if (serverIP != null) {
                        BASE_URL = "http://" + serverIP + "/API/data/";
                    }
                }
                retrofit = buildRetrofit();
                callback.run();
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                retrofit = buildRetrofit();
                callback.run();
            }
        });
    }

    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getClient() {
        return retrofit != null ? retrofit : buildRetrofit();
    }
}

