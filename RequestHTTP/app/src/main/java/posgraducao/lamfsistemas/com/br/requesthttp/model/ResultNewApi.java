package posgraducao.lamfsistemas.com.br.requesthttp.model;

import java.util.List;

/**
 * Created by Leonardo on 12/08/2017.
 */

public class ResultNewApi {
    private String status;
    private List<Article> articles;

    public ResultNewApi() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}

