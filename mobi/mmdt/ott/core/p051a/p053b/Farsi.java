package mobi.mmdt.ott.core.p051a.p053b;

/* renamed from: mobi.mmdt.ott.core.a.b.a */
public final class Farsi {
    public static boolean f3487a;
    public static final char[] f3488b;
    static Farsi[] f3489c;
    private static final String f3490d;
    private static final String f3491e;
    private static final String f3492f;
    private static final String f3493g;

    /* renamed from: mobi.mmdt.ott.core.a.b.a.a */
    private static final class Farsi {
        public char f3482a;
        public char f3483b;
        public char f3484c;
        public char f3485d;
        public char f3486e;

        public Farsi(char c, char c2, char c3, char c4, char c5) {
            this.f3482a = c;
            this.f3483b = c2;
            this.f3484c = c3;
            this.f3485d = c4;
            this.f3486e = c5;
        }
    }

    static {
        f3487a = true;
        f3490d = Character.toString('\ufedf') + Character.toString('\ufe8e');
        f3491e = Character.toString('\ufee0') + Character.toString('\ufe8e');
        f3492f = Character.toString('\ufefb');
        f3493g = Character.toString('\ufefc');
        f3488b = new char[]{'\u06f0', '\u06f1', '\u06f2', '\u06f3', '\u06f4', '\u06f5', '\u06f6', '\u06f7', '\u06f8', '\u06f9'};
        f3489c = new Farsi[]{new Farsi('\u0630', '\ufeac', '\ufeab', '\ufeac', '\ufeab'), new Farsi('\u062f', '\ufeaa', '\ufea9', '\ufeaa', '\ufea9'), new Farsi('\u062c', '\ufe9e', '\ufe9f', '\ufea0', '\ufe9d'), new Farsi('\u062d', '\ufea2', '\ufea3', '\ufea4', '\ufea1'), new Farsi('\u062e', '\ufea6', '\ufea7', '\ufea8', '\ufea5'), new Farsi('\u0647', '\ufeea', '\ufeeb', '\ufeec', '\ufee9'), new Farsi('\u0639', '\ufeca', '\ufecb', '\ufecc', '\ufec9'), new Farsi('\u063a', '\ufece', '\ufecf', '\ufed0', '\ufecd'), new Farsi('\u0641', '\ufed2', '\ufed3', '\ufed4', '\ufed1'), new Farsi('\u0642', '\ufed6', '\ufed7', '\ufed8', '\ufed5'), new Farsi('\u062b', '\ufe9a', '\ufe9b', '\ufe9c', '\ufe99'), new Farsi('\u0635', '\ufeba', '\ufebb', '\ufebc', '\ufeb9'), new Farsi('\u0636', '\ufebe', '\ufebf', '\ufec0', '\ufebd'), new Farsi('\u0637', '\ufec2', '\ufec3', '\ufec4', '\ufec1'), new Farsi('\u0643', '\ufeda', '\ufedb', '\ufedc', '\ufed9'), new Farsi('\u0645', '\ufee2', '\ufee3', '\ufee4', '\ufee1'), new Farsi('\u0646', '\ufee6', '\ufee7', '\ufee8', '\ufee5'), new Farsi('\u062a', '\ufe96', '\ufe97', '\ufe98', '\ufe95'), new Farsi('\u0627', '\ufe8e', '\ufe8d', '\ufe8e', '\ufe8d'), new Farsi('\u0644', '\ufede', '\ufedf', '\ufee0', '\ufedd'), new Farsi('\u0628', '\ufe90', '\ufe91', '\ufe92', '\ufe8f'), new Farsi('\u064a', '\ufef2', '\ufef3', '\ufef4', '\ufef1'), new Farsi('\u0633', '\ufeb2', '\ufeb3', '\ufeb4', '\ufeb1'), new Farsi('\u0634', '\ufeb6', '\ufeb7', '\ufeb8', '\ufeb5'), new Farsi('\u0638', '\ufec6', '\ufec7', '\ufec8', '\ufec5'), new Farsi('\u0632', '\ufeb0', '\ufeaf', '\ufeb0', '\ufeaf'), new Farsi('\u0648', '\ufeee', '\ufeed', '\ufeee', '\ufeed'), new Farsi('\u0629', '\ufe94', '\ufe93', '\ufe93', '\ufe93'), new Farsi('\u0649', '\ufef0', '\ufeef', '\ufef0', '\ufeef'), new Farsi('\u0631', '\ufeae', '\ufead', '\ufeae', '\ufead'), new Farsi('\u0624', '\ufe86', '\ufe85', '\ufe86', '\ufe85'), new Farsi('\u0621', '\ufe80', '\ufe80', '\ufe80', '\ufe80'), new Farsi('\u0626', '\ufe8a', '\ufe8b', '\ufe8c', '\ufe89'), new Farsi('\u0623', '\ufe84', '\ufe83', '\ufe84', '\ufe83'), new Farsi('\u0622', '\ufe82', '\ufe81', '\ufe82', '\ufe81'), new Farsi('\u0625', '\ufe88', '\ufe87', '\ufe88', '\ufe87'), new Farsi('\u067e', '\ufb57', '\ufb58', '\ufb59', '\ufb56'), new Farsi('\u0686', '\ufb7b', '\ufb7c', '\ufb7d', '\ufb7a'), new Farsi('\u0698', '\ufb8b', '\ufb8a', '\ufb8b', '\ufb8a'), new Farsi('\u06a9', '\ufb8f', '\ufb90', '\ufb91', '\ufb8e'), new Farsi('\u06af', '\ufb93', '\ufb94', '\ufb95', '\ufb92'), new Farsi('\u06cc', '\ufbfd', '\ufef3', '\ufef4', '\ufbfc'), new Farsi('\u06c0', '\ufba5', '\ufba4', '\ufba5', '\ufba4')};
    }

    public static final String m4404a(String str) {
        int i = 0;
        if (!f3487a) {
            return str;
        }
        try {
            int i2;
            char[] cArr = new char[str.length()];
            char[] toCharArray = str.toCharArray();
            cArr = new char[str.length()];
            char[] toCharArray2 = str.toCharArray();
            int i3 = 0;
            while (i3 < str.length()) {
                char c = toCharArray2[i3];
                if ((c >= '\u0621' && c <= '\u064a') || c == '\u067e' || c == '\u0686' || c == '\u0698' || c == '\u06a9' || c == '\u06af' || c == '\u06cc' || c == '\u06c0') {
                    int i4 = 0;
                    while (i4 < 43 && f3489c[i4].f3482a != toCharArray2[i3]) {
                        i4++;
                    }
                    int i5;
                    if (i3 == str.length() - 1) {
                        i5 = 0;
                    } else {
                        i2 = (Farsi.m4405a(toCharArray2[i3 + 1]) || Farsi.m4407b(toCharArray2[i3 + 1])) ? 1 : 0;
                        i5 = i2;
                    }
                    boolean a = i3 == 0 ? false : Farsi.m4405a(toCharArray2[i3 - 1]);
                    if (i4 < 43) {
                        if (a && r2 != 0) {
                            toCharArray[i3] = f3489c[i4].f3485d;
                        }
                        if (a && r2 == 0) {
                            toCharArray[i3] = f3489c[i4].f3483b;
                        }
                        if (!(a || r2 == 0)) {
                            toCharArray[i3] = f3489c[i4].f3484c;
                        }
                        if (!a && r2 == 0) {
                            toCharArray[i3] = f3489c[i4].f3486e;
                        }
                    } else {
                        toCharArray[i3] = toCharArray2[i3];
                    }
                } else {
                    toCharArray[i3] = toCharArray2[i3];
                }
                i3++;
            }
            String str2 = "";
            for (char append : toCharArray) {
                str2 = new StringBuilder(String.valueOf(str2)).append(append).toString();
            }
            Farsi.m4406b(str2);
            cArr = str2.replace('\u200c', ' ').replace(f3490d, f3492f).replace(f3491e, f3493g).toCharArray();
            while (i < cArr.length) {
                if (cArr[i] >= '0' && cArr[i] <= '9') {
                    cArr[i] = (char) (cArr[i] + 1728);
                }
                i++;
            }
            return new String(cArr);
        } catch (Exception e) {
            return "";
        }
    }

    private static final boolean m4405a(char c) {
        char[] cArr = new char[]{'\u062c', '\u062d', '\u062e', '\u0647', '\u0639', '\u063a', '\u0641', '\u0642', '\u062b', '\u0635', '\u0636', '\u0637', '\u0643', '\u0645', '\u0646', '\u062a', '\u0644', '\u0628', '\u064a', '\u0633', '\u0634', '\u0638', '\u067e', '\u0686', '\u06a9', '\u06af', '\u06cc', '\u0626'};
        for (int i = 0; i < 28; i++) {
            if (c == cArr[i]) {
                return true;
            }
        }
        return false;
    }

    private static final String m4406b(String str) {
        try {
            String str2 = "";
            str = Farsi.m4408c(str);
            char[] cArr = new char[str.length()];
            char[] toCharArray = str.toCharArray();
            int i = 0;
            while (i < str.length()) {
                if (toCharArray[i] < '0' || toCharArray[i] > '9') {
                    str2 = new StringBuilder(String.valueOf(str2)).append(toCharArray[i]).toString();
                    i++;
                } else {
                    String str3 = "";
                    while (i < str.length() && ((toCharArray[i] >= '0' && toCharArray[i] <= '9') || toCharArray[i] == '/')) {
                        str3 = new StringBuilder(String.valueOf(str3)).append(toCharArray[i]).toString();
                        i++;
                    }
                    str2 = new StringBuilder(String.valueOf(str2)).append(Farsi.m4408c(str3)).toString();
                }
            }
            return str2;
        } catch (Exception e) {
            return str;
        }
    }

    private static final boolean m4407b(char c) {
        char[] cArr = new char[]{'\u0627', '\u0623', '\u0625', '\u0622', '\u062f', '\u0630', '\u0631', '\u0632', '\u0648', '\u0624', '\u0629', '\u0649', '\u0698', '\u06c0'};
        for (int i = 0; i < 14; i++) {
            if (c == cArr[i]) {
                return true;
            }
        }
        return false;
    }

    private static final String m4408c(String str) {
        String str2 = "";
        char[] cArr = new char[str.length()];
        char[] toCharArray = str.toCharArray();
        for (int length = str.length() - 1; length >= 0; length--) {
            str2 = new StringBuilder(String.valueOf(str2)).append(toCharArray[length]).toString();
        }
        return str2;
    }
}
