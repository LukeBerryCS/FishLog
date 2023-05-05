package com.example.fishlog.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fishlog.Fish;
import com.example.fishlog.Trip;

import java.util.List;

@Dao
public interface TripDAO {
    @Insert
    void insert(Trip... trip);

    @Update
    void update(Trip... trip);

    @Delete
    void delete(Trip trip);

    @Query("SELECT * FROM trip_table WHERE userId = :myUserId")
    public List<Trip> findTrips(int myUserId);
}
