package com.packetsoftware.sime.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.packetsoftware.sime.controller.Aluno;
import com.packetsoftware.sime.controller.Frequenciaaluno;
import com.packetsoftware.sime.controller.Matricula;
import com.packetsoftware.sime.controller.Pessoa;
import com.packetsoftware.sime.helper.DBHelper;

import java.util.Date;

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

    public boolean deletar(){
        insere.delete("frequenciaaluno", null,null);
        return false;
    }

    public Frequenciaaluno listarPorMatricula(String numeroMatricula){



        String[] values = new String[]{};
        //Cursor cursor = ler.rawQuery("SELECT * FROM frequenciaaluno", null);
        Cursor cursor = ler.rawQuery("SELECT fa.*, m.*, a.pessoa_idpessoa,  p.nomepessoa FROM frequenciaaluno fa " +
                " INNER JOIN matricula m ON m.idmatricula = fa.matricula_idmatricula " +
                " INNER JOIN aluno a ON a.idaluno = m.aluno_idaluno " +
                " INNER JOIN pessoa p ON p.idpessoa = a.pessoa_idpessoa; ", null);


        Frequenciaaluno frequenciaaluno = new Frequenciaaluno();
        Matricula matricula = new Matricula();
        Aluno aluno = new Aluno();
        Pessoa pessoa = new Pessoa();

        if(cursor.moveToLast()){
            Toast.makeText(contexto, "Aqui", Toast.LENGTH_LONG).show();

            if(cursor.getString(cursor.getColumnIndex("hrentrada")) == null){
                Toast.makeText(contexto, "Recuperou", Toast.LENGTH_LONG).show();
                //cursor.moveToFirst();
                Toast.makeText(contexto, cursor.getString(cursor.getColumnIndex("idfrequenciaaluno")), Toast.LENGTH_LONG).show();
                matricula.setIdmatricula(cursor.getString(cursor.getColumnIndex("idfrequenciaaluno")));
                /*pessoa.setNomepessoa();

                aluno.setPessoa(pessoa);

                frequenciaaluno.setIdfrequenciaaluno();
                frequenciaaluno.setData();
                frequenciaaluno.setHrentrada();
                frequenciaaluno.setMatricula(matricula);

*/
                return frequenciaaluno;
            }
            Toast.makeText(contexto, "Frequencia já foi realizada hoje",Toast.LENGTH_LONG);
            return null;
        }else{
            Toast.makeText(contexto, "Nada", Toast.LENGTH_LONG).show();
            return  null;
        }
    }
    public void limpabanco(){
        insere.delete("frequenciaaluno", null,null);
    }

}
