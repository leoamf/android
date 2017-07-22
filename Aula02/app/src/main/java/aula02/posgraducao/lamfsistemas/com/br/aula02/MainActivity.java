package aula02.posgraducao.lamfsistemas.com.br.aula02;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btAcao = (Button) findViewById(R.id.btAcao);
        btAcao.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                i.putExtra("parametro", "Teste de mensagem");
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }

        });


        Button btMapa = (Button) findViewById(R.id.btMapa);
        btMapa.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:9998889988"));
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Teste", Toast.LENGTH_LONG).show();
                }

            }

        });
    }
}
