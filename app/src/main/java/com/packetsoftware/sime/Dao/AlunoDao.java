package com.packetsoftware.sime.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.packetsoftware.sime.controller.Aluno;
import com.packetsoftware.sime.controller.Pessoa;
import com.packetsoftware.sime.helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class AlunoDao {
    private SQLiteDatabase insere;
    private SQLiteDatabase ler;

    public AlunoDao(Context context) {
        DBHelper db = new DBHelper(context);
        insere = db.getWritableDatabase();
        ler = db.getReadableDatabase();
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
}
