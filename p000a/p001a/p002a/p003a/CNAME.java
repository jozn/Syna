package p000a.p001a.p002a.p003a;

import java.io.DataInputStream;
import java.io.IOException;
import p000a.p001a.p002a.p004b.NameUtil;

/* renamed from: a.a.a.a.c */
public class CNAME implements Data {
    protected String f2a;

    public void m3a(DataInputStream dataInputStream, byte[] bArr, int i) throws IOException {
        this.f2a = NameUtil.m16a(dataInputStream, bArr);
    }

    public String toString() {
        return "to \"" + this.f2a + "\"";
    }
}
