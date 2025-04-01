package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class OrderItem {

    @SerializedName("item_id")
    private int itemId;

    @SerializedName("ord_id")
    private int orderId;

    @SerializedName("bev_id")
    private int beverageId;

    @SerializedName("item_quantity")
    private int itemQuantity;

    @SerializedName("item_current_price")
    private float itemPrice;

    @SerializedName("item_note")
    private String itemNote;

    @SerializedName("item_status")
    private int itemStatus;  // 0 = Not served (default), 1 = Served


    // Constructor

    public OrderItem(int itemId, int orderId, int beverageId, int itemQuantity, float itemPrice, String itemNote, int itemStatus) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.beverageId = beverageId;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.itemNote = itemNote;
        this.itemStatus = itemStatus;
    }


    // Getter methods

    public int getItemId() {
        return itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getBeverageId() {
        return beverageId;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public String getItemNote() {
        return itemNote;
    }

    public void setItemNote(String itemNote) {
        this.itemNote = itemNote;
    }

    // Setter methods

    public int getItemStatus() {
        return itemStatus;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setBeverageId(int beverageId) {
        this.beverageId = beverageId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemStatus(int itemStatus) {
        this.itemStatus = itemStatus;
    }

}
