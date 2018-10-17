package android.usuario.applogin;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Hérlon on 02/12/2016.
 */
public class Conexao {

    public static String postDados(String urlUsuario, String parametrosUsusario) {
        URL url;
        HttpURLConnection connection = null;//Aqui faz a conexão com segurançã
        try {
            url = new URL(urlUsuario);//Aqui recebe a url - http://... por parâmetro
            connection = (HttpURLConnection) url.openConnection();//Abre a conexão
            connection.setRequestMethod("POST"); //indica que método de passagem de dados será POST
            //Aqui o "content-Type" especifica como os dados serão enviados
            //O "application/x-www-form-urlencoded" indica que serão enviados como um formulário
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //Aqui informa a quantidade de bytes da trasação
            connection.setRequestProperty("Content-Lenght", "" + Integer.toString(parametrosUsusario.getBytes().length));
            //Aqui informa a linguagem
            connection.setRequestProperty("Content-Language", "pt-BR");
            //Desabilitando o cache para que os dados não fiquem armazenados no aparelho
            connection.setUseCaches(false);
            //Habilitando a entrada e saída de dados da aplicação, para enviar e receber dados
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Pegando os dados da conexão
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            //Pegando os dados do usuário
            outputStreamWriter.write(parametrosUsusario);
            outputStreamWriter.flush();

            //Aqui o inputstream serve para obter a informação
            InputStream inputStream = connection.getInputStream();
            //Pegando esses dados e colocando no buffer
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String linha;
            StringBuffer resposta = new StringBuffer();

            //Aqui o while pega cada linha e junta os dados que estão chegando, com o append
            while ((linha = bufferedReader.readLine()) != null) {
                resposta.append(linha);
                resposta.append('\r');
            }

            bufferedReader.close();
            return resposta.toString();

        } catch (Exception erro) {

            return null;
        } finally {
            //Aqui desconecta caso esteja conectado
            if (connection != null) {
                connection.disconnect();
            }
        }

    }
}
