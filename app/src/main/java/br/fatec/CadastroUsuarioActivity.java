package br.fatec;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CadastroUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void salvarUsuario(View view) {
        EditText editNome = findViewById(R.id.editNomeUsuario);
        EditText editUser = findViewById(R.id.editUsername);
        EditText editSenha = findViewById(R.id.editSenhaUsuario);

        String nome = editNome.getText().toString().trim();
        String username = editUser.getText().toString().trim();
        String senha = editSenha.getText().toString().trim();

        if (nome.isEmpty() || username.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if username already exists
        for (User u : DataRepository.usuarios) {
            if (u.getUsername().equals(username)) {
                Toast.makeText(this, "Usuário já existe!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        DataRepository.usuarios.add(new User(username, senha, nome));
        Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
        finish();
    }
}
