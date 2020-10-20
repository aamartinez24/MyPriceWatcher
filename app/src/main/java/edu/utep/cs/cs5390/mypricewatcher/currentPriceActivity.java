package edu.utep.cs.cs5390.mypricewatcher;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

// Airam Martinez & Mildred Brito
public class currentPriceActivity extends AppCompatActivity {

    private TextView itemCurrentPrice;
    private TextView itemPercentChange;
    private Button urlButton;
    private PriceFinder priceFinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_price);
/*
        // Set up button
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
*/
        // Get all information passed from previous activity
        String itemName = getIntent().getStringExtra("itemName");
        double itemInitalPrice = getIntent().getDoubleExtra("itemInitalPrice", 0);
        String itemURL = getIntent().getStringExtra("itemURL");
        Item item = new Item(itemName, itemInitalPrice, itemURL);
        priceFinder = new PriceFinder(item);

        itemCurrentPrice = findViewById(R.id.itemCurrentPrice);
        itemPercentChange = findViewById(R.id.itemPercentChange);
        priceFinder.findCurrPrice();
        double currentPrice = priceFinder.findCurrPrice();
        itemCurrentPrice.setText("$" + currentPrice);
        double percentChange = priceFinder.getPercentageChange();
        itemPercentChange.setText(percentChange + "%");

        urlButton = findViewById(R.id.urlButton);
        urlButton.setOnClickListener(v ->{
            Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.walmart.com/ip/Nintendo-Switch-Console-with-Neon-Blue-Red-Joy-Con/709776123"));
            startActivity(i);
        });
    }
}