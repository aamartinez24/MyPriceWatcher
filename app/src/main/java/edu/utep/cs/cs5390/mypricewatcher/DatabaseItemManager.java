package edu.utep.cs.cs5390.mypricewatcher;

import android.content.Context;

public class DatabaseItemManager extends Item {

    private final Context context;
    private ItemDatabaseHelper itemDatabaseHelper;

    public DatabaseItemManager(String name, double price, String url, Context context) {
        super(name, price, url);
        this.context = context;
        itemDatabaseHelper = new ItemDatabaseHelper(this.context);
        itemDatabaseHelper.allItems();
    }
}
