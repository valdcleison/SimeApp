package com.packetsoftware.sime.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATA_BASE_NAME = "simeApp";

    public DBHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FREQUENCIA = "CREATE TABLE IF NOT EXISTS frequencia (" +
                "idfrequencia INTEGER PRIMARY KEY," +
                "qtalunosausentes INTEGER," +
                "qtalunospresentes INTEGER," +
                "dtfrequencia DATE," +
                "hrinicio DATETIME," +
                "hrtermino DATETIME," +
                "status INTEGER)";

        String CREATE_TABLE_FREQUENCIAALUNO = "CREATE TABLE IF NOT EXISTS frequenciaaluno (" +
                "idfrequenciaaluno INTEGER PRIMARY KEY," +
                "data DATE," +
                "hrentrada DATETIME," +
                "hrsaida DATETIME," +
                "matricula_idmatricula INTEGER," +
                "frequencia_idfrequencia INTEGER)";

        String CREATE_TABLE_MATRICULA = "CREATE TABLE IF NOT EXISTS matricula (" +
                "idmatricula INTEGER PRIMARY KEY," +
                "aluno_idaluno INTEGER," +
                "numeromatricula VARCHAR(45))";

        String CREATE_TABLE_ALUNO = "CREATE TABLE IF NOT EXISTS aluno (" +
                "idaluno INTEGER PRIMARY KEY," +
                "pessoa_idpessoa INTEGER," +
                "numeromatricula VARCHAR(45))";

        String CREATE_TABLE_PESSOA = "CREATE TABLE IF NOT EXISTS aluno (" +
                "idpessoa INTEGER PRIMARY KEY," +
                "nomepessoa VARCHAR(150)," +
                "cpfpessoa VARCHAR(11))";

        try{
            db.execSQL(CREATE_TABLE_FREQUENCIA);
            db.execSQL(CREATE_TABLE_FREQUENCIAALUNO);
            db.execSQL(CREATE_TABLE_MATRICULA);
            db.execSQL(CREATE_TABLE_ALUNO);
            db.execSQL(CREATE_TABLE_PESSOA);

        }catch (Exception e){
            Log.i("simeapp", "Erro ao criar tabela: " + e.getMessage());
        }
  }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

