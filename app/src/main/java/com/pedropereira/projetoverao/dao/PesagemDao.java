package com.pedropereira.projetoverao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pedropereira.projetoverao.modelo.Pesagem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro.Pereira on 28/12/2017.
 */

public class PesagemDao extends SQLiteOpenHelper {

    private static final String DATABASE = "appProjetoVerao";
    private static final int VERSAO = 1;

    private static final String TABELA = "PESAGEM";

    public PesagemDao(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABELA + " ("
                   + "chave INTEGER PRIMARY KEY, "
                   + "dataHora TEXT, "
                   + "momento TEXT, "
                   + "local TEXT, "
                   + "peso REAL"
                   + " );";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void inserir(Pesagem pesagem) {
        ContentValues values = new ContentValues();
        values.put("chave", pesagem.getChave());
        values.put("dataHora", pesagem.getDataHora());
        values.put("momento", pesagem.getMomento());
        values.put("local", pesagem.getLocal());
        values.put("peso", pesagem.getPeso());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Pesagem> getLista() {

        List<Pesagem> lista = new ArrayList<Pesagem>();
        String sql = "SELECT * FROM " + TABELA + ";";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        Pesagem pesagem = null;

        while (c.moveToNext()) {
            pesagem = new Pesagem();
            pesagem.setChave(c.getLong(c.getColumnIndex("chave")));
            pesagem.setDataHora(c.getString(c.getColumnIndex("dataHora")));
            pesagem.setMomento(c.getString(c.getColumnIndex("momento")));
            pesagem.setLocal(c.getString(c.getColumnIndex("local")));
            pesagem.setPeso(c.getDouble(c.getColumnIndex("peso")));

            lista.add(pesagem);
        }
        return lista;
    }
}
