package posgraducao.lamfsistemas.com.br.requesthttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import posgraducao.lamfsistemas.com.br.requesthttp.communication.RetrofitAsyncTask;
import posgraducao.lamfsistemas.com.br.requesthttp.model.Article;
import posgraducao.lamfsistemas.com.br.requesthttp.util.Util;

public class RetrofitActivity extends AppCompatActivity implements RetrofitAsyncTask.GetNewsApiListener {
    private static final String URL_SERVICO = "https://newsapi.org/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        if(Util.IsConnected(getApplicationContext())){
            RetrofitAsyncTask asyncTask = new RetrofitAsyncTask(this ) ;
            asyncTask.execute(URL_SERVICO);
        }

    }

    @Override
    public void onNewsApiResult(List<Article> articles) {
        TextView txtFieldText   = (TextView)findViewById(R.id.txtTexto2);
        String texto = "";
        for(Article obj:articles){
            texto +=  " articles =" +  obj.getDescription() +"\n" ;
        }
        txtFieldText.setText( texto );
    }
}
