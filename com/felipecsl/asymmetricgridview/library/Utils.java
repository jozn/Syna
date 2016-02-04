package com.felipecsl.asymmetricgridview.library;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/* renamed from: com.felipecsl.asymmetricgridview.library.h */
public class Utils {
    public static int m2311a(Context context) {
        return context == null ? 0 : Utils.m2313b(context).widthPixels;
    }

    public static int m2312a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static DisplayMetrics m2313b(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
