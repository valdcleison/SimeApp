package com.packetsoftware.sime.api;


import com.packetsoftware.sime.model.Sime;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SimeService {
    @GET("{versao}/escola/sincdata/")
    Call<Sime> recuperaSime(@Path("versao") String versao);
}
