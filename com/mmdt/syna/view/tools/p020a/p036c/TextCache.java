package com.mmdt.syna.view.tools.p020a.p036c;

import android.text.Spannable;
import android.util.LruCache;

/* renamed from: com.mmdt.syna.view.tools.a.c.b */
class TextCache extends LruCache<String, Spannable> {
    final /* synthetic */ TextCache f3141a;

    TextCache(TextCache textCache, int i) {
        this.f3141a = textCache;
        super(i);
    }

    protected int m3997a(String str, Spannable spannable) {
        int length = spannable.length();
        return length == 0 ? 1 : length;
    }

    protected /* synthetic */ int sizeOf(Object obj, Object obj2) {
        return m3997a((String) obj, (Spannable) obj2);
    }
}
