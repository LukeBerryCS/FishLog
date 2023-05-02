package com.example.fishlog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalBest extends AppCompatActivity {
    Button personalBestBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("PersonalBest created");
        setContentView(R.layout.personal_best);

        personalBestBack = findViewById(R.id.personalBestBack);
        personalBestBack.setOnClickListener(view -> back());
    }

    private void back() {
        Intent intent = new Intent(PersonalBest.this, Index.class);
        startActivity(intent);
        finish();
    }

}