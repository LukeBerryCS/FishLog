package com.example.fishlog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;
import androidx.room.Room;

import com.example.fishlog.DB.FishDAO;
import com.example.fishlog.DB.FishDatabase;
import com.example.fishlog.DB.UserDAO;
import com.example.fishlog.DB.UserDatabase;

@Entity(tableName = FishDatabase.FISH_TABLE)
public class LogTripCatch extends AppCompatActivity {
    Button logTripCatchCancel;
    Button logTripCatchSave;

    EditText logTripCatchSpecies;
    EditText logTripCatchWeight;
    EditText logTripCatchSize;

    FishDAO myFishDAO; //uses same FishDAO as the LogCatch class
    UserDAO myUserDAO;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("LogTripCatch created");
        setContentView(R.layout.log_trip_catch);

        myFishDAO = Room.databaseBuilder(this, FishDatabase.class, FishDatabase.DATABASE_NAME).allowMainThreadQueries().build().FishDAO();
        myUserDAO = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DATABASE_NAME).allowMainThreadQueries().build().UserDAO();

        logTripCatchSpecies = findViewById(R.id.logTripCatchSpecies);
        logTripCatchWeight = findViewById(R.id.logTripCatchWeight);
        logTripCatchSize = findViewById(R.id.logTripCatchSize);

        logTripCatchCancel = findViewById(R.id.logTripCatchCancel);
        logTripCatchSave = findViewById(R.id.logTripCatchSave);

        logTripCatchCancel.setOnClickListener(view -> cancel());
        logTripCatchSave.setOnClickListener(view -> save());
    }

    private void cancel() {
        Intent intent = new Intent(LogTripCatch.this, LogTrip.class);
        startActivity(intent);
        finish();
    }

    private void save() {
        String species = logTripCatchSpecies.getText().toString();
        String location = null;
        float weight = Float.parseFloat(logTripCatchWeight.getText().toString());
        int length = Integer.parseInt(logTripCatchSize.getText().toString());
        Fish newFish = new Fish(Trip.classTripId+1, species, weight, length);

        //Updating user stats
        User myUser = myUserDAO.findUser(MainActivity.currentUser.getUsername());
        int currentCatches = myUser.getTotalCatches() + 1;
        myUser.setTotalCatches(currentCatches);
        if(weight >= myUser.getBestWeight()) {
            myUser.setBestWeight(weight);
            myUser.setBestWeightSpecies(species);
        }
        if(length >= myUser.getBestLength()) {
            myUser.setBestLength(length);
            myUser.setBestLengthSpecies(species);
        }
        MainActivity.currentUser = myUser;
        myUserDAO.update(myUser);

        myFishDAO.insert(newFish);
        System.out.println("New trip fish created: " + Trip.classTripId+1 + ". " + species);



        Intent intent = new Intent(LogTripCatch.this, LogTrip.class);
        startActivity(intent);
        finish();
    }
}
