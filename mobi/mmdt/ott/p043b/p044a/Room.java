package mobi.mmdt.ott.p043b.p044a;

/* renamed from: mobi.mmdt.ott.b.a.e */
public class Room {
    private String f3402a;
    private String f3403b;
    private String f3404c;
    private String f3405d;
    private String f3406e;
    private String f3407f;
    private String f3408g;
    private String f3409h;
    private boolean f3410i;

    public Room(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i) {
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        this.f3410i = z;
        this.f3402a = str;
        this.f3403b = str2;
        this.f3404c = str3;
        this.f3405d = str4;
        this.f3406e = str5;
        this.f3407f = str6;
        this.f3408g = str7;
        this.f3409h = str8;
    }

    public String m4326a() {
        return this.f3404c;
    }

    public String m4327b() {
        return this.f3408g;
    }

    public String m4328c() {
        return this.f3402a;
    }

    public String m4329d() {
        return this.f3409h;
    }

    public String m4330e() {
        return this.f3403b;
    }

    public String m4331f() {
        return this.f3406e;
    }

    public String m4332g() {
        return this.f3405d;
    }

    public boolean m4333h() {
        return this.f3410i;
    }
}
