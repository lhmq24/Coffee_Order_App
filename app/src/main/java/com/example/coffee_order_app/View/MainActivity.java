package com.example.coffee_order_app.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coffee_order_app.Adapter.MainActivityAdapter;
import com.example.coffee_order_app.Interface.MainActivityInterface;
import com.example.coffee_order_app.Model.TableOrderDTO;
import com.example.coffee_order_app.Presenter.MainPresenter;
import com.example.coffee_order_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    private Toolbar toolbar;
    private GridView tables;
    private List<TableOrderDTO> tableList;
    private MainActivityAdapter adapter;
    private MainPresenter presenter;
    private static final int REFRESH_INTERVAL = 5000; // 5 seconds
    private Handler handler = new Handler();
    private Runnable refreshRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Add toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        Objects.requireNonNull(toolbar.getOverflowIcon()).setTint(ContextCompat.getColor(this, R.color.white));

        //Initialize view, presenter
        tables = findViewById(R.id.table_grid_view);
        presenter = new MainPresenter(this);
        tableList = new ArrayList<>();
        adapter = new MainActivityAdapter(this, tableList);
        tables.setAdapter(adapter);

        // Fetch tables using the presenter
        presenter.getAllTables();
        // Set up periodic refresh of tables
        startPeriodicRefresh();


        // Click event to open TableActivity
        tables.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            TableOrderDTO selectedTable = tableList.get(position);
            Intent intent = new Intent(MainActivity.this, TableActivity.class);
            intent.putExtra("tableNumber", selectedTable.getTable().getTableNumber());
            startActivity(intent);
        });

    }

    // Start periodic refresh using Handler and Runnable
    private void startPeriodicRefresh() {
        refreshRunnable = new Runnable() {
            @Override
            public void run() {
                presenter.getAllTables(); // Fetch tables periodically
                handler.postDelayed(this, REFRESH_INTERVAL); // Re-run after delay
            }
        };
        handler.post(refreshRunnable); // Initial trigger
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the periodic refresh when the activity is destroyed to avoid memory leaks
        handler.removeCallbacks(refreshRunnable);
    }

    // Method to receive table list from presenter
    public void showTables(List<TableOrderDTO> tablesList) {
        tableList.clear();
        tableList.addAll(tablesList);
        adapter.notifyDataSetChanged();
    }

    public void showError(String message) {
        Log.d("Main Error", "Loading Error: " + message);
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
            Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}