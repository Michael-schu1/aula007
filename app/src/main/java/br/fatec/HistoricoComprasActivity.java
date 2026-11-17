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

public class HistoricoComprasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historico_compras);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        carregarHistorico();
    }

    private void carregarHistorico() {
        LinearLayout container = findViewById(R.id.listaHistoricoContainer);
        if (container == null) return;

        container.setBackgroundColor(Color.parseColor("#020617"));

        int corFundoCard = Color.parseColor("#001D3D");
        int corBordaSombra = Color.parseColor("#00B4D8");
        int corTextoTitulo = Color.parseColor("#FFFFFF");
        int corTextoValor = Color.parseColor("#00B4D8");
        int corTextoDetalhes = Color.parseColor("#ADE8F4");

        if (DataRepository.compras.isEmpty()) {
            TextView vazio = new TextView(this);
            vazio.setText("Nenhuma compra realizada ainda.");
            vazio.setTextSize(16f);
            vazio.setTextColor(Color.parseColor("#ADE8F4"));
            vazio.setPadding(32, 32, 32, 32);
            container.addView(vazio);
            return;
        }

        for (Compra c : DataRepository.compras) {
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
            titulo.setText("FILME: " + c.getFilme().getTitulo());
            titulo.setTextSize(18f);
            titulo.setTextColor(corTextoTitulo);
            titulo.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD));
            titulo.setPadding(0, 0, 0, 8);

            TextView qtd = new TextView(this);
            qtd.setText("Quantidade: " + c.getQuantidade());
            qtd.setTextSize(14f);
            qtd.setTextColor(corTextoDetalhes);

            TextView total = new TextView(this);
            total.setText("Total: R$ " + String.format("%.2f", c.getValorTotal()));
            total.setTextSize(15f);
            total.setTextColor(corTextoValor);
            total.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
            total.setPadding(0, 4, 0, 4);

            TextView usuario = new TextView(this);
            usuario.setText("Comprador: " + c.getUsuario());
            usuario.setTextSize(13f);
            usuario.setTextColor(corTextoDetalhes);

            card.addView(titulo);
            card.addView(qtd);
            card.addView(total);
            card.addView(usuario);

            container.addView(card);
        }
    }
}
