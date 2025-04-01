package com.example.coffee_order_app.Model.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static String BASE_URL = "http://192.168.1.4/API/"; // Giá trị mặc định
    private static Retrofit retrofit;

    public static void init(ApiService apiService, Runnable callback) {
        retrofit = buildRetrofit();
        callback.run();
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

    public static String getURL() {
        return BASE_URL;
    }
}

