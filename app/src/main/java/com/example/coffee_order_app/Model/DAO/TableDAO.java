package com.example.coffee_order_app.Model.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coffee_order_app.Model.Table;

import java.util.List;

@Dao
public interface TableDAO {

    @Insert
    void addTable(Table table);

    @Query("Select * from tables")
    LiveData<List<Table>> getAllTables();

    @Query("Select * from tables where tbl_number = :tableNumber LIMIT 1")
    Table getTable(int tableNumber);


    @Query("Update tables set tbl_status = :status where tbl_number = :tableNumber")
    int setTableStatus(int tableNumber, int status);

    @Query("Select * from tables where tbl_status = 0")
    LiveData<List<Table>> getAvailableTables();

//    @Query("Select ord_total_price from tables t " +
//            "join orders o on t.tbl_id = o.ord_table_id where o.ord_created_at ")
//    FLoat getTotalAmount();

    @Delete
    void deleteTable(Table table);

}
