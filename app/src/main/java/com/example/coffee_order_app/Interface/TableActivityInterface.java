package com.example.coffee_order_app.Interface;

import com.example.coffee_order_app.Model.BeveragePriceDTO;
import com.example.coffee_order_app.Model.OrderItemBeverageDTO;

import java.util.List;

public interface TableActivityInterface {
    void showMatchedBeverages(List<BeveragePriceDTO> beverages);

    void addTableRows(List<OrderItemBeverageDTO> items);

    void showError(String error);
}
