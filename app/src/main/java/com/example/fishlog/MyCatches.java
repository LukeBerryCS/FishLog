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

import java.util.ArrayList;
import java.util.List;

public class MyCatches extends AppCompatActivity {
    Button myCatchesBack;
    Button myCatchesLogCatch;
    TextView myCatchesList;

    FishDAO myFishDAO;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MyCatches created");
        setContentView(R.layout.my_catches);

        myFishDAO = Room.databaseBuilder(this, FishDatabase.class, FishDatabase.DATABASE_NAME).allowMainThreadQueries().build().FishDAO();

        myCatchesBack = findViewById(R.id.myCatchesBack);
        myCatchesLogCatch = findViewById(R.id.myCatchesLogCatch);
        myCatchesList = findViewById(R.id.myCatchesCatchList);

        showCatches();
        System.out.println(MainActivity.currentUserId);

        myCatchesBack.setOnClickListener(view -> back());
        myCatchesLogCatch.setOnClickListener(view -> logCatch());
        myCatchesList.setMovementMethod(new ScrollingMovementMethod());

    }

    private void back() {
        Intent intent = new Intent(MyCatches.this, Index.class);
        startActivity(intent);
        finish();
    }

    private void logCatch() {
        Intent intent = new Intent(MyCatches.this, LogCatch.class);
        startActivity(intent);
        finish();
    }

    private void showCatches() {
        List<Fish> allCatches = myFishDAO.getMyFish(MainActivity.currentUserId);
        String catchesText = "";
        for(int i = 0; i < allCatches.size(); i++) {
            String species = allCatches.get(i).getSpecies();
            String location = allCatches.get(i).getLocation();
            int length = allCatches.get(i).getSize();
            float weight = allCatches.get(i).getWeight();
            catchesText = catchesText + location + ": " + species + " (" + weight + " lbs. " + length + " inches)\n\n";
        }
        myCatchesList.setText(catchesText);
    }
}
