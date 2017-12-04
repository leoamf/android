package posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel;

import java.util.List;

import posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel.People;

/**
 * Created by Leonardo on 12/08/2017.
 */

public class ResultSwapi {
    private String count;
    private Peoples results;

    public ResultSwapi() {

    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public  Peoples getResults() {
        return results;
    }

    public void setResults(Peoples results) {
        this.results = results;
    }
}

