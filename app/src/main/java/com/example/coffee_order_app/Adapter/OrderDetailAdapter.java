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
import com.example.coffee_order_app.Model.OrderItemBeverageDTO;
import com.example.coffee_order_app.R;

import java.util.List;

public class OrderDetailAdapter extends BaseAdapter {

    private final Context context;
    private List<OrderItemBeverageDTO> itemList;

    public OrderDetailAdapter(Context context, List<OrderItemBeverageDTO> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.order_bev_item, parent, false);

            view = new ViewHolder();
            view.bevImage = convertView.findViewById(R.id.bev_img);
            view.bevName = convertView.findViewById(R.id.bev_name);
            view.bevPrice = convertView.findViewById(R.id.bev_price);
            view.bevQuantity = convertView.findViewById(R.id.bevQuantity);
            convertView.setTag(view);
        } else {
            view = (OrderDetailAdapter.ViewHolder) convertView.getTag(); // Lấy lại ViewHolder đã lưu
        }

        OrderItemBeverageDTO dto = itemList.get(position);

        if (itemList.isEmpty()) {
            Log.d("Order detail adapter", "Beverage List is null or empty, cannot convert the View");
        }

        Glide.with(context)
                .load(ApiClient.getURL() + dto.getBeverage().getImg())
                .into(view.bevImage);

        view.bevName.setText(context.getString(R.string.beverage_name, dto.getBeverage().getName()));
        view.bevPrice.setText(context.getString(R.string.beverage_price, dto.getOrderItem().getItemPrice()));
        view.bevQuantity.setText(context.getString(R.string.Order_item_quantity, dto.getOrderItem().getItemQuantity()));
        return convertView;
    }

    static class ViewHolder {
        ImageView bevImage;
        TextView bevName;
        TextView bevPrice;
        TextView bevQuantity;
    }
}


