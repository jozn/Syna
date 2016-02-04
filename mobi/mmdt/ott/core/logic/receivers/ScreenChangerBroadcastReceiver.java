package mobi.mmdt.ott.core.logic.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import mobi.mmdt.ott.core.logic.core.NotificationService;
import mobi.mmdt.ott.core.logic.message.TransmitterService;
import org.linphone.SipLibService;

public class ScreenChangerBroadcastReceiver extends BroadcastReceiver {
    private boolean m4832a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo == null) {
            return false;
        }
        for (NetworkInfo state : allNetworkInfo) {
            if (state.getState() == State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase("android.intent.action.SCREEN_OFF") && !m4832a(context)) {
            try {
                context.stopService(new Intent(context, TransmitterService.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                context.stopService(new Intent(context, SipLibService.class));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                context.stopService(new Intent(context, NotificationService.class));
            } catch (Exception e22) {
                e22.printStackTrace();
            }
        }
    }
}
