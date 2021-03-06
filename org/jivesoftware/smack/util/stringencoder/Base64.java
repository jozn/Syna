package org.jivesoftware.smack.util.stringencoder;

import java.io.UnsupportedEncodingException;
import org.jivesoftware.smack.util.Objects;
import org.jivesoftware.smack.util.StringUtils;

public class Base64 {
    private static C0190a base64encoder;

    /* renamed from: org.jivesoftware.smack.util.stringencoder.Base64.a */
    public interface C0190a {
        byte[] decode(String str);

        byte[] decode(byte[] bArr, int i, int i2);

        byte[] encode(byte[] bArr, int i, int i2);
    }

    public static final byte[] decode(String str) {
        return base64encoder.decode(str);
    }

    public static final byte[] decode(byte[] bArr) {
        return base64encoder.decode(bArr, 0, bArr.length);
    }

    public static final byte[] decode(byte[] bArr, int i, int i2) {
        return base64encoder.decode(bArr, i, i2);
    }

    public static final String decodeToString(String str) {
        try {
            return new String(decode(str), StringUtils.UTF8);
        } catch (Throwable e) {
            throw new IllegalStateException("UTF-8 not supported", e);
        }
    }

    public static final String decodeToString(byte[] bArr, int i, int i2) {
        try {
            return new String(decode(bArr, i, i2), StringUtils.UTF8);
        } catch (Throwable e) {
            throw new IllegalStateException("UTF-8 not supported", e);
        }
    }

    public static final String encode(String str) {
        try {
            return encodeToString(str.getBytes(StringUtils.UTF8));
        } catch (Throwable e) {
            throw new IllegalStateException("UTF-8 not supported", e);
        }
    }

    public static final byte[] encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length);
    }

    public static final byte[] encode(byte[] bArr, int i, int i2) {
        return base64encoder.encode(bArr, i, i2);
    }

    public static final String encodeToString(byte[] bArr) {
        try {
            return new String(encode(bArr), StringUtils.USASCII);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static final String encodeToString(byte[] bArr, int i, int i2) {
        try {
            return new String(encode(bArr, i, i2), StringUtils.USASCII);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static void setEncoder(C0190a c0190a) {
        Objects.m5847a(c0190a, "encoder must no be null");
        base64encoder = c0190a;
    }
}
