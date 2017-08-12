package posgraducao.lamfsistemas.com.br.requesthttp.communication;

import posgraducao.lamfsistemas.com.br.requesthttp.model.Article;
import posgraducao.lamfsistemas.com.br.requesthttp.model.ResultNewApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Leonardo on 12/08/2017.
 */

public interface RequestApi {
    //articles?source=buzzfeed&sortBy=top&apiKey=2a478e913dea43a6a935ead757e1cbb5
    @GET("articles")
    Call<ResultNewApi> getNewApi(@Query("source") String source,
                                    @Query("sortBy") String sortBy,
                                    @Query("apiKey") String apiKey );
}
