package com.ralphm10.starwars.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroFitService {
    @GET("people/")
    Call<PersonResponse> getPeople();
}
