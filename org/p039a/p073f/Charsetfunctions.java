package org.p039a.p073f;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import org.apache.http.protocol.HTTP;
import org.p039a.p070c.InvalidDataException;

/* renamed from: org.a.f.b */
public class Charsetfunctions {
    public static CodingErrorAction f4102a;

    static {
        f4102a = CodingErrorAction.REPORT;
    }

    public static String m5313a(ByteBuffer byteBuffer) throws InvalidDataException {
        CharsetDecoder newDecoder = Charset.forName("UTF8").newDecoder();
        newDecoder.onMalformedInput(f4102a);
        newDecoder.onUnmappableCharacter(f4102a);
        try {
            byteBuffer.mark();
            String charBuffer = newDecoder.decode(byteBuffer).toString();
            byteBuffer.reset();
            return charBuffer;
        } catch (Throwable e) {
            throw new InvalidDataException(1007, e);
        }
    }

    public static String m5314a(byte[] bArr, int i, int i2) {
        try {
            return new String(bArr, i, i2, HTTP.ASCII);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] m5315a(String str) {
        try {
            return str.getBytes("UTF8");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] m5316b(String str) {
        try {
            return str.getBytes(HTTP.ASCII);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
