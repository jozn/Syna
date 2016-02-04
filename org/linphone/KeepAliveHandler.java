package org.linphone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.linphone.mediastream.Log;

public class KeepAliveHandler extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.m6032i("Keep alive handler invoked");
        try {
            if (LinphoneManager.getLcIfManagerNotDestroyedOrNull() != null) {
                LinphoneManager.getLc().refreshRegisters();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Log.m6028e("Cannot sleep for 2s", e);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
