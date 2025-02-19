package com.example.coffee_order_app.Model.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coffee_order_app.Model.Order;

import java.util.List;

@Dao
public interface OrderDAO {
    @Insert
    void addOrder(Order order);

    @Delete
    void removeOrder(Order order);

    @Query("SELECT * FROM orders")
    LiveData<List<Order>> getAllOrders();

    @Query("SELECT * FROM orders WHERE ord_table_id = :t_id LIMIT 1")
    Order getOrderByTableId(String t_id);

    @Query("SELECT * FROM orders WHERE ord_id = :o_id LIMIT 1")
    Order getOrderById(int o_id);
}
