package mobi.mmdt.ott.core.logic.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import mobi.mmdt.ott.core.logic.core.NotificationService;
import mobi.mmdt.ott.core.logic.message.TransmitterService;
import org.linphone.SipLibService;

public class BootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            context.startService(new Intent(context, NotificationService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            context.registerReceiver(new ScreenChangerBroadcastReceiver(), intentFilter);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            context.startService(new Intent(context, TransmitterService.class));
        } catch (Exception e22) {
            e22.printStackTrace();
        }
        try {
            context.startService(new Intent(context, SipLibService.class));
        } catch (Exception e222) {
            e222.printStackTrace();
        }
    }
}
