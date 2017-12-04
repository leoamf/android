package com.example.lmachadodefreitas.architecturesample.repository.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceClient {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://swapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit getBuilderStarWarsRetrofit() {
        return retrofit;
    }

}
