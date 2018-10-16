package com.github.victormun.petmanager.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PetDao {

    @Query("SELECT * FROM pet ORDER BY id")
    LiveData<List<PetEntry>> loadAllPets();

    @Insert
    void insertPet(PetEntry petEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePet(PetEntry petEntry);

    @Delete
    void deletePet(PetEntry petEntry);

    @Query("SELECT * FROM pet WHERE id = :id")
    LiveData<PetEntry> loadPetById(int id);
}
