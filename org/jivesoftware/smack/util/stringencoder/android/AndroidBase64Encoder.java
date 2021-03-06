package org.jivesoftware.smack.util.stringencoder.android;

import android.util.Base64;
import org.jivesoftware.smack.util.stringencoder.Base64.C0190a;

public class AndroidBase64Encoder implements C0190a {
    private static final int BASE64_ENCODER_FLAGS = 2;
    private static AndroidBase64Encoder instance;

    static {
        instance = new AndroidBase64Encoder();
    }

    private AndroidBase64Encoder() {
    }

    public static AndroidBase64Encoder getInstance() {
        return instance;
    }

    public byte[] decode(String str) {
        return Base64.decode(str, 0);
    }

    public byte[] decode(byte[] bArr, int i, int i2) {
        return Base64.decode(bArr, i, i2, 0);
    }

    public byte[] encode(byte[] bArr, int i, int i2) {
        return Base64.encode(bArr, i, i2, BASE64_ENCODER_FLAGS);
    }

    public String encodeToString(byte[] bArr, int i, int i2) {
        return Base64.encodeToString(bArr, i, i2, BASE64_ENCODER_FLAGS);
    }
}
