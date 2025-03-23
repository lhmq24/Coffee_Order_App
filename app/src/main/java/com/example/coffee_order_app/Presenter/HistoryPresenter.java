package com.example.coffee_order_app.Presenter;

import android.util.Log;

import com.example.coffee_order_app.Interface.HistoryActivityInterface;
import com.example.coffee_order_app.Model.API.ApiClient;
import com.example.coffee_order_app.Model.API.ApiService;
import com.example.coffee_order_app.Model.TableOrderDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPresenter {
    private final ApiService apiService;
    private HistoryActivityInterface view;

    public HistoryPresenter(HistoryActivityInterface view) {
        this.view = view;
        this.apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void getPaidOrders() {
        //Status 1: Paid order
        apiService.getPaidOrders(1).enqueue(new Callback<List<TableOrderDTO>>() {
            @Override
            public void onResponse(Call<List<TableOrderDTO>> call, Response<List<TableOrderDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.showHistories(response.body());
                } else {
                    Log.e("HistoryPresenter", "Failed to fetch orders: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<TableOrderDTO>> call, Throwable t) {
                Log.e("HistoryPresenter", "API Call failed: " + t.getMessage());
            }
        });
    }

}
