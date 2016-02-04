package mobi.mmdt.ott.core.p051a;

/* renamed from: mobi.mmdt.ott.core.a.a */
public class ArabicReshaper {
    public static char f3473a;
    public static char f3474b;
    public static char f3475c;
    public static char f3476d;
    public static char f3477e;
    public static char[][] f3478f;
    public static char[] f3479g;
    public static char[][] f3480h;
    private String f3481i;

    /* renamed from: mobi.mmdt.ott.core.a.a.a */
    class ArabicReshaper {
        char[] f3466a;
        int[] f3467b;
        char[] f3468c;
        int[] f3469d;
        final /* synthetic */ ArabicReshaper f3470e;

        ArabicReshaper(ArabicReshaper arabicReshaper, String str) {
            int i;
            int i2 = 0;
            this.f3470e = arabicReshaper;
            int length = str.length();
            int i3 = 0;
            for (i = 0; i < length; i++) {
                if (arabicReshaper.m4401b(str.charAt(i))) {
                    i3++;
                }
            }
            this.f3467b = new int[i3];
            this.f3466a = new char[i3];
            this.f3469d = new int[(length - i3)];
            this.f3468c = new char[(length - i3)];
            i = 0;
            for (i3 = 0; i3 < str.length(); i3++) {
                if (arabicReshaper.m4401b(str.charAt(i3))) {
                    this.f3467b[i] = i3;
                    this.f3466a[i] = str.charAt(i3);
                    i++;
                } else {
                    this.f3469d[i2] = i3;
                    this.f3468c[i2] = str.charAt(i3);
                    i2++;
                }
            }
        }

        String m4393a(String str) {
            int i = 0;
            char[] cArr = new char[(str.length() + this.f3466a.length)];
            for (int i2 = 0; i2 < this.f3469d.length; i2++) {
                cArr[this.f3469d[i2]] = str.charAt(i2);
            }
            while (i < this.f3467b.length) {
                cArr[this.f3467b[i]] = this.f3466a[i];
                i++;
            }
            return new String(cArr);
        }
    }

    static {
        f3473a = '\u0622';
        f3474b = '\u0623';
        f3475c = '\u0625';
        f3476d = '\u0627';
        f3477e = '\u0644';
        f3478f = new char[][]{new char[]{'\u3ba6', '\ufef6', '\ufef5'}, new char[]{'\u3ba7', '\ufef8', '\ufef7'}, new char[]{'\u0627', '\ufefc', '\ufefb'}, new char[]{'\u0625', '\ufefa', '\ufef9'}};
        f3479g = new char[]{'\u0600', '\u0601', '\u0602', '\u0603', '\u0606', '\u0607', '\u0608', '\u0609', '\u060a', '\u060b', '\u060d', '\u060e', '\u0610', '\u0611', '\u0612', '\u0613', '\u0614', '\u0615', '\u0616', '\u0617', '\u0618', '\u0619', '\u061a', '\u061b', '\u061e', '\u061f', '\u0621', '\u063b', '\u063c', '\u063d', '\u063e', '\u063f', '\u0640', '\u064b', '\u064c', '\u064d', '\u064e', '\u064f', '\u0650', '\u0651', '\u0652', '\u0653', '\u0654', '\u0655', '\u0656', '\u0657', '\u0658', '\u0659', '\u065a', '\u065b', '\u065c', '\u065d', '\u065e', '\u0660', '\u066a', '\u066b', '\u066c', '\u066f', '\u0670', '\u0672', '\u06d4', '\u06d5', '\u06d6', '\u06d7', '\u06d8', '\u06d9', '\u06da', '\u06db', '\u06dc', '\u06df', '\u06e0', '\u06e1', '\u06e2', '\u06e3', '\u06e4', '\u06e5', '\u06e6', '\u06e7', '\u06e8', '\u06e9', '\u06ea', '\u06eb', '\u06ec', '\u06ed', '\u06ee', '\u06ef', '\u06d6', '\u06d7', '\u06d8', '\u06d9', '\u06da', '\u06db', '\u06dc', '\u06dd', '\u06de', '\u06df', '\u06f0', '\u06fd', '\ufe70', '\ufe71', '\ufe72', '\ufe73', '\ufe74', '\ufe75', '\ufe76', '\ufe77', '\ufe78', '\ufe79', '\ufe7a', '\ufe7b', '\ufe7c', '\ufe7d', '\ufe7e', '\ufe7f', '\ufc5e', '\ufc5f', '\ufc60', '\ufc61', '\ufc62', '\ufc63'};
        f3480h = new char[][]{new char[]{'\u0622', '\ufe81', '\ufe81', '\ufe82', '\ufe82', '\u0002'}, new char[]{'\u0623', '\ufe83', '\ufe83', '\ufe84', '\ufe84', '\u0002'}, new char[]{'\u0624', '\ufe85', '\ufe85', '\ufe86', '\ufe86', '\u0002'}, new char[]{'\u0625', '\ufe87', '\ufe87', '\ufe88', '\ufe88', '\u0002'}, new char[]{'\u0626', '\ufe89', '\ufe8b', '\ufe8c', '\ufe8a', '\u0004'}, new char[]{'\u0627', '\ufe8d', '\ufe8d', '\ufe8e', '\ufe8e', '\u0002'}, new char[]{'\u0628', '\ufe8f', '\ufe91', '\ufe92', '\ufe90', '\u0004'}, new char[]{'\u0629', '\ufe93', '\ufe93', '\ufe94', '\ufe94', '\u0002'}, new char[]{'\u062a', '\ufe95', '\ufe97', '\ufe98', '\ufe96', '\u0004'}, new char[]{'\u062b', '\ufe99', '\ufe9b', '\ufe9c', '\ufe9a', '\u0004'}, new char[]{'\u062c', '\ufe9d', '\ufe9f', '\ufea0', '\ufe9e', '\u0004'}, new char[]{'\u062d', '\ufea1', '\ufea3', '\ufea4', '\ufea2', '\u0004'}, new char[]{'\u062e', '\ufea5', '\ufea7', '\ufea8', '\ufea6', '\u0004'}, new char[]{'\u062f', '\ufea9', '\ufea9', '\ufeaa', '\ufeaa', '\u0002'}, new char[]{'\u0630', '\ufeab', '\ufeab', '\ufeac', '\ufeac', '\u0002'}, new char[]{'\u0631', '\ufead', '\ufead', '\ufeae', '\ufeae', '\u0002'}, new char[]{'\u0632', '\ufeaf', '\ufeaf', '\ufeb0', '\ufeb0', '\u0002'}, new char[]{'\u0633', '\ufeb1', '\ufeb3', '\ufeb4', '\ufeb2', '\u0004'}, new char[]{'\u0634', '\ufeb5', '\ufeb7', '\ufeb8', '\ufeb6', '\u0004'}, new char[]{'\u0635', '\ufeb9', '\ufebb', '\ufebc', '\ufeba', '\u0004'}, new char[]{'\u0636', '\ufebd', '\ufebf', '\ufec0', '\ufebe', '\u0004'}, new char[]{'\u0637', '\ufec1', '\ufec3', '\ufec4', '\ufec2', '\u0004'}, new char[]{'\u0638', '\ufec5', '\ufec7', '\ufec8', '\ufec6', '\u0004'}, new char[]{'\u0639', '\ufec9', '\ufecb', '\ufecc', '\ufeca', '\u0004'}, new char[]{'\u063a', '\ufecd', '\ufecf', '\ufed0', '\ufece', '\u0004'}, new char[]{'\u0641', '\ufed1', '\ufed3', '\ufed4', '\ufed2', '\u0004'}, new char[]{'\u0642', '\ufed5', '\ufed7', '\ufed8', '\ufed6', '\u0004'}, new char[]{'\u0643', '\ufed9', '\ufedb', '\ufedc', '\ufeda', '\u0004'}, new char[]{'\u0644', '\ufedd', '\ufedf', '\ufee0', '\ufede', '\u0004'}, new char[]{'\u0645', '\ufee1', '\ufee3', '\ufee4', '\ufee2', '\u0004'}, new char[]{'\u0646', '\ufee5', '\ufee7', '\ufee8', '\ufee6', '\u0004'}, new char[]{'\u0647', '\ufee9', '\ufeeb', '\ufeec', '\ufeea', '\u0004'}, new char[]{'\u0648', '\ufeed', '\ufeed', '\ufeee', '\ufeee', '\u0002'}, new char[]{'\u0649', '\ufeef', '\ufeef', '\ufef0', '\ufef0', '\u0002'}, new char[]{'\u0671', '\u0671', '\u0671', '\ufb51', '\ufb51', '\u0002'}, new char[]{'\u064a', '\ufef1', '\ufef3', '\ufef4', '\ufef2', '\u0004'}, new char[]{'\u066e', '\ufbe4', '\ufbe8', '\ufbe9', '\ufbe5', '\u0004'}, new char[]{'\u0671', '\u0671', '\u0671', '\ufb51', '\ufb51', '\u0002'}, new char[]{'\u06aa', '\ufb8e', '\ufb90', '\ufb91', '\ufb8f', '\u0004'}, new char[]{'\u06c1', '\ufba6', '\ufba8', '\ufba9', '\ufba7', '\u0004'}, new char[]{'\u06e4', '\u06e4', '\u06e4', '\u06e4', '\ufeee', '\u0002'}, new char[]{'\u00d7', '\ufb56', '\ufb58', '\ufb59', '\ufb57', '\u0004'}, new char[]{'\u067e', '\ufb56', '\ufb58', '\ufb59', '\ufb57', '\u0004'}, new char[]{'\u0686', '\ufb7a', '\ufb7c', '\ufb7d', '\ufb7b', '\u0004'}, new char[]{'\u0698', '\ufb8a', '\ufb8a', '\ufb8b', '\ufb8b', '\u0002'}, new char[]{'\u06a9', '\ufb8e', '\ufb90', '\ufb91', '\ufb8f', '\u0004'}, new char[]{'\u06af', '\ufb92', '\ufb94', '\ufb95', '\ufb93', '\u0004'}, new char[]{'\u06cc', '\ufbfc', '\ufbfe', '\ufbff', '\ufbfd', '\u0004'}, new char[]{'\u06c0', '\ufba4', '\ufba4', '\ufba5', '\ufba5', '\u0002'}};
    }

    public ArabicReshaper(String str) {
        this.f3481i = "";
        ArabicReshaper arabicReshaper = new ArabicReshaper(this, m4400b(str));
        if (arabicReshaper.f3468c.length > 0) {
            this.f3481i = m4403a(new String(arabicReshaper.f3468c));
        }
        this.f3481i = arabicReshaper.m4393a(this.f3481i);
    }

    private char m4396a(char c, char c2, boolean z) {
        char c3 = '\u0000';
        int i = z ? 2 : 1;
        if (f3477e != c2) {
            return '\u0000';
        }
        if (c == f3473a) {
            c3 = f3478f[0][i];
        }
        if (c == f3474b) {
            c3 = f3478f[1][i];
        }
        if (c == f3475c) {
            c3 = f3478f[3][i];
        }
        return c == f3476d ? f3478f[2][i] : c3;
    }

    private char m4397a(char c, int i) {
        for (int i2 = 0; i2 < f3480h.length; i2++) {
            if (f3480h[i2][0] == c) {
                return f3480h[i2][i];
            }
        }
        return c;
    }

    private int m4398a(char c) {
        for (int i = 0; i < f3480h.length; i++) {
            if (f3480h[i][0] == c) {
                return f3480h[i][5];
            }
        }
        return 2;
    }

    private String m4400b(String str) {
        int length = str.length();
        char[] cArr = new char[length];
        str.getChars(0, length, cArr, 0);
        length = 0;
        char c = '\u0000';
        while (length < cArr.length - 1) {
            if (!(m4401b(cArr[length]) || f3477e == cArr[length])) {
                c = cArr[length];
            }
            if (f3477e == cArr[length]) {
                char c2 = cArr[length];
                int i = length + 1;
                while (i < cArr.length && m4401b(cArr[i])) {
                    i++;
                }
                if (i < cArr.length) {
                    c2 = (length <= 0 || m4398a(c) <= 2) ? m4396a(cArr[i], c2, true) : m4396a(cArr[i], c2, false);
                    if (c2 != '\u0000') {
                        cArr[length] = c2;
                        cArr[i] = ' ';
                    }
                }
            }
            length++;
        }
        return new String(cArr).replaceAll(" ", "").trim();
    }

    private boolean m4401b(char c) {
        for (char c2 : f3479g) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    public String m4402a() {
        return this.f3481i;
    }

    public String m4403a(String str) {
        StringBuffer stringBuffer = new StringBuffer("");
        int length = str.length();
        char[] cArr = new char[length];
        str.getChars(0, length, cArr, 0);
        stringBuffer.append(m4397a(cArr[0], 2));
        for (int i = 1; i < length - 1; i++) {
            if (m4398a(cArr[i - 1]) == 2) {
                stringBuffer.append(m4397a(cArr[i], 2));
            } else {
                stringBuffer.append(m4397a(cArr[i], 3));
            }
        }
        if (length >= 2) {
            if (m4398a(cArr[length - 2]) == 2) {
                stringBuffer.append(m4397a(cArr[length - 1], 1));
            } else {
                stringBuffer.append(m4397a(cArr[length - 1], 4));
            }
        }
        return stringBuffer.toString();
    }
}
