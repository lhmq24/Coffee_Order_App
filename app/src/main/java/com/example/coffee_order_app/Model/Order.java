package com.example.coffee_order_app.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders",
        foreignKeys = @ForeignKey(entity = Table.class,
                parentColumns = "tbl_id",
                childColumns = "ord_table_id",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = "ord_table_id")}
)
public class Order {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ord_id")
    private int id;

    @ColumnInfo(name = "ord_table_id")
    private int tableId;

    @ColumnInfo(name = "ord_total_price")
    private float totalPrice;

    @ColumnInfo(name = "ord_status")
    private int status; //=0: open, =1:paid

    @ColumnInfo(name = "ord_created_at")
    //SQLite stores timestamp as long (milliseconds)
    //Should be converted
    private long createdAt;

    public Order(int tableId) {
        this.tableId = tableId;
        this.totalPrice = 0;
        this.status = 0;
        //Auto assign created time
        this.createdAt = System.currentTimeMillis();
    }

    //Getter
    public int getId() {
        return id;
    }

    public int getTableId() {
        return tableId;
    }

    public int getStatus() {
        return status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    //Setter
    public void setStatus(int newStatus) {
        this.status = newStatus;
    }

    public void setTotalPrice(float newTotalPrice) {
        this.totalPrice = newTotalPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}

