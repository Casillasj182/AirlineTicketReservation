package com.example.joelcasillas.project2part3;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View.OnClickListener;
import 	java.util.regex.Pattern;
import java.util.regex.Matcher;
import android.database.sqlite.SQLiteDatabase;

import java.util.regex.Pattern;
import android.support.v7.widget.Toolbar;


/**
 * Created by joelcasillas on 12/10/17.
 */

public class flightList extends AppCompatActivity implements OnClickListener, AdapterView.OnItemClickListener {

    Database myDatabase;
    Toolbar toolbar;
    private String total;

    ListView listView;
    private String departure_string;
    private String arrival_string;
    Button ReserveButton;


    //When the app is started, itll execute everything in my OnCreaye method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_list);
        Bundle extras = getIntent().getExtras();

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle(getResources().getString(R.string.app_name));
        listView = (ListView) findViewById(R.id.listview);
        ReserveButton =(Button) findViewById(R.id.button5);



ReserveButton.setOnClickListener(this);
        departure_string = extras.getString(Database.FARR);
        arrival_string = extras.getString(Database.FARR);


        // ArrayAdapter<String> mAdapter=new ArrayAdapter<String>(flightList.this,android.R.layout.simple_list_item_1,getResources(),)
        ArrayList<String> listData = new ArrayList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Intent intent=new Intent(flightList.this,SecondActivity.class);
                //  intent.putExtra("Flight Number",listView.getItemAtPosition(i).toString());
                //startActivity(intent);
            }
        });
        //listView.setAdapter();


        // ReturntoMenu.setOnClickListener(this);

        //calling method
        //addData();
        //TextChecker();
        //check();
        //pop
      List();

    }

//still not functioning
    public void List() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        Cursor res = myDatabase.getData3(myDatabase.FDEP, myDatabase.FARR);

        ArrayList<String> listData = new ArrayList();


        while (res.moveToNext())
        {
            //grabbing the information
            res.getString(1);
            res.getString(4);
            res.getString(5);
            listData.add(this.total);
        }
        //this.listView.setAdapter(new ArrayAdapter(this, R.layout.flight_list, listData));

//still need to debug and find the issue of the button
        this.listView.setOnItemClickListener(this);
    }

    //inserts the data from the buttons and lets users know that there account was created succesfully.


    //Method to check if the password/username is acceptable
    public boolean TextChecker(final String password) {

        Pattern ThePattern;
        Matcher Matchcheck;
        //Pattern that makes sure the username and password Have capital and a number in it, not sure it works 100%, check leter reminder
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).*$";

        ThePattern = Pattern.compile(PASSWORD_PATTERN);
        Matchcheck = ThePattern.matcher(password);

        return Matchcheck.matches();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reserve:
                List();
                break;

        }


    /*
    public long insertPlayer(String Username, String Password)
    {
        ContentValues values = new ContentValues();
        values.put("USERNAME", Username);
        values.put("PASSWORD", Password);


        //return myDatabase.insertWithOnConflict(TableName, null, values, SQLiteDatabase.CONFLICT_IGNORE);

    }
*/

    }
}
