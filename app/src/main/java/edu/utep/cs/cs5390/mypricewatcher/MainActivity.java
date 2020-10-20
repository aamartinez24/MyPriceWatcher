package edu.utep.cs.cs5390.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

// Airam Martinez & Mildred Brito
public class MainActivity extends AppCompatActivity implements AddItemDialog.AddItemDialogListener {

    private ListView listView;
    private WatchListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String action = getIntent().getAction();
        String type = getIntent().getType();
        if(Intent.ACTION_SEND.equalsIgnoreCase(action)
        && type != null && ("text/plain".equals(type))){
            String url = getIntent().getStringExtra(Intent.EXTRA_TEXT);
            Log.d("URL", "" + url);
            openDialog(url);
        }

        listView = findViewById(R.id.listView);
        adapter = new WatchListAdapter(this, R.layout.watch_list, Item.allItems());
        listView.setAdapter(adapter);
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

    private void openDialog() {
        AddItemDialog addItemDialog = new AddItemDialog();
        addItemDialog.show(getSupportFragmentManager(), "Add Item Dialog");
    }

    private void openDialog(String url) {
        AddItemDialog addItemDialog = new AddItemDialog();
        addItemDialog.setEditTextItemURL(url);
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