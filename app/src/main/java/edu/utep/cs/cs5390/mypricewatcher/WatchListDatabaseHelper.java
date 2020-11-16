package edu.utep.cs.cs5390.mypricewatcher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

// Airam Martinez & Mildred Brito.
// SQL Database to save all items and their data.
public class WatchListDatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "priceWatcherDB";
    private static final String ITEM_TABLE = "items";

    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_INITIAL_PRICE = "initial_price";
    private static final String KEY_CURRENT_PRICE = "current_price";
    private static final String KEY_URL = "url";

    public WatchListDatabaseHelper(Context context) {
        super (context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE " + ITEM_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, "
                + KEY_INITIAL_PRICE + " FLOAT, "
                + KEY_CURRENT_PRICE + " FLOAT, "
                + KEY_URL + " TEXT" + ")";
        db.execSQL(table);
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE);
        onCreate(database);
    }

    // Add item to the database.
    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_INITIAL_PRICE, item.getInitialPrice());
        values.put(KEY_CURRENT_PRICE, item.getCurrentPrice());
        values.put(KEY_URL, item.getUrl());
        long id = db.insert(ITEM_TABLE, null, values);
        item.setId((int) id);
        db.close();
    }

    // Creates list of all items saved in the database.
    public List<Item> allItems() {
        List<Item> itemList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + ITEM_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                double initialPrice = cursor.getDouble(2);
                double currentPrice = cursor.getDouble(3);
                String url = cursor.getString(4);
                Item task = new Item(id, name, initialPrice, currentPrice, url);
                itemList.add(task);
            } while (cursor.moveToNext());
        }
        return itemList;
    }

    // Deletes all items in the database.
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ITEM_TABLE, null, new String[]{});
        db.close();
    }

    // Deletes a specific item from the database.
    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ITEM_TABLE, KEY_ID + " = ?", new String[] { Integer.toString(id) } );
        db.close();
    }

    // Updates a changed item from the database.
    public void update(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_CURRENT_PRICE, item.getCurrentPrice());
        values.put(KEY_INITIAL_PRICE, item.getInitialPrice());
        values.put(KEY_URL, item.getUrl());
        db.update(ITEM_TABLE, values, KEY_ID + " = ?", new String[] {String.valueOf(item.getId())});
        db.close();
    }

}
