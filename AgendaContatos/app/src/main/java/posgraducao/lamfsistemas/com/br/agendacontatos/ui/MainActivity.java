package posgraducao.lamfsistemas.com.br.agendacontatos.ui;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observer;

import posgraducao.lamfsistemas.com.br.agendacontatos.DAO.BaseDados;
import posgraducao.lamfsistemas.com.br.agendacontatos.R;
import posgraducao.lamfsistemas.com.br.agendacontatos.communication.RetrofitAsyncTask;
import posgraducao.lamfsistemas.com.br.agendacontatos.model.Contact;
import posgraducao.lamfsistemas.com.br.agendacontatos.model.ContactModel;
import posgraducao.lamfsistemas.com.br.agendacontatos.model.Contacts;
import posgraducao.lamfsistemas.com.br.agendacontatos.model.ContactsAdapter;
import posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel.People;
import posgraducao.lamfsistemas.com.br.agendacontatos.service.SincronizarDadosWeb;
import posgraducao.lamfsistemas.com.br.agendacontatos.util.MeuOpenHelper;
import posgraducao.lamfsistemas.com.br.agendacontatos.util.Util;
import posgraducao.lamfsistemas.com.br.agendacontatos.viewmodel.ContactViewModel;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener , LifecycleRegistryOwner, LifecycleObserver  {


    public final static String ID_CONTATC = "posgraducao.lamfsistemas.com.br.agendacontatos.ID_CONTATC";


    public static MeuOpenHelper meuOpenHelper;
    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    private    Contact contado  ;
    private ContactsAdapter adp = null ;
    private static long  idList;
    private static int  posList ;
    private AlertDialog.Builder alertDelete;
    private ContactsAdapter adapter;
    private ContactViewModel viewModel;
    private ListView lstView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        FloatingActionButton btnAlert = (FloatingActionButton) findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(this);

        FloatingActionButton btnLoad = (FloatingActionButton) findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);

        getLifecycle().addObserver(this);
        viewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        lstView = (ListView)findViewById(R.id.lstContacts);

        viewModel.getContacts().observe(this, new android.arch.lifecycle.Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {
                if (contacts != null) {
                    lstView.setAdapter(new ContactsAdapter(getApplicationContext(), contacts));
                    associateList();
                }
            }
        });

    }
    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroyLifecycke() {
        Log.d(MainActivity.class.getSimpleName(), "Lifecycle.Event.ON_DESTROY");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreateLifecycke() {
        Log.d(MainActivity.class.getSimpleName(), "Lifecycle.Event.ON_CREATE");
    }
    private void associateList(){

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent it = new Intent(getApplicationContext(),DetailContact.class);
                it.putExtra(ID_CONTATC, ((Contact)(((ListView) parent).getAdapter().getItem(position))).getId());
                startActivity(it);
            }
        });
        alertDelete =  new AlertDialog.Builder(this)
                .setTitle(R.string.deleteRecord)
                .setMessage(R.string.askDeleteRecord)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                       // delete(contado.getId());
                        BaseDados db =   BaseDados.getDatabase(getApplicationContext()  );
                        db.ContactDao().excluir( db.ContactDao().findPeloId( contado.getId()));
                        Toast.makeText(getApplicationContext(),String.valueOf(contado.getName()) + getString(R.string.deleteSucess) ,  Toast.LENGTH_SHORT).show();

                    }})
                .setNegativeButton(android.R.string.no, null);


        lstView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                posList = pos;
                idList=id;
                contado = ((Contact)(((ListView) arg0).getAdapter().getItem(pos)));
                alertDelete.show();
                return true;
            }
        });

    }

    public void delete(int idSelected) {
        BaseDados db =   BaseDados.getDatabase(getApplicationContext()  );
        Contact contact = db.ContactDao().findPeloId(idSelected);
        db.ContactDao().excluir(contact);
        db.close();
    }
    public void received(int idSelected) {
        BaseDados db =   BaseDados.getDatabase(getApplicationContext()  );
        Contact contact = db.ContactDao().findPeloId(idSelected);
        db.ContactDao().excluir(contact);
        db.close();
    }

    @Override
    public void onClick(View view) {
        Intent  it =null;

        switch (view.getId()) {
            case R.id.btnAdd:
                it = new Intent(getApplicationContext(),InsertContact.class);
                startActivity(it);
                break;
            case R.id.btnAlert:
                ativarAlarme();
                break;
            case R.id.btnLoad:
                Intent i = new Intent(MainActivity.this, SincronizarDadosWeb.class);
                startService(i);
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
