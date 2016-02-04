package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.p009a.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ViewDragHelper.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.apache.http.HttpStatus;
import org.linphone.core.VideoSize;

public class SlidingPaneLayout extends ViewGroup {
    static final C0029e f624a;
    private int f625b;
    private int f626c;
    private Drawable f627d;
    private final int f628e;
    private boolean f629f;
    private View f630g;
    private float f631h;
    private float f632i;
    private int f633j;
    private boolean f634k;
    private int f635l;
    private float f636m;
    private float f637n;
    private C0028d f638o;
    private final ViewDragHelper f639p;
    private boolean f640q;
    private boolean f641r;
    private final Rect f642s;
    private final ArrayList<C0026b> f643t;

    public static class LayoutParams extends MarginLayoutParams {
        private static final int[] f611e;
        public float f612a;
        boolean f613b;
        boolean f614c;
        Paint f615d;

        static {
            f611e = new int[]{16843137};
        }

        public LayoutParams() {
            super(-1, -1);
            this.f612a = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f612a = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f611e);
            this.f612a = obtainStyledAttributes.getFloat(0, 0.0f);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f612a = 0.0f;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f612a = 0.0f;
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        boolean f616a;

        static {
            CREATOR = new SlidingPaneLayout();
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f616a = parcel.readInt() != 0;
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f616a ? 1 : 0);
        }
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.a */
    class C0025a extends AccessibilityDelegateCompat {
        final /* synthetic */ SlidingPaneLayout f617b;
        private final Rect f618c;

        C0025a(SlidingPaneLayout slidingPaneLayout) {
            this.f617b = slidingPaneLayout;
            this.f618c = new Rect();
        }

        private void m1226a(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
            Rect rect = this.f618c;
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
            accessibilityNodeInfoCompat.m778b(accessibilityNodeInfoCompat2.m783c());
        }

        public void m1227a(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            AccessibilityNodeInfoCompat a = AccessibilityNodeInfoCompat.m768a(accessibilityNodeInfoCompat);
            super.m542a(view, a);
            m1226a(accessibilityNodeInfoCompat, a);
            a.m811t();
            accessibilityNodeInfoCompat.m781b(SlidingPaneLayout.class.getName());
            accessibilityNodeInfoCompat.m774a(view);
            ViewParent f = ViewCompat.m1151f(view);
            if (f instanceof View) {
                accessibilityNodeInfoCompat.m785c((View) f);
            }
            int childCount = this.f617b.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.f617b.getChildAt(i);
                if (!m1229b(childAt) && childAt.getVisibility() == 0) {
                    ViewCompat.m1148c(childAt, 1);
                    accessibilityNodeInfoCompat.m780b(childAt);
                }
            }
        }

        public boolean m1228a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return !m1229b(view) ? super.m545a(viewGroup, view, accessibilityEvent) : false;
        }

        public boolean m1229b(View view) {
            return this.f617b.m1272e(view);
        }

        public void m1230d(View view, AccessibilityEvent accessibilityEvent) {
            super.m548d(view, accessibilityEvent);
            accessibilityEvent.setClassName(SlidingPaneLayout.class.getName());
        }
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.b */
    private class C0026b implements Runnable {
        final View f619a;
        final /* synthetic */ SlidingPaneLayout f620b;

        C0026b(SlidingPaneLayout slidingPaneLayout, View view) {
            this.f620b = slidingPaneLayout;
            this.f619a = view;
        }

        public void run() {
            if (this.f619a.getParent() == this.f620b) {
                ViewCompat.m1140a(this.f619a, 0, null);
                this.f620b.m1261g(this.f619a);
            }
            this.f620b.f643t.remove(this);
        }
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.c */
    private class C0027c extends ViewDragHelper {
        final /* synthetic */ SlidingPaneLayout f621a;

        private C0027c(SlidingPaneLayout slidingPaneLayout) {
            this.f621a = slidingPaneLayout;
        }

        public int m1231a(View view) {
            return this.f621a.f633j;
        }

        public int m1232a(View view, int i, int i2) {
            LayoutParams layoutParams = (LayoutParams) this.f621a.f630g.getLayoutParams();
            int paddingLeft = layoutParams.leftMargin + this.f621a.getPaddingLeft();
            return Math.min(Math.max(i, paddingLeft), this.f621a.f633j + paddingLeft);
        }

        public void m1233a(int i) {
            if (this.f621a.f639p.m1428a() != 0) {
                return;
            }
            if (this.f621a.f631h == 0.0f) {
                this.f621a.m1269d(this.f621a.f630g);
                this.f621a.m1267c(this.f621a.f630g);
                this.f621a.f640q = false;
                return;
            }
            this.f621a.m1265b(this.f621a.f630g);
            this.f621a.f640q = true;
        }

        public void m1234a(View view, float f, float f2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int paddingLeft = layoutParams.leftMargin + this.f621a.getPaddingLeft();
            if (f > 0.0f || (f == 0.0f && this.f621a.f631h > 0.5f)) {
                paddingLeft += this.f621a.f633j;
            }
            this.f621a.f639p.m1432a(paddingLeft, view.getTop());
            this.f621a.invalidate();
        }

        public void m1235a(View view, int i, int i2, int i3, int i4) {
            this.f621a.m1247a(i);
            this.f621a.invalidate();
        }

        public boolean m1236a(View view, int i) {
            return this.f621a.f634k ? false : ((LayoutParams) view.getLayoutParams()).f613b;
        }

        public void m1237b(int i, int i2) {
            this.f621a.f639p.m1431a(this.f621a.f630g, i2);
        }

        public void m1238b(View view, int i) {
            this.f621a.m1262a();
        }
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.d */
    public interface C0028d {
        void m1239a(View view);

        void m1240a(View view, float f);

        void m1241b(View view);
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.e */
    interface C0029e {
        void m1242a(SlidingPaneLayout slidingPaneLayout, View view);
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.f */
    static class C0030f implements C0029e {
        C0030f() {
        }

        public void m1243a(SlidingPaneLayout slidingPaneLayout, View view) {
            ViewCompat.m1139a(slidingPaneLayout, view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.g */
    static class C0031g extends C0030f {
        private Method f622a;
        private Field f623b;

        C0031g() {
            try {
                this.f622a = View.class.getDeclaredMethod("getDisplayList", (Class[]) null);
            } catch (Throwable e) {
                Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", e);
            }
            try {
                this.f623b = View.class.getDeclaredField("mRecreateDisplayList");
                this.f623b.setAccessible(true);
            } catch (Throwable e2) {
                Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", e2);
            }
        }

        public void m1244a(SlidingPaneLayout slidingPaneLayout, View view) {
            if (this.f622a == null || this.f623b == null) {
                view.invalidate();
                return;
            }
            try {
                this.f623b.setBoolean(view, true);
                this.f622a.invoke(view, (Object[]) null);
            } catch (Throwable e) {
                Log.e("SlidingPaneLayout", "Error refreshing display list state", e);
            }
            super.m1243a(slidingPaneLayout, view);
        }
    }

    /* renamed from: android.support.v4.widget.SlidingPaneLayout.h */
    static class C0032h extends C0030f {
        C0032h() {
        }

        public void m1245a(SlidingPaneLayout slidingPaneLayout, View view) {
            ViewCompat.m1141a(view, ((LayoutParams) view.getLayoutParams()).f615d);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 17) {
            f624a = new C0032h();
        } else if (i >= 16) {
            f624a = new C0031g();
        } else {
            f624a = new C0030f();
        }
    }

    public SlidingPaneLayout(Context context) {
        this(context, null);
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f625b = -858993460;
        this.f641r = true;
        this.f642s = new Rect();
        this.f643t = new ArrayList();
        float f = context.getResources().getDisplayMetrics().density;
        this.f628e = (int) ((32.0f * f) + 0.5f);
        ViewConfiguration.get(context);
        setWillNotDraw(false);
        ViewCompat.m1142a((View) this, new C0025a(this));
        ViewCompat.m1148c(this, 1);
        this.f639p = ViewDragHelper.m1411a((ViewGroup) this, 0.5f, new C0027c());
        this.f639p.m1430a(1);
        this.f639p.m1429a(f * 400.0f);
    }

    private void m1246a(float f) {
        int i = 0;
        LayoutParams layoutParams = (LayoutParams) this.f630g.getLayoutParams();
        int i2 = (!layoutParams.f614c || layoutParams.leftMargin > 0) ? 0 : 1;
        int childCount = getChildCount();
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (childAt != this.f630g) {
                int i3 = (int) ((1.0f - this.f632i) * ((float) this.f635l));
                this.f632i = f;
                childAt.offsetLeftAndRight(i3 - ((int) ((1.0f - f) * ((float) this.f635l))));
                if (i2 != 0) {
                    m1250a(childAt, 1.0f - this.f632i, this.f626c);
                }
            }
            i++;
        }
    }

    private void m1247a(int i) {
        if (this.f630g == null) {
            this.f631h = 0.0f;
            return;
        }
        LayoutParams layoutParams = (LayoutParams) this.f630g.getLayoutParams();
        this.f631h = ((float) (i - (getPaddingLeft() + layoutParams.leftMargin))) / ((float) this.f633j);
        if (this.f635l != 0) {
            m1246a(this.f631h);
        }
        if (layoutParams.f614c) {
            m1250a(this.f630g, this.f631h, this.f625b);
        }
        m1263a(this.f630g);
    }

    private void m1250a(View view, float f, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f > 0.0f && i != 0) {
            int i2 = (((int) (((float) ((-16777216 & i) >>> 24)) * f)) << 24) | (16777215 & i);
            if (layoutParams.f615d == null) {
                layoutParams.f615d = new Paint();
            }
            layoutParams.f615d.setColorFilter(new PorterDuffColorFilter(i2, Mode.SRC_OVER));
            if (ViewCompat.m1149d(view) != 2) {
                ViewCompat.m1140a(view, 2, layoutParams.f615d);
            }
            m1261g(view);
        } else if (ViewCompat.m1149d(view) != 0) {
            if (layoutParams.f615d != null) {
                layoutParams.f615d.setColorFilter(null);
            }
            Runnable c0026b = new C0026b(this, view);
            this.f643t.add(c0026b);
            ViewCompat.m1143a((View) this, c0026b);
        }
    }

    private boolean m1253a(View view, int i) {
        if (!this.f641r && !m1264a(0.0f, i)) {
            return false;
        }
        this.f640q = false;
        return true;
    }

    private boolean m1255b(View view, int i) {
        if (!this.f641r && !m1264a(1.0f, i)) {
            return false;
        }
        this.f640q = true;
        return true;
    }

    private static boolean m1260f(View view) {
        if (ViewCompat.m1152g(view)) {
            return true;
        }
        if (VERSION.SDK_INT >= 18) {
            return false;
        }
        Drawable background = view.getBackground();
        return background != null ? background.getOpacity() == -1 : false;
    }

    private void m1261g(View view) {
        f624a.m1242a(this, view);
    }

    void m1262a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    void m1263a(View view) {
        if (this.f638o != null) {
            this.f638o.m1240a(view, this.f631h);
        }
    }

    boolean m1264a(float f, int i) {
        if (!this.f629f) {
            return false;
        }
        LayoutParams layoutParams = (LayoutParams) this.f630g.getLayoutParams();
        if (!this.f639p.m1434a(this.f630g, (int) (((float) (layoutParams.leftMargin + getPaddingLeft())) + (((float) this.f633j) * f)), this.f630g.getTop())) {
            return false;
        }
        m1262a();
        ViewCompat.m1145b(this);
        return true;
    }

    void m1265b(View view) {
        if (this.f638o != null) {
            this.f638o.m1239a(view);
        }
        sendAccessibilityEvent(32);
    }

    public boolean m1266b() {
        return m1255b(this.f630g, 0);
    }

    void m1267c(View view) {
        if (this.f638o != null) {
            this.f638o.m1241b(view);
        }
        sendAccessibilityEvent(32);
    }

    public boolean m1268c() {
        return m1253a(this.f630g, 0);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public void computeScroll() {
        if (!this.f639p.m1435a(true)) {
            return;
        }
        if (this.f629f) {
            ViewCompat.m1145b(this);
        } else {
            this.f639p.m1449f();
        }
    }

    void m1269d(View view) {
        int i;
        int i2;
        int i3;
        int i4;
        int paddingLeft = getPaddingLeft();
        int width = getWidth() - getPaddingRight();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        if (view == null || !m1260f(view)) {
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
        } else {
            i4 = view.getLeft();
            i3 = view.getRight();
            i2 = view.getTop();
            i = view.getBottom();
        }
        int childCount = getChildCount();
        int i5 = 0;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            if (childAt != view) {
                int i6 = (Math.max(paddingLeft, childAt.getLeft()) < i4 || Math.max(paddingTop, childAt.getTop()) < i2 || Math.min(width, childAt.getRight()) > i3 || Math.min(height, childAt.getBottom()) > i) ? 0 : 4;
                childAt.setVisibility(i6);
                i5++;
            } else {
                return;
            }
        }
    }

    public boolean m1270d() {
        return !this.f629f || this.f631h == 1.0f;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt != null && this.f627d != null) {
            int intrinsicWidth = this.f627d.getIntrinsicWidth();
            int left = childAt.getLeft();
            this.f627d.setBounds(left - intrinsicWidth, childAt.getTop(), left, childAt.getBottom());
            this.f627d.draw(canvas);
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        boolean drawChild;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int save = canvas.save(2);
        if (!(!this.f629f || layoutParams.f613b || this.f630g == null)) {
            canvas.getClipBounds(this.f642s);
            this.f642s.right = Math.min(this.f642s.right, this.f630g.getLeft());
            canvas.clipRect(this.f642s);
        }
        if (VERSION.SDK_INT >= 11) {
            drawChild = super.drawChild(canvas, view, j);
        } else if (!layoutParams.f614c || this.f631h <= 0.0f) {
            if (view.isDrawingCacheEnabled()) {
                view.setDrawingCacheEnabled(false);
            }
            drawChild = super.drawChild(canvas, view, j);
        } else {
            if (!view.isDrawingCacheEnabled()) {
                view.setDrawingCacheEnabled(true);
            }
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                canvas.drawBitmap(drawingCache, (float) view.getLeft(), (float) view.getTop(), layoutParams.f615d);
                drawChild = false;
            } else {
                Log.e("SlidingPaneLayout", "drawChild: child view " + view + " returned null drawing cache");
                drawChild = super.drawChild(canvas, view, j);
            }
        }
        canvas.restoreToCount(save);
        return drawChild;
    }

    public boolean m1271e() {
        return this.f629f;
    }

    boolean m1272e(View view) {
        if (view == null) {
            return false;
        }
        boolean z = this.f629f && ((LayoutParams) view.getLayoutParams()).f614c && this.f631h > 0.0f;
        return z;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f641r = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f641r = true;
        int size = this.f643t.size();
        for (int i = 0; i < size; i++) {
            ((C0026b) this.f643t.get(i)).run();
        }
        this.f643t.clear();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r8) {
        /*
        r7 = this;
        r2 = 0;
        r1 = 1;
        r3 = android.support.v4.view.MotionEventCompat.m1067a(r8);
        r0 = r7.f629f;
        if (r0 != 0) goto L_0x002d;
    L_0x000a:
        if (r3 != 0) goto L_0x002d;
    L_0x000c:
        r0 = r7.getChildCount();
        if (r0 <= r1) goto L_0x002d;
    L_0x0012:
        r0 = r7.getChildAt(r1);
        if (r0 == 0) goto L_0x002d;
    L_0x0018:
        r4 = r7.f639p;
        r5 = r8.getX();
        r5 = (int) r5;
        r6 = r8.getY();
        r6 = (int) r6;
        r0 = r4.m1441b(r0, r5, r6);
        if (r0 != 0) goto L_0x0041;
    L_0x002a:
        r0 = r1;
    L_0x002b:
        r7.f640q = r0;
    L_0x002d:
        r0 = r7.f629f;
        if (r0 == 0) goto L_0x0037;
    L_0x0031:
        r0 = r7.f634k;
        if (r0 == 0) goto L_0x0043;
    L_0x0035:
        if (r3 == 0) goto L_0x0043;
    L_0x0037:
        r0 = r7.f639p;
        r0.m1448e();
        r2 = super.onInterceptTouchEvent(r8);
    L_0x0040:
        return r2;
    L_0x0041:
        r0 = r2;
        goto L_0x002b;
    L_0x0043:
        r0 = 3;
        if (r3 == r0) goto L_0x0048;
    L_0x0046:
        if (r3 != r1) goto L_0x004e;
    L_0x0048:
        r0 = r7.f639p;
        r0.m1448e();
        goto L_0x0040;
    L_0x004e:
        switch(r3) {
            case 0: goto L_0x005e;
            case 1: goto L_0x0051;
            case 2: goto L_0x0082;
            default: goto L_0x0051;
        };
    L_0x0051:
        r0 = r2;
    L_0x0052:
        r3 = r7.f639p;
        r3 = r3.m1433a(r8);
        if (r3 != 0) goto L_0x005c;
    L_0x005a:
        if (r0 == 0) goto L_0x0040;
    L_0x005c:
        r2 = r1;
        goto L_0x0040;
    L_0x005e:
        r7.f634k = r2;
        r0 = r8.getX();
        r3 = r8.getY();
        r7.f636m = r0;
        r7.f637n = r3;
        r4 = r7.f639p;
        r5 = r7.f630g;
        r0 = (int) r0;
        r3 = (int) r3;
        r0 = r4.m1441b(r5, r0, r3);
        if (r0 == 0) goto L_0x0051;
    L_0x0078:
        r0 = r7.f630g;
        r0 = r7.m1272e(r0);
        if (r0 == 0) goto L_0x0051;
    L_0x0080:
        r0 = r1;
        goto L_0x0052;
    L_0x0082:
        r0 = r8.getX();
        r3 = r8.getY();
        r4 = r7.f636m;
        r0 = r0 - r4;
        r0 = java.lang.Math.abs(r0);
        r4 = r7.f637n;
        r3 = r3 - r4;
        r3 = java.lang.Math.abs(r3);
        r4 = r7.f639p;
        r4 = r4.m1445d();
        r4 = (float) r4;
        r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x0051;
    L_0x00a3:
        r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1));
        if (r0 <= 0) goto L_0x0051;
    L_0x00a7:
        r0 = r7.f639p;
        r0.m1448e();
        r7.f634k = r1;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.SlidingPaneLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.f641r) {
            float f = (this.f629f && this.f640q) ? 1.0f : 0.0f;
            this.f631h = f;
        }
        int i6 = 0;
        int i7 = paddingLeft;
        while (i6 < childCount) {
            int i8;
            int i9;
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() == 8) {
                i8 = i7;
            } else {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                if (layoutParams.f613b) {
                    int min = (Math.min(paddingLeft, (i5 - paddingRight) - this.f628e) - i7) - (layoutParams.leftMargin + layoutParams.rightMargin);
                    this.f633j = min;
                    layoutParams.f614c = ((layoutParams.leftMargin + i7) + min) + (measuredWidth / 2) > i5 - paddingRight;
                    i8 = (int) (((float) min) * this.f631h);
                    i9 = (layoutParams.leftMargin + i8) + i7;
                    this.f631h = ((float) i8) / ((float) this.f633j);
                    i8 = i9;
                    i9 = 0;
                } else if (!this.f629f || this.f635l == 0) {
                    i9 = 0;
                    i8 = paddingLeft;
                } else {
                    i9 = (int) ((1.0f - this.f631h) * ((float) this.f635l));
                    i8 = paddingLeft;
                }
                i9 = i8 - i9;
                childAt.layout(i9, paddingTop, i9 + measuredWidth, childAt.getMeasuredHeight() + paddingTop);
                paddingLeft += childAt.getWidth();
            }
            i6++;
            i7 = i8;
        }
        if (this.f641r) {
            if (this.f629f) {
                if (this.f635l != 0) {
                    m1246a(this.f631h);
                }
                if (((LayoutParams) this.f630g.getLayoutParams()).f614c) {
                    m1250a(this.f630g, this.f631h, this.f625b);
                }
            } else {
                for (i9 = 0; i9 < childCount; i9++) {
                    m1250a(getChildAt(i9), 0.0f, this.f625b);
                }
            }
            m1269d(this.f630g);
        }
        this.f641r = false;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            if (mode2 == 0) {
                if (!isInEditMode()) {
                    throw new IllegalStateException("Height must not be UNSPECIFIED");
                } else if (mode2 == 0) {
                    i3 = Integer.MIN_VALUE;
                    i4 = size;
                    size = HttpStatus.SC_MULTIPLE_CHOICES;
                }
            }
            i3 = mode2;
            i4 = size;
            size = size2;
        } else if (!isInEditMode()) {
            throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
        } else if (mode == Integer.MIN_VALUE) {
            i3 = mode2;
            i4 = size;
            size = size2;
        } else {
            if (mode == 0) {
                i3 = mode2;
                i4 = HttpStatus.SC_MULTIPLE_CHOICES;
                size = size2;
            }
            i3 = mode2;
            i4 = size;
            size = size2;
        }
        switch (i3) {
            case Integer.MIN_VALUE:
                size2 = 0;
                mode2 = (size - getPaddingTop()) - getPaddingBottom();
                break;
            case 1073741824:
                size2 = (size - getPaddingTop()) - getPaddingBottom();
                mode2 = size2;
                break;
            default:
                size2 = 0;
                mode2 = -1;
                break;
        }
        boolean z = false;
        int paddingLeft = (i4 - getPaddingLeft()) - getPaddingRight();
        int childCount = getChildCount();
        if (childCount > 2) {
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        }
        this.f630g = null;
        int i5 = 0;
        int i6 = size2;
        float f = 0.0f;
        while (i5 < childCount) {
            float f2;
            int i7;
            boolean z2;
            View childAt = getChildAt(i5);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (childAt.getVisibility() == 8) {
                layoutParams.f614c = false;
                size2 = paddingLeft;
                f2 = f;
                i7 = i6;
                z2 = z;
            } else {
                if (layoutParams.f612a > 0.0f) {
                    f += layoutParams.f612a;
                    if (layoutParams.width == 0) {
                        size2 = paddingLeft;
                        f2 = f;
                        i7 = i6;
                        z2 = z;
                    }
                }
                mode = layoutParams.leftMargin + layoutParams.rightMargin;
                mode = layoutParams.width == -2 ? MeasureSpec.makeMeasureSpec(i4 - mode, Integer.MIN_VALUE) : layoutParams.width == -1 ? MeasureSpec.makeMeasureSpec(i4 - mode, 1073741824) : MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824);
                i7 = layoutParams.height == -2 ? MeasureSpec.makeMeasureSpec(mode2, Integer.MIN_VALUE) : layoutParams.height == -1 ? MeasureSpec.makeMeasureSpec(mode2, 1073741824) : MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
                childAt.measure(mode, i7);
                mode = childAt.getMeasuredWidth();
                i7 = childAt.getMeasuredHeight();
                if (i3 == Integer.MIN_VALUE && i7 > i6) {
                    i6 = Math.min(i7, mode2);
                }
                i7 = paddingLeft - mode;
                boolean z3 = i7 < 0;
                layoutParams.f613b = z3;
                z3 |= z;
                if (layoutParams.f613b) {
                    this.f630g = childAt;
                }
                size2 = i7;
                i7 = i6;
                float f3 = f;
                z2 = z3;
                f2 = f3;
            }
            i5++;
            z = z2;
            i6 = i7;
            f = f2;
            paddingLeft = size2;
        }
        if (z || f > 0.0f) {
            int i8 = i4 - this.f628e;
            for (i3 = 0; i3 < childCount; i3++) {
                View childAt2 = getChildAt(i3);
                if (childAt2.getVisibility() != 8) {
                    layoutParams = (LayoutParams) childAt2.getLayoutParams();
                    if (childAt2.getVisibility() != 8) {
                        Object obj = (layoutParams.width != 0 || layoutParams.f612a <= 0.0f) ? null : 1;
                        i7 = obj != null ? 0 : childAt2.getMeasuredWidth();
                        if (!z || childAt2 == this.f630g) {
                            if (layoutParams.f612a > 0.0f) {
                                mode = layoutParams.width == 0 ? layoutParams.height == -2 ? MeasureSpec.makeMeasureSpec(mode2, Integer.MIN_VALUE) : layoutParams.height == -1 ? MeasureSpec.makeMeasureSpec(mode2, 1073741824) : MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824) : MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                                if (z) {
                                    size2 = i4 - (layoutParams.rightMargin + layoutParams.leftMargin);
                                    i5 = MeasureSpec.makeMeasureSpec(size2, 1073741824);
                                    if (i7 != size2) {
                                        childAt2.measure(i5, mode);
                                    }
                                } else {
                                    childAt2.measure(MeasureSpec.makeMeasureSpec(((int) ((layoutParams.f612a * ((float) Math.max(0, paddingLeft))) / f)) + i7, 1073741824), mode);
                                }
                            }
                        } else if (layoutParams.width < 0 && (i7 > i8 || layoutParams.f612a > 0.0f)) {
                            size2 = obj != null ? layoutParams.height == -2 ? MeasureSpec.makeMeasureSpec(mode2, Integer.MIN_VALUE) : layoutParams.height == -1 ? MeasureSpec.makeMeasureSpec(mode2, 1073741824) : MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824) : MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                            childAt2.measure(MeasureSpec.makeMeasureSpec(i8, 1073741824), size2);
                        }
                    }
                }
            }
        }
        setMeasuredDimension(i4, i6);
        this.f629f = z;
        if (this.f639p.m1428a() != 0 && !z) {
            this.f639p.m1449f();
        }
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.f616a) {
            m1266b();
        } else {
            m1268c();
        }
        this.f640q = savedState.f616a;
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f616a = m1271e() ? m1270d() : this.f640q;
        return savedState;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            this.f641r = true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f629f) {
            return super.onTouchEvent(motionEvent);
        }
        this.f639p.m1437b(motionEvent);
        float x;
        float y;
        switch (motionEvent.getAction() & 255) {
            case VideoSize.QCIF /*0*/:
                x = motionEvent.getX();
                y = motionEvent.getY();
                this.f636m = x;
                this.f637n = y;
                return true;
            case VideoSize.CIF /*1*/:
                if (!m1272e(this.f630g)) {
                    return true;
                }
                x = motionEvent.getX();
                y = motionEvent.getY();
                float f = x - this.f636m;
                float f2 = y - this.f637n;
                int d = this.f639p.m1445d();
                if ((f * f) + (f2 * f2) >= ((float) (d * d)) || !this.f639p.m1441b(this.f630g, (int) x, (int) y)) {
                    return true;
                }
                m1253a(this.f630g, 0);
                return true;
            default:
                return true;
        }
    }

    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (!isInTouchMode() && !this.f629f) {
            this.f640q = view == this.f630g;
        }
    }

    public void setCoveredFadeColor(int i) {
        this.f626c = i;
    }

    public void setPanelSlideListener(C0028d c0028d) {
        this.f638o = c0028d;
    }

    public void setParallaxDistance(int i) {
        this.f635l = i;
        requestLayout();
    }

    public void setShadowDrawable(Drawable drawable) {
        this.f627d = drawable;
    }

    public void setShadowResource(int i) {
        setShadowDrawable(getResources().getDrawable(i));
    }

    public void setSliderFadeColor(int i) {
        this.f625b = i;
    }
}
