package com.example.fishlog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LogTrip extends AppCompatActivity {
    Button logTripCancel;
    Button logTripSave;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("LogTrip created");
        setContentView(R.layout.log_trip);

        logTripCancel = findViewById(R.id.logTripCancel);
        logTripSave = findViewById(R.id.logTripSave);

        logTripCancel.setOnClickListener(view -> cancel());
        logTripSave.setOnClickListener(view -> save());
    }
    private void cancel() {
        Intent intent = new Intent(LogTrip.this, MyCatches.class);
        startActivity(intent);
        finish();
    }

    private void save() {
        Intent intent = new Intent(LogTrip.this, MyCatches.class);
        startActivity(intent);
        finish();
    }
}
