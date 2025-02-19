package com.example.coffee_order_app.Model.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coffee_order_app.Model.OrderItem;

import java.util.List;

@Dao
public interface OrderItemDAO {
    @Insert
    void addOrderItem(OrderItem item);

    @Delete
    void removeOrderItem(OrderItem item);

    @Query("SELECT * FROM order_items")
    LiveData<List<OrderItem>> getAllOrderItems();

    @Query("SELECT * FROM order_items WHERE item_order_id = :i_id LIMIT 1")
    OrderItem getItemById(int i_id);
}
