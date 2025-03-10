package com.example.coffee_order_app.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.coffee_order_app.Adapter.TableBeveragesAdapter;
import com.example.coffee_order_app.Interface.TableActivityInterface;
import com.example.coffee_order_app.Model.Beverage;
import com.example.coffee_order_app.Model.Order;
import com.example.coffee_order_app.Model.OrderItemBeverageDTO;
import com.example.coffee_order_app.Presenter.TablePresenter;
import com.example.coffee_order_app.R;

import java.util.List;
import java.util.Objects;

public class TableActivity extends AppCompatActivity implements TableActivityInterface {
    Toolbar toolbar;
    private EditText search_box;
    private ListView matchedBeveragesView;
    private List<Beverage> matchedBeveragesList;
    private TableLayout ItemTable;
    private TextView total;
    private Button button;
    private TableBeveragesAdapter beverageAdapter;
    private TablePresenter presenter;
    private int OrderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            int table_number = getIntent().getIntExtra("tableNumber", -1);
            TextView title = findViewById(R.id.toolbar_title);
            String s_title = getString(R.string.table_number,table_number);
            title.setText(s_title);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        toolbar.setNavigationIcon(R.drawable.go_back);
        toolbar.setNavigationOnClickListener(v -> finish());
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setBackgroundResource(R.color.toolbar_color);



        //Initialize components
        search_box = findViewById(R.id.searchBeverage);
        matchedBeveragesView = findViewById(R.id.matchedBeveragesView);
        ItemTable = findViewById(R.id.orderDetailTable);
        total = findViewById(R.id.totalPrice);
        button = findViewById(R.id.markAsPaidButton);

        //Set beverageAdapter
        beverageAdapter = new TableBeveragesAdapter(this, matchedBeveragesList);
        matchedBeveragesView.setAdapter(beverageAdapter);



        //Set presenter
        presenter = new TablePresenter(TableActivity.this);
        presenter.showOrderItems();

        // Add TextWatcher to search box
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Capture the input text and pass it to the presenter
                String item_name = charSequence.toString().trim();
                //Accept only alphabet characters
                if (validCharacters(item_name)) {
                    presenter.queryBeverages(item_name); // Call presenter to query based on search text
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void showMatchedBeverages(List<Beverage> beverages) {
        matchedBeveragesList.clear();
        matchedBeveragesList.addAll(beverages);
        beverageAdapter.notifyDataSetChanged();
    }

    public void showError(String error) {
        Log.d("Table Error", "Loading Error: " + error);
    }

    public void addTableRows(List<OrderItemBeverageDTO> items) {
        TextView bev_name = findViewById(R.id.bev_name);
        TextView bev_price = findViewById(R.id.bev_price);
        TextView bev_quantity = findViewById(R.id.bev_quantity);
        TextView bev_status = findViewById(R.id.bev_status);
        TextView total_amount = findViewById(R.id.totalPrice);

        for (OrderItemBeverageDTO item : items) {
            // Create a new TableRow
            TableRow tableRow = new TableRow(this);
            bev_name.setText(item.getBeverage().getName());
            bev_price.setText(getString(R.string.Order_item_price, item.getOrderItem().getItemPrice()));
            bev_quantity.setText(getString(R.string.Order_quantity, item.getOrderItem().getItemPrice()));
            String status = (item.getOrderItem().getItemStatus() == 1 ? "Served" : "Not served");
            bev_status.setText(status);

            OrderID = item.getOrderItem().getOrderId();

            tableRow.addView(bev_name);
            tableRow.addView(bev_price);
            tableRow.addView(bev_quantity);
            tableRow.addView(bev_status);
        }

        presenter.showTotalAmount(OrderID);
    }

    public void showTotalAmount(Order order) {
        TextView total_amount = findViewById(R.id.totalPrice);
        total_amount.setText(getString(R.string.Order_total_price, order.getTotalPrice()));
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

    private boolean validCharacters(String s) {
        return s.matches("^[A-Za-z]+$");
    }
}
