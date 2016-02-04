package p000a.p001a.p002a.p004b;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.IDN;
import java.util.HashSet;

/* renamed from: a.a.a.b.a */
public class NameUtil {
    public static String m16a(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        if ((readUnsignedByte & 192) == 192) {
            readUnsignedByte = ((readUnsignedByte & 63) << 8) + dataInputStream.readUnsignedByte();
            HashSet hashSet = new HashSet();
            hashSet.add(Integer.valueOf(readUnsignedByte));
            return NameUtil.m17a(bArr, readUnsignedByte, hashSet);
        } else if (readUnsignedByte == 0) {
            return "";
        } else {
            byte[] bArr2 = new byte[readUnsignedByte];
            dataInputStream.readFully(bArr2);
            String toUnicode = IDN.toUnicode(new String(bArr2));
            String a = NameUtil.m16a(dataInputStream, bArr);
            return a.length() > 0 ? toUnicode + "." + a : toUnicode;
        }
    }

    public static String m17a(byte[] bArr, int i, HashSet<Integer> hashSet) {
        int i2 = bArr[i] & 255;
        if ((i2 & 192) == 192) {
            int i3 = ((i2 & 63) << 8) + (bArr[i + 1] & 255);
            if (hashSet.contains(Integer.valueOf(i3))) {
                throw new IllegalStateException("Cyclic offsets detected.");
            }
            hashSet.add(Integer.valueOf(i3));
            return NameUtil.m17a(bArr, i3, hashSet);
        } else if (i2 == 0) {
            return "";
        } else {
            String str = new String(bArr, i + 1, i2);
            String a = NameUtil.m17a(bArr, i2 + (i + 1), hashSet);
            return a.length() > 0 ? str + "." + a : str;
        }
    }

    public static byte[] m18a(String str) throws IOException {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(64);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (String toASCII : str.split("[.\u3002\uff0e\uff61]")) {
            byte[] bytes = IDN.toASCII(toASCII).getBytes();
            dataOutputStream.writeByte(bytes.length);
            dataOutputStream.write(bytes);
        }
        dataOutputStream.writeByte(0);
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }
}
