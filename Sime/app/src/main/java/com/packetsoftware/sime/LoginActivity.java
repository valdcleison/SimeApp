package com.packetsoftware.sime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    Button bt;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bt = findViewById(R.id.bt);
        tv = findViewById(R.id.tv);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask task = new MyTask(tv);
                String urlApi = "http://www.simeescola.com.br/ws/1.0/escola/sincdata/";

                task.execute(urlApi);
            }
        });
    }
}
