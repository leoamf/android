package com.example.lmachadodefreitas.architecturesample.repository.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.lmachadodefreitas.architecturesample.model.Species;
import com.example.lmachadodefreitas.architecturesample.repository.api.ServiceClient;
import com.example.lmachadodefreitas.architecturesample.repository.api.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by l.machado.de.freitas on 02/12/2017.
 */

public class SpeciesRepository {

    private WebService webService = ServiceClient.getBuilderStarWarsRetrofit().create(WebService.class);

    public LiveData<Species> getSpecies() {
        final MutableLiveData<Species> data = new MutableLiveData<>();
        webService.getSpecies().enqueue(new Callback<Species>() {
            @Override
            public void onResponse(Call<Species> call, Response<Species> response) {
                data.setValue(response.body());

            }

            @Override
            public void onFailure(Call<Species> call, Throwable t) {
                Log.d(SpeciesRepository.class.getSimpleName(), "onFailure:getSpecies");
            }
        });
        return data;
    }
}
