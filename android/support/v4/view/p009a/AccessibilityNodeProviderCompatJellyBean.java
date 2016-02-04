package android.support.v4.view.p009a;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* renamed from: android.support.v4.view.a.i */
final class AccessibilityNodeProviderCompatJellyBean extends AccessibilityNodeProvider {
    final /* synthetic */ AccessibilityNodeProviderCompatJellyBean f544a;

    AccessibilityNodeProviderCompatJellyBean(AccessibilityNodeProviderCompatJellyBean accessibilityNodeProviderCompatJellyBean) {
        this.f544a = accessibilityNodeProviderCompatJellyBean;
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        return (AccessibilityNodeInfo) this.f544a.m864a(i);
    }

    public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
        return this.f544a.m865a(str, i);
    }

    public boolean performAction(int i, int i2, Bundle bundle) {
        return this.f544a.m866a(i, i2, bundle);
    }
}
