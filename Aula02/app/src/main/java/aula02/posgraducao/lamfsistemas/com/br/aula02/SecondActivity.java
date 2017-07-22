package aula02.posgraducao.lamfsistemas.com.br.aula02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String name = getIntent().getStringExtra("name");
        String parametro = getIntent().getStringExtra("parametro");
        Toast.makeText(getApplicationContext(), parametro, Toast.LENGTH_LONG ).show();
    }
}
