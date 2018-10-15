package com.packetsoftware.sime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.packetsoftware.sime.api.DataService;

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

        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.simeescola.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recuperarDadosEscola(18, "admin123456", "admin123456");

    }


    private void recuperarDadosEscola(int idusuario, String usuario, String senha){
        DataService simeService = retrofit.create(DataService.class);

        Call<List<Array>> call = simeService.sinc("1.0");
        call.enqueue(new Callback<List<Array>>() {
            @Override
            public void onResponse(Call<List<Array>> call, Response<List<Array>> response) {
                if(response.isSuccessful()){

                        Log.d("loginn", "onResponse: "+response.body().toString());
                        Toast.makeText(TesteActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onFailure(Call<List<Array>> call, Throwable t) {
                Log.d("loginn", "onFailure: "+t.toString());
                Toast.makeText(TesteActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
