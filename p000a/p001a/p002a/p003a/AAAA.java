package p000a.p001a.p002a.p003a;

import java.io.DataInputStream;
import java.io.IOException;

/* renamed from: a.a.a.a.b */
public class AAAA implements Data {
    private byte[] f1a;

    public void m2a(DataInputStream dataInputStream, byte[] bArr, int i) throws IOException {
        this.f1a = new byte[16];
        dataInputStream.readFully(this.f1a);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.f1a.length; i += 2) {
            if (i != 0) {
                stringBuilder.append(':');
            }
            stringBuilder.append(Integer.toHexString(((this.f1a[i] & 255) << 8) + (this.f1a[i + 1] & 255)));
        }
        return stringBuilder.toString();
    }
}
