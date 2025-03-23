package com.example.coffee_order_app.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.coffee_order_app.Adapter.OrderHistoryActivityAdapter;
import com.example.coffee_order_app.Interface.HistoryActivityInterface;
import com.example.coffee_order_app.Model.TableOrderDTO;
import com.example.coffee_order_app.Presenter.HistoryPresenter;
import com.example.coffee_order_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderHistoryActivity extends AppCompatActivity implements HistoryActivityInterface {
    private ListView listView;
    private OrderHistoryActivityAdapter adapter;
    private List<TableOrderDTO> HistoryList;
    private HistoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        // Toolbar setup
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.go_back);
        toolbar.setNavigationOnClickListener(v -> finish());
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        TextView title = findViewById(R.id.toolbar_title);
        title.setText(R.string.order_history);
        toolbar.setBackgroundResource(R.color.toolbar_color);

        // Initialize ListView
        listView = findViewById(R.id.order_list_view);
        HistoryList = new ArrayList<>();
        adapter = new OrderHistoryActivityAdapter(this, HistoryList);
        listView.setAdapter(adapter);

        // Initialize presenter
        presenter = new HistoryPresenter(this);
        presenter.getPaidOrders();

        // Click event for order details
        listView.setOnItemClickListener((parent, view, position, id) -> {
            TableOrderDTO order = HistoryList.get(position);
            Intent intent = new Intent(OrderHistoryActivity.this, OrderDetailActivity.class);
            intent.putExtra("tableNumber", order.getTable().getTableNumber());
            startActivity(intent);
        });
    }

    @Override
    public void showHistories(List<TableOrderDTO> orders) {
        HistoryList.clear();
        HistoryList.addAll(orders);
        adapter.notifyDataSetChanged();
    }
}
