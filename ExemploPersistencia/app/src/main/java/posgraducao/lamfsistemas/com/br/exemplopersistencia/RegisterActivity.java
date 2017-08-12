package posgraducao.lamfsistemas.com.br.exemplopersistencia;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        SharedPreferences sharedPreferences = getSharedPreferences("exemplo",0);
        String email =  sharedPreferences.getString("email","default");
        boolean isConnect = sharedPreferences.getBoolean("isConnect",false);
        int quantidade = sharedPreferences.getInt("quantidade",8873);

        if(isConnect){
            Toast toast =  Toast.makeText(getApplicationContext(),email,Toast.LENGTH_LONG);
            toast.show();
        }else{
            finish();
        }
    }
}
