package com.example.coffee_order_app.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

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
import com.example.coffee_order_app.Model.TokenManager;
import com.example.coffee_order_app.Presenter.MainPresenter;
import com.example.coffee_order_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    private Toolbar toolbar;
    private Spinner floorSpinner;
    private Spinner sortSpinner;
    private GridView tables;
    private List<TableOrderDTO> tableList;
    private List<TableOrderDTO> floorTableList;
    private MainActivityAdapter adapter;
    private MainPresenter presenter;
    private static final int REFRESH_INTERVAL = 5000; // 5 seconds
    private Handler handler;
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

        // Setup toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        Objects.requireNonNull(toolbar.getOverflowIcon()).setTint(ContextCompat.getColor(this, R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_logout);
        toolbar.setNavigationOnClickListener(v -> {
            // Log out, clear token and move to Log in
            TokenManager tokenManager = TokenManager.getInstance(this);
            tokenManager.clearAccessToken();
            tokenManager.clearRefreshToken();
            Intent intent = new Intent(MainActivity.this, InitActivity.class);
            startActivity(intent);
            finish();
        });

        // Initialize UI components
        floorSpinner = findViewById(R.id.floor_spinner);
        sortSpinner = findViewById(R.id.sort_spinner);
        tables = findViewById(R.id.table_grid_view);
        presenter = new MainPresenter(this);
        tableList = new ArrayList<>();
        floorTableList = new ArrayList<>();

        // Floor selection spinner (Default: "Floor 1")
        ArrayAdapter<String> floorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Floor 1", "Floor 2", "Floor 3"});
        floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floorSpinner.setAdapter(floorAdapter);
        floorSpinner.setSelection(0); // Default to "Floor 1"

        // Sorting spinner (Default: No selection, shows hint)
        ArrayAdapter<String> sortAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Sort tables", "Sort by Status", "Sort by Capacity", "Sort by Both"});
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdapter);
        sortSpinner.setSelection(0); // Show hint

        // Floor selection listener
        floorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Filter", "Starting Filter table in Activity at floor " + (position + 1));
                Log.d("Filter", "Starting Filter table in Activity with tableList size " + tableList.size());
                presenter.filterTablesByFloor(position + 1, tableList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Sorting listener (ignores hint "Sort tables")
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) { // Ignore the hint option
                    presenter.sortTables(position, floorTableList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Fetch tables and start periodic refresh
        adapter = new MainActivityAdapter(this, floorTableList);
        presenter.getAllTables(floorSpinner.getSelectedItemPosition() + 1);
        startPeriodicRefresh();
        tables.setAdapter(adapter);

        // Click event to open TableActivity
        tables.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            TableOrderDTO selectedTable = floorTableList.get(position);
            Intent intent = new Intent(MainActivity.this, TableActivity.class);
            intent.putExtra("tableNumber", selectedTable.getTable().getTableNumber());
            intent.putExtra("tableFloor", selectedTable.getTable().getFloorNumber());
            startActivity(intent);
        });
    }

    private void startPeriodicRefresh() {
        handler = new Handler(Looper.getMainLooper());
        refreshRunnable = new Runnable() {
            @Override
            public void run() {
                presenter.getAllTables(floorSpinner.getSelectedItemPosition() + 1);
                handler.postDelayed(this, REFRESH_INTERVAL);
            }
        };
        handler.post(refreshRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(refreshRunnable);
    }

    @Override
    public void showTables(List<TableOrderDTO> tablesList) {
        Log.d("Sort tables", "List size before show: " + tablesList.size());
        Log.d("Sort tables", "Floor list size before show: " + floorTableList.size());
        floorTableList.clear();
        floorTableList.addAll(new ArrayList<>(tablesList));
        adapter.notifyDataSetChanged();
        Log.d("Sort tables", "List size after show: " + tablesList.size());
        Log.d("Sort tables", "Floor list size after show: " + floorTableList.size());
    }


    @Override
    public void showError(String message) {
        Log.d("Main Error", "Loading Error: " + message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_toolbar_menu, menu);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.toolbar_history) {
            startActivity(new Intent(MainActivity.this, OrderHistoryActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateTableList(List<TableOrderDTO> tablesList) {
        tableList.clear(); // Clear previous data
        tableList.addAll(tablesList); // Store the new fetched tables
    }

}
