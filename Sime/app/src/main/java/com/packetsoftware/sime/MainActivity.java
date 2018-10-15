package com.packetsoftware.sime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.packetsoftware.sime.api.DataService;
import com.packetsoftware.sime.controller.SimeEscola;
import com.packetsoftware.sime.controller.SimeFrequencia;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    int idusuario;
    String nomeusuario;
    String senhausuario;
    int idescola;

    private Retrofit retrofit;

    Button btSinc;
    TextView tvNmEscola;
    TextView tvNmUsuario;
    Button btInFrequencia;

    private static final String SIME_PREFERENCE = "simepreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNmEscola = findViewById(R.id.tvNmEscola);
        tvNmUsuario = findViewById(R.id.tvNmUsuario);
        btSinc = findViewById(R.id.btISyncData);
        btInFrequencia = findViewById(R.id.btIFrequencia);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            idusuario = Integer.parseInt(extras.getString("idusuario"));

        }


        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.simeescola.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recuperarDadosEscola(idusuario,nomeusuario,senhausuario);
        //recuperar();

        btSinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarDadosFrequencia(idusuario,nomeusuario,senhausuario);
                Toast.makeText(MainActivity.this, String.valueOf(idusuario), Toast.LENGTH_LONG).show();

            }
        });

        btInFrequencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FrequenciaActivity.class);
                i.putExtra("nomeusuario", nomeusuario);
                i.putExtra("senhausuario", senhausuario);
                i.putExtra("idescola", idescola);
                startActivity(i);
            }
        });


    }


    private void recuperarDadosFrequencia(int idusuario, String usuario, String senha){
        DataService simeService = retrofit.create(DataService.class);

        Call<List<SimeFrequencia>> call = simeService.buscarDadosFrequencia("1.0", "admin123456", "admin123456");

        call.enqueue(new Callback<List<SimeFrequencia>>() {
            @Override
            public void onResponse(Call<List<SimeFrequencia>> call, Response<List<SimeFrequencia>> response) {
                Log.d("simeapp", "onFailure: "+response);
                if(response.isSuccessful()){
                    List<SimeFrequencia> simeFrequencia = response.body();
                    for(SimeFrequencia sF: simeFrequencia){
                        Log.d("simeapp", "onResponse: " + sF.getStstus());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SimeFrequencia>> call, Throwable t) {
                Log.d("simeapp", "onFailure: "+t);
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void recuperarDadosEscola(int idusuario, String usuario, String senha){
        DataService simeService = retrofit.create(DataService.class);

        Call<SimeEscola> call = simeService.buscarPorIdUsuario("1.0", "admin123456", "admin123456");
        call.enqueue(new Callback<SimeEscola>() {
            @Override
            public void onResponse(Call<SimeEscola> call, Response<SimeEscola> response) {
                Log.d("simeapp", "onFailure: "+response);
                if(response.isSuccessful()){
                    SimeEscola simeEscola = response.body();
                    tvNmEscola.setText(simeEscola.getEscola_usuario().getEscola().getNomeescola());
                    tvNmUsuario.setText(simeEscola.getEscola_usuario().getUsuario().getUsuario());
                    idescola = simeEscola.getEscola_usuario().getUsuario().getIdusuario();
                }
            }

            @Override
            public void onFailure(Call<SimeEscola> call, Throwable t) {
                Log.d("simeapp", "onFailure: "+t);
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void recuperar(){
        DataService simeService = retrofit.create(DataService.class);

        Call call = simeService.sinc("1.0");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("simeapp", "onFailure: "+response);
                if(response.isSuccessful()){
                    Log.d("simeapp", "onResponse: "+response.body().toString());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("simeapp", "onFailure: "+t);
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
