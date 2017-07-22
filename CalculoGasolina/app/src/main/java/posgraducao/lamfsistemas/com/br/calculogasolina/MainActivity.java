package posgraducao.lamfsistemas.com.br.calculogasolina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btAcao = (Button) findViewById(R.id.btnCalcular);
        btAcao.setOnClickListener(this);
/*

        btAcao.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                float precoGasolina = 0;
                float precoAlcool = 0;
                EditText valAlcool = (EditText)findViewById(R.id.txtAlcool);
                EditText valGasolina = (EditText)findViewById(R.id.txtGasolina);

                try{
                    precoGasolina = Float.valueOf(valGasolina.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                try{
                    precoAlcool = Float.valueOf(valAlcool.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                if((precoAlcool/precoGasolina) < 0.7){
                    Toast.makeText(getApplicationContext(), "Alcool", Toast.LENGTH_LONG ).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Gasolina", Toast.LENGTH_LONG ).show();
                }
            }

        });*/
    }
    public void calcular(View view) {
        float precoGasolina = 0;
        float precoAlcool = 0;
        EditText valAlcool = (EditText) findViewById(R.id.txtAlcool);
        EditText valGasolina = (EditText) findViewById(R.id.txtGasolina);

        try {
            precoGasolina = Float.valueOf(valGasolina.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            precoAlcool = Float.valueOf(valAlcool.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if ((precoAlcool / precoGasolina) < 0.7) {
            Toast.makeText(getApplicationContext(), "Alcool", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Gasolina", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onClick(View view) {
         if(view.getId() == R.id.btnCalcular )
            calcular( view);
    }

}
