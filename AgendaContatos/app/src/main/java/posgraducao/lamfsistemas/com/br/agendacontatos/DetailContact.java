package posgraducao.lamfsistemas.com.br.agendacontatos;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailContact extends AppCompatActivity implements View.OnClickListener{
    public final static String OBJ_CONTATC = "posgraducao.lamfsistemas.com.br.agendacontatos.OBJ_CONTATC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);


        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            Contact obj = (Contact)extras.getSerializable(OBJ_CONTATC);

            TextView txtName = (TextView)this.findViewById(R.id.txtName );
            TextView txtFone = (TextView)this.findViewById(R.id.txtFone);
            TextView txtId = (TextView)this.findViewById(R.id.txtId);

            txtId.setText(String.valueOf(  obj.id));
            txtName.setText(obj.name);
            txtFone.setText(obj.fone);

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
}
