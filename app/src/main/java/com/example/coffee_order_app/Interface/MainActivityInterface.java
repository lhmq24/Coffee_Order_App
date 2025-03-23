package com.example.coffee_order_app.Interface;

import com.example.coffee_order_app.Model.TableOrderDTO;

import java.util.List;

public interface MainActivityInterface {
    void showTables(List<TableOrderDTO> tableList);

    void showError(String errorMessage);

    void updateTableList(List<TableOrderDTO> tableList);

}
