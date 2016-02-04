package android.support.v4.view.p009a;

import android.os.Bundle;
import android.support.v4.view.p009a.AccessibilityNodeProviderCompatKitKat.AccessibilityNodeProviderCompatKitKat;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v4.view.a.g */
class AccessibilityNodeProviderCompat implements AccessibilityNodeProviderCompatKitKat {
    final /* synthetic */ AccessibilityNodeProviderCompat f542a;
    final /* synthetic */ AccessibilityNodeProviderCompat f543b;

    AccessibilityNodeProviderCompat(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat2) {
        this.f543b = accessibilityNodeProviderCompat;
        this.f542a = accessibilityNodeProviderCompat2;
    }

    public Object m874a(int i) {
        AccessibilityNodeInfoCompat a = this.f542a.m859a(i);
        return a == null ? null : a.m771a();
    }

    public List<Object> m875a(String str, int i) {
        List a = this.f542a.m861a(str, i);
        List<Object> arrayList = new ArrayList();
        int size = a.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(((AccessibilityNodeInfoCompat) a.get(i2)).m771a());
        }
        return arrayList;
    }

    public boolean m876a(int i, int i2, Bundle bundle) {
        return this.f542a.m862a(i, i2, bundle);
    }

    public Object m877b(int i) {
        AccessibilityNodeInfoCompat b = this.f542a.m863b(i);
        return b == null ? null : b.m771a();
    }
}
