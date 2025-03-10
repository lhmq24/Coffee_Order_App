package com.example.coffee_order_app.Presenter;

import android.util.Log;

import com.example.coffee_order_app.Interface.TableActivityInterface;
import com.example.coffee_order_app.Model.API.ApiClient;
import com.example.coffee_order_app.Model.API.ApiService;
import com.example.coffee_order_app.Model.Beverage;
import com.example.coffee_order_app.Model.Order;
import com.example.coffee_order_app.Model.OrderItemBeverageDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TablePresenter {
    private final TableActivityInterface view;
    private final ApiService apiService;

    public TablePresenter(TableActivityInterface view) {
        this.view = view;
        this.apiService = ApiClient.getClient().create(ApiService.class); // Initialize the database helper
    }

    public void queryBeverages(String query) {
        ApiClient.init(ApiClient.getClient().create(ApiService.class), () -> {
            Call<List<Beverage>> call = apiService.queryBeverages(query);
            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<List<Beverage>> call, Response<List<Beverage>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("Fetch Beverages", "Success");
                        view.showMatchedBeverages(response.body());
                    } else {
                        Log.e("Fetch Beverages", "Server error");
                    }
                }

                @Override
                public void onFailure(Call<List<Beverage>> call, Throwable t) {
                    Log.e("Fetch Beverages", "Network error");
                }
            });
        });
    }

    public void showOrderItems() {
        Call<List<OrderItemBeverageDTO>> call = apiService.getOrderItems();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<OrderItemBeverageDTO>> call, Response<List<OrderItemBeverageDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("Fetch OrderItem", "Fetch success");
                    view.addTableRows(response.body());
                } else {
                    Log.d("Fetch OrderItem", "Server error");
                    view.showError("Failed to fetch order items");
                }
            }

            @Override
            public void onFailure(Call<List<OrderItemBeverageDTO>> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }

    public void showTotalAmount(int orderId) {
        Call<Order> call = apiService.getOrder(orderId);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("Fetch OrderAmount", "Fetch success");
                    view.showTotalAmount(response.body());
                } else {
                    Log.d("Fetch OrderAmount", "Server error");
                    view.showError("Failed to fetch order items");
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                view.showError(t.getMessage());
                Log.d("Fetch OrderAmount", "Network error: " + t.getMessage());
            }

        });
    }
}
