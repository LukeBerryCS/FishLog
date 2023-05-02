package com.example.fishlog.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.fishlog.Fish;

@Dao
public interface FishDAO {
    @Insert
    void insert(Fish... fish);

    @Update
    void update(Fish... fish);

    @Delete
    void delete(Fish fish);

}