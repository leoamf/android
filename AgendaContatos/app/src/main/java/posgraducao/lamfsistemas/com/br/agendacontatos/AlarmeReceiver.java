package posgraducao.lamfsistemas.com.br.agendacontatos;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by Leonardo on 08/08/2017.
 */

public class AlarmeReceiver extends BroadcastReceiver  {

    public static final int SIMPLE_NOTIFICATION_ID = 10001;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Liga Para tu esposa agora!", Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Alarme da agenda")
                        .setContentText("Liga para tua mulher porra!");

        NotificationManager  mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(SIMPLE_NOTIFICATION_ID, mBuilder.build());
    }
}
