package com.packetsoftware.sime.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.packetsoftware.sime.controller.Frequencia;
import com.packetsoftware.sime.helper.DBHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrequenciaDao {
    private SQLiteDatabase insere;
    private SQLiteDatabase ler;
    private Context contexto;

    public FrequenciaDao(Context context) {
        contexto = context;
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
        insere.close();
        return true;
    }

    public boolean deletar(Frequencia frequencia){

        String[] values = {frequencia.getDtfrequencia()};
        try{
            insere.delete("frequencia", "idfrequencia", values);

        }catch (Exception e){
            return false;
        }
        insere.close();
        return  true;
    }

    public List<Frequencia> listar(){
        List<Frequencia> lista = new ArrayList<>();

        String sql = "SELECT * FROM frequencia;";
        Cursor cursor = ler.rawQuery(sql, null);



        while (cursor.moveToNext()){

            Frequencia frequencia = new Frequencia();

            String dt = cursor.getString(cursor.getColumnIndex("dtfrequencia"));
            frequencia.setDtfrequencia(dt);
            lista.add( frequencia );

        }
        return lista;
    }

    public Frequencia listarHoje(){

        Date data = new Date();
        String[] values = new String[]{data.toString()};
        String [] campos = new String[]{"idfrequencia", "qtalunospresentes", "qtalunosausentes", "dtfrequencia", "status"};
        Cursor cursor = ler.query("frequencia ", campos, " dtfrequencia = ? ", values, null, null, null);
        Frequencia frequencia = new Frequencia();

        if(cursor != null){
            if(cursor.getString(5) != null && cursor.getString(6) == null){
                cursor.moveToFirst();

                frequencia = new Frequencia();
                frequencia.setIdfrequencia(cursor.getString(0));
                frequencia.setQtalunosausentes(cursor.getString(1));
                frequencia.setQtalunospresentes(cursor.getString(2));
                frequencia.setDtfrequencia(cursor.getString(3));
                frequencia.setHrinicio(cursor.getString(4));
                frequencia.setHrinicio(cursor.getString(5));
                frequencia.setHrtermino(cursor.getString(6));
                return frequencia;
            }
            Toast.makeText(contexto, "Frequencia já foi realizada hoje",Toast.LENGTH_LONG);
            return null;
        }else{
            return  null;
        }

    }

    public void limpabanco(){

        insere.delete("frequencia", null,null);
    }
}
