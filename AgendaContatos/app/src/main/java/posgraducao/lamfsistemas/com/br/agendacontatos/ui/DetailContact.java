package posgraducao.lamfsistemas.com.br.agendacontatos.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import posgraducao.lamfsistemas.com.br.agendacontatos.DAO.BaseDados;
import posgraducao.lamfsistemas.com.br.agendacontatos.R;
import posgraducao.lamfsistemas.com.br.agendacontatos.model.Contact;
import posgraducao.lamfsistemas.com.br.agendacontatos.util.MeuOpenHelper;

public class DetailContact extends AppCompatActivity implements View.OnClickListener{

    public final static String ID_CONTATC = "posgraducao.lamfsistemas.com.br.agendacontatos.ID_CONTATC";
    MeuOpenHelper meuOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);

        meuOpenHelper = new MeuOpenHelper(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            int idContact = (int)extras.getSerializable(ID_CONTATC);
            getContact(idContact);

        }


        FloatingActionButton btnInternet = (FloatingActionButton) findViewById(R.id.btnInternet);
        btnInternet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent it =null;
        switch (view.getId()) {
            case R.id.btnInternet:
                TextView txtName = (TextView)this.findViewById(R.id.txtName );
                Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse("http://www.google.com.br/search?q=" + txtName.getText()));
                startActivity(intent);
                break;

        }
    }

    public void getContact(int Id) {

            TextView txtName = (TextView)this.findViewById(R.id.txtName );
            TextView txtFone = (TextView)this.findViewById(R.id.txtFone);
            TextView txtId = (TextView)this.findViewById(R.id.txtId);

            BaseDados db =   BaseDados.getDatabase(getApplicationContext()  );
            Contact contato =   db.ContactDao().findPeloId(Id);
            txtId.setText(String.valueOf( contato.getId()));
            txtName.setText(contato.getName());
            txtFone.setText(contato.getFone());

    }
}
