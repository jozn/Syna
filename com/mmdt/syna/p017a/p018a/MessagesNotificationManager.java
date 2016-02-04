package com.mmdt.syna.p017a.p018a;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.mmdt.syna.view.conversation.conversationpage.activities.GroupConversationActivity;
import com.mmdt.syna.view.conversation.conversationpage.activities.SingleConversationActivity;
import com.mmdt.syna.view.main.MainActivity;
import com.mmdt.syna.view.tools.p038c.StickerManager;
import mobi.mmdt.ott.core.model.database.ContactShow;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;
import mobi.mmdt.ott.core.model.database.p063d.GroupRow;
import mobi.mmdt.ott.core.model.database.p063d.Groups;
import mobi.mmdt.ott.core.model.database.p065f.MessageRow;
import mobi.mmdt.ott.core.model.database.p065f.Messages;
import mobi.mmdt.p041a.DateAndTime;

/* renamed from: com.mmdt.syna.a.a.c */
public class MessagesNotificationManager {
    private static NotificationManager f1588a;

    public static void m2382a(Context context) {
        try {
            Thread thread = new Thread(new MessagesNotificationManager(context));
            thread.setPriority(1);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String m2385b(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
                if (resolveInfo.activityInfo.applicationInfo.packageName.equalsIgnoreCase(context.getPackageName())) {
                    return resolveInfo.activityInfo.name;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void m2386b(Context context, int i) {
        try {
            MessagesNotificationManager.m2388c(context, i);
            Intent intent = new Intent();
            intent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
            intent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", "com.mmdt.sipclient.view.MainActivity");
            if (i == 0) {
                intent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", false);
            } else {
                intent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", true);
                intent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", new StringBuilder(String.valueOf(i)).toString());
            }
            intent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", context.getPackageName());
            context.sendBroadcast(intent);
            String b = MessagesNotificationManager.m2385b(context);
            if (b != null) {
                Intent intent2 = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
                intent2.putExtra("badge_count", i);
                intent2.putExtra("badge_count_package_name", context.getPackageName());
                intent2.putExtra("badge_count_class_name", b);
                context.sendBroadcast(intent2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void m2387b(Context context, Uri[] uriArr) {
        try {
            Intent intent;
            Notification notification;
            MessageRow b = Messages.m5109b(context, uriArr[0]);
            Intent intent2;
            if (uriArr.length == 1 || Messages.m5106b(context, b.m5086c()) == uriArr.length) {
                intent2 = b.m5085b() ? new Intent(context, GroupConversationActivity.class) : new Intent(context, SingleConversationActivity.class);
                intent2.putExtra("party", b.m5086c());
                intent = intent2;
            } else {
                intent2 = new Intent(context, MainActivity.class);
                intent2.putExtra("com.mmdt.sipclient.view.MainActivity.JOB_VALUE", 2);
                intent = intent2;
            }
            intent.setFlags(67108864);
            MessagesNotificationManager.m2386b(context, uriArr.length);
            if (uriArr.length == 1 || Messages.m5106b(context, b.m5086c()) == uriArr.length) {
                String str;
                String c = b.m5086c();
                if (b.m5085b()) {
                    GroupRow d = Groups.m5056d(context, c);
                    if (d != null) {
                        c = d.m5042b();
                        intent.putExtra("party_name", c);
                        str = c;
                    }
                    str = c;
                } else {
                    ContactShow a = SynaContacts.m5002a(context, c, false);
                    if (a != null) {
                        c = a.m5062c();
                        intent.putExtra("party_name", c);
                    }
                    str = c;
                }
                PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 134217728);
                if (uriArr.length != 1) {
                    notification = new Notification(2130838098, "New Instant Messages From : " + str, DateAndTime.m4085a(Long.parseLong(b.m5084a())));
                    notification.setLatestEventInfo(context, "New Instant Messages", "From : " + str, activity);
                } else if (b.m5088e() != -1) {
                    notification = new Notification(2130838098, "New File From : " + str, DateAndTime.m4085a(Long.parseLong(b.m5084a())));
                    notification.setLatestEventInfo(context, "New File Message", "From : " + str, activity);
                } else if (StickerManager.m4025a(new String(b.m5090g()))) {
                    notification = new Notification(2130838098, "New Instant Message From : " + str + " " + "(Sticker)", DateAndTime.m4085a(Long.parseLong(b.m5084a())));
                    notification.setLatestEventInfo(context, "New Instant Message", "From : " + str, activity);
                } else {
                    notification = new Notification(2130838098, "New Instant Message From : " + str + " " + new String(b.m5090g()), DateAndTime.m4085a(Long.parseLong(b.m5084a())));
                    notification.setLatestEventInfo(context, "New Instant Message", "From : " + str, activity);
                }
            } else {
                PendingIntent activity2 = PendingIntent.getActivity(context, 0, intent, 134217728);
                notification = new Notification(2130838098, "New Instant Messages are available.", System.currentTimeMillis());
                notification.setLatestEventInfo(context, uriArr.length + " New Instant Messages.", "", activity2);
            }
            notification.flags |= 16;
            f1588a.notify(1742346, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void m2388c(Context context, int i) {
        try {
            Intent intent = new Intent();
            intent.setAction("SYNA_MainActivity.IntentFilter");
            intent.putExtra("SYNA_MainActivity.UNREAD_MESSAGES_COUNT", i);
            context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
