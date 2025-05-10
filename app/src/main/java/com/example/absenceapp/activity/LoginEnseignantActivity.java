package com.example.absenceapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.absenceapp.R;
import com.example.absenceapp.database.AppDatabase;
import com.example.absenceapp.model.Enseignant;

public class LoginEnseignantActivity extends AppCompatActivity {

    private EditText edtId, edtPassword;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_enseignant);

        edtId = findViewById(R.id.edt_id);
        edtPassword = findViewById(R.id.edt_password);
        db = AppDatabase.getInstance(this);

        findViewById(R.id.btn_login).setOnClickListener(v -> {
            String id = edtId.getText().toString();
            String password = edtPassword.getText().toString();

            if (id.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                Enseignant enseignant = db.enseignantDao().login(id, password);
                runOnUiThread(() -> {
                    if (enseignant != null) {
                        Toast.makeText(LoginEnseignantActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();

                        // Redirection vers le dashboard enseignant
                        Intent intent = new Intent(LoginEnseignantActivity.this, DashboardEnseignantActivity.class);
                        startActivity(intent);
                        finish(); // Pour ne pas revenir à l'écran de login en utilisant le bouton retour
                    } else {
                        Toast.makeText(LoginEnseignantActivity.this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });

        findViewById(R.id.btn_signup).setOnClickListener(v ->
                startActivity(new Intent(this, SignUpEnseignantActivity.class)));
    }
}
