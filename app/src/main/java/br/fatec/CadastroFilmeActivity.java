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

public class CadastroFilmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_filme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void salvarFilme(View view) {
        EditText editTitulo = findViewById(R.id.editTitulo);
        EditText editGenero = findViewById(R.id.editGenero);
        EditText editPreco = findViewById(R.id.editPreco);
        EditText editAno = findViewById(R.id.editAno);
        EditText editDescricao = findViewById(R.id.editDescricaoFilme);

        String titulo = editTitulo.getText().toString().trim();
        String genero = editGenero.getText().toString().trim();
        String precoStr = editPreco.getText().toString().trim();
        String anoStr = editAno.getText().toString().trim();
        String descricao = editDescricao.getText().toString().trim();

        if (titulo.isEmpty() || genero.isEmpty() || precoStr.isEmpty() || anoStr.isEmpty()) {
            Toast.makeText(this, "Preencha os campos obrigatórios!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double preco = Double.parseDouble(precoStr);
            int ano = Integer.parseInt(anoStr);

            Filme novoFilme = new Filme(titulo, genero, preco, ano, descricao);
            DataRepository.filmes.add(novoFilme);

            Toast.makeText(this, "Filme cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Preço ou ano inválidos!", Toast.LENGTH_SHORT).show();
        }
    }
}
