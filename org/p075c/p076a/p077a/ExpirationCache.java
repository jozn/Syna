package org.p075c.p076a.p077a;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: org.c.a.a.b */
public class ExpirationCache<K, V> implements Map<K, V>, Cache<K, V> {
    private final LruCache<K, ExpirationCache<V>> f4149a;
    private long f4150b;

    /* renamed from: org.c.a.a.b.a */
    private static class ExpirationCache<K, V> implements Entry<K, V> {
        private final K f4145a;
        private V f4146b;

        public ExpirationCache(K k, V v) {
            this.f4145a = k;
            this.f4146b = v;
        }

        public K getKey() {
            return this.f4145a;
        }

        public V getValue() {
            return this.f4146b;
        }

        public V setValue(V v) {
            V v2 = this.f4146b;
            this.f4146b = v;
            return v2;
        }
    }

    /* renamed from: org.c.a.a.b.b */
    private static class ExpirationCache<V> {
        public final V f4147a;
        public final long f4148b;

        public ExpirationCache(V v, long j) {
            this.f4147a = v;
            this.f4148b = System.currentTimeMillis() + j;
        }

        public boolean m5380a() {
            return System.currentTimeMillis() > this.f4148b;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ExpirationCache)) {
                return false;
            }
            return this.f4147a.equals(((ExpirationCache) obj).f4147a);
        }

        public int hashCode() {
            return this.f4147a.hashCode();
        }
    }

    public ExpirationCache(int i, long j) {
        this.f4149a = new LruCache(i);
        m5382a(j);
    }

    public V m5381a(K k, V v, long j) {
        ExpirationCache expirationCache = (ExpirationCache) this.f4149a.put(k, new ExpirationCache(v, j));
        return expirationCache == null ? null : expirationCache.f4147a;
    }

    public void m5382a(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException();
        }
        this.f4150b = j;
    }

    public void clear() {
        this.f4149a.clear();
    }

    public boolean containsKey(Object obj) {
        return this.f4149a.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.f4149a.containsValue(obj);
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> hashSet = new HashSet();
        for (Entry entry : this.f4149a.entrySet()) {
            hashSet.add(new ExpirationCache(entry.getKey(), ((ExpirationCache) entry.getValue()).f4147a));
        }
        return hashSet;
    }

    public V get(Object obj) {
        ExpirationCache expirationCache = (ExpirationCache) this.f4149a.get(obj);
        if (expirationCache == null) {
            return null;
        }
        if (!expirationCache.m5380a()) {
            return expirationCache.f4147a;
        }
        remove(obj);
        return null;
    }

    public boolean isEmpty() {
        return this.f4149a.isEmpty();
    }

    public Set<K> keySet() {
        return this.f4149a.keySet();
    }

    public V put(K k, V v) {
        return m5381a(k, v, this.f4150b);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public V remove(Object obj) {
        ExpirationCache expirationCache = (ExpirationCache) this.f4149a.remove(obj);
        return expirationCache == null ? null : expirationCache.f4147a;
    }

    public int size() {
        return this.f4149a.size();
    }

    public Collection<V> values() {
        Collection hashSet = new HashSet();
        for (ExpirationCache expirationCache : this.f4149a.values()) {
            hashSet.add(expirationCache.f4147a);
        }
        return hashSet;
    }
}
