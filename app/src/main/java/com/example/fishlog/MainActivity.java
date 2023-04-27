package com.example.fishlog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.fishlog.DB.UserDatabase;
import com.example.fishlog.databinding.ActivityMainBinding;
import com.example.fishlog.DB.UserDAO;
import com.example.fishlog.databinding.IndexBinding;

public class MainActivity extends AppCompatActivity {

    //Login screen fields
    EditText usernameEntry;
    EditText passwordEntry;
    TextView errorPrompt;
    Button submit;
    Button newUser;

    ActivityMainBinding binding; //Binds login screen fields

    //Index screen
    TextView loggedInText;
    IndexBinding indexBinding;

    //Database holding users
    UserDAO myUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //sets screen to login screen)

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //text fields
        usernameEntry = binding.loginUsernameEntry;
        passwordEntry = binding.loginPasswordEntry;
        errorPrompt = binding.loginErrorPrompt;
        //buttons
        submit = binding.loginSubmitButton;
        newUser = binding.loginNewUser;
        //creates a variable for a new database
        myUserDAO = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DATABASE_NAME).allowMainThreadQueries().build().UserDAO();

        //runs if "login" button is clicked
        submit.setOnClickListener(view -> attemptLogin());

        //runs if "new user" button is clicked
        newUser.setOnClickListener(view -> createNewUser());
    }

    private void attemptLogin() {
        String myUsername = usernameEntry.getText().toString(); //gets values in username and password fields
        String myPassword = passwordEntry.getText().toString();
        User myUser = myUserDAO.findUser(myUsername); //returns the user object with the entered username

        try {
            if (myUser.getPassword().equals(myPassword)) { //Runs if password matches username
                confirmLogin(myUser.getUsername());
                //LOGGING
                System.out.println("User " + myUsername + "logged in");
            } else { //Incorrect password
                String incorrectPasswordPrompt = "Incorrect password";
                errorPrompt.setText(incorrectPasswordPrompt);
                System.out.println("Incorrect password for user " + myUsername + " entered");
            }
        } catch (NullPointerException e) { //Accounts for the attempt of a login for a username that has not been created
            String incorrectPasswordPrompt = "No user found";
            errorPrompt.setText(incorrectPasswordPrompt);
            System.out.println("User attempted a login with a username " + myUsername + " that did not exist");
        }
    }

    private void createNewUser() {
        String myUsername = usernameEntry.getText().toString(); //gets values in username and password fields
        String myPassword = passwordEntry.getText().toString();

        User myUser = myUserDAO.findUser(myUsername);
        if(myUser == null) { //Runs if user with that username has NOT been created
            if(myPassword.length() >= 4) {
                User newUser = new User(myUsername, myPassword); //Creates a new user object with fields in username and password screen
                myUserDAO.insert(newUser); //adds new user to the database
                confirmLogin(myUsername);
                //LOGGING
                System.out.println("New user created: " + myUsername);
            } else {
                String errorText = "4 char min. password";
                errorPrompt.setText(errorText);
                System.out.println("User attempted create user " + myUsername + " with a password less than 4 characters");
            }
        } else {
            String errorText = "User already created";
            errorPrompt.setText(errorText);
            System.out.println("User attempted to create a user " + myUsername + " that had already been created");
        }
    }

    private void confirmLogin(String username) {
        setContentView(R.layout.index); //sets screen to "logged in" screen
        indexBinding = IndexBinding.inflate(getLayoutInflater()); //creates screen from "logged_in.xml"
        setContentView(indexBinding.getRoot()); //makes layout visible

        loggedInText = indexBinding.loginInfoTemp; //connects textView object
        String tempText = "Logged in as user: " + username;
        loggedInText.setText(tempText);
    }
}