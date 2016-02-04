package com.mmdt.syna.p017a.p018a;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.mmdt.syna.view.call.CallingActivity;
import mobi.mmdt.ott.core.model.database.ContactShow;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;
import mobi.mmdt.ott.core.p051a.ArabicUtilities;
import org.catrobat.paintroid.R.R;
import org.linphone.core.Privacy;
import org.linphone.core.VideoSize;

/* renamed from: com.mmdt.syna.a.a.a */
public class CallNotification {
    private static NotificationManager f1582a;
    private static int f1583b;

    static {
        f1583b = -1;
    }

    public static void m2377a(Context context, int i, String str, boolean z) {
        try {
            Thread thread = new Thread(new CallNotification(context, i, str, z));
            thread.setPriority(1);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void m2379c(Context context, int i, String str, boolean z) {
        try {
            if (f1583b == -1 || f1583b != i) {
                f1583b = i;
                switch (i) {
                    case VideoSize.CIF /*1*/:
                        ContactShow a = SynaContacts.m5002a(context, str, false);
                        String c = (a == null || a.m5062c() == null) ? str : a.m5062c();
                        Intent intent = new Intent(context, CallingActivity.class);
                        intent.putExtra("com.mmdt.sipclient.view.call.CallingActivity.NUMBER_FROM_CALLER", str);
                        intent.putExtra("com.mmdt.sipclient.view.call.CallingActivity.IS_SUN_FROM_CALLER", z);
                        intent.setFlags(Privacy.DEFAULT);
                        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 134217728);
                        Notification notification = new Notification(2130838497, ArabicUtilities.m4412c(context.getString(R.app_name)), System.currentTimeMillis());
                        notification.setLatestEventInfo(context, ArabicUtilities.m4412c(context.getString(R.app_name)), ArabicUtilities.m4412c(context.getString(2131493697) + " " + c), activity);
                        notification.flags |= 2;
                        f1582a.notify(974235626, notification);
                        return;
                    case VideoSize.HVGA /*2*/:
                        f1582a.cancel(974235626);
                        f1583b = -1;
                        return;
                    default:
                        return;
                }
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
