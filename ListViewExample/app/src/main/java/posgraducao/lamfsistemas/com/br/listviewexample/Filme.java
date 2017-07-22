package posgraducao.lamfsistemas.com.br.listviewexample;

/**
 * Created by Leonardo on 22/07/2017.
 */

public class Filme {

    private String title;
    private String year;

    private String urlImg;
    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }


    public Filme(String title, String year, String urlImg) {
        this.title = title;
        this.year = year;
        this.urlImg = urlImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


}
