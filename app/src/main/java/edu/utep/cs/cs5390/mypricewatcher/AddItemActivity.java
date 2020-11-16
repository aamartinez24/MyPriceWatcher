package edu.utep.cs.cs5390.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Airam Martinez & Mildred Brito
// Activity for adding or editing an item from the watch list.
public class AddItemActivity extends AppCompatActivity {

    private EditText itemName;
    private EditText itemUrl;
    private Button cancelButton;
    private Button saveButton;

    private int position;
    private Item editItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        itemName = findViewById(R.id.edit_item_name);
        itemUrl = findViewById(R.id.edit_item_URL);
        Intent i = getIntent();
        // Boolean to determine if the activity was called to add or edit an item.
        boolean isAddingItem = i.getBooleanExtra("AddingItem", true);
        // String holding url when shared to the app.
        String sharedUrl = i.getStringExtra("SharedUrl");
        // Activity was called for editing existing item in the list.
        if(!isAddingItem) {
            Toast.makeText(getApplicationContext(), "editing item", Toast.LENGTH_LONG).show();
            editItem = (Item) i.getSerializableExtra("ITEM");       // Item that will be edited.
            // Fill TextViews with the item's current information.
            itemName.setText(editItem.getName());
            itemUrl.setText(editItem.getUrl());
            position = i.getIntExtra("Position", 0);
        }
        // Fill edit_item_URL TextView with the url that was shared.
        else if(sharedUrl != null) {
            itemUrl.setText(sharedUrl);
        }
        // Cancels the activity and returns to the main activity.
        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(view -> {
            Intent returnIntent = new Intent();
            setResult(-1, returnIntent);
            finish();
        });
        // Saves all information inputted.
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view -> {
            int isCorrect = checkInput();       // Checks if input is valid.
            switch (isCorrect) {
                case 0:
                    Toast.makeText(getApplicationContext(), "Please fill all fields.", Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(getApplicationContext(), "Invalid URL. Check format.", Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), "URL not supported.", Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    // Input is valid and user is adding a new item.
                    if(isAddingItem) {
                        String newItemName = itemName.getText().toString();
                        // Static value until we fix PriceWatcher class.
                        // TODO fix PriceFinder
                        double newItemInit = 10.20;
                        double newItemCurr = 20.30;

                        String newItemUrl = itemUrl.getText().toString();
                        Intent returnIntent = new Intent();
                        // Pass all data to create a new item.
                        returnIntent.putExtra("Name", newItemName);
                        returnIntent.putExtra("Initial", newItemInit);
                        returnIntent.putExtra("Current", newItemCurr);
                        returnIntent.putExtra("Url", newItemUrl);
                        setResult(0, returnIntent);
                    }
                    // Input is valid and user is editing an existing item.
                    else {
                        editItem.setName(itemName.getText().toString());
                        editItem.setUrl(itemUrl.getText().toString());
                        // TODO fix PriceFinder
                        /*editItem.findCurrentPrice();
                        editItem.setInitialPrice(editItem.getCurrentPrice());
                        editItem.setPercentageChange();*/
                        Intent returnIntent = new Intent();
                        // Pass the item and its position in the ListView.
                        returnIntent.putExtra("ITEM", editItem);
                        returnIntent.putExtra("position", position);
                        setResult(1, returnIntent);
                    }
                    finish();
            }
        });
    }

    // Checks if item has a name and a valid url.
    private int checkInput() {
        String newItemName = itemName.getText().toString();
        String newItemUrl = itemUrl.getText().toString();
        if(newItemName.isEmpty() || newItemUrl.isEmpty())
            return 0;       // All fields are not filled.
        else if(!URLUtil.isValidUrl(newItemUrl))
            return 1;       // URL is invalid.
        // TODO fix PriceFinder
        /*else if(PriceFinder.validateUrl(newItemUrl) == 0)
            return 2;*/     // URL is not supported by app.
        else
            return 3;       // All input is valid.
    }
}