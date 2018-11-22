package com.packetsoftware.sime.api;


import com.packetsoftware.sime.controller.Sime;
import com.packetsoftware.sime.controller.SimeEscola;
import com.packetsoftware.sime.controller.SimeFrequencia;
import com.packetsoftware.sime.controller.SimeFrequenciaAluno;
import com.packetsoftware.sime.controller.SimeLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DataService {

    @GET("{versao}/escola/sincdata/")
    Call<SimeLogin> recuperaSime(@Path("versao") String versao);

    @GET("/ws/{versao}/login/{usuario}/{senha}")
    Call<SimeLogin> realizarLogin(@Path("versao") String versao, @Path("usuario") String usuario, @Path("senha") String senha);

    @GET("/ws/{versao}/portal/{usuario}/{senha}/dados-escola/")
    Call<SimeEscola> buscarPorUsuario(@Path("versao") String versao, @Path("usuario") String usuario, @Path("senha") String senha);

    @GET("/ws/{versao}/portal/{usuario}/{senha}/sincdata/{idfrequenciaaluno}/{hrentrada}/")
    Call<Sime> sinca(@Path("versao") String versao, @Path("usuario") String usuario, @Path("senha") String senha, @Path("idfrequenciaaluno") String idAluno, @Path("hrentrada") String hrentrada);

    @GET("/ws/{versao}/portal/{usuario}/{senha}/dados-frequencia-aluno/")
    Call<List<SimeFrequenciaAluno>> buscarDadosFrequenciaAluno(@Path("versao") String versao, @Path("usuario") String usuario, @Path("senha") String senha);

    @GET("/ws/{versao}/portal/{usuario}/{senha}/dados-frequencia/")
    Call<SimeFrequencia> buscarDadosFrequencia(@Path("versao") String versao, @Path("usuario") String usuario, @Path("senha") String senha);

    @FormUrlEncoded
    @POST("/ws/{versao}/portal/{usuario}/{senha}/sincfrequencia/")
    Call<Sime> sincFrequencia(@Path("versao") String versao,
                              @Path("usuario") String usuario,
                              @Path("senha") String senha,
                              @Field("idfrequencia") String idfrequencia,
                              @Field("hrinicio") String hrentrada,
                              @Field("hrtermino") String hrtermino);


    @FormUrlEncoded
    @POST("/ws/{versao}/portal/{usuario}/{senha}/sincdata/")
    Call<Sime> sinc(@Path("versao") String versao,
                    @Path("usuario") String usuario,
                    @Path("senha") String senha,
                    @Field("idfrequenciaaluno") String idfrequenciaaluno,
                    @Field("hrentrada") String hrentrada);

}
