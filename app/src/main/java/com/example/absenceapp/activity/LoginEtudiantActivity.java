package com.example.absenceapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.absenceapp.R;
import com.example.absenceapp.database.AppDatabase;
import com.example.absenceapp.model.Etudiant;

public class LoginEtudiantActivity extends AppCompatActivity {

    private EditText edtCne, edtPassword;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_etudiant);

        edtCne = findViewById(R.id.edt_cne);
        edtPassword = findViewById(R.id.edt_password);
        db = AppDatabase.getInstance(this);

        findViewById(R.id.btn_login).setOnClickListener(v -> {
            String cne = edtCne.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (cne.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                Etudiant etudiant = db.etudiantDao().login(cne, password);
                runOnUiThread(() -> {
                    if (etudiant != null) {
                        Intent intent = new Intent(LoginEtudiantActivity.this, DashboardEtudiantActivity.class);
                        intent.putExtra("etudiant_cne", etudiant.getCne()); // ✅ Ajout ici
                        startActivity(intent);
                        finish(); // facultatif : pour empêcher retour au login
                    } else {
                        Toast.makeText(LoginEtudiantActivity.this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });

        findViewById(R.id.btn_signup).setOnClickListener(v ->
                startActivity(new Intent(this, SignUpEtudiantActivity.class)));
    }
}
