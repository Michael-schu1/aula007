package br.fatec;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_produto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public  static List<Produto> listaProdutos = new ArrayList<Produto>();
    public void salvarProduto(View view ){
        EditText edtDescricao = findViewById(R.id.editDescricao);
        EditText editQuantidade = findViewById(R.id.editQuantidade);
        EditText editValor = findViewById(R.id.editValor);

        Integer quantidade = Integer.parseInt(editQuantidade.getText().toString());
        Double valor = Double.parseDouble(editValor.getText().toString());

        Produto novoProduto = new Produto();
        novoProduto.setDescricao(edtDescricao.getText().toString());
        novoProduto.setQuantidade(quantidade);
        novoProduto.setValor(valor);

        listaProdutos.add(novoProduto);
        editQuantidade.setText("");
        editValor.setText("");
        edtDescricao.setText("");

        TextView viewProdutos = findViewById(R.id.viewProdutos);
        String produtos = "";
        for(Produto p : listaProdutos) {
            produtos += p.getDescricao() + "  -  " + p.getQuantidade() + "  -  " + p.getValor() + "\n";
        }
        viewProdutos.setText(produtos);

    }
}