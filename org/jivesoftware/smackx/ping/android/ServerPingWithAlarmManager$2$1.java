package org.jivesoftware.smackx.ping.android;

import org.jivesoftware.smackx.ping.PingManager;

class ServerPingWithAlarmManager$2$1 implements Runnable {
    final /* synthetic */ ServerPingWithAlarmManager this$0;
    final /* synthetic */ PingManager val$pingManager;

    ServerPingWithAlarmManager$2$1(ServerPingWithAlarmManager serverPingWithAlarmManager, PingManager pingManager) {
        this.this$0 = serverPingWithAlarmManager;
        this.val$pingManager = pingManager;
    }

    public void run() {
        this.val$pingManager.pingServerIfNecessary();
    }
}
