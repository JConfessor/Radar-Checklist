package br.imd.ufrn.checklist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_LOGIN = "login";

    private String login;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView textViewUsuario = findViewById(R.id.textViewUsuario);
        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnNovoChecklist = findViewById(R.id.btnNovoChecklist);
        Button btnListarVeiculos = findViewById(R.id.btnListarVeiculos);
        Button btnListarColaboradores = findViewById(R.id.btnListarColaboradores);
        login = obterLoginSalvo();
        textViewUsuario.setText("Bem-vindo, " + login);
        btnLogout.setOnClickListener(v -> {
            Toast.makeText(MenuActivity.this, "Logout realizado.", Toast.LENGTH_SHORT).show();
            abrirTelaLogin();
        });

        btnNovoChecklist.setOnClickListener(v -> abrirTelaNovoChecklist());

        btnListarVeiculos.setOnClickListener(v -> {
            Toast.makeText(MenuActivity.this, "Abrindo Lista de Veículos", Toast.LENGTH_SHORT).show();
            abrirTelaListarVeiculos();
        });

        btnListarColaboradores.setOnClickListener(v -> {
            Toast.makeText(MenuActivity.this, "Abrindo Lista de Colaboradores", Toast.LENGTH_SHORT).show();
        });
    }

    private String obterLoginSalvo() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_LOGIN, "");
    }
    private void abrirTelaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); // Fecha a tela de menu após o logout
    }

    private void abrirTelaNovoChecklist() {
        Intent intent = new Intent(MenuActivity.this, NovoChecklistActivity.class);
        startActivity(intent);
    }

    private void abrirTelaListarVeiculos() {
        Intent intent = new Intent(MenuActivity.this, ListarVeiculosActivity.class);
        startActivity(intent);
    }
}
