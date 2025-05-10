package com.example.absenceapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "etudiants")
public class Etudiant {
    @PrimaryKey
    @NonNull
    private String cne;
    private String nom;
    private String email;
    private String password;

    public Etudiant(String cne, String nom, String email, String password) {
        this.cne = cne;
        this.nom = nom;
        this.email = email;
        this.password = password;
    }

    public String getCne() { return cne; }
    public void setCne(String cne) { this.cne = cne; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}