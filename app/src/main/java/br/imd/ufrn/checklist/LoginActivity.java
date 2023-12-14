package br.imd.ufrn.checklist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_LOGIN = "login";

    private EditText editTextLogin, editTextSenha;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextLogin = findViewById(R.id.editTextLogin);
        editTextSenha = findViewById(R.id.editTextSenha);

        databaseHelper = new DatabaseHelper(this);

        Button btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(v -> realizarLogin());

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(v -> abrirTelaCadastro());
    }

    private void realizarLogin() {
        String login = editTextLogin.getText().toString();
        String senha = editTextSenha.getText().toString();

        if (verificarCredenciais(login, senha)) {
            salvarLogin(login);
            Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
            abrirTelaMenu();
        } else {
            Toast.makeText(this, "Login falhou. Verifique suas credenciais.", Toast.LENGTH_SHORT).show();
        }
    }

    private void abrirTelaMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean verificarCredenciais(String login, String senha) {
        return databaseHelper.verificarCredenciais(login, senha);
    }

    private void abrirTelaCadastro() {
        startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
    }

    private void salvarLogin(String login) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_LOGIN, login);
        editor.apply();
    }
}
