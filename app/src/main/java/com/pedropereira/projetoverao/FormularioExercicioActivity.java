package com.pedropereira.projetoverao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pedropereira.projetoverao.modelo.Pesagem;

public class FormularioExercicioActivity extends AppCompatActivity {

    EditText edtQuantidade;
    EditText edtQuantidadeUnidade;
    EditText edtDescricao;
    EditText edtIntensidade;
    EditText edtIntensidadeUnidade;
    Button btnAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_exercicio);

        // Recuperando parametro em caso de edicao
        final Pesagem pesagem = (Pesagem) getIntent().getSerializableExtra("pesagem");

        edtQuantidade = findViewById(R.id.edt_quantidade);
        String quantidade = edtQuantidade.getText().toString();
        Toast.makeText(this, "Quantidade: " + quantidade, Toast.LENGTH_LONG).show();

        edtQuantidadeUnidade = findViewById(R.id.edt_quantidadeUnidade);
        edtDescricao = findViewById(R.id.edt_descricao);
        edtIntensidade = findViewById(R.id.edt_intensidade);
        edtIntensidadeUnidade = findViewById(R.id.edt_intensidadeUnidade);

        Log.i("PESAGEM_EM_EXERCIO_CP", pesagem.toString());

        btnAdicionar = findViewById(R.id.btn_adicionar);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("PESAGEM_EM_EXERCIO_CA", pesagem.toString());
            }
        });
    }
}
