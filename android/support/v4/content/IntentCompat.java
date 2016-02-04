package android.support.v4.content;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build.VERSION;

/* renamed from: android.support.v4.content.f */
public class IntentCompat {
    private static final IntentCompat f420a;

    /* renamed from: android.support.v4.content.f.a */
    interface IntentCompat {
        Intent m514a(ComponentName componentName);
    }

    /* renamed from: android.support.v4.content.f.b */
    static class IntentCompat implements IntentCompat {
        IntentCompat() {
        }

        public Intent m515a(ComponentName componentName) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(componentName);
            intent.addCategory("android.intent.category.LAUNCHER");
            return intent;
        }
    }

    /* renamed from: android.support.v4.content.f.c */
    static class IntentCompat extends IntentCompat {
        IntentCompat() {
        }

        public Intent m516a(ComponentName componentName) {
            return IntentCompatHoneycomb.m518a(componentName);
        }
    }

    /* renamed from: android.support.v4.content.f.d */
    static class IntentCompat extends IntentCompat {
        IntentCompat() {
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 15) {
            f420a = new IntentCompat();
        } else if (i >= 11) {
            f420a = new IntentCompat();
        } else {
            f420a = new IntentCompat();
        }
    }

    public static Intent m517a(ComponentName componentName) {
        return f420a.m514a(componentName);
    }
}
