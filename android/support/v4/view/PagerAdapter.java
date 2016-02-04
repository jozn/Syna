package android.support.v4.view;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: android.support.v4.view.s */
public abstract class PagerAdapter {
    private DataSetObservable f319a;

    public PagerAdapter() {
        this.f319a = new DataSetObservable();
    }

    public int m317a(Object obj) {
        return -1;
    }

    public Parcelable m318a() {
        return null;
    }

    public Object m319a(View view, int i) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public Object m320a(ViewGroup viewGroup, int i) {
        return m319a((View) viewGroup, i);
    }

    public void m321a(DataSetObserver dataSetObserver) {
        this.f319a.registerObserver(dataSetObserver);
    }

    public void m322a(Parcelable parcelable, ClassLoader classLoader) {
    }

    public void m323a(View view) {
    }

    public void m324a(View view, int i, Object obj) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void m325a(ViewGroup viewGroup) {
        m323a((View) viewGroup);
    }

    public void m326a(ViewGroup viewGroup, int i, Object obj) {
        m324a((View) viewGroup, i, obj);
    }

    public abstract boolean m327a(View view, Object obj);

    public abstract int m328b();

    public void m329b(DataSetObserver dataSetObserver) {
        this.f319a.unregisterObserver(dataSetObserver);
    }

    public void m330b(View view) {
    }

    public void m331b(View view, int i, Object obj) {
    }

    public void m332b(ViewGroup viewGroup) {
        m330b((View) viewGroup);
    }

    public void m333b(ViewGroup viewGroup, int i, Object obj) {
        m331b((View) viewGroup, i, obj);
    }

    public CharSequence m334c(int i) {
        return null;
    }

    public void m335c() {
        this.f319a.notifyChanged();
    }

    public float m336d(int i) {
        return 1.0f;
    }
}
