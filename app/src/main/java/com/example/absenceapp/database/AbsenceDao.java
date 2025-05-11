package com.example.absenceapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.absenceapp.model.Absence;

import java.util.List;

@Dao
public interface AbsenceDao {

    // Méthode pour insérer une absence
    @Insert
    void insert(Absence absence);

    // Méthode pour obtenir toutes les absences d'une matière
    @Query("SELECT * FROM absences WHERE matiere = :matiere")
    List<Absence> getAbsencesByMatiere(String matiere);

    // Méthode pour obtenir toutes les absences
    @Query("SELECT * FROM absences")
    List<Absence> getAllAbsences();

    // Méthode pour obtenir toutes les absences d'un étudiant spécifique (par CNE)
    @Query("SELECT * FROM absences WHERE cneEtudiant = :cne")
    List<Absence> getAbsencesByEtudiant(String cne);

    // Méthode pour obtenir une absence par ID
    @Query("SELECT * FROM absences WHERE id = :id")
    Absence getAbsenceById(int id);

    // Méthode pour supprimer une absence par ID
    @Query("DELETE FROM absences WHERE id = :id")
    void deleteById(int id);

    // Méthode pour supprimer une absence spécifique par l'objet Absence
    @Delete
    void delete(Absence absence);

    // Méthode pour mettre à jour une absence (par exemple, pour la justification et la pénalité)
    @Update
    void updateAbsence(Absence absence);

    // Méthode pour mettre à jour une absence en fonction de son ID (pour la justification et pénalité)
    @Query("UPDATE absences SET estJustifiee = :estJustifiee, justificationFilePath = :filePath, penalite = :penalite WHERE id = :id")
    void updateJustification(int id, boolean estJustifiee, String filePath, boolean penalite);
}
