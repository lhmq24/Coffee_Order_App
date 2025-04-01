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

import com.bumptech.glide.Glide;
import com.example.coffee_order_app.Model.API.ApiClient;
import com.example.coffee_order_app.Model.TableOrderDTO;
import com.example.coffee_order_app.R;

import java.util.List;


public class MainActivityAdapter extends BaseAdapter {
    private final Context context;
    TextView tableCapacity;
    ImageView tableImage;
    TextView tableNumber;
    private LayoutInflater inflater;
    TextView tableStatus;
    TextView tableAmount;
    private List<TableOrderDTO> tableList;

    public MainActivityAdapter(Context context, List<TableOrderDTO> tableList) {
        this.context = context;
        this.tableList = tableList;
        this.inflater = LayoutInflater.from(context);
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
        long startTime = System.currentTimeMillis();

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.table_item, parent, false);
            holder = new ViewHolder();
            holder.tableImage = convertView.findViewById(R.id.tableImage);
            holder.tableNumber = convertView.findViewById(R.id.tableNumber);
            holder.tableCapacity = convertView.findViewById(R.id.tableCapacity);
            holder.tableStatus = convertView.findViewById(R.id.tableStatus);
            holder.tableAmount = convertView.findViewById(R.id.tableAmount);
            convertView.setTag(holder); // Lưu ViewHolder vào View
        } else {
            Log.d("AdapterDebug", "The View is reuse");
            holder = (ViewHolder) convertView.getTag(); // Lấy lại ViewHolder đã lưu
        }

        TableOrderDTO table = tableList.get(position);

        Glide.with(context)
                .load(ApiClient.getURL() + table.getTable().getTableImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.tableImage);

        long imageLoadTime = System.currentTimeMillis();

        holder.tableNumber.setText(context.getString(R.string.table_number, table.getTable().getTableNumber()));
        holder.tableCapacity.setText(context.getString(R.string.table_capacity, table.getTable().getTableCapacity()));

        if (table.getTable().getStatus() == 0) {
            holder.tableStatus.setText(context.getString(R.string.table_status, "Available"));
            holder.tableStatus.setTextColor(ContextCompat.getColor(context, R.color.green));
            holder.tableAmount.setText(context.getString(R.string.table_amount, 0.0f));
        } else {
            holder.tableStatus.setText(context.getString(R.string.table_status, "Not Available"));
            holder.tableStatus.setTextColor(ContextCompat.getColor(context, R.color.red));

            if (table.getOrder() != null) {
                holder.tableAmount.setText(context.getString(R.string.table_amount, table.getOrder().getTotalPrice()));
            } else {
                holder.tableAmount.setText(context.getString(R.string.table_amount, 0.0f));
            }
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView tableImage;
        TextView tableNumber;
        TextView tableCapacity;
        TextView tableStatus;
        TextView tableAmount;
    }
}


