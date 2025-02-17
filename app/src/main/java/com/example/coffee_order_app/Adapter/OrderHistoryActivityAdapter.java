package com.example.coffee_order_app.Adapter;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffee_order_app.Model.Receipt;
import com.example.coffee_order_app.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class OrderHistoryActivityAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<Receipt> ReceiptList;

    public OrderHistoryActivityAdapter(Context context, ArrayList<Receipt> ReceiptList) {
        this.context = context;
        this.ReceiptList = ReceiptList;
    }

    @Override
    public int getCount() {
        return ReceiptList.size();
    }

    @Override
    public Object getItem(int position) {
        return ReceiptList.get(position);
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

        Receipt receipt = ReceiptList.get(position);

        ImageView tableImage = convertView.findViewById(R.id.tableImage);
        TextView tableNumber = convertView.findViewById(R.id.history_table_number);
        TextView tableAmount = convertView.findViewById(R.id.history_table_amount);
        TextView tablePayTime = convertView.findViewById(R.id.history_paid_time);
        ;
        //Get table ID for table name, must be change
        tableNumber.setText(context.getString(R.string.table_number, receipt.getTableId()));
        tableAmount.setText(context.getString(R.string.table_amount, receipt.getTotalPrice()));
        tablePayTime.setText(context.getString(R.string.pay_time, convertTimestampToDate(receipt.getPaidTime())));

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
