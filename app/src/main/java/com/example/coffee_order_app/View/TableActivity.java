package com.example.coffee_order_app.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.coffee_order_app.Adapter.TableBeveragesAdapter;
import com.example.coffee_order_app.Interface.TableActivityInterface;
import com.example.coffee_order_app.Model.Beverage;
import com.example.coffee_order_app.Model.OrderItemBeverageDTO;
import com.example.coffee_order_app.Presenter.TablePresenter;
import com.example.coffee_order_app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private Map<String, TableRow> existingRows;

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
        matchedBeveragesList = new ArrayList<>();
        beverageAdapter = new TableBeveragesAdapter(this, matchedBeveragesList);
        matchedBeveragesView.setAdapter(beverageAdapter);

        matchedBeveragesView.setOnItemClickListener((parent, view, position, id) -> {
            Beverage selectedBeverage = matchedBeveragesList.get(position);
            int floorNumber = getIntent().getIntExtra("tableFloor", -1);
            int tableNumber = getIntent().getIntExtra("tableNumber", -1);
            int bev_id = selectedBeverage.getId();
            String bev_name = selectedBeverage.getName();
            //Add select beverage
            presenter.addOrderItem(floorNumber, tableNumber, bev_id, bev_name);
        });



        //Set presenter
        presenter = new TablePresenter(TableActivity.this);
        presenter.showOrderItems(getIntent().getIntExtra("tableFloor", -1), getIntent().getIntExtra("tableNumber", -1));

        // Add TextWatcher to search box
        //Show all beverages when not input
        presenter.queryBeverages();
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

        // Add listener to Paid button
        button.setOnClickListener(c -> {
            // Create request to pay the bill
            int floorNumber = getIntent().getIntExtra("tableFloor", -1);
            int tableNumber = getIntent().getIntExtra("tableNumber", -1);
            presenter.payOrder(floorNumber, tableNumber, success -> {
                if (!success) {
                    Log.d("Payment", "Pay order failed");
                    Toast.makeText(this, "Server error. Please try again!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Payment", "Pay order successfully");
                    Toast.makeText(this, "Pay successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        });
    }

    public void showMatchedBeverages(List<Beverage> beverages) {
        if (beverages == null || beverages.isEmpty()) {
            Log.d("TableActivity", "No beverages returned!");
            return;
        }

        Log.d("TableActivity", "Number of beverages: " + beverages.size());

        matchedBeveragesList.clear();
        matchedBeveragesList.addAll(beverages);
        beverageAdapter.notifyDataSetChanged();
    }


    public void showError(String error) {
        Log.d("Table Error", "Loading Error: " + error);
    }

    public void addTableRows(List<OrderItemBeverageDTO> items) {
        TableLayout itemTable = findViewById(R.id.orderDetailTable);
        if (existingRows == null) {
            existingRows = new HashMap<>();
        }

        // Store existing rows
        for (int i = 0; i < itemTable.getChildCount(); i++) {
            TableRow row = (TableRow) itemTable.getChildAt(i);
            TextView nameView = (TextView) row.getChildAt(0);
            Log.d("Order Item", "put bev name to Map: " + nameView.getText().toString());
            existingRows.put(nameView.getText().toString(), row);
        }
        float total_price = 0;

        for (OrderItemBeverageDTO item : items) {
            String bevName = item.getBeverage().getName();
            int quantity = item.getOrderItem().getItemQuantity();
            float price = item.getOrderItem().getItemPrice();

            Log.d("Order Item", "bevName: " + bevName);
            Log.d("Order Item", "Quantity: " + quantity);
            Log.d("Order Item", "price: " + price);

            total_price += price * quantity;

            if (existingRows.containsKey(bevName)) {
                updateRow(existingRows.get(bevName), quantity, price);
            } else {
                addNewRow(item);
            }
        }

        updateTotalPrice();
    }

    // Updates an existing row’s quantity and price
    private void updateRow(TableRow row, int newQuantity, float itemPrice) {
        TextView quantityView = (TextView) row.getChildAt(2);
        TextView priceView = (TextView) row.getChildAt(1);

        Log.d("Order Item", "item price: " + itemPrice);
        Log.d("Order Item", "quantity: " + newQuantity);
        Log.d("Order Item", "update price view: " + itemPrice * newQuantity);

        quantityView.setText(getString(R.string.Order_quantity, newQuantity));
        priceView.setText(getString(R.string.Order_item_price, itemPrice * newQuantity));
    }

    // Adds a new row for a beverage item
    private void addNewRow(OrderItemBeverageDTO item) {
        TableLayout itemTable = findViewById(R.id.orderDetailTable);
        TableRow tableRow = new TableRow(this);

        TextView bevName = createTextView(item.getBeverage().getName());
        float price = item.getOrderItem().getItemPrice();
        int quantity = item.getOrderItem().getItemQuantity();
        TextView bevPrice = createTextView(getString(R.string.Order_item_price, price * quantity));
        TextView bevQuantity = createTextView(getString(R.string.Order_quantity, quantity));
        TextView bevStatus = createTextView(item.getOrderItem().getItemStatus() == 1 ? "Served" : "Not served");
        ImageButton deleteButton = createDeleteButton(tableRow, item);

        // Add views to row
        tableRow.addView(bevName);
        tableRow.addView(bevPrice);
        tableRow.addView(bevQuantity);
        tableRow.addView(bevStatus);
        tableRow.addView(deleteButton);

        itemTable.addView(tableRow);

        updateTotalPrice();
    }

    // Creates a styled TextView
    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        int padding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()
        );

        textView.setText(text);
        textView.setPadding(padding, 5, padding, 0);
        textView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        return textView;
    }

    // Creates a delete button
    private ImageButton createDeleteButton(TableRow row, OrderItemBeverageDTO item) {
        ImageButton deleteButton = new ImageButton(this);
        int padding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()
        );

        deleteButton.setImageResource(android.R.drawable.ic_delete);
        deleteButton.setBackgroundResource(android.R.color.transparent);
        deleteButton.setPadding(padding, padding, padding, padding);
        deleteButton.setLayoutParams(new TableRow.LayoutParams(100, 100));
        deleteButton.setOnClickListener(v -> removeRow(row, item));

        return deleteButton;
    }

    // Removes or updates a row based on quantity
    private void removeRow(TableRow row, OrderItemBeverageDTO item) {
        TableLayout itemTable = findViewById(R.id.orderDetailTable);
        TextView quantityView = (TextView) row.getChildAt(2);
        TextView priceView = (TextView) row.getChildAt(1);

        int currentQuantity = Integer.parseInt(quantityView.getText().toString().replaceAll("[^0-9]", ""));
        float itemPrice = item.getOrderItem().getItemPrice();

        if (currentQuantity > 1) {
            int newQuantity = currentQuantity - 1;
            //Update database
            presenter.updateOrderItem(item.getOrderItem().getOrderId(), item.getBeverage().getId(), newQuantity, success -> {
                if (!success) {
                    Log.e("Update Item", "Failed to update order item.");
                    Toast.makeText(this, "Error updating beverage in server. Please try again later!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Update Item", "Order item updated successfully!");
                    quantityView.setText(getString(R.string.Order_quantity, newQuantity));
                    priceView.setText(getString(R.string.Order_item_price, itemPrice * newQuantity));
                    updateTotalPrice();
                }
            });
        } else {
            //Update database
            presenter.deleteOrderItem(item.getOrderItem().getOrderId(), item.getBeverage().getId(), success -> {
                if (!success) {
                    Log.e("Delete Item", "Failed to delete order item.");
                    Toast.makeText(this, "Error delete beverage in server. Please try again later!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Delete Item", "Order item deleted successfully!");
                    itemTable.removeView(row);
                    updateTotalPrice();
                }
            });
        }
    }

    // Updates the total price dynamically
    private void updateTotalPrice() {
        TableLayout itemTable = findViewById(R.id.orderDetailTable);
        float totalAmount = 0;

        for (int i = 0; i < itemTable.getChildCount(); i++) {
            TableRow row = (TableRow) itemTable.getChildAt(i);
            TextView priceView = (TextView) row.getChildAt(1);
            totalAmount += Float.parseFloat(priceView.getText().toString().replaceAll("[^0-9.]", ""));
        }

        updateTotalPrice(totalAmount);
    }

    // Overloaded method for updating total price
    private void updateTotalPrice(float totalAmount) {
        total.setText(getString(R.string.Order_total_price, totalAmount));
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
