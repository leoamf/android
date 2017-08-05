package posgraducao.lamfsistemas.com.br.agendacontatos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contacts = new ArrayList<>();
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadContacts();

        lstView = (ListView)findViewById(R.id.lstContacts);
        ContactsAdapter adp
                = new ContactsAdapter(getApplicationContext(),contacts);
        lstView.setAdapter(adp);


      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void loadContacts(){
        contacts.add(new Contact(1,"Leonardo","12332122"));
        contacts.add(new Contact(2,"Alfredo Gordo Safado","89872256"));
        contacts.add(new Contact(3,"Ana Maria","55255866"));
    }
}
