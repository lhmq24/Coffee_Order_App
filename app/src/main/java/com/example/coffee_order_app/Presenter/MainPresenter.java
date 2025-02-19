package com.example.coffee_order_app.Presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.coffee_order_app.Model.AppDatabase;
import com.example.coffee_order_app.Model.DAO.TableDAO;
import com.example.coffee_order_app.Model.Table;

import java.util.List;

public class MainPresenter {
    private TableDAO tableDAO;

    public MainPresenter(Context context) {
        // Lấy instance của database và DAO
        AppDatabase db = AppDatabase.getDatabase(context);
        tableDAO = db.tableDAO();
    }

    public LiveData<List<Table>> getAllTables() {
        return tableDAO.getAllTables();
    }
}
