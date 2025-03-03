package com.example.coffee_order_app.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.coffee_order_app.Model.TableOrderDTO;
import com.example.coffee_order_app.R;

import java.util.List;
import java.util.Random;

public class MainActivityAdapter extends BaseAdapter {
    private final Context context;
    ImageView tableImage;
    TextView tableNumber;
    TextView tableStatus;
    TextView tableAmount;
    private List<TableOrderDTO> tableList;

    private int[] tableImages = {
            R.drawable.table_img_1,
            R.drawable.table_img_2,
            R.drawable.table_img_3,
            R.drawable.table_img_4,
            R.drawable.table_img_5
    };

    public MainActivityAdapter(Context context, List<TableOrderDTO> tableList) {
        this.context = context;
        this.tableList = tableList;
    }

    @Override
    public int getCount() {
        return tableList.size();
    }

    @Override
    public Object getItem(int position) {
        return tableList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.table_item, parent, false);
        }

        TableOrderDTO table = tableList.get(position);

        tableImage = convertView.findViewById(R.id.tableImage);
        tableNumber = convertView.findViewById(R.id.tableNumber);
        tableStatus = convertView.findViewById(R.id.tableStatus);
        tableAmount = convertView.findViewById(R.id.tableAmount);

        //Random set table image
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            int randomIndex = random.nextInt(tableImages.length); // Get a random index
            tableImage.setImageResource(tableImages[randomIndex]); // Set random image
        }

        tableNumber.setText(context.getString(R.string.table_number, table.getTable().getTableNumber()));
        String status = table.getTable().getStatus() == 0 ? "Available" : "Not Available";
        tableStatus.setText(context.getString(R.string.table_status, status));
        tableAmount.setText(context.getString(R.string.table_amount, table.getOrder().getTotalPrice()));

        // Change color based on table status
        if (table.getTable().getStatus() == 1) {
            //Table is not available
            tableStatus.setTextColor(ContextCompat.getColor(context, R.color.red));
        } else {
            //Table is available
            tableStatus.setTextColor(ContextCompat.getColor(context, R.color.green));
        }

        return convertView;
    }
}
