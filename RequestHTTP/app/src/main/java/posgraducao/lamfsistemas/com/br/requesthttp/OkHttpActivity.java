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
import posgraducao.lamfsistemas.com.br.requesthttp.model.Article;
import posgraducao.lamfsistemas.com.br.requesthttp.util.Util;

public class OkHttpActivity extends AppCompatActivity {
    private static final String URL_SERVICO = "https://newsapi.org/v1/articles?source=buzzfeed&sortBy=top&apiKey=2a478e913dea43a6a935ead757e1cbb5\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);


        if(Util.IsConnected(getApplicationContext())){
            OkHttpAsyncTask asyncTask = new OkHttpAsyncTask();
            asyncTask.execute(URL_SERVICO);
        }
    }


    public class OkHttpAsyncTask
            extends AsyncTask<String, Void,List<Article>> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();


        }

        @Override
        protected List<Article> doInBackground(String... strings) {
            List<Article> articles = new ArrayList<Article>();
            //Pega da net
            String url = strings[0];
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = null;
            String body =null;
            try {
                response = client.newCall(request).execute();
                body = response.body().string();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //parse
            articles = Util.parse(body);

            return articles;
        }

        @Override
        protected void onPostExecute(List<Article> articles){
            super.onPostExecute(articles);

            TextView txtFieldText   = (TextView)findViewById(R.id.txtTexto2);
            String texto = "";
            for(Article obj:articles){
                texto +=  " articles =" +  obj.getDescription() +"\n" ;
            }
            txtFieldText.setText( texto );
        }
    }
}
