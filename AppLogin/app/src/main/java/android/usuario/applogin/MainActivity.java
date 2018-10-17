package android.usuario.applogin;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText ctUser, ctSenha;
    Button btEntrar;
    TextView rtCad;

    String url ="";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ctSenha = (EditText)findViewById(R.id.ctSenha);
        ctUser = (EditText)findViewById(R.id.ctUser);
        btEntrar = (Button)findViewById(R.id.btEntrar);

        rtCad = (TextView)findViewById(R.id.rtCad);
        rtCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CadActivity.class));
            }
        });

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Criando a conexão remota
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                //verifica se tem conexão com internet
                if (networkInfo != null || networkInfo.isConnected()){
                    String email = ctUser.getText().toString();
                    String senha = ctSenha.getText().toString();
                    if(email.isEmpty() || senha.isEmpty()){
                        Toast.makeText(MainActivity.this, "Campo(s) vazio", Toast.LENGTH_SHORT).show();
                    }else{
                        url = "http://10.11.15.21/login/logar.php";
                        parametros = "email="+email+"&senha="+senha;
                        new SolicitaDados().execute(url);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Não há Conexão", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //Solocita os dados através do AsyncTask
    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);

        }

        // onPostExecute mostra os resultados vindo do AsynTask.
        @Override
        protected void onPostExecute(String resultado) {
            //editEmail1.setText(resultado);
            String dados[] = resultado.split(",");
//            editEmail1.setText(dados[0]+" - "+dados[1]+" + "+dados[2]);
            if (resultado.contains("Login_Ok")) {
                Intent abreInicio = new Intent(MainActivity.this, TelaInicial.class);
                abreInicio.putExtra("idUsuario", dados[1]);
                abreInicio.putExtra("nomeUsuario", dados[2]);
                startActivity(abreInicio);
            } else {
                Toast.makeText(getApplicationContext(), "Usuário ou senha incorretos", Toast.LENGTH_LONG).show();
            }
        }
    }

    //Fechando o login
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
