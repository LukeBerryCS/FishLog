package com.example.fishlog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Index extends AppCompatActivity {
    TextView indexLogout;
    Button indexMyCatches;
    Button indexMyTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        System.out.println("Index created");
        TextView loginInfoTemp = (TextView) findViewById(R.id.loginInfoTemp);
        String tempText = "User:  " + MainActivity.currentUser.getUsername();
        loginInfoTemp.setText(tempText);

        indexLogout = findViewById(R.id.indexLogoutButton);
        indexMyCatches = findViewById(R.id.indexMyCatches);
        indexMyTrips = findViewById(R.id.indexMyTrips);

        indexLogout.setOnClickListener(view -> logout());
        indexMyCatches.setOnClickListener(view -> indexToCatches());
        indexMyTrips.setOnClickListener(view -> indexToTrips());
    }
    private void logout() {
        Intent intent = new Intent(Index.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void indexToCatches() {
        Intent intent = new Intent(Index.this, MyCatches.class);
        startActivity(intent);
        finish();
    }

    private void indexToTrips() {
        Intent intent = new Intent(Index.this, MyTrips.class);
        startActivity(intent);
        finish();
    }
}
