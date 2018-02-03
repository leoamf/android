package posgraducao.lamfsistemas.com.br.agendacontatos.model;

import java.io.Serializable;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Leonardo on 05/08/2017.
 */
@Entity
public class Contact    {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String fone;

    @Ignore
    public Contact(){}

    public Contact( String name,   String fone ){
        this.name = name;
        this.fone = fone;
    }

    @Ignore
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

    public String toString() {
        return " Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fone='" + fone + '\'' +
                '}';
    }
}
