package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Beverage_Price_History {
    @SerializedName("price_id")
    private int priceId;

    @SerializedName("bev_id")
    private int bevId;

    @SerializedName("price_daystart")
    private Timestamp dayStart;

    @SerializedName("price_amount")
    private float priceAmount;

    public Beverage_Price_History(int priceId, int bevId, Timestamp dayStart, float priceAmount) {
        this.priceId = priceId;
        this.bevId = bevId;
        this.dayStart = dayStart;
        this.priceAmount = priceAmount;
    }

    //Getter

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public int getBevId() {
        return bevId;
    }

    public void setBevId(int bevId) {
        this.bevId = bevId;
    }

    //Setter

    public Timestamp getDayStart() {
        return dayStart;
    }

    public void setDayStart(Timestamp dayStart) {
        this.dayStart = dayStart;
    }

    public float getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(float priceAmount) {
        this.priceAmount = priceAmount;
    }
}
