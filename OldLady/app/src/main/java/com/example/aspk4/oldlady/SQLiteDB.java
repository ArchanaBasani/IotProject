package com.example.aspk4.oldlady;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class SQLiteDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lab3.db";
    private static final String USER_DATA = "USER_INFORMATION";
    private static final String COLUMN_1 = "EMAIL_ID";
    private static final String COLUMN_2 = "TYPE";
    private static final String COLUMN_3 = "INFORMATION";
    private static final String COLUMN_4 = "DATE_CREATED";

    public SQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("On Create");
        // Creating Table First
        db.execSQL("create table " + USER_DATA +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "EMAIL_ID TEXT,TYPE TEXT,INFORMATION TEXT, DATE_CREATED DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // On DB Upgrade, Dropping and Creating Table Again
        db.execSQL("DROP TABLE IF EXISTS "+USER_DATA);
        onCreate(db);
    }

    // Inserting data
    public boolean insertData(String emailId, String type, String information) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1,emailId.toLowerCase());
        contentValues.put(COLUMN_2,type);
        contentValues.put(COLUMN_3,information);
        // Inserting the Data, Return Type will be 'long'
        long result = db.insert(USER_DATA,null ,contentValues);
        if(result == -1) {
            //f Insert haven't happened properly, it will return a "long" value '-1'
            // as Output
            return false;
        }
        else {
            // If Insertion is Successful
            return true;
        }
    }

    // Check if Data Exist
    public Cursor checkIfTypeExistForUser(String emailId, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+USER_DATA+" where LOWER(EMAIL_ID) = '"+emailId.toLowerCase()+"'"
                +" AND TYPE = '"+type+"' ORDER BY DATE_CREATED LIMIT 1" ,null);
        return res;
    }


    // Fetch Recipe Details
    public List<String> fetchData(String type) {
        List<String> textDetList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select INFORMATION from "+USER_DATA+" WHERE TYPE='"+type+"'",null);

        if (res.getCount() != 0) {
            try {
                while (res.moveToNext()) {
                    textDetList.add(res.getString(0));
                }
            }finally {
                res.close();
            }
        }
        return textDetList;
    }

}