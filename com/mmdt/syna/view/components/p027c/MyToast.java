package com.mmdt.syna.view.components.p027c;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.devspark.robototextview.p013a.RobotoTextViewUtils;
import com.devspark.robototextview.p013a.RobotoTypefaceManager;
import com.devspark.robototextview.widget.RobotoTextView;

/* renamed from: com.mmdt.syna.view.components.c.a */
public class MyToast {
    public static synchronized void m2755a(Context context, String str, int i) {
        synchronized (MyToast.class) {
            Toast toast = new Toast(context);
            View robotoTextView = new RobotoTextView(context);
            RobotoTextViewUtils.m2215a(robotoTextView, RobotoTypefaceManager.m2217a(context, 4));
            robotoTextView.setText(str);
            robotoTextView.setTextSize(16.0f);
            toast.setView(robotoTextView);
            robotoTextView.setBackgroundResource(2130838429);
            robotoTextView.setTextColor(-1);
            toast.setDuration(i);
            toast.show();
        }
    }
}
