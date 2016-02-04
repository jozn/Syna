package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ah;
import android.support.v4.view.p009a.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ViewDragHelper.ViewDragHelper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

public class DrawerLayout extends ViewGroup {
    private static final int[] f588a;
    private int f589b;
    private int f590c;
    private float f591d;
    private Paint f592e;
    private final ViewDragHelper f593f;
    private final ViewDragHelper f594g;
    private final C0023c f595h;
    private final C0023c f596i;
    private int f597j;
    private boolean f598k;
    private boolean f599l;
    private int f600m;
    private int f601n;
    private boolean f602o;
    private boolean f603p;
    private C0022b f604q;
    private float f605r;
    private float f606s;
    private Drawable f607t;
    private Drawable f608u;
    private CharSequence f609v;
    private CharSequence f610w;

    public static class LayoutParams extends MarginLayoutParams {
        public int f575a;
        float f576b;
        boolean f577c;
        boolean f578d;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.f575a = 0;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f575a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.f588a);
            this.f575a = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.f575a = 0;
            this.f575a = layoutParams.f575a;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f575a = 0;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f575a = 0;
        }
    }

    protected static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int f579a;
        int f580b;
        int f581c;

        static {
            CREATOR = new DrawerLayout();
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f579a = 0;
            this.f580b = 0;
            this.f581c = 0;
            this.f579a = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
            this.f579a = 0;
            this.f580b = 0;
            this.f581c = 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f579a);
        }
    }

    /* renamed from: android.support.v4.widget.DrawerLayout.a */
    class C0021a extends AccessibilityDelegateCompat {
        final /* synthetic */ DrawerLayout f582b;
        private final Rect f583c;

        C0021a(DrawerLayout drawerLayout) {
            this.f582b = drawerLayout;
            this.f583c = new Rect();
        }

        private void m1159a(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
            Rect rect = this.f583c;
            accessibilityNodeInfoCompat2.m773a(rect);
            accessibilityNodeInfoCompat.m779b(rect);
            accessibilityNodeInfoCompat2.m784c(rect);
            accessibilityNodeInfoCompat.m788d(rect);
            accessibilityNodeInfoCompat.m787c(accessibilityNodeInfoCompat2.m798h());
            accessibilityNodeInfoCompat.m775a(accessibilityNodeInfoCompat2.m807p());
            accessibilityNodeInfoCompat.m781b(accessibilityNodeInfoCompat2.m808q());
            accessibilityNodeInfoCompat.m786c(accessibilityNodeInfoCompat2.m810s());
            accessibilityNodeInfoCompat.m797h(accessibilityNodeInfoCompat2.m804m());
            accessibilityNodeInfoCompat.m793f(accessibilityNodeInfoCompat2.m802k());
            accessibilityNodeInfoCompat.m776a(accessibilityNodeInfoCompat2.m794f());
            accessibilityNodeInfoCompat.m782b(accessibilityNodeInfoCompat2.m796g());
            accessibilityNodeInfoCompat.m789d(accessibilityNodeInfoCompat2.m800i());
            accessibilityNodeInfoCompat.m791e(accessibilityNodeInfoCompat2.m801j());
            accessibilityNodeInfoCompat.m795g(accessibilityNodeInfoCompat2.m803l());
            accessibilityNodeInfoCompat.m772a(accessibilityNodeInfoCompat2.m777b());
        }

        private void m1160a(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (!m1163b(childAt)) {
                    switch (ViewCompat.m1147c(childAt)) {
                        case VideoSize.QCIF /*0*/:
                            ViewCompat.m1148c(childAt, 1);
                            break;
                        case VideoSize.CIF /*1*/:
                            break;
                        case VideoSize.HVGA /*2*/:
                            if (childAt instanceof ViewGroup) {
                                m1160a(accessibilityNodeInfoCompat, (ViewGroup) childAt);
                                break;
                            }
                            continue;
                        case Version.API04_DONUT_16 /*4*/:
                            break;
                        default:
                            continue;
                    }
                    accessibilityNodeInfoCompat.m780b(childAt);
                }
            }
        }

        public void m1161a(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            AccessibilityNodeInfoCompat a = AccessibilityNodeInfoCompat.m768a(accessibilityNodeInfoCompat);
            super.m542a(view, a);
            accessibilityNodeInfoCompat.m781b(DrawerLayout.class.getName());
            accessibilityNodeInfoCompat.m774a(view);
            ViewParent f = ViewCompat.m1151f(view);
            if (f instanceof View) {
                accessibilityNodeInfoCompat.m785c((View) f);
            }
            m1159a(accessibilityNodeInfoCompat, a);
            a.m811t();
            m1160a(accessibilityNodeInfoCompat, (ViewGroup) view);
        }

        public boolean m1162a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return !m1163b(view) ? super.m545a(viewGroup, view, accessibilityEvent) : false;
        }

        public boolean m1163b(View view) {
            View a = this.f582b.m1207a();
            return (a == null || a == view) ? false : true;
        }

        public boolean m1164b(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() != 32) {
                return super.m546b(view, accessibilityEvent);
            }
            List text = accessibilityEvent.getText();
            View a = this.f582b.m1204g();
            if (a != null) {
                CharSequence a2 = this.f582b.m1208a(this.f582b.m1220e(a));
                if (a2 != null) {
                    text.add(a2);
                }
            }
            return true;
        }

        public void m1165d(View view, AccessibilityEvent accessibilityEvent) {
            super.m548d(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }
    }

    /* renamed from: android.support.v4.widget.DrawerLayout.b */
    public interface C0022b {
        void m1166a(int i);

        void m1167a(View view);

        void m1168a(View view, float f);

        void m1169b(View view);
    }

    /* renamed from: android.support.v4.widget.DrawerLayout.c */
    private class C0023c extends ViewDragHelper {
        final /* synthetic */ DrawerLayout f584a;
        private final int f585b;
        private ViewDragHelper f586c;
        private final Runnable f587d;

        public C0023c(DrawerLayout drawerLayout, int i) {
            this.f584a = drawerLayout;
            this.f587d = new DrawerLayout(this);
            this.f585b = i;
        }

        private void m1184b() {
            int i = 3;
            if (this.f585b == 3) {
                i = 5;
            }
            View b = this.f584a.m1213b(i);
            if (b != null) {
                this.f584a.m1224i(b);
            }
        }

        private void m1185c() {
            View view;
            int i;
            int i2 = 0;
            int b = this.f586c.m1436b();
            boolean z = this.f585b == 3;
            if (z) {
                View b2 = this.f584a.m1213b(3);
                if (b2 != null) {
                    i2 = -b2.getWidth();
                }
                i2 += b;
                view = b2;
                i = i2;
            } else {
                i2 = this.f584a.getWidth() - b;
                view = this.f584a.m1213b(5);
                i = i2;
            }
            if (view == null) {
                return;
            }
            if (((z && view.getLeft() < i) || (!z && view.getLeft() > i)) && this.f584a.m1206a(view) == 0) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                this.f586c.m1434a(view, i, view.getTop());
                layoutParams.f577c = true;
                this.f584a.invalidate();
                m1184b();
                this.f584a.m1217c();
            }
        }

        public int m1186a(View view) {
            return view.getWidth();
        }

        public int m1187a(View view, int i, int i2) {
            if (this.f584a.m1212a(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            }
            int width = this.f584a.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i, width));
        }

        public void m1188a() {
            this.f584a.removeCallbacks(this.f587d);
        }

        public void m1189a(int i) {
            this.f584a.m1209a(this.f585b, i, this.f586c.m1442c());
        }

        public void m1190a(int i, int i2) {
            this.f584a.postDelayed(this.f587d, 160);
        }

        public void m1191a(ViewDragHelper viewDragHelper) {
            this.f586c = viewDragHelper;
        }

        public void m1192a(View view, float f, float f2) {
            int i;
            float d = this.f584a.m1219d(view);
            int width = view.getWidth();
            if (this.f584a.m1212a(view, 3)) {
                i = (f > 0.0f || (f == 0.0f && d > 0.5f)) ? 0 : -width;
            } else {
                i = this.f584a.getWidth();
                if (f < 0.0f || (f == 0.0f && d > 0.5f)) {
                    i -= width;
                }
            }
            this.f586c.m1432a(i, view.getTop());
            this.f584a.invalidate();
        }

        public void m1193a(View view, int i, int i2, int i3, int i4) {
            int width = view.getWidth();
            float width2 = this.f584a.m1212a(view, 3) ? ((float) (width + i)) / ((float) width) : ((float) (this.f584a.getWidth() - i)) / ((float) width);
            this.f584a.m1216b(view, width2);
            view.setVisibility(width2 == 0.0f ? 4 : 0);
            this.f584a.invalidate();
        }

        public boolean m1194a(View view, int i) {
            return this.f584a.m1222g(view) && this.f584a.m1212a(view, this.f585b) && this.f584a.m1206a(view) == 0;
        }

        public int m1195b(View view, int i, int i2) {
            return view.getTop();
        }

        public void m1196b(int i, int i2) {
            View b = (i & 1) == 1 ? this.f584a.m1213b(3) : this.f584a.m1213b(5);
            if (b != null && this.f584a.m1206a(b) == 0) {
                this.f586c.m1431a(b, i2);
            }
        }

        public void m1197b(View view, int i) {
            ((LayoutParams) view.getLayoutParams()).f577c = false;
            m1184b();
        }

        public boolean m1198b(int i) {
            return false;
        }
    }

    static {
        f588a = new int[]{16842931};
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f590c = -1728053248;
        this.f592e = new Paint();
        this.f599l = true;
        float f = getResources().getDisplayMetrics().density;
        this.f589b = (int) ((64.0f * f) + 0.5f);
        f *= 400.0f;
        this.f595h = new C0023c(this, 3);
        this.f596i = new C0023c(this, 5);
        this.f593f = ViewDragHelper.m1411a((ViewGroup) this, 1.0f, this.f595h);
        this.f593f.m1430a(1);
        this.f593f.m1429a(f);
        this.f595h.m1191a(this.f593f);
        this.f594g = ViewDragHelper.m1411a((ViewGroup) this, 1.0f, this.f596i);
        this.f594g.m1430a(2);
        this.f594g.m1429a(f);
        this.f596i.m1191a(this.f594g);
        setFocusableInTouchMode(true);
        ViewCompat.m1142a((View) this, new C0021a(this));
        ah.m928a(this, false);
    }

    static String m1200c(int i) {
        return (i & 3) == 3 ? "LEFT" : (i & 5) == 5 ? "RIGHT" : Integer.toHexString(i);
    }

    private boolean m1202e() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (((LayoutParams) getChildAt(i).getLayoutParams()).f577c) {
                return true;
            }
        }
        return false;
    }

    private boolean m1203f() {
        return m1204g() != null;
    }

    private View m1204g() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (m1222g(childAt) && m1225j(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    private static boolean m1205k(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    public int m1206a(View view) {
        int e = m1220e(view);
        return e == 3 ? this.f600m : e == 5 ? this.f601n : 0;
    }

    View m1207a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (((LayoutParams) childAt.getLayoutParams()).f578d) {
                return childAt;
            }
        }
        return null;
    }

    public CharSequence m1208a(int i) {
        int a = GravityCompat.m996a(i, ViewCompat.m1150e(this));
        return a == 3 ? this.f609v : a == 5 ? this.f610w : null;
    }

    void m1209a(int i, int i2, View view) {
        int i3 = 1;
        int a = this.f593f.m1428a();
        int a2 = this.f594g.m1428a();
        if (!(a == 1 || a2 == 1)) {
            i3 = (a == 2 || a2 == 2) ? 2 : 0;
        }
        if (view != null && i2 == 0) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (layoutParams.f576b == 0.0f) {
                m1215b(view);
            } else if (layoutParams.f576b == 1.0f) {
                m1218c(view);
            }
        }
        if (i3 != this.f597j) {
            this.f597j = i3;
            if (this.f604q != null) {
                this.f604q.m1166a(i3);
            }
        }
    }

    void m1210a(View view, float f) {
        if (this.f604q != null) {
            this.f604q.m1168a(view, f);
        }
    }

    void m1211a(boolean z) {
        int childCount = getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (m1222g(childAt) && (!z || layoutParams.f577c)) {
                i = m1212a(childAt, 3) ? i | this.f593f.m1434a(childAt, -childAt.getWidth(), childAt.getTop()) : i | this.f594g.m1434a(childAt, getWidth(), childAt.getTop());
                layoutParams.f577c = false;
            }
        }
        this.f595h.m1188a();
        this.f596i.m1188a();
        if (i != 0) {
            invalidate();
        }
    }

    boolean m1212a(View view, int i) {
        return (m1220e(view) & i) == i;
    }

    View m1213b(int i) {
        int a = GravityCompat.m996a(i, ViewCompat.m1150e(this)) & 7;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((m1220e(childAt) & 7) == a) {
                return childAt;
            }
        }
        return null;
    }

    public void m1214b() {
        m1211a(false);
    }

    void m1215b(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.f578d) {
            layoutParams.f578d = false;
            if (this.f604q != null) {
                this.f604q.m1169b(view);
            }
            if (hasWindowFocus()) {
                View rootView = getRootView();
                if (rootView != null) {
                    rootView.sendAccessibilityEvent(32);
                }
            }
        }
    }

    void m1216b(View view, float f) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f != layoutParams.f576b) {
            layoutParams.f576b = f;
            m1210a(view, f);
        }
    }

    void m1217c() {
        int i = 0;
        if (!this.f603p) {
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            int childCount = getChildCount();
            while (i < childCount) {
                getChildAt(i).dispatchTouchEvent(obtain);
                i++;
            }
            obtain.recycle();
            this.f603p = true;
        }
    }

    void m1218c(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.f578d) {
            layoutParams.f578d = true;
            if (this.f604q != null) {
                this.f604q.m1167a(view);
            }
            sendAccessibilityEvent(32);
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public void computeScroll() {
        int childCount = getChildCount();
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            f = Math.max(f, ((LayoutParams) getChildAt(i).getLayoutParams()).f576b);
        }
        this.f591d = f;
        if ((this.f593f.m1435a(true) | this.f594g.m1435a(true)) != 0) {
            ViewCompat.m1145b(this);
        }
    }

    float m1219d(View view) {
        return ((LayoutParams) view.getLayoutParams()).f576b;
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        int i;
        int height = getHeight();
        boolean f = m1221f(view);
        int i2 = 0;
        int width = getWidth();
        int save = canvas.save();
        if (f) {
            int childCount = getChildCount();
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = getChildAt(i3);
                if (childAt != view && childAt.getVisibility() == 0 && m1205k(childAt) && m1222g(childAt)) {
                    if (childAt.getHeight() < height) {
                        i = width;
                    } else if (m1212a(childAt, 3)) {
                        i = childAt.getRight();
                        if (i <= i2) {
                            i = i2;
                        }
                        i2 = i;
                        i = width;
                    } else {
                        i = childAt.getLeft();
                        if (i < width) {
                        }
                    }
                    i3++;
                    width = i;
                }
                i = width;
                i3++;
                width = i;
            }
            canvas.clipRect(i2, 0, width, getHeight());
        }
        i = width;
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(save);
        if (this.f591d > 0.0f && f) {
            this.f592e.setColor((((int) (((float) ((this.f590c & -16777216) >>> 24)) * this.f591d)) << 24) | (this.f590c & 16777215));
            canvas.drawRect((float) i2, 0.0f, (float) i, (float) getHeight(), this.f592e);
        } else if (this.f607t != null && m1212a(view, 3)) {
            i = this.f607t.getIntrinsicWidth();
            i2 = view.getRight();
            r2 = Math.max(0.0f, Math.min(((float) i2) / ((float) this.f593f.m1436b()), 1.0f));
            this.f607t.setBounds(i2, view.getTop(), i + i2, view.getBottom());
            this.f607t.setAlpha((int) (255.0f * r2));
            this.f607t.draw(canvas);
        } else if (this.f608u != null && m1212a(view, 5)) {
            i = this.f608u.getIntrinsicWidth();
            i2 = view.getLeft();
            r2 = Math.max(0.0f, Math.min(((float) (getWidth() - i2)) / ((float) this.f594g.m1436b()), 1.0f));
            this.f608u.setBounds(i2 - i, view.getTop(), i2, view.getBottom());
            this.f608u.setAlpha((int) (255.0f * r2));
            this.f608u.draw(canvas);
        }
        return drawChild;
    }

    int m1220e(View view) {
        return GravityCompat.m996a(((LayoutParams) view.getLayoutParams()).f575a, ViewCompat.m1150e(this));
    }

    boolean m1221f(View view) {
        return ((LayoutParams) view.getLayoutParams()).f575a == 0;
    }

    boolean m1222g(View view) {
        return (GravityCompat.m996a(((LayoutParams) view.getLayoutParams()).f575a, ViewCompat.m1150e(view)) & 7) != 0;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public void m1223h(View view) {
        if (m1222g(view)) {
            if (this.f599l) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                layoutParams.f576b = 1.0f;
                layoutParams.f578d = true;
            } else if (m1212a(view, 3)) {
                this.f593f.m1434a(view, 0, view.getTop());
            } else {
                this.f594g.m1434a(view, getWidth() - view.getWidth(), view.getTop());
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public void m1224i(View view) {
        if (m1222g(view)) {
            if (this.f599l) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                layoutParams.f576b = 0.0f;
                layoutParams.f578d = false;
            } else if (m1212a(view, 3)) {
                this.f593f.m1434a(view, -view.getWidth(), view.getTop());
            } else {
                this.f594g.m1434a(view, getWidth(), view.getTop());
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public boolean m1225j(View view) {
        if (m1222g(view)) {
            return ((LayoutParams) view.getLayoutParams()).f576b > 0.0f;
        } else {
            throw new IllegalArgumentException("View " + view + " is not a drawer");
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f599l = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f599l = true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r8) {
        /*
        r7 = this;
        r1 = 1;
        r2 = 0;
        r0 = android.support.v4.view.MotionEventCompat.m1067a(r8);
        r3 = r7.f593f;
        r3 = r3.m1433a(r8);
        r4 = r7.f594g;
        r4 = r4.m1433a(r8);
        r3 = r3 | r4;
        switch(r0) {
            case 0: goto L_0x0027;
            case 1: goto L_0x0063;
            case 2: goto L_0x004e;
            case 3: goto L_0x0063;
            default: goto L_0x0016;
        };
    L_0x0016:
        r0 = r2;
    L_0x0017:
        if (r3 != 0) goto L_0x0025;
    L_0x0019:
        if (r0 != 0) goto L_0x0025;
    L_0x001b:
        r0 = r7.m1202e();
        if (r0 != 0) goto L_0x0025;
    L_0x0021:
        r0 = r7.f603p;
        if (r0 == 0) goto L_0x0026;
    L_0x0025:
        r2 = r1;
    L_0x0026:
        return r2;
    L_0x0027:
        r0 = r8.getX();
        r4 = r8.getY();
        r7.f605r = r0;
        r7.f606s = r4;
        r5 = r7.f591d;
        r6 = 0;
        r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1));
        if (r5 <= 0) goto L_0x006b;
    L_0x003a:
        r5 = r7.f593f;
        r0 = (int) r0;
        r4 = (int) r4;
        r0 = r5.m1446d(r0, r4);
        r0 = r7.m1221f(r0);
        if (r0 == 0) goto L_0x006b;
    L_0x0048:
        r0 = r1;
    L_0x0049:
        r7.f602o = r2;
        r7.f603p = r2;
        goto L_0x0017;
    L_0x004e:
        r0 = r7.f593f;
        r4 = 3;
        r0 = r0.m1447d(r4);
        if (r0 == 0) goto L_0x0016;
    L_0x0057:
        r0 = r7.f595h;
        r0.m1188a();
        r0 = r7.f596i;
        r0.m1188a();
        r0 = r2;
        goto L_0x0017;
    L_0x0063:
        r7.m1211a(r1);
        r7.f602o = r2;
        r7.f603p = r2;
        goto L_0x0016;
    L_0x006b:
        r0 = r2;
        goto L_0x0049;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !m1203f()) {
            return super.onKeyDown(i, keyEvent);
        }
        KeyEventCompat.m1012b(keyEvent);
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        View g = m1204g();
        if (g != null && m1206a(g) == 0) {
            m1214b();
        }
        return g != null;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f598k = true;
        int i5 = i3 - i;
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (m1221f(childAt)) {
                    childAt.layout(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + childAt.getMeasuredWidth(), layoutParams.topMargin + childAt.getMeasuredHeight());
                } else {
                    int i7;
                    float f;
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (m1212a(childAt, 3)) {
                        i7 = ((int) (((float) measuredWidth) * layoutParams.f576b)) + (-measuredWidth);
                        f = ((float) (measuredWidth + i7)) / ((float) measuredWidth);
                    } else {
                        i7 = i5 - ((int) (((float) measuredWidth) * layoutParams.f576b));
                        f = ((float) (i5 - i7)) / ((float) measuredWidth);
                    }
                    Object obj = f != layoutParams.f576b ? 1 : null;
                    int i8;
                    switch (layoutParams.f575a & 112) {
                        case Version.API16_JELLY_BEAN_41 /*16*/:
                            int i9 = i4 - i2;
                            i8 = (i9 - measuredHeight) / 2;
                            if (i8 < layoutParams.topMargin) {
                                i8 = layoutParams.topMargin;
                            } else if (i8 + measuredHeight > i9 - layoutParams.bottomMargin) {
                                i8 = (i9 - layoutParams.bottomMargin) - measuredHeight;
                            }
                            childAt.layout(i7, i8, measuredWidth + i7, measuredHeight + i8);
                            break;
                        case 80:
                            i8 = i4 - i2;
                            childAt.layout(i7, (i8 - layoutParams.bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i7, i8 - layoutParams.bottomMargin);
                            break;
                        default:
                            childAt.layout(i7, layoutParams.topMargin, measuredWidth + i7, measuredHeight + layoutParams.topMargin);
                            break;
                    }
                    if (obj != null) {
                        m1216b(childAt, f);
                    }
                    int i10 = layoutParams.f576b > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i10) {
                        childAt.setVisibility(i10);
                    }
                }
            }
        }
        this.f598k = false;
        this.f599l = false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onMeasure(int r12, int r13) {
        /*
        r11 = this;
        r1 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        r4 = 0;
        r7 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r10 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = android.view.View.MeasureSpec.getMode(r12);
        r5 = android.view.View.MeasureSpec.getMode(r13);
        r2 = android.view.View.MeasureSpec.getSize(r12);
        r0 = android.view.View.MeasureSpec.getSize(r13);
        if (r3 != r10) goto L_0x001b;
    L_0x0019:
        if (r5 == r10) goto L_0x0046;
    L_0x001b:
        r6 = r11.isInEditMode();
        if (r6 == 0) goto L_0x0048;
    L_0x0021:
        if (r3 != r7) goto L_0x0040;
    L_0x0023:
        if (r5 != r7) goto L_0x0044;
    L_0x0025:
        r1 = r0;
    L_0x0026:
        r11.setMeasuredDimension(r2, r1);
        r5 = r11.getChildCount();
        r3 = r4;
    L_0x002e:
        if (r3 >= r5) goto L_0x0109;
    L_0x0030:
        r6 = r11.getChildAt(r3);
        r0 = r6.getVisibility();
        r7 = 8;
        if (r0 != r7) goto L_0x0050;
    L_0x003c:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x002e;
    L_0x0040:
        if (r3 != 0) goto L_0x0023;
    L_0x0042:
        r2 = r1;
        goto L_0x0023;
    L_0x0044:
        if (r5 == 0) goto L_0x0026;
    L_0x0046:
        r1 = r0;
        goto L_0x0026;
    L_0x0048:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "DrawerLayout must be measured with MeasureSpec.EXACTLY.";
        r0.<init>(r1);
        throw r0;
    L_0x0050:
        r0 = r6.getLayoutParams();
        r0 = (android.support.v4.widget.DrawerLayout.LayoutParams) r0;
        r7 = r11.m1221f(r6);
        if (r7 == 0) goto L_0x0077;
    L_0x005c:
        r7 = r0.leftMargin;
        r7 = r2 - r7;
        r8 = r0.rightMargin;
        r7 = r7 - r8;
        r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r10);
        r8 = r0.topMargin;
        r8 = r1 - r8;
        r0 = r0.bottomMargin;
        r0 = r8 - r0;
        r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r10);
        r6.measure(r7, r0);
        goto L_0x003c;
    L_0x0077:
        r7 = r11.m1222g(r6);
        if (r7 == 0) goto L_0x00da;
    L_0x007d:
        r7 = r11.m1220e(r6);
        r7 = r7 & 7;
        r8 = r4 & r7;
        if (r8 == 0) goto L_0x00bc;
    L_0x0087:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Child drawer has absolute gravity ";
        r1 = r1.append(r2);
        r2 = m1200c(r7);
        r1 = r1.append(r2);
        r2 = " but this ";
        r1 = r1.append(r2);
        r2 = "DrawerLayout";
        r1 = r1.append(r2);
        r2 = " already has a ";
        r1 = r1.append(r2);
        r2 = "drawer view along that edge";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00bc:
        r7 = r11.f589b;
        r8 = r0.leftMargin;
        r7 = r7 + r8;
        r8 = r0.rightMargin;
        r7 = r7 + r8;
        r8 = r0.width;
        r7 = getChildMeasureSpec(r12, r7, r8);
        r8 = r0.topMargin;
        r9 = r0.bottomMargin;
        r8 = r8 + r9;
        r0 = r0.height;
        r0 = getChildMeasureSpec(r13, r8, r0);
        r6.measure(r7, r0);
        goto L_0x003c;
    L_0x00da:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Child ";
        r1 = r1.append(r2);
        r1 = r1.append(r6);
        r2 = " at index ";
        r1 = r1.append(r2);
        r1 = r1.append(r3);
        r2 = " does not have a valid layout_gravity - must be Gravity.LEFT, ";
        r1 = r1.append(r2);
        r2 = "Gravity.RIGHT or Gravity.NO_GRAVITY";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0109:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.DrawerLayout.onMeasure(int, int):void");
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.f579a != 0) {
            View b = m1213b(savedState.f579a);
            if (b != null) {
                m1223h(b);
            }
        }
        setDrawerLockMode(savedState.f580b, 3);
        setDrawerLockMode(savedState.f581c, 5);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (m1222g(childAt)) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.f578d) {
                    savedState.f579a = layoutParams.f575a;
                    break;
                }
            }
        }
        savedState.f580b = this.f600m;
        savedState.f581c = this.f601n;
        return savedState;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f593f.m1437b(motionEvent);
        this.f594g.m1437b(motionEvent);
        float x;
        float y;
        switch (motionEvent.getAction() & 255) {
            case VideoSize.QCIF /*0*/:
                x = motionEvent.getX();
                y = motionEvent.getY();
                this.f605r = x;
                this.f606s = y;
                this.f602o = false;
                this.f603p = false;
                break;
            case VideoSize.CIF /*1*/:
                boolean z;
                x = motionEvent.getX();
                y = motionEvent.getY();
                View d = this.f593f.m1446d((int) x, (int) y);
                if (d != null && m1221f(d)) {
                    x -= this.f605r;
                    y -= this.f606s;
                    int d2 = this.f593f.m1445d();
                    if ((x * x) + (y * y) < ((float) (d2 * d2))) {
                        View a = m1207a();
                        if (a != null) {
                            z = m1206a(a) == 2;
                            m1211a(z);
                            this.f602o = false;
                            break;
                        }
                    }
                }
                z = true;
                m1211a(z);
                this.f602o = false;
            case Version.API03_CUPCAKE_15 /*3*/:
                m1211a(true);
                this.f602o = false;
                this.f603p = false;
                break;
        }
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        this.f602o = z;
        if (z) {
            m1211a(true);
        }
    }

    public void requestLayout() {
        if (!this.f598k) {
            super.requestLayout();
        }
    }

    public void setDrawerListener(C0022b c0022b) {
        this.f604q = c0022b;
    }

    public void setDrawerLockMode(int i) {
        setDrawerLockMode(i, 3);
        setDrawerLockMode(i, 5);
    }

    public void setDrawerLockMode(int i, int i2) {
        int a = GravityCompat.m996a(i2, ViewCompat.m1150e(this));
        if (a == 3) {
            this.f600m = i;
        } else if (a == 5) {
            this.f601n = i;
        }
        if (i != 0) {
            (a == 3 ? this.f593f : this.f594g).m1448e();
        }
        View b;
        switch (i) {
            case VideoSize.CIF /*1*/:
                b = m1213b(a);
                if (b != null) {
                    m1224i(b);
                }
            case VideoSize.HVGA /*2*/:
                b = m1213b(a);
                if (b != null) {
                    m1223h(b);
                }
            default:
        }
    }

    public void setDrawerLockMode(int i, View view) {
        if (m1222g(view)) {
            setDrawerLockMode(i, ((LayoutParams) view.getLayoutParams()).f575a);
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a " + "drawer with appropriate layout_gravity");
    }

    public void setDrawerShadow(int i, int i2) {
        setDrawerShadow(getResources().getDrawable(i), i2);
    }

    public void setDrawerShadow(Drawable drawable, int i) {
        int a = GravityCompat.m996a(i, ViewCompat.m1150e(this));
        if ((a & 3) == 3) {
            this.f607t = drawable;
            invalidate();
        }
        if ((a & 5) == 5) {
            this.f608u = drawable;
            invalidate();
        }
    }

    public void setDrawerTitle(int i, CharSequence charSequence) {
        int a = GravityCompat.m996a(i, ViewCompat.m1150e(this));
        if (a == 3) {
            this.f609v = charSequence;
        } else if (a == 5) {
            this.f610w = charSequence;
        }
    }

    public void setScrimColor(int i) {
        this.f590c = i;
        invalidate();
    }
}
