package com.packetsoftware.sime.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.packetsoftware.sime.controller.Aluno;
import com.packetsoftware.sime.controller.Frequencia;
import com.packetsoftware.sime.controller.Frequenciaaluno;
import com.packetsoftware.sime.controller.Matricula;
import com.packetsoftware.sime.controller.Pessoa;
import com.packetsoftware.sime.helper.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FrequenciaAlunoDao {
    private SQLiteDatabase insere;
    private SQLiteDatabase ler;
    private Context contexto;

    public FrequenciaAlunoDao(Context context) {
        contexto = context;
        DBHelper db = new DBHelper(context);
        insere = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    public boolean salvar(Frequenciaaluno frequenciaaluno){

        ContentValues cvFrequenciaAluno = new ContentValues();
        cvFrequenciaAluno.put("idfrequenciaaluno", frequenciaaluno.getIdfrequenciaaluno());
        cvFrequenciaAluno.put("data", frequenciaaluno.getData());
        cvFrequenciaAluno.put("matricula_idmatricula", frequenciaaluno.getMatricula().getIdmatricula());
        cvFrequenciaAluno.put("frequencia_idfrequencia", frequenciaaluno.getFrequencia().getIdfrequencia());


        try {
            insere.insert("frequenciaaluno", null, cvFrequenciaAluno );

        }catch (Exception e){

            return false;
        }

        return false;
    }

    public boolean editar(Frequenciaaluno frequenciaaluno){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String data = simpleDateFormat.format(calendar.getTime());

        ContentValues cvFrequencia = new ContentValues();
        cvFrequencia.put("hrentrada",  data);

        String [] values = {frequenciaaluno.getIdfrequenciaaluno()};

        try {
            insere.update("frequenciaaluno", cvFrequencia, "idfrequenciaaluno = ?", values);
        }catch (Exception e){
            Log.d("simeapp", "salvar: "+ e.getMessage());
            return false;
        }

        return false;
    }

    public boolean deletar(Frequenciaaluno frequenciaaluno){
        String[] values = {frequenciaaluno.getIdfrequenciaaluno()};
        insere.delete("frequenciaaluno", "idfrequenciaaluno = ?",values);
        return false;
    }

    public List<Frequenciaaluno> listar(){


        List<Frequenciaaluno> lista = new ArrayList<>();

        String [] campos = {"idfrequenciaaluno","data", "hrentrada"};
        Cursor cursor = ler.query("frequenciaaluno", campos,null,null,null,null,null);



        while (cursor.moveToNext()){

            Frequenciaaluno frequencia = new Frequenciaaluno();
            frequencia.setIdfrequenciaaluno(cursor.getString(cursor.getColumnIndex("idfrequenciaaluno")));
            frequencia.setData(cursor.getString(cursor.getColumnIndex("data")));
            frequencia.setHrentrada(cursor.getString(cursor.getColumnIndex("hrentrada")));
            lista.add( frequencia );

        }
        return lista;
    }

    public Frequenciaaluno listarPorMatricula(String idmatricula){



        String [] values = {idmatricula};
        String [] campos = new String[]{"idfrequenciaaluno", "data", "hrentrada", "matricula_idmatricula", "frequencia_idfrequencia"};
        Cursor cursor = ler.query("frequenciaaluno", campos,"matricula_idmatricula = ?", values, null, null, null);

        Frequenciaaluno frequenciaAluno;

        if(cursor != null){

            if(cursor.getCount() <= 0){
                return null;
            }
            cursor.moveToFirst();
            frequenciaAluno = new Frequenciaaluno();
            frequenciaAluno.setIdfrequenciaaluno(cursor.getString(0));
            frequenciaAluno.setData(cursor.getString(1));
            frequenciaAluno.setHrentrada(cursor.getString(2));

            return frequenciaAluno;
        }else{
            return  null;
        }
    }

    public void limpabanco(){
        insere.delete("frequenciaaluno", null,null);
        insere.delete("matricula", null, null);
        insere.delete("aluno", null, null);
        insere.delete("pessoa", null, null);
    }

}
