package com.packetsoftware.sime.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.packetsoftware.sime.controller.Matricula;
import com.packetsoftware.sime.helper.DBHelper;

public class MatriculaDao {
    private SQLiteDatabase insere;
    private SQLiteDatabase ler;

    public MatriculaDao(Context context) {
        DBHelper db = new DBHelper(context);
        insere = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    public boolean salvar(Matricula matricula){
        ContentValues cvMatricula = new ContentValues();
        cvMatricula.put("idmatricula", matricula.getIdmatricula());
        cvMatricula.put("aluno_idaluno", matricula.getAluno().getIdaluno());
        cvMatricula.put("numeromatricula", matricula.getNumeromatricula());

        try {
            insere.insert("matricula", null, cvMatricula );
        }catch (Exception e){
            Log.d("simeapp", "salvar: "+ e.getMessage());
            return false;
        }

        return false;
    }
}
