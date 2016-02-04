package mobi.mmdt.ott.core.logic.core.p056a;

/* renamed from: mobi.mmdt.ott.core.logic.core.a.a */
public abstract class BaseConfig {
    private String f3654a;
    private int f3655b;
    private String f3656c;
    private String f3657d;
    private int f3658e;

    public BaseConfig(String str, String str2, String str3, int i, int i2) {
        this.f3657d = str2;
        this.f3656c = str;
        this.f3658e = i2;
        this.f3654a = str3;
        this.f3655b = i;
    }

    public int m4674a() {
        return this.f3658e;
    }

    public String m4675b() {
        return this.f3657d;
    }

    public String m4676c() {
        return this.f3654a;
    }

    public int m4677d() {
        return this.f3655b;
    }

    public String m4678e() {
        return this.f3656c;
    }
}
