package com.example.coffee_order_app;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private GridView tables;
    private List<Table> tableList;
    private TableAdapter tableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Add toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Add tables
        tables = findViewById(R.id.table_grid_view);
        tableList = new ArrayList<>();

        // Add tables (For testing UI, must load from a database)
        tableList.add(new Table(1, R.drawable.table_img, "Available", 0.00));
        tableList.add(new Table(2, R.drawable.table_img, "Occupied", 15.50));
        tableList.add(new Table(3, R.drawable.table_img, "Available", 0.00));
        tableList.add(new Table(4, R.drawable.table_img, "Occupied", 22.30));

        //Add adapter to tables grid view
        tableAdapter = new TableAdapter(this, tableList);
        tables.setAdapter(tableAdapter);

        // Click event to open TableActivity
        tables.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Table selectedTable = tableList.get(position);
            Intent intent = new Intent(MainActivity.this, TableActivity.class);
            intent.putExtra("tableNumber", selectedTable.getNumber());
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
            //switch to Shop's order history
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}