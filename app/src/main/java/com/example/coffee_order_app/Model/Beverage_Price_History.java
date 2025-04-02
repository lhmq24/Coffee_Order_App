package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;


public class Beverage_Price_History {
    @SerializedName("price_id")
    private int priceId;

    @SerializedName("bev_id")
    private int bevId;

    @SerializedName("price_daystart")
    private String dayStart;

    @SerializedName("price_amount")
    private float priceAmount;

    public Beverage_Price_History(int priceId, int bevId, String dayStart, float priceAmount) {
        this.priceId = priceId;
        this.bevId = bevId;
        this.dayStart = dayStart;
        this.priceAmount = priceAmount;
    }

    //Getter

    public int getPriceId() {
        return priceId;
    }

    public int getBevId() {
        return bevId;
    }

    public String getDayStart() {
        return dayStart;
    }

    public float getPriceAmount() {
        return priceAmount;
    }


    //Setter

    public void setDayStart(String dayStart) {
        this.dayStart = dayStart;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public void setBevId(int bevId) {
        this.bevId = bevId;
    }

    public void setPriceAmount(float priceAmount) {
        this.priceAmount = priceAmount;
    }
}
