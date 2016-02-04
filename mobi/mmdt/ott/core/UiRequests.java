package mobi.mmdt.ott.core;

import android.accounts.AccountAuthenticatorResponse;
import android.content.Context;
import android.os.Bundle;

/* renamed from: mobi.mmdt.ott.core.d */
public class UiRequests {
    public static final Bundle m4494a(Context context, AccountAuthenticatorResponse accountAuthenticatorResponse) {
        return UiInterfaceManager.m4491a().m4493b() != null ? UiInterfaceManager.m4491a().m4493b().m2410a(context, accountAuthenticatorResponse) : null;
    }

    public static final String m4495a() {
        return UiInterfaceManager.m4491a().m4493b() != null ? UiInterfaceManager.m4491a().m4493b().m2411a() : null;
    }

    public static final String m4496a(Context context) {
        return UiInterfaceManager.m4491a().m4493b() != null ? UiInterfaceManager.m4491a().m4493b().m2420c(context) : null;
    }

    public static final void m4497a(Context context, String str) {
        if (UiInterfaceManager.m4491a().m4493b() != null) {
            UiInterfaceManager.m4491a().m4493b().m2413a(context, str);
        }
    }

    public static final void m4498a(Context context, String str, int i) {
        if (UiInterfaceManager.m4491a().m4493b() != null) {
            UiInterfaceManager.m4491a().m4493b().m2414a(context, str, i);
        }
    }

    public static final void m4499a(Context context, String str, boolean z) {
        try {
            if (UiInterfaceManager.m4491a().m4493b() != null) {
                UiInterfaceManager.m4491a().m4493b().m2415a(context, str, z);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final String m4500b() {
        return UiInterfaceManager.m4491a().m4493b() != null ? UiInterfaceManager.m4491a().m4493b().m2416b() : null;
    }

    public static final String m4501b(Context context) {
        return UiInterfaceManager.m4491a().m4493b() != null ? UiInterfaceManager.m4491a().m4493b().m2423d(context) : null;
    }

    public static final void m4502b(Context context, String str, boolean z) {
        if (UiInterfaceManager.m4491a().m4493b() != null) {
            UiInterfaceManager.m4491a().m4493b().m2421c(context, str, z);
        }
    }

    public static final String m4503c() {
        return UiInterfaceManager.m4491a().m4493b() != null ? UiInterfaceManager.m4491a().m4493b().m2419c() : null;
    }

    public static final void m4504c(Context context) {
        if (UiInterfaceManager.m4491a().m4493b() != null) {
            UiInterfaceManager.m4491a().m4493b().m2412a(context);
        }
    }

    public static final void m4505c(Context context, String str, boolean z) {
        if (UiInterfaceManager.m4491a().m4493b() != null) {
            UiInterfaceManager.m4491a().m4493b().m2418b(context, str, z);
        }
    }

    public static final String m4506d() {
        return UiInterfaceManager.m4491a().m4493b() != null ? UiInterfaceManager.m4491a().m4493b().m2422d() : null;
    }

    public static final void m4507d(Context context) {
        if (UiInterfaceManager.m4491a().m4493b() != null) {
            UiInterfaceManager.m4491a().m4493b().m2427f(context);
        }
    }

    public static final void m4508e(Context context) {
        if (UiInterfaceManager.m4491a().m4493b() != null) {
            UiInterfaceManager.m4491a().m4493b().m2424e(context);
        }
    }

    public static final String[] m4509e() {
        return UiInterfaceManager.m4491a().m4493b() != null ? UiInterfaceManager.m4491a().m4493b().m2425e() : null;
    }

    public static final String m4510f() {
        return UiInterfaceManager.m4491a().m4493b() != null ? UiInterfaceManager.m4491a().m4493b().m2426f() : null;
    }

    public static final void m4511f(Context context) {
        if (UiInterfaceManager.m4491a().m4493b() != null) {
            UiInterfaceManager.m4491a().m4493b().m2428g(context);
        }
    }

    public static final void m4512g(Context context) {
        if (UiInterfaceManager.m4491a().m4493b() != null) {
            UiInterfaceManager.m4491a().m4493b().m2417b(context);
        }
    }
}
