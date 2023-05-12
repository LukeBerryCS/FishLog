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
    private boolean admin;

    //Personal best values
    private String bestWeightSpecies = null;
    private float bestWeight = 0;
    private String bestLengthSpecies = null;
    private int bestLength = 0;
    private int totalCatches = 0;

    public User(String username, String password, boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getBestWeightSpecies() {
        return bestWeightSpecies;
    }

    public void setBestWeightSpecies(String bestWeightSpecies) {
        this.bestWeightSpecies = bestWeightSpecies;
    }

    public float getBestWeight() {
        return bestWeight;
    }

    public void setBestWeight(float bestWeight) {
        this.bestWeight = bestWeight;
    }

    public String getBestLengthSpecies() {
        return bestLengthSpecies;
    }

    public void setBestLengthSpecies(String bestLengthSpecies) {
        this.bestLengthSpecies = bestLengthSpecies;
    }

    public int getBestLength() {
        return bestLength;
    }

    public void setBestLength(int bestLength) {
        this.bestLength = bestLength;
    }

    public int getTotalCatches() {
        return totalCatches;
    }

    public void setTotalCatches(int totalCatches) {
        this.totalCatches = totalCatches;
    }
}
