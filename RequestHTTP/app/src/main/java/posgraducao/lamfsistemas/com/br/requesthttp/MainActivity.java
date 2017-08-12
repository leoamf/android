package posgraducao.lamfsistemas.com.br.requesthttp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import posgraducao.lamfsistemas.com.br.requesthttp.model.Article;
import posgraducao.lamfsistemas.com.br.requesthttp.util.Util;

public class MainActivity extends AppCompatActivity {
    private static final String URL_SERVICO = "https://newsapi.org/v1/articles?source=buzzfeed&sortBy=top&apiKey=2a478e913dea43a6a935ead757e1cbb5\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Util.IsConnected(getApplicationContext())){
            MinhaAsyncTask  asyncTask = new MinhaAsyncTask();
            asyncTask.execute(URL_SERVICO);
        }
    }

    public void retrofit(View view){
        Intent i = new Intent(getApplicationContext(),RetrofitActivity.class);
        startActivity(i);
    }
    public void okHttp(View view){
        Intent i = new Intent(getApplicationContext(),OkHttpActivity.class);
        startActivity(i);
    }


    public class MinhaAsyncTask
            extends AsyncTask<String, Void,List<Article>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected List<Article> doInBackground(String... strings) {
            List<Article> articles = new ArrayList<Article>();
            //Pega da net
            String url = strings[0];
            InputStream inputStream = Util.GetStream(url);
            //Converter
            String body = Util.StreamToString(inputStream);
            //Parse
            articles = Util.parse(body);

            return articles;
        }

        @Override
        protected void onPostExecute(List<Article> articles){
            super.onPostExecute(articles);


            TextView txtFieldText   = (TextView)findViewById(R.id.txtTexto);
            String texto = "";
            for(Article obj:articles){
                texto +=  " articles =" +  obj.getDescription() +"\n" ;
            }
            txtFieldText.setText( texto );
        }
    }
}
