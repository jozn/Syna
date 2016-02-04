package org.p075c.p076a;

import org.p075c.p076a.p077a.LruCache;

/* renamed from: org.c.a.c */
public class XmppStringUtils {
    private static final LruCache<String, String> f4176a;
    private static final LruCache<String, String> f4177b;

    static {
        f4176a = new LruCache(100);
        f4177b = new LruCache(100);
    }

    public static String m5397a(CharSequence charSequence, CharSequence charSequence2) {
        return XmppStringUtils.m5399a(charSequence != null ? charSequence.toString() : null, charSequence2.toString());
    }

    public static String m5398a(String str) {
        int indexOf = str.indexOf(64);
        if (indexOf <= 0) {
            return "";
        }
        int indexOf2 = str.indexOf(47);
        return (indexOf2 < 0 || indexOf2 >= indexOf) ? str.substring(0, indexOf) : "";
    }

    public static String m5399a(String str, String str2) {
        return XmppStringUtils.m5400a(str, str2, null);
    }

    public static String m5400a(String str, String str2, String str3) {
        int i = 0;
        if (str2 == null) {
            throw new IllegalArgumentException("domainpart must not be null");
        }
        int length = str != null ? str.length() : 0;
        int length2 = str2.length();
        if (str3 != null) {
            i = str3.length();
        }
        StringBuilder stringBuilder = new StringBuilder(((length2 + length) + i) + 2);
        if (length > 0) {
            stringBuilder.append(str).append('@');
        }
        stringBuilder.append(str2);
        if (i > 0) {
            stringBuilder.append('/').append(str3);
        }
        return stringBuilder.toString();
    }

    public static String m5401b(String str) {
        int indexOf = str.indexOf(64);
        if (indexOf + 1 > str.length()) {
            return "";
        }
        int indexOf2 = str.indexOf(47);
        return indexOf2 > 0 ? indexOf2 > indexOf ? str.substring(indexOf + 1, indexOf2) : str.substring(0, indexOf2) : str.substring(indexOf + 1);
    }

    public static String m5402b(String str, String str2) {
        return str + '\t' + str2;
    }

    public static String m5403c(String str) {
        int indexOf = str.indexOf(47);
        return (indexOf + 1 > str.length() || indexOf < 0) ? "" : str.substring(indexOf + 1);
    }

    public static String m5404d(String str) {
        int indexOf = str.indexOf(47);
        return indexOf < 0 ? str : indexOf == 0 ? "" : str.substring(0, indexOf);
    }

    public static boolean m5405e(String str) {
        return XmppStringUtils.m5398a(str).length() > 0 && XmppStringUtils.m5401b(str).length() > 0 && XmppStringUtils.m5403c(str).length() > 0;
    }
}
