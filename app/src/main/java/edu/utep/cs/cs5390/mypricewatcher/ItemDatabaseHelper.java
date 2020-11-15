package edu.utep.cs.cs5390.mypricewatcher;

// Airam Martinez & Mildred Brito
//Database helper to store or remove Items

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ItemDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String ITEM_TABLE = "item_table";
    private static final String COL1 = "Name";
    private static final String COL2 = "URL";


    public ItemDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, ITEM_TABLE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + ITEM_TABLE + "("
                + COL2 + " TEXT PRIMARY KEY, "
                + COL1 + " TEXT" + ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE);
        onCreate(db);
    }

    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item);

        Log.d(TAG, "Add data: Adding "+item+ "to " +ITEM_TABLE);

        //Notifies if data was inserted correctly
        long result = db.insert(ITEM_TABLE, null, contentValues);
        //If data is inserted correctly, it will return -1
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }
}
