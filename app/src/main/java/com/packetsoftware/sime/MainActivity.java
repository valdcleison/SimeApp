package com.packetsoftware.sime;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.packetsoftware.sime.Dao.AlunoDao;
import com.packetsoftware.sime.Dao.FrequenciaAlunoDao;
import com.packetsoftware.sime.Dao.FrequenciaDao;
import com.packetsoftware.sime.Dao.MatriculaDao;
import com.packetsoftware.sime.Dao.PessoaDao;
import com.packetsoftware.sime.api.DataService;
import com.packetsoftware.sime.controller.Matricula;
import com.packetsoftware.sime.controller.SimeEscola;
import com.packetsoftware.sime.controller.SimeFrequencia;
import com.packetsoftware.sime.controller.SimeFrequenciaAluno;
import com.packetsoftware.sime.helper.DBHelper;

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
    DBHelper db;

    private static final String SIME_PREFERENCE = "simepreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNmEscola = findViewById(R.id.tvNmEscola);
        tvNmUsuario = findViewById(R.id.tvNmUsuario);
        btSinc = findViewById(R.id.btISyncData);
        btInFrequencia = findViewById(R.id.btIFrequencia);
        db = new DBHelper( getApplicationContext() );

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            idusuario = Integer.parseInt(extras.getString("idusuario"));
            nomeusuario = extras.getString("nomeusuario");
            senhausuario = extras.getString("senhausuario");
        }


        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.simeescola.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recuperarDadosEscola(idusuario,nomeusuario,senhausuario);


        btSinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarDadosFrequencia(nomeusuario,senhausuario);
                recuperarDadosFrequenciaAluno(idusuario,nomeusuario,senhausuario);
                Intent i = new Intent(MainActivity.this, TesteActivity.class);

                startActivity(i);


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


    private void recuperarDadosFrequencia(String usuario,String senha){
        DataService simeService = retrofit.create(DataService.class);

        Call<SimeFrequencia> call = simeService.buscarDadosFrequencia("1.0", usuario, senha);
        Log.d("simeapp", "recuperarDadosFrequencia: "+call.toString());
        call.enqueue(new Callback<SimeFrequencia>() {
            @Override
            public void onResponse(Call<SimeFrequencia> call, Response<SimeFrequencia> response) {


                if(response.isSuccessful()){
                    SimeFrequencia simeFrequencia = response.body();
                    FrequenciaDao frequenciaDao = new FrequenciaDao(getApplicationContext());
                    frequenciaDao.salvar(simeFrequencia.getFrequencia());
                }
            }

            @Override
            public void onFailure(Call<SimeFrequencia> call, Throwable t) {
                Log.d("simeapp", "onFailure: "+t);
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void recuperarDadosFrequenciaAluno(int idusuario, String usuario, String senha){
        DataService simeService = retrofit.create(DataService.class);

        Call<List<SimeFrequenciaAluno>> call = simeService.buscarDadosFrequenciaAluno("1.0", usuario, senha);
        Log.d("simeapp", "recuperarDadosFrequencia: "+call.toString());
        call.enqueue(new Callback<List<SimeFrequenciaAluno>>() {
            @Override
            public void onResponse(Call<List<SimeFrequenciaAluno>> call, Response<List<SimeFrequenciaAluno>> response) {


                if(response.isSuccessful()){
                    List<SimeFrequenciaAluno> simeFrequencia = response.body();
                    for(SimeFrequenciaAluno sF: simeFrequencia){

                        PessoaDao pessoaDao = new PessoaDao(getApplicationContext());
                        pessoaDao.salvar(sF.getFrequenciaaluno().getMatricula().getAluno().getPessoa());

                        AlunoDao alunoDao = new AlunoDao(getApplicationContext());
                        alunoDao.salvar(sF.getFrequenciaaluno().getMatricula().getAluno());

                        MatriculaDao matriculaDao = new MatriculaDao(getApplicationContext());
                        matriculaDao.salvar(sF.getFrequenciaaluno().getMatricula());

                        FrequenciaAlunoDao frequenciaAlunoDao = new FrequenciaAlunoDao(getApplicationContext());
                        frequenciaAlunoDao.salvar(sF.getFrequenciaaluno());

                        Toast.makeText(MainActivity.this, sF.getFrequenciaaluno().getIdfrequenciaaluno(), Toast.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<SimeFrequenciaAluno>> call, Throwable t) {
                Log.d("simeapp", "onFailure: "+t);
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void recuperarDadosEscola(int idusuario, String usuario, String senha){
        DataService simeService = retrofit.create(DataService.class);

        Call<SimeEscola> call = simeService.buscarPorIdUsuario("1.0", usuario, senha);
        call.enqueue(new Callback<SimeEscola>() {
            @Override
            public void onResponse(Call<SimeEscola> call, Response<SimeEscola> response) {
                Log.d("simeapp", "onSuccess: "+response);
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
