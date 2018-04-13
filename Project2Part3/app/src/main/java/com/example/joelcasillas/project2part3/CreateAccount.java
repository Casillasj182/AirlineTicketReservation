package com.example.joelcasillas.project2part3;

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


public class CreateAccount  extends AppCompatActivity implements View.OnClickListener {
    Database myDatabase;

    TextView username;
    EditText username1;
    TextView password;
    EditText password1;
    Button CreateAccountButton;
    Button ReturntoMenu;



    //When the app is started, itll execute everything in my OnCreaye method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);



        myDatabase= new Database(this);

        username = (TextView) findViewById(R.id.createacccounttext);
        username1 = (EditText) findViewById(R.id.createaccountedittext);
        password = (TextView) findViewById(R.id.createacccounttext2);
        password1 = (EditText) findViewById(R.id.createaccountedittext2);
        CreateAccountButton =(Button) findViewById(R.id.createaccountedittext3);

        ReturntoMenu = (Button) findViewById(R.id.button2);
        ReturntoMenu.setOnClickListener(this);

        //calling method
        addData();
        //TextChecker();
        //check();

    }

    //inserts the data from the buttons and lets users know that there account was created succesfully.
    public void addData()
    {

        //final Boolean validUser = myDatabase.checkLogin(username1.getText().toString());
        CreateAccountButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean WordChecker = myDatabase.WordChecker(username1.getText().toString());
                if (!WordChecker) {
                    if (TextChecker(username1.getText().toString().trim()) && TextChecker(password1.getText().toString().trim())) {

                        Toast.makeText(CreateAccount.this, "Account" + username1.getText().toString() + " " +"Has Been Created Successfully", Toast.LENGTH_LONG).show();
                        myDatabase.insertData(username1.getText().toString(), password1.getText().toString());
                    }
                    else
                    {
                        Toast.makeText(CreateAccount.this, "Account couldn't be made", Toast.LENGTH_LONG).show();
                    }
                }
                    else
                        {
                            Toast.makeText(CreateAccount.this, "Username"  +" " + username1.getText().toString() +" " +"already exists", Toast.LENGTH_LONG).show();
                    }

                }



        });
    }


    //Method to check if the password/username is acceptable
    public boolean TextChecker(final String password) {

        Pattern ThePattern;

        Matcher Matchcheck;
        //Pattern that makes sure the username and password Have capital and a number in it
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).*$";

        ThePattern = Pattern.compile(PASSWORD_PATTERN);
        Matchcheck = ThePattern.matcher(password);

        return Matchcheck.matches();
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



    // im not sure what this is
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button2:
                Intent result = new Intent(getApplicationContext(), AirlineTicketReservationSystem.class);
                startActivity(result);
                break;

        }
    }
}
