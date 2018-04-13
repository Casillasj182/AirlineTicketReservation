package com.example.joelcasillas.project2part3;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joelcasillas on 12/4/17.
 */



public class ReserveSeat extends AppCompatActivity  implements View.OnClickListener {

    Database myDatabase;
    TextView depart2;
    EditText depart;
    TextView arrival2;
    TextView tickets2;
    EditText arrival;
    EditText tickets;
    private Button search;
    private Button ReturnMenu;
    private Button Reserve;
    private Button Verify;


    Toolbar toolbar;
    ListView listView;




    ArrayList <String> list=new ArrayList<String>();
ArrayAdapter<String> adapter;



    //When the app is started, itll execute everything in my OnCreaye method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_seat);


        myDatabase = new Database(this);
        depart2 = (TextView) findViewById(R.id.reserve1);
        depart = (EditText) findViewById(R.id.edittext);
        arrival2 = (TextView) findViewById(R.id.reserve2);
        arrival = (EditText) findViewById(R.id.edittext2);
        tickets2 = (TextView) findViewById(R.id.reserve3);
        tickets = (EditText) findViewById(R.id.edittext3);
        search = (Button) findViewById(R.id.button3);
        Reserve= (Button) findViewById(R.id.reserve);


        //VerifyButton.setOnClickListener(this);

        ReturnMenu = (Button) findViewById(R.id.button4);
        ReturnMenu.setOnClickListener(this);
//        Reserve.setOnClickListener(this);
        search.setOnClickListener(this);
        SimpleCursorAdapter dataAdapter;

      //  DisplayFlights();
        //TicketChecker();
        viewAvailableFlights();
//        populateListView();
        //GetList();
//displayList();

    }

/*
    public void displayList(){
        //myDatabase.InsertValues();
        Cursor cursor=myDatabase.getData3(depart.getText().toString(),arrival.getText().toString());
        String from [] = new String[]{myDatabase.FID};
        int to [] = new int[] {R.id.textView1};
        dataAdapter = new SimpleCursorAdapter(this, R.layout.row_item, cursor, from, to, 0);
        db.close();

        ListView lv = getListView();
        lv.setAdapter(dataAdapter);



ArrayAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.reserve_seat_selection,
R.id.reserveSeatTitle, sbFlight);

    }
    */


/*
    void DisplayFlights() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean FlightChecker = myDatabase.DisplayFlights(depart.getText().toString());
                if (FlightChecker) {

                    Toast.makeText(ReserveSeat.this, "There is a flight that matches that", Toast.LENGTH_LONG).show();
                    // myDatabase.insertData(username1.getText().toString(), password1.getText().toString());
                } else {
                    Toast.makeText(ReserveSeat.this, "No Flight like that exists", Toast.LENGTH_LONG).show();
                }
            }


        });
    }
    */

public boolean TicketChecker(String Count)
{
    Cursor res = myDatabase.TicketCheck(tickets.getText().toString());
    int foo = Integer.parseInt(Count);
    String why=res.getString(5);
    int foo2 = Integer.parseInt(why);
    if(foo2>foo) //if5>2
    {
        return true;
    }
    else
    {
        return false;
    }



}

    public void viewAll() //stoppded workinh
    {
        Verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabase.getData();
                Cursor res= myDatabase.getData();
                //res=myDatabase.getData2();
                StringBuffer buff = new StringBuffer();
                while(res.moveToNext())
                {
                    //to only show one at a time instead of all of them
                    //buff.delete(0,buff.length());
                    buff.append("Transaction Type: Canceled"+"\n");
                    buff.append("Username:" + res.getString(1)+ "" + "\n");
                    // buff.append("Username:" + res.getString(1)+ "" + "\n");
                    //getTime();
                    buff.append("Time:" + res.getString(3) + "" + "\n");

                    //buff.append("Password:" + res.getString(2) + "" + "\n");
                    //buff.append("DateTime:"+ )


                    //Show("yay","it works  ");
                }
                ShowMessage("",buff.toString());
             /*
                   StringBuffer buff = new StringBuffer();

                       buff.append("Transaction Type: New Account" + res.getString(0) + "" + "\n");
                       buff.append("Username:" + username3.getText().toString()+ "" + "\n");
                       buff.append("Password:" + password3.getText().toString() + "" + "\n");
                       Show("Data",buff.toString());
*/
                //Show("error","it works sike ");
            }

        });

    }



    public void viewAvailableFlights() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabase.getData3(depart.getText().toString(),arrival.getText().toString());
                Cursor res = myDatabase.getData3(depart.getText().toString(),arrival.getText().toString());
                StringBuffer buff = new StringBuffer();
                //while (res.moveToNext()) {
                    //to only show one at a time instead of all of them
                    //buff.delete(0,buff.length());
                    Cursor Availability = myDatabase.getData3(depart.getText().toString(),arrival.getText().toString());
              // boolean Availability2 = TicketChecker(tickets.getText().toString());
                    //boolean SeatCount=myDatabase.CountChecker(tickets.getText().toString());
                    if (Availability.getCount() == 0) {




                            //Show("Doesnt Exist", buff.toString());
                            Toast.makeText(ReserveSeat.this, "That flight doesn't exist", Toast.LENGTH_LONG).show();
                            //getTime();
                            // buff.append("Time:" + res.getString(3) + "" + "\n");

                            //buff.append("Password:" + res.getString(2) + "" + "\n");
                            //buff.append("DateTime:"+ )

                    }
                    else {
                            while (res.moveToNext()) {
                                Toast.makeText(ReserveSeat.this, "FlightNumber:" + res.getString(1) + " " + "exists", Toast.LENGTH_LONG).show();
                                // myDatabase.DisplayFlights(false);
                                //buff.append("FlightNumber:" + res.getString(2) + "\"doesn't exist\"" + "\n");
                                ShowMessage("That flight exists", buff.toString());
                                //buff.append("FlightNumber:" + res.getString(1) + " " + "exists" + "\n");

                             }
                        }




                    //Toast.makeText(ReserveSeat.this, "That flight doesn't exist", Toast.LENGTH_LONG).show();
                    //Show("Doesnt Exist", buff.toString());
             /*
                   StringBuffer buff = new StringBuffer();

                       buff.append("Transaction Type: New Account" + res.getString(0) + "" + "\n");
                       buff.append("Username:" + username3.getText().toString()+ "" + "\n");
                       buff.append("Password:" + password3.getText().toString() + "" + "\n");
                       Show("Data",buff.toString());
*/
                    //Show("error","it works sike ");
                }
                //Toast.makeText(ReserveSeat.this, "That flight doesn't exist", Toast.LENGTH_LONG).show();


        })

    ;}

            public void ShowMessage(String Title, String Message) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false);
                builder.setTitle(Title);
                builder.setMessage(Message);

                builder.setPositiveButton("Add flight",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //add intent to add flights;
                                Intent result = new Intent(getApplicationContext(), AddFlights.class);
                                startActivity(result);
                            }
                        });

                builder.setNegativeButton("EXIT",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
               // Cursor res = myDatabase.getData3(depart.getText().toString(),arrival.getText().toString());

                builder.show();

            }






    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.button4:
                Intent result = new Intent(getApplicationContext(),AirlineTicketReservationSystem.class);
                startActivity(result);
                break;






        }

    }


}
