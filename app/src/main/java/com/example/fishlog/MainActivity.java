package com.example.fishlog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fishlog.DB.UserDatabase;
import com.example.fishlog.databinding.ActivityMainBinding;
import com.example.fishlog.databinding.LoggedInBinding;
import com.example.fishlog.DB.UserDAO;
import com.example.fishlog.databinding.NewUserBinding;

public class MainActivity extends AppCompatActivity {

    EditText usernameEntry;
    EditText passwordEntry;
    TextView loggedInText;
    TextView newUserText;
    Button submit;
    Button newUser;

    ActivityMainBinding binding;

    LoggedInBinding loginInfoBinding;
    NewUserBinding newUserBinding;

    UserDAO myUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        usernameEntry = binding.loginUsernameEntry;
        passwordEntry = binding.loginPasswordEntry;

        submit = binding.loginSubmitButton;
        newUser = binding.loginNewUser;




        myUserDAO = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DATABASE_NAME).allowMainThreadQueries().build().UserDAO();

        //CHANGE SCREEN HERE

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
               attemptLogin();
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewUser();
            }
        });

    }


    private void attemptLogin() {
        String myUsername = usernameEntry.getText().toString();
        String myPassword = passwordEntry.getText().toString();

        //CHECK FOR MATCHING
        setContentView(R.layout.logged_in);
        loginInfoBinding = LoggedInBinding.inflate(getLayoutInflater());
        setContentView(loginInfoBinding.getRoot());
        loggedInText = loginInfoBinding.loginInfoTemp;
        loggedInText.setText("You logged in with the following credentials\nUsername: " + myUsername + "\n" + "Password: " + myPassword);
    }

    private void createNewUser() {
        String myUsername = usernameEntry.getText().toString();
        String myPassword = passwordEntry.getText().toString();

        User newUser = new User(myUsername, myPassword);
        myUserDAO.insert(newUser);
        setContentView(R.layout.new_user);

        newUserBinding = NewUserBinding.inflate(getLayoutInflater());
        setContentView(newUserBinding.getRoot());
        newUserText = newUserBinding.newUserText;
        newUserText.setText("You created a new user with the following credentials\nUsername: " + myUsername + "\n" + "Password: " + myPassword);



    }
}