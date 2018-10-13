package com.packetsoftware.sime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.packetsoftware.sime.api.SimeService;
import com.packetsoftware.sime.model.Sime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    Button bt;
    Button btLogin;
    TextView tv;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.simeescola.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();

            }
        });



    }


    private void recuperaSime(){
        SimeService simeService = retrofit.create(SimeService.class);
        Call<Sime> call = simeService.recuperaSime("2.0");
        call.enqueue(new Callback<Sime>() {
            @Override
            public void onResponse(Call<Sime> call, Response<Sime> response) {
                if(response.isSuccessful()){
                    Sime sime = response.body();
                    tv.setText("Status: "+ sime.getStatus()+ " Mensagen: " + sime.getMenssage());
                }
            }

            @Override
            public void onFailure(Call<Sime> call, Throwable t) {

            }
        });
    }
}
