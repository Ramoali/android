package com.example.absenceapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absenceapp.R;
import com.example.absenceapp.adapter.AbsenceAdapter;
import com.example.absenceapp.database.AppDatabase;
import com.example.absenceapp.model.Absence;

import java.util.List;

public class DashboardEtudiantActivity extends AppCompatActivity {

    private static final int FILE_PICKER_REQUEST_CODE = 101;

    private AppDatabase db;
    private RecyclerView recyclerView;
    private AbsenceAdapter adapter;
    private String cne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_etudiant);

        cne = getIntent().getStringExtra("etudiant_cne");
        db = AppDatabase.getInstance(this);

        TextView txtCne = findViewById(R.id.txt_cne);
        recyclerView = findViewById(R.id.recycler_absences);
        Button btnLogout = findViewById(R.id.btn_logout);

        txtCne.setText("CNE : " + cne);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardEtudiantActivity.this, LoginEtudiantActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        new Thread(() -> {
            List<Absence> absences = db.absenceDao().getAbsencesByEtudiant(cne);
            runOnUiThread(() -> {
                adapter = new AbsenceAdapter(absences, this);
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri fileUri = data.getData();
            if (fileUri != null) {
                Toast.makeText(this, "Fichier sélectionné: " + fileUri.getLastPathSegment(), Toast.LENGTH_LONG).show();
                // TODO: Enregistrez le fichier ou envoyez-le au serveur
            }
        }
    }
}
