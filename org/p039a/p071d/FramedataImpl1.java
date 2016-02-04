package org.p039a.p071d;

import java.nio.ByteBuffer;
import java.util.Arrays;
import org.p039a.p070c.InvalidDataException;
import org.p039a.p071d.Framedata.Framedata;
import org.p039a.p073f.Charsetfunctions;

/* renamed from: org.a.d.e */
public class FramedataImpl1 implements FrameBuilder {
    protected static byte[] f4065b;
    private ByteBuffer f4066a;
    protected boolean f4067c;
    protected Framedata f4068d;
    protected boolean f4069e;

    static {
        f4065b = new byte[0];
    }

    public FramedataImpl1(Framedata framedata) {
        this.f4068d = framedata;
        this.f4066a = ByteBuffer.wrap(f4065b);
    }

    public FramedataImpl1(Framedata framedata) {
        this.f4067c = framedata.m5253d();
        this.f4068d = framedata.m5255f();
        this.f4066a = framedata.m5252c();
        this.f4069e = framedata.m5254e();
    }

    public void m5262a(ByteBuffer byteBuffer) throws InvalidDataException {
        this.f4066a = byteBuffer;
    }

    public void m5263a(Framedata framedata) {
        this.f4068d = framedata;
    }

    public void m5264a(boolean z) {
        this.f4067c = z;
    }

    public void m5265b(boolean z) {
        this.f4069e = z;
    }

    public ByteBuffer m5266c() {
        return this.f4066a;
    }

    public boolean m5267d() {
        return this.f4067c;
    }

    public boolean m5268e() {
        return this.f4069e;
    }

    public Framedata m5269f() {
        return this.f4068d;
    }

    public String toString() {
        return "Framedata{ optcode:" + m5269f() + ", fin:" + m5267d() + ", payloadlength:" + this.f4066a.limit() + ", payload:" + Arrays.toString(Charsetfunctions.m5315a(new String(this.f4066a.array()))) + "}";
    }
}
