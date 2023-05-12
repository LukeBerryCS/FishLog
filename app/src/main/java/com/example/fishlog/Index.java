package com.example.fishlog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Index extends AppCompatActivity {
    TextView indexLogout;
    Button indexMyCatches;
    Button indexMyTrips;
    static Button indexAdminTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        System.out.println("Index created");
        TextView loginInfoTemp = (TextView) findViewById(R.id.loginInfoTemp);
        String tempText = "User:  " + MainActivity.currentUser.getUsername();
        String statText;
        if(MainActivity.currentUser.getTotalCatches() >= 1) {
            statText = "\nTotal Catches: " + MainActivity.currentUser.getTotalCatches() + "\nPB (weight): " + MainActivity.currentUser.getBestWeightSpecies() + " (" + MainActivity.currentUser.getBestWeight() + " lbs.)" + "\nPB (length): " + MainActivity.currentUser.getBestLengthSpecies() + " (" + MainActivity.currentUser.getBestLength() + " in.)";
        } else {
            statText = "";
        }
        loginInfoTemp.setText(tempText + statText);



        indexLogout = findViewById(R.id.indexLogoutButton);
        indexMyCatches = findViewById(R.id.indexMyCatches);
        indexMyTrips = findViewById(R.id.indexMyTrips);
   
        indexAdminTools = findViewById(R.id.indexAdminTools);

        if(!MainActivity.currentUser.isAdmin()) {
            System.out.println("Current user is not admin");
            indexAdminTools.setVisibility(View.INVISIBLE);
        } else {
            System.out.println("Current user is admin");
            indexAdminTools.setVisibility(View.VISIBLE);
        }

        indexLogout.setOnClickListener(view -> logout());
        indexMyCatches.setOnClickListener(view -> indexToCatches());
        indexMyTrips.setOnClickListener(view -> indexToTrips());
        indexAdminTools.setOnClickListener(view -> indexToAdminTools());
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
    private void indexToAdminTools() {
        Intent intent = new Intent(Index.this, AdminTools.class);
        startActivity(intent);
        finish();
    }
}
