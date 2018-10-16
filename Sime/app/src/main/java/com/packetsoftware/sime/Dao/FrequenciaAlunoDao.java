package com.packetsoftware.sime.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.packetsoftware.sime.FrequenciaActivity;
import com.packetsoftware.sime.controller.Frequencia;
import com.packetsoftware.sime.controller.Frequenciaaluno;
import com.packetsoftware.sime.helper.DBHelper;

public class FrequenciaAlunoDao {
    private SQLiteDatabase insere;
    private SQLiteDatabase ler;

    public FrequenciaAlunoDao(Context context) {
        DBHelper db = new DBHelper(context);
        insere = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    public boolean salvar(Frequenciaaluno frequenciaaluno){
        ContentValues cvFrequenciaAluno = new ContentValues();
        cvFrequenciaAluno.put("idfrequenciaaluno", frequenciaaluno.getIdfrequenciaaluno());
        cvFrequenciaAluno.put("data", frequenciaaluno.getData());
        cvFrequenciaAluno.put("hrentrada", frequenciaaluno.getHrentrada());
        cvFrequenciaAluno.put("frequencia_idfrequencia", frequenciaaluno.getFrequencia().getIdfrequencia());

        try {
            insere.insert("frequenciaaluno", null, cvFrequenciaAluno );
        }catch (Exception e){
            Log.d("simeapp", "salvar: "+ e.getMessage());
            return false;
        }

        return false;
    }

    public boolean editar(Frequenciaaluno frequenciaaluno){
        return false;
    }

    public boolean deletar(Frequenciaaluno frequenciaaluno){
        return false;
    }

}
