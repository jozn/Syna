package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompatJellyBean.AccessibilityDelegateCompatJellyBean;
import android.support.v4.view.p009a.AccessibilityNodeInfoCompat;
import android.support.v4.view.p009a.AccessibilityNodeProviderCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* renamed from: android.support.v4.view.c */
class AccessibilityDelegateCompat implements AccessibilityDelegateCompatJellyBean {
    final /* synthetic */ AccessibilityDelegateCompat f553a;
    final /* synthetic */ AccessibilityDelegateCompat f554b;

    AccessibilityDelegateCompat(AccessibilityDelegateCompat accessibilityDelegateCompat, AccessibilityDelegateCompat accessibilityDelegateCompat2) {
        this.f554b = accessibilityDelegateCompat;
        this.f553a = accessibilityDelegateCompat2;
    }

    public Object m958a(View view) {
        AccessibilityNodeProviderCompat a = this.f553a.m539a(view);
        return a != null ? a.m860a() : null;
    }

    public void m959a(View view, int i) {
        this.f553a.m541a(view, i);
    }

    public void m960a(View view, Object obj) {
        this.f553a.m542a(view, new AccessibilityNodeInfoCompat(obj));
    }

    public boolean m961a(View view, int i, Bundle bundle) {
        return this.f553a.m544a(view, i, bundle);
    }

    public boolean m962a(View view, AccessibilityEvent accessibilityEvent) {
        return this.f553a.m546b(view, accessibilityEvent);
    }

    public boolean m963a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f553a.m545a(viewGroup, view, accessibilityEvent);
    }

    public void m964b(View view, AccessibilityEvent accessibilityEvent) {
        this.f553a.m548d(view, accessibilityEvent);
    }

    public void m965c(View view, AccessibilityEvent accessibilityEvent) {
        this.f553a.m547c(view, accessibilityEvent);
    }

    public void m966d(View view, AccessibilityEvent accessibilityEvent) {
        this.f553a.m543a(view, accessibilityEvent);
    }
}
