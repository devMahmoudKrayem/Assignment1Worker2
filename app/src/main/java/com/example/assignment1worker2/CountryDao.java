package com.example.assignment1worker2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CountryDao {
    @Insert
    void insert(Country country);

    @Delete
    void delete(Country country);

    @Update
    void update(Country country);

    @Query("SELECT * FROM Country ORDER BY name ASC")
    LiveData<List<Country>> getAllCountries();

    @Query("SELECT * FROM Country WHERE name = :name")
    LiveData<List<Country>> getByName(String name);

    @Query("SELECT * FROM Country WHERE id = :id")
    LiveData<Country> getById(int id);
}
