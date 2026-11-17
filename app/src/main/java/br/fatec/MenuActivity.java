package br.fatec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView txtBemVindo = findViewById(R.id.txtBemVindo);
        if (txtBemVindo != null && !DataRepository.currentUser.isEmpty()) {
            txtBemVindo.setText("Bem-vindo(a), " + DataRepository.currentUser + "!");
        }
    }

    public void abrirCadastroFilme(View view) {
        startActivity(new Intent(this, CadastroFilmeActivity.class));
    }

    public void abrirListaFilmes(View view) {
        startActivity(new Intent(this, ListaFilmesActivity.class));
    }

    public void abrirCompra(View view) {
        startActivity(new Intent(this, CompraFilmeActivity.class));
    }

    public void abrirHistorico(View view) {
        startActivity(new Intent(this, HistoricoComprasActivity.class));
    }

    public void abrirSobre(View view) {
        startActivity(new Intent(this, SobreAppActivity.class));
    }
}
