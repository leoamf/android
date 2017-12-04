package posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import posgraducao.lamfsistemas.com.br.agendacontatos.communication.RequestApi;
import posgraducao.lamfsistemas.com.br.agendacontatos.communication.RetrofitAsyncTask;
import posgraducao.lamfsistemas.com.br.agendacontatos.model.Contacts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by l.machado.de.freitas on 02/12/2017.
 */

public class PeoplesRepository {

    private RequestApi webService = RetrofitAsyncTask.getBuilderStarWarsRetrofit().create(RequestApi.class);

    public LiveData<Peoples> getPeoples() {
        final MutableLiveData<Peoples> data = new MutableLiveData<>();
        webService.getNewApi().enqueue(new Callback<ResultSwapi>() {
            @Override
            public void onResponse(Call<ResultSwapi> call, Response<ResultSwapi> response) {
                data.setValue(response.body().getResults());

            }

            @Override
            public void onFailure(Call<ResultSwapi> call, Throwable t) {
                Log.d(PeoplesRepository.class.getSimpleName(), "onFailure:getNewApi");
            }
        });
        return data;
    }
}
