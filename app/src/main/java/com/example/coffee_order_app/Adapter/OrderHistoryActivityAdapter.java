package com.example.coffee_order_app.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.coffee_order_app.Model.API.ApiClient;
import com.example.coffee_order_app.Model.TableOrderDTO;
import com.example.coffee_order_app.R;

import java.util.List;

public class OrderHistoryActivityAdapter extends BaseAdapter {
    private final Context context;
    private List<TableOrderDTO> HistoryList;

    public OrderHistoryActivityAdapter(Context context, List<TableOrderDTO> historyList) {
        this.context = context;
        HistoryList = historyList;
    }

    @Override
    public int getCount() {
        return HistoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return HistoryList.get(position);
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

        TableOrderDTO TableOrderDTO = HistoryList.get(position);

        ImageView tableImage = convertView.findViewById(R.id.tableImage);
        TextView tableNumber = convertView.findViewById(R.id.history_table_number);
        TextView tableAmount = convertView.findViewById(R.id.history_table_amount);
        TextView paidTime = convertView.findViewById(R.id.history_paid_time);

        ;
        //Set data for View
        Log.d("Order History Adapter", "image uri: " + ApiClient.getURL() + TableOrderDTO.getTable().getTableImage());
        Glide.with(context).load(ApiClient.getURL() + TableOrderDTO.getTable().getTableImage()).into(tableImage);
        tableNumber.setText(context.getString(R.string.table_number, TableOrderDTO.getTable().getTableNumber()));
        tableAmount.setText(context.getString(R.string.table_amount, TableOrderDTO.getOrder().getTotalPrice()));
        // Convert timestamp
        paidTime.setText(TableOrderDTO.getOrder().getPaidAt());
        return convertView;
    }
}
