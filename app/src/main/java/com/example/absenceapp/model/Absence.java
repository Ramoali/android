package com.example.absenceapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "absences")
public class Absence {

    @PrimaryKey(autoGenerate = true)  // Use autoGenerate to allow Room to handle the id
    private int id;
    private String cneEtudiant;
    private String matiere;
    private String date;
    private String seance;
    private boolean estJustifiee;
    private boolean penalite;
    private String justificationFilePath;

    // Default constructor required by Room (empty constructor)
    public Absence() {
    }

    // Constructor with all parameters, for existing entries (without auto-generate for id)
    public Absence(int id, String cneEtudiant, String matiere, String date, String seance,
                   boolean estJustifiee, boolean penalite, String justificationFilePath) {
        this.id = id;
        this.cneEtudiant = cneEtudiant;
        this.matiere = matiere;
        this.date = date;
        this.seance = seance;
        this.estJustifiee = estJustifiee;
        this.penalite = penalite;
        this.justificationFilePath = justificationFilePath;
    }

    // Constructor for creating new entries (without id, as Room handles it automatically)
    @Ignore
    public Absence(String cneEtudiant, String matiere, String date, String seance,
                   boolean estJustifiee, boolean penalite, String justificationFilePath) {
        this.cneEtudiant = cneEtudiant;
        this.matiere = matiere;
        this.date = date;
        this.seance = seance;
        this.estJustifiee = estJustifiee;
        this.penalite = penalite;
        this.justificationFilePath = justificationFilePath;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCneEtudiant() {
        return cneEtudiant;
    }

    public void setCneEtudiant(String cneEtudiant) {
        this.cneEtudiant = cneEtudiant;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeance() {
        return seance;
    }

    public void setSeance(String seance) {
        this.seance = seance;
    }

    public boolean isEstJustifiee() {
        return estJustifiee;
    }

    public void setEstJustifiee(boolean estJustifiee) {
        this.estJustifiee = estJustifiee;
    }

    public boolean isPenalite() {
        return penalite;
    }

    public void setPenalite(boolean penalite) {
        this.penalite = penalite;
    }

    public String getJustificationFilePath() {
        return justificationFilePath;
    }

    public void setJustificationFilePath(String justificationFilePath) {
        this.justificationFilePath = justificationFilePath;
    }
}
