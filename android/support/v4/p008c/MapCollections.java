package android.support.v4.p008c;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: android.support.v4.c.f */
abstract class MapCollections<K, V> {
    MapCollections f351b;
    MapCollections f352c;
    MapCollections f353d;

    /* renamed from: android.support.v4.c.f.a */
    final class MapCollections<T> implements Iterator<T> {
        final int f360a;
        int f361b;
        int f362c;
        boolean f363d;
        final /* synthetic */ MapCollections f364e;

        MapCollections(MapCollections mapCollections, int i) {
            this.f364e = mapCollections;
            this.f363d = false;
            this.f360a = i;
            this.f361b = mapCollections.m392a();
        }

        public boolean hasNext() {
            return this.f362c < this.f361b;
        }

        public T next() {
            T a = this.f364e.m394a(this.f362c, this.f360a);
            this.f362c++;
            this.f363d = true;
            return a;
        }

        public void remove() {
            if (this.f363d) {
                this.f362c--;
                this.f361b--;
                this.f363d = false;
                this.f364e.m396a(this.f362c);
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: android.support.v4.c.f.b */
    final class MapCollections implements Set<Entry<K, V>> {
        final /* synthetic */ MapCollections f365a;

        MapCollections(MapCollections mapCollections) {
            this.f365a = mapCollections;
        }

        public boolean m421a(Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        public /* synthetic */ boolean add(Object obj) {
            return m421a((Entry) obj);
        }

        public boolean addAll(Collection<? extends Entry<K, V>> collection) {
            int a = this.f365a.m392a();
            for (Entry entry : collection) {
                this.f365a.m397a(entry.getKey(), entry.getValue());
            }
            return a != this.f365a.m392a();
        }

        public void clear() {
            this.f365a.m402c();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            int a = this.f365a.m393a(entry.getKey());
            return a >= 0 ? ContainerHelpers.m417a(this.f365a.m394a(a, 1), entry.getValue()) : false;
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object obj) {
            return MapCollections.m389a((Set) this, obj);
        }

        public int hashCode() {
            int a = this.f365a.m392a() - 1;
            int i = 0;
            while (a >= 0) {
                Object a2 = this.f365a.m394a(a, 0);
                Object a3 = this.f365a.m394a(a, 1);
                a--;
                i += (a3 == null ? 0 : a3.hashCode()) ^ (a2 == null ? 0 : a2.hashCode());
            }
            return i;
        }

        public boolean isEmpty() {
            return this.f365a.m392a() == 0;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new MapCollections(this.f365a);
        }

        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return this.f365a.m392a();
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        public <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }
    }

    /* renamed from: android.support.v4.c.f.c */
    final class MapCollections implements Set<K> {
        final /* synthetic */ MapCollections f366a;

        MapCollections(MapCollections mapCollections) {
            this.f366a = mapCollections;
        }

        public boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            this.f366a.m402c();
        }

        public boolean contains(Object obj) {
            return this.f366a.m393a(obj) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            return MapCollections.m388a(this.f366a.m400b(), (Collection) collection);
        }

        public boolean equals(Object obj) {
            return MapCollections.m389a((Set) this, obj);
        }

        public int hashCode() {
            int i = 0;
            for (int a = this.f366a.m392a() - 1; a >= 0; a--) {
                Object a2 = this.f366a.m394a(a, 0);
                i += a2 == null ? 0 : a2.hashCode();
            }
            return i;
        }

        public boolean isEmpty() {
            return this.f366a.m392a() == 0;
        }

        public Iterator<K> iterator() {
            return new MapCollections(this.f366a, 0);
        }

        public boolean remove(Object obj) {
            int a = this.f366a.m393a(obj);
            if (a < 0) {
                return false;
            }
            this.f366a.m396a(a);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            return MapCollections.m390b(this.f366a.m400b(), collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return MapCollections.m391c(this.f366a.m400b(), collection);
        }

        public int size() {
            return this.f366a.m392a();
        }

        public Object[] toArray() {
            return this.f366a.m401b(0);
        }

        public <T> T[] toArray(T[] tArr) {
            return this.f366a.m398a((Object[]) tArr, 0);
        }
    }

    /* renamed from: android.support.v4.c.f.d */
    final class MapCollections implements Iterator<Entry<K, V>>, Entry<K, V> {
        int f367a;
        int f368b;
        boolean f369c;
        final /* synthetic */ MapCollections f370d;

        MapCollections(MapCollections mapCollections) {
            this.f370d = mapCollections;
            this.f369c = false;
            this.f367a = mapCollections.m392a() - 1;
            this.f368b = -1;
        }

        public Entry<K, V> m422a() {
            this.f368b++;
            this.f369c = true;
            return this;
        }

        public final boolean equals(Object obj) {
            boolean z = true;
            if (!this.f369c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            } else if (!(obj instanceof Entry)) {
                return false;
            } else {
                Entry entry = (Entry) obj;
                if (!(ContainerHelpers.m417a(entry.getKey(), this.f370d.m394a(this.f368b, 0)) && ContainerHelpers.m417a(entry.getValue(), this.f370d.m394a(this.f368b, 1)))) {
                    z = false;
                }
                return z;
            }
        }

        public K getKey() {
            if (this.f369c) {
                return this.f370d.m394a(this.f368b, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public V getValue() {
            if (this.f369c) {
                return this.f370d.m394a(this.f368b, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public boolean hasNext() {
            return this.f368b < this.f367a;
        }

        public final int hashCode() {
            int i = 0;
            if (this.f369c) {
                Object a = this.f370d.m394a(this.f368b, 0);
                Object a2 = this.f370d.m394a(this.f368b, 1);
                int hashCode = a == null ? 0 : a.hashCode();
                if (a2 != null) {
                    i = a2.hashCode();
                }
                return i ^ hashCode;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public /* synthetic */ Object next() {
            return m422a();
        }

        public void remove() {
            if (this.f369c) {
                this.f370d.m396a(this.f368b);
                this.f368b--;
                this.f367a--;
                this.f369c = false;
                return;
            }
            throw new IllegalStateException();
        }

        public V setValue(V v) {
            if (this.f369c) {
                return this.f370d.m395a(this.f368b, (Object) v);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* renamed from: android.support.v4.c.f.e */
    final class MapCollections implements Collection<V> {
        final /* synthetic */ MapCollections f371a;

        MapCollections(MapCollections mapCollections) {
            this.f371a = mapCollections;
        }

        public boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            this.f371a.m402c();
        }

        public boolean contains(Object obj) {
            return this.f371a.m399b(obj) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isEmpty() {
            return this.f371a.m392a() == 0;
        }

        public Iterator<V> iterator() {
            return new MapCollections(this.f371a, 1);
        }

        public boolean remove(Object obj) {
            int b = this.f371a.m399b(obj);
            if (b < 0) {
                return false;
            }
            this.f371a.m396a(b);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            int i = 0;
            int a = this.f371a.m392a();
            boolean z = false;
            while (i < a) {
                if (collection.contains(this.f371a.m394a(i, 1))) {
                    this.f371a.m396a(i);
                    i--;
                    a--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        public boolean retainAll(Collection<?> collection) {
            int i = 0;
            int a = this.f371a.m392a();
            boolean z = false;
            while (i < a) {
                if (!collection.contains(this.f371a.m394a(i, 1))) {
                    this.f371a.m396a(i);
                    i--;
                    a--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        public int size() {
            return this.f371a.m392a();
        }

        public Object[] toArray() {
            return this.f371a.m401b(1);
        }

        public <T> T[] toArray(T[] tArr) {
            return this.f371a.m398a((Object[]) tArr, 1);
        }
    }

    MapCollections() {
    }

    public static <K, V> boolean m388a(Map<K, V> map, Collection<?> collection) {
        for (Object containsKey : collection) {
            if (!map.containsKey(containsKey)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean m389a(Set<T> set, Object obj) {
        boolean z = true;
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            if (!(set.size() == set2.size() && set.containsAll(set2))) {
                z = false;
            }
            return z;
        } catch (NullPointerException e) {
            return false;
        } catch (ClassCastException e2) {
            return false;
        }
    }

    public static <K, V> boolean m390b(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        for (Object remove : collection) {
            map.remove(remove);
        }
        return size != map.size();
    }

    public static <K, V> boolean m391c(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    protected abstract int m392a();

    protected abstract int m393a(Object obj);

    protected abstract Object m394a(int i, int i2);

    protected abstract V m395a(int i, V v);

    protected abstract void m396a(int i);

    protected abstract void m397a(K k, V v);

    public <T> T[] m398a(T[] tArr, int i) {
        int a = m392a();
        T[] tArr2 = tArr.length < a ? (Object[]) Array.newInstance(tArr.getClass().getComponentType(), a) : tArr;
        for (int i2 = 0; i2 < a; i2++) {
            tArr2[i2] = m394a(i2, i);
        }
        if (tArr2.length > a) {
            tArr2[a] = null;
        }
        return tArr2;
    }

    protected abstract int m399b(Object obj);

    protected abstract Map<K, V> m400b();

    public Object[] m401b(int i) {
        int a = m392a();
        Object[] objArr = new Object[a];
        for (int i2 = 0; i2 < a; i2++) {
            objArr[i2] = m394a(i2, i);
        }
        return objArr;
    }

    protected abstract void m402c();

    public Set<Entry<K, V>> m403d() {
        if (this.f351b == null) {
            this.f351b = new MapCollections(this);
        }
        return this.f351b;
    }

    public Set<K> m404e() {
        if (this.f352c == null) {
            this.f352c = new MapCollections(this);
        }
        return this.f352c;
    }

    public Collection<V> m405f() {
        if (this.f353d == null) {
            this.f353d = new MapCollections(this);
        }
        return this.f353d;
    }
}
