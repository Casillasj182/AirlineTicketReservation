package com.example.joelcasillas.project2part3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.DialogInterface;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.widget.Toast;

/**
 * Created by joelcasillas on 12/2/17.
 */

public class ManageSystem extends AppCompatActivity implements View.OnClickListener {

    Database myDatabase;

    TextView username2;
    EditText username3;
    TextView password2;
    EditText password3;
    Button VerifyButton;
    private Button ReturnMenu;


    //When the app is started, itll execute everything in my OnCreaye method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_system);

        myDatabase= new Database(this);

        username2 = (TextView) findViewById(R.id.managetext);
        username3 = (EditText) findViewById(R.id.manageeditText);
        password2 = (TextView) findViewById(R.id.managetext2);
        password3 = (EditText) findViewById(R.id.manageText2);
        VerifyButton =(Button) findViewById(R.id.manageButton);
        //VerifyButton.setOnClickListener(this);

       ReturnMenu = (Button) findViewById(R.id.button);
        ReturnMenu.setOnClickListener(this);

        //calling method
        //addData();
        viewAll();
        //getTime();
    }
/*
    public String getTime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date newdate = new Date();
        return dateFormat.format(newdate);
    }
    */
    //inserts the data from the buttons and lets users know that there account was created succesfully.
   public void viewAll()
   {
       VerifyButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               myDatabase.getData();
               Cursor res= myDatabase.getData();
               //res=myDatabase.getData2();
               StringBuffer buff = new StringBuffer();
              while(username3.getText().toString().equals("admin2")&& password3.getText().toString().equals("admin2")&& res.moveToNext())
               {
                   //to only show one at a time instead of all of them
                   //buff.delete(0,buff.length());
                   buff.append("Transaction Type: New Account"+"\n");
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

    // im not sure what this is
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View view) {
        //button
        switch (view.getId())
        {
            case R.id.button:
                Intent result = new Intent(getApplicationContext(), AirlineTicketReservationSystem.class);
                startActivity(result);
                break;

        }

    }
}
