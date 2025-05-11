package com.example.absenceapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.absenceapp.R;

public class DashboardEnseignantActivity extends AppCompatActivity {

    private Spinner spinnerMatiere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_enseignant);

        TextView txtWelcome = findViewById(R.id.txt_welcome);
        spinnerMatiere = findViewById(R.id.spinner_matiere);
        Button btnRechercher = findViewById(R.id.btn_rechercher_absences);
        Button btnAjouter = findViewById(R.id.btnAjouterAbsence);
        Button btnDeconnexion = findViewById(R.id.btn_deconnexion);

        txtWelcome.setText("Bienvenue, Professeur");

        // Liste dynamique des matières — tu peux la charger depuis une BDD/API aussi
        String[] matieres = {"Mathématiques", "Physique", "Chimie", "Informatique"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, matieres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMatiere.setAdapter(adapter);

        // Rechercher absences par matière
        btnRechercher.setOnClickListener(v -> {
            String matiereSelectionnee = spinnerMatiere.getSelectedItem().toString();
            Intent intent = new Intent(DashboardEnseignantActivity.this, ListeAbsencesParMatiereActivity.class);
            intent.putExtra("MATIERE", matiereSelectionnee);
            startActivity(intent);
        });

        // Ajouter une absence
        btnAjouter.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardEnseignantActivity.this, AjoutAbsenceActivity.class);
            startActivity(intent);
        });

        // Déconnexion
        btnDeconnexion.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardEnseignantActivity.this, LoginEnseignantActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
