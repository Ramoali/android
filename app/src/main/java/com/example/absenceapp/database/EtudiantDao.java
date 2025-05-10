package com.example.absenceapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.absenceapp.model.Etudiant;

@Dao
public interface EtudiantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Etudiant etudiant);

    @Query("SELECT * FROM etudiants WHERE cne = :cne AND password = :password")
    Etudiant login(String cne, String password);

    @Query("SELECT * FROM etudiants WHERE cne = :cne OR email = :email")
    Etudiant checkExisting(String cne, String email);
}