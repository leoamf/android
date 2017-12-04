package com.example.lmachadodefreitas.architecturesample.repository.api;

import com.example.lmachadodefreitas.architecturesample.model.Species;

import retrofit2.Call;
import retrofit2.http.GET;


public interface WebService {
    /**
     * @GET the list of star wars films an HTTP GET request
     */
    @GET("species")
    Call<Species> getSpecies();
}
