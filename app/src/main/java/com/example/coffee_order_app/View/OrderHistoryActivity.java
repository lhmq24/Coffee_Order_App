package com.example.coffee_order_app.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coffee_order_app.Adapter.OrderHistoryActivityAdapter;
import com.example.coffee_order_app.Model.Order;
import com.example.coffee_order_app.R;

import java.util.List;
import java.util.Objects;

public class OrderHistoryActivity extends AppCompatActivity {
    private ListView view;
    private OrderHistoryActivityAdapter adapter;
    private List<Order> OrderList;
//    private DatabaseHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_history);

        //Add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.go_back);
        toolbar.setNavigationOnClickListener(v -> finish());
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        TextView title = findViewById(R.id.toolbar_title);
        title.setText(R.string.order_history);
        toolbar.setBackgroundResource(R.color.toolbar_color);

        //Add view
        view = findViewById(R.id.order_recycler_view);

        // Add tables (For testing UI, must load from a database)


        //Add adapter to tables grid view
        adapter = new OrderHistoryActivityAdapter(this, OrderList);
        //Call getCount() for number of element and getView() for each GridView element
        view.setAdapter(adapter);

        // Click event to open OrderDetailActivity
        view.setOnItemClickListener((parent, view, position, id) -> {
            Order order = OrderList.get(position);
            Intent intent = new Intent(OrderHistoryActivity.this, OrderDetailActivity.class);
            intent.putExtra("tableNumber", order.getTableId());
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void showTables(List<Order> OrderList) {

    }
}