package com.example.coffee_order_app.Presenter;


import com.example.coffee_order_app.Model.ApiClient;
import com.example.coffee_order_app.Model.ApiService;
import com.example.coffee_order_app.Model.TableOrderDTO;
import com.example.coffee_order_app.View.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private final ApiService apiService;
    private MainActivity activity;

    public MainPresenter(MainActivity view) {
        this.activity = view;
        this.apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void getAllTables() {
        ApiClient.init(ApiClient.getClient().create(ApiService.class), () -> {
            Call<List<TableOrderDTO>> call = ApiClient.getClient().create(ApiService.class).getAllTables();
            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<List<TableOrderDTO>> call, Response<List<TableOrderDTO>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        activity.showTables(response.body());
                    } else {
                        activity.showError("Failed to load items");
                    }
                }

                @Override
                public void onFailure(Call<List<TableOrderDTO>> call, Throwable t) {
                    activity.showError(t.getMessage());
                }
            });
        });
    }
}
