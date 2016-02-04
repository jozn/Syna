package com.mmdt.syna.p017a;

/* renamed from: com.mmdt.syna.a.a */
public class PrivateSetting {
    private static PrivateSetting f1601a;

    public static PrivateSetting m2403a() {
        if (f1601a == null) {
            f1601a = new PrivateSetting();
        }
        return f1601a;
    }

    public String m2404b() {
        return "https://ws.syna.name/util";
    }

    public String m2405c() {
        return "https://api.stickers.syna.name";
    }

    public String m2406d() {
        return "http://api.4.sunbird.name/a2billing/esn4";
    }

    public String m2407e() {
        return "http://api.4.sunbird.name/a2billing/esn4";
    }

    public String[] m2408f() {
        return new String[]{"http://ir.4.sunbird.name:3000", "http://de.4.sunbird.name:3000", "http://push-standby.4.sunbird.name:3000"};
    }

    public String m2409g() {
        return "sunbird.name";
    }
}
