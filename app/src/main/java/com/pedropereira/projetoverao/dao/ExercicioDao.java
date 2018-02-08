package com.pedropereira.projetoverao.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pedropereira.projetoverao.modelo.Exercicio;
import com.pedropereira.projetoverao.modelo.Pesagem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro.Pereira on 02/02/2018.
 */

public class ExercicioDao extends SQLiteOpenHelper {

    private static final String DATABASE = "appProjetoVerao";
    private static final int VERSAO = 16;

    private static final String TABELA = "EXERCICIO";

    public ExercicioDao(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABELA + " ("
                + "id INTEGER PRIMARY KEY, "
                + "quantidade REAL, "
                + "quantidadeUnidade TEXT, "
                + "descricao TEXT, "
                + "intensidade REAL, "
                + "intensidadeUnidade TEXT, "
                + "observacao TEXT, "
                + "id_pesagem INTEGER "
                + " );";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public List<Exercicio> getExercicosDeUmaPesagem() {

        List<Exercicio> lista = new ArrayList<Exercicio>();
        String sql = "SELECT id, quantidade, quantidadeUnidade, descricao, intensidade, intensidadeUnidade, observacao, id_pesagem FROM " + TABELA + " WHERE id_pesagem = ORDER BY chave DESC";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        Exercicio exercicio = null;

        return null;
    }
}
