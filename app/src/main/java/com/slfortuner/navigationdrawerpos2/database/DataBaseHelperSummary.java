package com.slfortuner.navigationdrawerpos2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelperSummary extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "users_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "FIRSTNAME";
    public static final String COL3 = "LASTNAME";
    public static final String COL4 = "FAVFOOD";


    public DataBaseHelperSummary(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " FIRSTNAME TEXT, LASTNAME TEXT, FAVFOOD TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String fName, String lName, String fFood) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, fName);
        contentValues.put(COL3, lName);
        contentValues.put(COL4, fFood);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateData(String fName, String lName, String fFood) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL3, lName);

        Cursor cursor = db.rawQuery( "select * from TABLE_NAME where LASTNAME = ?", new String[] {fName} );
        if (cursor.getCount()>0) {

            long result = db.update( TABLE_NAME, contentValues, "LASTNAME=?", new String[]{fName} );

            //if date as inserted incorrectly it will return -1
            if (result == -1) {
                return false;
            } else {
                return true;
            }

        }else {
            return false;
        }
    }

    public boolean daleteData(String fName) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery( "select * from TABLE_NAME where LASTNAME = ?", new String[] {fName} );
        if (cursor.getCount()>0) {

            long result = db.delete( TABLE_NAME, "LASTNAME=?", new String[]{fName} );

            //if date as inserted incorrectly it will return -1
            if (result == -1) {
                return false;
            } else {
                return true;
            }

        }else {
            return false;
        }
    }

    //query for 1 week repeats
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public String getSum()
    {
        SQLiteDatabase database = getReadableDatabase();
        String sAmount;
        String sQuary = "select sum(FIRSTNAME) from " + TABLE_NAME;
        Cursor cursor = database.rawQuery( sQuary, null );
        if (cursor.moveToFirst()){
            sAmount = String.valueOf( cursor.getInt( 0 ) );
        }else {
            sAmount = "0";
        }
        cursor.close();
        database.close();
        return sAmount;
    }

    public String getCount()
    {
        SQLiteDatabase database = getReadableDatabase();
        String sCount;
        String scQuary = "select sum(LASTNAME) from " + TABLE_NAME;
        Cursor cursor = database.rawQuery( scQuary, null );
        if (cursor.moveToFirst()){
            sCount = String.valueOf( cursor.getInt( 0 ) );
        }else {
            sCount = "0";
        }
        cursor.close();
        database.close();
        return sCount;
    }



}
