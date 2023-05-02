package com.example.fishlog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MyCatches extends AppCompatActivity {
    Button myCatchesBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MyCatches created");
        setContentView(R.layout.my_catches);

        myCatchesBack = findViewById(R.id.myCatchesBack);
        myCatchesBack.setOnClickListener(view -> back());
    }

    private void back() {
        Intent intent = new Intent(MyCatches.this, Index.class);
        startActivity(intent);
        finish();
    }

}
