package mobi.mmdt.ott.core.model.p059b;

import java.util.HashMap;

/* renamed from: mobi.mmdt.ott.core.model.b.b */
public class RateObject {
    private String f3863a;
    private String f3864b;
    private HashMap<String, String> f3865c;

    public RateObject(String str, String str2, HashMap<String, String> hashMap) {
        this.f3863a = str;
        this.f3864b = str2;
        this.f3865c = hashMap;
    }

    public HashMap<String, String> m4919a() {
        return this.f3865c;
    }
}
