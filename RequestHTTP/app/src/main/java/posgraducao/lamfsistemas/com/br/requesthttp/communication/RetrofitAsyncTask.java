package posgraducao.lamfsistemas.com.br.requesthttp.communication;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import posgraducao.lamfsistemas.com.br.requesthttp.R;
import posgraducao.lamfsistemas.com.br.requesthttp.model.Article;
import posgraducao.lamfsistemas.com.br.requesthttp.model.ResultNewApi;
import posgraducao.lamfsistemas.com.br.requesthttp.util.Util;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leonardo on 12/08/2017.
 */

public class RetrofitAsyncTask
        extends AsyncTask<String, Void,List<Article>> {

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
    protected List<Article> doInBackground(String... strings) {

        //Pega da net
        String url = strings[0];
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestApi service = retrofit.create(RequestApi.class);
        Response<ResultNewApi> resultNewApi = null;
        try {
            resultNewApi =  service.getNewApi("buzzfeed","top","2a478e913dea43a6a935ead757e1cbb5").execute();
            return resultNewApi.body().getArticles();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Article> articles){
        super.onPostExecute(articles);

        getNewsApiListener.onNewsApiResult(articles);

    }

    public interface GetNewsApiListener{
        void onNewsApiResult(List<Article> articles);
    }
}