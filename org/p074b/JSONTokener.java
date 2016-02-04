package org.p074b;

import org.apache.http.HttpStatus;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: org.b.e */
public class JSONTokener {
    private int f4128a;
    private String f4129b;

    public JSONTokener(String str) {
        this.f4128a = 0;
        this.f4129b = str;
    }

    public String m5370a(char c) throws JSONException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            char c2 = m5375c();
            switch (c2) {
                case VideoSize.QCIF /*0*/:
                case Version.API10_GINGERBREAD_MR1_233 /*10*/:
                case Version.API13_HONEYCOMB_MR2_32 /*13*/:
                    throw m5372a("Unterminated string");
                case '\\':
                    c2 = m5375c();
                    switch (c2) {
                        case 'b':
                            stringBuffer.append('\b');
                            break;
                        case HttpStatus.SC_PROCESSING /*102*/:
                            stringBuffer.append('\f');
                            break;
                        case 'n':
                            stringBuffer.append('\n');
                            break;
                        case 'r':
                            stringBuffer.append('\r');
                            break;
                        case 't':
                            stringBuffer.append('\t');
                            break;
                        case 'u':
                            stringBuffer.append((char) Integer.parseInt(m5371a(4), 16));
                            break;
                        case 'x':
                            stringBuffer.append((char) Integer.parseInt(m5371a(2), 16));
                            break;
                        default:
                            stringBuffer.append(c2);
                            break;
                    }
                default:
                    if (c2 != c) {
                        stringBuffer.append(c2);
                        break;
                    }
                    return stringBuffer.toString();
            }
        }
    }

    public String m5371a(int i) throws JSONException {
        int i2 = this.f4128a;
        int i3 = i2 + i;
        if (i3 >= this.f4129b.length()) {
            throw m5372a("Substring bounds error");
        }
        this.f4128a += i;
        return this.f4129b.substring(i2, i3);
    }

    public JSONException m5372a(String str) {
        return new JSONException(str + toString());
    }

    public void m5373a() {
        if (this.f4128a > 0) {
            this.f4128a--;
        }
    }

    public boolean m5374b() {
        return this.f4128a < this.f4129b.length();
    }

    public char m5375c() {
        if (!m5374b()) {
            return '\u0000';
        }
        char charAt = this.f4129b.charAt(this.f4128a);
        this.f4128a++;
        return charAt;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char m5376d() throws org.p074b.JSONException {
        /*
        r5 = this;
        r4 = 13;
        r3 = 10;
        r0 = 47;
    L_0x0006:
        r1 = r5.m5375c();
        if (r1 != r0) goto L_0x003d;
    L_0x000c:
        r1 = r5.m5375c();
        switch(r1) {
            case 42: goto L_0x0025;
            case 47: goto L_0x0017;
            default: goto L_0x0013;
        };
    L_0x0013:
        r5.m5373a();
    L_0x0016:
        return r0;
    L_0x0017:
        r1 = r5.m5375c();
        if (r1 == r3) goto L_0x0006;
    L_0x001d:
        if (r1 == r4) goto L_0x0006;
    L_0x001f:
        if (r1 != 0) goto L_0x0017;
    L_0x0021:
        goto L_0x0006;
    L_0x0022:
        r5.m5373a();
    L_0x0025:
        r1 = r5.m5375c();
        if (r1 != 0) goto L_0x0032;
    L_0x002b:
        r0 = "Unclosed comment";
        r0 = r5.m5372a(r0);
        throw r0;
    L_0x0032:
        r2 = 42;
        if (r1 != r2) goto L_0x0025;
    L_0x0036:
        r1 = r5.m5375c();
        if (r1 != r0) goto L_0x0022;
    L_0x003c:
        goto L_0x0006;
    L_0x003d:
        r2 = 35;
        if (r1 != r2) goto L_0x004c;
    L_0x0041:
        r1 = r5.m5375c();
        if (r1 == r3) goto L_0x0006;
    L_0x0047:
        if (r1 == r4) goto L_0x0006;
    L_0x0049:
        if (r1 != 0) goto L_0x0041;
    L_0x004b:
        goto L_0x0006;
    L_0x004c:
        if (r1 == 0) goto L_0x0052;
    L_0x004e:
        r2 = 32;
        if (r1 <= r2) goto L_0x0006;
    L_0x0052:
        r0 = r1;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.b.e.d():char");
    }

    public Object m5377e() throws JSONException {
        char d = m5376d();
        switch (d) {
            case '\"':
            case '\'':
                return m5370a(d);
            case '[':
                m5373a();
                return new JSONArray(this);
            case '{':
                m5373a();
                return new JSONObject(this);
            default:
                StringBuffer stringBuffer = new StringBuffer();
                char c = d;
                while (c >= ' ' && ",:]}/\\\"[{;=#".indexOf(c) < 0) {
                    stringBuffer.append(c);
                    c = m5375c();
                }
                m5373a();
                String trim = stringBuffer.toString().trim();
                if (trim.equals("")) {
                    throw m5372a("Missing value");
                } else if (trim.equalsIgnoreCase("true")) {
                    return Boolean.TRUE;
                } else {
                    if (trim.equalsIgnoreCase("false")) {
                        return Boolean.FALSE;
                    }
                    if (trim.equalsIgnoreCase("null")) {
                        return JSONObject.f4126a;
                    }
                    if ((d < '0' || d > '9') && d != '.' && d != '-' && d != '+') {
                        return trim;
                    }
                    if (d == '0') {
                        if (trim.length() <= 2 || !(trim.charAt(1) == 'x' || trim.charAt(1) == 'X')) {
                            try {
                                return new Integer(Integer.parseInt(trim, 8));
                            } catch (Exception e) {
                            }
                        } else {
                            try {
                                return new Integer(Integer.parseInt(trim.substring(2), 16));
                            } catch (Exception e2) {
                            }
                        }
                    }
                    try {
                        return new Integer(trim);
                    } catch (Exception e3) {
                        try {
                            return new Long(trim);
                        } catch (Exception e4) {
                            try {
                                return new Double(trim);
                            } catch (Exception e5) {
                                return trim;
                            }
                        }
                    }
                }
        }
    }

    public String toString() {
        return " at character " + this.f4128a + " of " + this.f4129b;
    }
}
