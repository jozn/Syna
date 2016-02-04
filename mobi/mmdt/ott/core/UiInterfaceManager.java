package mobi.mmdt.ott.core;

/* renamed from: mobi.mmdt.ott.core.c */
public class UiInterfaceManager {
    private static UiInterfaceManager f3534a;
    private UiInterface f3535b;

    public static UiInterfaceManager m4491a() {
        if (f3534a == null) {
            f3534a = new UiInterfaceManager();
        }
        return f3534a;
    }

    public void m4492a(UiInterface uiInterface) {
        this.f3535b = uiInterface;
    }

    public UiInterface m4493b() {
        return this.f3535b;
    }
}
