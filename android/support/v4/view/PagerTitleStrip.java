package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewPager.C0007a;
import android.support.v4.view.ViewPager.C0009d;
import android.support.v4.view.ViewPager.C0010e;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import org.linphone.mediastream.Version;

public class PagerTitleStrip extends ViewGroup implements C0007a {
    private static final int[] f433n;
    private static final int[] f434o;
    private static final C0012b f435q;
    ViewPager f436a;
    TextView f437b;
    TextView f438c;
    TextView f439d;
    int f440e;
    private int f441f;
    private float f442g;
    private int f443h;
    private int f444i;
    private boolean f445j;
    private boolean f446k;
    private final C0011a f447l;
    private WeakReference<PagerAdapter> f448m;
    private int f449p;

    /* renamed from: android.support.v4.view.PagerTitleStrip.a */
    private class C0011a extends DataSetObserver implements C0009d, C0010e {
        final /* synthetic */ PagerTitleStrip f466a;
        private int f467b;

        private C0011a(PagerTitleStrip pagerTitleStrip) {
            this.f466a = pagerTitleStrip;
        }

        public void m532a(int i) {
            float f = 0.0f;
            if (this.f467b == 0) {
                this.f466a.m523a(this.f466a.f436a.m599c(), this.f466a.f436a.m597b());
                if (this.f466a.f442g >= 0.0f) {
                    f = this.f466a.f442g;
                }
                this.f466a.m522a(this.f466a.f436a.m599c(), f, true);
            }
        }

        public void m533a(int i, float f, int i2) {
            if (f > 0.5f) {
                i++;
            }
            this.f466a.m522a(i, f, false);
        }

        public void m534a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
            this.f466a.m524a(pagerAdapter, pagerAdapter2);
        }

        public void m535b(int i) {
            this.f467b = i;
        }

        public void onChanged() {
            float f = 0.0f;
            this.f466a.m523a(this.f466a.f436a.m599c(), this.f466a.f436a.m597b());
            if (this.f466a.f442g >= 0.0f) {
                f = this.f466a.f442g;
            }
            this.f466a.m522a(this.f466a.f436a.m599c(), f, true);
        }
    }

    /* renamed from: android.support.v4.view.PagerTitleStrip.b */
    interface C0012b {
        void m536a(TextView textView);
    }

    /* renamed from: android.support.v4.view.PagerTitleStrip.c */
    static class C0013c implements C0012b {
        C0013c() {
        }

        public void m537a(TextView textView) {
            textView.setSingleLine();
        }
    }

    /* renamed from: android.support.v4.view.PagerTitleStrip.d */
    static class C0014d implements C0012b {
        C0014d() {
        }

        public void m538a(TextView textView) {
            PagerTitleStripIcs.m1079a(textView);
        }
    }

    static {
        f433n = new int[]{16842804, 16842901, 16842904, 16842927};
        f434o = new int[]{16843660};
        if (VERSION.SDK_INT >= 14) {
            f435q = new C0014d();
        } else {
            f435q = new C0013c();
        }
    }

    public PagerTitleStrip(Context context) {
        this(context, null);
    }

    public PagerTitleStrip(Context context, AttributeSet attributeSet) {
        boolean z = false;
        super(context, attributeSet);
        this.f441f = -1;
        this.f442g = -1.0f;
        this.f447l = new C0011a();
        View textView = new TextView(context);
        this.f437b = textView;
        addView(textView);
        textView = new TextView(context);
        this.f438c = textView;
        addView(textView);
        textView = new TextView(context);
        this.f439d = textView;
        addView(textView);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f433n);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            this.f437b.setTextAppearance(context, resourceId);
            this.f438c.setTextAppearance(context, resourceId);
            this.f439d.setTextAppearance(context, resourceId);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            setTextSize(0, (float) dimensionPixelSize);
        }
        if (obtainStyledAttributes.hasValue(2)) {
            dimensionPixelSize = obtainStyledAttributes.getColor(2, 0);
            this.f437b.setTextColor(dimensionPixelSize);
            this.f438c.setTextColor(dimensionPixelSize);
            this.f439d.setTextColor(dimensionPixelSize);
        }
        this.f444i = obtainStyledAttributes.getInteger(3, 80);
        obtainStyledAttributes.recycle();
        this.f440e = this.f438c.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        this.f437b.setEllipsize(TruncateAt.END);
        this.f438c.setEllipsize(TruncateAt.END);
        this.f439d.setEllipsize(TruncateAt.END);
        if (resourceId != 0) {
            obtainStyledAttributes = context.obtainStyledAttributes(resourceId, f434o);
            z = obtainStyledAttributes.getBoolean(0, false);
            obtainStyledAttributes.recycle();
        }
        if (z) {
            m520a(this.f437b);
            m520a(this.f438c);
            m520a(this.f439d);
        } else {
            this.f437b.setSingleLine();
            this.f438c.setSingleLine();
            this.f439d.setSingleLine();
        }
        this.f443h = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }

    private static void m520a(TextView textView) {
        f435q.m536a(textView);
    }

    int m521a() {
        Drawable background = getBackground();
        return background != null ? background.getIntrinsicHeight() : 0;
    }

    void m522a(int i, float f, boolean z) {
        if (i != this.f441f) {
            m523a(i, this.f436a.m597b());
        } else if (!z && f == this.f442g) {
            return;
        }
        this.f446k = true;
        int measuredWidth = this.f437b.getMeasuredWidth();
        int measuredWidth2 = this.f438c.getMeasuredWidth();
        int measuredWidth3 = this.f439d.getMeasuredWidth();
        int i2 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i3 = paddingRight + i2;
        int i4 = (width - (paddingLeft + i2)) - i3;
        float f2 = 0.5f + f;
        if (f2 > 1.0f) {
            f2 -= 1.0f;
        }
        i4 = ((width - i3) - ((int) (f2 * ((float) i4)))) - (measuredWidth2 / 2);
        i3 = i4 + measuredWidth2;
        i2 = this.f437b.getBaseline();
        measuredWidth2 = this.f438c.getBaseline();
        int baseline = this.f439d.getBaseline();
        int max = Math.max(Math.max(i2, measuredWidth2), baseline);
        i2 = max - i2;
        measuredWidth2 = max - measuredWidth2;
        baseline = max - baseline;
        max = this.f437b.getMeasuredHeight() + i2;
        int measuredHeight = this.f438c.getMeasuredHeight() + measuredWidth2;
        max = Math.max(Math.max(max, measuredHeight), this.f439d.getMeasuredHeight() + baseline);
        switch (this.f444i & 112) {
            case Version.API16_JELLY_BEAN_41 /*16*/:
                paddingTop = (((height - paddingTop) - paddingBottom) - max) / 2;
                height = paddingTop + i2;
                measuredWidth2 += paddingTop;
                i2 = paddingTop + baseline;
                break;
            case 80:
                paddingTop = (height - paddingBottom) - max;
                height = paddingTop + i2;
                measuredWidth2 += paddingTop;
                i2 = paddingTop + baseline;
                break;
            default:
                height = paddingTop + i2;
                measuredWidth2 += paddingTop;
                i2 = paddingTop + baseline;
                break;
        }
        this.f438c.layout(i4, measuredWidth2, i3, this.f438c.getMeasuredHeight() + measuredWidth2);
        measuredWidth2 = Math.min(paddingLeft, (i4 - this.f443h) - measuredWidth);
        this.f437b.layout(measuredWidth2, height, measuredWidth + measuredWidth2, this.f437b.getMeasuredHeight() + height);
        measuredWidth2 = Math.max((width - paddingRight) - measuredWidth3, this.f443h + i3);
        this.f439d.layout(measuredWidth2, i2, measuredWidth2 + measuredWidth3, this.f439d.getMeasuredHeight() + i2);
        this.f442g = f;
        this.f446k = false;
    }

    void m523a(int i, PagerAdapter pagerAdapter) {
        CharSequence charSequence = null;
        int b = pagerAdapter != null ? pagerAdapter.m328b() : 0;
        this.f445j = true;
        CharSequence c = (i < 1 || pagerAdapter == null) ? null : pagerAdapter.m334c(i - 1);
        this.f437b.setText(c);
        TextView textView = this.f438c;
        c = (pagerAdapter == null || i >= b) ? null : pagerAdapter.m334c(i);
        textView.setText(c);
        if (i + 1 < b && pagerAdapter != null) {
            charSequence = pagerAdapter.m334c(i + 1);
        }
        this.f439d.setText(charSequence);
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        b = MeasureSpec.makeMeasureSpec((int) (((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) * 0.8f), Integer.MIN_VALUE);
        height = MeasureSpec.makeMeasureSpec(height, Integer.MIN_VALUE);
        this.f437b.measure(b, height);
        this.f438c.measure(b, height);
        this.f439d.measure(b, height);
        this.f441f = i;
        if (!this.f446k) {
            m522a(i, this.f442g, false);
        }
        this.f445j = false;
    }

    void m524a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
        if (pagerAdapter != null) {
            pagerAdapter.m329b(this.f447l);
            this.f448m = null;
        }
        if (pagerAdapter2 != null) {
            pagerAdapter2.m321a(this.f447l);
            this.f448m = new WeakReference(pagerAdapter2);
        }
        if (this.f436a != null) {
            this.f441f = -1;
            this.f442g = -1.0f;
            m523a(this.f436a.m599c(), pagerAdapter2);
            requestLayout();
        }
    }

    public int m525b() {
        return this.f443h;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) parent;
            PagerAdapter b = viewPager.m597b();
            viewPager.m584a(this.f447l);
            viewPager.m591a(this.f447l);
            this.f436a = viewPager;
            m524a(this.f448m != null ? (PagerAdapter) this.f448m.get() : null, b);
            return;
        }
        throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f436a != null) {
            m524a(this.f436a.m597b(), null);
            this.f436a.m584a(null);
            this.f436a.m591a(null);
            this.f436a = null;
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f = 0.0f;
        if (this.f436a != null) {
            if (this.f442g >= 0.0f) {
                f = this.f442g;
            }
            m522a(this.f441f, f, true);
        }
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        mode = m521a();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int i3 = size2 - paddingTop;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec((int) (((float) size) * 0.8f), Integer.MIN_VALUE);
        i3 = MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
        this.f437b.measure(makeMeasureSpec, i3);
        this.f438c.measure(makeMeasureSpec, i3);
        this.f439d.measure(makeMeasureSpec, i3);
        if (mode2 == 1073741824) {
            setMeasuredDimension(size, size2);
        } else {
            setMeasuredDimension(size, Math.max(mode, this.f438c.getMeasuredHeight() + paddingTop));
        }
    }

    public void requestLayout() {
        if (!this.f445j) {
            super.requestLayout();
        }
    }

    public void setGravity(int i) {
        this.f444i = i;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float f) {
        this.f449p = ((int) (255.0f * f)) & 255;
        int i = (this.f449p << 24) | (this.f440e & 16777215);
        this.f437b.setTextColor(i);
        this.f439d.setTextColor(i);
    }

    public void setTextColor(int i) {
        this.f440e = i;
        this.f438c.setTextColor(i);
        int i2 = (this.f449p << 24) | (this.f440e & 16777215);
        this.f437b.setTextColor(i2);
        this.f439d.setTextColor(i2);
    }

    public void setTextSize(int i, float f) {
        this.f437b.setTextSize(i, f);
        this.f438c.setTextSize(i, f);
        this.f439d.setTextSize(i, f);
    }

    public void setTextSpacing(int i) {
        this.f443h = i;
        requestLayout();
    }
}
