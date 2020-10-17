package edu.utep.cs.cs5390.mypricewatcher;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WatchListAdapter extends ArrayAdapter<Item> {
    PriceFinder currentPrice;

    public WatchListAdapter(Context context, int resourceId, List<Item>items){
        super(context, resourceId, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.watch_list, parent, false);
        }
        Item current = getItem(position);
        TextView itemName = convertView.findViewById(R.id.ItemName);
        itemName.setText(current.getItemName());

        TextView itemPrice = convertView.findViewById(R.id.itemPrice);
        itemPrice.setText((int) current.getInitalPrice());

        TextView itemCurrentPrice = convertView.findViewById(R.id.itemCurrentPrice);
        itemCurrentPrice.setText((int) currentPrice.getCurrentPrice());

        TextView itemPercentChange = convertView.findViewById(R.id.itemPercentChange);
        itemCurrentPrice.setText((int) currentPrice.getPercentageChange());

        return convertView;
    }
}
