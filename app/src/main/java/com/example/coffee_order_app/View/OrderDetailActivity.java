package com.example.coffee_order_app.View;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coffee_order_app.R;

public class OrderDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView title;

//    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_detail);

        //Add toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        //Set toolbar title
        //Add textview -> dynamic text
        try {
            int table_number = getIntent().getIntExtra("tableNumber", -1);
            title = findViewById(R.id.toolbar_title);
            String s_title = getString(R.string.receipt_table_number, table_number);
            title.setText(s_title);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Set left icon
        toolbar.setNavigationIcon(R.drawable.go_back);
        // Handle navigation click
        toolbar.setNavigationOnClickListener(v -> finish());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}