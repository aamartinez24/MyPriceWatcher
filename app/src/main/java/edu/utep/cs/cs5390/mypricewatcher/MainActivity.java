package edu.utep.cs.cs5390.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

// Airam Martinez
public class MainActivity extends AppCompatActivity implements AddItemDialog.AddItemDialogListener {

    private TextView itemName;
    private TextView itemInitalPrice;
    private Item item;
    private ListView listView;
    private WatchListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        adapter = new WatchListAdapter(this, R.layout.watch_list, Item.allItems());
        listView.setAdapter(adapter);
        /*itemName = findViewById(R.id.itemName);
        itemInitalPrice = findViewById(R.id.itemInitalPrice);
        // Fixed item's name and url
        String name = "Nintendo Switch";
        String url = "https://www.walmart.com/ip/Nintendo-Switch-Console-with-Neon-Blue-Red-Joy-Con/709776123";
        item = new Item(name, url);
        itemName.setText(item.getItemName());
        // Fixed item's inital price
        double initalPrice = 299.99;
        item.setInitalPrice(initalPrice);
        itemInitalPrice.setText("$" + initalPrice);
    }

    public void buttonClicked(View view) {
        Intent intent = new Intent(this, currentPriceActivity.class);
        intent.putExtra("itemInitalPrice", item.getInitalPrice());
        intent.putExtra("itemName", item.getItemName());
        intent.putExtra("itemURL", item.getUrl());
        startActivity(intent);*/
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_add_item:
                openDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openDialog() {
        AddItemDialog addItemDialog = new AddItemDialog();
        addItemDialog.show(getSupportFragmentManager(), "Add Item Dialog");
    }

    @Override
    public void applyTexts(String itemName, String itemURL, double itemPrice) {
        Log.d("Name", "" + itemName);
        Log.d("URL", "" + itemURL);
        Log.d("Price", "" + itemPrice);
        Item item = new Item(itemName, itemPrice, itemURL);
        adapter.add(item);
    }

}