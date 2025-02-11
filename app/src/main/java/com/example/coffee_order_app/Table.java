package com.example.coffee_order_app;

public class Table {
    private final int number;
    private int image;
    private String status;
    private double totalAmount;

    public Table(int number, int image, String status, double totalAmount) {
        this.number = number;
        this.image = image;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public int getNumber() {
        return number;
    }

    public int getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
