package posgraducao.lamfsistemas.com.br.calculogasolina;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class AlarmeReceiver extends BroadcastReceiver  {
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Abastercer agora!", Toast.LENGTH_SHORT).show();
    }
}
