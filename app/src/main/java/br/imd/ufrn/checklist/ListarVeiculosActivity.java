package br.imd.ufrn.checklist;

import android.app.Dialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.InputType;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListarVeiculosActivity extends AppCompatActivity {

    private VeiculoDBHelper dbHelper;
    private VeiculoAdapter veiculoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_veiculos);

        dbHelper = new VeiculoDBHelper(this);
        veiculoAdapter = new VeiculoAdapter(getVeiculos());

        RecyclerView recyclerView = findViewById(R.id.recyclerViewVeiculos);
        recyclerView.setAdapter(veiculoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btnAdicionarVeiculo = findViewById(R.id.btnAdicionarVeiculo);
        btnAdicionarVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogAdicionarVeiculo();
            }
        });

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mostrarDialogAdicionarVeiculo() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_adicionar_veiculo);

        final EditText editTextPlaca = dialog.findViewById(R.id.editTextPlaca);
        final EditText editTextFrota = dialog.findViewById(R.id.editTextFrota);
        final EditText editTextModelo = dialog.findViewById(R.id.editTextModelo);
        Button btnAdicionar = dialog.findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placa = editTextPlaca.getText().toString();
                String frota = editTextFrota.getText().toString();
                String modelo = editTextModelo.getText().toString();

                if (!placa.isEmpty() && !frota.isEmpty() && !modelo.isEmpty()) {
                    dbHelper.addVeiculo(new Veiculo(placa, frota, modelo));
                    veiculoAdapter.atualizarVeiculos(getVeiculos());
                    dialog.dismiss();
                } else {
                    Toast.makeText(ListarVeiculosActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }
    private List<Veiculo> getVeiculos() {
        return dbHelper.getAllVeiculos();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}
