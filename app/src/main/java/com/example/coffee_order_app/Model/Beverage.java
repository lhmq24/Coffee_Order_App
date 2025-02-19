package com.example.coffee_order_app.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "beverages")
public class Beverage {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bev_id")
    private int id;

    @ColumnInfo(name = "bev_name")
    private String name;

    @ColumnInfo(name = "bev_price")
    private float price;

    public Beverage(String name, float price) {
        this.name = name;
        this.price = price;
    }

    //Getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
    //Setter

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

