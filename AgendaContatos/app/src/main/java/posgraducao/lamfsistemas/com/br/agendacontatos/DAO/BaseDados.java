package posgraducao.lamfsistemas.com.br.agendacontatos.DAO;

/**
 * Created by l.machado.de.freitas on 03/02/2018.
 */
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import posgraducao.lamfsistemas.com.br.agendacontatos.model.Contact;
import posgraducao.lamfsistemas.com.br.agendacontatos.ui.MainActivity;


@Database(entities = {Contact.class}, version = 1)
public abstract class BaseDados extends RoomDatabase {
    private static BaseDados INSTANCE;

    public static BaseDados getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            BaseDados.class,
                            "arch_components_db").allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public abstract ContactDAO ContactDao();

}
