package com.example.coffee_order_app.Interface;

import com.example.coffee_order_app.Model.Beverage;
import com.example.coffee_order_app.Model.Order;
import com.example.coffee_order_app.Model.OrderItemBeverageDTO;

import java.util.List;

public interface TableActivityInterface {
    void showMatchedBeverages(List<Beverage> beverages);

    void addTableRows(List<OrderItemBeverageDTO> items);

    public void showTotalAmount(Order order);

    public void showError(String error);
}
