package com.example.joelcasillas.project2part3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by joelcasillas on 12/5/17.
 */

public class FlightDatabase extends SQLiteOpenHelper
{
    //left off here, add flights make new columsn and etc

    Database myDatabase;
    //database name
    public static final String FlightDatabaseName="flights.db";
    //table name in database
    public static final String TableName="flightsTable";
    //columns in database table
    public static final String ID_COL="ID";
    public static final String Username_COL="USERNAME";
    public static final String Password_COL="PASSWORD";
    public static final String Time_COL="TIME";

    public FlightDatabase(Context context) {
        super(context,FlightDatabaseName, null, 1);
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    }
    public String getTime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date newdate = new Date();
        return dateFormat.format(newdate);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "  + TableName + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT,TIME TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String username, String Password)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Username_COL,username);
        contentValues.put(Password_COL,Password);
        contentValues.put(Time_COL,getTime());
        long result =sqLiteDatabase.insert(TableName,null,contentValues);

        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }




    public Cursor getData()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("select * from " + TableName,null);
        return res;
    }
}
