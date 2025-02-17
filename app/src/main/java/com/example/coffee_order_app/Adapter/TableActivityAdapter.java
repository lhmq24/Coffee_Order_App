package com.example.coffee_order_app.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.coffee_order_app.Model.OrderItem;

import java.util.ArrayList;

public class TableActivityAdapter extends BaseAdapter {
    private Context activity;
    private ArrayList<OrderItem> ItemList;

    public TableActivityAdapter(Context context, ArrayList<OrderItem> list) {
        activity = context;
        ItemList = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
