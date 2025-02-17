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
import com.example.coffee_order_app.Model.Receipt;
import com.example.coffee_order_app.R;

import java.util.ArrayList;
import java.util.Objects;

public class OrderHistoryActivity extends AppCompatActivity {
    private ListView view;
    private OrderHistoryActivityAdapter adapter;
    private ArrayList<Receipt> receiptList;
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

        //Add view
        view = findViewById(R.id.order_recycler_view);
        receiptList = new ArrayList<>();

        // Add tables (For testing UI, must load from a database)
        receiptList.add(new Receipt(1, 1, 25000));
        receiptList.add(new Receipt(2, 2, 50000));
        receiptList.add(new Receipt(3, 3, 48000));
        receiptList.add(new Receipt(4, 4, 100000));

        //Add adapter to tables grid view
        adapter = new OrderHistoryActivityAdapter(this, receiptList);
        //Call getCount() for number of element and getView() for each GridView element
        view.setAdapter(adapter);

        // Click event to open ReceiptActivity
        view.setOnItemClickListener((parent, view, position, id) -> {
            Receipt receipt = receiptList.get(position);
            Intent intent = new Intent(OrderHistoryActivity.this, ReceiptDetailActivity.class);
            intent.putExtra("tableNumber", receipt.getTableId());
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}