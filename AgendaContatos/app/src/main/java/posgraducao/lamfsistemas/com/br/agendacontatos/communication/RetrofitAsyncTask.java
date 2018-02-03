package posgraducao.lamfsistemas.com.br.agendacontatos.communication;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel.People;
import posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel.ResultSwapi;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leonardo on 12/08/2017.
 */

public class RetrofitAsyncTask
        extends AsyncTask<String, Void,List<People>> {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://swapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit getBuilderStarWarsRetrofit() {
        return retrofit;
    }

    public RetrofitAsyncTask(GetNewsApiListener getNewsApiListener) {
        if(getNewsApiListener!=null) {
            this.getNewsApiListener = getNewsApiListener;
        }
    }

    private GetNewsApiListener getNewsApiListener;
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected List<People> doInBackground(String... strings) {

        //Pega da net
        String url = strings[0];
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestApi service = retrofit.create(RequestApi.class);
        Response<ResultSwapi> resultNewApi = null;
        try {
            resultNewApi =  service.getPeople().execute();
            return resultNewApi.body().getResults()   ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<People> peoples){
        super.onPostExecute(peoples);
        getNewsApiListener.onNewsApiResult(peoples);

    }

    public interface GetNewsApiListener{
        void onNewsApiResult(List<People> peoples);
    }
}