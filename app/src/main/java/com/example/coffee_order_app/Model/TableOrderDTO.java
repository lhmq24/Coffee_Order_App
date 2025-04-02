package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TableOrderDTO implements Serializable {
    @SerializedName("table")
    private Table table;
    @SerializedName("order")
    private Order order;

    public Table getTable() {
        return table;
    }

    public Order getOrder() {
        return order;
    }
}
