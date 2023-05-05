package com.example.fishlog;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.fishlog.DB.TripDatabase;

import java.sql.Date;

@Entity(tableName = TripDatabase.TRIP_TABLE)
public class Trip {
    protected static int classTripId = 0;
    @PrimaryKey(autoGenerate = false)
    private int tripId;
    private String location;
    private String tripStart;
    private String tripEnd;
    private int userId;

    public Trip(String location, String tripStart, String tripEnd) {
        classTripId += 1;
        this.tripId = classTripId;
        this.location = location;
        this.tripStart = tripStart;
        this.tripEnd = tripEnd;
        this.userId = MainActivity.currentUserId;
    }

    public static int getClassTripId() {
        return classTripId;
    }

    public static void setClassTripId(int classTripId) {
        Trip.classTripId = classTripId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTripStart() {
        return tripStart;
    }

    public void setTripStart(String tripStart) {
        this.tripStart = tripStart;
    }

    public String getTripEnd() {
        return tripEnd;
    }

    public void setTripEnd(String tripEnd) {
        this.tripEnd = tripEnd;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
