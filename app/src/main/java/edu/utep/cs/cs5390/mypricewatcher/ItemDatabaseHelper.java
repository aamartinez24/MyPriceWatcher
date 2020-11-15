package edu.utep.cs.cs5390.mypricewatcher;

// Airam Martinez & Mildred Brito
//Database helper to store or remove Items

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ItemDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final int DB_VERSION = 1;
    private static final String ITEM_TABLE = "item_table";

    private static final String COL1 = "Name";
    private static final String COL2 = "URL";
    private static final String COL3 = "Price";


    public ItemDatabaseHelper(Context context) {
        super(context, ITEM_TABLE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + ITEM_TABLE + "("
                + COL2 + " TEXT PRIMARY KEY, "
                + COL3 + " TEXT, "
                + COL1 + " TEXT" + ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE);
        onCreate(db);
    }

    public boolean addData(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item.getItemName());
        contentValues.put(COL2, item.getUrl());
        contentValues.put(COL3, item.getInitalPrice());

        Log.d(TAG, "Add data: Adding "+item+ "to " +ITEM_TABLE);

        long result = db.insert(ITEM_TABLE, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public List<Item> allItems() {
        List<Item> itemsList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + ITEM_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                double price = cursor.getDouble(1);
                String url = cursor.getString(2);
                Item task = new Item(name, price, url);
                itemsList.add(task);
            } while (cursor.moveToNext());
        }
        return itemsList;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ITEM_TABLE, null, new String[]{});
        db.close();
    }

    /*Still need to fix this to match items instead of to do id*/
    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ITEM_TABLE, COL2 + " = ?", new String[] { Integer.toString(id) } );
        db.close();
    }

    public void update(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item.getItemName());
        contentValues.put(COL2, item.getUrl());
        contentValues.put(COL3, item.getInitalPrice());
        db.update(ITEM_TABLE, contentValues, COL2 + " = ?", new String[]{String.valueOf(item.getUrl())});
        db.close();
    }
}
