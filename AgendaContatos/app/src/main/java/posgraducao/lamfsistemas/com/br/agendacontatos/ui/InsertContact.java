package posgraducao.lamfsistemas.com.br.agendacontatos.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import posgraducao.lamfsistemas.com.br.agendacontatos.DAO.BaseDados;
import posgraducao.lamfsistemas.com.br.agendacontatos.R;
import posgraducao.lamfsistemas.com.br.agendacontatos.model.Contact;
import posgraducao.lamfsistemas.com.br.agendacontatos.ui.MainActivity;
import posgraducao.lamfsistemas.com.br.agendacontatos.util.MeuOpenHelper;

public class InsertContact extends AppCompatActivity implements View.OnClickListener {

    MeuOpenHelper meuOpenHelper;

    private ArrayList<Contact> contacts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_contact);
        meuOpenHelper = new MeuOpenHelper(getApplicationContext());

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }

    public int getLastId(int Id) {

        TextView txtName = (TextView)this.findViewById(R.id.txtName );
        TextView txtFone = (TextView)this.findViewById(R.id.txtFone);
        TextView txtId = (TextView)this.findViewById(R.id.txtId);

        SQLiteDatabase db = meuOpenHelper.getWritableDatabase();
        String table = "contatos";
        String[] projection = {"id"};


        Cursor c =
                db.query(table,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if (c.moveToLast()) {
           return  Integer.parseInt(c.getString(c.getColumnIndex("id"))) +1 ;
        }else{
            return 1 ;
        }
    }
    @Override
    public void onClick(View view) {
        Intent it =null;
        switch (view.getId()) {
            case R.id.btnSave:

                TextView txtName = (TextView)this.findViewById(R.id.txtName );
                TextView txtFone = (TextView)this.findViewById(R.id.txtFone);
                if(!(TextUtils.isEmpty(txtName.getText()))  ){

//                    SQLiteDatabase db = meuOpenHelper.getWritableDatabase();
//                    ContentValues contentValues = new ContentValues();
//
//                    contentValues.put("name", txtName.getText().toString());
//                    contentValues.put("fone", txtFone.getText().toString());
//                    long idContato = db.insert("contatos", null, contentValues);
                    BaseDados db =   BaseDados.getDatabase(getApplicationContext()  );
                    db.ContactDao().criar(new Contact( txtName.getText().toString(),txtFone.getText().toString()));


                    Toast.makeText(getApplicationContext(),
                            this.getResources().getString(R.string.saveInfo)  ,
                            Toast.LENGTH_SHORT).show();

                    it = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(it);
                }else{
                    Toast.makeText(this, this.getResources().getString(R.string.withoutName), Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }


}
