package com.packetsoftware.sime;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyTask extends AsyncTask<String, Void, String> {
    TextView tv;
    public MyTask(TextView tv){
        this.tv = tv;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String stringUrl = strings[0];
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        StringBuffer buffer = null;

        try {

            URL url = new URL(stringUrl);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            // Recupera os dados em Bytes
            inputStream = conexao.getInputStream();

            //inputStreamReader lê os dados em Bytes e decodifica para caracteres
            inputStreamReader = new InputStreamReader( inputStream );

            //Objeto utilizado para leitura dos caracteres do InpuStreamReader
            BufferedReader reader = new BufferedReader( inputStreamReader );
            buffer = new StringBuffer();
            String linha = "";

            while((linha = reader.readLine()) != null){
                buffer.append( linha );
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

    @Override
    protected void onPostExecute(String resultado) {
        super.onPostExecute(resultado);
        tv.setText(resultado);
    }
}
