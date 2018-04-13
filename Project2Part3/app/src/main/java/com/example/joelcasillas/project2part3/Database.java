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
 * Created by joelcasillas on 12/2/17.
 */

public class Database extends SQLiteOpenHelper {

    Database myDatabase;
    //database name
    public static final String DatabaseName = "users.db";
    //table name in database
    public static final String TableName = "usersTable";
    //columns in database table
    public static final String ID_COL = "ID";
    public static final String Username_COL = "USERNAME";
    public static final String Password_COL = "PASSWORD";
    public static final String Time_COL = "TIME";

    //FOR 2ND TABLE FLIGHTS
    public static final String TableName2 = "Flighttable";
    public static final String FID = "ID";
    public static final String FNUM = "FLIGHTNUMBER";
    public static final String FDEP = "DEPARTURE";
    public static final String FARR = "ARRIVAL";
    public static final String FTIM = "DEPARTURETIME";
    public static final String FCAP = "CAPACITY";
    public static final String FPRICE = "PRICE";


    public Database(Context context) {
        super(context, DatabaseName, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // SQLiteDatabase sqLiteDatabase2=this.getWritableDatabase();
    }

    //check later to see if its the correct patter
    public String Time()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat
                (
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date newdate = new Date();

        return dateFormat.format(newdate);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TableName + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT,TIME TEXT)");
        sqLiteDatabase.execSQL("create table " + TableName2 + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT,FLIGHTNUMBER TEXT,DEPARTURE TEXT,ARRIVAL TEXT,DEPARTURETIME TEXT,CAPACITY TEXT,PRICE TEXT)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableName);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableName2);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String username, String Password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Username_COL, username);
        contentValues.put(Password_COL, Password);
        contentValues.put(Time_COL, Time());

        long result = sqLiteDatabase.insert(TableName, null, contentValues);
        //long result2 =sqLiteDatabase.insert(TableName2,null,contentValues);

        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
//For flightsinsert intodatabase
    public boolean insertData2(String Flightnumber, String departure, String arrival, String departuretime, String capacity, String price) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // contentValues.put(FID,ID);
        contentValues.put(FNUM, Flightnumber);
        contentValues.put(FDEP, departure);
        contentValues.put(FARR, arrival);
        contentValues.put(FTIM, departuretime);
        contentValues.put(FCAP, capacity);
        contentValues.put(FPRICE, price);

        long result2 = sqLiteDatabase.insert(TableName2, null, contentValues);
        if (result2 == -1) {
            return false;
        } else {
            return true;
        }
    }

    /*
    public boolean checkLogin(String username) {
        SQLiteDatabase db = myDatabase.getWritableDatabase();


        Cursor c = db.rawQuery("SELECT * FROM TableName WHERE USERNAME=?" ,new String[]{username});

        if(c.getCount() == 0) {

            return false;
        } else {

            return true;
        }
    }
*/

    public boolean WordChecker(String USERNAME) {
        SQLiteDatabase db = this.getWritableDatabase();

        String request = "SELECT * FROM usersTable WHERE USERNAME = ?";

        Cursor result = db.rawQuery(request, new String[]{USERNAME});

        if (result.getCount() == 0)
            return false;
        else
            return true;
    }

    public boolean FlightChecker(String flightnumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        String request = "SELECT * FROM Flighttable WHERE FLIGHTNUMBER = ?";

        Cursor result = db.rawQuery(request, new String[]{flightnumber});

        if (result.getCount() == 0)
            return false;
        else
            return true;
    }
/*
    public boolean DisplayFlights(String depnumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        //String request = "SELECT * FROM Flighttable WHERE DEPARTURE LIKE %?%";
        String max = "SELECT * FROM "
                + TableName2 + " where DEPART =?" + depnumber;
        Cursor result = db.rawQuery(max, null);

        if (result.getCount() == 0)
            return false;
        else
            return true;
    }
    */

    public Cursor DisplayFlights2(String dep) {
        SQLiteDatabase db = this.getWritableDatabase();

        String max = "SELECT * FROM "
                + TableName2 + " where DEPART =?" + dep + " like '%" + dep
                + "%'";
        Cursor result = db.rawQuery(max, null);

        return result;
    }

    public Cursor getData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TableName, null);

        return res;
    }

    public Cursor getData2() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TableName2, null);

        return res;
    }

    public Cursor TicketCheck(String tickets)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String check = "select * from " + TableName2 + " where CAPACITY " + "=?";
        Cursor res = sqLiteDatabase.rawQuery(check, new String[]{tickets});


        return res;
    }


    public Cursor getData3(String dep, String arr)

    {
        String max = "SELECT * FROM "
                + TableName2 + " where " + FDEP + "=?" + " AND " + FARR + "=?";

        //String max="SELECT * FROM "
        //+ TableName2 + "like'" +  "'%" +dep +"%'" + " , " + "'%" +arr + "%'";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String request = "SELECT * FROM Flighttable WHERE DEPARTURE  = ?";
        Cursor res = sqLiteDatabase.rawQuery(max, new String[]{dep, arr});

        return res;
    }

}
