package posgraducao.lamfsistemas.com.br.exemplobancodedados;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.sql.SQLData;

public class MainActivity extends AppCompatActivity {
    MeuOpenHelper meuOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meuOpenHelper = new MeuOpenHelper(getApplicationContext());
    }

    public void getInfo(View v) {
        SQLiteDatabase db = meuOpenHelper.getWritableDatabase();
        String[] projection = {"name","ano"};
        String table = "carros";

        String selection = "name = ? and ano=?";
        String[] selectionValues = {"Teste","1982"};

        Cursor cursor = db.query(table, projection, selection, selectionValues, null, null, null);
        String textoFinal ="Não acessou!";
        if(cursor.moveToFirst()){
            textoFinal="";
            do{
                textoFinal = textoFinal + " | " + cursor.getString(cursor.getColumnIndex("name"));
                textoFinal = textoFinal + " | " +cursor.getString(cursor.getColumnIndex("ano"));

            } while(cursor.moveToNext());
        }

        Toast.makeText(getApplicationContext(),textoFinal,Toast.LENGTH_LONG).show();
    }

    public void insert(View v){

        SQLiteDatabase db = meuOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name","Teste");
        contentValues.put("ano","1982");

        long idCarro = db.insert("carros",null,contentValues);

        Toast.makeText(getApplicationContext(),String.valueOf(  idCarro),Toast.LENGTH_LONG).show();
    }
}
