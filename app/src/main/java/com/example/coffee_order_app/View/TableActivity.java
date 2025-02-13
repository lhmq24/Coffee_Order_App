package com.example.coffee_order_app.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.coffee_order_app.R;

import java.util.Objects;

public class TableActivity extends AppCompatActivity {
    Toolbar toolbar;
    int table_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Set toolbar title
        //Add textview -> dynamic text
        try {
            table_number = getIntent().getIntExtra("tableNumber", -1);
            TextView title = findViewById(R.id.toolbar_title);
            String s_title = getString(R.string.table_number,table_number);
            title.setText(s_title);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Set left icon
        toolbar.setNavigationIcon(R.drawable.go_back);
        // Handle navigation click
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.table_toolbar_menu, menu);
        // Hide default toolbar title (handle NullPointer)
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId(); // Get selected menu item ID

        if (id == R.id.toolbar_history) {
            //Move to order history view
            Intent intent = new Intent(TableActivity.this, OrderHistoryActivity.class);
            startActivity(intent);
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
            return true;
        } else if (id == R.id.toolbar_go_back) {
            //Return to main activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
