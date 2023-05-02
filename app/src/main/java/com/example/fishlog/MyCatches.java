package com.example.fishlog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MyCatches extends AppCompatActivity {
    Button myCatchesBack;
    Button myCatchesLogCatch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MyCatches created");
        setContentView(R.layout.my_catches);

        myCatchesBack = findViewById(R.id.myCatchesBack);
        myCatchesLogCatch = findViewById(R.id.myCatchesLogCatch);

        myCatchesBack.setOnClickListener(view -> back());
        myCatchesLogCatch.setOnClickListener(view -> logCatch());
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

}
