package com.example.absenceapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.absenceapp.R;

public class DashboardEtudiantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_etudiant);

        TextView txtCne = findViewById(R.id.txt_cne);
        txtCne.setText("CNE : ETU123");  // Tu peux remplacer par une valeur dynamique si besoin

        Button btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardEtudiantActivity.this, LoginEtudiantActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // empêche de revenir avec le bouton retour
            startActivity(intent);
        });
    }
}
