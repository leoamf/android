package posgraducao.lamfsistemas.com.br.agendacontatos.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel.Peoples;
import posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel.PeoplesRepository;
import posgraducao.lamfsistemas.com.br.agendacontatos.model.Contact;

/**
 * Created by l.machado.de.freitas on 02/12/2017.
 */

public class ContactViewModel extends ViewModel  {
    private LiveData<Peoples> mPeoples;
    private PeoplesRepository  peoplesRepository
            = new PeoplesRepository();

    public void init(boolean isUpdadeForcado) {
        if(mPeoples != null && !isUpdadeForcado){
            return;
        }

        mPeoples = peoplesRepository.getPeoples();
    }

    public LiveData<Peoples> getContacts() {
        return this.mPeoples;
    }
}
