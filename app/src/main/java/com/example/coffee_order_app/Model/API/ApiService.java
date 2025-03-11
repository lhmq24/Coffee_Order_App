package com.example.coffee_order_app.Model.API;

import com.example.coffee_order_app.Model.Beverage;
import com.example.coffee_order_app.Model.Order;
import com.example.coffee_order_app.Model.OrderItemBeverageDTO;
import com.example.coffee_order_app.Model.Request.LogInRequest;
import com.example.coffee_order_app.Model.Request.ValidateRequest;
import com.example.coffee_order_app.Model.Response.LogInResponse;
import com.example.coffee_order_app.Model.Response.ValidateResponse;
import com.example.coffee_order_app.Model.TableOrderDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("auth/login.php")
        // Ensure this matches your PHP script's endpoint
    Call<LogInResponse> login(@Body LogInRequest request);

    @GET("data/TableOrder.php")
        // API Endpoint
    Call<List<TableOrderDTO>> getAllTables();

    @POST("data/Beverage.php")
    Call<List<Beverage>> queryBeverages(@Body String bev_name);

    @GET("data/OrderItemBeverage.php")
    Call<List<OrderItemBeverageDTO>> getOrderItems();

    @POST("data/Order.php")
    Call<Order> getOrder(@Body int orderId);

    @POST("auth/refresh.php")
    Call<LogInResponse> refreshToken(@Body String refreshToken);

    @POST("auth/validate_token.php")
    Call<ValidateResponse> isTokenExpired(@Body ValidateRequest request);
}

