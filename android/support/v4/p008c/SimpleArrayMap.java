package android.support.v4.p008c;

import java.util.Map;

/* renamed from: android.support.v4.c.g */
public class SimpleArrayMap<K, V> {
    static Object[] f343b;
    static int f344c;
    static Object[] f345d;
    static int f346e;
    int[] f347f;
    Object[] f348g;
    int f349h;

    public SimpleArrayMap() {
        this.f347f = ContainerHelpers.f355a;
        this.f348g = ContainerHelpers.f357c;
        this.f349h = 0;
    }

    private static void m377a(int[] iArr, Object[] objArr, int i) {
        int i2;
        if (iArr.length == 8) {
            synchronized (ArrayMap.class) {
                if (f346e < 10) {
                    objArr[0] = f345d;
                    objArr[1] = iArr;
                    for (i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f345d = objArr;
                    f346e++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (ArrayMap.class) {
                if (f344c < 10) {
                    objArr[0] = f343b;
                    objArr[1] = iArr;
                    for (i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f343b = objArr;
                    f344c++;
                }
            }
        }
    }

    private void m378e(int i) {
        Object[] objArr;
        if (i == 8) {
            synchronized (ArrayMap.class) {
                if (f345d != null) {
                    objArr = f345d;
                    this.f348g = objArr;
                    f345d = (Object[]) objArr[0];
                    this.f347f = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f346e--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (ArrayMap.class) {
                if (f343b != null) {
                    objArr = f343b;
                    this.f348g = objArr;
                    f343b = (Object[]) objArr[0];
                    this.f347f = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f344c--;
                    return;
                }
            }
        }
        this.f347f = new int[i];
        this.f348g = new Object[(i << 1)];
    }

    int m379a() {
        int i = this.f349h;
        if (i == 0) {
            return -1;
        }
        int a = ContainerHelpers.m416a(this.f347f, i, 0);
        if (a < 0 || this.f348g[a << 1] == null) {
            return a;
        }
        int i2 = a + 1;
        while (i2 < i && this.f347f[i2] == 0) {
            if (this.f348g[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        a--;
        while (a >= 0 && this.f347f[a] == 0) {
            if (this.f348g[a << 1] == null) {
                return a;
            }
            a--;
        }
        return i2 ^ -1;
    }

    int m380a(Object obj) {
        int i = 1;
        int i2 = this.f349h * 2;
        Object[] objArr = this.f348g;
        if (obj == null) {
            while (i < i2) {
                if (objArr[i] == null) {
                    return i >> 1;
                }
                i += 2;
            }
        } else {
            while (i < i2) {
                if (obj.equals(objArr[i])) {
                    return i >> 1;
                }
                i += 2;
            }
        }
        return -1;
    }

    int m381a(Object obj, int i) {
        int i2 = this.f349h;
        if (i2 == 0) {
            return -1;
        }
        int a = ContainerHelpers.m416a(this.f347f, i2, i);
        if (a < 0 || obj.equals(this.f348g[a << 1])) {
            return a;
        }
        int i3 = a + 1;
        while (i3 < i2 && this.f347f[i3] == i) {
            if (obj.equals(this.f348g[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        a--;
        while (a >= 0 && this.f347f[a] == i) {
            if (obj.equals(this.f348g[a << 1])) {
                return a;
            }
            a--;
        }
        return i3 ^ -1;
    }

    public V m382a(int i, V v) {
        int i2 = (i << 1) + 1;
        V v2 = this.f348g[i2];
        this.f348g[i2] = v;
        return v2;
    }

    public void m383a(int i) {
        if (this.f347f.length < i) {
            Object obj = this.f347f;
            Object obj2 = this.f348g;
            m378e(i);
            if (this.f349h > 0) {
                System.arraycopy(obj, 0, this.f347f, 0, this.f349h);
                System.arraycopy(obj2, 0, this.f348g, 0, this.f349h << 1);
            }
            SimpleArrayMap.m377a(obj, obj2, this.f349h);
        }
    }

    public K m384b(int i) {
        return this.f348g[i << 1];
    }

    public V m385c(int i) {
        return this.f348g[(i << 1) + 1];
    }

    public void clear() {
        if (this.f349h != 0) {
            SimpleArrayMap.m377a(this.f347f, this.f348g, this.f349h);
            this.f347f = ContainerHelpers.f355a;
            this.f348g = ContainerHelpers.f357c;
            this.f349h = 0;
        }
    }

    public boolean containsKey(Object obj) {
        return obj == null ? m379a() >= 0 : m381a(obj, obj.hashCode()) >= 0;
    }

    public boolean containsValue(Object obj) {
        return m380a(obj) >= 0;
    }

    public V m386d(int i) {
        int i2 = 8;
        V v = this.f348g[(i << 1) + 1];
        if (this.f349h <= 1) {
            SimpleArrayMap.m377a(this.f347f, this.f348g, this.f349h);
            this.f347f = ContainerHelpers.f355a;
            this.f348g = ContainerHelpers.f357c;
            this.f349h = 0;
        } else if (this.f347f.length <= 8 || this.f349h >= this.f347f.length / 3) {
            this.f349h--;
            if (i < this.f349h) {
                System.arraycopy(this.f347f, i + 1, this.f347f, i, this.f349h - i);
                System.arraycopy(this.f348g, (i + 1) << 1, this.f348g, i << 1, (this.f349h - i) << 1);
            }
            this.f348g[this.f349h << 1] = null;
            this.f348g[(this.f349h << 1) + 1] = null;
        } else {
            if (this.f349h > 8) {
                i2 = this.f349h + (this.f349h >> 1);
            }
            Object obj = this.f347f;
            Object obj2 = this.f348g;
            m378e(i2);
            this.f349h--;
            if (i > 0) {
                System.arraycopy(obj, 0, this.f347f, 0, i);
                System.arraycopy(obj2, 0, this.f348g, 0, i << 1);
            }
            if (i < this.f349h) {
                System.arraycopy(obj, i + 1, this.f347f, i, this.f349h - i);
                System.arraycopy(obj2, (i + 1) << 1, this.f348g, i << 1, (this.f349h - i) << 1);
            }
        }
        return v;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (size() != map.size()) {
            return false;
        }
        int i = 0;
        while (i < this.f349h) {
            try {
                Object b = m384b(i);
                Object c = m385c(i);
                Object obj2 = map.get(b);
                if (c == null) {
                    if (obj2 != null || !map.containsKey(b)) {
                        return false;
                    }
                } else if (!c.equals(obj2)) {
                    return false;
                }
                i++;
            } catch (NullPointerException e) {
                return false;
            } catch (ClassCastException e2) {
                return false;
            }
        }
        return true;
    }

    public V get(Object obj) {
        int a = obj == null ? m379a() : m381a(obj, obj.hashCode());
        return a >= 0 ? this.f348g[(a << 1) + 1] : null;
    }

    public int hashCode() {
        int[] iArr = this.f347f;
        Object[] objArr = this.f348g;
        int i = this.f349h;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            i4 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return i4;
    }

    public boolean isEmpty() {
        return this.f349h <= 0;
    }

    public V put(K k, V v) {
        int a;
        int i;
        int i2 = 8;
        if (k == null) {
            a = m379a();
            i = 0;
        } else {
            i = k.hashCode();
            a = m381a((Object) k, i);
        }
        if (a >= 0) {
            int i3 = (a << 1) + 1;
            V v2 = this.f348g[i3];
            this.f348g[i3] = v;
            return v2;
        }
        a ^= -1;
        if (this.f349h >= this.f347f.length) {
            if (this.f349h >= 8) {
                i2 = this.f349h + (this.f349h >> 1);
            } else if (this.f349h < 4) {
                i2 = 4;
            }
            Object obj = this.f347f;
            Object obj2 = this.f348g;
            m378e(i2);
            if (this.f347f.length > 0) {
                System.arraycopy(obj, 0, this.f347f, 0, obj.length);
                System.arraycopy(obj2, 0, this.f348g, 0, obj2.length);
            }
            SimpleArrayMap.m377a(obj, obj2, this.f349h);
        }
        if (a < this.f349h) {
            System.arraycopy(this.f347f, a, this.f347f, a + 1, this.f349h - a);
            System.arraycopy(this.f348g, a << 1, this.f348g, (a + 1) << 1, (this.f349h - a) << 1);
        }
        this.f347f[a] = i;
        this.f348g[a << 1] = k;
        this.f348g[(a << 1) + 1] = v;
        this.f349h++;
        return null;
    }

    public V remove(Object obj) {
        int a = obj == null ? m379a() : m381a(obj, obj.hashCode());
        return a >= 0 ? m386d(a) : null;
    }

    public int size() {
        return this.f349h;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.f349h * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.f349h; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            SimpleArrayMap b = m384b(i);
            if (b != this) {
                stringBuilder.append(b);
            } else {
                stringBuilder.append("(this Map)");
            }
            stringBuilder.append('=');
            b = m385c(i);
            if (b != this) {
                stringBuilder.append(b);
            } else {
                stringBuilder.append("(this Map)");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
