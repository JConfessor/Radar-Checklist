package br.imd.ufrn.checklist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChecklistAdapter extends RecyclerView.Adapter<ChecklistAdapter.ChecklistViewHolder> {

    private List<ChecklistItem> checklistItens;

    public ChecklistAdapter(List<ChecklistItem> checklistItens) {
        this.checklistItens = checklistItens;
    }

    @NonNull
    @Override
    public ChecklistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checklist, parent, false);
        return new ChecklistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChecklistViewHolder holder, int position) {
        ChecklistItem checklistItem = checklistItens.get(position);
        holder.textViewItemName.setText(checklistItem.getItemName());
    }

    @Override
    public int getItemCount() {
        return checklistItens.size();
    }

    public static class ChecklistViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItemName;
        RadioButton radioButtonConforme;
        RadioButton radioButtonNaoConforme;

        public ChecklistViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewItemName = itemView.findViewById(R.id.textViewItemName);
            radioButtonConforme = itemView.findViewById(R.id.radioButtonConforme);
            radioButtonNaoConforme = itemView.findViewById(R.id.radioButtonNaoConforme);

            radioButtonConforme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButtonNaoConforme.setChecked(false);
                }
            });

            radioButtonNaoConforme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButtonConforme.setChecked(false);
                }
            });
        }
    }
}
