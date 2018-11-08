package com.example.melvil.bondi;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Position.db";
    public static final String TABLE_NAME = "position_table";
    public static final String COL_1 = "id";
    public static final String COL_2 = "latitude";
    public static final String COL_3 = "longitude";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_2+" ,"+COL_3+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData( String lat, String lng ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, lat );
        contentValues.put(COL_3, lng );
        long result = db.insert(TABLE_NAME, null, contentValues );
        if (result == -1) {
            return false;
        }else{
            return true;
        }
    }
}
