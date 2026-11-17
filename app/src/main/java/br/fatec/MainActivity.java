package br.fatec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void abrirMenu(View view) {
        EditText editUsuario = findViewById(R.id.editUsuario);
        EditText editSenha = findViewById(R.id.editSenha);

        String username = editUsuario.getText().toString().trim();
        String senha = editSenha.getText().toString().trim();

        boolean authenticated = false;
        for (User u : DataRepository.usuarios) {
            if (u.getUsername().equals(username) && u.getPassword().equals(senha)) {
                authenticated = true;
                DataRepository.currentUser = u.getName();
                break;
            }
        }

        if (authenticated) {
            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MenuActivity.class));
        } else {
            Toast.makeText(this, "Usuário e/ou senha inválidos", Toast.LENGTH_LONG).show();
        }
    }

    public void abrirCadastroUsuario(View view) {
        startActivity(new Intent(this, CadastroUsuarioActivity.class));
    }
}
