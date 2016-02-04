package android.support.v4.view.p009a;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* renamed from: android.support.v4.view.a.k */
final class AccessibilityNodeProviderCompatKitKat extends AccessibilityNodeProvider {
    final /* synthetic */ AccessibilityNodeProviderCompatKitKat f545a;

    AccessibilityNodeProviderCompatKitKat(AccessibilityNodeProviderCompatKitKat accessibilityNodeProviderCompatKitKat) {
        this.f545a = accessibilityNodeProviderCompatKitKat;
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        return (AccessibilityNodeInfo) this.f545a.m870a(i);
    }

    public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
        return this.f545a.m871a(str, i);
    }

    public AccessibilityNodeInfo findFocus(int i) {
        return (AccessibilityNodeInfo) this.f545a.m873b(i);
    }

    public boolean performAction(int i, int i2, Bundle bundle) {
        return this.f545a.m872a(i, i2, bundle);
    }
}
