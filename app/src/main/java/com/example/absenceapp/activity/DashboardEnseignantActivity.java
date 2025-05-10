package com.example.absenceapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.absenceapp.R;

public class DashboardEnseignantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_enseignant);

        TextView txtWelcome = findViewById(R.id.txt_welcome);
        Button btnRechercher = findViewById(R.id.btn_rechercher_absences);
        Button btnDeconnexion = findViewById(R.id.btn_deconnexion); // Nouveau bouton de déconnexion

        txtWelcome.setText("Bienvenue, Professeur");

        // Redirection vers la recherche d'absences par matière
        btnRechercher.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardEnseignantActivity.this, ListeAbsencesParMatiereActivity.class);
            intent.putExtra("MATIERE", "Mathématiques");
            startActivity(intent);
        });

        // Action pour la déconnexion
        btnDeconnexion.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardEnseignantActivity.this, LoginEnseignantActivity.class);
            startActivity(intent);
            finish(); // Ferme le dashboard pour ne pas permettre de revenir en arrière
        });
    }
}
