package posgraducao.lamfsistemas.com.br.agendacontatos.communication;

import posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel.ResultSwapi;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Leonardo on 12/08/2017.
 */

public interface RequestApi {

    @GET("people")
    Call<ResultSwapi> getNewApi();
}
