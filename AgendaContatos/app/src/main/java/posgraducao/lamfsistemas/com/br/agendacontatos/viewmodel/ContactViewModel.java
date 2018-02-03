package posgraducao.lamfsistemas.com.br.agendacontatos.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel.People;
import posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel.PeoplesRepository;

/**
 * Created by l.machado.de.freitas on 02/12/2017.
 */

public class ContactViewModel extends ViewModel  {
    private LiveData<List<People>> mPeoples;
    private PeoplesRepository  peoplesRepository
            = new PeoplesRepository();

    public void init(boolean isUpdadeForcado) {
        if(mPeoples != null && !isUpdadeForcado){
            return;
        }

        mPeoples = peoplesRepository.getPeoples();
    }

    public LiveData<List<People>> getContacts() {
        return this.mPeoples;
    }
}
