package com.example.absenceapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.absenceapp.R;
import com.example.absenceapp.activity.LoginEnseignantActivity;
import com.example.absenceapp.activity.LoginEtudiantActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Rediriger vers LoginEnseignantActivity
        findViewById(R.id.btn_login_enseignant).setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginEnseignantActivity.class);
            startActivity(intent);
        });

        // Rediriger vers LoginEtudiantActivity
        findViewById(R.id.btn_login_etudiant).setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginEtudiantActivity.class);
            startActivity(intent);
        });
    }
}