package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.p007b.ParcelableCompat;
import android.support.v4.view.p009a.AccessibilityNodeInfoCompat;
import android.support.v4.view.p009a.AccessibilityRecordCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

public class ViewPager extends ViewGroup {
    private static final int[] f487a;
    private static final C0020i ah;
    private static final Comparator<C0015b> f488c;
    private static final Interpolator f489d;
    private boolean f490A;
    private boolean f491B;
    private int f492C;
    private int f493D;
    private int f494E;
    private float f495F;
    private float f496G;
    private float f497H;
    private float f498I;
    private int f499J;
    private VelocityTracker f500K;
    private int f501L;
    private int f502M;
    private int f503N;
    private int f504O;
    private boolean f505P;
    private long f506Q;
    private EdgeEffectCompat f507R;
    private EdgeEffectCompat f508S;
    private boolean f509T;
    private boolean f510U;
    private boolean f511V;
    private int f512W;
    private C0010e aa;
    private C0010e ab;
    private C0009d ac;
    private C0017f ad;
    private Method ae;
    private int af;
    private ArrayList<View> ag;
    private final Runnable ai;
    private int aj;
    private int f513b;
    private final ArrayList<C0015b> f514e;
    private final C0015b f515f;
    private final Rect f516g;
    private PagerAdapter f517h;
    private int f518i;
    private int f519j;
    private Parcelable f520k;
    private ClassLoader f521l;
    private Scroller f522m;
    private C0018g f523n;
    private int f524o;
    private Drawable f525p;
    private int f526q;
    private int f527r;
    private float f528s;
    private float f529t;
    private int f530u;
    private int f531v;
    private boolean f532w;
    private boolean f533x;
    private boolean f534y;
    private int f535z;

    /* renamed from: android.support.v4.view.ViewPager.a */
    interface C0007a {
    }

    /* renamed from: android.support.v4.view.ViewPager.d */
    interface C0009d {
        void m528a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2);
    }

    /* renamed from: android.support.v4.view.ViewPager.e */
    public interface C0010e {
        void m529a(int i);

        void m530a(int i, float f, int i2);

        void m531b(int i);
    }

    public static class LayoutParams extends android.view.ViewGroup.LayoutParams {
        public boolean f468a;
        public int f469b;
        float f470c;
        boolean f471d;
        int f472e;
        int f473f;

        public LayoutParams() {
            super(-1, -1);
            this.f470c = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f470c = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.f487a);
            this.f469b = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int f474a;
        Parcelable f475b;
        ClassLoader f476c;

        static {
            CREATOR = ParcelableCompat.m373a(new am());
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            if (classLoader == null) {
                classLoader = getClass().getClassLoader();
            }
            this.f474a = parcel.readInt();
            this.f475b = parcel.readParcelable(classLoader);
            this.f476c = classLoader;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f474a + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f474a);
            parcel.writeParcelable(this.f475b, i);
        }
    }

    /* renamed from: android.support.v4.view.ViewPager.b */
    static class C0015b {
        Object f477a;
        int f478b;
        boolean f479c;
        float f480d;
        float f481e;

        C0015b() {
        }
    }

    /* renamed from: android.support.v4.view.ViewPager.c */
    class C0016c extends AccessibilityDelegateCompat {
        final /* synthetic */ ViewPager f485b;

        C0016c(ViewPager viewPager) {
            this.f485b = viewPager;
        }

        private boolean m549b() {
            return this.f485b.f517h != null && this.f485b.f517h.m328b() > 1;
        }

        public void m550a(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.m542a(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.m781b(ViewPager.class.getName());
            accessibilityNodeInfoCompat.m799i(m549b());
            if (this.f485b.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.m772a(4096);
            }
            if (this.f485b.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.m772a(8192);
            }
        }

        public boolean m551a(View view, int i, Bundle bundle) {
            if (super.m544a(view, i, bundle)) {
                return true;
            }
            switch (i) {
                case 4096:
                    if (!this.f485b.canScrollHorizontally(1)) {
                        return false;
                    }
                    this.f485b.setCurrentItem(this.f485b.f518i + 1);
                    return true;
                case 8192:
                    if (!this.f485b.canScrollHorizontally(-1)) {
                        return false;
                    }
                    this.f485b.setCurrentItem(this.f485b.f518i - 1);
                    return true;
                default:
                    return false;
            }
        }

        public void m552d(View view, AccessibilityEvent accessibilityEvent) {
            super.m548d(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            AccessibilityRecordCompat a = AccessibilityRecordCompat.m895a();
            a.m897a(m549b());
            if (accessibilityEvent.getEventType() == 4096 && this.f485b.f517h != null) {
                a.m896a(this.f485b.f517h.m328b());
                a.m898b(this.f485b.f518i);
                a.m899c(this.f485b.f518i);
            }
        }
    }

    /* renamed from: android.support.v4.view.ViewPager.f */
    public interface C0017f {
        void m553a(View view, float f);
    }

    /* renamed from: android.support.v4.view.ViewPager.g */
    private class C0018g extends DataSetObserver {
        final /* synthetic */ ViewPager f486a;

        private C0018g(ViewPager viewPager) {
            this.f486a = viewPager;
        }

        public void onChanged() {
            this.f486a.m601d();
        }

        public void onInvalidated() {
            this.f486a.m601d();
        }
    }

    /* renamed from: android.support.v4.view.ViewPager.h */
    public static class C0019h implements C0010e {
        public void m554a(int i) {
        }

        public void m555a(int i, float f, int i2) {
        }

        public void m556b(int i) {
        }
    }

    /* renamed from: android.support.v4.view.ViewPager.i */
    static class C0020i implements Comparator<View> {
        C0020i() {
        }

        public int m557a(View view, View view2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
            return layoutParams.f468a != layoutParams2.f468a ? layoutParams.f468a ? 1 : -1 : layoutParams.f472e - layoutParams2.f472e;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m557a((View) obj, (View) obj2);
        }
    }

    static {
        f487a = new int[]{16842931};
        f488c = new aj();
        f489d = new ak();
        ah = new C0020i();
    }

    public ViewPager(Context context) {
        super(context);
        this.f514e = new ArrayList();
        this.f515f = new C0015b();
        this.f516g = new Rect();
        this.f519j = -1;
        this.f520k = null;
        this.f521l = null;
        this.f528s = -3.4028235E38f;
        this.f529t = Float.MAX_VALUE;
        this.f535z = 1;
        this.f499J = -1;
        this.f509T = true;
        this.f510U = false;
        this.ai = new al(this);
        this.aj = 0;
        m585a();
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f514e = new ArrayList();
        this.f515f = new C0015b();
        this.f516g = new Rect();
        this.f519j = -1;
        this.f520k = null;
        this.f521l = null;
        this.f528s = -3.4028235E38f;
        this.f529t = Float.MAX_VALUE;
        this.f535z = 1;
        this.f499J = -1;
        this.f509T = true;
        this.f510U = false;
        this.ai = new al(this);
        this.aj = 0;
        m585a();
    }

    private int m558a(int i, float f, int i2, int i3) {
        if (Math.abs(i3) <= this.f503N || Math.abs(i2) <= this.f501L) {
            i = (int) ((i >= this.f518i ? 0.4f : 0.6f) + (((float) i) + f));
        } else if (i2 <= 0) {
            i++;
        }
        if (this.f514e.size() <= 0) {
            return i;
        }
        return Math.max(((C0015b) this.f514e.get(0)).f478b, Math.min(i, ((C0015b) this.f514e.get(this.f514e.size() - 1)).f478b));
    }

    private Rect m559a(Rect rect, View view) {
        Rect rect2 = rect == null ? new Rect() : rect;
        if (view == null) {
            rect2.set(0, 0, 0, 0);
            return rect2;
        }
        rect2.left = view.getLeft();
        rect2.right = view.getRight();
        rect2.top = view.getTop();
        rect2.bottom = view.getBottom();
        ViewPager parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = parent;
            rect2.left += viewGroup.getLeft();
            rect2.right += viewGroup.getRight();
            rect2.top += viewGroup.getTop();
            rect2.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect2;
    }

    private void m561a(int i, int i2, int i3, int i4) {
        if (i2 <= 0 || this.f514e.isEmpty()) {
            C0015b b = m595b(this.f518i);
            int min = (int) ((b != null ? Math.min(b.f481e, this.f529t) : 0.0f) * ((float) ((i - getPaddingLeft()) - getPaddingRight())));
            if (min != getScrollX()) {
                m568b(false);
                scrollTo(min, getScrollY());
                return;
            }
            return;
        }
        int paddingLeft = (int) (((float) (((i - getPaddingLeft()) - getPaddingRight()) + i3)) * (((float) getScrollX()) / ((float) (((i2 - getPaddingLeft()) - getPaddingRight()) + i4))));
        scrollTo(paddingLeft, getScrollY());
        if (!this.f522m.isFinished()) {
            this.f522m.startScroll(paddingLeft, 0, (int) (m595b(this.f518i).f481e * ((float) i)), 0, this.f522m.getDuration() - this.f522m.timePassed());
        }
    }

    private void m562a(int i, boolean z, int i2, boolean z2) {
        int max;
        C0015b b = m595b(i);
        if (b != null) {
            max = (int) (Math.max(this.f528s, Math.min(b.f481e, this.f529t)) * ((float) m577m()));
        } else {
            max = 0;
        }
        if (z) {
            m588a(max, 0, i2);
            if (z2 && this.aa != null) {
                this.aa.m529a(i);
            }
            if (z2 && this.ab != null) {
                this.ab.m529a(i);
                return;
            }
            return;
        }
        if (z2 && this.aa != null) {
            this.aa.m529a(i);
        }
        if (z2 && this.ab != null) {
            this.ab.m529a(i);
        }
        m568b(false);
        scrollTo(max, 0);
        m574e(max);
    }

    private void m563a(C0015b c0015b, int i, C0015b c0015b2) {
        float f;
        int i2;
        C0015b c0015b3;
        int i3;
        int b = this.f517h.m328b();
        int m = m577m();
        float f2 = m > 0 ? ((float) this.f524o) / ((float) m) : 0.0f;
        if (c0015b2 != null) {
            m = c0015b2.f478b;
            int i4;
            if (m < c0015b.f478b) {
                f = (c0015b2.f481e + c0015b2.f480d) + f2;
                i4 = m + 1;
                i2 = 0;
                while (i4 <= c0015b.f478b && i2 < this.f514e.size()) {
                    c0015b3 = (C0015b) this.f514e.get(i2);
                    while (i4 > c0015b3.f478b && i2 < this.f514e.size() - 1) {
                        i2++;
                        c0015b3 = (C0015b) this.f514e.get(i2);
                    }
                    while (i4 < c0015b3.f478b) {
                        f += this.f517h.m336d(i4) + f2;
                        i4++;
                    }
                    c0015b3.f481e = f;
                    f += c0015b3.f480d + f2;
                    i4++;
                }
            } else if (m > c0015b.f478b) {
                i2 = this.f514e.size() - 1;
                f = c0015b2.f481e;
                i4 = m - 1;
                while (i4 >= c0015b.f478b && i2 >= 0) {
                    c0015b3 = (C0015b) this.f514e.get(i2);
                    while (i4 < c0015b3.f478b && i2 > 0) {
                        i2--;
                        c0015b3 = (C0015b) this.f514e.get(i2);
                    }
                    while (i4 > c0015b3.f478b) {
                        f -= this.f517h.m336d(i4) + f2;
                        i4--;
                    }
                    f -= c0015b3.f480d + f2;
                    c0015b3.f481e = f;
                    i4--;
                }
            }
        }
        int size = this.f514e.size();
        float f3 = c0015b.f481e;
        i2 = c0015b.f478b - 1;
        this.f528s = c0015b.f478b == 0 ? c0015b.f481e : -3.4028235E38f;
        this.f529t = c0015b.f478b == b + -1 ? (c0015b.f481e + c0015b.f480d) - 1.0f : Float.MAX_VALUE;
        for (i3 = i - 1; i3 >= 0; i3--) {
            c0015b3 = (C0015b) this.f514e.get(i3);
            f = f3;
            while (i2 > c0015b3.f478b) {
                f -= this.f517h.m336d(i2) + f2;
                i2--;
            }
            f3 = f - (c0015b3.f480d + f2);
            c0015b3.f481e = f3;
            if (c0015b3.f478b == 0) {
                this.f528s = f3;
            }
            i2--;
        }
        f3 = (c0015b.f481e + c0015b.f480d) + f2;
        i2 = c0015b.f478b + 1;
        for (i3 = i + 1; i3 < size; i3++) {
            c0015b3 = (C0015b) this.f514e.get(i3);
            f = f3;
            while (i2 < c0015b3.f478b) {
                f = (this.f517h.m336d(i2) + f2) + f;
                i2++;
            }
            if (c0015b3.f478b == b - 1) {
                this.f529t = (c0015b3.f480d + f) - 1.0f;
            }
            c0015b3.f481e = f;
            f3 = f + (c0015b3.f480d + f2);
            i2++;
        }
        this.f510U = false;
    }

    private void m565a(MotionEvent motionEvent) {
        int b = MotionEventCompat.m1069b(motionEvent);
        if (MotionEventCompat.m1070b(motionEvent, b) == this.f499J) {
            b = b == 0 ? 1 : 0;
            this.f495F = MotionEventCompat.m1071c(motionEvent, b);
            this.f499J = MotionEventCompat.m1070b(motionEvent, b);
            if (this.f500K != null) {
                this.f500K.clear();
            }
        }
    }

    private boolean m566a(float f, float f2) {
        return (f < ((float) this.f493D) && f2 > 0.0f) || (f > ((float) (getWidth() - this.f493D)) && f2 < 0.0f);
    }

    private void m568b(boolean z) {
        int scrollX;
        boolean z2 = this.aj == 2;
        if (z2) {
            m573e(false);
            this.f522m.abortAnimation();
            scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.f522m.getCurrX();
            int currY = this.f522m.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                scrollTo(currX, currY);
            }
        }
        this.f534y = false;
        boolean z3 = z2;
        for (scrollX = 0; scrollX < this.f514e.size(); scrollX++) {
            C0015b c0015b = (C0015b) this.f514e.get(scrollX);
            if (c0015b.f479c) {
                c0015b.f479c = false;
                z3 = true;
            }
        }
        if (!z3) {
            return;
        }
        if (z) {
            ViewCompat.m1143a((View) this, this.ai);
        } else {
            this.ai.run();
        }
    }

    private void m569c(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewCompat.m1140a(getChildAt(i), z ? 2 : 0, null);
        }
    }

    private boolean m570c(float f) {
        boolean z;
        float f2;
        boolean z2 = true;
        boolean z3 = false;
        float f3 = this.f495F - f;
        this.f495F = f;
        float scrollX = ((float) getScrollX()) + f3;
        int m = m577m();
        float f4 = ((float) m) * this.f528s;
        float f5 = ((float) m) * this.f529t;
        C0015b c0015b = (C0015b) this.f514e.get(0);
        C0015b c0015b2 = (C0015b) this.f514e.get(this.f514e.size() - 1);
        if (c0015b.f478b != 0) {
            f4 = c0015b.f481e * ((float) m);
            z = false;
        } else {
            z = true;
        }
        if (c0015b2.f478b != this.f517h.m328b() - 1) {
            f2 = c0015b2.f481e * ((float) m);
            z2 = false;
        } else {
            f2 = f5;
        }
        if (scrollX < f4) {
            if (z) {
                z3 = this.f507R.m1340a(Math.abs(f4 - scrollX) / ((float) m));
            }
        } else if (scrollX > f2) {
            if (z2) {
                z3 = this.f508S.m1340a(Math.abs(scrollX - f2) / ((float) m));
            }
            f4 = f2;
        } else {
            f4 = scrollX;
        }
        this.f495F += f4 - ((float) ((int) f4));
        scrollTo((int) f4, getScrollY());
        m574e((int) f4);
        return z3;
    }

    private void m571d(int i) {
        if (this.aj != i) {
            this.aj = i;
            if (this.ad != null) {
                m569c(i != 0);
            }
            if (this.aa != null) {
                this.aa.m531b(i);
            }
        }
    }

    private void m572d(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private void m573e(boolean z) {
        if (this.f533x != z) {
            this.f533x = z;
        }
    }

    private boolean m574e(int i) {
        if (this.f514e.size() == 0) {
            this.f511V = false;
            m587a(0, 0.0f, 0);
            if (this.f511V) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        C0015b o = m579o();
        int m = m577m();
        int i2 = this.f524o + m;
        float f = ((float) this.f524o) / ((float) m);
        int i3 = o.f478b;
        float f2 = ((((float) i) / ((float) m)) - o.f481e) / (o.f480d + f);
        m = (int) (((float) i2) * f2);
        this.f511V = false;
        m587a(i3, f2, m);
        if (this.f511V) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private void m576l() {
        int i = 0;
        while (i < getChildCount()) {
            if (!((LayoutParams) getChildAt(i).getLayoutParams()).f468a) {
                removeViewAt(i);
                i--;
            }
            i++;
        }
    }

    private int m577m() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void m578n() {
        if (this.af != 0) {
            if (this.ag == null) {
                this.ag = new ArrayList();
            } else {
                this.ag.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.ag.add(getChildAt(i));
            }
            Collections.sort(this.ag, ah);
        }
    }

    private C0015b m579o() {
        int m = m577m();
        float scrollX = m > 0 ? ((float) getScrollX()) / ((float) m) : 0.0f;
        float f = m > 0 ? ((float) this.f524o) / ((float) m) : 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i = -1;
        int i2 = 0;
        Object obj = 1;
        C0015b c0015b = null;
        while (i2 < this.f514e.size()) {
            int i3;
            C0015b c0015b2;
            C0015b c0015b3 = (C0015b) this.f514e.get(i2);
            C0015b c0015b4;
            if (obj != null || c0015b3.f478b == i + 1) {
                c0015b4 = c0015b3;
                i3 = i2;
                c0015b2 = c0015b4;
            } else {
                c0015b3 = this.f515f;
                c0015b3.f481e = (f2 + f3) + f;
                c0015b3.f478b = i + 1;
                c0015b3.f480d = this.f517h.m336d(c0015b3.f478b);
                c0015b4 = c0015b3;
                i3 = i2 - 1;
                c0015b2 = c0015b4;
            }
            f2 = c0015b2.f481e;
            f3 = (c0015b2.f480d + f2) + f;
            if (obj == null && scrollX < f2) {
                return c0015b;
            }
            if (scrollX < f3 || i3 == this.f514e.size() - 1) {
                return c0015b2;
            }
            f3 = f2;
            i = c0015b2.f478b;
            obj = null;
            f2 = c0015b2.f480d;
            c0015b = c0015b2;
            i2 = i3 + 1;
        }
        return c0015b;
    }

    private void m580p() {
        this.f490A = false;
        this.f491B = false;
        if (this.f500K != null) {
            this.f500K.recycle();
            this.f500K = null;
        }
    }

    float m581a(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    C0015b m582a(int i, int i2) {
        C0015b c0015b = new C0015b();
        c0015b.f478b = i;
        c0015b.f477a = this.f517h.m320a((ViewGroup) this, i);
        c0015b.f480d = this.f517h.m336d(i);
        if (i2 < 0 || i2 >= this.f514e.size()) {
            this.f514e.add(c0015b);
        } else {
            this.f514e.add(i2, c0015b);
        }
        return c0015b;
    }

    C0015b m583a(View view) {
        for (int i = 0; i < this.f514e.size(); i++) {
            C0015b c0015b = (C0015b) this.f514e.get(i);
            if (this.f517h.m327a(view, c0015b.f477a)) {
                return c0015b;
            }
        }
        return null;
    }

    C0010e m584a(C0010e c0010e) {
        C0010e c0010e2 = this.ab;
        this.ab = c0010e;
        return c0010e2;
    }

    void m585a() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.f522m = new Scroller(context, f489d);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.f494E = af.m923a(viewConfiguration);
        this.f501L = (int) (400.0f * f);
        this.f502M = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f507R = new EdgeEffectCompat(context);
        this.f508S = new EdgeEffectCompat(context);
        this.f503N = (int) (25.0f * f);
        this.f504O = (int) (2.0f * f);
        this.f492C = (int) (16.0f * f);
        ViewCompat.m1142a((View) this, new C0016c(this));
        if (ViewCompat.m1147c(this) == 0) {
            ViewCompat.m1148c(this, 1);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void m586a(int r19) {
        /*
        r18 = this;
        r3 = 0;
        r2 = 2;
        r0 = r18;
        r4 = r0.f518i;
        r0 = r19;
        if (r4 == r0) goto L_0x033f;
    L_0x000a:
        r0 = r18;
        r2 = r0.f518i;
        r0 = r19;
        if (r2 >= r0) goto L_0x0030;
    L_0x0012:
        r2 = 66;
    L_0x0014:
        r0 = r18;
        r3 = r0.f518i;
        r0 = r18;
        r3 = r0.m595b(r3);
        r0 = r19;
        r1 = r18;
        r1.f518i = r0;
        r4 = r3;
        r3 = r2;
    L_0x0026:
        r0 = r18;
        r2 = r0.f517h;
        if (r2 != 0) goto L_0x0033;
    L_0x002c:
        r18.m578n();
    L_0x002f:
        return;
    L_0x0030:
        r2 = 17;
        goto L_0x0014;
    L_0x0033:
        r0 = r18;
        r2 = r0.f534y;
        if (r2 == 0) goto L_0x003d;
    L_0x0039:
        r18.m578n();
        goto L_0x002f;
    L_0x003d:
        r2 = r18.getWindowToken();
        if (r2 == 0) goto L_0x002f;
    L_0x0043:
        r0 = r18;
        r2 = r0.f517h;
        r0 = r18;
        r2.m325a(r0);
        r0 = r18;
        r2 = r0.f535z;
        r5 = 0;
        r0 = r18;
        r6 = r0.f518i;
        r6 = r6 - r2;
        r11 = java.lang.Math.max(r5, r6);
        r0 = r18;
        r5 = r0.f517h;
        r12 = r5.m328b();
        r5 = r12 + -1;
        r0 = r18;
        r6 = r0.f518i;
        r2 = r2 + r6;
        r13 = java.lang.Math.min(r5, r2);
        r0 = r18;
        r2 = r0.f513b;
        if (r12 == r2) goto L_0x00da;
    L_0x0073:
        r2 = r18.getResources();	 Catch:{ NotFoundException -> 0x00d0 }
        r3 = r18.getId();	 Catch:{ NotFoundException -> 0x00d0 }
        r2 = r2.getResourceName(r3);	 Catch:{ NotFoundException -> 0x00d0 }
    L_0x007f:
        r3 = new java.lang.IllegalStateException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ";
        r4 = r4.append(r5);
        r0 = r18;
        r5 = r0.f513b;
        r4 = r4.append(r5);
        r5 = ", found: ";
        r4 = r4.append(r5);
        r4 = r4.append(r12);
        r5 = " Pager id: ";
        r4 = r4.append(r5);
        r2 = r4.append(r2);
        r4 = " Pager class: ";
        r2 = r2.append(r4);
        r4 = r18.getClass();
        r2 = r2.append(r4);
        r4 = " Problematic adapter: ";
        r2 = r2.append(r4);
        r0 = r18;
        r4 = r0.f517h;
        r4 = r4.getClass();
        r2 = r2.append(r4);
        r2 = r2.toString();
        r3.<init>(r2);
        throw r3;
    L_0x00d0:
        r2 = move-exception;
        r2 = r18.getId();
        r2 = java.lang.Integer.toHexString(r2);
        goto L_0x007f;
    L_0x00da:
        r6 = 0;
        r2 = 0;
        r5 = r2;
    L_0x00dd:
        r0 = r18;
        r2 = r0.f514e;
        r2 = r2.size();
        if (r5 >= r2) goto L_0x033c;
    L_0x00e7:
        r0 = r18;
        r2 = r0.f514e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.C0015b) r2;
        r7 = r2.f478b;
        r0 = r18;
        r8 = r0.f518i;
        if (r7 < r8) goto L_0x01cf;
    L_0x00f9:
        r7 = r2.f478b;
        r0 = r18;
        r8 = r0.f518i;
        if (r7 != r8) goto L_0x033c;
    L_0x0101:
        if (r2 != 0) goto L_0x0339;
    L_0x0103:
        if (r12 <= 0) goto L_0x0339;
    L_0x0105:
        r0 = r18;
        r2 = r0.f518i;
        r0 = r18;
        r2 = r0.m582a(r2, r5);
        r10 = r2;
    L_0x0110:
        if (r10 == 0) goto L_0x0180;
    L_0x0112:
        r9 = 0;
        r8 = r5 + -1;
        if (r8 < 0) goto L_0x01d4;
    L_0x0117:
        r0 = r18;
        r2 = r0.f514e;
        r2 = r2.get(r8);
        r2 = (android.support.v4.view.ViewPager.C0015b) r2;
    L_0x0121:
        r14 = r18.m577m();
        if (r14 > 0) goto L_0x01d7;
    L_0x0127:
        r6 = 0;
    L_0x0128:
        r0 = r18;
        r7 = r0.f518i;
        r7 = r7 + -1;
        r16 = r7;
        r7 = r9;
        r9 = r16;
        r17 = r8;
        r8 = r5;
        r5 = r17;
    L_0x0138:
        if (r9 < 0) goto L_0x0142;
    L_0x013a:
        r15 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1));
        if (r15 < 0) goto L_0x0216;
    L_0x013e:
        if (r9 >= r11) goto L_0x0216;
    L_0x0140:
        if (r2 != 0) goto L_0x01e6;
    L_0x0142:
        r6 = r10.f480d;
        r9 = r8 + 1;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x017b;
    L_0x014c:
        r0 = r18;
        r2 = r0.f514e;
        r2 = r2.size();
        if (r9 >= r2) goto L_0x024c;
    L_0x0156:
        r0 = r18;
        r2 = r0.f514e;
        r2 = r2.get(r9);
        r2 = (android.support.v4.view.ViewPager.C0015b) r2;
        r7 = r2;
    L_0x0161:
        if (r14 > 0) goto L_0x024f;
    L_0x0163:
        r2 = 0;
        r5 = r2;
    L_0x0165:
        r0 = r18;
        r2 = r0.f518i;
        r2 = r2 + 1;
        r16 = r2;
        r2 = r7;
        r7 = r9;
        r9 = r16;
    L_0x0171:
        if (r9 >= r12) goto L_0x017b;
    L_0x0173:
        r11 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1));
        if (r11 < 0) goto L_0x029a;
    L_0x0177:
        if (r9 <= r13) goto L_0x029a;
    L_0x0179:
        if (r2 != 0) goto L_0x025c;
    L_0x017b:
        r0 = r18;
        r0.m563a(r10, r8, r4);
    L_0x0180:
        r0 = r18;
        r4 = r0.f517h;
        r0 = r18;
        r5 = r0.f518i;
        if (r10 == 0) goto L_0x02e8;
    L_0x018a:
        r2 = r10.f477a;
    L_0x018c:
        r0 = r18;
        r4.m333b(r0, r5, r2);
        r0 = r18;
        r2 = r0.f517h;
        r0 = r18;
        r2.m332b(r0);
        r5 = r18.getChildCount();
        r2 = 0;
        r4 = r2;
    L_0x01a0:
        if (r4 >= r5) goto L_0x02eb;
    L_0x01a2:
        r0 = r18;
        r6 = r0.getChildAt(r4);
        r2 = r6.getLayoutParams();
        r2 = (android.support.v4.view.ViewPager.LayoutParams) r2;
        r2.f473f = r4;
        r7 = r2.f468a;
        if (r7 != 0) goto L_0x01cb;
    L_0x01b4:
        r7 = r2.f470c;
        r8 = 0;
        r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1));
        if (r7 != 0) goto L_0x01cb;
    L_0x01bb:
        r0 = r18;
        r6 = r0.m583a(r6);
        if (r6 == 0) goto L_0x01cb;
    L_0x01c3:
        r7 = r6.f480d;
        r2.f470c = r7;
        r6 = r6.f478b;
        r2.f472e = r6;
    L_0x01cb:
        r2 = r4 + 1;
        r4 = r2;
        goto L_0x01a0;
    L_0x01cf:
        r2 = r5 + 1;
        r5 = r2;
        goto L_0x00dd;
    L_0x01d4:
        r2 = 0;
        goto L_0x0121;
    L_0x01d7:
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = r10.f480d;
        r6 = r6 - r7;
        r7 = r18.getPaddingLeft();
        r7 = (float) r7;
        r15 = (float) r14;
        r7 = r7 / r15;
        r6 = r6 + r7;
        goto L_0x0128;
    L_0x01e6:
        r15 = r2.f478b;
        if (r9 != r15) goto L_0x0210;
    L_0x01ea:
        r15 = r2.f479c;
        if (r15 != 0) goto L_0x0210;
    L_0x01ee:
        r0 = r18;
        r15 = r0.f514e;
        r15.remove(r5);
        r0 = r18;
        r15 = r0.f517h;
        r2 = r2.f477a;
        r0 = r18;
        r15.m326a(r0, r9, r2);
        r5 = r5 + -1;
        r8 = r8 + -1;
        if (r5 < 0) goto L_0x0214;
    L_0x0206:
        r0 = r18;
        r2 = r0.f514e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.C0015b) r2;
    L_0x0210:
        r9 = r9 + -1;
        goto L_0x0138;
    L_0x0214:
        r2 = 0;
        goto L_0x0210;
    L_0x0216:
        if (r2 == 0) goto L_0x0230;
    L_0x0218:
        r15 = r2.f478b;
        if (r9 != r15) goto L_0x0230;
    L_0x021c:
        r2 = r2.f480d;
        r7 = r7 + r2;
        r5 = r5 + -1;
        if (r5 < 0) goto L_0x022e;
    L_0x0223:
        r0 = r18;
        r2 = r0.f514e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.C0015b) r2;
        goto L_0x0210;
    L_0x022e:
        r2 = 0;
        goto L_0x0210;
    L_0x0230:
        r2 = r5 + 1;
        r0 = r18;
        r2 = r0.m582a(r9, r2);
        r2 = r2.f480d;
        r7 = r7 + r2;
        r8 = r8 + 1;
        if (r5 < 0) goto L_0x024a;
    L_0x023f:
        r0 = r18;
        r2 = r0.f514e;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.ViewPager.C0015b) r2;
        goto L_0x0210;
    L_0x024a:
        r2 = 0;
        goto L_0x0210;
    L_0x024c:
        r7 = 0;
        goto L_0x0161;
    L_0x024f:
        r2 = r18.getPaddingRight();
        r2 = (float) r2;
        r5 = (float) r14;
        r2 = r2 / r5;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 + r5;
        r5 = r2;
        goto L_0x0165;
    L_0x025c:
        r11 = r2.f478b;
        if (r9 != r11) goto L_0x0332;
    L_0x0260:
        r11 = r2.f479c;
        if (r11 != 0) goto L_0x0332;
    L_0x0264:
        r0 = r18;
        r11 = r0.f514e;
        r11.remove(r7);
        r0 = r18;
        r11 = r0.f517h;
        r2 = r2.f477a;
        r0 = r18;
        r11.m326a(r0, r9, r2);
        r0 = r18;
        r2 = r0.f514e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x0298;
    L_0x0280:
        r0 = r18;
        r2 = r0.f514e;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.ViewPager.C0015b) r2;
    L_0x028a:
        r16 = r6;
        r6 = r2;
        r2 = r16;
    L_0x028f:
        r9 = r9 + 1;
        r16 = r2;
        r2 = r6;
        r6 = r16;
        goto L_0x0171;
    L_0x0298:
        r2 = 0;
        goto L_0x028a;
    L_0x029a:
        if (r2 == 0) goto L_0x02c1;
    L_0x029c:
        r11 = r2.f478b;
        if (r9 != r11) goto L_0x02c1;
    L_0x02a0:
        r2 = r2.f480d;
        r6 = r6 + r2;
        r7 = r7 + 1;
        r0 = r18;
        r2 = r0.f514e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x02bf;
    L_0x02af:
        r0 = r18;
        r2 = r0.f514e;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.ViewPager.C0015b) r2;
    L_0x02b9:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x02bf:
        r2 = 0;
        goto L_0x02b9;
    L_0x02c1:
        r0 = r18;
        r2 = r0.m582a(r9, r7);
        r7 = r7 + 1;
        r2 = r2.f480d;
        r6 = r6 + r2;
        r0 = r18;
        r2 = r0.f514e;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x02e6;
    L_0x02d6:
        r0 = r18;
        r2 = r0.f514e;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.ViewPager.C0015b) r2;
    L_0x02e0:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x02e6:
        r2 = 0;
        goto L_0x02e0;
    L_0x02e8:
        r2 = 0;
        goto L_0x018c;
    L_0x02eb:
        r18.m578n();
        r2 = r18.hasFocus();
        if (r2 == 0) goto L_0x002f;
    L_0x02f4:
        r2 = r18.findFocus();
        if (r2 == 0) goto L_0x0330;
    L_0x02fa:
        r0 = r18;
        r2 = r0.m596b(r2);
    L_0x0300:
        if (r2 == 0) goto L_0x030a;
    L_0x0302:
        r2 = r2.f478b;
        r0 = r18;
        r4 = r0.f518i;
        if (r2 == r4) goto L_0x002f;
    L_0x030a:
        r2 = 0;
    L_0x030b:
        r4 = r18.getChildCount();
        if (r2 >= r4) goto L_0x002f;
    L_0x0311:
        r0 = r18;
        r4 = r0.getChildAt(r2);
        r0 = r18;
        r5 = r0.m583a(r4);
        if (r5 == 0) goto L_0x032d;
    L_0x031f:
        r5 = r5.f478b;
        r0 = r18;
        r6 = r0.f518i;
        if (r5 != r6) goto L_0x032d;
    L_0x0327:
        r4 = r4.requestFocus(r3);
        if (r4 != 0) goto L_0x002f;
    L_0x032d:
        r2 = r2 + 1;
        goto L_0x030b;
    L_0x0330:
        r2 = 0;
        goto L_0x0300;
    L_0x0332:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x0339:
        r10 = r2;
        goto L_0x0110;
    L_0x033c:
        r2 = r6;
        goto L_0x0101;
    L_0x033f:
        r4 = r3;
        r3 = r2;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.a(int):void");
    }

    protected void m587a(int i, float f, int i2) {
        int paddingLeft;
        int paddingRight;
        int i3;
        if (this.f512W > 0) {
            int scrollX = getScrollX();
            paddingLeft = getPaddingLeft();
            paddingRight = getPaddingRight();
            int width = getWidth();
            int childCount = getChildCount();
            i3 = 0;
            while (i3 < childCount) {
                int i4;
                View childAt = getChildAt(i3);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.f468a) {
                    int max;
                    switch (layoutParams.f469b & 7) {
                        case VideoSize.CIF /*1*/:
                            max = Math.max((width - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            i4 = paddingRight;
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                        case Version.API03_CUPCAKE_15 /*3*/:
                            max = childAt.getWidth() + paddingLeft;
                            i4 = paddingLeft;
                            paddingLeft = paddingRight;
                            paddingRight = max;
                            max = i4;
                            break;
                        case Version.API05_ECLAIR_20 /*5*/:
                            max = (width - paddingRight) - childAt.getMeasuredWidth();
                            i4 = paddingRight + childAt.getMeasuredWidth();
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                        default:
                            max = paddingLeft;
                            i4 = paddingRight;
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                    }
                    max = (max + scrollX) - childAt.getLeft();
                    if (max != 0) {
                        childAt.offsetLeftAndRight(max);
                    }
                } else {
                    i4 = paddingRight;
                    paddingRight = paddingLeft;
                    paddingLeft = i4;
                }
                i3++;
                i4 = paddingLeft;
                paddingLeft = paddingRight;
                paddingRight = i4;
            }
        }
        if (this.aa != null) {
            this.aa.m530a(i, f, i2);
        }
        if (this.ab != null) {
            this.ab.m530a(i, f, i2);
        }
        if (this.ad != null) {
            paddingRight = getScrollX();
            i3 = getChildCount();
            for (paddingLeft = 0; paddingLeft < i3; paddingLeft++) {
                View childAt2 = getChildAt(paddingLeft);
                if (!((LayoutParams) childAt2.getLayoutParams()).f468a) {
                    this.ad.m553a(childAt2, ((float) (childAt2.getLeft() - paddingRight)) / ((float) m577m()));
                }
            }
        }
        this.f511V = true;
    }

    void m588a(int i, int i2, int i3) {
        if (getChildCount() == 0) {
            m573e(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i4 = i - scrollX;
        int i5 = i2 - scrollY;
        if (i4 == 0 && i5 == 0) {
            m568b(false);
            m602e();
            m571d(0);
            return;
        }
        m573e(true);
        m571d(2);
        int m = m577m();
        int i6 = m / 2;
        float a = (((float) i6) * m581a(Math.min(1.0f, (((float) Math.abs(i4)) * 1.0f) / ((float) m)))) + ((float) i6);
        int abs = Math.abs(i3);
        if (abs > 0) {
            m = Math.round(1000.0f * Math.abs(a / ((float) abs))) * 4;
        } else {
            m = (int) (((((float) Math.abs(i4)) / ((((float) m) * this.f517h.m336d(this.f518i)) + ((float) this.f524o))) + 1.0f) * 100.0f);
        }
        this.f522m.startScroll(scrollX, scrollY, i4, i5, Math.min(m, 600));
        ViewCompat.m1145b(this);
    }

    void m589a(int i, boolean z, boolean z2) {
        m590a(i, z, z2, 0);
    }

    void m590a(int i, boolean z, boolean z2, int i2) {
        boolean z3 = false;
        if (this.f517h == null || this.f517h.m328b() <= 0) {
            m573e(false);
        } else if (z2 || this.f518i != i || this.f514e.size() == 0) {
            if (i < 0) {
                i = 0;
            } else if (i >= this.f517h.m328b()) {
                i = this.f517h.m328b() - 1;
            }
            int i3 = this.f535z;
            if (i > this.f518i + i3 || i < this.f518i - i3) {
                for (int i4 = 0; i4 < this.f514e.size(); i4++) {
                    ((C0015b) this.f514e.get(i4)).f479c = true;
                }
            }
            if (this.f518i != i) {
                z3 = true;
            }
            if (this.f509T) {
                this.f518i = i;
                if (z3 && this.aa != null) {
                    this.aa.m529a(i);
                }
                if (z3 && this.ab != null) {
                    this.ab.m529a(i);
                }
                requestLayout();
                return;
            }
            m586a(i);
            m562a(i, z, i2, z3);
        } else {
            m573e(false);
        }
    }

    void m591a(C0009d c0009d) {
        this.ac = c0009d;
    }

    void m592a(boolean z) {
        if (VERSION.SDK_INT >= 7) {
            if (this.ae == null) {
                try {
                    this.ae = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
                } catch (Throwable e) {
                    Log.e("ViewPager", "Can't find setChildrenDrawingOrderEnabled", e);
                }
            }
            try {
                this.ae.invoke(this, new Object[]{Boolean.valueOf(z)});
            } catch (Throwable e2) {
                Log.e("ViewPager", "Error changing children drawing order", e2);
            }
        }
    }

    public boolean m593a(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        switch (keyEvent.getKeyCode()) {
            case 21:
                return m600c(17);
            case 22:
                return m600c(66);
            case 61:
                return VERSION.SDK_INT >= 11 ? KeyEventCompat.m1010a(keyEvent) ? m600c(2) : KeyEventCompat.m1011a(keyEvent, 1) ? m600c(1) : false : false;
            default:
                return false;
        }
    }

    protected boolean m594a(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (i2 + scrollX >= childAt.getLeft() && i2 + scrollX < childAt.getRight() && i3 + scrollY >= childAt.getTop() && i3 + scrollY < childAt.getBottom()) {
                    if (m594a(childAt, true, i, (i2 + scrollX) - childAt.getLeft(), (i3 + scrollY) - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        return z && ViewCompat.m1144a(view, -i);
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0) {
                    C0015b a = m583a(childAt);
                    if (a != null && a.f478b == this.f518i) {
                        childAt.addFocusables(arrayList, i, i2);
                    }
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if (((i2 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && arrayList != null) {
            arrayList.add(this);
        }
    }

    public void addTouchables(ArrayList<View> arrayList) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                C0015b a = m583a(childAt);
                if (a != null && a.f478b == this.f518i) {
                    childAt.addTouchables(arrayList);
                }
            }
        }
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        android.view.ViewGroup.LayoutParams generateLayoutParams = !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : layoutParams;
        LayoutParams layoutParams2 = (LayoutParams) generateLayoutParams;
        layoutParams2.f468a |= view instanceof C0007a;
        if (!this.f532w) {
            super.addView(view, i, generateLayoutParams);
        } else if (layoutParams2 == null || !layoutParams2.f468a) {
            layoutParams2.f471d = true;
            addViewInLayout(view, i, generateLayoutParams);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    C0015b m595b(int i) {
        for (int i2 = 0; i2 < this.f514e.size(); i2++) {
            C0015b c0015b = (C0015b) this.f514e.get(i2);
            if (c0015b.f478b == i) {
                return c0015b;
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    android.support.v4.view.ViewPager.C0015b m596b(android.view.View r3) {
        /*
        r2 = this;
    L_0x0000:
        r0 = r3.getParent();
        if (r0 == r2) goto L_0x0012;
    L_0x0006:
        if (r0 == 0) goto L_0x000c;
    L_0x0008:
        r1 = r0 instanceof android.view.View;
        if (r1 != 0) goto L_0x000e;
    L_0x000c:
        r0 = 0;
    L_0x000d:
        return r0;
    L_0x000e:
        r0 = (android.view.View) r0;
        r3 = r0;
        goto L_0x0000;
    L_0x0012:
        r0 = r2.m583a(r3);
        goto L_0x000d;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.b(android.view.View):android.support.v4.view.ViewPager$b");
    }

    public PagerAdapter m597b() {
        return this.f517h;
    }

    public void m598b(float f) {
        if (this.f505P) {
            this.f495F += f;
            float scrollX = ((float) getScrollX()) - f;
            int m = m577m();
            float f2 = ((float) m) * this.f529t;
            C0015b c0015b = (C0015b) this.f514e.get(0);
            C0015b c0015b2 = (C0015b) this.f514e.get(this.f514e.size() - 1);
            float f3 = c0015b.f478b != 0 ? c0015b.f481e * ((float) m) : ((float) m) * this.f528s;
            float f4 = c0015b2.f478b != this.f517h.m328b() + -1 ? c0015b2.f481e * ((float) m) : f2;
            if (scrollX >= f3) {
                f3 = scrollX > f4 ? f4 : scrollX;
            }
            this.f495F += f3 - ((float) ((int) f3));
            scrollTo((int) f3, getScrollY());
            m574e((int) f3);
            MotionEvent obtain = MotionEvent.obtain(this.f506Q, SystemClock.uptimeMillis(), 2, this.f495F, 0.0f, 0);
            this.f500K.addMovement(obtain);
            obtain.recycle();
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public int m599c() {
        return this.f518i;
    }

    public boolean m600c(int i) {
        View view;
        boolean i2;
        View findFocus = findFocus();
        if (findFocus == this) {
            view = null;
        } else {
            if (findFocus != null) {
                Object obj;
                for (ViewPager parent = findFocus.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                    if (parent == this) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(findFocus.getClass().getSimpleName());
                    for (ViewParent parent2 = findFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                        stringBuilder.append(" => ").append(parent2.getClass().getSimpleName());
                    }
                    Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + stringBuilder.toString());
                    view = null;
                }
            }
            view = findFocus;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i);
        if (findNextFocus == null || findNextFocus == view) {
            if (i == 17 || i == 1) {
                i2 = m606i();
            } else {
                if (i == 66 || i == 2) {
                    i2 = m607j();
                }
                i2 = false;
            }
        } else if (i == 17) {
            i2 = (view == null || m559a(this.f516g, findNextFocus).left < m559a(this.f516g, view).left) ? findNextFocus.requestFocus() : m606i();
        } else {
            if (i == 66) {
                i2 = (view == null || m559a(this.f516g, findNextFocus).left > m559a(this.f516g, view).left) ? findNextFocus.requestFocus() : m607j();
            }
            i2 = false;
        }
        if (i2) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return i2;
    }

    public boolean canScrollHorizontally(int i) {
        boolean z = true;
        if (this.f517h == null) {
            return false;
        }
        int m = m577m();
        int scrollX = getScrollX();
        if (i < 0) {
            if (scrollX <= ((int) (((float) m) * this.f528s))) {
                z = false;
            }
            return z;
        } else if (i <= 0) {
            return false;
        } else {
            if (scrollX >= ((int) (((float) m) * this.f529t))) {
                z = false;
            }
            return z;
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public void computeScroll() {
        if (this.f522m.isFinished() || !this.f522m.computeScrollOffset()) {
            m568b(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.f522m.getCurrX();
        int currY = this.f522m.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!m574e(currX)) {
                this.f522m.abortAnimation();
                scrollTo(0, currY);
            }
        }
        ViewCompat.m1145b(this);
    }

    void m601d() {
        int b = this.f517h.m328b();
        this.f513b = b;
        boolean z = this.f514e.size() < (this.f535z * 2) + 1 && this.f514e.size() < b;
        boolean z2 = false;
        int i = this.f518i;
        boolean z3 = z;
        int i2 = 0;
        while (i2 < this.f514e.size()) {
            int i3;
            boolean z4;
            int i4;
            boolean z5;
            C0015b c0015b = (C0015b) this.f514e.get(i2);
            int a = this.f517h.m317a(c0015b.f477a);
            if (a == -1) {
                i3 = i2;
                z4 = z2;
                i4 = i;
                z5 = z3;
            } else if (a == -2) {
                this.f514e.remove(i2);
                i2--;
                if (!z2) {
                    this.f517h.m325a((ViewGroup) this);
                    z2 = true;
                }
                this.f517h.m326a((ViewGroup) this, c0015b.f478b, c0015b.f477a);
                if (this.f518i == c0015b.f478b) {
                    i3 = i2;
                    z4 = z2;
                    i4 = Math.max(0, Math.min(this.f518i, b - 1));
                    z5 = true;
                } else {
                    i3 = i2;
                    z4 = z2;
                    i4 = i;
                    z5 = true;
                }
            } else if (c0015b.f478b != a) {
                if (c0015b.f478b == this.f518i) {
                    i = a;
                }
                c0015b.f478b = a;
                i3 = i2;
                z4 = z2;
                i4 = i;
                z5 = true;
            } else {
                i3 = i2;
                z4 = z2;
                i4 = i;
                z5 = z3;
            }
            z3 = z5;
            i = i4;
            z2 = z4;
            i2 = i3 + 1;
        }
        if (z2) {
            this.f517h.m332b((ViewGroup) this);
        }
        Collections.sort(this.f514e, f488c);
        if (z3) {
            i4 = getChildCount();
            for (i2 = 0; i2 < i4; i2++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i2).getLayoutParams();
                if (!layoutParams.f468a) {
                    layoutParams.f470c = 0.0f;
                }
            }
            m589a(i, false, true);
            requestLayout();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || m593a(keyEvent);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                C0015b a = m583a(childAt);
                if (a != null && a.f478b == this.f518i && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int i = 0;
        int a = ViewCompat.m1138a(this);
        if (a == 0 || (a == 1 && this.f517h != null && this.f517h.m328b() > 1)) {
            int height;
            int width;
            if (!this.f507R.m1339a()) {
                a = canvas.save();
                height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) ((-height) + getPaddingTop()), this.f528s * ((float) width));
                this.f507R.m1338a(height, width);
                i = 0 | this.f507R.m1341a(canvas);
                canvas.restoreToCount(a);
            }
            if (!this.f508S.m1339a()) {
                a = canvas.save();
                height = getWidth();
                width = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.f529t + 1.0f)) * ((float) height));
                this.f508S.m1338a(width, height);
                i |= this.f508S.m1341a(canvas);
                canvas.restoreToCount(a);
            }
        } else {
            this.f507R.m1342b();
            this.f508S.m1342b();
        }
        if (i != 0) {
            ViewCompat.m1145b(this);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f525p;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    void m602e() {
        m586a(this.f518i);
    }

    public boolean m603f() {
        if (this.f490A) {
            return false;
        }
        this.f505P = true;
        m571d(1);
        this.f495F = 0.0f;
        this.f497H = 0.0f;
        if (this.f500K == null) {
            this.f500K = VelocityTracker.obtain();
        } else {
            this.f500K.clear();
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 0.0f, 0.0f, 0);
        this.f500K.addMovement(obtain);
        obtain.recycle();
        this.f506Q = uptimeMillis;
        return true;
    }

    public void m604g() {
        if (this.f505P) {
            VelocityTracker velocityTracker = this.f500K;
            velocityTracker.computeCurrentVelocity(1000, (float) this.f502M);
            int a = (int) VelocityTrackerCompat.m1086a(velocityTracker, this.f499J);
            this.f534y = true;
            int m = m577m();
            int scrollX = getScrollX();
            C0015b o = m579o();
            m590a(m558a(o.f478b, ((((float) scrollX) / ((float) m)) - o.f481e) / o.f480d, a, (int) (this.f495F - this.f497H)), true, true, a);
            m580p();
            this.f505P = false;
            return;
        }
        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.af == 2) {
            i2 = (i - 1) - i2;
        }
        return ((LayoutParams) ((View) this.ag.get(i2)).getLayoutParams()).f473f;
    }

    public boolean m605h() {
        return this.f505P;
    }

    boolean m606i() {
        if (this.f518i <= 0) {
            return false;
        }
        setCurrentItem(this.f518i - 1, true);
        return true;
    }

    boolean m607j() {
        if (this.f517h == null || this.f518i >= this.f517h.m328b() - 1) {
            return false;
        }
        setCurrentItem(this.f518i + 1, true);
        return true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f509T = true;
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.ai);
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f524o > 0 && this.f525p != null && this.f514e.size() > 0 && this.f517h != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float f = ((float) this.f524o) / ((float) width);
            C0015b c0015b = (C0015b) this.f514e.get(0);
            float f2 = c0015b.f481e;
            int size = this.f514e.size();
            int i = c0015b.f478b;
            int i2 = ((C0015b) this.f514e.get(size - 1)).f478b;
            int i3 = 0;
            int i4 = i;
            while (i4 < i2) {
                float f3;
                while (i4 > c0015b.f478b && i3 < size) {
                    i3++;
                    c0015b = (C0015b) this.f514e.get(i3);
                }
                if (i4 == c0015b.f478b) {
                    f3 = (c0015b.f481e + c0015b.f480d) * ((float) width);
                    f2 = (c0015b.f481e + c0015b.f480d) + f;
                } else {
                    float d = this.f517h.m336d(i4);
                    f3 = (f2 + d) * ((float) width);
                    f2 += d + f;
                }
                if (((float) this.f524o) + f3 > ((float) scrollX)) {
                    this.f525p.setBounds((int) f3, this.f526q, (int) ((((float) this.f524o) + f3) + 0.5f), this.f527r);
                    this.f525p.draw(canvas);
                }
                if (f3 <= ((float) (scrollX + width))) {
                    i4++;
                } else {
                    return;
                }
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.f490A = false;
            this.f491B = false;
            this.f499J = -1;
            if (this.f500K == null) {
                return false;
            }
            this.f500K.recycle();
            this.f500K = null;
            return false;
        }
        if (action != 0) {
            if (this.f490A) {
                return true;
            }
            if (this.f491B) {
                return false;
            }
        }
        switch (action) {
            case VideoSize.QCIF /*0*/:
                float x = motionEvent.getX();
                this.f497H = x;
                this.f495F = x;
                x = motionEvent.getY();
                this.f498I = x;
                this.f496G = x;
                this.f499J = MotionEventCompat.m1070b(motionEvent, 0);
                this.f491B = false;
                this.f522m.computeScrollOffset();
                if (this.aj == 2 && Math.abs(this.f522m.getFinalX() - this.f522m.getCurrX()) > this.f504O) {
                    this.f522m.abortAnimation();
                    this.f534y = false;
                    m602e();
                    this.f490A = true;
                    m572d(true);
                    m571d(1);
                    break;
                }
                m568b(false);
                this.f490A = false;
                break;
                break;
            case VideoSize.HVGA /*2*/:
                action = this.f499J;
                if (action != -1) {
                    action = MotionEventCompat.m1068a(motionEvent, action);
                    float c = MotionEventCompat.m1071c(motionEvent, action);
                    float f = c - this.f495F;
                    float abs = Math.abs(f);
                    float d = MotionEventCompat.m1073d(motionEvent, action);
                    float abs2 = Math.abs(d - this.f498I);
                    if (f == 0.0f || m566a(this.f495F, f) || !m594a(this, false, (int) f, (int) c, (int) d)) {
                        if (abs > ((float) this.f494E) && 0.5f * abs > abs2) {
                            this.f490A = true;
                            m572d(true);
                            m571d(1);
                            this.f495F = f > 0.0f ? this.f497H + ((float) this.f494E) : this.f497H - ((float) this.f494E);
                            this.f496G = d;
                            m573e(true);
                        } else if (abs2 > ((float) this.f494E)) {
                            this.f491B = true;
                        }
                        if (this.f490A && m570c(c)) {
                            ViewCompat.m1145b(this);
                            break;
                        }
                    }
                    this.f495F = c;
                    this.f496G = d;
                    this.f491B = true;
                    return false;
                }
                break;
            case Version.API06_ECLAIR_201 /*6*/:
                m565a(motionEvent);
                break;
        }
        if (this.f500K == null) {
            this.f500K = VelocityTracker.obtain();
        }
        this.f500K.addMovement(motionEvent);
        return this.f490A;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        LayoutParams layoutParams;
        int max;
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i7 = 0;
        int i8 = 0;
        while (i8 < childCount) {
            int measuredWidth;
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.f468a) {
                    int i9 = layoutParams.f469b & 112;
                    switch (layoutParams.f469b & 7) {
                        case VideoSize.CIF /*1*/:
                            max = Math.max((i5 - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        case Version.API03_CUPCAKE_15 /*3*/:
                            max = paddingLeft;
                            paddingLeft = childAt.getMeasuredWidth() + paddingLeft;
                            break;
                        case Version.API05_ECLAIR_20 /*5*/:
                            measuredWidth = (i5 - paddingRight) - childAt.getMeasuredWidth();
                            paddingRight += childAt.getMeasuredWidth();
                            max = measuredWidth;
                            break;
                        default:
                            max = paddingLeft;
                            break;
                    }
                    int i10;
                    switch (i9) {
                        case Version.API16_JELLY_BEAN_41 /*16*/:
                            measuredWidth = Math.max((i6 - childAt.getMeasuredHeight()) / 2, paddingTop);
                            i10 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                        case 48:
                            measuredWidth = childAt.getMeasuredHeight() + paddingTop;
                            i10 = paddingTop;
                            paddingTop = paddingBottom;
                            paddingBottom = measuredWidth;
                            measuredWidth = i10;
                            break;
                        case 80:
                            measuredWidth = (i6 - paddingBottom) - childAt.getMeasuredHeight();
                            i10 = paddingBottom + childAt.getMeasuredHeight();
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                        default:
                            measuredWidth = paddingTop;
                            i10 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                    }
                    max += scrollX;
                    childAt.layout(max, measuredWidth, childAt.getMeasuredWidth() + max, childAt.getMeasuredHeight() + measuredWidth);
                    measuredWidth = i7 + 1;
                    i7 = paddingBottom;
                    paddingBottom = paddingTop;
                    paddingTop = paddingRight;
                    paddingRight = paddingLeft;
                    i8++;
                    paddingLeft = paddingRight;
                    paddingRight = paddingTop;
                    paddingTop = i7;
                    i7 = measuredWidth;
                }
            }
            measuredWidth = i7;
            i7 = paddingTop;
            paddingTop = paddingRight;
            paddingRight = paddingLeft;
            i8++;
            paddingLeft = paddingRight;
            paddingRight = paddingTop;
            paddingTop = i7;
            i7 = measuredWidth;
        }
        max = (i5 - paddingLeft) - paddingRight;
        for (paddingRight = 0; paddingRight < childCount; paddingRight++) {
            View childAt2 = getChildAt(paddingRight);
            if (childAt2.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt2.getLayoutParams();
                if (!layoutParams.f468a) {
                    C0015b a = m583a(childAt2);
                    if (a != null) {
                        i5 = ((int) (a.f481e * ((float) max))) + paddingLeft;
                        if (layoutParams.f471d) {
                            layoutParams.f471d = false;
                            childAt2.measure(MeasureSpec.makeMeasureSpec((int) (layoutParams.f470c * ((float) max)), 1073741824), MeasureSpec.makeMeasureSpec((i6 - paddingTop) - paddingBottom, 1073741824));
                        }
                        childAt2.layout(i5, paddingTop, childAt2.getMeasuredWidth() + i5, childAt2.getMeasuredHeight() + paddingTop);
                    }
                }
            }
        }
        this.f526q = paddingTop;
        this.f527r = i6 - paddingBottom;
        this.f512W = i7;
        if (this.f509T) {
            m562a(this.f518i, false, 0, false);
        }
        this.f509T = false;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        int measuredWidth = getMeasuredWidth();
        this.f493D = Math.min(measuredWidth / 10, this.f492C);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            LayoutParams layoutParams;
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams != null && layoutParams.f468a) {
                    int i6 = layoutParams.f469b & 7;
                    int i7 = layoutParams.f469b & 112;
                    i3 = Integer.MIN_VALUE;
                    i4 = Integer.MIN_VALUE;
                    Object obj = (i7 == 48 || i7 == 80) ? 1 : null;
                    Object obj2 = (i6 == 3 || i6 == 5) ? 1 : null;
                    if (obj != null) {
                        i3 = 1073741824;
                    } else if (obj2 != null) {
                        i4 = 1073741824;
                    }
                    if (layoutParams.width != -2) {
                        i7 = 1073741824;
                        i3 = layoutParams.width != -1 ? layoutParams.width : paddingLeft;
                    } else {
                        i7 = i3;
                        i3 = paddingLeft;
                    }
                    if (layoutParams.height != -2) {
                        i4 = 1073741824;
                        if (layoutParams.height != -1) {
                            measuredWidth = layoutParams.height;
                            childAt.measure(MeasureSpec.makeMeasureSpec(i3, i7), MeasureSpec.makeMeasureSpec(measuredWidth, i4));
                            if (obj != null) {
                                measuredHeight -= childAt.getMeasuredHeight();
                            } else if (obj2 != null) {
                                paddingLeft -= childAt.getMeasuredWidth();
                            }
                        }
                    }
                    measuredWidth = measuredHeight;
                    childAt.measure(MeasureSpec.makeMeasureSpec(i3, i7), MeasureSpec.makeMeasureSpec(measuredWidth, i4));
                    if (obj != null) {
                        measuredHeight -= childAt.getMeasuredHeight();
                    } else if (obj2 != null) {
                        paddingLeft -= childAt.getMeasuredWidth();
                    }
                }
            }
        }
        this.f530u = MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        this.f531v = MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.f532w = true;
        m602e();
        this.f532w = false;
        i3 = getChildCount();
        for (i4 = 0; i4 < i3; i4++) {
            View childAt2 = getChildAt(i4);
            if (childAt2.getVisibility() != 8) {
                layoutParams = (LayoutParams) childAt2.getLayoutParams();
                if (layoutParams == null || !layoutParams.f468a) {
                    childAt2.measure(MeasureSpec.makeMeasureSpec((int) (layoutParams.f470c * ((float) paddingLeft)), 1073741824), this.f531v);
                }
            }
        }
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        int i3 = -1;
        int childCount = getChildCount();
        if ((i & 2) != 0) {
            i3 = 1;
            i2 = 0;
        } else {
            i2 = childCount - 1;
            childCount = -1;
        }
        while (i2 != childCount) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                C0015b a = m583a(childAt);
                if (a != null && a.f478b == this.f518i && childAt.requestFocus(i, rect)) {
                    return true;
                }
            }
            i2 += i3;
        }
        return false;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (this.f517h != null) {
                this.f517h.m322a(savedState.f475b, savedState.f476c);
                m589a(savedState.f474a, false, true);
                return;
            }
            this.f519j = savedState.f474a;
            this.f520k = savedState.f475b;
            this.f521l = savedState.f476c;
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f474a = this.f518i;
        if (this.f517h != null) {
            savedState.f475b = this.f517h.m318a();
        }
        return savedState;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            m561a(i, i3, this.f524o, this.f524o);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.f505P) {
            return true;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        if (this.f517h == null || this.f517h.m328b() == 0) {
            return false;
        }
        if (this.f500K == null) {
            this.f500K = VelocityTracker.obtain();
        }
        this.f500K.addMovement(motionEvent);
        float x;
        int a;
        switch (motionEvent.getAction() & 255) {
            case VideoSize.QCIF /*0*/:
                this.f522m.abortAnimation();
                this.f534y = false;
                m602e();
                x = motionEvent.getX();
                this.f497H = x;
                this.f495F = x;
                x = motionEvent.getY();
                this.f498I = x;
                this.f496G = x;
                this.f499J = MotionEventCompat.m1070b(motionEvent, 0);
                break;
            case VideoSize.CIF /*1*/:
                if (this.f490A) {
                    VelocityTracker velocityTracker = this.f500K;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.f502M);
                    a = (int) VelocityTrackerCompat.m1086a(velocityTracker, this.f499J);
                    this.f534y = true;
                    int m = m577m();
                    int scrollX = getScrollX();
                    C0015b o = m579o();
                    m590a(m558a(o.f478b, ((((float) scrollX) / ((float) m)) - o.f481e) / o.f480d, a, (int) (MotionEventCompat.m1071c(motionEvent, MotionEventCompat.m1068a(motionEvent, this.f499J)) - this.f497H)), true, true, a);
                    this.f499J = -1;
                    m580p();
                    z = this.f508S.m1343c() | this.f507R.m1343c();
                    break;
                }
                break;
            case VideoSize.HVGA /*2*/:
                if (!this.f490A) {
                    a = MotionEventCompat.m1068a(motionEvent, this.f499J);
                    float c = MotionEventCompat.m1071c(motionEvent, a);
                    float abs = Math.abs(c - this.f495F);
                    float d = MotionEventCompat.m1073d(motionEvent, a);
                    x = Math.abs(d - this.f496G);
                    if (abs > ((float) this.f494E) && abs > x) {
                        this.f490A = true;
                        m572d(true);
                        this.f495F = c - this.f497H > 0.0f ? this.f497H + ((float) this.f494E) : this.f497H - ((float) this.f494E);
                        this.f496G = d;
                        m571d(1);
                        m573e(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                if (this.f490A) {
                    z = false | m570c(MotionEventCompat.m1071c(motionEvent, MotionEventCompat.m1068a(motionEvent, this.f499J)));
                    break;
                }
                break;
            case Version.API03_CUPCAKE_15 /*3*/:
                if (this.f490A) {
                    m562a(this.f518i, true, 0, false);
                    this.f499J = -1;
                    m580p();
                    z = this.f508S.m1343c() | this.f507R.m1343c();
                    break;
                }
                break;
            case Version.API05_ECLAIR_20 /*5*/:
                a = MotionEventCompat.m1069b(motionEvent);
                this.f495F = MotionEventCompat.m1071c(motionEvent, a);
                this.f499J = MotionEventCompat.m1070b(motionEvent, a);
                break;
            case Version.API06_ECLAIR_201 /*6*/:
                m565a(motionEvent);
                this.f495F = MotionEventCompat.m1071c(motionEvent, MotionEventCompat.m1068a(motionEvent, this.f499J));
                break;
        }
        if (z) {
            ViewCompat.m1145b(this);
        }
        return true;
    }

    public void removeView(View view) {
        if (this.f532w) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (this.f517h != null) {
            this.f517h.m329b(this.f523n);
            this.f517h.m325a((ViewGroup) this);
            for (int i = 0; i < this.f514e.size(); i++) {
                C0015b c0015b = (C0015b) this.f514e.get(i);
                this.f517h.m326a((ViewGroup) this, c0015b.f478b, c0015b.f477a);
            }
            this.f517h.m332b((ViewGroup) this);
            this.f514e.clear();
            m576l();
            this.f518i = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pagerAdapter2 = this.f517h;
        this.f517h = pagerAdapter;
        this.f513b = 0;
        if (this.f517h != null) {
            if (this.f523n == null) {
                this.f523n = new C0018g();
            }
            this.f517h.m321a(this.f523n);
            this.f534y = false;
            boolean z = this.f509T;
            this.f509T = true;
            this.f513b = this.f517h.m328b();
            if (this.f519j >= 0) {
                this.f517h.m322a(this.f520k, this.f521l);
                m589a(this.f519j, false, true);
                this.f519j = -1;
                this.f520k = null;
                this.f521l = null;
            } else if (z) {
                requestLayout();
            } else {
                m602e();
            }
        }
        if (this.ac != null && pagerAdapter2 != pagerAdapter) {
            this.ac.m528a(pagerAdapter2, pagerAdapter);
        }
    }

    public void setCurrentItem(int i) {
        this.f534y = false;
        m589a(i, !this.f509T, false);
    }

    public void setCurrentItem(int i, boolean z) {
        this.f534y = false;
        m589a(i, z, false);
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i + " too small; defaulting to " + 1);
            i = 1;
        }
        if (i != this.f535z) {
            this.f535z = i;
            m602e();
        }
    }

    public void setOnPageChangeListener(C0010e c0010e) {
        this.aa = c0010e;
    }

    public void setPageMargin(int i) {
        int i2 = this.f524o;
        this.f524o = i;
        int width = getWidth();
        m561a(width, width, i, i2);
        requestLayout();
    }

    public void setPageMarginDrawable(int i) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i));
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.f525p = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageTransformer(boolean z, C0017f c0017f) {
        int i = 1;
        if (VERSION.SDK_INT >= 11) {
            boolean z2 = c0017f != null;
            int i2 = z2 != (this.ad != null) ? 1 : 0;
            this.ad = c0017f;
            m592a(z2);
            if (z2) {
                if (z) {
                    i = 2;
                }
                this.af = i;
            } else {
                this.af = 0;
            }
            if (i2 != 0) {
                m602e();
            }
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f525p;
    }
}
