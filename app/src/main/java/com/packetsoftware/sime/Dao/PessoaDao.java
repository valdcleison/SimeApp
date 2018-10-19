package com.packetsoftware.sime.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.packetsoftware.sime.controller.Pessoa;
import com.packetsoftware.sime.helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class PessoaDao {
    private SQLiteDatabase insere;
    private SQLiteDatabase ler;

    public PessoaDao(Context context) {
        DBHelper db = new DBHelper(context);
        insere = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    public boolean salvar(Pessoa pessoa){
        ContentValues cvPess = new ContentValues();
        cvPess.put("idpessoa", pessoa.getIdpessoa());
        cvPess.put("nomepessoa", pessoa.getNomepessoa());

        try {
            insere.insert("pessoa", null, cvPess );
        }catch (Exception e){
            Log.d("simeapp", "salvar: "+ e.getMessage());
            return false;
        }

        return false;
    }

    public boolean editar(Pessoa pessoa){
        return false;
    }

    public boolean deletar(Pessoa pessoa){
        return false;
    }

    public List<Pessoa> listar(){
        return new ArrayList<>();
    }
}
