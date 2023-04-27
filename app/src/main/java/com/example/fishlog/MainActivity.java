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

    //Login screen fields
    EditText usernameEntry;
    EditText passwordEntry;
    Button submit;
    Button newUser;
    ActivityMainBinding binding; //Binds login screen fields

    //Logged in screen
    TextView loggedInText;
    LoggedInBinding loginInfoBinding;

    //Signed up screen
    TextView newUserText;
    NewUserBinding newUserBinding;
    TextView errorPrompt;
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

        submit.setOnClickListener(new View.OnClickListener() { //runs if "login" button is clicked
            @Override
            public void onClick(View view){
               attemptLogin();
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() { //runs if "new user" button is clicked
            @Override
            public void onClick(View view) {
                createNewUser();
            }
        });

    }

    private void attemptLogin() {
        String myUsername = usernameEntry.getText().toString(); //gets values in username and password fields
        String myPassword = passwordEntry.getText().toString();

        User myUser = myUserDAO.findUser(myUsername); //returns the user object with the entered username
        if(myUser.getPassword().equals(myPassword)) { //Runs if password matches username
            setContentView(R.layout.logged_in); //sets screen to "logged in" screen
            loginInfoBinding = LoggedInBinding.inflate(getLayoutInflater()); //creates screen from "logged_in.xml"
            setContentView(loginInfoBinding.getRoot()); //makes layout visible

            loggedInText = loginInfoBinding.loginInfoTemp; //connects textView object
            String tempText = "You logged in with the following credentials\nUsername: " + myUsername + "\n" + "Password: " + myPassword;
            loggedInText.setText(tempText);
        } else { //Incorrect password
            String incorrectPasswordPrompt = "Incorrect password";
            errorPrompt.setText(incorrectPasswordPrompt);
        }
    }

    private void createNewUser() {
        String myUsername = usernameEntry.getText().toString(); //gets values in username and password fields
        String myPassword = passwordEntry.getText().toString();

        User newUser = new User(myUsername, myPassword); //Creates a new user object with fields in username and password screen
        myUserDAO.insert(newUser); //adds new user to the database

        setContentView(R.layout.new_user); //sets screen to "new user created" screen
        newUserBinding = NewUserBinding.inflate(getLayoutInflater()); //creates screen from "new_user.xml"
        setContentView(newUserBinding.getRoot()); //makes layout visible

        newUserText = newUserBinding.newUserTemp; //connects textView object
        String tempText2 = "You created a new user with the following credentials\nUsername: " + myUsername + "\n" + "Password: " + myPassword;
        newUserText.setText(tempText2);

    }
}