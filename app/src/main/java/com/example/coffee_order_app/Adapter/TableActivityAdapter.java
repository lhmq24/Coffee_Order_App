package com.example.coffee_order_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffee_order_app.Model.Beverage;
import com.example.coffee_order_app.R;

import java.util.List;

public class TableActivityAdapter extends BaseAdapter {
    private Context activity;
    private List<Beverage> BeverageList;

    public TableActivityAdapter(Context context, List<Beverage> list) {
        activity = context;
        BeverageList = list;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.bev_item, parent, false);
        }

        Beverage bev = BeverageList.get(position);

        ImageView bevImage = convertView.findViewById(R.id.bev_img);
        TextView bevName = convertView.findViewById(R.id.bev_name);
        TextView bevPrice = convertView.findViewById(R.id.bev_price);


        bevName.setText(activity.getString(R.string.beverage_name, bev.getName()));
//        bevName.setText(activity.getString(R.string.beverage_price, ));

        return convertView;
    }
}
