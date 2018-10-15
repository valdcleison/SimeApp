package com.packetsoftware.sime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.packetsoftware.sime.api.DataService;
import com.packetsoftware.sime.controller.SimeLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText etusuario, etsenha;
    Button btLogin;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializaComponentes();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.simeescola.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etusuario.getText().toString();
                String senha = etsenha.getText().toString();

                if(usuario.isEmpty() || senha.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                } else{
                    realizarLogin(usuario, senha);
                }
            }
        });



    }

    private void inicializaComponentes(){
        etusuario = (EditText)findViewById(R.id.etUsuario);
        etsenha = (EditText)findViewById(R.id.etSenha);
        btLogin = (Button) findViewById(R.id.btLogin);
    }



    private void realizarLogin(String user, final String senha){

        DataService simeService = retrofit.create(DataService.class);

        Call<SimeLogin> call = simeService.realizarLogin("1.0", user, senha);
        call.enqueue(new Callback<SimeLogin>() {
            @Override
            public void onResponse(Call<SimeLogin> call, Response<SimeLogin> response) {
                if(response.isSuccessful()){

                    SimeLogin sime = response.body();

                    if(sime.getStatus().equals("failed")){
                        Toast.makeText(LoginActivity.this, sime.getMenssage(), Toast.LENGTH_SHORT).show();
                    }else{

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("idusuario", String.valueOf(sime.getUsuario().getIdusuario()));
                        intent.putExtra("nomeusuario", sime.getUsuario().getUsuario());
                        intent.putExtra("senhausuario", senha);
                        startActivity(intent);
                    }

                }
            }

            @Override
            public void onFailure(Call<SimeLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
