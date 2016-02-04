package com.devspark.robototextview.p013a;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import com.mmdt.syna.R.R;

/* renamed from: com.devspark.robototextview.a.a */
public class RobotoTextViewUtils {
    public static void m2214a(TextView textView, Context context, AttributeSet attributeSet) {
        Typeface a;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.RobotoTextView);
            a = obtainStyledAttributes.hasValue(0) ? RobotoTypefaceManager.m2217a(context, obtainStyledAttributes.getInt(0, 4)) : RobotoTypefaceManager.m2218a(context, obtainStyledAttributes.getInt(1, 0), obtainStyledAttributes.getInt(2, 0), obtainStyledAttributes.getInt(3, 0));
            obtainStyledAttributes.recycle();
        } else {
            a = RobotoTypefaceManager.m2217a(context, 4);
        }
        RobotoTextViewUtils.m2215a(textView, a);
    }

    public static void m2215a(TextView textView, Typeface typeface) {
        textView.setPaintFlags(textView.getPaintFlags() | 128);
        textView.setTypeface(typeface);
    }
}
