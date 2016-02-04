package mobi.mmdt.ott.p043b.p050d;

import android.content.Context;

/* renamed from: mobi.mmdt.ott.b.d.a */
public class WebservicesTools {
    public static String m4392a(Context context) {
        switch (context.getResources().getDisplayMetrics().densityDpi) {
            case 120:
                return "ldpi";
            case 160:
                return "mdpi";
            case 240:
                return "hdpi";
            case 320:
                return "xhdpi";
            case 480:
                return "xxhdpi";
            case 640:
                return "xxxhdpi";
            default:
                return "hdpi";
        }
    }
}
