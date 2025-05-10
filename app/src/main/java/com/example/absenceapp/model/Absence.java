package com.example.absenceapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "absences")
public class Absence {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String cneEtudiant;
    private String matiere;
    private String date;
    private String seance;
    private boolean estJustifiee;
    private boolean penalite;
    private String justificationFilePath;

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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCneEtudiant() { return cneEtudiant; }
    public void setCneEtudiant(String cneEtudiant) { this.cneEtudiant = cneEtudiant; }

    public String getMatiere() { return matiere; }
    public void setMatiere(String matiere) { this.matiere = matiere; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getSeance() { return seance; }
    public void setSeance(String seance) { this.seance = seance; }

    public boolean isEstJustifiee() { return estJustifiee; }
    public void setEstJustifiee(boolean estJustifiee) { this.estJustifiee = estJustifiee; }

    public boolean isPenalite() { return penalite; }
    public void setPenalite(boolean penalite) { this.penalite = penalite; }

    public String getJustificationFilePath() { return justificationFilePath; }
    public void setJustificationFilePath(String justificationFilePath) { this.justificationFilePath = justificationFilePath; }
}