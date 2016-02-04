package com.mmdt.syna.view.components.pulltorefresh.internal;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;

/* renamed from: com.mmdt.syna.view.components.pulltorefresh.internal.c */
public class ViewCompat {

    @TargetApi(16)
    /* renamed from: com.mmdt.syna.view.components.pulltorefresh.internal.c.a */
    static class ViewCompat {
        public static void m2918a(View view, Drawable drawable) {
            view.setBackground(drawable);
        }

        public static void m2919a(View view, Runnable runnable) {
            view.postOnAnimation(runnable);
        }
    }

    public static void m2920a(View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            ViewCompat.m2918a(view, drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void m2921a(View view, Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            ViewCompat.m2919a(view, runnable);
        } else {
            view.postDelayed(runnable, 16);
        }
    }
}
