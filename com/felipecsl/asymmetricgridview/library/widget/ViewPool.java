package com.felipecsl.asymmetricgridview.library.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import java.util.Stack;

class ViewPool<T extends View> implements Parcelable {
    Stack<T> f1549a;
    PoolObjectFactory<T> f1550b;
    C0069a f1551c;

    /* renamed from: com.felipecsl.asymmetricgridview.library.widget.ViewPool.a */
    static class C0069a {
        int f1545a;
        int f1546b;
        int f1547c;
        int f1548d;

        C0069a() {
            this.f1545a = 0;
            this.f1546b = 0;
            this.f1547c = 0;
            this.f1548d = 0;
        }

        String m2332a(String str) {
            return String.format("%s: size %d, hits %d, misses %d, created %d", new Object[]{str, Integer.valueOf(this.f1545a), Integer.valueOf(this.f1546b), Integer.valueOf(this.f1547c), Integer.valueOf(this.f1548d)});
        }
    }

    ViewPool() {
        this.f1549a = new Stack();
        this.f1550b = null;
        this.f1551c = new C0069a();
    }

    ViewPool(PoolObjectFactory<T> poolObjectFactory) {
        this.f1549a = new Stack();
        this.f1550b = null;
        this.f1550b = poolObjectFactory;
    }

    T m2333a() {
        if (this.f1549a.size() > 0) {
            C0069a c0069a = this.f1551c;
            c0069a.f1546b++;
            c0069a = this.f1551c;
            c0069a.f1545a--;
            return (View) this.f1549a.pop();
        }
        c0069a = this.f1551c;
        c0069a.f1547c++;
        T b = this.f1550b != null ? this.f1550b.m2370b() : null;
        if (b == null) {
            return b;
        }
        C0069a c0069a2 = this.f1551c;
        c0069a2.f1548d++;
        return b;
    }

    String m2334a(String str) {
        return this.f1551c.m2332a(str);
    }

    void m2335a(T t) {
        this.f1549a.push(t);
        C0069a c0069a = this.f1551c;
        c0069a.f1545a++;
    }

    void m2336b() {
        this.f1551c = new C0069a();
        this.f1549a.clear();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }
}
