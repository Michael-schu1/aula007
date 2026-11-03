package br.fatec;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListaProdutosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_produtos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.carregarCardsProdutos();
    }

    private void carregarCardsProdutos(){

        LinearLayout container = findViewById(R.id.listaProdutos);

        // Fundo off-white limpo para destacar a sombra projetada pelo card escuro
        container.setBackgroundColor(Color.parseColor("#F5F7F8"));

        int corFundoCard = Color.parseColor("#43545B");
        int corBordaSombra = Color.parseColor("#2E3A3F"); // Tom mais escuro para criar profundidade na borda
        int corTextoDescricao = Color.parseColor("#FFFFFF");
        int corTextoValor = Color.parseColor("#81D4FA"); // Ciano neon suave para o preço contrastar bem
        int corTextoQuantidade = Color.parseColor("#B0BEC5");


        for (Produto p : ProdutoActivity.listaProdutos) {

            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            // Margens para o card flutuar e a sombra aparecer em toda a volta
            parametros.setMargins(32, 20, 32, 20);
            card.setLayoutParams(parametros);

            // Padding interno generoso
            card.setPadding(44, 40, 44, 40);

            // Cria o fundo com cantos arredondados e efeito de borda/sombra integrada
            GradientDrawable shape = new GradientDrawable();
            shape.setShape(GradientDrawable.RECTANGLE);
            shape.setCornerRadius(28f); // Cantos elegantemente curvados
            shape.setColor(corFundoCard);
            // Define uma borda de 2 pixels mais escura para simular sombreamento 3D perfeito
            shape.setStroke(2, corBordaSombra);
            card.setBackground(shape);

            // Sombra física real (Android 5.0+)
            card.setElevation(12f);

            // Descrição do Produto (DESTAQUE MÁXIMO)
            TextView descricao = new TextView(this);
            descricao.setText(p.getDescricao().toUpperCase()); // Converte para caixa alta (estilo premium)
            descricao.setTextSize(18f); // Fonte grande e marcante
            descricao.setTextColor(corTextoDescricao);
            // Fonte condensada moderna com negrito acentuado
            descricao.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD));
            descricao.setPadding(0, 0, 0, 14); // Mais espaço antes do preço

            // Valor do Produto
            TextView valor = new TextView(this);
            valor.setText("R$ " + String.format("%.2f", p.getValor()));
            valor.setTextSize(14f); // Menor para a descrição reinar sozinha no card
            valor.setTextColor(corTextoValor);
            valor.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));

            TextView quantidade = new TextView(this);
            // Assume que o método na sua classe Produto seja getQuantidade() ou similar
            quantidade.setText("Quantidade: " + p.getQuantidade());
            quantidade.setTextSize(13f); // Um pouco menor para respeitar a hierarquia visual
            quantidade.setTextColor(corTextoQuantidade);
            quantidade.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));



            card.addView(descricao);
            card.addView(valor);
            card.addView(quantidade);

            container.addView(card);
        }
    }
}
