package com.example.fishlog.DB;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fishlog.User;

@Database(entities = {User.class}, version = 1) //marks UserDatabase class as a database holding user objects
public abstract class UserDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "Users.db";
    public static final String USERS_TABLE = "users_table";

    public static volatile UserDatabase instance; //creates instance of UserDatabase that will update on all
                                                  //threads throughout entire program

    public static final Object LOCK = new Object(); //Creates a "Lock" object used for synchronization during database creation

    public abstract UserDAO UserDAO(); //Returns an instance of UserDAO, objects interacting with database

    public static UserDatabase getInstance(Context context) {
        if(instance == null) {
            synchronized (LOCK) { //Locks threads until new database created
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

}
