package edu.utep.cs.cs5390.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// Airam Martinez
public class MainActivity extends AppCompatActivity {

    private TextView itemName;
    private TextView itemInitalPrice;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemName = findViewById(R.id.itemName);
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
        startActivity(intent);
    }
}