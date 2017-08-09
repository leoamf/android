package posgraducao.lamfsistemas.com.br.agendacontatos;

import java.io.Serializable;

/**
 * Created by Leonardo on 05/08/2017.
 */

public class Contact implements Serializable  {

    public int id;
    public String name;
    public String fone;

    public Contact(int id, String name,   String fone ){
        this.id = id;
        this.name = name;
        this.fone = fone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

}
