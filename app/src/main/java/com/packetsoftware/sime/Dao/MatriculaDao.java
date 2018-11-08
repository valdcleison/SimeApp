package com.packetsoftware.sime.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.packetsoftware.sime.controller.Aluno;
import com.packetsoftware.sime.controller.Matricula;
import com.packetsoftware.sime.helper.DBHelper;

public class MatriculaDao {
    private SQLiteDatabase insere;
    private SQLiteDatabase ler;
    private Context contexto;

    public MatriculaDao(Context context) {
        this.contexto = context;
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

    public Matricula listarPorMatricula(String numeroMatricula){
        String [] values = {numeroMatricula};
        String [] campos = new String[]{"idmatricula", "aluno_idaluno","numeromatricula"};
        //Cursor cursor = ler.query("frequenciaaluno", campos,null, null, null, null, null);
        Cursor cursor = ler.query("matricula", campos,null, null, null, null, null);

        Matricula matricula;

        if(cursor != null){

            if(cursor.getCount() <= 0){
                Toast.makeText(contexto, "Sem Frequência registrada", Toast.LENGTH_SHORT).show();
                return null;
            }
            cursor.moveToFirst();

            AlunoDao alunoDao = new AlunoDao(contexto);
            Aluno aluno = alunoDao.listarPorId(cursor.getString(1));
            matricula = new Matricula();
            matricula.setIdmatricula(cursor.getString(0));
            matricula.setNumeromatricula(cursor.getString(2));
            matricula.setAluno(aluno);
            return matricula;
        }else{

            return  null;
        }
    }
    public void limpabanco(){
        insere.delete("matricula", null,null);
    }
}
