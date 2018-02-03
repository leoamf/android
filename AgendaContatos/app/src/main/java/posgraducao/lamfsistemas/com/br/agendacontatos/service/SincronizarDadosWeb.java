package posgraducao.lamfsistemas.com.br.agendacontatos.service;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import posgraducao.lamfsistemas.com.br.agendacontatos.DAO.BaseDados;
import posgraducao.lamfsistemas.com.br.agendacontatos.HttpModel.People;
import posgraducao.lamfsistemas.com.br.agendacontatos.R;
import posgraducao.lamfsistemas.com.br.agendacontatos.communication.RetrofitAsyncTask;
import posgraducao.lamfsistemas.com.br.agendacontatos.model.Contact;
import posgraducao.lamfsistemas.com.br.agendacontatos.model.ContactsAdapter;
import posgraducao.lamfsistemas.com.br.agendacontatos.ui.MainActivity;
import posgraducao.lamfsistemas.com.br.agendacontatos.util.Util;

/**
 * Created by l.machado.de.freitas on 03/02/2018.
 */

public class SincronizarDadosWeb  extends Service {
    private String uuid = "";

    public SincronizarDadosWeb() {
        uuid = UUID.randomUUID().toString();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("SincronizarDadosWeb", "onStartCommand:Before(" + uuid + ")");

        MyAsyncTask task = new MyAsyncTask();
        task.execute();

        return START_STICKY;
    }


    private class MyAsyncTask extends AsyncTask implements RetrofitAsyncTask.GetNewsApiListener {

        @Override
        public void onNewsApiResult(List<People> peoples) {
            BaseDados db =   BaseDados.getDatabase(getApplicationContext()  );

            for(People obj:peoples){
                String name = obj.getName();
                String fone = obj.getHeight();
                List<Contact> contacts =  db.ContactDao().findPeloName(name)  ;
                if(contacts.size() >0){
                    Contact contato = contacts.get(0);
                    contato.setFone(fone);
                    contato.setName(name);
                    db.ContactDao().atualizar(contato);
                }else {
                    db.ContactDao().criar(new Contact(0,name,fone));
                }
            }

            Toast.makeText(getApplicationContext(),
                    getApplicationContext().getResources().getString(R.string.infoLoadWeb)  ,
                    Toast.LENGTH_SHORT).show();

        }

        private static final String URL_SERVICO = "https://swapi.co/api/";
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Intent intent = new Intent("received.received");
                intent.putExtra("result", "Chamando servicosWeb");
                if(!Util.IsConnected(getApplicationContext()))
                    Toast.makeText(getApplicationContext(), getApplication().getResources().getString(R.string.offline), Toast.LENGTH_LONG).show();
                else{
                    loadHTTPContacts();
                }

                LocalBroadcastManager.getInstance(SincronizarDadosWeb.this).
                        sendBroadcast(intent);

            } catch (Exception  e) {
                e.printStackTrace();
            }

            return null;
        }

        private void loadHTTPContacts(){
            if(Util.IsConnected(getApplicationContext())){
                RetrofitAsyncTask asyncTask = new RetrofitAsyncTask(this) ;
                asyncTask.execute(URL_SERVICO);
            }
        }
        @Override
        protected void onPostExecute(Object o) {
            Log.d("MyFirstService", "onStartCommand:After");


            super.onPostExecute(o);
        }
    }
}
