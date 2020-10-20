package edu.utep.cs.cs5390.mypricewatcher;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// Airam Martinez & Mildred Brito

public class WatchListAdapter extends ArrayAdapter<Item> {

    PriceFinder priceFinder;
    Context context;

    public WatchListAdapter(Context context, int resourceId, List<Item>items){
        super(context, resourceId, items);
        this.context = context;
    }

    @Override
    public int getPosition(Item item) {
        return super.getPosition(item);
    }

    @Override
    public Item getItem(int position) {
        return super.getItem(position);
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

        Button settingButton = convertView.findViewById(R.id.settings);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupWindow popupWindow = new PopupWindow(getContext());
                ArrayList<String> settingList = new ArrayList<>();
                settingList.add("Remove");
                settingList.add("Edit");

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line, settingList);
                ListView listViewSetting = new ListView(getContext());
                listViewSetting.setAdapter(adapter);

                listViewSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (adapterView.getAdapter().getItem(i).toString()) {
                            case "Remove":
                                Log.d("Main", "Remove");
                                Log.d("Main", "" + getItem(position).getItemName());
                                remove(getItem(position));
                                break;
                            case "Edit":
                                Log.d("Main", "Edit");

                                break;
                        }
                        popupWindow.dismiss();
                    }
                });

                popupWindow.setFocusable(true);
                popupWindow.setWidth(200);
                popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                popupWindow.setElevation(10);
                popupWindow.setContentView(listViewSetting);
                popupWindow.showAsDropDown(view, 0, 0);
            }
        });

        ImageButton searchButton = convertView.findViewById(R.id.searchURLButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = getItem(position).getUrl();
                Log.d("URL", "" + url);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });
        return convertView;
    }

}
