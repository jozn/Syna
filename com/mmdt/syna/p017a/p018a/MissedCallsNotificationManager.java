package com.mmdt.syna.p017a.p018a;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.mmdt.syna.view.main.MainActivity;
import mobi.mmdt.ott.core.model.database.ContactShow;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;
import mobi.mmdt.ott.core.model.database.p064e.LogRow;

/* renamed from: com.mmdt.syna.a.a.e */
public class MissedCallsNotificationManager {
    private static NotificationManager f1590a;

    public static void m2391a(Context context) {
        Thread thread = new Thread(new MissedCallsNotificationManager(context));
        thread.setPriority(1);
        thread.start();
    }

    private static void m2393b(Context context, LogRow[] logRowArr) {
        Notification notification;
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("com.mmdt.sipclient.view.MainActivity.JOB_VALUE", 3);
        intent.putExtra("com.mmdt.sipclient.view.MainActivity.KEY_NOTIFICATION_PHONE_NUMBER", logRowArr[0].m5064b());
        intent.setFlags(67108864);
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 134217728);
        if (logRowArr.length == 1) {
            String b = logRowArr[0].m5064b();
            ContactShow a = SynaContacts.m5002a(context, logRowArr[0].m5064b(), false);
            if (!(a == null || a.m5062c() == null)) {
                b = a.m5062c();
            }
            Notification notification2 = new Notification(2130838098, "Missed Call From : " + b, logRowArr[0].m5063a());
            notification2.setLatestEventInfo(context, "Missed Call", "From : " + b, activity);
            notification = notification2;
        } else {
            notification = new Notification(2130838098, "Missed Calls.", System.currentTimeMillis());
            notification.setLatestEventInfo(context, logRowArr.length + " Missed Calls.", "", activity);
        }
        notification.flags |= 16;
        f1590a.notify(34868814, notification);
    }
}
