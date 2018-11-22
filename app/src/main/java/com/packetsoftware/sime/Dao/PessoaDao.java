package com.packetsoftware.sime.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.packetsoftware.sime.controller.Pessoa;
import com.packetsoftware.sime.helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class PessoaDao {
    private SQLiteDatabase insere;
    private SQLiteDatabase ler;
    private Context contexto;


    public PessoaDao(Context context) {
        DBHelper db = new DBHelper(context);
        insere = db.getWritableDatabase();
        ler = db.getReadableDatabase();
        contexto = context;
    }

    public boolean salvar(Pessoa pessoa){
        ContentValues cvPess = new ContentValues();
        cvPess.put("idpessoa", pessoa.getIdpessoa());
        cvPess.put("nomepessoa", pessoa.getNomepessoa());

        try {
            insere.insert("pessoa", null, cvPess );
        }catch (Exception e){

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

    public Pessoa listarPorId(String idPessoa){
        String [] values = {idPessoa};
        String [] campos = new String[]{"idpessoa", "nomepessoa", "cpfpessoa"};

        Cursor cursor = ler.query("pessoa", campos,"idpessoa = ?", values, null, null, null);

        Pessoa pessoa;

        if(cursor != null){

            if(cursor.getCount() <= 0){
                return null;
            }
            cursor.moveToFirst();


            pessoa = new Pessoa();
            pessoa.setNomepessoa(cursor.getString(1));
            pessoa.setCpfpessoa(cursor.getString(2));
            return pessoa;
        }else{
            return  null;
        }
    }

    public void limpabanco(){
        insere.delete("pessoa", null,null);
    }
}
