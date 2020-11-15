package edu.utep.cs.cs5390.mypricewatcher;

// Airam Martinez & Mildred Brito
//Database helper to store or remove Items

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ItemDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String ITEM_TABLE = "item_table";
    private static final String COL1 = "Name";
    private static final String COL2 = "URL";
    private static final String COL3 = "Price";


    public ItemDatabaseHelper(Context context) {
        super(context, ITEM_TABLE, null, 1);
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

    public void addData(String item, String url, double price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item);
        contentValues.put(COL2, url);
        contentValues.put(COL3, price);

        db.insert(ITEM_TABLE, null, contentValues);
        db.close();

        Log.d(TAG, "Add data: Adding "+item+ "to " +ITEM_TABLE);
    }
}
