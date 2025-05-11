package com.example.absenceapp.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.absenceapp.R;
import com.example.absenceapp.database.AppDatabase;
import com.example.absenceapp.model.Absence;

public class AjoutAbsenceActivity extends AppCompatActivity {

    private EditText editCne, editDate, editSeance;
    private Spinner spinnerMatiere;
    private CheckBox checkJustifiee, checkPenalite;
    private Button btnAjouter;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_absence);

        editCne = findViewById(R.id.editCne);
        editDate = findViewById(R.id.editDate);
        editSeance = findViewById(R.id.editSeance);
        spinnerMatiere = findViewById(R.id.spinnerMatiere); // Spinner au lieu d’EditText
        checkJustifiee = findViewById(R.id.checkJustifiee);
        checkPenalite = findViewById(R.id.checkPenalite);
        btnAjouter = findViewById(R.id.btnAjouterAbsence);

        db = AppDatabase.getInstance(this);

        // Liste des matières (tu peux les charger dynamiquement depuis la BDD si nécessaire)
        String[] matieres = {"Mathématiques", "Physique", "Chimie", "Informatique"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, matieres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMatiere.setAdapter(adapter);

        btnAjouter.setOnClickListener(v -> {
            String cne = editCne.getText().toString().trim();
            String matiere = spinnerMatiere.getSelectedItem().toString();
            String date = editDate.getText().toString().trim();
            String seance = editSeance.getText().toString().trim();
            boolean justifiee = checkJustifiee.isChecked();
            boolean penalite = checkPenalite.isChecked();

            if (cne.isEmpty() || date.isEmpty() || seance.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            Absence absence = new Absence(cne, matiere, date, seance, justifiee, penalite, null);

            new Thread(() -> {
                db.absenceDao().insert(absence);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Absence ajoutée avec succès", Toast.LENGTH_SHORT).show();
                    finish(); // Retour au dashboard enseignant
                });
            }).start();
        });
    }
}
