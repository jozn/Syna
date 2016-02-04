package mobi.mmdt.ott.p043b.p048c.p049a;

/* renamed from: mobi.mmdt.ott.b.c.a.g */
public class RateObject implements Comparable<RateObject> {
    private String f3445a;
    private String f3446b;
    private String f3447c;
    private String f3448d;

    public RateObject(String str, String str2, String str3, String str4) {
        this.f3445a = str;
        this.f3446b = str4;
        this.f3448d = str3;
        this.f3447c = str2;
    }

    public int m4354a(RateObject rateObject) {
        return rateObject.m4355a().length() - m4355a().length();
    }

    public String m4355a() {
        return this.f3445a;
    }

    public String m4356b() {
        return this.f3446b;
    }

    public String m4357c() {
        return this.f3448d;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m4354a((RateObject) obj);
    }
}
