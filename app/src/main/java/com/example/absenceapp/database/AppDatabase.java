package com.example.absenceapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.absenceapp.model.Enseignant;
import com.example.absenceapp.model.Etudiant;
import com.example.absenceapp.model.Absence;

@Database(entities = {Enseignant.class, Etudiant.class, Absence.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EnseignantDao enseignantDao();
    public abstract EtudiantDao etudiantDao();
    public abstract AbsenceDao absenceDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "absence_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}