package posgraducao.lamfsistemas.com.br.agendacontatos.util;

/**
 * Created by Leonardo on 12/08/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MeuOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "agendaContatosLamf";


    public MeuOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table contatos(id INTEGER PRIMARY KEY, " +
                "name TEXT, fone TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //upgrade de banco de dados
        //sqLiteDatabase.execSQL("alter table add()");
    }
}
