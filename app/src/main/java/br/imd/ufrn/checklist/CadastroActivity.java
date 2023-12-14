package br.imd.ufrn.checklist;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    private EditText editTextUsuario, editTextSenhaCadastro, editTextConfirmaSenha, editTextEmail;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextSenhaCadastro = findViewById(R.id.editTextSenhaCadastro);
        editTextConfirmaSenha = findViewById(R.id.editTextConfirmaSenha);
        editTextEmail = findViewById(R.id.editTextEmail);
        CheckBox checkBoxMostrarSenha = findViewById(R.id.checkBoxMostrarSenha);

        databaseHelper = new DatabaseHelper(this);

        Button btnCadastrarUsuario = findViewById(R.id.btnCadastrarUsuario);
        btnCadastrarUsuario.setOnClickListener(v -> cadastrarUsuario());

        checkBoxMostrarSenha.setOnCheckedChangeListener((buttonView, isChecked) -> mostrarOcultarSenha(isChecked));
    }

    private void cadastrarUsuario() {
        String usuario = editTextUsuario.getText().toString();
        String senha = editTextSenhaCadastro.getText().toString();
        String confirmaSenha = editTextConfirmaSenha.getText().toString();
        String email = editTextEmail.getText().toString();

        Log.d("CadastroActivity", "Dados do usuário: " + usuario + ", " + email);

        if (!senha.equals(confirmaSenha)) {
            Toast.makeText(this, "As senhas não coincidem. Tente novamente.", Toast.LENGTH_SHORT).show();
            return;
        }

        String campoDuplicado = databaseHelper.verificarDuplicatas(usuario, email);
        if (!campoDuplicado.isEmpty()) {
            Toast.makeText(this, campoDuplicado + " já cadastrado. Tente outro.", Toast.LENGTH_SHORT).show();
            return;
        }

        Usuario novoUsuario = new Usuario(usuario, senha, email);

        long id = databaseHelper.inserirUsuario(novoUsuario);
        if (id != -1) {
            Toast.makeText(this, "Usuário cadastrado com sucesso! ID: " + id, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Falha ao cadastrar usuário.", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarOcultarSenha(boolean mostrar) {
        int tipoSenha = mostrar ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        editTextSenhaCadastro.setInputType(tipoSenha);
        editTextConfirmaSenha.setInputType(tipoSenha);
    }
}
