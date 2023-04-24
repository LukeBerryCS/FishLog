package com.example.fishlog.DB;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fishlog.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "Users.db";
    public static final String USERS_TABLE = "users_table";

    public static volatile UserDatabase instance;

    public static final Object LOCK = new Object();

    public abstract UserDAO UserDAO();

    public static UserDatabase getInstance(Context context) {
        if(instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

}
