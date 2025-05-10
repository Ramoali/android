package com.example.absenceapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.absenceapp.model.Absence;
import java.util.List;

@Dao
public interface AbsenceDao {
    @Insert
    void insert(Absence absence);

    @Query("SELECT * FROM absences WHERE matiere = :matiere")
    List<Absence> getAbsencesByMatiere(String matiere);

    @Query("SELECT * FROM absences")
    List<Absence> getAllAbsences();
}