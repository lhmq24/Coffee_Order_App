package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class Beverage {

    @SerializedName("bev_id")
    private int id;

    @SerializedName("bev_name")
    private String name;


    // Constructor
    public Beverage(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter methods


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
