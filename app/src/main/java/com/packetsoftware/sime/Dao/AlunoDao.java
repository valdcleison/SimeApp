package com.packetsoftware.sime.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.packetsoftware.sime.controller.Aluno;
import com.packetsoftware.sime.controller.Matricula;
import com.packetsoftware.sime.controller.Pessoa;
import com.packetsoftware.sime.helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class AlunoDao {

    private SQLiteDatabase insere;
    private SQLiteDatabase ler;
    private Context contexto;

    public AlunoDao(Context context) {
        DBHelper db = new DBHelper(context);
        insere = db.getWritableDatabase();
        ler = db.getReadableDatabase();
        contexto = context;
    }

    public boolean salvar(Aluno aluno){
        ContentValues cvAluno = new ContentValues();
        cvAluno.put("idaluno", aluno.getIdaluno());
        cvAluno.put("pessoa_idpessoa", aluno.getPessoa().getIdpessoa());


        try {
            insere.insert("aluno", null, cvAluno );
        }catch (Exception e){
            Log.d("simeapp", "salvar: "+ e.getMessage());
            return false;
        }

        return false;
    }

    public boolean editar(Aluno aluno){
        return false;
    }

    public boolean deletar(Pessoa aluno){
        return false;
    }

    public List<Aluno> listar(){
        return new ArrayList<>();
    }

    public Aluno listarPorId(String idAluno){
        String [] values = {idAluno};
        String [] campos = new String[]{"idaluno", "pessoa_idpessoa"};
        Cursor cursor = ler.query("aluno", campos,"idaluno = ?", values, null, null, null);

        Aluno aluno;

        if(cursor != null){

            if(cursor.getCount() <= 0){
                return null;
            }
            cursor.moveToFirst();

            PessoaDao pessoaDao = new PessoaDao(contexto);
            Pessoa pessoa = pessoaDao.listarPorId(cursor.getString(1));

            aluno = new Aluno();
            aluno.setPessoa(pessoa);
            aluno.setPessoa(pessoa);
            return aluno;
        }else{
            return  null;
        }
    }

    public void limpabanco(){
        insere.delete("aluno", null,null);
    }
}
