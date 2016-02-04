package android.support.v4.app;

import android.app.Activity;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;

/* renamed from: android.support.v4.app.a */
public class ActivityCompat extends ContextCompat {
    public static void m133a(Activity activity) {
        if (VERSION.SDK_INT >= 16) {
            ActivityCompatJB.m194a(activity);
        } else {
            activity.finish();
        }
    }
}
