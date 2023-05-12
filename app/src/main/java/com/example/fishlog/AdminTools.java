package com.example.fishlog;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.room.Room;

import com.example.fishlog.DB.UserDAO;
import com.example.fishlog.DB.UserDatabase;

import org.w3c.dom.Text;

public class AdminTools extends AppCompatActivity {

    TextView adminToolsTitle;
    TextView adminToolsUsername;
    Button adminToolsDeleteUser;
    Button adminToolsGrantAdmin;
    Button adminToolsChangePassword;
    Button adminToolsBack;
    Button adminToolsChangePasswordCancel;
    TextView adminToolsChangePasswordEntry;
    Button adminToolsChangePasswordConfirm;

    UserDAO myUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("AdminTools created");
        setContentView(R.layout.admin_tools);

        myUserDAO = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DATABASE_NAME).allowMainThreadQueries().build().UserDAO();

        adminToolsTitle = findViewById(R.id.adminToolsTitle);
        adminToolsUsername = findViewById(R.id.adminToolsUsername);
        adminToolsDeleteUser = findViewById(R.id.adminToolsDeleteUser);
        adminToolsGrantAdmin = findViewById(R.id.adminToolsGrantAdmin);
        adminToolsChangePassword = findViewById(R.id.adminToolsChangePassword);
        adminToolsBack = findViewById(R.id.adminToolsBack);
        adminToolsChangePasswordCancel = findViewById(R.id.adminToolsChangePasswordCancel);
        adminToolsChangePasswordConfirm = findViewById(R.id.adminToolsChangePasswordConfirm);
        adminToolsChangePasswordEntry = findViewById(R.id.adminToolsChangePasswordEntry);

        adminToolsDeleteUser.setOnClickListener(view -> deleteUser());
        adminToolsGrantAdmin.setOnClickListener(view -> grantAdmin());
        adminToolsChangePassword.setOnClickListener(view -> changePassword(null));
        adminToolsBack.setOnClickListener(view -> back());
        adminToolsChangePasswordCancel.setOnClickListener(view -> cancelChangePassword());
        adminToolsChangePasswordConfirm.setOnClickListener(view -> confirmChangePassword());

        adminToolsChangePasswordConfirm.setVisibility(View.INVISIBLE);
        adminToolsChangePassword.setVisibility(View.VISIBLE);
        adminToolsChangePasswordCancel.setVisibility(View.INVISIBLE);
        adminToolsChangePasswordEntry.setVisibility(View.INVISIBLE);
        adminToolsChangePasswordEntry.setText("");
    }

    private void confirmChangePassword() {
        User myUser = myUserDAO.findUser(adminToolsUsername.getText().toString());
        myUser.setPassword(adminToolsChangePasswordEntry.getText().toString());
        myUserDAO.update(myUser);
        adminToolsChangePasswordConfirm.setVisibility(View.INVISIBLE);
        adminToolsChangePassword.setVisibility(View.VISIBLE);
        adminToolsChangePasswordCancel.setVisibility(View.INVISIBLE);
        adminToolsChangePasswordEntry.setVisibility(View.INVISIBLE);
        adminToolsChangePasswordEntry.setText("");
    }

    private void cancelChangePassword() {
        adminToolsChangePasswordConfirm.setVisibility(View.INVISIBLE);
        adminToolsChangePassword.setVisibility(View.VISIBLE);
        adminToolsChangePasswordCancel.setVisibility(View.INVISIBLE);
        adminToolsChangePasswordEntry.setVisibility(View.INVISIBLE);
        adminToolsChangePasswordEntry.setText("");
    }
    private void deleteUser() {
        User myUser = myUserDAO.findUser(adminToolsUsername.getText().toString());
        myUserDAO.delete(myUser);
        Toast.makeText(AdminTools.this, "User: " + myUser.getUsername() + " deleted", Toast.LENGTH_LONG).show();
    }

    private void grantAdmin() {
        User myUser = myUserDAO.findUser(adminToolsUsername.getText().toString());
        myUser.setAdmin(true);
        myUserDAO.update(myUser);
    }

    //TODO implement changePassword()
    private void changePassword(String password) {
        adminToolsChangePasswordConfirm.setVisibility(View.VISIBLE);
        adminToolsChangePassword.setVisibility(View.INVISIBLE);
        adminToolsChangePasswordCancel.setVisibility(View.VISIBLE);
        adminToolsChangePasswordEntry.setVisibility(View.VISIBLE);
        adminToolsChangePasswordEntry.setText("");
    }


    private void back() {
        Intent intent = new Intent(AdminTools.this, Index.class);
        startActivity(intent);
        finish();
    }
}
