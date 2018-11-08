package com.packetsoftware.sime.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.packetsoftware.sime.controller.Frequencia;
import com.packetsoftware.sime.helper.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String data = simpleDateFormat.format(calendar.getTime());

        Log.d("simeapp", "salvar data: " + data);

        ContentValues cvFrequencia = new ContentValues();
        cvFrequencia.put("idfrequencia", frequencia.getIdfrequencia());
        cvFrequencia.put("qtalunospresentes", frequencia.getQtalunospresentes());
        cvFrequencia.put("qtalunosausentes", frequencia.getQtalunosausentes());
        cvFrequencia.put("dtfrequencia", frequencia.getDtfrequencia());
        cvFrequencia.put("hrinicio", data);

        try {
            insere.insert("frequencia", null, cvFrequencia );

        }catch (Exception e){

            return false;
        }
        insere.close();
        return true;
    }

    public boolean editarFim(Frequencia frequencia){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String data = simpleDateFormat.format(calendar.getTime());
        Log.d("simeapp", "editarFim: "+data);

        ContentValues cvFrequencia = new ContentValues();
        cvFrequencia.put("hrtermino", data);

        String [] values = {frequencia.getIdfrequencia()};

        try {
            insere.update("frequencia", cvFrequencia, "idfrequencia = ?", values);
        }catch (Exception e){
            Log.d("simeapp", "salvar: "+ e.getMessage());
            return false;
        }
        insere.close();
        return true;
    }

    public boolean deletar(Frequencia frequencia){


        try{
            insere.delete("frequencia", null, null);
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
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String data = simpleDateFormat.format(calendar.getTime());
        Toast.makeText(contexto, data, Toast.LENGTH_SHORT).show();
        String[] values = new String[]{data};

        String [] campos = new String[]{"idfrequencia", "qtalunosausentes", "qtalunospresentes", "dtfrequencia", "hrinicio", "hrtermino"};
        Cursor cursor = ler.query("frequencia ", campos,"dtfrequencia = ?", values, null, null, null);
        Frequencia frequencia;

        if(cursor != null){

            if(cursor.getCount() <= 0){

                return null;
            }
            cursor.moveToFirst();

            if(cursor.getString(4) != null) {


                frequencia = new Frequencia();
                frequencia.setIdfrequencia(cursor.getString(0));
                frequencia.setQtalunosausentes(cursor.getString(1));
                frequencia.setQtalunospresentes(cursor.getString(2));
                frequencia.setDtfrequencia(cursor.getString(3));
                frequencia.setHrinicio(cursor.getString(4));
                frequencia.setHrtermino(cursor.getString(5));
                return frequencia;
            }else{

                return null;
            }

        }else{
            return  null;
        }

    }

    public void limpabanco(){

        insere.delete("frequencia", null,null);
    }
}
