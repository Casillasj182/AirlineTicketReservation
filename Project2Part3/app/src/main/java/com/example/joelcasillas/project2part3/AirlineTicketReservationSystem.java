package com.example.joelcasillas.project2part3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class AirlineTicketReservationSystem extends Activity
    implements OnClickListener
{
    Database myDatabase;

    private Button createAccount;
    private  Button verifyAccount;
    private  Button reserve;
    private Button Cancel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airline_ticket_reservation_system);
        myDatabase= new Database(this);


        createAccount = (Button) findViewById(R.id.CreateAccount);
        createAccount.setOnClickListener(this);

        verifyAccount = (Button) findViewById(R.id.ManageSystem);
        verifyAccount.setOnClickListener(this);

        Cancel = (Button) findViewById(R.id.CancelReservation);
        Cancel.setOnClickListener(this);

        reserve = (Button) findViewById(R.id.ReserveSeat);
        reserve.setOnClickListener(this);






    }

    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.CreateAccount:
                Intent result = new Intent(getApplicationContext(), CreateAccount.class);
                startActivity(result);
                break;
            case R.id.ManageSystem:
                Intent result2 = new Intent(getApplicationContext(), ManageSystem.class);
                startActivity(result2);
                break;
            case R.id.ReserveSeat:
                Intent result3 = new Intent(getApplicationContext(), ReserveSeat.class);
                startActivity(result3);
                break;
            case R.id.CancelReservation:
                Intent result4 = new Intent(getApplicationContext(), Reservation.class);
                startActivity(result4);
                break;



        }
    }






}
