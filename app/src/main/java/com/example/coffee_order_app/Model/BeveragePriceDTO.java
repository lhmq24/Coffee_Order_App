package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class BeveragePriceDTO {
    @SerializedName("beverages")
    private Beverage bev;
    @SerializedName("beverage_price_history")
    private Beverage_Price_History price;

    public Beverage getBev() {
        return bev;
    }

    public Beverage_Price_History getPrice() {
        return price;
    }
}
