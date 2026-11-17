package br.fatec;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CompraFilmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compra_filme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        carregarSpinnerFilmes();
    }

    private void carregarSpinnerFilmes() {
        Spinner spinnerFilmes = findViewById(R.id.spinnerFilmes);
        if (spinnerFilmes != null) {
            ArrayAdapter<Filme> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line,
                    DataRepository.filmes);
            spinnerFilmes.setAdapter(adapter);
        }
    }

    public void finalizarCompra(View view) {
        Spinner spinnerFilmes = findViewById(R.id.spinnerFilmes);
        EditText editQtd = findViewById(R.id.editQuantidadeCompra);

        if (spinnerFilmes == null || spinnerFilmes.getSelectedItem() == null) {
            Toast.makeText(this, "Nenhum filme selecionado!", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtdStr = editQtd.getText().toString().trim();
        if (qtdStr.isEmpty()) {
            Toast.makeText(this, "Informe a quantidade!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int qtd = Integer.parseInt(qtdStr);
            if (qtd <= 0) {
                Toast.makeText(this, "Quantidade deve ser maior que zero!", Toast.LENGTH_SHORT).show();
                return;
            }

            Filme filmeSelecionado = (Filme) spinnerFilmes.getSelectedItem();
            double total = qtd * filmeSelecionado.getPreco();

            Compra compra = new Compra(filmeSelecionado, qtd, total, DataRepository.currentUser);
            DataRepository.compras.add(compra);

            Toast.makeText(this, "Compra realizada com sucesso! Total: R$ " + String.format("%.2f", total), Toast.LENGTH_LONG).show();
            finish();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Quantidade inválida!", Toast.LENGTH_SHORT).show();
        }
    }
}
