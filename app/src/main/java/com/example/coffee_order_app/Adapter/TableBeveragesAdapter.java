package com.example.coffee_order_app.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffee_order_app.Model.Beverage;
import com.example.coffee_order_app.R;
import com.example.coffee_order_app.View.TableActivity;

import java.util.List;

public class TableBeveragesAdapter extends BaseAdapter {
    private TableActivity view;
    private List<Beverage> BeverageList;

    public TableBeveragesAdapter(TableActivity context, List<Beverage> list) {
        this.view = context;
        this.BeverageList = list;
    }

    @Override
    public int getCount() {
        return BeverageList != null ? BeverageList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return BeverageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(view).inflate(R.layout.bev_item, parent, false);
        }

        Beverage bev = BeverageList.get(position);

        ImageView bevImage = convertView.findViewById(R.id.bev_img);
        TextView bevName = convertView.findViewById(R.id.bev_name);
        TextView bevPrice = convertView.findViewById(R.id.bev_price);


        bevName.setText(view.getString(R.string.beverage_name, bev.getName()));
//        bevName.setText(view.getString(R.string.beverage_price, ));
        if (BeverageList == null || BeverageList.isEmpty()) {
            Log.d("Table beverage adapter", "Beverage List is null or empty, cannot convert the View");
        }
        return convertView;
    }
}
