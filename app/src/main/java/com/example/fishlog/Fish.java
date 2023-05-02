package com.example.fishlog;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.fishlog.DB.FishDatabase;


@Entity(tableName = FishDatabase.FISH_TABLE)
public class Fish {
    @PrimaryKey(autoGenerate = true) //Room autogenerates a key for table entries
    private int fishId;

    private int userId;
    private String species;
    private float weight;
    private int size;
    private String location;

    public Fish(String species, float weight, int size, String location) {
        this.userId = MainActivity.currentUserId;
        this.species = species;
        this.weight = weight;
        this.size = size;
        this.location = location;
        System.out.println("Fish created");
    }

    public Fish() {
        System.out.println("No-parameter Fish created");
    }

    public int getFishId() {
        return fishId;
    }

    public void setFishId(int fishId) {
        this.fishId = fishId;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
