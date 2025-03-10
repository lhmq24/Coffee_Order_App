package com.example.coffee_order_app.Presenter;


import com.example.coffee_order_app.Interface.MainActivityInterface;
import com.example.coffee_order_app.Model.API.ApiClient;
import com.example.coffee_order_app.Model.API.ApiService;
import com.example.coffee_order_app.Model.TableOrderDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private final ApiService apiService;
    private final MainActivityInterface view;

    public MainPresenter(MainActivityInterface view) {
        this.view = view;
        this.apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void getAllTables() {
        ApiClient.init(apiService, () -> {
            Call<List<TableOrderDTO>> call = apiService.getAllTables();
            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<List<TableOrderDTO>> call, Response<List<TableOrderDTO>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        view.showTables(response.body());
                    } else {
                        view.showError("Failed to load items");
                    }
                }

                @Override
                public void onFailure(Call<List<TableOrderDTO>> call, Throwable t) {
                    view.showError(t.getMessage());
                }
            });
        });
    }
}
