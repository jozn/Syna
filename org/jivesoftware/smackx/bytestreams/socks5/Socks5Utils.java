package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.DataInputStream;
import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.util.SHA1;

/* renamed from: org.jivesoftware.smackx.bytestreams.socks5.b */
class Socks5Utils {
    public static String m5864a(String str, String str2, String str3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str).append(str2).append(str3);
        return SHA1.hex(stringBuilder.toString());
    }

    public static byte[] m5865a(DataInputStream dataInputStream) throws IOException, SmackException {
        Object obj = new byte[5];
        dataInputStream.readFully(obj, 0, 5);
        if (obj[3] != (byte) 3) {
            throw new SmackException("Unsupported SOCKS5 address type");
        }
        byte b = obj[4];
        Object obj2 = new byte[(b + 7)];
        System.arraycopy(obj, 0, obj2, 0, obj.length);
        dataInputStream.readFully(obj2, obj.length, b + 2);
        return obj2;
    }
}
