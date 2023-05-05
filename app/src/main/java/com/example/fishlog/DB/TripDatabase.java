package com.example.fishlog.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fishlog.Trip;

@Database(entities = {Trip.class}, version = 1)
public abstract class TripDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "Trip.db";
    public static final String TRIP_TABLE = "trip_table";

    public static volatile TripDatabase instance;
    public static final Object LOCK = new Object();

    public abstract TripDAO TripDAO();

    public static TripDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if(instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), TripDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
