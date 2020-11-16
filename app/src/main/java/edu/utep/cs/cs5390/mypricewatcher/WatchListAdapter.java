package edu.utep.cs.cs5390.mypricewatcher;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

// Airam Martinez & Mildred Brito
// Provide views for an AdapterView by returning a view for each Item
// contained in the watchlist.
public class WatchListAdapter extends ArrayAdapter<Item> {

    WatchListDatabaseHelper helper;
    Context context;
    List<Item> items;

    public WatchListAdapter(Context context, int resourceId, List<Item> items) {
        super(context, resourceId, items);
        this.context = context;
        this.items = items;
    }

    public interface ItemClickListener {
        void itemClicked(Item item);
    }

    private ItemClickListener listener;

    public void setItemClickListener(ItemClickListener listener) {this.listener = listener; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.watched_item, parent, false);
        }

        Item current = getItem(position);
        // Sets all views with the item's information.
        TextView itemNameView = convertView.findViewById(R.id.text_item_name);
        itemNameView.setText(current.getName());
        TextView curPriceView = convertView.findViewById(R.id.text_current_price);
        curPriceView.setText(String.format("Current Price: $%.02f", current.getCurrentPrice()));
        TextView initPriceView = convertView.findViewById(R.id.text_initial_price);
        initPriceView.setText(String.format("Initial Price: $%.02f", current.getInitialPrice()));
        TextView percentageView = convertView.findViewById(R.id.text_percentage_difference);
        percentageView.setText(String.format("%.02f%% off!", current.calculatePercentageDiff()));

        ImageButton itemMenuButton = convertView.findViewById(R.id.item_menu_button);
        PopupMenu itemMenu = new PopupMenu(this.getContext(), itemMenuButton);
        itemMenu.inflate(R.menu.item_menu);
        // Popup menu when the itemMenuButton is pressed.
        itemMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                helper = new WatchListDatabaseHelper(getContext());
                switch(menuItem.getItemId()) {
                    case R.id.item_visit:
                        Intent search = new Intent(Intent.ACTION_VIEW);
                        search.setData(Uri.parse(current.getUrl()));
                        context.startActivity(search);
                        return true;
                    case R.id.item_edit:
                        Intent edit = new Intent(getContext(), AddItemActivity.class);
                        edit.putExtra("AddingItem", false);
                        edit.putExtra("Position", position);
                        edit.putExtra("ITEM", current);
                        Activity origin = (Activity) context;
                        origin.startActivityForResult(edit, 1);
                        return true;
                    case R.id.item_remove:
                        helper.delete(current.getId());
                        remove(getItem(position));
                        return true;
                }
                return false;
            }
        });
        itemMenuButton.setOnClickListener(view -> itemMenu.show());

        ImageButton itemRefreshButton = convertView.findViewById(R.id.refresh_button);
        itemRefreshButton.setOnClickListener(view -> {
            current.findCurrentPrice();
            notifyDataSetChanged();
            // TODO add refresh functionality with help of PriceFinder
            Toast.makeText(getContext(), "Item refreshed!", Toast.LENGTH_SHORT).show();
        });
        return convertView;
    }

    @Override
    public int getPosition(Item item) {
        return super.getPosition(item);
    }

    @Override
    public Item getItem(int position) {
        return super.getItem(position);
    }

}
