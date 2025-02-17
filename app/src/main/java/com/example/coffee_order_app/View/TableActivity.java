package com.example.coffee_order_app.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.coffee_order_app.Adapter.TableActivityAdapter;
import com.example.coffee_order_app.Model.OrderItem;
import com.example.coffee_order_app.R;

import java.util.ArrayList;
import java.util.Objects;

public class TableActivity extends AppCompatActivity {
    Toolbar toolbar;
    private EditText search_box;
    private ListView matchedBeveragesList;
    private TableActivityAdapter adapter;
    private ArrayList<OrderItem> OrderItemList; // Assuming Receipt is the model for orders
    //    private TablePresenter presenter;
    private TextView total;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Set toolbar title
        //Add textview -> dynamic text
        try {
            int table_number = getIntent().getIntExtra("tableNumber", -1);
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

        //Initialize components
        search_box = findViewById(R.id.searchBeverage);
        matchedBeveragesList = findViewById(R.id.matchedBeveragesList);
        OrderItemList = new ArrayList<>();
        total = findViewById(R.id.totalPrice);
        button = findViewById(R.id.markAsPaidButton);

        //Set adapter
        adapter = new TableActivityAdapter(this, OrderItemList);
        matchedBeveragesList.setAdapter(adapter);

        //Set presenter
//        presenter = new TablePresenter(this);

        // Add TextWatcher to search box
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This is where we capture the input text and pass it to the presenter
                String item_name = charSequence.toString().trim();
//                presenter.queryBeverages(item_name); // Call presenter to query based on search text
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
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
