package com.pedropereira.projetoverao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
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
    private ArrayAdapter<Pesagem> adapter;
    private Pesagem pesagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pesagem);

        this.listaPesagem = (ListView) findViewById(R.id.lista_pesagem);

        registerForContextMenu(listaPesagem);

        Button novaPesagem = (Button) findViewById(R.id.bt_flutuante_nova_pesagem);
        novaPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencao = new Intent(ListaPesagemActivity.this, FormularioPesagemActivity.class);
                startActivity(intencao);
            }
        });

        this.listaPesagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Pesagem pesagemSelecionada = (Pesagem) parent.getItemAtPosition(position);

                Intent irParaOFormulario = new Intent(ListaPesagemActivity.this, FormularioPesagemActivity.class);
                irParaOFormulario.putExtra("pesagemSelecionada", pesagemSelecionada);

                startActivity(irParaOFormulario);

            }
        });

        this.listaPesagem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pesagem = (Pesagem) parent.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        PesagemDao pesagemDao = new PesagemDao(ListaPesagemActivity.this);
        List<Pesagem> pesagens = pesagemDao.getLista();

        adapter = new ArrayAdapter<Pesagem>(this, android.R.layout.simple_list_item_1, pesagens);
        this.listaPesagem.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem deletar = menu.add("Deletar");
        menu.add("Ver exercícios");
        menu.add("Ver no mapa");
        menu.add("Ver foto");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                PesagemDao pesagemDao = new PesagemDao(ListaPesagemActivity.this);
                pesagemDao.deletar(pesagem);
                pesagemDao.close();

                carregarListaPesagens();

                return false;
            }
        });

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private void carregarListaPesagens() {
        PesagemDao pesagemDao = new PesagemDao(ListaPesagemActivity.this);
        List<Pesagem> pesagens = pesagemDao.getLista();

        this.adapter = new ArrayAdapter<Pesagem>(this, android.R.layout.simple_list_item_1, pesagens);
        this.listaPesagem.setAdapter(adapter);
    }
}