package edu.utep.cs.cs5390.mypricewatcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class WatchListAdapter extends ArrayAdapter<Item> {

    public WatchListAdapter(Context context, int resource, List<Item> itemList) {
        super(context, resource, itemList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.watch_list, parent, false);


        }
        return super.getView(position, convertView, parent);
    }
}
