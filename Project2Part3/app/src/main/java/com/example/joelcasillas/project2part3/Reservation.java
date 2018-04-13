package com.example.joelcasillas.project2part3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by joelcasillas on 12/10/17.
 */

public class Reservation extends AppCompatActivity implements View.OnClickListener {
    Database myDatabase;

    TextView username;
    EditText username1;
    TextView password;
    EditText password1;
    Button CancelButton;
    Button ReturntoMenu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);



        myDatabase= new Database(this);

      //  username = (TextView) findViewById(R.id.createacccounttext);
        username1 = (EditText) findViewById(R.id.reserveedittext);
       // password = (TextView) findViewById(R.id.createacccounttext2);
        password1 = (EditText) findViewById(R.id.reservationedittext2);
        CancelButton =(Button) findViewById(R.id.reserveedittext3);

        ReturntoMenu = (Button) findViewById(R.id.reserve2);
        ReturntoMenu.setOnClickListener(this);


        //calling method
        //addData();
        //TextChecker();
       addData();

    }

    public void addData()
    {
        //final Boolean validUser = myDatabase.checkLogin(username1.getText().toString());
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean WordChecker = myDatabase.WordChecker(username1.getText().toString());
                if (!WordChecker) {
                    if (TextChecker(username1.getText().toString().trim()) && TextChecker(password1.getText().toString().trim())) {

                        Toast.makeText(Reservation.this, "Usernane exists", Toast.LENGTH_LONG).show();
                        myDatabase.insertData(username1.getText().toString(), password1.getText().toString());
                    }
                    else
                    {
                        Toast.makeText(Reservation.this, "No Reservation with that username", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(Reservation.this, "No Reservation with that username", Toast.LENGTH_LONG).show();
                }
            }

 //Toast.makeText(Reservation.this, "No Reservation with that username", Toast.LENGTH_LONG).show();

        });
    }

    public boolean TextChecker(final String password) {

        Pattern ThePattern;
        Matcher Matchcheck;
        //Pattern that makes sure the username and password Have capital and a number in it
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).*$";

        ThePattern = Pattern.compile(PASSWORD_PATTERN);
        Matchcheck = ThePattern.matcher(password);

        return Matchcheck.matches();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reserve2:
                Intent result = new Intent(getApplicationContext(), AirlineTicketReservationSystem.class);
                startActivity(result);
                break;

        }
    }
}
