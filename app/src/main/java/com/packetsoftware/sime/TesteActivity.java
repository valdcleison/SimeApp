package com.packetsoftware.sime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.packetsoftware.sime.Dao.FrequenciaDao;
import com.packetsoftware.sime.api.DataService;
import com.packetsoftware.sime.controller.Frequencia;

import java.lang.reflect.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TesteActivity extends AppCompatActivity {
    String idusuario;
    String nomeusuario;
    String senhausuario;
    ListView listaEscola;
    String lista[];
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        listaEscola = findViewById(R.id.lvEscola);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            idusuario = extras.getString("id");

        }
        FrequenciaDao frequenciaDao = new FrequenciaDao(getApplicationContext());
        for(Frequencia f: frequenciaDao.listar()){
            Log.d("ffffff", "Fre " + f.getDtfrequencia());
        }
    }




}
