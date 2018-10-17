package android.usuario.applogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TelaInicial extends AppCompatActivity {


    TextView rtId, rtNome;
    String idUsuario, nomeUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        rtId = (TextView)findViewById(R.id.rtId);
        rtNome = (TextView)findViewById(R.id.rtNome);

        idUsuario = getIntent().getExtras().getString("idUsuario");
        nomeUsuario = getIntent().getExtras().getString("nomeUsuario");

        rtId.setText(idUsuario);
        rtNome.setText(nomeUsuario);
    }
}
