package posgraducao.lamfsistemas.com.br.agendacontatos.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Leonardo on 12/08/2017.
 */

public  class Util {

    public static boolean IsConnected(Context contexto) {
        ConnectivityManager manager = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnected())
            return true;

        return false;
    }

}
