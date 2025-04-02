package com.example.coffee_order_app.Presenter;

import android.util.Log;

import com.example.coffee_order_app.Interface.OrderItemCallback;
import com.example.coffee_order_app.Interface.OrderPayCallback;
import com.example.coffee_order_app.Interface.TableActivityInterface;
import com.example.coffee_order_app.Model.API.ApiClient;
import com.example.coffee_order_app.Model.API.ApiService;
import com.example.coffee_order_app.Model.BeveragePriceDTO;
import com.example.coffee_order_app.Model.OrderItemBeverageDTO;
import com.example.coffee_order_app.Model.Request.PaymentRequest;
import com.example.coffee_order_app.Model.Request.addOrderItemRequest;
import com.example.coffee_order_app.Model.Request.updateOrderItemRequest;
import com.example.coffee_order_app.Model.Response.PaymentResponse;
import com.example.coffee_order_app.Model.Response.deleteOrderItemResponse;
import com.example.coffee_order_app.Model.Response.updateOrderItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;


public class TablePresenter {
    private final TableActivityInterface view;
    private final ApiService apiService;

    public TablePresenter(TableActivityInterface view) {
        this.view = view;
        this.apiService = ApiClient.getClient().create(ApiService.class); // Initialize the database helper
    }

    public void queryBeverages() {
        Log.d("Fetch Beverages", "Fetch all beverages");
        Call<List<BeveragePriceDTO>> call = apiService.queryBeverages();
        call.enqueue(new Callback<List<BeveragePriceDTO>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<BeveragePriceDTO>> call, Response<List<BeveragePriceDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("Fetch Beverages", "Fetch all beverages successfully, number of bev: " + response.body().size());
                    view.showMatchedBeverages(response.body());
                } else {
                    Log.e("Fetch Beverages", "Server error! No response or response is null");
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<BeveragePriceDTO>> call, Throwable t) {
                Log.e("Fetch Beverages", "Network error: " + t.getMessage());
            }
        });
    }

    public void queryBeverages(String query) {
        Log.d("Fetch Beverages", "The bev name inputted is: " + query);
        Call<List<BeveragePriceDTO>> call = apiService.queryBeverages(query);
        call.enqueue(new Callback<List<BeveragePriceDTO>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<BeveragePriceDTO>> call, Response<List<BeveragePriceDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("Fetch Beverages", "Fetch beverages successfully, number of bev: " + response.body().size());
                    view.showMatchedBeverages(response.body());
                } else {
                    Log.e("Fetch Beverages", "Server error! No response or response is null");
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<BeveragePriceDTO>> call, Throwable t) {
                Log.e("Fetch Beverages", "Network error: " + t.getMessage());
            }
        });
    }


    public void showOrderItems(int floor_number, int tableNumber) {
        Call<List<OrderItemBeverageDTO>> call = apiService.getOrderItems(floor_number, tableNumber);
        call.enqueue(new Callback<>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<OrderItemBeverageDTO>> call, Response<List<OrderItemBeverageDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("Fetch OrderItem", "Fetch order item successfully, number of items: " + response.body().size());
                    view.addTableRows(response.body());
                } else {
                    Log.d("Fetch OrderItem", "Server error");
                    view.showError("No order to get!");
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<OrderItemBeverageDTO>> call, Throwable t) {
                view.showError(t.getMessage());
                Log.d("Fetch OrderItem", "Network error: " + t.getMessage());
            }
        });
    }

    public void addOrderItem(int floorNumber, int tableNumber, int bevId, String bevName) {
        addOrderItemRequest request = new addOrderItemRequest(floorNumber, tableNumber, bevId, bevName);
        Call<List<OrderItemBeverageDTO>> call = apiService.addOrderItem(request);
        call.enqueue(new Callback<>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<OrderItemBeverageDTO>> call, Response<List<OrderItemBeverageDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("Fetch new OrderItem", "Fetch order item successfully, number of items: " + response.body().size());
                    view.addTableRows(response.body());
                } else {
                    Log.d("Fetch new OrderItem", "Server error");
                    view.showError("No order to get!");
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<OrderItemBeverageDTO>> call, Throwable t) {
                view.showError(t.getMessage());
                Log.d("Fetch new OrderItem", "Network error: " + t.getMessage());
            }
        });
    }

    public void deleteOrderItem(int ord_id, int bev_id, OrderItemCallback callback) {
        Call<deleteOrderItemResponse> call = apiService.deleteOrderItem(ord_id, bev_id);
        call.enqueue(new Callback<deleteOrderItemResponse>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<deleteOrderItemResponse> call, Response<deleteOrderItemResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("Delete Item", "Response: " + response.body());
                    callback.onResult(response.body().isSuccess());
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<deleteOrderItemResponse> call, Throwable t) {
                Log.e("Delete Item", "Network error: " + t.getMessage());
                callback.onResult(false);
            }
        });
    }

    public void updateOrderItem(int ord_id, int bev_id, int new_quantity, OrderItemCallback callback) {
        updateOrderItemRequest request = new updateOrderItemRequest(ord_id, bev_id, new_quantity);
        Call<updateOrderItemResponse> call = apiService.updateOrderItem(request);
        call.enqueue(new Callback<updateOrderItemResponse>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<updateOrderItemResponse> call, Response<updateOrderItemResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResult(response.body().isSuccess());
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<updateOrderItemResponse> call, Throwable t) {
                Log.e("Update Item", "Network error: " + t.getMessage());
                callback.onResult(false);
            }
        });
    }

    public void payOrder(int floorNumber, int tableNumber, OrderPayCallback callback) {
        PaymentRequest request = new PaymentRequest(floorNumber, tableNumber);
        Call<PaymentResponse> call = apiService.payOrder(request);
        call.enqueue(new Callback<PaymentResponse>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("Pay order", "presenter call successfully");
                    callback.onResult(response.body().isSuccess());
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                Log.e("Pay order", "Network error: " + t.getMessage());
                callback.onResult(false);
            }
        });
    }

}
