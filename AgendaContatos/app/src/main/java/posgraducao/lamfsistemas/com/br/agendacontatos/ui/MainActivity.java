package posgraducao.lamfsistemas.com.br.agendacontatos.ui;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import posgraducao.lamfsistemas.com.br.agendacontatos.R;
import posgraducao.lamfsistemas.com.br.agendacontatos.communication.RetrofitAsyncTask;
import posgraducao.lamfsistemas.com.br.agendacontatos.model.Contact;
import posgraducao.lamfsistemas.com.br.agendacontatos.model.ContactsAdapter;
import posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel.People;
import posgraducao.lamfsistemas.com.br.agendacontatos.util.MeuOpenHelper;
import posgraducao.lamfsistemas.com.br.agendacontatos.util.Util;
import posgraducao.lamfsistemas.com.br.agendacontatos.viewmodel.ContactViewModel;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,   RetrofitAsyncTask.GetNewsApiListener {

    public final static String ID_CONTATC = "posgraducao.lamfsistemas.com.br.agendacontatos.ID_CONTATC";
    private static final String URL_SERVICO = "https://swapi.co/api/";

    private MeuOpenHelper meuOpenHelper;

    private ArrayList<Contact> contacts = new ArrayList<>();
    private ListView lstView;
    private ContactsAdapter adp = null ;
    private static long  idList;
    private static int  posList ;
    private AlertDialog.Builder alertDelete;

    private ContactViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meuOpenHelper = new MeuOpenHelper(getApplicationContext());

        loadDBContacts();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        FloatingActionButton btnAlert = (FloatingActionButton) findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(this);

        FloatingActionButton btnLoad = (FloatingActionButton) findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);

    }

    private void associateList(){

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent it = new Intent(getApplicationContext(),DetailContact.class);
                it.putExtra(ID_CONTATC, contacts.get((int)id).getId());
                startActivity(it);
            }
        });
        alertDelete =  new AlertDialog.Builder(this)
                .setTitle(R.string.deleteRecord)
                .setMessage(R.string.askDeleteRecord)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        delete(contacts.get((int)idList).getId());
                        Toast.makeText(getApplicationContext(),String.valueOf(contacts.get((int)idList).getName()) + getString(R.string.deleteSucess) ,  Toast.LENGTH_SHORT).show();

                        contacts.remove(posList);
                        adp.notifyDataSetChanged();
                    }})
                .setNegativeButton(android.R.string.no, null);


        lstView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                posList = pos;
                idList=id;
                alertDelete.show();
                return true;
            }
        });

    }

    public void insert(String name,String fone) {
        SQLiteDatabase db = meuOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("fone", fone);
        long idContato = db.insert("contatos", null, contentValues);
        db.close();
    }

    public void update(Contact contact){
        SQLiteDatabase db = meuOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", contact.getName());
        contentValues.put("fone", contact.getFone());

        String where = "id = ? ";
        String[] whereArg = {  String.valueOf(contact.getId())};

        db.beginTransaction();
        db.update("contatos", contentValues, where, whereArg);
        db.endTransaction();
    }

    public void delete(int idSelected) {
        SQLiteDatabase db = meuOpenHelper.getWritableDatabase();
        String whereClause = "id = ?";
        int id = idSelected;
        String[] whereArgs = { String.valueOf(id)};
        db.delete("contatos", whereClause, whereArgs);
    }

    public Contact getContact(String name){
        SQLiteDatabase db = meuOpenHelper.getWritableDatabase();
        String[] projection = {"id","name","fone"};
        String whereClause = "name = ?";
        String[] whereArgs = { name};

        Cursor cursor = db.query("contatos", projection, whereClause, whereArgs, null, null, null);
        Contact registro =null;
        if(cursor.moveToFirst()){
            do{
                registro = new Contact( cursor.getInt(cursor.getColumnIndex("id")),
                                        cursor.getString(cursor.getColumnIndex("name")),
                                        cursor.getString(cursor.getColumnIndex("fone")));
            } while(cursor.moveToNext());
        }
        return registro;

    }

    @Override
    public void onNewsApiResult(List<People> peoples) {
        contacts.clear();
        for(People obj:peoples){
            String name = obj.getName();
            String fone = obj.getHeight();
            Contact contact = getContact(name);
            if(contact!=null){
                this.update(contact);
            }else {
                this.insert(name, fone);
            }
        }
        loadDBContacts();
    }

    private void loadHTTPContacts(){
        if(Util.IsConnected(getApplicationContext())){
            RetrofitAsyncTask asyncTask = new RetrofitAsyncTask(this) ;
            asyncTask.execute(URL_SERVICO);
        }
    }
    private void loadDBContacts(){

        SQLiteDatabase db = meuOpenHelper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from contatos", null);
        c.moveToFirst();
        List<String> carrosStringList = new ArrayList();

        if(c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex("name"));
                String fone = c.getString(c.getColumnIndex("fone"));
                int id = c.getInt(c.getColumnIndex("id"));
                contacts.add(new Contact(id, name, fone));
            } while (c.moveToNext());
        }
        lstView = (ListView)findViewById(R.id.lstContacts);
        adp  = new ContactsAdapter(getApplicationContext(),contacts);
        lstView.setAdapter(adp);
        associateList();

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
                if(!Util.IsConnected(getApplicationContext()))
                    Toast.makeText(getApplicationContext(), this.getResources().getString(R.string.offline), Toast.LENGTH_LONG).show();
                else{
                    loadHTTPContacts();
                }
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