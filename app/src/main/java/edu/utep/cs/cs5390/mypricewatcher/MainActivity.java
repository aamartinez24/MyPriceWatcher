package edu.utep.cs.cs5390.mypricewatcher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


// Airam Martinez & Mildred Brito
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private WatchListAdapter adapter;
    private WatchListDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        helper = new WatchListDatabaseHelper(this);
        adapter = new WatchListAdapter(this, R.layout.watched_item, helper.allItems());
        listView.setAdapter(adapter);
        // Check if wifi is on.
        checkNetworkStatus();
        // Intent when user shares a url to the app.
        Intent intent = getIntent();
        if(Intent.ACTION_SEND.equalsIgnoreCase(intent.getAction())
                && intent.getType() != null && ("text/plain".equals(intent.getType()))){
            String url = intent.getStringExtra(Intent.EXTRA_TEXT);
            Intent i = new Intent(this, AddItemActivity.class);
            i.putExtra("SharedUrl", url);
            i.putExtra("AddingItem", true);
            startActivityForResult(i, 1);
        }
    }

    // Add menu options when activity is created.
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Checks if wifi is on, If wifi is off, it will redirect to wifi settings.
    private void checkNetworkStatus() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if(!isConnected) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Wifi not connected. Redirecting to Network Setting.");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    // Listener for option menu selections
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            // Add button was pressed.
            case R.id.addButton:
                Intent i = new Intent(this, AddItemActivity.class);
                i.putExtra("addingItem", true);
                // Call activity to add an item to the list.
                startActivityForResult(i, 1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            // Item was added.
            if(resultCode == 0) {
                String name = data.getStringExtra("Name");
                double initial = data.getDoubleExtra("Initial", 0);
                double current = data.getDoubleExtra("Current", 0);
                String url = data.getStringExtra("Url");
                // Create item class from all data passed.
                Item newItem = new Item(name, initial, current, url);
                adapter.add(newItem);
                helper.addItem(newItem);
            }
            // Item was edited.
            else if(resultCode == 1) {
                Item editedItem = (Item) data.getSerializableExtra("ITEM");
                int position = data.getIntExtra("position", 0);
                // Update the changed item.
                adapter.items.set(position, editedItem);
                adapter.notifyDataSetChanged();
                helper.update(editedItem);
            }
        }
    }
}