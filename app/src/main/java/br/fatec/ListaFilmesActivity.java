package br.fatec;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListaFilmesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_filmes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        carregarCardsFilmes();
    }

    private void carregarCardsFilmes() {
        LinearLayout container = findViewById(R.id.listaFilmesContainer);
        if (container == null) return;

        container.setBackgroundColor(Color.parseColor("#020617"));

        int corFundoCard = Color.parseColor("#001D3D");
        int corBordaSombra = Color.parseColor("#00B4D8");
        int corTextoTitulo = Color.parseColor("#FFFFFF");
        int corTextoPreco = Color.parseColor("#00B4D8");
        int corTextoDetalhes = Color.parseColor("#ADE8F4");

        for (Filme f : DataRepository.filmes) {
            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            parametros.setMargins(32, 20, 32, 20);
            card.setLayoutParams(parametros);
            card.setPadding(44, 40, 44, 40);

            GradientDrawable shape = new GradientDrawable();
            shape.setShape(GradientDrawable.RECTANGLE);
            shape.setCornerRadius(28f);
            shape.setColor(corFundoCard);
            shape.setStroke(2, corBordaSombra);
            card.setBackground(shape);
            card.setElevation(12f);

            TextView titulo = new TextView(this);
            titulo.setText(f.getTitulo().toUpperCase() + " (" + f.getAno() + ")");
            titulo.setTextSize(18f);
            titulo.setTextColor(corTextoTitulo);
            titulo.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD));
            titulo.setPadding(0, 0, 0, 8);

            TextView genero = new TextView(this);
            genero.setText("Gênero: " + f.getGenero());
            genero.setTextSize(14f);
            genero.setTextColor(corTextoDetalhes);

            TextView preco = new TextView(this);
            preco.setText("R$ " + String.format("%.2f", f.getPreco()));
            preco.setTextSize(15f);
            preco.setTextColor(corTextoPreco);
            preco.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
            preco.setPadding(0, 4, 0, 4);

            TextView descricao = new TextView(this);
            descricao.setText(f.getDescricao());
            descricao.setTextSize(13f);
            descricao.setTextColor(corTextoDetalhes);

            card.addView(titulo);
            card.addView(genero);
            card.addView(preco);
            card.addView(descricao);

            container.addView(card);
        }
    }
}
