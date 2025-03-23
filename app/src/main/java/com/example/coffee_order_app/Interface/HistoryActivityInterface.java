package com.example.coffee_order_app.Interface;

import com.example.coffee_order_app.Model.TableOrderDTO;

import java.util.List;

public interface HistoryActivityInterface {
    void showHistories(List<TableOrderDTO> HistoryList);
}
