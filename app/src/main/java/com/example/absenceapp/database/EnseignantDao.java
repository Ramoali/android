package com.example.absenceapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.absenceapp.model.Enseignant;

@Dao
public interface EnseignantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Enseignant enseignant);

    @Query("SELECT * FROM enseignants WHERE id = :id AND password = :password")
    Enseignant login(String id, String password);

    @Query("SELECT * FROM enseignants WHERE id = :id OR email = :email")
    Enseignant checkExisting(String id, String email);
}