package com.example.coffee_order_app.Model.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coffee_order_app.Model.Beverage;

import java.util.List;

@Dao
public interface BeverageDAO {
    @Insert
    void addBeverage(Beverage beverage);

    @Delete
    void removeBeverage(Beverage beverage);

    @Query("SELECT * FROM beverages")
    LiveData<List<Beverage>> getAllBeverages();

    @Query("SELECT * FROM beverages WHERE bev_name LIKE :b_name || '%' LIMIT 1")
    Beverage getBeverageByName(String b_name);

    @Query("SELECT * FROM beverages WHERE bev_id = :b_id LIMIT 1")
    Beverage getBeverageById(int b_id);
}
