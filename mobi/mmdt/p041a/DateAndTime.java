package mobi.mmdt.p041a;

import android.annotation.TargetApi;
import android.content.Context;
import android.provider.Settings.Global;
import android.provider.Settings.System;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* renamed from: mobi.mmdt.a.b */
public class DateAndTime {
    public static long m4084a() {
        return Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis();
    }

    public static long m4085a(long j) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        gregorianCalendar.setTimeInMillis(j);
        gregorianCalendar.setTimeZone(TimeZone.getDefault());
        return gregorianCalendar.getTimeInMillis();
    }

    public static boolean m4086a(Context context) {
        return HasVersion.m4101d() ? DateAndTime.m4088c(context) : DateAndTime.m4089d(context);
    }

    public static boolean m4087b(Context context) {
        return HasVersion.m4101d() ? DateAndTime.m4090e(context) : DateAndTime.m4091f(context);
    }

    @TargetApi(17)
    private static boolean m4088c(Context context) {
        return Global.getInt(context.getContentResolver(), "auto_time", 0) == 1;
    }

    private static boolean m4089d(Context context) {
        return System.getInt(context.getContentResolver(), "auto_time", 0) == 1;
    }

    @TargetApi(17)
    private static boolean m4090e(Context context) {
        return Global.getInt(context.getContentResolver(), "auto_time_zone", 0) == 1;
    }

    private static boolean m4091f(Context context) {
        return System.getInt(context.getContentResolver(), "auto_time_zone", 0) == 1;
    }
}
