package org.p074b;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: org.b.c */
public class JSONObject {
    public static final Object f4126a;
    private HashMap f4127b;

    /* renamed from: org.b.c.a */
    private static final class JSONObject {
        private JSONObject() {
        }

        protected final Object clone() {
            return this;
        }

        public boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public String toString() {
            return "null";
        }
    }

    static {
        f4126a = new JSONObject();
    }

    public JSONObject() {
        this.f4127b = new HashMap();
    }

    public JSONObject(String str) throws JSONException {
        this(new JSONTokener(str));
    }

    public JSONObject(Map map) {
        this.f4127b = map == null ? new HashMap() : new HashMap(map);
    }

    public JSONObject(JSONTokener jSONTokener) throws JSONException {
        this();
        if (jSONTokener.m5376d() != '{') {
            throw jSONTokener.m5372a("A JSONObject text must begin with '{'");
        }
        while (true) {
            switch (jSONTokener.m5376d()) {
                case VideoSize.QCIF /*0*/:
                    throw jSONTokener.m5372a("A JSONObject text must end with '}'");
                case '}':
                    return;
                default:
                    jSONTokener.m5373a();
                    String obj = jSONTokener.m5377e().toString();
                    char d = jSONTokener.m5376d();
                    if (d == '=') {
                        if (jSONTokener.m5375c() != '>') {
                            jSONTokener.m5373a();
                        }
                    } else if (d != ':') {
                        throw jSONTokener.m5372a("Expected a ':' after a key");
                    }
                    m5359a(obj, jSONTokener.m5377e());
                    switch (jSONTokener.m5376d()) {
                        case ',':
                        case ';':
                            if (jSONTokener.m5376d() != '}') {
                                jSONTokener.m5373a();
                            } else {
                                return;
                            }
                        case '}':
                            return;
                        default:
                            throw jSONTokener.m5372a("Expected a ',' or '}'");
                    }
            }
        }
    }

    public static String m5352a(Number number) throws JSONException {
        if (number == null) {
            throw new JSONException("Null pointer");
        }
        JSONObject.m5353a((Object) number);
        String obj = number.toString();
        if (obj.indexOf(46) <= 0 || obj.indexOf(HttpStatus.SC_SWITCHING_PROTOCOLS) >= 0 || obj.indexOf(69) >= 0) {
            return obj;
        }
        while (obj.endsWith("0")) {
            obj = obj.substring(0, obj.length() - 1);
        }
        return obj.endsWith(".") ? obj.substring(0, obj.length() - 1) : obj;
    }

    static void m5353a(Object obj) throws JSONException {
        if (obj == null) {
            return;
        }
        if (obj instanceof Double) {
            if (((Double) obj).isInfinite() || ((Double) obj).isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        } else if (!(obj instanceof Float)) {
        } else {
            if (((Float) obj).isInfinite() || ((Float) obj).isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        }
    }

    static String m5354b(Object obj) throws JSONException {
        if (obj == null || obj.equals(null)) {
            return "null";
        }
        if (!(obj instanceof JSONString)) {
            return obj instanceof Number ? JSONObject.m5352a((Number) obj) : ((obj instanceof Boolean) || (obj instanceof JSONObject) || (obj instanceof JSONArray)) ? obj.toString() : obj instanceof Map ? new JSONObject((Map) obj).toString() : obj instanceof Collection ? new JSONArray((Collection) obj).toString() : obj.getClass().isArray() ? new JSONArray(obj).toString() : JSONObject.m5355j(obj.toString());
        } else {
            try {
                String a = ((JSONString) obj).m5369a();
                if (a instanceof String) {
                    return a;
                }
                throw new JSONException("Bad value from toJSONString: " + a);
            } catch (Throwable e) {
                throw new JSONException(e);
            }
        }
    }

    public static String m5355j(String str) {
        int i = 0;
        if (str == null || str.length() == 0) {
            return "\"\"";
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length + 4);
        stringBuffer.append('\"');
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case Version.API08_FROYO_22 /*8*/:
                    stringBuffer.append("\\b");
                    break;
                case Version.API09_GINGERBREAD_23 /*9*/:
                    stringBuffer.append("\\t");
                    break;
                case Version.API10_GINGERBREAD_MR1_233 /*10*/:
                    stringBuffer.append("\\n");
                    break;
                case Version.API12_HONEYCOMB_MR1_31X /*12*/:
                    stringBuffer.append("\\f");
                    break;
                case Version.API13_HONEYCOMB_MR2_32 /*13*/:
                    stringBuffer.append("\\r");
                    break;
                case '\"':
                case '\\':
                    stringBuffer.append('\\');
                    stringBuffer.append(charAt);
                    break;
                case '/':
                    if (i2 == 60) {
                        stringBuffer.append('\\');
                    }
                    stringBuffer.append(charAt);
                    break;
                default:
                    if (charAt >= ' ' && ((charAt < '\u0080' || charAt >= '\u00a0') && (charAt < '\u2000' || charAt >= '\u2100'))) {
                        stringBuffer.append(charAt);
                        break;
                    }
                    String str2 = "000" + Integer.toHexString(charAt);
                    stringBuffer.append("\\u" + str2.substring(str2.length() - 4));
                    break;
            }
            i++;
            char c = charAt;
        }
        stringBuffer.append('\"');
        return stringBuffer.toString();
    }

    public Object m5356a(String str) throws JSONException {
        Object i = m5367i(str);
        if (i != null) {
            return i;
        }
        throw new JSONException("JSONObject[" + JSONObject.m5355j(str) + "] not found.");
    }

    public Iterator m5357a() {
        return this.f4127b.keySet().iterator();
    }

    public JSONObject m5358a(String str, int i) throws JSONException {
        m5359a(str, new Integer(i));
        return this;
    }

    public JSONObject m5359a(String str, Object obj) throws JSONException {
        if (str == null) {
            throw new JSONException("Null key.");
        }
        if (obj != null) {
            JSONObject.m5353a(obj);
            this.f4127b.put(str, obj);
        } else {
            m5368k(str);
        }
        return this;
    }

    public double m5360b(String str) throws JSONException {
        Object a = m5356a(str);
        try {
            return a instanceof Number ? ((Number) a).doubleValue() : Double.valueOf((String) a).doubleValue();
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + JSONObject.m5355j(str) + "] is not a number.");
        }
    }

    public int m5361c(String str) throws JSONException {
        Object a = m5356a(str);
        return a instanceof Number ? ((Number) a).intValue() : (int) m5360b(str);
    }

    public JSONArray m5362d(String str) throws JSONException {
        Object a = m5356a(str);
        if (a instanceof JSONArray) {
            return (JSONArray) a;
        }
        throw new JSONException("JSONObject[" + JSONObject.m5355j(str) + "] is not a JSONArray.");
    }

    public JSONObject m5363e(String str) throws JSONException {
        Object a = m5356a(str);
        if (a instanceof JSONObject) {
            return (JSONObject) a;
        }
        throw new JSONException("JSONObject[" + JSONObject.m5355j(str) + "] is not a JSONObject.");
    }

    public long m5364f(String str) throws JSONException {
        Object a = m5356a(str);
        return a instanceof Number ? ((Number) a).longValue() : (long) m5360b(str);
    }

    public String m5365g(String str) throws JSONException {
        return m5356a(str).toString();
    }

    public boolean m5366h(String str) {
        return this.f4127b.containsKey(str);
    }

    public Object m5367i(String str) {
        return str == null ? null : this.f4127b.get(str);
    }

    public Object m5368k(String str) {
        return this.f4127b.remove(str);
    }

    public String toString() {
        try {
            Iterator a = m5357a();
            StringBuffer stringBuffer = new StringBuffer("{");
            while (a.hasNext()) {
                if (stringBuffer.length() > 1) {
                    stringBuffer.append(',');
                }
                Object next = a.next();
                stringBuffer.append(JSONObject.m5355j(next.toString()));
                stringBuffer.append(':');
                stringBuffer.append(JSONObject.m5354b(this.f4127b.get(next)));
            }
            stringBuffer.append('}');
            return stringBuffer.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
