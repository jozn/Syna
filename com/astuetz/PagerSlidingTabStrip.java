package com.astuetz;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.C0010e;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.astuetz.p012a.R.R;
import java.util.Locale;

public class PagerSlidingTabStrip extends HorizontalScrollView {
    private static final int[] f1391b;
    private int f1392A;
    private int f1393B;
    private int f1394C;
    private Locale f1395D;
    public C0010e f1396a;
    private LayoutParams f1397c;
    private LayoutParams f1398d;
    private final C0068b f1399e;
    private LinearLayout f1400f;
    private ViewPager f1401g;
    private int f1402h;
    private int f1403i;
    private float f1404j;
    private Paint f1405k;
    private Paint f1406l;
    private int f1407m;
    private int f1408n;
    private int f1409o;
    private boolean f1410p;
    private boolean f1411q;
    private int f1412r;
    private int f1413s;
    private int f1414t;
    private int f1415u;
    private int f1416v;
    private int f1417w;
    private int f1418x;
    private int f1419y;
    private Typeface f1420z;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int f1389a;

        static {
            CREATOR = new PagerSlidingTabStrip();
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f1389a = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1389a);
        }
    }

    /* renamed from: com.astuetz.PagerSlidingTabStrip.a */
    public interface C0067a {
        int m2196a(int i);
    }

    /* renamed from: com.astuetz.PagerSlidingTabStrip.b */
    private class C0068b implements C0010e {
        final /* synthetic */ PagerSlidingTabStrip f1390a;

        private C0068b(PagerSlidingTabStrip pagerSlidingTabStrip) {
            this.f1390a = pagerSlidingTabStrip;
        }

        public void m2197a(int i) {
            if (this.f1390a.f1396a != null) {
                this.f1390a.f1396a.m529a(i);
            }
        }

        public void m2198a(int i, float f, int i2) {
            this.f1390a.f1403i = i;
            this.f1390a.f1404j = f;
            this.f1390a.m2209b(i, (int) (((float) this.f1390a.f1400f.getChildAt(i).getWidth()) * f));
            this.f1390a.invalidate();
            if (this.f1390a.f1396a != null) {
                this.f1390a.f1396a.m530a(i, f, i2);
            }
        }

        public void m2199b(int i) {
            if (i == 0) {
                this.f1390a.m2209b(this.f1390a.f1401g.m599c(), 0);
            }
            if (this.f1390a.f1396a != null) {
                this.f1390a.f1396a.m531b(i);
            }
        }
    }

    static {
        f1391b = new int[]{16842901, 16842904};
    }

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1399e = new C0068b();
        this.f1403i = 0;
        this.f1404j = 0.0f;
        this.f1407m = -10066330;
        this.f1408n = 436207616;
        this.f1409o = 436207616;
        this.f1410p = false;
        this.f1411q = true;
        this.f1412r = 52;
        this.f1413s = 8;
        this.f1414t = 2;
        this.f1415u = 12;
        this.f1416v = 24;
        this.f1417w = 1;
        this.f1418x = 12;
        this.f1419y = -10066330;
        this.f1420z = null;
        this.f1392A = 1;
        this.f1393B = 0;
        this.f1394C = R.background_tab;
        setFillViewport(true);
        setWillNotDraw(false);
        this.f1400f = new LinearLayout(context);
        this.f1400f.setOrientation(0);
        this.f1400f.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.f1400f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f1412r = (int) TypedValue.applyDimension(1, (float) this.f1412r, displayMetrics);
        this.f1413s = (int) TypedValue.applyDimension(1, (float) this.f1413s, displayMetrics);
        this.f1414t = (int) TypedValue.applyDimension(1, (float) this.f1414t, displayMetrics);
        this.f1415u = (int) TypedValue.applyDimension(1, (float) this.f1415u, displayMetrics);
        this.f1416v = (int) TypedValue.applyDimension(1, (float) this.f1416v, displayMetrics);
        this.f1417w = (int) TypedValue.applyDimension(1, (float) this.f1417w, displayMetrics);
        this.f1418x = (int) TypedValue.applyDimension(2, (float) this.f1418x, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1391b);
        this.f1418x = obtainStyledAttributes.getDimensionPixelSize(0, this.f1418x);
        this.f1419y = obtainStyledAttributes.getColor(1, this.f1419y);
        obtainStyledAttributes.recycle();
        obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.PagerSlidingTabStrip);
        this.f1407m = obtainStyledAttributes.getColor(R.PagerSlidingTabStrip_pstsIndicatorColor, this.f1407m);
        this.f1408n = obtainStyledAttributes.getColor(R.PagerSlidingTabStrip_pstsUnderlineColor, this.f1408n);
        this.f1409o = obtainStyledAttributes.getColor(R.PagerSlidingTabStrip_pstsDividerColor, this.f1409o);
        this.f1413s = obtainStyledAttributes.getDimensionPixelSize(R.PagerSlidingTabStrip_pstsIndicatorHeight, this.f1413s);
        this.f1414t = obtainStyledAttributes.getDimensionPixelSize(R.PagerSlidingTabStrip_pstsUnderlineHeight, this.f1414t);
        this.f1415u = obtainStyledAttributes.getDimensionPixelSize(R.PagerSlidingTabStrip_pstsDividerPadding, this.f1415u);
        this.f1416v = obtainStyledAttributes.getDimensionPixelSize(R.PagerSlidingTabStrip_pstsTabPaddingLeftRight, this.f1416v);
        this.f1394C = obtainStyledAttributes.getResourceId(R.PagerSlidingTabStrip_pstsTabBackground, this.f1394C);
        this.f1410p = obtainStyledAttributes.getBoolean(R.PagerSlidingTabStrip_pstsShouldExpand, this.f1410p);
        this.f1412r = obtainStyledAttributes.getDimensionPixelSize(R.PagerSlidingTabStrip_pstsScrollOffset, this.f1412r);
        this.f1411q = obtainStyledAttributes.getBoolean(R.PagerSlidingTabStrip_pstsTextAllCaps, this.f1411q);
        obtainStyledAttributes.recycle();
        this.f1405k = new Paint();
        this.f1405k.setAntiAlias(true);
        this.f1405k.setStyle(Style.FILL);
        this.f1406l = new Paint();
        this.f1406l.setAntiAlias(true);
        this.f1406l.setStrokeWidth((float) this.f1417w);
        this.f1397c = new LayoutParams(-2, -1);
        this.f1398d = new LayoutParams(0, -1, 1.0f);
        if (this.f1395D == null) {
            this.f1395D = getResources().getConfiguration().locale;
        }
    }

    private void m2201a(int i, int i2) {
        View imageButton = new ImageButton(getContext());
        imageButton.setImageResource(i2);
        m2202a(i, imageButton);
    }

    private void m2202a(int i, View view) {
        view.setFocusable(true);
        view.setOnClickListener(new PagerSlidingTabStrip(this, i));
        view.setPadding(this.f1416v, 0, this.f1416v, 0);
        this.f1400f.addView(view, i, this.f1410p ? this.f1398d : this.f1397c);
    }

    private void m2203a(int i, String str) {
        View textView = new TextView(getContext());
        textView.setText(str);
        textView.setGravity(17);
        textView.setSingleLine();
        m2202a(i, textView);
    }

    private void m2208b() {
        for (int i = 0; i < this.f1402h; i++) {
            View childAt = this.f1400f.getChildAt(i);
            childAt.setBackgroundResource(this.f1394C);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                textView.setTextSize(0, (float) this.f1418x);
                textView.setTypeface(this.f1420z, this.f1392A);
                textView.setTextColor(this.f1419y);
                if (this.f1411q) {
                    if (VERSION.SDK_INT >= 14) {
                        textView.setAllCaps(true);
                    } else {
                        textView.setText(textView.getText().toString().toUpperCase(this.f1395D));
                    }
                }
            }
        }
    }

    private void m2209b(int i, int i2) {
        if (this.f1402h != 0) {
            int left = this.f1400f.getChildAt(i).getLeft() + i2;
            if (i > 0 || i2 > 0) {
                left -= this.f1412r;
            }
            if (left != this.f1393B) {
                this.f1393B = left;
                scrollTo(left, 0);
            }
        }
    }

    public void m2211a() {
        this.f1400f.removeAllViews();
        this.f1402h = this.f1401g.m597b().m328b();
        for (int i = 0; i < this.f1402h; i++) {
            if (this.f1401g.m597b() instanceof C0067a) {
                m2201a(i, ((C0067a) this.f1401g.m597b()).m2196a(i));
            } else {
                m2203a(i, this.f1401g.m597b().m334c(i).toString());
            }
        }
        m2208b();
        getViewTreeObserver().addOnGlobalLayoutListener(new PagerSlidingTabStrip(this));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && this.f1402h != 0) {
            int height = getHeight();
            this.f1405k.setColor(this.f1407m);
            View childAt = this.f1400f.getChildAt(this.f1403i);
            float left = (float) childAt.getLeft();
            float right = (float) childAt.getRight();
            if (this.f1404j > 0.0f && this.f1403i < this.f1402h - 1) {
                childAt = this.f1400f.getChildAt(this.f1403i + 1);
                float left2 = (float) childAt.getLeft();
                left = (left * (1.0f - this.f1404j)) + (left2 * this.f1404j);
                right = (((float) childAt.getRight()) * this.f1404j) + ((1.0f - this.f1404j) * right);
            }
            canvas.drawRect(left, (float) (height - this.f1413s), right, (float) height, this.f1405k);
            this.f1405k.setColor(this.f1408n);
            canvas.drawRect(0.0f, (float) (height - this.f1414t), (float) this.f1400f.getWidth(), (float) height, this.f1405k);
            this.f1406l.setColor(this.f1409o);
            for (int i = 0; i < this.f1402h - 1; i++) {
                childAt = this.f1400f.getChildAt(i);
                canvas.drawLine((float) childAt.getRight(), (float) this.f1415u, (float) childAt.getRight(), (float) (height - this.f1415u), this.f1406l);
            }
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f1403i = savedState.f1389a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1389a = this.f1403i;
        return savedState;
    }

    public void setAllCaps(boolean z) {
        this.f1411q = z;
    }

    public void setDividerColor(int i) {
        this.f1409o = i;
        invalidate();
    }

    public void setDividerColorResource(int i) {
        this.f1409o = getResources().getColor(i);
        invalidate();
    }

    public void setDividerPadding(int i) {
        this.f1415u = i;
        invalidate();
    }

    public void setIndicatorColor(int i) {
        this.f1407m = i;
        invalidate();
    }

    public void setIndicatorColorResource(int i) {
        this.f1407m = getResources().getColor(i);
        invalidate();
    }

    public void setIndicatorHeight(int i) {
        this.f1413s = i;
        invalidate();
    }

    public void setOnPageChangeListener(C0010e c0010e) {
        this.f1396a = c0010e;
    }

    public void setScrollOffset(int i) {
        this.f1412r = i;
        invalidate();
    }

    public void setShouldExpand(boolean z) {
        this.f1410p = z;
        requestLayout();
    }

    public void setTabBackground(int i) {
        this.f1394C = i;
    }

    public void setTabPaddingLeftRight(int i) {
        this.f1416v = i;
        m2208b();
    }

    public void setTextColor(int i) {
        this.f1419y = i;
        m2208b();
    }

    public void setTextColorResource(int i) {
        this.f1419y = getResources().getColor(i);
        m2208b();
    }

    public void setTextSize(int i) {
        this.f1418x = i;
        m2208b();
    }

    public void setTypeface(Typeface typeface, int i) {
        this.f1420z = typeface;
        this.f1392A = i;
        m2208b();
    }

    public void setUnderlineColor(int i) {
        this.f1408n = i;
        invalidate();
    }

    public void setUnderlineColorResource(int i) {
        this.f1408n = getResources().getColor(i);
        invalidate();
    }

    public void setUnderlineHeight(int i) {
        this.f1414t = i;
        invalidate();
    }

    public void setViewPager(ViewPager viewPager) {
        this.f1401g = viewPager;
        if (viewPager.m597b() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        viewPager.setOnPageChangeListener(this.f1399e);
        m2211a();
    }
}
