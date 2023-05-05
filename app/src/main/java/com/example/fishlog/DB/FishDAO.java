package com.example.fishlog.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fishlog.Fish;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface FishDAO {
    @Insert
    void insert(Fish... fish);

    @Update
    void update(Fish... fish);

    @Delete
    void delete(Fish fish);

    @Query("SELECT * FROM fish_table WHERE userId = :currentUserId AND tripId = 0")
    public List<Fish> getMyFish(int currentUserId);

    @Query("SELECT * FROM fish_table WHERE tripId = :tripId")
    public List<Fish> populateCatches(int tripId);

}