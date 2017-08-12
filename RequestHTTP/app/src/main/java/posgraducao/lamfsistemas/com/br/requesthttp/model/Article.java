package posgraducao.lamfsistemas.com.br.requesthttp.model;

/**
 * Created by Leonardo on 12/08/2017.
 */

public class Article {

    private String author ;
    private String url ;
    private String description ;
    private String urlToImage ;

    public Article(String author, String url, String description, String urlToImage) {
        this.author = author;
        this.url = url;
        this.description = description;
        this.urlToImage = urlToImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }


}
