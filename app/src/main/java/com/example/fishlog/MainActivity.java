package com.example.fishlog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.fishlog.DB.UserDatabase;
import com.example.fishlog.DB.UserDAO;

public class MainActivity extends AppCompatActivity {
    protected static int currentUserId = 0;
    protected static User currentUser = null;
    // Login screen fields
    EditText usernameEntry;
    EditText passwordEntry;
    TextView errorPrompt;
    Button submit;
    Button newUser;

    // Database holding users
    UserDAO myUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MainActivity created");
        setContentView(R.layout.activity_main); // sets screen to login screen

        //Connects fields with app fields
        usernameEntry = findViewById(R.id.usernameEntry);
        passwordEntry = findViewById(R.id.loginPasswordEntry);
        errorPrompt = findViewById(R.id.loginErrorPrompt);
        submit = findViewById(R.id.loginSubmitButton);
        newUser = findViewById(R.id.loginNewUser);

        //Creates new database and assigns it to variable
        myUserDAO = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DATABASE_NAME).allowMainThreadQueries().build().UserDAO();

        submit.setOnClickListener(view -> attemptLogin()); //Methods called on buttons clicked
        newUser.setOnClickListener(view -> createNewUser());

        User myUser = myUserDAO.findUser("admin");
        if(myUser == null) {
            User adminUser = new User("admin", "admin", true);
            myUserDAO.insert(adminUser);
        }
    }

    private void attemptLogin() {
        String myUsername = usernameEntry.getText().toString(); // gets values in username and password fields
        String myPassword = passwordEntry.getText().toString();
        User myUser = myUserDAO.findUser(myUsername); // returns the user object with the entered username

        try {
            if(myUser.getPassword().equals(myPassword)) { // Runs if password matches username
                currentUser = myUser;
                confirmLogin();
                System.out.println("User " + myUsername + "logged in");
            } else { // Incorrect password
                String incorrectPasswordPrompt = "Incorrect password";
                errorPrompt.setText(incorrectPasswordPrompt);
                System.out.println("Incorrect password for user " + myUsername + " entered");
            }
        } catch (NullPointerException e) { // Accounts for the attempt of a login for a username that has not been created
            String incorrectPasswordPrompt = "No user found";
            errorPrompt.setText(incorrectPasswordPrompt);
            System.out.println("User attempted a login with a username " + myUsername + " that did not exist");
        }
    }

    private void createNewUser() {
        String myUsername = usernameEntry.getText().toString(); // gets values in username and password fields
        String myPassword = passwordEntry.getText().toString();
        User myUser = myUserDAO.findUser(myUsername);
        if(myUser == null) { // Runs if user with that username has NOT been created
            if(myPassword.length() >= 4) {
                User newUser = new User(myUsername, myPassword, false); // Creates a new user object with fields in username and password screen
                myUserDAO.insert(newUser); // adds new user to the database
                currentUser = newUser;
                User tempUser = myUserDAO.findUser(currentUser.getUsername());
                currentUserId = tempUser.getUserId();
                confirmLogin();
                System.out.println("New user created: " + currentUser.getUsername());
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
    private void confirmLogin() {
        Intent intent = new Intent(MainActivity.this, Index.class);
        if(!currentUser.isAdmin()) {
            System.out.println("Current user is not admin");
        } else {
            System.out.println("Current user is admin");
        }
        startActivity(intent);
        System.out.println("Login confirmed: " + currentUser.getUsername());
        finish();
    }
}