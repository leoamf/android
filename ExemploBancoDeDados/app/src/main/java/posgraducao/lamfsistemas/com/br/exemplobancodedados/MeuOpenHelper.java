package posgraducao.lamfsistemas.com.br.exemplobancodedados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Leonardo on 11/08/2017.
 */

public class MeuOpenHelper extends SQLiteOpenHelper   {

    private static final  int   DATABASE_VERSION=1;
    private static final   String  DATABASE_NAME="lamfTesteBanco";

    public MeuOpenHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table carros(id integer primary key, name TEXT, ano TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("alter table carros  ");
    }
}
