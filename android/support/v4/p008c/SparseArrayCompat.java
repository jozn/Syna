package android.support.v4.p008c;

/* renamed from: android.support.v4.c.h */
public class SparseArrayCompat<E> implements Cloneable {
    private static final Object f372a;
    private boolean f373b;
    private int[] f374c;
    private Object[] f375d;
    private int f376e;

    static {
        f372a = new Object();
    }

    public SparseArrayCompat() {
        this(10);
    }

    public SparseArrayCompat(int i) {
        this.f373b = false;
        if (i == 0) {
            this.f374c = ContainerHelpers.f355a;
            this.f375d = ContainerHelpers.f357c;
        } else {
            int a = ContainerHelpers.m415a(i);
            this.f374c = new int[a];
            this.f375d = new Object[a];
        }
        this.f376e = 0;
    }

    private void m423d() {
        int i = this.f376e;
        int[] iArr = this.f374c;
        Object[] objArr = this.f375d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f372a) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f373b = false;
        this.f376e = i2;
    }

    public SparseArrayCompat<E> m424a() {
        try {
            SparseArrayCompat<E> sparseArrayCompat = (SparseArrayCompat) super.clone();
            try {
                sparseArrayCompat.f374c = (int[]) this.f374c.clone();
                sparseArrayCompat.f375d = (Object[]) this.f375d.clone();
                return sparseArrayCompat;
            } catch (CloneNotSupportedException e) {
                return sparseArrayCompat;
            }
        } catch (CloneNotSupportedException e2) {
            return null;
        }
    }

    public E m425a(int i) {
        return m426a(i, null);
    }

    public E m426a(int i, E e) {
        int a = ContainerHelpers.m416a(this.f374c, this.f376e, i);
        return (a < 0 || this.f375d[a] == f372a) ? e : this.f375d[a];
    }

    public int m427b() {
        if (this.f373b) {
            m423d();
        }
        return this.f376e;
    }

    public void m428b(int i) {
        int a = ContainerHelpers.m416a(this.f374c, this.f376e, i);
        if (a >= 0 && this.f375d[a] != f372a) {
            this.f375d[a] = f372a;
            this.f373b = true;
        }
    }

    public void m429b(int i, E e) {
        int a = ContainerHelpers.m416a(this.f374c, this.f376e, i);
        if (a >= 0) {
            this.f375d[a] = e;
            return;
        }
        a ^= -1;
        if (a >= this.f376e || this.f375d[a] != f372a) {
            if (this.f373b && this.f376e >= this.f374c.length) {
                m423d();
                a = ContainerHelpers.m416a(this.f374c, this.f376e, i) ^ -1;
            }
            if (this.f376e >= this.f374c.length) {
                int a2 = ContainerHelpers.m415a(this.f376e + 1);
                Object obj = new int[a2];
                Object obj2 = new Object[a2];
                System.arraycopy(this.f374c, 0, obj, 0, this.f374c.length);
                System.arraycopy(this.f375d, 0, obj2, 0, this.f375d.length);
                this.f374c = obj;
                this.f375d = obj2;
            }
            if (this.f376e - a != 0) {
                System.arraycopy(this.f374c, a, this.f374c, a + 1, this.f376e - a);
                System.arraycopy(this.f375d, a, this.f375d, a + 1, this.f376e - a);
            }
            this.f374c[a] = i;
            this.f375d[a] = e;
            this.f376e++;
            return;
        }
        this.f374c[a] = i;
        this.f375d[a] = e;
    }

    public void m430c() {
        int i = this.f376e;
        Object[] objArr = this.f375d;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f376e = 0;
        this.f373b = false;
    }

    public void m431c(int i) {
        m428b(i);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return m424a();
    }

    public int m432d(int i) {
        if (this.f373b) {
            m423d();
        }
        return this.f374c[i];
    }

    public E m433e(int i) {
        if (this.f373b) {
            m423d();
        }
        return this.f375d[i];
    }

    public String toString() {
        if (m427b() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.f376e * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.f376e; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(m432d(i));
            stringBuilder.append('=');
            SparseArrayCompat e = m433e(i);
            if (e != this) {
                stringBuilder.append(e);
            } else {
                stringBuilder.append("(this Map)");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
