package com.example.fishlog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MyTrips extends AppCompatActivity {
    Button myTripsBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MyTrips created");
        setContentView(R.layout.my_trips);

        myTripsBack = findViewById(R.id.myTripsBack);
        myTripsBack.setOnClickListener(view -> back());
    }

    private void back() {
        Intent intent = new Intent(MyTrips.this, Index.class);
        startActivity(intent);
        finish();
    }

}