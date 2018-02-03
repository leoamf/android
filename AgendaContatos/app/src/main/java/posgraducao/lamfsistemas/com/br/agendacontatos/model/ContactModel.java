package posgraducao.lamfsistemas.com.br.agendacontatos.model;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import posgraducao.lamfsistemas.com.br.agendacontatos.DAO.BaseDados;

/**
 * Created by l.machado.de.freitas on 03/02/2018.
 */

public class ContactModel extends AndroidViewModel {
    private LiveData<List<Contact>> contacts;
    private BaseDados bd;

    public ContactModel(Application application)
    {
        super(application);
        bd = BaseDados.getDatabase(application.getApplicationContext());
        carregarDados();
    }

    public LiveData<List<Contact>> getContacts() {
        if(contacts == null) {
            //Transformations.map()
            contacts = Transformations.map(contacts, new Function<List<Contact>, List<Contact>>() {
                @Override
                public List<Contact> apply(List<Contact> novasContacts ) {
                    novasContacts = new ArrayList<>();
                    novasContacts.add(new Contact( "Teste",
                            "123"));
                    return novasContacts;
                }
            });
        }
        return contacts;
    }

    private void carregarDados()
    {
        //Carregar os dados da nossa Base de dados e armazenar no LiveData
        contacts = bd.ContactDao().lerContact() ;
    }
}