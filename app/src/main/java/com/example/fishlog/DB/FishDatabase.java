package com.example.fishlog.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fishlog.Fish;

@Database(entities = {Fish.class}, version = 1)
public abstract class FishDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "Fish.db";
    public static final String FISH_TABLE = "fish_table";

    public static volatile FishDatabase instance; //creates instance of FishDatabase that will update on all
    //threads throughout entire program
    public static final Object LOCK = new Object(); //Creates a "Lock" object used for synchronization during database creation

    public abstract FishDAO FishDAO(); //Returns an instance of FishDAO, objects interacting with database

    public static FishDatabase getInstance(Context context) {
        if(instance == null) {
            synchronized (LOCK) { //Locks threads until new database created
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), FishDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
