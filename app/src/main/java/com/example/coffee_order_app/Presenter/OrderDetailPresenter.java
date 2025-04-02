package com.example.coffee_order_app.Presenter;

import android.util.Log;

import com.example.coffee_order_app.Interface.OrderDetailActivityInterface;
import com.example.coffee_order_app.Model.API.ApiClient;
import com.example.coffee_order_app.Model.API.ApiService;
import com.example.coffee_order_app.Model.Order;
import com.example.coffee_order_app.Model.OrderItemBeverageDTO;
import com.example.coffee_order_app.Model.Table;
import com.example.coffee_order_app.Model.TableOrderDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailPresenter {
    private OrderDetailActivityInterface view;
    private ApiService apiService;

    public OrderDetailPresenter(OrderDetailActivityInterface view) {
        this.view = view;
        this.apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void fetchOrderDetail(TableOrderDTO dto) {
        Table table = dto.getTable();
        Order order = dto.getOrder();
        Call<List<OrderItemBeverageDTO>> call = apiService.getOrderItems(table.getFloorNumber(),
                table.getTableNumber(), order.getOrderId());
        call.enqueue(new Callback<List<OrderItemBeverageDTO>>() {
            @Override
            public void onResponse(Call<List<OrderItemBeverageDTO>> call, Response<List<OrderItemBeverageDTO>> response) {
                Log.d("Order detail presenter", "Fetch successfully");
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("Order detail presenter", "Fetch successfully and body not null: " + response.body().size());
                    view.showOrderItems(response.body());
                } else {
                    Log.d("Order detail presenter", "Server error");
                }
            }

            @Override
            public void onFailure(Call<List<OrderItemBeverageDTO>> call, Throwable t) {
                Log.d("Order detail presenter", "Network error: " + t.getMessage());
            }
        });
    }

}
