package android.support.v4.p008c;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: android.support.v4.c.a */
public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {
    MapCollections<K, V> f350a;

    private MapCollections<K, V> m387b() {
        if (this.f350a == null) {
            this.f350a = new ArrayMap(this);
        }
        return this.f350a;
    }

    public Set<Entry<K, V>> entrySet() {
        return m387b().m403d();
    }

    public Set<K> keySet() {
        return m387b().m404e();
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        m383a(this.h + map.size());
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public Collection<V> values() {
        return m387b().m405f();
    }
}
