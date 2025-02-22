package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class Beverage {

    @SerializedName("bev_id")
    private int id;

    @SerializedName("bev_name")
    private String name;

    @SerializedName("bev_price")
    private float price;

    // Constructor
    public Beverage(String name, float price) {
        this.name = name;
        this.price = price;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
