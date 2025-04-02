package com.example.coffee_order_app.Presenter;

import android.util.Log;

import com.example.coffee_order_app.Interface.MainActivityInterface;
import com.example.coffee_order_app.Model.API.ApiClient;
import com.example.coffee_order_app.Model.API.ApiService;
import com.example.coffee_order_app.Model.TableOrderDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public void getAllTables(int floor_number) {
        ApiClient.init(apiService, () -> {
            Call<List<TableOrderDTO>> call = apiService.getAllTables(0); // Status 0: Unpaid
            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<List<TableOrderDTO>> call, Response<List<TableOrderDTO>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("Fetch", "Fetched " + response.body().size() + " tables from API");
                        view.updateTableList(response.body()); // Store the fetched list
                        filterTablesByFloor(floor_number, response.body()); // Filter by floor
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


    public void filterTablesByFloor(int floor, List<TableOrderDTO> tableList) {
        List<TableOrderDTO> filteredTables = new ArrayList<>();
        for (TableOrderDTO table : tableList) {
            if (table.getTable().getFloorNumber() == floor) {
                filteredTables.add(table);
            }
        }

        // Call sortTables to apply sorting after filtering
        int sortCriteria = view.getSortSpinnerSelection();  // Get the current sort spinner selection
        sortTables(sortCriteria, filteredTables);  // Reapply sorting after filtering
    }

    public void sortTables(int criteria, List<TableOrderDTO> tableList) {
        Log.d("Sort tables", "List size before sort: " + tableList.size());
        if (tableList.isEmpty()) return;

        List<TableOrderDTO> sortedList = new ArrayList<>(tableList);

        switch (criteria) {
            case 0:
                sortedList.sort(Comparator.comparingInt(t -> t.getTable().getTableNumber()));
                break;
            case 1:
                sortedList.sort(Collections.reverseOrder(Comparator.comparingInt(t -> t.getTable().getStatus())));
                break;
            case 2:
                sortedList.sort(Collections.reverseOrder(Comparator.comparingInt(t -> t.getTable().getTableCapacity())));
                break;
            case 3:
                // Prefer Availability on Capacity in reverse order
                sortedList.sort(Collections.reverseOrder(Comparator.comparingInt(t -> t.getTable().getStatus() * 100 + t.getTable().getTableCapacity())));
                break;
            default:
                break;
        }

        Log.d("Sort tables", "List size after sort: " + sortedList.size());
        view.showTables(sortedList);
    }
}
