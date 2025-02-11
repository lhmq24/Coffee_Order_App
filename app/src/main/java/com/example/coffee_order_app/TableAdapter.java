package com.example.coffee_order_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.List;

public class TableAdapter extends BaseAdapter {
    private final Context context;
    private List<Table> tableList;

    public TableAdapter(Context context, List<Table> tableList) {
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

        Table table = tableList.get(position);

        ImageView tableImage = convertView.findViewById(R.id.tableImage);
        TextView tableNumber = convertView.findViewById(R.id.tableNumber);
        TextView tableStatus = convertView.findViewById(R.id.tableStatus);
        TextView tableAmount = convertView.findViewById(R.id.tableAmount);

//        tableImage.setImageResource(table.getImage());
        tableNumber.setText(context.getString(R.string.table_number, table.getNumber()));
        tableStatus.setText(context.getString(R.string.table_status, table.getStatus()));
        tableAmount.setText(context.getString(R.string.table_amount, table.getTotalAmount()));

        // Change color based on table status
        if (table.getStatus().equals("Occupied")) {
            tableStatus.setTextColor(ContextCompat.getColor(context, R.color.red));
        } else {
            tableStatus.setTextColor(ContextCompat.getColor(context, R.color.green));
        }

        return convertView;
    }
}
