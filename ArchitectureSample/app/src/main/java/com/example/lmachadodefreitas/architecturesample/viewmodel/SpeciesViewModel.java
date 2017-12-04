package com.example.lmachadodefreitas.architecturesample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.example.lmachadodefreitas.architecturesample.model.Species;
import com.example.lmachadodefreitas.architecturesample.repository.network.SpeciesRepository;

/**
 * Created by l.machado.de.freitas on 02/12/2017.
 */

public class SpeciesViewModel extends ViewModel {
    private LiveData<Species> mSpecies;
    private SpeciesRepository  speciesRepository
            = new SpeciesRepository();

    public void init(boolean isUpdadeForcado) {
        if(mSpecies != null && !isUpdadeForcado){
            return;
        }

        mSpecies = speciesRepository.getSpecies();
    }

    public LiveData<Species> getSpecies() {
        return this.mSpecies;
    }
}
