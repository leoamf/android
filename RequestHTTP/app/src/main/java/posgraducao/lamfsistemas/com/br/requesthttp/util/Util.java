package posgraducao.lamfsistemas.com.br.requesthttp.util;

import android.content.Context;
import android.net.ConnectivityManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import posgraducao.lamfsistemas.com.br.requesthttp.model.Article;

/**
 * Created by Leonardo on 12/08/2017.
 */

public  class Util {

    public static boolean IsConnected(Context contexto) {
        ConnectivityManager manager = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnected())
            return true;

        return false;
    }

    public static InputStream GetStream(String URL_SERVICO) {

        InputStream retorno = null;
        try {
            URL endereco = new URL(URL_SERVICO);
            HttpURLConnection connection = (HttpURLConnection) endereco.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setDoInput(true);
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                retorno = connection.getInputStream();
            }
        } catch (MalformedURLException m) {
            m.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public static String StreamToString(InputStream stream) {
        String retorno = null;
        if (stream != null) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int temDados = 0;
            try {
                while ((temDados = stream.read(bytes)) > 0) {
                    out.write(bytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            retorno = new String(out.toByteArray());
        }

        return retorno;
    }


    public static List<Article> parse(String body) {
        List<Article> articleList = new ArrayList<Article>();
        try {
            JSONObject jsonObject = new JSONObject(body);
            JSONArray jsonArray = jsonObject.getJSONArray("articles");

            for (int x = 0; x < jsonArray.length(); x++) {
                JSONObject obj = jsonArray.getJSONObject(x);
                Article article = new Article(
                        obj.getString("author"),
                        obj.getString("url"),
                        obj.getString("description"),
                        obj.getString("urlToImage")

                );
                articleList.add(article);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return articleList;
    }

}
