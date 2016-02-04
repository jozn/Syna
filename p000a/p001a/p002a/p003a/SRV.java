package p000a.p001a.p002a.p003a;

import java.io.DataInputStream;
import java.io.IOException;
import p000a.p001a.p002a.p004b.NameUtil;

/* renamed from: a.a.a.a.f */
public class SRV implements Data {
    protected int f3a;
    protected int f4b;
    protected int f5c;
    protected String f6d;

    public int m4a() {
        return this.f3a;
    }

    public void m5a(DataInputStream dataInputStream, byte[] bArr, int i) throws IOException {
        this.f3a = dataInputStream.readUnsignedShort();
        this.f4b = dataInputStream.readUnsignedShort();
        this.f5c = dataInputStream.readUnsignedShort();
        this.f6d = NameUtil.m16a(dataInputStream, bArr);
    }

    public int m6b() {
        return this.f4b;
    }

    public int m7c() {
        return this.f5c;
    }

    public String m8d() {
        return this.f6d;
    }

    public String toString() {
        return "SRV " + this.f6d + ":" + this.f5c + " p:" + this.f3a + " w:" + this.f4b;
    }
}
