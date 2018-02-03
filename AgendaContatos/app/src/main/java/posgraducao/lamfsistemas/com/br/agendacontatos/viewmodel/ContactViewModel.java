package posgraducao.lamfsistemas.com.br.agendacontatos.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import posgraducao.lamfsistemas.com.br.agendacontatos.DAO.BaseDados;
import posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel.People;
import posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel.PeoplesRepository;
import posgraducao.lamfsistemas.com.br.agendacontatos.model.Contact;

/**
 * Created by l.machado.de.freitas on 02/12/2017.
 */


public class ContactViewModel extends AndroidViewModel {
    private LiveData<List<Contact>> Contacts;
    private BaseDados bd;

    public ContactViewModel(Application application)
    {
        super(application);
        bd = BaseDados.getDatabase(application.getApplicationContext());
        carregarDados();
    }

    public LiveData<List<Contact>> getContacts() {
        if(Contacts == null) {
            //Transformations.map()
            Contacts = Transformations.map(Contacts, new Function<List<Contact>,
                                List<Contact>>() {
                @Override
                public List<Contact> apply(List<Contact> novasContacts) {
                    novasContacts = new ArrayList<>();
                    novasContacts.add(new Contact( "Bem-vindo",
                            "Bem-vindo ao mundo dos Architecture Components"));
                    return novasContacts;
                }
            });
        }
        return Contacts;
    }

    private void carregarDados()
    {
        //Carregar os dados da nossa Base de dados e armazenar no LiveData
        Contacts = bd.ContactDao().lerContact();
    }

}
