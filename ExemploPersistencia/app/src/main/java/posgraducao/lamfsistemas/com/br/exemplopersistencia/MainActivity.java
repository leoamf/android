package posgraducao.lamfsistemas.com.br.exemplopersistencia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences ;
    String nomeArquivo = "meuArquivo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("exemplo",0);
    }

    public void callCadasdro(View  view){
        Intent it = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(it);
    }

    public void Login(View  view){
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putString("email","leoamf@gmail.com");
        editor.putBoolean("isConnect",true);
        editor.putInt("quantidade",99);
        editor.commit();
    }
    public void readInterno(View  view) {
        File file = getFileStreamPath(nomeArquivo);
        try{
            FileInputStream fileOutputStream =  new FileInputStream(file);
            ObjectInputStream objectInputStream =  new ObjectInputStream(fileOutputStream);
            String retorno =  (String) objectInputStream.readObject();
            objectInputStream.close();
            fileOutputStream.close();
            Toast toast =  Toast.makeText(getApplicationContext(),retorno,Toast.LENGTH_LONG);
            toast.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void writeInterno(View  view) {

        File file = getFileStreamPath(nomeArquivo);
        try{
            FileOutputStream fileOutputStream =  new FileOutputStream(file);
            ObjectOutputStream objectOutputStream =  new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject("Salvando em memoria");

            objectOutputStream.close();
            fileOutputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
