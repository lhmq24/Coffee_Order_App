package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class OrderItemBeverageDTO {
    @SerializedName("beverages")
    private Beverage beverage;
    @SerializedName("order_items")
    private OrderItem item;

    public Beverage getBeverage() {
        return beverage;
    }

    public OrderItem getOrderItem() {
        return item;
    }
}
