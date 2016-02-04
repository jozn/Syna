package org.p074b;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/* renamed from: org.b.a */
public class JSONArray {
    private ArrayList f4124a;

    public JSONArray() {
        this.f4124a = new ArrayList();
    }

    public JSONArray(Object obj) throws JSONException {
        this();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                m5347a(Array.get(obj, i));
            }
            return;
        }
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }

    public JSONArray(String str) throws JSONException {
        this(new JSONTokener(str));
    }

    public JSONArray(Collection collection) {
        this.f4124a = collection == null ? new ArrayList() : new ArrayList(collection);
    }

    public JSONArray(JSONTokener jSONTokener) throws JSONException {
        this();
        if (jSONTokener.m5376d() != '[') {
            throw jSONTokener.m5372a("A JSONArray text must start with '['");
        } else if (jSONTokener.m5376d() != ']') {
            jSONTokener.m5373a();
            while (true) {
                if (jSONTokener.m5376d() == ',') {
                    jSONTokener.m5373a();
                    this.f4124a.add(null);
                } else {
                    jSONTokener.m5373a();
                    this.f4124a.add(jSONTokener.m5377e());
                }
                switch (jSONTokener.m5376d()) {
                    case ',':
                    case ';':
                        if (jSONTokener.m5376d() != ']') {
                            jSONTokener.m5373a();
                        } else {
                            return;
                        }
                    case ']':
                        return;
                    default:
                        throw jSONTokener.m5372a("Expected a ',' or ']'");
                }
            }
        }
    }

    public int m5344a() {
        return this.f4124a.size();
    }

    public Object m5345a(int i) throws JSONException {
        Object e = m5351e(i);
        if (e != null) {
            return e;
        }
        throw new JSONException("JSONArray[" + i + "] not found.");
    }

    public String m5346a(String str) throws JSONException {
        int a = m5344a();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < a; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(JSONObject.m5354b(this.f4124a.get(i)));
        }
        return stringBuffer.toString();
    }

    public JSONArray m5347a(Object obj) {
        this.f4124a.add(obj);
        return this;
    }

    public JSONObject m5348b(int i) throws JSONException {
        Object a = m5345a(i);
        if (a instanceof JSONObject) {
            return (JSONObject) a;
        }
        throw new JSONException("JSONArray[" + i + "] is not a JSONObject.");
    }

    public String m5349c(int i) throws JSONException {
        return m5345a(i).toString();
    }

    public boolean m5350d(int i) {
        return JSONObject.f4126a.equals(m5351e(i));
    }

    public Object m5351e(int i) {
        return (i < 0 || i >= m5344a()) ? null : this.f4124a.get(i);
    }

    public String toString() {
        try {
            return '[' + m5346a(",") + ']';
        } catch (Exception e) {
            return null;
        }
    }
}
