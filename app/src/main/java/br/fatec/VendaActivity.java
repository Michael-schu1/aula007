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


public class VendaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_venda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.carrregarSpinnerProdutos();
    }

    public void calcular(View view){
        Spinner spinnerProdutos = findViewById(R.id.spinnerProdutos);
        EditText editQuantidadeVenda = findViewById(R.id.editQuantidadeVenda);
        Integer quantidade = Integer.parseInt(editQuantidadeVenda.getText().toString());

        Produto produtoSelecionado = (Produto) spinnerProdutos.getSelectedItem();

        Double total = quantidade * produtoSelecionado.getValor();

        Toast.makeText(this, "valor venda : " + total.toString(), Toast.LENGTH_LONG).show();
    }
    private void carrregarSpinnerProdutos(){
        Spinner spinnerProdutos = findViewById(R.id.spinnerProdutos);

        ArrayAdapter<Produto> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                ProdutoActivity.listaProdutos);

        spinnerProdutos.setAdapter(adapter);
    }
}