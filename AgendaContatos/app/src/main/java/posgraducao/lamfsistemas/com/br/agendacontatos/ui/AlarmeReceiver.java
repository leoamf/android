package posgraducao.lamfsistemas.com.br.agendacontatos.ui;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import posgraducao.lamfsistemas.com.br.agendacontatos.R;

/**
 * Created by Leonardo on 08/08/2017.
 */

public class AlarmeReceiver extends BroadcastReceiver  {

    public static final int SIMPLE_NOTIFICATION_ID = 10001;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, context.getResources().getString(R.string.alert_alarm), Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(context.getResources().getString(R.string.text_alarm))
                        .setContentText(context.getResources().getString(R.string.alert_alarm));

        NotificationManager  mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(SIMPLE_NOTIFICATION_ID, mBuilder.build());
    }
}
