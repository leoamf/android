package posgraducao.lamfsistemas.com.br.agendacontatos;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String OBJ_CONTATC = "posgraducao.lamfsistemas.com.br.agendacontatos.OBJ_CONTATC";
    public final static String OBJ_LIST_CONTATC = "posgraducao.lamfsistemas.com.br.agendacontatos.OBJ_LIST_CONTATCS";

    private ArrayList<Contact> contacts = new ArrayList<>();
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            contacts = (ArrayList<Contact>)extras.getSerializable(OBJ_LIST_CONTATC);
        }else {
            loadContacts();
        }
        lstView = (ListView)findViewById(R.id.lstContacts);
        ContactsAdapter adp
                = new ContactsAdapter(getApplicationContext(),contacts);
        lstView.setAdapter(adp);

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent it = new Intent(getApplicationContext(),DetailContact.class);
                it.putExtra(OBJ_CONTATC, contacts.get((int)id));
                startActivity(it);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        FloatingActionButton btnAlert = (FloatingActionButton) findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(this);


    }


    private void loadContacts(){
        contacts.add(new Contact(1,"Leonardo","12332122"));
        contacts.add(new Contact(2 ,"Alfredo Gordo Safado","89872256"));
        contacts.add(new Contact(3,"Ana Maria","55255866"));
    }

    @Override
    public void onClick(View view) {
        Intent  it =null;
        switch (view.getId()) {
            case R.id.btnAdd:
                it = new Intent(getApplicationContext(),InsertContact.class);
                it.putExtra(OBJ_LIST_CONTATC,contacts);
                startActivity(it);
                break;
            case R.id.btnAlert:
                ativarAlarme();
                break;

        }
    }

    private void ativarAlarme() {
        TimePickerDialog.OnTimeSetListener tratador =
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Intent it = new Intent(MainActivity.this, AlarmeReceiver.class);
                        PendingIntent pit = PendingIntent.getBroadcast(
                                MainActivity.this, 0, it, 0);

                        AlarmManager alarmManager = (AlarmManager)
                                getSystemService(Context.ALARM_SERVICE);

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 0);

                        alarmManager.set(
                                AlarmManager.RTC_WAKEUP,
                                calendar.getTimeInMillis(),
                                pit);
                    }
                };
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                tratador,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true);
        dialog.show();
    }
}
