package com.example.fishlog;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.fishlog.DB.FishDAO;
import com.example.fishlog.DB.FishDatabase;
import com.example.fishlog.DB.TripDAO;
import com.example.fishlog.DB.TripDatabase;

import java.sql.Date;
import java.util.List;

public class LogTrip extends AppCompatActivity {
    private static String tempLocation = "";
    private static String tempStartDate = "";
    private static String tempEndDate = "";

    Button logTripCancel;
    Button logTripSave;
    Button logTripAddCatch;
    EditText logTripLocation;
    TextView logTripViewCatches;

    TripDAO myTripDAO;
    FishDAO myFishDAO;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("LogTrip created");
        setContentView(R.layout.log_trip);

        myTripDAO = Room.databaseBuilder(this, TripDatabase.class, TripDatabase.DATABASE_NAME).allowMainThreadQueries().build().TripDAO();
        myFishDAO = Room.databaseBuilder(this, FishDatabase.class, FishDatabase.DATABASE_NAME).allowMainThreadQueries().build().FishDAO();

        logTripCancel = findViewById(R.id.logTripCancel);
        logTripSave = findViewById(R.id.logTripSave);
        logTripAddCatch = findViewById(R.id.logTripAddCatch);
        logTripLocation = findViewById(R.id.logTripLocation);
        logTripViewCatches = findViewById(R.id.logTripViewCatches);

        logTripLocation.setText(tempLocation);
        logTripViewCatches.setMovementMethod(new ScrollingMovementMethod());

        List<Fish> currentFish = myFishDAO.populateCatches(Trip.classTripId+1);

        String tempText = "Catches:\n";
        for(int i = 0; i < currentFish.size(); i++) {
            String species = currentFish.get(i).getSpecies();
            float weight = currentFish.get(i).getWeight();
            int length = currentFish.get(i).getSize();
            tempText += "\t" + species + " (" + weight + " lbs. " + length + " inches)\n";
        }
        logTripViewCatches.setText(tempText);

        logTripCancel.setOnClickListener(view -> cancel());
        logTripSave.setOnClickListener(view -> save());
        logTripAddCatch.setOnClickListener(view -> addCatchToTrip());
    }
    private void cancel() {
        tempLocation = "";
        tempStartDate = "";
        tempEndDate = "";
        Trip.classTripId += 1;
        Intent intent = new Intent(LogTrip.this, MyTrips.class);
        startActivity(intent);
        finish();
    }

    private void save() {
        String location = logTripLocation.getText().toString();

        Trip newTrip = new Trip(location);
        myTripDAO.insert(newTrip);
        System.out.println("New trip created: " + location);

        tempLocation = "";
        tempStartDate = "";
        tempEndDate = "";

        Intent intent = new Intent(LogTrip.this, MyTrips.class);
        startActivity(intent);
        finish();
    }

    private void addCatchToTrip() {
        tempLocation = logTripLocation.getText().toString();
        Intent intent = new Intent(LogTrip.this, LogTripCatch.class);
        startActivity(intent);
        finish();
    }
}
