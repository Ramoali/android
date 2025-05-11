package com.example.absenceapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.absenceapp.R;
import com.example.absenceapp.database.AppDatabase;
import com.example.absenceapp.model.Absence;

public class ModifierAbsenceActivity extends AppCompatActivity {

    private EditText edtMatiere, edtDate, edtSeance;
    private Switch switchJustifiee;
    private Button btnSaveChanges;
    private int absenceId;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_absence);

        edtMatiere = findViewById(R.id.edtMatiere);
        edtDate = findViewById(R.id.edtDate);
        edtSeance = findViewById(R.id.edtSeance);
        switchJustifiee = findViewById(R.id.switchJustifiee);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);

        db = AppDatabase.getInstance(this);

        // Récupérer l'ID de l'absence à modifier
        absenceId = getIntent().getIntExtra("absence_id", -1);

        // Charger les informations de l'absence à modifier
        loadAbsenceData(absenceId);

        // Sauvegarder les modifications
        btnSaveChanges.setOnClickListener(v -> saveChanges());
    }

    private void loadAbsenceData(int id) {
        new Thread(() -> {
            Absence absence = db.absenceDao().getAbsenceById(id);

            runOnUiThread(() -> {
                if (absence != null) {
                    edtMatiere.setText(absence.getMatiere());
                    edtDate.setText(absence.getDate());
                    edtSeance.setText(absence.getSeance());
                    switchJustifiee.setChecked(absence.isEstJustifiee());
                }
            });
        }).start();
    }

    private void saveChanges() {
        String matiere = edtMatiere.getText().toString();
        String date = edtDate.getText().toString();
        String seance = edtSeance.getText().toString();
        boolean isJustifiee = switchJustifiee.isChecked();

        Absence absenceToUpdate = new Absence(absenceId, "", matiere, date, seance, isJustifiee, false, "");

        new Thread(() -> {
            db.absenceDao().updateAbsence(absenceToUpdate);

            runOnUiThread(() -> {
                // Retourner à l'écran précédent ou afficher un message de confirmation
                finish();
            });
        }).start();
    }
}
