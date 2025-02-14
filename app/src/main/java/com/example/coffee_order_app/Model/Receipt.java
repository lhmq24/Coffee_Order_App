package com.example.coffee_order_app.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "receipts",
        foreignKeys = {
                @ForeignKey(entity = Order.class,
                        parentColumns = "ord_id",
                        childColumns = "rec_order_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Table.class,
                        parentColumns = "tbl_id",
                        childColumns = "rec_table_id",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index(value = "rec_order_id")}
)
public class Receipt {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rec_id")
    private int id;

    @ColumnInfo(name = "rec_order_id")
    private int orderId;

    @ColumnInfo(name = "rec_table_id")
    private int tableId;

    @ColumnInfo(name = "rec_total_price")
    private float totalPrice;

    @ColumnInfo(name = "rec_paid_time")
    private long paidTime;

    public Receipt(int orderId, int tableId, float totalPrice) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.totalPrice = totalPrice;
        this.paidTime = System.currentTimeMillis();
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

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    //Setter

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(long paidTime) {
        this.paidTime = paidTime;
    }
}

