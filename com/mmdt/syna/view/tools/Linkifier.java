package com.mmdt.syna.view.tools;

import android.text.util.Linkify;
import android.util.Patterns;
import android.widget.TextView;

/* renamed from: com.mmdt.syna.view.tools.j */
public class Linkifier {
    public static void m4027a(TextView textView) {
        Linkify.addLinks(textView, 1);
        Linkify.addLinks(textView, Patterns.PHONE, "tel:");
        Linkify.addLinks(textView, Patterns.EMAIL_ADDRESS, "mailto:");
    }
}
