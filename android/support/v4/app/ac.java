package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

/* compiled from: NavUtilsJB */
class ac {
    public static Intent m181a(Activity activity) {
        return activity.getParentActivityIntent();
    }

    public static String m182a(ActivityInfo activityInfo) {
        return activityInfo.parentActivityName;
    }

    public static boolean m183a(Activity activity, Intent intent) {
        return activity.shouldUpRecreateTask(intent);
    }

    public static void m184b(Activity activity, Intent intent) {
        activity.navigateUpTo(intent);
    }
}
