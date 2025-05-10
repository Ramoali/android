package com.example.absenceapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.absenceapp.R;
import com.example.absenceapp.database.AppDatabase;
import com.example.absenceapp.model.Etudiant;

public class SignUpEtudiantActivity extends AppCompatActivity {

    private EditText edtCne, edtNom, edtEmail, edtPassword;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_etudiant);

        edtCne = findViewById(R.id.edt_cne);
        edtNom = findViewById(R.id.edt_nom);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        db = AppDatabase.getInstance(this);

        findViewById(R.id.btn_signup).setOnClickListener(v -> {
            String cne = edtCne.getText().toString();
            String nom = edtNom.getText().toString();
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();

            if (cne.isEmpty() || nom.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                Etudiant existing = db.etudiantDao().checkExisting(cne, email);
                if (existing != null) {
                    runOnUiThread(() ->
                            Toast.makeText(SignUpEtudiantActivity.this, "CNE ou Email déjà utilisé", Toast.LENGTH_SHORT).show()
                    );
                } else {
                    Etudiant etudiant = new Etudiant(cne, nom, email, password);
                    db.etudiantDao().insert(etudiant);
                    runOnUiThread(() -> {
                        Toast.makeText(SignUpEtudiantActivity.this, "Compte créé avec succès", Toast.LENGTH_SHORT).show();
                        finish();
                    });
                }
            }).start();
        });
    }
}
