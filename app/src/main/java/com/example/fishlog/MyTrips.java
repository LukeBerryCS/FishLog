package com.example.fishlog;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.fishlog.DB.FishDAO;
import com.example.fishlog.DB.FishDatabase;
import com.example.fishlog.DB.TripDAO;
import com.example.fishlog.DB.TripDatabase;

import java.util.List;

public class MyTrips extends AppCompatActivity {
    Button myTripsBack;
    Button myTripsLogTrip;
    TextView myTripsViewTrip;

    FishDAO myFishDAO;
    TripDAO myTripDAO;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MyTrips created");
        setContentView(R.layout.my_trips);

        myFishDAO = Room.databaseBuilder(this, FishDatabase.class, FishDatabase.DATABASE_NAME).allowMainThreadQueries().build().FishDAO();
        myTripDAO = Room.databaseBuilder(this, TripDatabase.class, TripDatabase.DATABASE_NAME).allowMainThreadQueries().build().TripDAO();


        myTripsBack = findViewById(R.id.myTripsBack);
        myTripsLogTrip = findViewById(R.id.myTripsLogTrip);
        myTripsViewTrip = findViewById(R.id.myTripsViewTrips);

        myTripsBack.setOnClickListener(view -> back());
        myTripsLogTrip.setOnClickListener(view -> logTrip());
        myTripsViewTrip.setMovementMethod(new ScrollingMovementMethod());

        showTrips();
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

    private void showTrips() {
        List<Trip> myTrips = myTripDAO.findTrips(MainActivity.currentUserId);
        String tempText = "";
        for(int i = 0; i < myTrips.size(); i++) {
            System.out.println("i: " + i + " myTrips.size(): " + myTrips.size());
            tempText += myTrips.get(i).getLocation() + "\n";
            List<Fish> tripFish = null;
            tripFish = myFishDAO.populateCatches(myTrips.get(i).getTripId());
            for(int j = 0; j < tripFish.size(); j++) {
                System.out.println("j: " + j + " tripFish.size(): " + tripFish.size());
                String species = tripFish.get(j).getSpecies();
                float weight = tripFish.get(j).getWeight();
                int length = tripFish.get(j).getSize();
                tempText += "\t" + species + " (" + weight + " lbs. " + length + " inches)\n";
            }
            tempText += "\n";
        }
        myTripsViewTrip.setText(tempText);
        System.out.println("showTrips()");
    }

}