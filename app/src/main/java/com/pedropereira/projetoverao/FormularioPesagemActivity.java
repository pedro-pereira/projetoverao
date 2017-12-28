package com.pedropereira.projetoverao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pedropereira.projetoverao.dao.PesagemDao;
import com.pedropereira.projetoverao.modelo.Pesagem;
import com.pedropereira.projetoverao.modelo.PesagemHelper;

public class FormularioPesagemActivity extends AppCompatActivity {

    private PesagemHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_pesagem);


        Button botaoInserirPesagem = (Button) findViewById(R.id.btn_inserir_pesagem);
        helper = new PesagemHelper(this);

        botaoInserirPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FormularioPesagemActivity.this, "Clicou no inserir...", Toast.LENGTH_LONG).show();
                Pesagem pesagemDoFormulario = helper.getPesagemDoFormulario();

                PesagemDao pesagemDao = new PesagemDao(FormularioPesagemActivity.this);
                pesagemDao.inserir(pesagemDoFormulario);

                pesagemDao.close();

                finish();
            }
        });
    }
}
