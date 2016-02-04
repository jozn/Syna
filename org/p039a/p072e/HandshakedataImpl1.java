package org.p039a.p072e;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

/* renamed from: org.a.e.g */
public class HandshakedataImpl1 implements HandshakeBuilder {
    private byte[] f4080a;
    private TreeMap<String, String> f4081b;

    public HandshakedataImpl1() {
        this.f4081b = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    }

    public void m5287a(String str, String str2) {
        this.f4081b.put(str, str2);
    }

    public void m5288a(byte[] bArr) {
        this.f4080a = bArr;
    }

    public String m5289b(String str) {
        String str2 = (String) this.f4081b.get(str);
        return str2 == null ? "" : str2;
    }

    public Iterator<String> m5290b() {
        return Collections.unmodifiableSet(this.f4081b.keySet()).iterator();
    }

    public boolean m5291c(String str) {
        return this.f4081b.containsKey(str);
    }

    public byte[] m5292c() {
        return this.f4080a;
    }
}
