package com.example.fishlog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.fishlog.DB.FishDAO;
import com.example.fishlog.DB.FishDatabase;
import com.example.fishlog.DB.UserDAO;
import com.example.fishlog.DB.UserDatabase;

public class LogCatch extends AppCompatActivity {
    Button logCatchCancel;
    Button logCatchSave;

    EditText logCatchSpecies;
    EditText logCatchLocation;
    EditText logCatchWeight;
    EditText logCatchSize;

    FishDAO myFishDAO;
    UserDAO myUserDAO;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("LogCatch created");
        setContentView(R.layout.log_catch);

        myFishDAO = Room.databaseBuilder(this, FishDatabase.class, FishDatabase.DATABASE_NAME).allowMainThreadQueries().build().FishDAO();
        myUserDAO = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DATABASE_NAME).allowMainThreadQueries().build().UserDAO();

        logCatchSpecies = findViewById(R.id.logCatchSpecies);
        logCatchLocation = findViewById(R.id.logCatchLocation);
        logCatchWeight = findViewById(R.id.logCatchWeight);
        logCatchSize = findViewById(R.id.logCatchSize);

        logCatchCancel = findViewById(R.id.logCatchCancel);
        logCatchSave = findViewById(R.id.logCatchSave);

        logCatchCancel.setOnClickListener(view -> cancel());
        logCatchSave.setOnClickListener(view -> save());
    }
    private void cancel() {
        Intent intent = new Intent(LogCatch.this, MyCatches.class);
        startActivity(intent);
        finish();
    }

    private void save() {
        //Add catch to database
        String species = logCatchSpecies.getText().toString();
        String location = logCatchLocation.getText().toString();
        float weight = Float.parseFloat(logCatchWeight.getText().toString());
        int length = Integer.parseInt(logCatchSize.getText().toString());
        Fish newFish = new Fish(species, weight, length, location);

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
        System.out.println("New fish created: " + species);
        Intent intent = new Intent(LogCatch.this, MyCatches.class);
        startActivity(intent);
        finish();
    }
}
