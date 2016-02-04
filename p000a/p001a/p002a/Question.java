package p000a.p001a.p002a;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import p000a.p001a.p002a.Record.Record;
import p000a.p001a.p002a.p004b.NameUtil;

/* renamed from: a.a.a.d */
public class Question {
    private final String f49a;
    private final Record f50b;
    private final Record f51c;
    private byte[] f52d;

    public Question(String str, Record record, Record record2) {
        this.f49a = str;
        this.f50b = record;
        this.f51c = record2;
    }

    public static Question m31a(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        return new Question(NameUtil.m16a(dataInputStream, bArr), Record.m38a(dataInputStream.readUnsignedShort()), Record.m36a(dataInputStream.readUnsignedShort()));
    }

    public Record m32a() {
        return this.f50b;
    }

    public Record m33b() {
        return this.f51c;
    }

    public String m34c() {
        return this.f49a;
    }

    public byte[] m35d() {
        if (this.f52d == null) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.write(NameUtil.m18a(this.f49a));
                dataOutputStream.writeShort(this.f50b.m39a());
                dataOutputStream.writeShort(this.f51c.m37a());
                dataOutputStream.flush();
                this.f52d = byteArrayOutputStream.toByteArray();
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
        return this.f52d;
    }

    public boolean equals(Object obj) {
        return this == obj ? true : !(obj instanceof Question) ? false : Arrays.equals(m35d(), ((Question) obj).m35d());
    }

    public int hashCode() {
        return Arrays.hashCode(m35d());
    }
}
