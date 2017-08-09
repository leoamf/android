package posgraducao.lamfsistemas.com.br.agendacontatos;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InsertContact extends AppCompatActivity implements View.OnClickListener {

    public final static String OBJ_LIST_CONTATC = "posgraducao.lamfsistemas.com.br.agendacontatos.OBJ_LIST_CONTATCS";

    private ArrayList<Contact> contacts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_contact);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            contacts = (ArrayList<Contact>)extras.getSerializable(OBJ_LIST_CONTATC);
        }


        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent it =null;
        switch (view.getId()) {
            case R.id.btnSave:

                TextView txtName = (TextView)this.findViewById(R.id.txtName );
                TextView txtFone = (TextView)this.findViewById(R.id.txtFone);
                if(!(TextUtils.isEmpty(txtName.getText()))  ){
                    contacts.add(new Contact(contacts.size()+1, txtName.getText().toString(),txtFone.getText().toString()));
                    Toast.makeText(this, this.getResources().getString(R.string.saveInfo), Toast.LENGTH_SHORT).show();

                    it = new Intent(getApplicationContext(), MainActivity.class);
                    it.putExtra(OBJ_LIST_CONTATC,contacts);
                    startActivity(it);
                }else{
                    Toast.makeText(this, this.getResources().getString(R.string.withoutName), Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
