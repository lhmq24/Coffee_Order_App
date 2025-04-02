package com.example.coffee_order_app.Model.API;

import com.example.coffee_order_app.Model.BeveragePriceDTO;
import com.example.coffee_order_app.Model.OrderItemBeverageDTO;
import com.example.coffee_order_app.Model.Request.LogInRequest;
import com.example.coffee_order_app.Model.Request.PaymentRequest;
import com.example.coffee_order_app.Model.Request.addOrderItemRequest;
import com.example.coffee_order_app.Model.Request.updateOrderItemRequest;
import com.example.coffee_order_app.Model.Response.LogInResponse;
import com.example.coffee_order_app.Model.Response.PaymentResponse;
import com.example.coffee_order_app.Model.Response.RefreshResponse;
import com.example.coffee_order_app.Model.Response.ValidateResponse;
import com.example.coffee_order_app.Model.Response.deleteOrderItemResponse;
import com.example.coffee_order_app.Model.Response.updateOrderItemResponse;
import com.example.coffee_order_app.Model.TableOrderDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @GET("data/BeveragePrice.php")
    Call<List<BeveragePriceDTO>> queryBeverages(@Query("bev_name") String bev_name);

    @GET("data/BeveragePrice.php")
    Call<List<BeveragePriceDTO>> queryBeverages();

    @GET("data/OrderItemBeverage.php")
    Call<List<OrderItemBeverageDTO>> getOrderItems(@Query("floor_number") int floor_number, @Query("tbl_number") int tableNumber);

    @GET("data/OrderItemBeverage.php")
    Call<List<OrderItemBeverageDTO>> getOrderItems(@Query("floor_number") int floor_number, @Query("tbl_number") int tableNumber, @Query("ord_id") int ord_id);

    @POST("data/OrderItemBeverage.php")
    Call<List<OrderItemBeverageDTO>> addOrderItem(@Body addOrderItemRequest request);

    @POST("data/OrderItems.php")
    Call<updateOrderItemResponse> updateOrderItem(@Body updateOrderItemRequest request);

    @DELETE("data/OrderItems.php")
    Call<deleteOrderItemResponse> deleteOrderItem(
            @Query("ord_id") int orderId,
            @Query("bev_id") int bevId
    );

    @POST("data/Orders.php")
    Call<PaymentResponse> payOrder(@Body PaymentRequest request);

    @POST("auth/refresh.php")
    Call<RefreshResponse> refreshToken(@Header("Authorization") String authHeader);

    @POST("auth/validate_token.php")
    @Headers("Content-Type: application/json")
    Call<ValidateResponse> isTokenExpired(@Header("Authorization") String token);

}

