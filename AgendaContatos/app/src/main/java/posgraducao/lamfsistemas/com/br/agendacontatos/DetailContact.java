package posgraducao.lamfsistemas.com.br.agendacontatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);


        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            Contact customer = (Contact)extras.getSerializable("contact");
        }


    }
}
