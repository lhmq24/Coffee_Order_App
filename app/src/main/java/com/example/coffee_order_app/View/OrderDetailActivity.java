package com.example.coffee_order_app.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coffee_order_app.Adapter.OrderDetailAdapter;
import com.example.coffee_order_app.Interface.OrderDetailActivityInterface;
import com.example.coffee_order_app.Model.OrderItemBeverageDTO;
import com.example.coffee_order_app.Model.TableOrderDTO;
import com.example.coffee_order_app.Presenter.OrderDetailPresenter;
import com.example.coffee_order_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDetailActivity extends AppCompatActivity implements OrderDetailActivityInterface {

    private Toolbar toolbar;
    private TextView title;
    private List<OrderItemBeverageDTO> itemList;
    private TableOrderDTO dto;
    private ListView listView;
    private OrderDetailAdapter adapter;

    private OrderDetailPresenter presenter;
    private TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_detail);

        itemList = new ArrayList<>();

        //Add toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setBackgroundResource(R.color.toolbar_color);


        try {
            Intent intent = getIntent();
            dto = (TableOrderDTO) intent.getSerializableExtra("TableOrderDTO");
            if (dto == null) {
                Log.e("Order detail", "dto is null, get Serializable failed");
            }
            int table_number = dto.getTable().getTableNumber();
            title = findViewById(R.id.toolbar_title);
            String s_title = getString(R.string.receipt_table_number, table_number);
            title.setText(s_title);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Set left icon
        toolbar.setNavigationIcon(R.drawable.go_back);
        int color = getColor(R.color.white);
        Objects.requireNonNull(toolbar.getNavigationIcon()).setTint(color);
        // Handle navigation click
        toolbar.setNavigationOnClickListener(v -> finish());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize
        listView = findViewById(R.id.order_item_list_view);
        adapter = new OrderDetailAdapter(this, itemList);
        presenter = new OrderDetailPresenter(this);

        presenter.fetchOrderDetail(dto);

        listView.setAdapter(adapter);

        totalPrice = findViewById(R.id.totalPrice);
        totalPrice.setText(getString(R.string.table_amount, dto.getOrder().getTotalPrice()));

    }

    public void showOrderItems(List<OrderItemBeverageDTO> list) {
        if (list.isEmpty()) {
            Log.e("Order detail activity", "List is empty, can't show Order Items");
        }
        itemList.clear();
        itemList.addAll(list);
        adapter.notifyDataSetChanged();
    }
}