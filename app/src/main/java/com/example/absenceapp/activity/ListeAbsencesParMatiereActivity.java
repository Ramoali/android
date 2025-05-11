package com.example.absenceapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.absenceapp.R;
import com.example.absenceapp.database.AppDatabase;
import com.example.absenceapp.model.Absence;

import java.util.ArrayList;
import java.util.List;

public class ListeAbsencesParMatiereActivity extends AppCompatActivity {

    private Spinner spinnerMatiere;
    private ListView listViewAbsences;

    private AppDatabase db;
    private AbsenceAdapter absenceAdapter;

    private String[] matieres = {"Mathématiques", "Physique", "Chimie", "Informatique"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_absences_par_matiere);

        spinnerMatiere = findViewById(R.id.spinnerMatiereListe);
        listViewAbsences = findViewById(R.id.listViewAbsences);

        db = AppDatabase.getInstance(this);

        // Adapter pour les matières
        ArrayAdapter<String> matiereAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, matieres);
        matiereAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMatiere.setAdapter(matiereAdapter);

        spinnerMatiere.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String matiereChoisie = matieres[position];
                chargerAbsencesParMatiere(matiereChoisie);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void chargerAbsencesParMatiere(String matiere) {
        new Thread(() -> {
            List<Absence> absences = db.absenceDao().getAbsencesByMatiere(matiere);

            runOnUiThread(() -> {
                absenceAdapter = new AbsenceAdapter(ListeAbsencesParMatiereActivity.this, absences);
                listViewAbsences.setAdapter(absenceAdapter);
            });
        }).start();
    }

    private class AbsenceAdapter extends ArrayAdapter<Absence> {

        private List<Absence> absenceList;

        public AbsenceAdapter(ListeAbsencesParMatiereActivity context, List<Absence> absences) {
            super(context, 0, absences);
            this.absenceList = absences;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;

            if (listItemView == null) {
                listItemView = getLayoutInflater().inflate(R.layout.item_absence, parent, false);
            }

            Absence currentAbsence = absenceList.get(position);

            // Mettre à jour les informations de l'absence dans l'UI
            TextView txtInfo = listItemView.findViewById(R.id.txt_info);
            txtInfo.setText("CNE: " + currentAbsence.getCneEtudiant() + "\nDate: " + currentAbsence.getDate() +
                    "\nSéance: " + currentAbsence.getSeance() +
                    "\nJustifiée: " + (currentAbsence.isEstJustifiee() ? "Oui" : "Non") +
                    "\nPénalité: " + (currentAbsence.isPenalite() ? "Oui" : "Non"));

            // Bouton Modifier
            Button btnModifier = listItemView.findViewById(R.id.btn_modifier);
            btnModifier.setOnClickListener(v -> {
                Intent intent = new Intent(ListeAbsencesParMatiereActivity.this, ModifierAbsenceActivity.class);
                intent.putExtra("absenceId", currentAbsence.getId());
                startActivity(intent);
            });

            // Bouton Supprimer
            Button btnSupprimer = listItemView.findViewById(R.id.btn_supprimer);
            btnSupprimer.setOnClickListener(v -> {
                new AlertDialog.Builder(ListeAbsencesParMatiereActivity.this)
                        .setTitle("Confirmation")
                        .setMessage("Êtes-vous sûr de vouloir supprimer cette absence ?")
                        .setPositiveButton("Oui", (dialog, which) -> {
                            new Thread(() -> {
                                db.absenceDao().delete(currentAbsence);
                                runOnUiThread(() -> {
                                    absenceList.remove(position);
                                    notifyDataSetChanged();
                                    Toast.makeText(ListeAbsencesParMatiereActivity.this, "Absence supprimée", Toast.LENGTH_SHORT).show();
                                });
                            }).start();
                        })
                        .setNegativeButton("Non", null)
                        .show();
            });

            return listItemView;
        }
    }
}
