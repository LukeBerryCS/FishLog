package com.example.fishlog;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.fishlog.DB.UserDatabase;

@Entity(tableName = UserDatabase.USERS_TABLE) //"User" is an entity that can be stored in the SQLite table
public class User {

    @PrimaryKey(autoGenerate = true) //Room autogenerates a key for table entries
    private int userId;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
