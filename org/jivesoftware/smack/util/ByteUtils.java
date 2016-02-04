package org.jivesoftware.smack.util;

/* renamed from: org.jivesoftware.smack.util.b */
public class ByteUtils {
    public static byte[] m5845a(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }
}
