package edu.utep.cs.cs5390.mypricewatcher;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WatchListAdapter extends ArrayAdapter<Item> {
    PriceFinder priceFinder;

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
        String stringDouble = Double.toString(current.getInitalPrice());
        itemPrice.setText(stringDouble);

        priceFinder = new PriceFinder(current);

        TextView itemCurrentPrice = convertView.findViewById(R.id.itemCurrentPrice);
        stringDouble = Double.toString(priceFinder.findCurrPrice());
        itemCurrentPrice.setText(stringDouble);

        TextView itemPercentChange = convertView.findViewById(R.id.itemPercentChange);
        itemPercentChange.setText("%" + priceFinder.getPercentageChange());

        return convertView;
    }
}
