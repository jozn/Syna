package p000a.p001a.p002a;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.HttpStatus;
import org.linphone.core.Privacy;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;
import p000a.p001a.p002a.p003a.A;
import p000a.p001a.p002a.p003a.AAAA;
import p000a.p001a.p002a.p003a.CNAME;
import p000a.p001a.p002a.p003a.Data;
import p000a.p001a.p002a.p003a.NS;
import p000a.p001a.p002a.p003a.SRV;
import p000a.p001a.p002a.p004b.NameUtil;

/* renamed from: a.a.a.e */
public class Record {
    protected String f114a;
    protected Record f115b;
    protected Record f116c;
    protected long f117d;
    protected Data f118e;

    /* renamed from: a.a.a.e.1 */
    static /* synthetic */ class Record {
        static final /* synthetic */ int[] f53a;

        static {
            f53a = new int[Record.values().length];
            try {
                f53a[Record.SRV.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f53a[Record.AAAA.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f53a[Record.A.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f53a[Record.NS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f53a[Record.CNAME.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    /* renamed from: a.a.a.e.a */
    public enum Record {
        IN(1),
        CH(3),
        HS(4),
        NONE(254),
        ANY(255);
        
        private static final HashMap<Integer, Record> f59f;
        private final int f61g;

        static {
            f59f = new HashMap();
            Record[] values = Record.values();
            int length = values.length;
            int i;
            while (i < length) {
                Record record = values[i];
                f59f.put(Integer.valueOf(record.m37a()), record);
                i++;
            }
        }

        private Record(int i) {
            this.f61g = i;
        }

        public static Record m36a(int i) {
            return (Record) f59f.get(Integer.valueOf(i));
        }

        public int m37a() {
            return this.f61g;
        }
    }

    /* renamed from: a.a.a.e.b */
    public enum Record {
        A(1),
        NS(2),
        MD(3),
        MF(4),
        CNAME(5),
        SOA(6),
        MB(7),
        MG(8),
        MR(9),
        NULL(10),
        WKS(11),
        PTR(12),
        HINFO(13),
        MINFO(14),
        MX(15),
        TXT(16),
        RP(17),
        AFSDB(18),
        X25(19),
        ISDN(20),
        RT(21),
        NSAP(22),
        NSAP_PTR(23),
        SIG(24),
        KEY(25),
        PX(26),
        GPOS(27),
        AAAA(28),
        LOC(29),
        NXT(30),
        EID(31),
        NIMLOC(32),
        SRV(33),
        ATMA(34),
        NAPTR(35),
        KX(36),
        CERT(37),
        A6(38),
        DNAME(39),
        SINK(40),
        OPT(41),
        APL(42),
        DS(43),
        SSHFP(44),
        IPSECKEY(45),
        RRSIG(46),
        NSEC(47),
        DNSKEY(48),
        DHCID(49),
        NSEC3(50),
        NSEC3PARAM(51),
        HIP(55),
        NINFO(56),
        RKEY(57),
        TALINK(58),
        SPF(99),
        UINFO(100),
        UID(HttpStatus.SC_SWITCHING_PROTOCOLS),
        GID(HttpStatus.SC_PROCESSING),
        TKEY(249),
        TSIG(250),
        IXFR(251),
        AXFR(252),
        MAILB(253),
        MAILA(254),
        ANY(255),
        TA(Privacy.DEFAULT),
        DLV(32769);
        
        private static final HashMap<Integer, Record> ar;
        private final int aq;

        static {
            ar = new HashMap();
            Record[] values = Record.values();
            int length = values.length;
            int i;
            while (i < length) {
                Record record = values[i];
                ar.put(Integer.valueOf(record.m39a()), record);
                i++;
            }
        }

        private Record(int i) {
            this.aq = i;
        }

        public static Record m38a(int i) {
            return (Record) ar.get(Integer.valueOf(i));
        }

        public int m39a() {
            return this.aq;
        }
    }

    public Data m40a() {
        return this.f118e;
    }

    public void m41a(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        this.f114a = NameUtil.m16a(dataInputStream, bArr);
        this.f115b = Record.m38a(dataInputStream.readUnsignedShort());
        this.f116c = Record.m36a(dataInputStream.readUnsignedShort());
        this.f117d = (((long) dataInputStream.readUnsignedShort()) << 32) + ((long) dataInputStream.readUnsignedShort());
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        switch (Record.f53a[this.f115b.ordinal()]) {
            case VideoSize.CIF /*1*/:
                this.f118e = new SRV();
                break;
            case VideoSize.HVGA /*2*/:
                this.f118e = new AAAA();
                break;
            case Version.API03_CUPCAKE_15 /*3*/:
                this.f118e = new A();
                break;
            case Version.API04_DONUT_16 /*4*/:
                this.f118e = new NS();
                break;
            case Version.API05_ECLAIR_20 /*5*/:
                this.f118e = new CNAME();
                break;
            default:
                System.out.println("Unparsed type " + this.f115b);
                this.f118e = null;
                for (int i = 0; i < readUnsignedShort; i++) {
                    dataInputStream.readByte();
                }
                break;
        }
        if (this.f118e != null) {
            this.f118e.m0a(dataInputStream, bArr, readUnsignedShort);
        }
    }

    public boolean m42a(Question question) {
        return (question.m32a() == this.f115b || question.m32a() == Record.ANY) && ((question.m33b() == this.f116c || question.m33b() == Record.ANY) && question.m34c().equals(this.f114a));
    }

    public long m43b() {
        return this.f117d;
    }

    public String toString() {
        return this.f118e == null ? "RR " + this.f115b + "/" + this.f116c : "RR " + this.f115b + "/" + this.f116c + ": " + this.f118e.toString();
    }
}
