package org.p039a.p071d;

import java.nio.ByteBuffer;
import org.p039a.p070c.InvalidDataException;
import org.p039a.p070c.InvalidFrameException;
import org.p039a.p071d.Framedata.Framedata;
import org.p039a.p073f.Charsetfunctions;

/* renamed from: org.a.d.b */
public class CloseFrameBuilder extends FramedataImpl1 implements CloseFrame {
    static final ByteBuffer f4070a;
    private int f4071f;
    private String f4072g;

    static {
        f4070a = ByteBuffer.allocate(0);
    }

    public CloseFrameBuilder() {
        super(Framedata.CLOSING);
        m5264a(true);
    }

    public CloseFrameBuilder(int i) throws InvalidDataException {
        super(Framedata.CLOSING);
        m5264a(true);
        m5270a(i, "");
    }

    public CloseFrameBuilder(int i, String str) throws InvalidDataException {
        super(Framedata.CLOSING);
        m5264a(true);
        m5270a(i, str);
    }

    private void m5270a(int i, String str) throws InvalidDataException {
        String str2 = str == null ? "" : str;
        if (i == 1015) {
            str2 = "";
            i = 1005;
        }
        if (i != 1005) {
            byte[] a = Charsetfunctions.m5315a(str2);
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.putInt(i);
            allocate.position(2);
            ByteBuffer allocate2 = ByteBuffer.allocate(a.length + 2);
            allocate2.put(allocate);
            allocate2.put(a);
            allocate2.rewind();
            m5274a(allocate2);
        } else if (str2.length() > 0) {
            throw new InvalidDataException(1002, "A close frame must have a closecode if it has a reason");
        }
    }

    private void m5271g() throws InvalidFrameException {
        this.f4071f = 1005;
        ByteBuffer c = super.m5266c();
        c.mark();
        if (c.remaining() >= 2) {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.position(2);
            allocate.putShort(c.getShort());
            allocate.position(0);
            this.f4071f = allocate.getInt();
            if (this.f4071f == 1006 || this.f4071f == 1015 || this.f4071f == 1005 || this.f4071f > 4999 || this.f4071f < 1000 || this.f4071f == 1004) {
                throw new InvalidFrameException("closecode must not be sent over the wire: " + this.f4071f);
            }
        }
        c.reset();
    }

    private void m5272h() throws InvalidDataException {
        if (this.f4071f == 1005) {
            this.f4072g = Charsetfunctions.m5313a(super.m5266c());
            return;
        }
        ByteBuffer c = super.m5266c();
        int position = c.position();
        try {
            c.position(c.position() + 2);
            this.f4072g = Charsetfunctions.m5313a(c);
            c.position(position);
        } catch (Throwable e) {
            throw new InvalidFrameException(e);
        } catch (Throwable th) {
            c.position(position);
        }
    }

    public int m5273a() {
        return this.f4071f;
    }

    public void m5274a(ByteBuffer byteBuffer) throws InvalidDataException {
        super.m5262a(byteBuffer);
        m5271g();
        m5272h();
    }

    public String m5275b() {
        return this.f4072g;
    }

    public ByteBuffer m5276c() {
        return this.f4071f == 1005 ? f4070a : super.m5266c();
    }

    public String toString() {
        return super.toString() + "code: " + this.f4071f;
    }
}
