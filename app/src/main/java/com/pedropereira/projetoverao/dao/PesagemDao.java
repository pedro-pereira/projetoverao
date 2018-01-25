package com.pedropereira.projetoverao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pedropereira.projetoverao.modelo.Pesagem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
                   + "dataHora DATETIME, "
                   + "latitude REAL, "
                   + "longitude REAL, "
                   + "filial TEXT, "
                   + "momento TEXT, "
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
        Long chave = obterProximaChavePesagem();

        ContentValues values = new ContentValues();
        values.put("chave", chave);
        values.put("dataHora", pesagem.getDataHora().toString());
        values.put("latitude", pesagem.getLatitude());
        values.put("longitude", pesagem.getLongitude());
        values.put("filial", pesagem.getFilial());
        values.put("momento", pesagem.getMomento());
        values.put("peso", pesagem.getPeso());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Pesagem> getLista() {
        List<Pesagem> lista = new ArrayList<Pesagem>();
        String sql = "SELECT chave, latitude, longitude, filial, momento, peso, dataHora FROM " + TABELA + " ORDER BY chave DESC";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        Pesagem pesagem = null;

        int i = 0;

        while (c. moveToNext()) {
            pesagem = new Pesagem();
            pesagem.setChave(c.getLong(c.getColumnIndex("chave")));
            pesagem.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
            pesagem.setLongitude(c.getDouble(c.getColumnIndex("longitude")));
            pesagem.setFilial(c.getString(c.getColumnIndex("filial")));
            pesagem.setMomento(c.getString(c.getColumnIndex("momento")));
            pesagem.setPeso(c.getDouble(c.getColumnIndex("peso")));

            Date dataFormatada = null;
            try {
                String dataHora = c.getString(c.getColumnIndex("dataHora"));
                SimpleDateFormat formatador = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                dataFormatada = formatador.parse(dataHora);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            pesagem.setDataHora(dataFormatada);

            lista.add(i, pesagem);
            i = i + 1;
        }
        return lista;
    }

    public void alterar(Pesagem pesagem) {
        ContentValues values = new ContentValues();
        values.put("dataHora", pesagem.getDataHora().toString());
        values.put("latitude", pesagem.getLatitude());
        values.put("longitude", pesagem.getLongitude());
        values.put("filial", pesagem.getFilial());
        values.put("momento", pesagem.getMomento());
        values.put("peso", pesagem.getPeso());

        String[] argumentos = { pesagem.getChave().toString() };
        getWritableDatabase().update(TABELA, values, "chave = ?", argumentos);
    }

    public void deletar(Pesagem pesagem) {
        String[] argumentos = { pesagem.getChave().toString() };
        getWritableDatabase().delete(TABELA, "chave = ?", argumentos);
    }

    public Long obterProximaChavePesagem() {

        String sql = "SELECT MAX(chave) AS maiorChave FROM " + TABELA + ";";
        Cursor c = getReadableDatabase(). rawQuery(sql, null);
        Long maiorChave = 0L;

        while (c.moveToNext()) {
            maiorChave = c.getLong(c.getColumnIndex("maiorChave"));
        }

        return maiorChave + 1L;
    }
}