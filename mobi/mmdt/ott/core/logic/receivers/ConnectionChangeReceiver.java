package mobi.mmdt.ott.core.logic.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.util.Log;
import mobi.mmdt.ott.core.logic.core.NotificationService;
import mobi.mmdt.ott.core.logic.message.TransmitterService;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import org.linphone.SipLibService;

public class ConnectionChangeReceiver extends BroadcastReceiver {
    private boolean m4831a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
                if (allNetworkInfo != null) {
                    for (NetworkInfo state : allNetworkInfo) {
                        if (state.getState() == State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void onReceive(Context context, Intent intent) {
        try {
            boolean a = m4831a(context);
            if (a) {
                Log.i("NET", "connecte" + a);
                try {
                    context.startService(new Intent(context, NotificationService.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    context.startService(new Intent(context, TransmitterService.class));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                try {
                    context.startService(new Intent(context, SipLibService.class));
                    return;
                } catch (Exception e22) {
                    e22.printStackTrace();
                    return;
                }
            }
            Log.i("NET", "not connecte" + a);
            try {
                context.stopService(new Intent(context, TransmitterService.class));
            } catch (Exception e222) {
                e222.printStackTrace();
            }
            try {
                context.stopService(new Intent(context, SipLibService.class));
            } catch (Exception e2222) {
                e2222.printStackTrace();
            }
            if (AppSettings.m4867a(context).m4911u()) {
                try {
                    context.stopService(new Intent(context, NotificationService.class));
                } catch (Exception e22222) {
                    e22222.printStackTrace();
                }
            }
        } catch (Exception e222222) {
            e222222.printStackTrace();
        }
    }
}
