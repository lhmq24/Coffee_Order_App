package com.example.coffee_order_app.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("login.php")
        // Ensure this matches your PHP script's endpoint
    Call<LogInResponse> login(@Body LogInRequest request);
    @GET("TableOrder.php")
        // API Endpoint
    Call<List<TableOrderDTO>> getAllTables();

    @GET("Tables.php")
        // API Endpoint
    Call<Table> getTableById(int tableId);

}

