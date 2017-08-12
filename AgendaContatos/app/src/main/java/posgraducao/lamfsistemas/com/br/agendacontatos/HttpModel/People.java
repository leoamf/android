package posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel;

/**
 * Created by Leonardo on 12/08/2017.
 */

public class People {


    private String name ;
    private String height ;
    private String gender ;

    public People(String name, String height, String gender) {
        this.name = name;
        this.height = height;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
