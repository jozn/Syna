package mobi.mmdt.ott.core.model.database.p065f;

/* renamed from: mobi.mmdt.ott.core.model.database.f.d */
public class TextMessageDataHolder {
    private int f3982a;
    private long f3983b;
    private byte[] f3984c;

    public TextMessageDataHolder(int i, long j, byte[] bArr) {
        this.f3982a = i;
        this.f3983b = j;
        this.f3984c = bArr;
    }

    public byte[] m5123a() {
        return this.f3984c;
    }

    public int m5124b() {
        return this.f3982a;
    }

    public long m5125c() {
        return this.f3983b;
    }
}
