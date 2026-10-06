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

    private static final String USUARIO = "admin";
    private  static  final String SENHA = "fatec";

    public void abrirMenu(View view) {

        EditText editUsuario = findViewById(R.id.editUsuario);
        EditText editSenha = findViewById(R.id.editSenha);

        if (USUARIO.equals(editUsuario.getText().toString()) &&
                SENHA.equals(editSenha.getText().toString())) {


            startActivity(new Intent(this, MenuActivity.class));
        }else {
            Toast.makeText(this, "Usuario e/ou senha inválida", Toast.LENGTH_LONG).show();
        }
    }
}