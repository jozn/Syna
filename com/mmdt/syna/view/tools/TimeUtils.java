package com.mmdt.syna.view.tools;

import android.content.Context;
import java.util.Calendar;
import org.linphone.C0282R;

/* renamed from: com.mmdt.syna.view.tools.k */
public class TimeUtils {
    public static String m4028a(Context context, long j) {
        String str = "";
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        instance.set(11, 23);
        instance.set(12, 59);
        instance.set(13, 59);
        instance.set(14, 999);
        long timeInMillis2 = instance.getTimeInMillis();
        if (j < timeInMillis || j > timeInMillis2) {
            timeInMillis2 -= 86400000;
            if (j < timeInMillis - 86400000 || j > timeInMillis2) {
                instance.setTimeInMillis(j);
                return instance.get(5) + "/" + (instance.get(2) + 1) + "/" + instance.get(1) + "   " + instance.get(11) + ":" + (instance.get(12) < 10 ? "0" + instance.get(12) : new StringBuilder(String.valueOf(instance.get(12))).toString());
            }
            instance.setTimeInMillis(j);
            return context.getString(C0282R.string.yesterday) + "   " + instance.get(11) + ":" + (instance.get(12) < 10 ? "0" + instance.get(12) : new StringBuilder(String.valueOf(instance.get(12))).toString());
        }
        instance.setTimeInMillis(j);
        return instance.get(11) + ":" + (instance.get(12) < 10 ? "0" + instance.get(12) : new StringBuilder(String.valueOf(instance.get(12))).toString());
    }
}
