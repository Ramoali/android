package com.example.absenceapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absenceapp.R;
import com.example.absenceapp.model.Absence;

import java.util.List;

public class AbsenceAdapter extends RecyclerView.Adapter<AbsenceAdapter.AbsenceViewHolder> {

    private List<Absence> absenceList;
    private Activity activity;

    private static final int FILE_PICKER_REQUEST_CODE = 101;

    public AbsenceAdapter(List<Absence> absences, Activity activity) {
        this.absenceList = absences;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AbsenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_absence_etudiant, parent, false);
        return new AbsenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsenceViewHolder holder, int position) {
        Absence absence = absenceList.get(position);
        holder.txtMatiere.setText("Matière: " + absence.getMatiere());
        holder.txtDate.setText("Date: " + absence.getDate());
        holder.txtSeance.setText("Séance: " + absence.getSeance());
        holder.txtJustifiee.setText("Justifiée: " + (absence.isEstJustifiee() ? "Oui" : "Non"));

        holder.btnJustifier.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            activity.startActivityForResult(Intent.createChooser(intent, "Sélectionner un fichier de justification"), FILE_PICKER_REQUEST_CODE);
        });
    }

    @Override
    public int getItemCount() {
        return absenceList.size();
    }

    static class AbsenceViewHolder extends RecyclerView.ViewHolder {
        TextView txtMatiere, txtDate, txtSeance, txtJustifiee;
        Button btnJustifier;

        public AbsenceViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMatiere = itemView.findViewById(R.id.txt_matiere);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtSeance = itemView.findViewById(R.id.txt_seance);
            txtJustifiee = itemView.findViewById(R.id.txt_justifiee);
            btnJustifier = itemView.findViewById(R.id.btn_justifier);
        }
    }
}
