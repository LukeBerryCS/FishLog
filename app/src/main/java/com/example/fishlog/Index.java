package com.example.fishlog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Index extends AppCompatActivity {
    protected String username;
    TextView indexLogout;

    public Index(String username) {
        this.username = username;
    }

    public Index() {
        this.username = MainActivity.currentUser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        TextView loginInfoTemp = (TextView) findViewById(R.id.loginInfoTemp);
        String tempText = "User:  " + username;
        loginInfoTemp.setText(tempText);

        indexLogout = findViewById(R.id.indexLogoutButton);
        indexLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Index.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
