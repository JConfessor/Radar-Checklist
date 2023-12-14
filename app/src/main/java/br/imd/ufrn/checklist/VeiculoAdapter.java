package br.imd.ufrn.checklist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VeiculoAdapter extends RecyclerView.Adapter<VeiculoAdapter.VeiculoViewHolder> {

    private List<Veiculo> veiculos;

    public VeiculoAdapter(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    @NonNull
    @Override
    public VeiculoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_veiculo, parent, false);
        return new VeiculoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VeiculoViewHolder holder, int position) {
        Veiculo veiculo = veiculos.get(position);

        holder.textViewPlaca.setText("Placa: ");
        holder.placa.setText(veiculo.getPlaca());

        holder.textViewModelo.setText("Modelo: ");
        holder.modelo.setText(veiculo.getModelo());

        holder.textViewFrota.setText("Frota: ");
        holder.frota.setText(veiculo.getFrota());
    }

    @Override
    public int getItemCount() {
        return veiculos.size();
    }
    public void atualizarVeiculos(List<Veiculo> veiculosAtualizados) {
        veiculos = veiculosAtualizados;
        notifyDataSetChanged();
    }

    public static class VeiculoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPlaca;
        TextView placa;
        TextView textViewModelo;
        TextView modelo;
        TextView textViewFrota;
        TextView frota;

        public VeiculoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPlaca = itemView.findViewById(R.id.textViewPlaca);
            placa = itemView.findViewById(R.id.placa);
            textViewModelo = itemView.findViewById(R.id.textViewModelo);
            modelo = itemView.findViewById(R.id.modelo);
            textViewFrota = itemView.findViewById(R.id.textViewFrota);
            frota = itemView.findViewById(R.id.frota);
        }
    }
}