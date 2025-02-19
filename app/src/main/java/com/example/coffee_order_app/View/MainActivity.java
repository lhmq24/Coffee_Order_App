package com.example.coffee_order_app.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.coffee_order_app.Adapter.MainActivityAdapter;
import com.example.coffee_order_app.Model.Table;
import com.example.coffee_order_app.Presenter.MainPresenter;
import com.example.coffee_order_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private GridView tables;
    private List<Table> tableList;
    private MainActivityAdapter adapter;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Add toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.getOverflowIcon().setTint(ContextCompat.getColor(this, R.color.white));


        //Initialize views, presenter
        tables = findViewById(R.id.table_grid_view);
        presenter = new MainPresenter(this);
        tableList = new ArrayList<>();

        //Add adapter to tables grid view
        adapter = new MainActivityAdapter(this, tableList);
        //Call getCount() for number of element and getView() for each GridView element
        tables.setAdapter(adapter);

        //Add tables to views
        presenter.getAllTables().observe(this, tables -> {
            if (tables != null) {
                tableList.clear();
                tableList.addAll(tables); // Update list
                adapter.notifyDataSetChanged(); // Refresh UI
            }
        });



        // Click event to open TableActivity
        tables.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Table selectedTable = tableList.get(position);
            Intent intent = new Intent(MainActivity.this, TableActivity.class);
            intent.putExtra("tableNumber", selectedTable.getTableNumber());
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_toolbar_menu, menu);
        // Hide default toolbar title (handle NullPointer)
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId(); // Get selected menu item ID

        if (id == R.id.toolbar_history) {
            Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}