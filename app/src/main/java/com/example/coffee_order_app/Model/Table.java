package com.example.coffee_order_app.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "tables")
public class Table {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tbl_id")
    private int id;

    @ColumnInfo(name = "tbl_number")
    private int tableNumber;

    @ColumnInfo(name = "tbl_status")
    private int status = 0; // 0 = available, 1 = occupied

    @ColumnInfo(name = "tbl_total_amount")
    private float totalAmount = 0;

    public Table(int id, int tableNumber) {
        this.id = id;
        this.tableNumber = tableNumber;
    }

    //Getter
    public int getId() {
        return id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    //Setter
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
}

