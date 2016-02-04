package mobi.mmdt.ott.core.model.database.p061b;

/* renamed from: mobi.mmdt.ott.core.model.database.b.c */
public class SynaContactRow {
    private String f3918a;
    private String f3919b;
    private String f3920c;
    private String f3921d;
    private String f3922e;
    private String f3923f;
    private String f3924g;
    private Boolean f3925h;
    private String f3926i;
    private int f3927j;

    public SynaContactRow(String str, String str2, String str3, String str4, String str5, Boolean bool, String str6, String str7, String str8, int i) {
        this.f3923f = str7;
        this.f3927j = i;
        this.f3924g = str6;
        this.f3925h = bool;
        this.f3921d = str4;
        this.f3922e = str5;
        this.f3918a = str;
        this.f3920c = str3;
        this.f3919b = str2;
        this.f3926i = str8;
    }

    public int m4982a() {
        return this.f3927j;
    }

    public String m4983b() {
        return this.f3926i;
    }

    public String m4984c() {
        return this.f3923f;
    }

    public String m4985d() {
        return this.f3924g;
    }

    public Boolean m4986e() {
        return this.f3925h;
    }

    public String m4987f() {
        return this.f3921d;
    }

    public String m4988g() {
        return this.f3919b;
    }

    public String m4989h() {
        return this.f3918a;
    }

    public String m4990i() {
        return this.f3920c;
    }
}
