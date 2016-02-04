package mobi.mmdt.ott.core.model.database.p065f;

import java.io.Serializable;

/* renamed from: mobi.mmdt.ott.core.model.database.f.a */
public class MessageRow implements Serializable {
    private long f3970a;
    private String f3971b;
    private long f3972c;
    private int f3973d;
    private int f3974e;
    private byte[] f3975f;
    private int f3976g;
    private String f3977h;
    private int f3978i;
    private boolean f3979j;
    private String f3980k;
    private String f3981l;

    public MessageRow(long j, String str, String str2, String str3, long j2, int i, int i2, byte[] bArr, int i3, int i4, boolean z, String str4) {
        this.f3979j = false;
        this.f3970a = j;
        this.f3978i = i4;
        this.f3976g = i3;
        this.f3977h = str;
        this.f3971b = str2;
        this.f3972c = j2;
        this.f3973d = i;
        this.f3974e = i2;
        this.f3975f = bArr;
        this.f3979j = z;
        this.f3980k = str3;
        this.f3981l = str4;
    }

    public String m5084a() {
        return this.f3981l;
    }

    public boolean m5085b() {
        return this.f3979j;
    }

    public String m5086c() {
        return this.f3980k;
    }

    public int m5087d() {
        return this.f3978i;
    }

    public int m5088e() {
        return this.f3976g;
    }

    public String m5089f() {
        return this.f3977h;
    }

    public byte[] m5090g() {
        return this.f3975f;
    }

    public long m5091h() {
        return this.f3970a;
    }

    public int m5092i() {
        return this.f3974e;
    }

    public int m5093j() {
        return this.f3973d;
    }
}
