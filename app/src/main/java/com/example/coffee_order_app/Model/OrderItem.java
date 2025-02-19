package com.example.coffee_order_app.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "order_items",
        foreignKeys = {
                @ForeignKey(entity = Order.class,
                        parentColumns = "ord_id",
                        childColumns = "item_order_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Beverage.class,
                        parentColumns = "bev_id",
                        childColumns = "item_beverage_id",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index(value = "item_order_id"), @Index(value = "item_beverage_id")}
)
public class OrderItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    private int id;

    @ColumnInfo(name = "item_order_id")
    private int orderId;

    @ColumnInfo(name = "item_beverage_id")
    private int beverageId;

    @ColumnInfo(name = "item_quantity")
    private int quantity;

    @ColumnInfo(name = "item_price")
    private float price;

    @ColumnInfo(name = "item_status")
    private int status;  //=0: Not served yet (Default when ini instance),  =1:Served

    @ColumnInfo(name = "item_order_time")
    private long orderTime;

    public OrderItem(int orderId, int beverageId, int quantity, float price) {
        this.orderId = orderId;
        this.beverageId = beverageId;
        this.quantity = quantity;
        this.price = price;
        this.status = 0;
        this.orderTime = System.currentTimeMillis();
    }

    //Getter
    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBeverageId() {
        return beverageId;
    }

    public void setBeverageId(int beverageId) {
        this.beverageId = beverageId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //Setter

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }
}

