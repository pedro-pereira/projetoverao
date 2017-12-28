package com.pedropereira.projetoverao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.pedropereira.projetoverao.dao.PesagemDao;
import com.pedropereira.projetoverao.modelo.Pesagem;

import java.util.List;

public class ListaPesagemActivity extends AppCompatActivity {

    private ListView listaPesagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pesagem);

        this.listaPesagem = (ListView) findViewById(R.id.lista_pesagem);

        Button novaPesagem = (Button) findViewById(R.id.bt_flutuante_nova_pesagem);
        novaPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencao = new Intent(ListaPesagemActivity.this, FormularioPesagemActivity.class);
                startActivity(intencao);
            }
        });

        PesagemDao pesagemDao = new PesagemDao(ListaPesagemActivity.this);
        List<Pesagem> pesagens = pesagemDao.getLista();

        final ArrayAdapter<Pesagem> adapter = new ArrayAdapter<Pesagem>(this, android.R.layout.simple_list_item_1, pesagens);
        this.listaPesagem.setAdapter(adapter);

    }
}
