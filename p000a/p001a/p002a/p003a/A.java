package p000a.p001a.p002a.p003a;

import java.io.DataInputStream;
import java.io.IOException;

/* renamed from: a.a.a.a.a */
public class A implements Data {
    private byte[] f0a;

    public void m1a(DataInputStream dataInputStream, byte[] bArr, int i) throws IOException {
        this.f0a = new byte[4];
        dataInputStream.readFully(this.f0a);
    }

    public String toString() {
        return Integer.toString(this.f0a[0] & 255) + "." + Integer.toString(this.f0a[1] & 255) + "." + Integer.toString(this.f0a[2] & 255) + "." + Integer.toString(this.f0a[3] & 255);
    }
}
