package com.example.coffee_order_app.Model;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("TableOrder.php")
        // API Endpoint
    Call<List<TableOrderDTO>> getAllTables();

    @GET("Tables.php")
        // API Endpoint
    Call<Table> getTableById(int tableId);

    @GET("get_ip.php")
        // API lay IP dong từ PHP
    Call<Map<String, String>> getServerIP();
}

