package br.imd.ufrn.checklist;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NovoChecklistActivity extends AppCompatActivity {

    private EditText editTextColaborador, editTextMatricula;
    private Spinner spinnerFrota, spinnerTipo;
    private RecyclerView recyclerView;
    private ChecklistAdapter checklistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_checklist);

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editTextColaborador = findViewById(R.id.editTextColaborador);
        editTextMatricula = findViewById(R.id.editTextMatricula);
        spinnerFrota = findViewById(R.id.spinnerFrota); // Alterado para Spinner
        spinnerTipo = findViewById(R.id.spinnerTipo);
        preencherSpinnerFrota();
        recyclerView = findViewById(R.id.recyclerViewChecklist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        checklistAdapter = new ChecklistAdapter(getChecklistItens());
        recyclerView.setAdapter(checklistAdapter);
    }

    private List<ChecklistItem> getChecklistItens() {
        List<ChecklistItem> checklistItens = new ArrayList<>();

        // Documentos
        checklistItens.add(new ChecklistItem("Documento CRLV"));
        checklistItens.add(new ChecklistItem("Credenciais da Empresa"));
        checklistItens.add(new ChecklistItem("Credenciais da Veículos"));
        checklistItens.add(new ChecklistItem("Credenciais da Motorista"));
        checklistItens.add(new ChecklistItem("Cartão de Abastecimento"));

        // Mecânica
        checklistItens.add(new ChecklistItem("Nível de Óleo"));
        checklistItens.add(new ChecklistItem("Nível de Água"));
        checklistItens.add(new ChecklistItem("Suspensão"));
        checklistItens.add(new ChecklistItem("Alinhamento/Balanceamento"));

        // Exterior do Veículo
        checklistItens.add(new ChecklistItem("Calotas"));
        checklistItens.add(new ChecklistItem("Pneus/Estepe"));
        checklistItens.add(new ChecklistItem("Triângulo"));
        checklistItens.add(new ChecklistItem("Macaco"));
        checklistItens.add(new ChecklistItem("Chave de Roda"));
        checklistItens.add(new ChecklistItem("Placa"));
        checklistItens.add(new ChecklistItem("Retrovisores"));
        checklistItens.add(new ChecklistItem("Iluminação"));
        checklistItens.add(new ChecklistItem("Vidros"));
        checklistItens.add(new ChecklistItem("Pintura do Veículo"));
        checklistItens.add(new ChecklistItem("Faróis/Lanternas"));

        // Interior do Veículo
        checklistItens.add(new ChecklistItem("Extintor interno (Verificar Validade)"));
        checklistItens.add(new ChecklistItem("Tapetes"));
        checklistItens.add(new ChecklistItem("Cinto de Segurança"));
        checklistItens.add(new ChecklistItem("Antena"));
        checklistItens.add(new ChecklistItem("Estofamento"));
        checklistItens.add(new ChecklistItem("Higiene"));

        // Equipamentos de Emergência
        checklistItens.add(new ChecklistItem("8 Cones"));
        checklistItens.add(new ChecklistItem("4 Sinalizadores Portáteis"));
        checklistItens.add(new ChecklistItem("4 Bandeiras Vermelhas"));
        checklistItens.add(new ChecklistItem("1 Luvas de Raspa"));
        checklistItens.add(new ChecklistItem("1 Lanterna"));
        checklistItens.add(new ChecklistItem("1 Trena 30M"));
        checklistItens.add(new ChecklistItem("4 Suportes para Bandeiras"));
        checklistItens.add(new ChecklistItem("Giroled"));
        checklistItens.add(new ChecklistItem("2 Extintores 4KG Tipo ABC"));
        checklistItens.add(new ChecklistItem("3 Rádios"));
        checklistItens.add(new ChecklistItem("1 Colete Refletivo"));

        return checklistItens;
    }

    private void preencherSpinnerFrota() {
        VeiculoDBHelper veiculoDBHelper = new VeiculoDBHelper(this);
        List<Veiculo> veiculos = veiculoDBHelper.getAllVeiculos();
        Set<String> setFrotas = new HashSet<>();
        for (Veiculo veiculo : veiculos) {
            setFrotas.add(veiculo.getFrota());
        }
        List<String> listaFrotas = new ArrayList<>(setFrotas);
        Collections.sort(listaFrotas, new Comparator<String>() {
            @Override
            public int compare(String frota1, String frota2) {
                return frota1.compareTo(frota2);
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaFrotas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrota.setAdapter(adapter);
    }
}
