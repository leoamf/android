package posgraducao.lamfsistemas.com.br.agendacontatos.DAO;

/**
 * Created by l.machado.de.freitas on 03/02/2018.
 */
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import posgraducao.lamfsistemas.com.br.agendacontatos.model.Contact;

@Dao
public interface  ContactDAO {

    @Insert
    void criar(Contact contact);

    @Query("SELECT * FROM Contact")
    LiveData<List<Contact>> lerContact();

    @Query("SELECT * FROM Contact WHERE id = :id")
    Contact findPeloId(int id);

    @Query("SELECT * FROM Contact WHERE name  LIKE :name ")
     List<Contact>   findPeloName(String name);

    @Update
    void atualizar(Contact contact);

    @Delete
    void excluir(Contact contact);
}
