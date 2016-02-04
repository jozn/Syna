package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.v4.content.IntentCompat;
import android.util.Log;

/* compiled from: NavUtils */
public class ab {
    private static final NavUtils f240a;

    /* renamed from: android.support.v4.app.ab.a */
    interface NavUtils {
        Intent m162a(Activity activity);

        String m163a(Context context, ActivityInfo activityInfo);

        boolean m164a(Activity activity, Intent intent);

        void m165b(Activity activity, Intent intent);
    }

    /* renamed from: android.support.v4.app.ab.b */
    static class NavUtils implements NavUtils {
        NavUtils() {
        }

        public Intent m166a(Activity activity) {
            String b = ab.m178b(activity);
            if (b == null) {
                return null;
            }
            ComponentName componentName = new ComponentName(activity, b);
            try {
                return ab.m179b((Context) activity, componentName) == null ? IntentCompat.m517a(componentName) : new Intent().setComponent(componentName);
            } catch (NameNotFoundException e) {
                Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + b + "' in manifest");
                return null;
            }
        }

        public String m167a(Context context, ActivityInfo activityInfo) {
            if (activityInfo.metaData == null) {
                return null;
            }
            String string = activityInfo.metaData.getString("android.support.PARENT_ACTIVITY");
            return string == null ? null : string.charAt(0) == '.' ? context.getPackageName() + string : string;
        }

        public boolean m168a(Activity activity, Intent intent) {
            String action = activity.getIntent().getAction();
            return (action == null || action.equals("android.intent.action.MAIN")) ? false : true;
        }

        public void m169b(Activity activity, Intent intent) {
            intent.addFlags(67108864);
            activity.startActivity(intent);
            activity.finish();
        }
    }

    /* renamed from: android.support.v4.app.ab.c */
    static class NavUtils extends NavUtils {
        NavUtils() {
        }

        public Intent m170a(Activity activity) {
            Intent a = ac.m181a(activity);
            return a == null ? m173b(activity) : a;
        }

        public String m171a(Context context, ActivityInfo activityInfo) {
            String a = ac.m182a(activityInfo);
            return a == null ? super.m167a(context, activityInfo) : a;
        }

        public boolean m172a(Activity activity, Intent intent) {
            return ac.m183a(activity, intent);
        }

        Intent m173b(Activity activity) {
            return super.m166a(activity);
        }

        public void m174b(Activity activity, Intent intent) {
            ac.m184b(activity, intent);
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            f240a = new NavUtils();
        } else {
            f240a = new NavUtils();
        }
    }

    public static Intent m175a(Activity activity) {
        return f240a.m162a(activity);
    }

    public static Intent m176a(Context context, ComponentName componentName) throws NameNotFoundException {
        String b = m179b(context, componentName);
        if (b == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), b);
        return m179b(context, componentName2) == null ? IntentCompat.m517a(componentName2) : new Intent().setComponent(componentName2);
    }

    public static boolean m177a(Activity activity, Intent intent) {
        return f240a.m164a(activity, intent);
    }

    public static String m178b(Activity activity) {
        try {
            return m179b((Context) activity, activity.getComponentName());
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String m179b(Context context, ComponentName componentName) throws NameNotFoundException {
        return f240a.m163a(context, context.getPackageManager().getActivityInfo(componentName, 128));
    }

    public static void m180b(Activity activity, Intent intent) {
        f240a.m165b(activity, intent);
    }
}
