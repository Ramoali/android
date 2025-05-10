package com.example.absenceapp.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.absenceapp.R;
import com.example.absenceapp.database.AppDatabase;
import com.example.absenceapp.model.Enseignant;

public class SignUpEnseignantActivity extends AppCompatActivity {

    private EditText edtId, edtNom, edtEmail, edtPassword;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_enseignant);

        edtId = findViewById(R.id.edt_id);
        edtNom = findViewById(R.id.edt_nom);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        db = AppDatabase.getInstance(this);

        findViewById(R.id.btn_signup).setOnClickListener(v -> {
            String id = edtId.getText().toString();
            String nom = edtNom.getText().toString();
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();

            if (id.isEmpty() || nom.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                Enseignant existing = db.enseignantDao().checkExisting(id, email);
                if (existing != null) {
                    runOnUiThread(() ->
                            Toast.makeText(SignUpEnseignantActivity.this, "ID ou Email déjà utilisé", Toast.LENGTH_SHORT).show()
                    );
                } else {
                    Enseignant enseignant = new Enseignant(id, nom, email, password);
                    db.enseignantDao().insert(enseignant);
                    runOnUiThread(() -> {
                        Toast.makeText(SignUpEnseignantActivity.this, "Compte créé", Toast.LENGTH_SHORT).show();
                        finish();
                    });
                }
            }).start();
        });
    }
}
