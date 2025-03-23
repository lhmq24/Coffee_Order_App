package com.example.coffee_order_app.Model.API;

import com.example.coffee_order_app.Model.Beverage;
import com.example.coffee_order_app.Model.Order;
import com.example.coffee_order_app.Model.OrderItemBeverageDTO;
import com.example.coffee_order_app.Model.Request.LogInRequest;
import com.example.coffee_order_app.Model.Response.LogInResponse;
import com.example.coffee_order_app.Model.Response.RefreshResponse;
import com.example.coffee_order_app.Model.Response.ValidateResponse;
import com.example.coffee_order_app.Model.TableOrderDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("auth/login.php")
        // Ensure this matches your PHP script's endpoint
    Call<LogInResponse> login(@Body LogInRequest request);

    @GET("data/TableOrder.php")
    Call<List<TableOrderDTO>> getAllTables(@Query("status") int status);

    @GET("data/TableOrder.php")
    Call<List<TableOrderDTO>> getPaidOrders(@Query("status") int status);

    @GET("data/Beverages.php")
    Call<List<Beverage>> queryBeverages(@Query("bev_name") String bev_name);

    @GET("data/Beverages.php")
    Call<List<Beverage>> queryBeverages();

    @GET("data/OrderItemBeverage.php")
    Call<List<OrderItemBeverageDTO>> getOrderItems(@Query("floor_number") int floor_number, @Query("tbl_number") int tableNumber);

    @POST("data/Order.php")
    Call<Order> getOrder(@Body int orderId);

    @POST("auth/refresh.php")
    Call<RefreshResponse> refreshToken(@Header("Authorization") String authHeader);

    @POST("auth/validate_token.php")
    @Headers("Content-Type: application/json")
    Call<ValidateResponse> isTokenExpired(@Header("Authorization") String token);

}

