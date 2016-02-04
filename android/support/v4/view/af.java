package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ViewConfiguration;

/* compiled from: ViewConfigurationCompat */
public class af {
    static final ViewConfigurationCompat f548a;

    /* renamed from: android.support.v4.view.af.c */
    interface ViewConfigurationCompat {
        int m920a(ViewConfiguration viewConfiguration);
    }

    /* renamed from: android.support.v4.view.af.a */
    static class ViewConfigurationCompat implements ViewConfigurationCompat {
        ViewConfigurationCompat() {
        }

        public int m921a(ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledTouchSlop();
        }
    }

    /* renamed from: android.support.v4.view.af.b */
    static class ViewConfigurationCompat implements ViewConfigurationCompat {
        ViewConfigurationCompat() {
        }

        public int m922a(ViewConfiguration viewConfiguration) {
            return ag.m924a(viewConfiguration);
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            f548a = new ViewConfigurationCompat();
        } else {
            f548a = new ViewConfigurationCompat();
        }
    }

    public static int m923a(ViewConfiguration viewConfiguration) {
        return f548a.m920a(viewConfiguration);
    }
}
