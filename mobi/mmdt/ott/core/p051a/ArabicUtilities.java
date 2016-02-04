package mobi.mmdt.ott.core.p051a;

import java.util.ArrayList;
import java.util.List;

/* renamed from: mobi.mmdt.ott.core.a.b */
public class ArabicUtilities {
    private static boolean m4409a(char c) {
        for (char[] cArr : ArabicReshaper.f3480h) {
            if (cArr[0] == c) {
                return true;
            }
        }
        for (char c2 : ArabicReshaper.f3479g) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    public static boolean m4410a(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (ArabicUtilities.m4409a(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean m4411b(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!ArabicUtilities.m4409a(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String m4412c(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        String[] split = str.split("\n");
        for (int i = 0; i < split.length; i++) {
            stringBuffer.append(ArabicUtilities.m4413d(split[i]));
            if (i < split.length - 1) {
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }

    public static String m4413d(String str) {
        String[] e = ArabicUtilities.m4414e(str);
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < e.length; i++) {
            if (!ArabicUtilities.m4410a(e[i])) {
                stringBuffer.append(e[i]);
            } else if (ArabicUtilities.m4411b(e[i])) {
                stringBuffer.append(new ArabicReshaper(e[i]).m4402a());
            } else {
                String[] f = ArabicUtilities.m4415f(e[i]);
                for (String arabicReshaper : f) {
                    stringBuffer.append(new ArabicReshaper(arabicReshaper).m4402a());
                }
            }
            if (i < e.length - 1) {
                stringBuffer.append(" ");
            }
        }
        return stringBuffer.toString();
    }

    private static String[] m4414e(String str) {
        return str != null ? str.split("\\s") : new String[0];
    }

    private static String[] m4415f(String str) {
        List arrayList = new ArrayList();
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            if (ArabicUtilities.m4409a(str.charAt(i))) {
                if (str2.equals("") || ArabicUtilities.m4411b(str2)) {
                    str2 = new StringBuilder(String.valueOf(str2)).append(str.charAt(i)).toString();
                } else {
                    arrayList.add(str2);
                    str2 = str.charAt(i);
                }
            } else if (str2.equals("") || !ArabicUtilities.m4411b(str2)) {
                str2 = new StringBuilder(String.valueOf(str2)).append(str.charAt(i)).toString();
            } else {
                arrayList.add(str2);
                str2 = str.charAt(i);
            }
        }
        if (!str2.equals("")) {
            arrayList.add(str2);
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
