package posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel;

import java.util.List;

import posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel.People;

/**
 * Created by Leonardo on 12/08/2017.
 */

public class ResultSwapi {
    private String count;
    private List<People> results;

    public ResultSwapi() {

    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public  List<People> getResults() {
        return results;
    }

    public void setResults(List<People> results) {
        this.results = results;
    }
}

