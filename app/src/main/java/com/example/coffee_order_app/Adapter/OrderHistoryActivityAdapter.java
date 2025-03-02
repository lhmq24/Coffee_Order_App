package com.example.coffee_order_app.Adapter;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffee_order_app.Model.Order;
import com.example.coffee_order_app.R;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderHistoryActivityAdapter extends BaseAdapter {
    private final Context context;
    private List<Order> OrderList;

    public OrderHistoryActivityAdapter(Context context, List<Order> OrderList) {
        this.context = context;
        this.OrderList = OrderList;
    }

    @Override
    public int getCount() {
        return OrderList.size();
    }

    @Override
    public Object getItem(int position) {
        return OrderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
        }

        Order Order = OrderList.get(position);

        ImageView tableImage = convertView.findViewById(R.id.tableImage);
        TextView tableNumber = convertView.findViewById(R.id.history_table_number);
        TextView tableAmount = convertView.findViewById(R.id.history_table_amount);

        ;
        //Get table ID for table name, must be change
        tableNumber.setText(context.getString(R.string.table_number, Order.getTableId()));
        tableAmount.setText(context.getString(R.string.table_amount, Order.getTotalPrice()));
        return convertView;
    }

    public String convertTimestampToDate(long timestamp) {
        // Define date format
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());

        // Convert timestamp to Date
        Date date = new Date(timestamp);

        // Format the date
        return sdf.format(date);
    }
}
