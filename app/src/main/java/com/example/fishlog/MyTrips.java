package com.example.fishlog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MyTrips extends AppCompatActivity {
    Button myTripsBack;
    Button myTripsLogTrip;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MyTrips created");
        setContentView(R.layout.my_trips);

        myTripsBack = findViewById(R.id.myTripsBack);
        myTripsLogTrip = findViewById(R.id.myTripsLogTrip);

        myTripsBack.setOnClickListener(view -> back());
        myTripsLogTrip.setOnClickListener(view -> logTrip());
    }

    private void back() {
        Intent intent = new Intent(MyTrips.this, Index.class);
        startActivity(intent);
        finish();
    }

    private void logTrip() {
        Intent intent = new Intent(MyTrips.this, LogTrip.class);
        startActivity(intent);
        finish();
    }

}