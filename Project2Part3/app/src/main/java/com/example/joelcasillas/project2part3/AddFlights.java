package com.example.joelcasillas.project2part3;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by joelcasillas on 12/5/17.
 */

public class AddFlights extends AppCompatActivity implements View.OnClickListener
{

   private TextView fnum,fnum2,fnum3,fnum4,fnum5,fnum6;
   private EditText ftext,ftext2,ftext3,ftext4,ftext5,ftext6,ftext7;
    private Button menu;
    private Button conbutton,view;
    Database myDatabase;


    //When the app is started, itll execute everything in my OnCreaye method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_flights);


        myDatabase= new Database(this);

        //fnum = (TextView) findViewById(R.id.fnum);
        ftext = (EditText) findViewById(R.id.ftext);
        //fnum2 = (TextView) findViewById(R.id.fnum2);
        ftext2 = (EditText) findViewById(R.id.ftext2);
        //fnum3=(TextView) findViewById(R.id.fnum3);
        ftext3=(EditText) findViewById(R.id.ftext3);
       // fnum4=(TextView) findViewById(R.id.fnum3);
        ftext4=(EditText) findViewById(R.id.ftext4);
        //fnum5=(TextView) findViewById(R.id.fnum3);
        ftext5=(EditText) findViewById(R.id.ftext5);
        //fnum6=(TextView) findViewById(R.id.fnum3);
        ftext6=(EditText) findViewById(R.id.ftext6);
        //ftext7=(EditText) findViewById(R.id.ftext7);

        menu =(Button) findViewById(R.id.menu);
        //VerifyButton.setOnClickListener(this);

        conbutton = (Button) findViewById(R.id.confirmbutton);
       conbutton.setOnClickListener(this);
       menu.setOnClickListener(this);
        view =(Button) findViewById(R.id.view);
        view.setOnClickListener(this);
        addData2();
        viewAll();

    }

    public void addData2()
    {
        //final Boolean validUser = myDatabase.checkLogin(username1.getText().toString());
        conbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valid=myDatabase.FlightChecker(ftext.getText().toString());
                if(!valid) {

                       myDatabase.insertData2(ftext.getText().toString(), ftext2.getText().toString(), ftext3.getText().toString(),ftext4.getText().toString(),ftext5.getText().toString(),ftext6.getText().toString());

                        Toast.makeText(AddFlights.this, "Flight" + " " +ftext.getText().toString() + " " + "has been added Successfully", Toast.LENGTH_LONG).show();
                        //myDatabase.insertData2(ftext.getText().toString(), ftext2.getText().toString(), ftext3.getText().toString(),ftext4.getText().toString(),ftext5.getText().toString(),ftext6.getText().toString());
                    }


                else
                {
                    Toast.makeText(AddFlights.this, "Invalid! Flight" + " " +ftext.getText().toString() +" " + "already exists", Toast.LENGTH_LONG).show();
                }

            }

        });
    }
    public void ShowMessage(String Title, String Message)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
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
        builder.show();

    }
    public void viewAll()
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabase.getData2();
                Cursor res= myDatabase.getData2();
                StringBuffer buff = new StringBuffer();
                while( res.moveToNext())
                {
                    //to only show one at a time instead of all of them
                    //buff.delete(0,buff.length());
                    buff.append("Transaction Type: New Flight"+"\n");
                    buff.append("FLight Number:" + res.getString(1)+ "" + "\n");
                    //getTime();
                    buff.append("Departure City:" + res.getString(2) + "" + "\n");
                    buff.append("Arrival City:" + res.getString(3)+ "" + "\n");
                    buff.append("Departure Time:" + res.getString(4)+ "" + "\n");
                    buff.append("Flight Capacity:" + res.getString(5)+ "" + "\n");
                    buff.append("Price:" + res.getString(6)+ "" + "\n");

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



    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.menu:
                Intent result2 = new Intent(getApplicationContext(),AirlineTicketReservationSystem.class);
                startActivity(result2);
                break;

        }

    }

}
