package org.jivesoftware.smackx.ping.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.util.Async;
import org.jivesoftware.smackx.ping.PingManager;

/* renamed from: org.jivesoftware.smackx.ping.android.a */
final class ServerPingWithAlarmManager extends BroadcastReceiver {
    ServerPingWithAlarmManager() {
    }

    public void onReceive(Context context, Intent intent) {
        ServerPingWithAlarmManager.LOGGER.fine("Ping Alarm broadcast received");
        for (XMPPConnection xMPPConnection : ServerPingWithAlarmManager.INSTANCES.keySet()) {
            if (getInstanceFor(xMPPConnection).isEnabled()) {
                ServerPingWithAlarmManager.LOGGER.fine("Calling pingServerIfNecessary for connection " + xMPPConnection.getConnectionCounter());
                Async.m5844a(new ServerPingWithAlarmManager$2$1(this, PingManager.getInstanceFor(xMPPConnection)), "PingServerIfNecessary (" + xMPPConnection.getConnectionCounter() + ')');
            } else {
                ServerPingWithAlarmManager.LOGGER.fine("NOT calling pingServerIfNecessary (disabled) on connection " + xMPPConnection.getConnectionCounter());
            }
        }
    }
}
