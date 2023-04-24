package com.example.fishlog.DB;

import com.example.fishlog.User;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface UserDAO {
    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User users);
}
