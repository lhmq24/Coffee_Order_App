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
import com.example.coffee_order_app.Model.BeveragePriceDTO;
import com.example.coffee_order_app.R;
import com.example.coffee_order_app.View.TableActivity;

import java.util.List;

public class TableBeveragesAdapter extends BaseAdapter {
    private Context context;
    private List<BeveragePriceDTO> BeverageList;

    public TableBeveragesAdapter(TableActivity context, List<BeveragePriceDTO> list) {
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.bev_item, parent, false);
        }

        BeveragePriceDTO bev = BeverageList.get(position);
        if (BeverageList.isEmpty()) {
            Log.d("Table beverage adapter", "Beverage List is null or empty, cannot convert the View");
        }

        ImageView bevImage = convertView.findViewById(R.id.bev_img);
        TextView bevName = convertView.findViewById(R.id.bev_name);
        TextView bevPrice = convertView.findViewById(R.id.bev_price);

        Glide.with(context)
                .load(ApiClient.getURL() + bev.getBev().getImg())
                .into(bevImage);
        bevName.setText(context.getString(R.string.beverage_name, bev.getBev().getName()));
        bevPrice.setText(context.getString(R.string.beverage_price, bev.getPrice().getPriceAmount()));

        return convertView;
    }
}
