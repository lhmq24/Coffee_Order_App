package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class Beverage {

    @SerializedName("bev_id")
    private int id;

    @SerializedName("bev_name")
    private String name;

    @SerializedName("bev_img")
    private String img;


    // Constructor

    public Beverage(int id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }


    // Getter methods


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
