package com.example.coffee_order_app.Presenter;

import com.example.coffee_order_app.Model.API.ApiClient;
import com.example.coffee_order_app.Model.API.ApiService;
import com.example.coffee_order_app.View.OrderHistoryActivity;
public class HistoryPresenter {
    private final ApiService apiService;
    private OrderHistoryActivity activity;

    public HistoryPresenter(OrderHistoryActivity view) {
        this.activity = view;
        this.apiService = ApiClient.getClient().create(ApiService.class);
    }

}
