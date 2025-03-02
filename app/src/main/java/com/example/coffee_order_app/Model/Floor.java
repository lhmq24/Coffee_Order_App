package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class Floor {
    @SerializedName("floor_number")
    private int floorNumber;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    //Getter

    public int getFloorNumber() {
        return floorNumber;
    }

    //Setter

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
