package posgraducao.lamfsistemas.com.br.calculogasolina;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.content.Context;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCalcular = (Button) findViewById(R.id.btnCalcular);
        Button btnAlarme = (Button) findViewById(R.id.btnAlarme);
        Button btnFoto = (Button) findViewById(R.id.btnFoto);

        btnCalcular.setOnClickListener(this);
        btnAlarme.setOnClickListener(this);
        btnFoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnCalcular ) {
            calcular(view);
        }else  if(view.getId() == R.id.btnAlarme ) {
            ativarAlarme();
        }else  if(view.getId() == R.id.btnFoto ) {
            capturarImagem();
        }
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
    private void capturarImagem(){
       int hasCamera = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA);
        if (hasCamera != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_IMAGE_CAPTURE);

        } else {
            mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(mIntent, REQUEST_IMAGE_CAPTURE);

        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case REQUEST_IMAGE_CAPTURE:

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(mIntent, REQUEST_IMAGE_CAPTURE);

                } else {
                    Toast.makeText(MainActivity.this, "Permission denied to read camera ", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Intent intent = new Intent(this, CameraActivity.class);
            intent.putExtras(data.getExtras());
            startActivity(intent);
        }
    }



}
