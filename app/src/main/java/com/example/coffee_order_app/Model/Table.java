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

    public int getStatus() {
        return status;
    }


    //Setter

    public void setId(int id) {
        this.id = id;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}

