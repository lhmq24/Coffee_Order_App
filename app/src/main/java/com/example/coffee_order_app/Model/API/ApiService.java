package com.example.coffee_order_app.Model.API;

import com.example.coffee_order_app.Model.Beverage;
import com.example.coffee_order_app.Model.LogInRequest;
import com.example.coffee_order_app.Model.LogInResponse;
import com.example.coffee_order_app.Model.Order;
import com.example.coffee_order_app.Model.OrderItemBeverageDTO;
import com.example.coffee_order_app.Model.TableOrderDTO;

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

    @POST("Beverage.php")
    Call<List<Beverage>> queryBeverages(String bev_name);

    @GET("OrderItemBeverage.php")
    Call<List<OrderItemBeverageDTO>> getOrderItems();

    @POST("Order.php")
    Call<Order> getOrder(int orderId);
}

