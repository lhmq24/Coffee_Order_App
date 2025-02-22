package com.example.coffee_order_app.Adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.coffee_order_app.Model.Table;
import com.example.coffee_order_app.R;

import java.util.List;
import java.util.Random;

public class MainActivityAdapter extends BaseAdapter {
    private final Context context;
    private List<Table> tableList;

    private int[] tableImages = {
            R.drawable.table_img_1,
            R.drawable.table_img_2,
            R.drawable.table_img_3,
            R.drawable.table_img_4,
            R.drawable.table_img_5
    };

    public MainActivityAdapter(Context context, List<Table> tableList) {
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
        Log.d("MainActivityAdapter", "Rendering table at position: " + position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.table_item, parent, false);
        }

        Table table = tableList.get(position);

        ImageView tableImage = convertView.findViewById(R.id.tableImage);
        TextView tableNumber = convertView.findViewById(R.id.tableNumber);
        TextView tableStatus = convertView.findViewById(R.id.tableStatus);
        TextView tableAmount = convertView.findViewById(R.id.tableAmount);

        //Random set table image
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            int randomIndex = random.nextInt(tableImages.length); // Get a random index
            tableImage.setImageResource(tableImages[randomIndex]); // Set random image
        }

        tableNumber.setText(context.getString(R.string.table_number, table.getTableNumber()));
        String status = table.getStatus() == 0 ? "Available" : "Not Available";
        tableStatus.setText(context.getString(R.string.table_status, status));
//        tableAmount.setText(context.getString(R.string.table_amount, table.getTotalAmount()));

        // Change color based on table status
        if (table.getStatus() == 1) {
            //Table is not available
            tableStatus.setTextColor(ContextCompat.getColor(context, R.color.red));
        } else {
            //Table is available
            tableStatus.setTextColor(ContextCompat.getColor(context, R.color.green));
        }

        return convertView;
    }
}
