package com.packetsoftware.sime.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.packetsoftware.sime.controller.Frequencia;
import com.packetsoftware.sime.helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class FrequenciaDao {
    private SQLiteDatabase insere;
    private SQLiteDatabase ler;

    public FrequenciaDao(Context context) {
        DBHelper db = new DBHelper(context);
        insere = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    public boolean salvar(Frequencia frequencia){
        ContentValues cvFrequencia = new ContentValues();
        cvFrequencia.put("idfrequencia", frequencia.getIdfrequencia());
        cvFrequencia.put("qtalunospresentes", frequencia.getQtalunospresentes());
        cvFrequencia.put("qtalunosausentes", frequencia.getQtalunosausentes());
        cvFrequencia.put("dtfrequencia", frequencia.getDtfrequencia());
        cvFrequencia.put("status", 0);

        try {
            insere.insert("frequencia", null, cvFrequencia );
        }catch (Exception e){
            Log.d("simeapp", "salvar: "+ e.getMessage());
            return false;
        }

        return false;
    }

    public List<Frequencia> listar(){
        List<Frequencia> lista = new ArrayList<>();

        String sql = "SELECT * FROM frequencia;";
        Cursor cursor = ler.rawQuery(sql, null);



        while (cursor.moveToNext()){

            Frequencia frequencia = new Frequencia();

            String dt = cursor.getString(cursor.getColumnIndex("dtfrequencia"));
            Log.d("ddddd", "listar: "+ dt);
            frequencia.setDtfrequencia(dt);
            lista.add( frequencia );

        }
        return lista;
    }
}
