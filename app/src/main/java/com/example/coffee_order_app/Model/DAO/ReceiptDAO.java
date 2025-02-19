package com.example.coffee_order_app.Model.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coffee_order_app.Model.Receipt;

import java.util.List;

@Dao
public interface ReceiptDAO {
    @Insert
    void addReceipt(Receipt receipt);

    @Delete
    void removeReceipt(Receipt receipt);

    @Query("SELECT * FROM receipts")
    LiveData<List<Receipt>> getAllReceipts();

    @Query("SELECT * FROM receipts WHERE rec_table_id = :t_id LIMIT 1")
    Receipt getReceiptByTable(int t_id);
}
