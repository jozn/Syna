package org.jivesoftware.smack.util;

/* renamed from: org.jivesoftware.smack.util.e */
public class NumberUtil {
    public static void m5846a(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("unsigned 32-bit integers can't be negative");
        } else if (j > 4294967295L) {
            throw new IllegalArgumentException("unsigned 32-bit integers can't be greater then 2^32 - 1");
        }
    }
}
