package android.support.v7.internal.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v7.internal.widget.AdapterViewICS.AdapterViewICS;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.Adapter;
import android.widget.SpinnerAdapter;

abstract class AbsSpinnerICS extends AdapterViewICS<SpinnerAdapter> {
    private DataSetObserver f1054E;
    SpinnerAdapter f1055a;
    int f1056b;
    int f1057c;
    boolean f1058d;
    int f1059e;
    int f1060f;
    int f1061g;
    int f1062h;
    final Rect f1063i;
    final C0048a f1064j;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        long f1025a;
        int f1026b;

        static {
            CREATOR = new AbsSpinnerICS();
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f1025a = parcel.readLong();
            this.f1026b = parcel.readInt();
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "AbsSpinner.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selectedId=" + this.f1025a + " position=" + this.f1026b + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeLong(this.f1025a);
            parcel.writeInt(this.f1026b);
        }
    }

    /* renamed from: android.support.v7.internal.widget.AbsSpinnerICS.a */
    class C0048a {
        final /* synthetic */ AbsSpinnerICS f1027a;
        private final SparseArray<View> f1028b;

        C0048a(AbsSpinnerICS absSpinnerICS) {
            this.f1027a = absSpinnerICS;
            this.f1028b = new SparseArray();
        }

        View m1853a(int i) {
            View view = (View) this.f1028b.get(i);
            if (view != null) {
                this.f1028b.delete(i);
            }
            return view;
        }

        void m1854a() {
            SparseArray sparseArray = this.f1028b;
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                View view = (View) sparseArray.valueAt(i);
                if (view != null) {
                    this.f1027a.removeDetachedView(view, true);
                }
            }
            sparseArray.clear();
        }

        public void m1855a(int i, View view) {
            this.f1028b.put(i, view);
        }
    }

    AbsSpinnerICS(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1059e = 0;
        this.f1060f = 0;
        this.f1061g = 0;
        this.f1062h = 0;
        this.f1063i = new Rect();
        this.f1064j = new C0048a(this);
        m1880o();
    }

    private void m1880o() {
        setFocusable(true);
        setWillNotDraw(false);
    }

    int m1881a(View view) {
        return view.getMeasuredHeight();
    }

    void m1882a() {
        this.u = false;
        this.p = false;
        removeAllViewsInLayout();
        this.B = -1;
        this.C = Long.MIN_VALUE;
        m1867c(-1);
        m1868d(-1);
        invalidate();
    }

    public void m1883a(int i) {
        m1868d(i);
        requestLayout();
        invalidate();
    }

    public void m1884a(SpinnerAdapter spinnerAdapter) {
        int i = -1;
        if (this.f1055a != null) {
            this.f1055a.unregisterDataSetObserver(this.f1054E);
            m1882a();
        }
        this.f1055a = spinnerAdapter;
        this.B = -1;
        this.C = Long.MIN_VALUE;
        if (this.f1055a != null) {
            this.A = this.z;
            this.z = this.f1055a.getCount();
            m1873i();
            this.f1054E = new AdapterViewICS(this);
            this.f1055a.registerDataSetObserver(this.f1054E);
            if (this.z > 0) {
                i = 0;
            }
            m1867c(i);
            m1868d(i);
            if (this.z == 0) {
                m1876l();
            }
        } else {
            m1873i();
            m1882a();
            m1876l();
        }
        requestLayout();
    }

    int m1885b(View view) {
        return view.getMeasuredWidth();
    }

    void m1886b() {
        int childCount = getChildCount();
        C0048a c0048a = this.f1064j;
        int i = this.k;
        for (int i2 = 0; i2 < childCount; i2++) {
            c0048a.m1855a(i + i2, getChildAt(i2));
        }
    }

    public View m1887c() {
        return (this.z <= 0 || this.x < 0) ? null : getChildAt(this.x - this.k);
    }

    public SpinnerAdapter m1888d() {
        return this.f1055a;
    }

    public /* synthetic */ Adapter m1889e() {
        return m1888d();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    protected void onMeasure(int i, int i2) {
        boolean z;
        int mode = MeasureSpec.getMode(i);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        Rect rect = this.f1063i;
        if (paddingLeft <= this.f1059e) {
            paddingLeft = this.f1059e;
        }
        rect.left = paddingLeft;
        this.f1063i.top = paddingTop > this.f1060f ? paddingTop : this.f1060f;
        this.f1063i.right = paddingRight > this.f1061g ? paddingRight : this.f1061g;
        this.f1063i.bottom = paddingBottom > this.f1062h ? paddingBottom : this.f1062h;
        if (this.u) {
            m1875k();
        }
        paddingTop = m1870f();
        if (paddingTop >= 0 && this.f1055a != null && paddingTop < this.f1055a.getCount()) {
            View a = this.f1064j.m1853a(paddingTop);
            if (a == null) {
                a = this.f1055a.getView(paddingTop, null, this);
            }
            if (a != null) {
                this.f1064j.m1855a(paddingTop, a);
            }
            if (a != null) {
                if (a.getLayoutParams() == null) {
                    this.f1058d = true;
                    a.setLayoutParams(generateDefaultLayoutParams());
                    this.f1058d = false;
                }
                measureChild(a, i, i2);
                paddingTop = (m1881a(a) + this.f1063i.top) + this.f1063i.bottom;
                paddingLeft = (m1885b(a) + this.f1063i.left) + this.f1063i.right;
                z = false;
                if (z) {
                    paddingTop = this.f1063i.top + this.f1063i.bottom;
                    if (mode == 0) {
                        paddingLeft = this.f1063i.left + this.f1063i.right;
                    }
                }
                setMeasuredDimension(resolveSize(Math.max(paddingLeft, getSuggestedMinimumWidth()), i), resolveSize(Math.max(paddingTop, getSuggestedMinimumHeight()), i2));
                this.f1056b = i2;
                this.f1057c = i;
            }
        }
        z = true;
        paddingLeft = 0;
        paddingTop = 0;
        if (z) {
            paddingTop = this.f1063i.top + this.f1063i.bottom;
            if (mode == 0) {
                paddingLeft = this.f1063i.left + this.f1063i.right;
            }
        }
        setMeasuredDimension(resolveSize(Math.max(paddingLeft, getSuggestedMinimumWidth()), i), resolveSize(Math.max(paddingTop, getSuggestedMinimumHeight()), i2));
        this.f1056b = i2;
        this.f1057c = i;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.f1025a >= 0) {
            this.u = true;
            this.p = true;
            this.n = savedState.f1025a;
            this.m = savedState.f1026b;
            this.q = 0;
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState() {
        Object savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1025a = m1871g();
        if (savedState.f1025a >= 0) {
            savedState.f1026b = m1870f();
        } else {
            savedState.f1026b = -1;
        }
        return savedState;
    }

    public void requestLayout() {
        if (!this.f1058d) {
            super.requestLayout();
        }
    }
}
