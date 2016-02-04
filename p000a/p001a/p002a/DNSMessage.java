package p000a.p001a.p002a;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;
import org.linphone.core.Privacy;

/* renamed from: a.a.a.c */
public class DNSMessage {
    protected int f34a;
    protected DNSMessage f35b;
    protected DNSMessage f36c;
    protected boolean f37d;
    protected boolean f38e;
    protected boolean f39f;
    protected boolean f40g;
    protected boolean f41h;
    protected boolean f42i;
    protected boolean f43j;
    protected Question[] f44k;
    protected Record[] f45l;
    protected Record[] f46m;
    protected Record[] f47n;
    protected long f48o;

    /* renamed from: a.a.a.c.a */
    public enum DNSMessage {
        QUERY(0),
        INVERSE_QUERY(1),
        STATUS(2),
        NOTIFY(4),
        UPDATE(5);
        
        private static final DNSMessage[] f17f;
        private final byte f19g;

        static {
            f17f = new DNSMessage[]{QUERY, INVERSE_QUERY, STATUS, null, NOTIFY, UPDATE, null, null, null, null, null, null, null, null, null};
        }

        private DNSMessage(int i) {
            this.f19g = (byte) i;
        }

        public static DNSMessage m19a(int i) {
            if (i >= 0 && i <= 15) {
                return f17f[i];
            }
            throw new IllegalArgumentException();
        }

        public byte m20a() {
            return this.f19g;
        }
    }

    /* renamed from: a.a.a.c.b */
    public enum DNSMessage {
        NO_ERROR(0),
        FORMAT_ERR(1),
        SERVER_FAIL(2),
        NX_DOMAIN(3),
        NO_IMP(4),
        REFUSED(5),
        YXDOMAIN(6),
        YXRRSET(7),
        NXRRSET(8),
        NOT_AUTH(9),
        NOT_ZONE(10);
        
        private static final DNSMessage[] f31l;
        private final byte f33m;

        static {
            f31l = new DNSMessage[]{NO_ERROR, FORMAT_ERR, SERVER_FAIL, NX_DOMAIN, NO_IMP, REFUSED, YXDOMAIN, YXRRSET, NXRRSET, NOT_AUTH, NOT_ZONE, null, null, null, null, null};
        }

        private DNSMessage(int i) {
            this.f33m = (byte) i;
        }

        public static DNSMessage m21a(int i) {
            if (i >= 0 && i <= 15) {
                return f31l[i];
            }
            throw new IllegalArgumentException();
        }

        public byte m22a() {
            return this.f33m;
        }
    }

    public static DNSMessage m23a(byte[] bArr) throws IOException {
        boolean z = true;
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        DNSMessage dNSMessage = new DNSMessage();
        dNSMessage.f34a = dataInputStream.readUnsignedShort();
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        dNSMessage.f37d = ((readUnsignedShort >> 15) & 1) == 0;
        dNSMessage.f35b = DNSMessage.m19a((readUnsignedShort >> 11) & 15);
        dNSMessage.f38e = ((readUnsignedShort >> 10) & 1) == 1;
        dNSMessage.f39f = ((readUnsignedShort >> 9) & 1) == 1;
        dNSMessage.f40g = ((readUnsignedShort >> 8) & 1) == 1;
        dNSMessage.f41h = ((readUnsignedShort >> 7) & 1) == 1;
        dNSMessage.f42i = ((readUnsignedShort >> 5) & 1) == 1;
        if (((readUnsignedShort >> 4) & 1) != 1) {
            z = false;
        }
        dNSMessage.f43j = z;
        dNSMessage.f36c = DNSMessage.m21a(readUnsignedShort & 15);
        dNSMessage.f48o = System.currentTimeMillis();
        readUnsignedShort = dataInputStream.readUnsignedShort();
        int readUnsignedShort2 = dataInputStream.readUnsignedShort();
        int readUnsignedShort3 = dataInputStream.readUnsignedShort();
        int readUnsignedShort4 = dataInputStream.readUnsignedShort();
        dNSMessage.f44k = new Question[readUnsignedShort];
        while (true) {
            int i = readUnsignedShort - 1;
            if (readUnsignedShort <= 0) {
                break;
            }
            dNSMessage.f44k[i] = Question.m31a(dataInputStream, bArr);
            readUnsignedShort = i;
        }
        dNSMessage.f45l = new Record[readUnsignedShort2];
        while (true) {
            readUnsignedShort = readUnsignedShort2 - 1;
            if (readUnsignedShort2 <= 0) {
                break;
            }
            Record record = new Record();
            record.m41a(dataInputStream, bArr);
            dNSMessage.f45l[readUnsignedShort] = record;
            readUnsignedShort2 = readUnsignedShort;
        }
        dNSMessage.f46m = new Record[readUnsignedShort3];
        while (true) {
            readUnsignedShort2 = readUnsignedShort3 - 1;
            if (readUnsignedShort3 <= 0) {
                break;
            }
            Record record2 = new Record();
            record2.m41a(dataInputStream, bArr);
            dNSMessage.f46m[readUnsignedShort2] = record2;
            readUnsignedShort3 = readUnsignedShort2;
        }
        dNSMessage.f47n = new Record[readUnsignedShort4];
        while (true) {
            readUnsignedShort3 = readUnsignedShort4 - 1;
            if (readUnsignedShort4 <= 0) {
                return dNSMessage;
            }
            Record record3 = new Record();
            record3.m41a(dataInputStream, bArr);
            dNSMessage.f47n[readUnsignedShort3] = record3;
            readUnsignedShort4 = readUnsignedShort3;
        }
    }

    public int m24a() {
        return this.f34a;
    }

    public void m25a(int i) {
        this.f34a = InBandBytestreamManager.MAXIMUM_BLOCK_SIZE & i;
    }

    public void m26a(boolean z) {
        this.f40g = z;
    }

    public void m27a(Question... questionArr) {
        this.f44k = questionArr;
    }

    public byte[] m28b() throws IOException {
        int i = 0;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        int i2 = this.f37d ? Privacy.DEFAULT : 0;
        if (this.f35b != null) {
            i2 += this.f35b.m20a() << 11;
        }
        if (this.f38e) {
            i2 += 1024;
        }
        if (this.f39f) {
            i2 += 512;
        }
        if (this.f40g) {
            i2 += 256;
        }
        if (this.f41h) {
            i2 += 128;
        }
        if (this.f42i) {
            i2 += 32;
        }
        if (this.f43j) {
            i2 += 16;
        }
        if (this.f36c != null) {
            i2 += this.f36c.m22a();
        }
        dataOutputStream.writeShort((short) this.f34a);
        dataOutputStream.writeShort((short) i2);
        if (this.f44k == null) {
            dataOutputStream.writeShort(0);
        } else {
            dataOutputStream.writeShort((short) this.f44k.length);
        }
        if (this.f45l == null) {
            dataOutputStream.writeShort(0);
        } else {
            dataOutputStream.writeShort((short) this.f45l.length);
        }
        if (this.f46m == null) {
            dataOutputStream.writeShort(0);
        } else {
            dataOutputStream.writeShort((short) this.f46m.length);
        }
        if (this.f47n == null) {
            dataOutputStream.writeShort(0);
        } else {
            dataOutputStream.writeShort((short) this.f47n.length);
        }
        Question[] questionArr = this.f44k;
        int length = questionArr.length;
        while (i < length) {
            dataOutputStream.write(questionArr[i].m35d());
            i++;
        }
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public DNSMessage m29c() {
        return this.f36c;
    }

    public Record[] m30d() {
        return this.f45l;
    }

    public String toString() {
        return "-- DNSMessage " + this.f34a + " --\n" + Arrays.toString(this.f45l);
    }
}
