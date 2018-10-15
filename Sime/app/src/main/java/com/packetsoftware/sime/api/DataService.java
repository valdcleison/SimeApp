package com.packetsoftware.sime.api;


import com.packetsoftware.sime.controller.SimeEscola;
import com.packetsoftware.sime.controller.SimeFrequencia;
import com.packetsoftware.sime.controller.SimeLogin;

import java.lang.reflect.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataService {

    @GET("{versao}/escola/sincdata/")
    Call<SimeLogin> recuperaSime(@Path("versao") String versao);

    @GET("/ws/{versao}/login/{usuario}/{senha}")
    Call<SimeLogin> realizarLogin(@Path("versao") String versao, @Path("usuario") String usuario, @Path("senha") String senha);

    @GET("/ws/{versao}/portal/{usuario}/{senha}/dados-escola/")
    Call<SimeEscola> buscarPorIdUsuario(@Path("versao") String versao,  @Path("usuario") String usuario, @Path("senha") String senha);

    @GET("{versao}/escola/sincdata/")
    Call sinc(@Path("versao") String versao);

    @GET("/ws/{versao}/portal/{usuario}/{senha}/dados-frequencia-aluno/")
    Call<List<SimeFrequencia>> buscarDadosFrequencia(@Path("versao") String versao, @Path("usuario") String usuario, @Path("senha") String senha);


}
