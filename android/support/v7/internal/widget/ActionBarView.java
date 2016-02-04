package android.support.v7.internal.widget;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.p005a.p006a.SupportMenu;
import android.support.v4.p005a.p006a.SupportMenuItem;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.C0036b;
import android.support.v7.internal.view.menu.ActionMenuItem;
import android.support.v7.internal.view.menu.ActionMenuPresenter;
import android.support.v7.internal.view.menu.ActionMenuView;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.widget.AdapterViewICS.AdapterViewICS;
import android.support.v7.p010a.R.R;
import android.support.v7.p011b.CollapsibleActionView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window.Callback;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import org.linphone.core.VideoSize;

public class ActionBarView extends AbsActionBarView {
    private ProgressBarICS f1104A;
    private int f1105B;
    private int f1106C;
    private int f1107D;
    private int f1108E;
    private int f1109F;
    private int f1110G;
    private boolean f1111H;
    private boolean f1112I;
    private boolean f1113J;
    private boolean f1114K;
    private MenuBuilder f1115L;
    private ActionBarContextView f1116M;
    private ActionMenuItem f1117N;
    private SpinnerAdapter f1118O;
    private C0036b f1119P;
    private Runnable f1120Q;
    private C0049a f1121R;
    private final AdapterViewICS f1122S;
    private final OnClickListener f1123T;
    private final OnClickListener f1124U;
    View f1125g;
    Callback f1126h;
    private int f1127i;
    private int f1128j;
    private CharSequence f1129k;
    private CharSequence f1130l;
    private Drawable f1131m;
    private Drawable f1132n;
    private Context f1133o;
    private HomeView f1134p;
    private HomeView f1135q;
    private LinearLayout f1136r;
    private TextView f1137s;
    private TextView f1138t;
    private View f1139u;
    private SpinnerICS f1140v;
    private LinearLayout f1141w;
    private ScrollingTabContainerView f1142x;
    private View f1143y;
    private ProgressBarICS f1144z;

    private static class HomeView extends FrameLayout {
        private ImageView f1094a;
        private ImageView f1095b;
        private int f1096c;
        private int f1097d;
        private Drawable f1098e;

        public HomeView(Context context) {
            this(context, null);
        }

        public HomeView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public int m1910a() {
            return this.f1094a.getVisibility() == 8 ? this.f1096c : 0;
        }

        public void m1911a(int i) {
            this.f1097d = i;
            this.f1094a.setImageDrawable(i != 0 ? getResources().getDrawable(i) : this.f1098e);
        }

        public void m1912a(Drawable drawable) {
            this.f1095b.setImageDrawable(drawable);
        }

        public void m1913a(boolean z) {
            this.f1094a.setVisibility(z ? 0 : 8);
        }

        public void m1914b(Drawable drawable) {
            ImageView imageView = this.f1094a;
            if (drawable == null) {
                drawable = this.f1098e;
            }
            imageView.setImageDrawable(drawable);
            this.f1097d = 0;
        }

        public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            CharSequence contentDescription = getContentDescription();
            if (!TextUtils.isEmpty(contentDescription)) {
                accessibilityEvent.getText().add(contentDescription);
            }
            return true;
        }

        protected void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            if (this.f1097d != 0) {
                m1911a(this.f1097d);
            }
        }

        protected void onFinishInflate() {
            this.f1094a = (ImageView) findViewById(R.up);
            this.f1095b = (ImageView) findViewById(R.home);
            this.f1098e = this.f1094a.getDrawable();
        }

        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            LayoutParams layoutParams;
            int measuredHeight;
            int i5;
            int i6 = 0;
            int i7 = (i4 - i2) / 2;
            int i8 = i3 - i;
            if (this.f1094a.getVisibility() != 8) {
                layoutParams = (LayoutParams) this.f1094a.getLayoutParams();
                measuredHeight = this.f1094a.getMeasuredHeight();
                int measuredWidth = this.f1094a.getMeasuredWidth();
                int i9 = i7 - (measuredHeight / 2);
                this.f1094a.layout(0, i9, measuredWidth, measuredHeight + i9);
                i5 = layoutParams.rightMargin + (layoutParams.leftMargin + measuredWidth);
                i6 = i8 - i5;
                i += i5;
                i6 = i5;
            }
            layoutParams = (LayoutParams) this.f1095b.getLayoutParams();
            i8 = this.f1095b.getMeasuredHeight();
            measuredHeight = this.f1095b.getMeasuredWidth();
            i6 += Math.max(layoutParams.leftMargin, ((i3 - i) / 2) - (measuredHeight / 2));
            i5 = Math.max(layoutParams.topMargin, i7 - (i8 / 2));
            this.f1095b.layout(i6, i5, measuredHeight + i6, i8 + i5);
        }

        protected void onMeasure(int i, int i2) {
            measureChildWithMargins(this.f1094a, i, 0, i2, 0);
            LayoutParams layoutParams = (LayoutParams) this.f1094a.getLayoutParams();
            this.f1096c = (layoutParams.leftMargin + this.f1094a.getMeasuredWidth()) + layoutParams.rightMargin;
            int i3 = this.f1094a.getVisibility() == 8 ? 0 : this.f1096c;
            int measuredHeight = (layoutParams.topMargin + this.f1094a.getMeasuredHeight()) + layoutParams.bottomMargin;
            measureChildWithMargins(this.f1095b, i, i3, i2, 0);
            layoutParams = (LayoutParams) this.f1095b.getLayoutParams();
            int measuredWidth = i3 + ((layoutParams.leftMargin + this.f1095b.getMeasuredWidth()) + layoutParams.rightMargin);
            measuredHeight = Math.max(measuredHeight, layoutParams.bottomMargin + (layoutParams.topMargin + this.f1095b.getMeasuredHeight()));
            int mode = MeasureSpec.getMode(i);
            int mode2 = MeasureSpec.getMode(i2);
            int size = MeasureSpec.getSize(i);
            int size2 = MeasureSpec.getSize(i2);
            switch (mode) {
                case Integer.MIN_VALUE:
                    size = Math.min(measuredWidth, size);
                    break;
                case 1073741824:
                    break;
                default:
                    size = measuredWidth;
                    break;
            }
            switch (mode2) {
                case Integer.MIN_VALUE:
                    size2 = Math.min(measuredHeight, size2);
                    break;
                case 1073741824:
                    break;
                default:
                    size2 = measuredHeight;
                    break;
            }
            setMeasuredDimension(size, size2);
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int f1099a;
        boolean f1100b;

        static {
            CREATOR = new ActionBarView();
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f1099a = parcel.readInt();
            this.f1100b = parcel.readInt() != 0;
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1099a);
            parcel.writeInt(this.f1100b ? 1 : 0);
        }
    }

    /* renamed from: android.support.v7.internal.widget.ActionBarView.a */
    private class C0049a implements MenuPresenter {
        MenuBuilder f1101a;
        MenuItemImpl f1102b;
        final /* synthetic */ ActionBarView f1103c;

        private C0049a(ActionBarView actionBarView) {
            this.f1103c = actionBarView;
        }

        public void m1915a(Context context, MenuBuilder menuBuilder) {
            if (!(this.f1101a == null || this.f1102b == null)) {
                this.f1101a.m1782d(this.f1102b);
            }
            this.f1101a = menuBuilder;
        }

        public void m1916a(MenuBuilder menuBuilder, boolean z) {
        }

        public boolean m1917a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            this.f1103c.f1125g = menuItemImpl.getActionView();
            this.f1103c.f1135q.m1912a(this.f1103c.f1131m.getConstantState().newDrawable(this.f1103c.getResources()));
            this.f1102b = menuItemImpl;
            if (this.f1103c.f1125g.getParent() != this.f1103c) {
                this.f1103c.addView(this.f1103c.f1125g);
            }
            if (this.f1103c.f1135q.getParent() != this.f1103c) {
                this.f1103c.addView(this.f1103c.f1135q);
            }
            this.f1103c.f1134p.setVisibility(8);
            if (this.f1103c.f1136r != null) {
                this.f1103c.f1136r.setVisibility(8);
            }
            if (this.f1103c.f1142x != null) {
                this.f1103c.f1142x.setVisibility(8);
            }
            if (this.f1103c.f1140v != null) {
                this.f1103c.f1140v.setVisibility(8);
            }
            if (this.f1103c.f1143y != null) {
                this.f1103c.f1143y.setVisibility(8);
            }
            this.f1103c.requestLayout();
            menuItemImpl.m1813e(true);
            if (this.f1103c.f1125g instanceof CollapsibleActionView) {
                ((CollapsibleActionView) this.f1103c.f1125g).m1587a();
            }
            return true;
        }

        public boolean m1918a(SubMenuBuilder subMenuBuilder) {
            return false;
        }

        public boolean m1919b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            if (this.f1103c.f1125g instanceof CollapsibleActionView) {
                ((CollapsibleActionView) this.f1103c.f1125g).m1588b();
            }
            this.f1103c.removeView(this.f1103c.f1125g);
            this.f1103c.removeView(this.f1103c.f1135q);
            this.f1103c.f1125g = null;
            if ((this.f1103c.f1128j & 2) != 0) {
                this.f1103c.f1134p.setVisibility(0);
            }
            if ((this.f1103c.f1128j & 8) != 0) {
                if (this.f1103c.f1136r == null) {
                    this.f1103c.m1937p();
                } else {
                    this.f1103c.f1136r.setVisibility(0);
                }
            }
            if (this.f1103c.f1142x != null && this.f1103c.f1127i == 2) {
                this.f1103c.f1142x.setVisibility(0);
            }
            if (this.f1103c.f1140v != null && this.f1103c.f1127i == 1) {
                this.f1103c.f1140v.setVisibility(0);
            }
            if (!(this.f1103c.f1143y == null || (this.f1103c.f1128j & 16) == 0)) {
                this.f1103c.f1143y.setVisibility(0);
            }
            this.f1103c.f1135q.m1912a(null);
            this.f1102b = null;
            this.f1103c.requestLayout();
            menuItemImpl.m1813e(false);
            return true;
        }

        public void m1920c(boolean z) {
            if (this.f1102b != null) {
                Object obj;
                if (this.f1101a != null) {
                    int size = this.f1101a.size();
                    for (int i = 0; i < size; i++) {
                        if (((SupportMenuItem) this.f1101a.getItem(i)) == this.f1102b) {
                            obj = 1;
                            break;
                        }
                    }
                }
                obj = null;
                if (obj == null) {
                    m1919b(this.f1101a, this.f1102b);
                }
            }
        }

        public boolean m1921g() {
            return false;
        }
    }

    public ActionBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1128j = -1;
        this.f1122S = new ActionBarView(this);
        this.f1123T = new ActionBarView(this);
        this.f1124U = new ActionBarView(this);
        this.f1133o = context;
        setBackgroundResource(0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.ActionBar, R.actionBarStyle, 0);
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        PackageManager packageManager = context.getPackageManager();
        this.f1127i = obtainStyledAttributes.getInt(2, 0);
        this.f1129k = obtainStyledAttributes.getText(0);
        this.f1130l = obtainStyledAttributes.getText(4);
        this.f1132n = obtainStyledAttributes.getDrawable(8);
        if (this.f1132n == null && VERSION.SDK_INT >= 9) {
            if (context instanceof Activity) {
                try {
                    this.f1132n = packageManager.getActivityLogo(((Activity) context).getComponentName());
                } catch (Throwable e) {
                    Log.e("ActionBarView", "Activity component name not found!", e);
                }
            }
            if (this.f1132n == null) {
                this.f1132n = applicationInfo.loadLogo(packageManager);
            }
        }
        this.f1131m = obtainStyledAttributes.getDrawable(7);
        if (this.f1131m == null) {
            if (context instanceof Activity) {
                try {
                    this.f1131m = packageManager.getActivityIcon(((Activity) context).getComponentName());
                } catch (Throwable e2) {
                    Log.e("ActionBarView", "Activity component name not found!", e2);
                }
            }
            if (this.f1131m == null) {
                this.f1131m = applicationInfo.loadIcon(packageManager);
            }
        }
        LayoutInflater from = LayoutInflater.from(context);
        int resourceId = obtainStyledAttributes.getResourceId(14, R.abc_action_bar_home);
        this.f1134p = (HomeView) from.inflate(resourceId, this, false);
        this.f1135q = (HomeView) from.inflate(resourceId, this, false);
        this.f1135q.m1913a(true);
        this.f1135q.setOnClickListener(this.f1123T);
        this.f1135q.setContentDescription(getResources().getText(R.abc_action_bar_up_description));
        this.f1107D = obtainStyledAttributes.getResourceId(5, 0);
        this.f1108E = obtainStyledAttributes.getResourceId(6, 0);
        this.f1109F = obtainStyledAttributes.getResourceId(15, 0);
        this.f1110G = obtainStyledAttributes.getResourceId(16, 0);
        this.f1105B = obtainStyledAttributes.getDimensionPixelOffset(17, 0);
        this.f1106C = obtainStyledAttributes.getDimensionPixelOffset(18, 0);
        setDisplayOptions(obtainStyledAttributes.getInt(3, 0));
        int resourceId2 = obtainStyledAttributes.getResourceId(13, 0);
        if (resourceId2 != 0) {
            this.f1143y = from.inflate(resourceId2, this, false);
            this.f1127i = 0;
            setDisplayOptions(this.f1128j | 16);
        }
        this.f = obtainStyledAttributes.getLayoutDimension(1, 0);
        obtainStyledAttributes.recycle();
        this.f1117N = new ActionMenuItem(context, 0, 16908332, 0, 0, this.f1129k);
        this.f1134p.setOnClickListener(this.f1124U);
        this.f1134p.setClickable(true);
        this.f1134p.setFocusable(true);
    }

    private void m1923a(MenuBuilder menuBuilder) {
        if (menuBuilder != null) {
            menuBuilder.m1767a(this.b);
            menuBuilder.m1767a(this.f1121R);
        } else {
            this.b.m1682a(this.f1133o, null);
            this.f1121R.m1915a(this.f1133o, null);
        }
        this.b.m1693c(true);
        this.f1121R.m1920c(true);
    }

    private void m1924a(CharSequence charSequence) {
        int i = 0;
        this.f1129k = charSequence;
        if (this.f1137s != null) {
            this.f1137s.setText(charSequence);
            int i2 = (this.f1125g != null || (this.f1128j & 8) == 0 || (TextUtils.isEmpty(this.f1129k) && TextUtils.isEmpty(this.f1130l))) ? 0 : 1;
            LinearLayout linearLayout = this.f1136r;
            if (i2 == 0) {
                i = 8;
            }
            linearLayout.setVisibility(i);
        }
        if (this.f1117N != null) {
            this.f1117N.setTitle(charSequence);
        }
    }

    private void m1937p() {
        boolean z = true;
        if (this.f1136r == null) {
            this.f1136r = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.abc_action_bar_title_item, this, false);
            this.f1137s = (TextView) this.f1136r.findViewById(R.action_bar_title);
            this.f1138t = (TextView) this.f1136r.findViewById(R.action_bar_subtitle);
            this.f1139u = this.f1136r.findViewById(R.up);
            this.f1136r.setOnClickListener(this.f1124U);
            if (this.f1107D != 0) {
                this.f1137s.setTextAppearance(this.f1133o, this.f1107D);
            }
            if (this.f1129k != null) {
                this.f1137s.setText(this.f1129k);
            }
            if (this.f1108E != 0) {
                this.f1138t.setTextAppearance(this.f1133o, this.f1108E);
            }
            if (this.f1130l != null) {
                this.f1138t.setText(this.f1130l);
                this.f1138t.setVisibility(0);
            }
            boolean z2 = (this.f1128j & 4) != 0;
            boolean z3 = (this.f1128j & 2) != 0;
            View view = this.f1139u;
            int i = !z3 ? z2 ? 0 : 4 : 8;
            view.setVisibility(i);
            LinearLayout linearLayout = this.f1136r;
            if (!z2 || z3) {
                z = false;
            }
            linearLayout.setEnabled(z);
        }
        addView(this.f1136r);
        if (this.f1125g != null || (TextUtils.isEmpty(this.f1129k) && TextUtils.isEmpty(this.f1130l))) {
            this.f1136r.setVisibility(8);
        }
    }

    public /* bridge */ /* synthetic */ int m1938a() {
        return super.m1891a();
    }

    public /* bridge */ /* synthetic */ boolean m1939b() {
        return super.m1894b();
    }

    public /* bridge */ /* synthetic */ void m1940c() {
        super.m1896c();
    }

    public /* bridge */ /* synthetic */ boolean m1941d() {
        return super.m1897d();
    }

    public /* bridge */ /* synthetic */ boolean m1942e() {
        return super.m1898e();
    }

    public /* bridge */ /* synthetic */ boolean m1943f() {
        return super.m1899f();
    }

    public /* bridge */ /* synthetic */ void m1944g() {
        super.m1900g();
    }

    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ActionBar.LayoutParams(19);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ActionBar.LayoutParams(getContext(), attributeSet);
    }

    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams == null ? generateDefaultLayoutParams() : layoutParams;
    }

    public void m1945h() {
        this.f1144z = new ProgressBarICS(this.f1133o, null, 0, this.f1109F);
        this.f1144z.setId(R.progress_horizontal);
        this.f1144z.setMax(10000);
        this.f1144z.setVisibility(8);
        addView(this.f1144z);
    }

    public void m1946i() {
        this.f1104A = new ProgressBarICS(this.f1133o, null, 0, this.f1110G);
        this.f1104A.setId(R.progress_circular);
        this.f1104A.setVisibility(8);
        addView(this.f1104A);
    }

    public boolean m1947j() {
        return this.d;
    }

    public boolean m1948k() {
        return (this.f1121R == null || this.f1121R.f1102b == null) ? false : true;
    }

    public void m1949l() {
        MenuItemImpl menuItemImpl = this.f1121R == null ? null : this.f1121R.f1102b;
        if (menuItemImpl != null) {
            menuItemImpl.collapseActionView();
        }
    }

    public int m1950m() {
        return this.f1127i;
    }

    public int m1951n() {
        return this.f1128j;
    }

    public boolean m1952o() {
        return this.f1114K;
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f1137s = null;
        this.f1138t = null;
        this.f1139u = null;
        if (this.f1136r != null && this.f1136r.getParent() == this) {
            removeView(this.f1136r);
        }
        this.f1136r = null;
        if ((this.f1128j & 8) != 0) {
            m1937p();
        }
        if (this.f1142x != null && this.f1112I) {
            ViewGroup.LayoutParams layoutParams = this.f1142x.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = -2;
                layoutParams.height = -1;
            }
            this.f1142x.setAllowCollapse(true);
        }
        if (this.f1144z != null) {
            removeView(this.f1144z);
            m1945h();
        }
        if (this.f1104A != null) {
            removeView(this.f1104A);
            m1946i();
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.f1120Q);
        if (this.b != null) {
            this.b.m1692b();
            this.b.m1695d();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        addView(this.f1134p);
        if (this.f1143y != null && (this.f1128j & 16) != 0) {
            ActionBarView parent = this.f1143y.getParent();
            if (parent != this) {
                if (parent instanceof ViewGroup) {
                    parent.removeView(this.f1143y);
                }
                addView(this.f1143y);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onLayout(boolean r10, int r11, int r12, int r13, int r14) {
        /*
        r9 = this;
        r1 = r9.getPaddingLeft();
        r2 = r9.getPaddingTop();
        r0 = r14 - r12;
        r3 = r9.getPaddingTop();
        r0 = r0 - r3;
        r3 = r9.getPaddingBottom();
        r3 = r0 - r3;
        if (r3 > 0) goto L_0x0018;
    L_0x0017:
        return;
    L_0x0018:
        r0 = r9.f1125g;
        if (r0 == 0) goto L_0x011a;
    L_0x001c:
        r0 = r9.f1135q;
    L_0x001e:
        r4 = r0.getVisibility();
        r5 = 8;
        if (r4 == r5) goto L_0x01c5;
    L_0x0026:
        r4 = r0.m1910a();
        r5 = r1 + r4;
        r0 = r9.m1893b(r0, r5, r2, r3);
        r0 = r0 + r4;
        r0 = r0 + r1;
    L_0x0032:
        r1 = r9.f1125g;
        if (r1 != 0) goto L_0x0059;
    L_0x0036:
        r1 = r9.f1136r;
        if (r1 == 0) goto L_0x011e;
    L_0x003a:
        r1 = r9.f1136r;
        r1 = r1.getVisibility();
        r4 = 8;
        if (r1 == r4) goto L_0x011e;
    L_0x0044:
        r1 = r9.f1128j;
        r1 = r1 & 8;
        if (r1 == 0) goto L_0x011e;
    L_0x004a:
        r1 = 1;
    L_0x004b:
        if (r1 == 0) goto L_0x0054;
    L_0x004d:
        r4 = r9.f1136r;
        r4 = r9.m1893b(r4, r0, r2, r3);
        r0 = r0 + r4;
    L_0x0054:
        r4 = r9.f1127i;
        switch(r4) {
            case 0: goto L_0x0121;
            case 1: goto L_0x0124;
            case 2: goto L_0x013a;
            default: goto L_0x0059;
        };
    L_0x0059:
        r1 = r0;
    L_0x005a:
        r0 = r13 - r11;
        r4 = r9.getPaddingRight();
        r0 = r0 - r4;
        r4 = r9.a;
        if (r4 == 0) goto L_0x0079;
    L_0x0065:
        r4 = r9.a;
        r4 = r4.getParent();
        if (r4 != r9) goto L_0x0079;
    L_0x006d:
        r4 = r9.a;
        r9.m1895c(r4, r0, r2, r3);
        r4 = r9.a;
        r4 = r4.getMeasuredWidth();
        r0 = r0 - r4;
    L_0x0079:
        r4 = r9.f1104A;
        if (r4 == 0) goto L_0x01c2;
    L_0x007d:
        r4 = r9.f1104A;
        r4 = r4.getVisibility();
        r5 = 8;
        if (r4 == r5) goto L_0x01c2;
    L_0x0087:
        r4 = r9.f1104A;
        r9.m1895c(r4, r0, r2, r3);
        r2 = r9.f1104A;
        r2 = r2.getMeasuredWidth();
        r0 = r0 - r2;
        r2 = r0;
    L_0x0094:
        r0 = 0;
        r3 = r9.f1125g;
        if (r3 == 0) goto L_0x0150;
    L_0x0099:
        r0 = r9.f1125g;
        r7 = r0;
    L_0x009c:
        if (r7 == 0) goto L_0x00f6;
    L_0x009e:
        r0 = r7.getLayoutParams();
        r3 = r0 instanceof android.support.v7.app.ActionBar.LayoutParams;
        if (r3 == 0) goto L_0x015f;
    L_0x00a6:
        r0 = (android.support.v7.app.ActionBar.LayoutParams) r0;
        r5 = r0;
    L_0x00a9:
        if (r5 == 0) goto L_0x0163;
    L_0x00ab:
        r0 = r5.f738a;
    L_0x00ad:
        r8 = r7.getMeasuredWidth();
        r4 = 0;
        r3 = 0;
        if (r5 == 0) goto L_0x01b9;
    L_0x00b5:
        r3 = r5.leftMargin;
        r4 = r1 + r3;
        r1 = r5.rightMargin;
        r3 = r2 - r1;
        r2 = r5.topMargin;
        r1 = r5.bottomMargin;
        r5 = r2;
        r6 = r3;
        r3 = r4;
        r4 = r1;
    L_0x00c5:
        r1 = r0 & 7;
        r2 = 1;
        if (r1 != r2) goto L_0x016d;
    L_0x00ca:
        r2 = r9.getWidth();
        r2 = r2 - r8;
        r2 = r2 / 2;
        if (r2 >= r3) goto L_0x0167;
    L_0x00d3:
        r1 = 3;
    L_0x00d4:
        r2 = r1;
    L_0x00d5:
        r1 = 0;
        switch(r2) {
            case 1: goto L_0x0174;
            case 2: goto L_0x00d9;
            case 3: goto L_0x017e;
            case 4: goto L_0x00d9;
            case 5: goto L_0x0181;
            default: goto L_0x00d9;
        };
    L_0x00d9:
        r2 = r1;
    L_0x00da:
        r1 = r0 & 112;
        r6 = -1;
        if (r0 != r6) goto L_0x00e2;
    L_0x00df:
        r0 = 16;
        r1 = r0;
    L_0x00e2:
        r0 = 0;
        switch(r1) {
            case 16: goto L_0x0186;
            case 48: goto L_0x019e;
            case 80: goto L_0x01a5;
            default: goto L_0x00e6;
        };
    L_0x00e6:
        r1 = r7.getMeasuredWidth();
        r4 = r2 + r1;
        r5 = r7.getMeasuredHeight();
        r5 = r5 + r0;
        r7.layout(r2, r0, r4, r5);
        r0 = r3 + r1;
    L_0x00f6:
        r0 = r9.f1144z;
        if (r0 == 0) goto L_0x0017;
    L_0x00fa:
        r0 = r9.f1144z;
        r0.bringToFront();
        r0 = r9.f1144z;
        r0 = r0.getMeasuredHeight();
        r0 = r0 / 2;
        r1 = r9.f1144z;
        r2 = r9.f1105B;
        r3 = -r0;
        r4 = r9.f1105B;
        r5 = r9.f1144z;
        r5 = r5.getMeasuredWidth();
        r4 = r4 + r5;
        r1.layout(r2, r3, r4, r0);
        goto L_0x0017;
    L_0x011a:
        r0 = r9.f1134p;
        goto L_0x001e;
    L_0x011e:
        r1 = 0;
        goto L_0x004b;
    L_0x0121:
        r1 = r0;
        goto L_0x005a;
    L_0x0124:
        r4 = r9.f1141w;
        if (r4 == 0) goto L_0x0059;
    L_0x0128:
        if (r1 == 0) goto L_0x012d;
    L_0x012a:
        r1 = r9.f1106C;
        r0 = r0 + r1;
    L_0x012d:
        r1 = r9.f1141w;
        r1 = r9.m1893b(r1, r0, r2, r3);
        r4 = r9.f1106C;
        r1 = r1 + r4;
        r0 = r0 + r1;
        r1 = r0;
        goto L_0x005a;
    L_0x013a:
        r4 = r9.f1142x;
        if (r4 == 0) goto L_0x0059;
    L_0x013e:
        if (r1 == 0) goto L_0x0143;
    L_0x0140:
        r1 = r9.f1106C;
        r0 = r0 + r1;
    L_0x0143:
        r1 = r9.f1142x;
        r1 = r9.m1893b(r1, r0, r2, r3);
        r4 = r9.f1106C;
        r1 = r1 + r4;
        r0 = r0 + r1;
        r1 = r0;
        goto L_0x005a;
    L_0x0150:
        r3 = r9.f1128j;
        r3 = r3 & 16;
        if (r3 == 0) goto L_0x01bf;
    L_0x0156:
        r3 = r9.f1143y;
        if (r3 == 0) goto L_0x01bf;
    L_0x015a:
        r0 = r9.f1143y;
        r7 = r0;
        goto L_0x009c;
    L_0x015f:
        r0 = 0;
        r5 = r0;
        goto L_0x00a9;
    L_0x0163:
        r0 = 19;
        goto L_0x00ad;
    L_0x0167:
        r2 = r2 + r8;
        if (r2 <= r6) goto L_0x00d4;
    L_0x016a:
        r1 = 5;
        goto L_0x00d4;
    L_0x016d:
        r2 = -1;
        if (r0 != r2) goto L_0x01b6;
    L_0x0170:
        r1 = 3;
        r2 = r1;
        goto L_0x00d5;
    L_0x0174:
        r1 = r9.getWidth();
        r1 = r1 - r8;
        r1 = r1 / 2;
        r2 = r1;
        goto L_0x00da;
    L_0x017e:
        r2 = r3;
        goto L_0x00da;
    L_0x0181:
        r1 = r6 - r8;
        r2 = r1;
        goto L_0x00da;
    L_0x0186:
        r0 = r9.getPaddingTop();
        r1 = r9.getHeight();
        r4 = r9.getPaddingBottom();
        r1 = r1 - r4;
        r0 = r1 - r0;
        r1 = r7.getMeasuredHeight();
        r0 = r0 - r1;
        r0 = r0 / 2;
        goto L_0x00e6;
    L_0x019e:
        r0 = r9.getPaddingTop();
        r0 = r0 + r5;
        goto L_0x00e6;
    L_0x01a5:
        r0 = r9.getHeight();
        r1 = r9.getPaddingBottom();
        r0 = r0 - r1;
        r1 = r7.getMeasuredHeight();
        r0 = r0 - r1;
        r0 = r0 - r4;
        goto L_0x00e6;
    L_0x01b6:
        r2 = r1;
        goto L_0x00d5;
    L_0x01b9:
        r5 = r4;
        r6 = r2;
        r4 = r3;
        r3 = r1;
        goto L_0x00c5;
    L_0x01bf:
        r7 = r0;
        goto L_0x009c;
    L_0x01c2:
        r2 = r0;
        goto L_0x0094;
    L_0x01c5:
        r0 = r1;
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.internal.widget.ActionBarView.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onMeasure(int r20, int r21) {
        /*
        r19 = this;
        r13 = r19.getChildCount();
        r0 = r19;
        r1 = r0.f1113J;
        if (r1 == 0) goto L_0x0045;
    L_0x000a:
        r2 = 0;
        r1 = 0;
        r18 = r1;
        r1 = r2;
        r2 = r18;
    L_0x0011:
        if (r2 >= r13) goto L_0x0036;
    L_0x0013:
        r0 = r19;
        r3 = r0.getChildAt(r2);
        r4 = r3.getVisibility();
        r5 = 8;
        if (r4 == r5) goto L_0x0033;
    L_0x0021:
        r0 = r19;
        r4 = r0.a;
        if (r3 != r4) goto L_0x0031;
    L_0x0027:
        r0 = r19;
        r3 = r0.a;
        r3 = r3.getChildCount();
        if (r3 == 0) goto L_0x0033;
    L_0x0031:
        r1 = r1 + 1;
    L_0x0033:
        r2 = r2 + 1;
        goto L_0x0011;
    L_0x0036:
        if (r1 != 0) goto L_0x0045;
    L_0x0038:
        r1 = 0;
        r2 = 0;
        r0 = r19;
        r0.setMeasuredDimension(r1, r2);
        r1 = 1;
        r0 = r19;
        r0.f1114K = r1;
    L_0x0044:
        return;
    L_0x0045:
        r1 = 0;
        r0 = r19;
        r0.f1114K = r1;
        r1 = android.view.View.MeasureSpec.getMode(r20);
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r1 == r2) goto L_0x0079;
    L_0x0052:
        r1 = new java.lang.IllegalStateException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r19.getClass();
        r3 = r3.getSimpleName();
        r2 = r2.append(r3);
        r3 = " can only be used ";
        r2 = r2.append(r3);
        r3 = "with android:layout_width=\"MATCH_PARENT\" (or fill_parent)";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2);
        throw r1;
    L_0x0079:
        r1 = android.view.View.MeasureSpec.getMode(r21);
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r1 == r2) goto L_0x00a8;
    L_0x0081:
        r1 = new java.lang.IllegalStateException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r19.getClass();
        r3 = r3.getSimpleName();
        r2 = r2.append(r3);
        r3 = " can only be used ";
        r2 = r2.append(r3);
        r3 = "with android:layout_height=\"wrap_content\"";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2);
        throw r1;
    L_0x00a8:
        r14 = android.view.View.MeasureSpec.getSize(r20);
        r0 = r19;
        r1 = r0.f;
        if (r1 <= 0) goto L_0x0271;
    L_0x00b2:
        r0 = r19;
        r1 = r0.f;
        r3 = r1;
    L_0x00b7:
        r1 = r19.getPaddingTop();
        r2 = r19.getPaddingBottom();
        r15 = r1 + r2;
        r1 = r19.getPaddingLeft();
        r2 = r19.getPaddingRight();
        r10 = r3 - r15;
        r4 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r6 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r4);
        r1 = r14 - r1;
        r5 = r1 - r2;
        r4 = r5 / 2;
        r0 = r19;
        r1 = r0.f1125g;
        if (r1 == 0) goto L_0x0278;
    L_0x00dd:
        r0 = r19;
        r1 = r0.f1135q;
    L_0x00e1:
        r2 = r1.getVisibility();
        r7 = 8;
        if (r2 == r7) goto L_0x03ab;
    L_0x00e9:
        r2 = r1.getLayoutParams();
        r7 = r2.width;
        if (r7 >= 0) goto L_0x027e;
    L_0x00f1:
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r2 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r2);
    L_0x00f7:
        r7 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r7);
        r1.measure(r2, r7);
        r2 = r1.getMeasuredWidth();
        r1 = r1.m1910a();
        r1 = r1 + r2;
        r2 = 0;
        r5 = r5 - r1;
        r2 = java.lang.Math.max(r2, r5);
        r5 = 0;
        r1 = r2 - r1;
        r1 = java.lang.Math.max(r5, r1);
    L_0x0116:
        r0 = r19;
        r5 = r0.a;
        if (r5 == 0) goto L_0x0141;
    L_0x011c:
        r0 = r19;
        r5 = r0.a;
        r5 = r5.getParent();
        r0 = r19;
        if (r5 != r0) goto L_0x0141;
    L_0x0128:
        r0 = r19;
        r5 = r0.a;
        r7 = 0;
        r0 = r19;
        r2 = r0.m1892a(r5, r2, r6, r7);
        r5 = 0;
        r0 = r19;
        r7 = r0.a;
        r7 = r7.getMeasuredWidth();
        r4 = r4 - r7;
        r4 = java.lang.Math.max(r5, r4);
    L_0x0141:
        r0 = r19;
        r5 = r0.f1104A;
        if (r5 == 0) goto L_0x016c;
    L_0x0147:
        r0 = r19;
        r5 = r0.f1104A;
        r5 = r5.getVisibility();
        r7 = 8;
        if (r5 == r7) goto L_0x016c;
    L_0x0153:
        r0 = r19;
        r5 = r0.f1104A;
        r7 = 0;
        r0 = r19;
        r2 = r0.m1892a(r5, r2, r6, r7);
        r5 = 0;
        r0 = r19;
        r6 = r0.f1104A;
        r6 = r6.getMeasuredWidth();
        r4 = r4 - r6;
        r4 = java.lang.Math.max(r5, r4);
    L_0x016c:
        r0 = r19;
        r5 = r0.f1136r;
        if (r5 == 0) goto L_0x0288;
    L_0x0172:
        r0 = r19;
        r5 = r0.f1136r;
        r5 = r5.getVisibility();
        r6 = 8;
        if (r5 == r6) goto L_0x0288;
    L_0x017e:
        r0 = r19;
        r5 = r0.f1128j;
        r5 = r5 & 8;
        if (r5 == 0) goto L_0x0288;
    L_0x0186:
        r5 = 1;
    L_0x0187:
        r0 = r19;
        r6 = r0.f1125g;
        if (r6 != 0) goto L_0x0194;
    L_0x018d:
        r0 = r19;
        r6 = r0.f1127i;
        switch(r6) {
            case 1: goto L_0x028b;
            case 2: goto L_0x02d5;
            default: goto L_0x0194;
        };
    L_0x0194:
        r6 = r1;
        r7 = r2;
    L_0x0196:
        r1 = 0;
        r0 = r19;
        r2 = r0.f1125g;
        if (r2 == 0) goto L_0x031f;
    L_0x019d:
        r0 = r19;
        r1 = r0.f1125g;
        r12 = r1;
    L_0x01a2:
        if (r12 == 0) goto L_0x022a;
    L_0x01a4:
        r1 = r12.getLayoutParams();
        r0 = r19;
        r2 = r0.generateLayoutParams(r1);
        r1 = r2 instanceof android.support.v7.app.ActionBar.LayoutParams;
        if (r1 == 0) goto L_0x0334;
    L_0x01b2:
        r1 = r2;
        r1 = (android.support.v7.app.ActionBar.LayoutParams) r1;
        r11 = r1;
    L_0x01b6:
        r8 = 0;
        r1 = 0;
        if (r11 == 0) goto L_0x01c4;
    L_0x01ba:
        r1 = r11.leftMargin;
        r8 = r11.rightMargin;
        r8 = r8 + r1;
        r1 = r11.topMargin;
        r9 = r11.bottomMargin;
        r1 = r1 + r9;
    L_0x01c4:
        r0 = r19;
        r9 = r0.f;
        if (r9 > 0) goto L_0x0338;
    L_0x01ca:
        r9 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
    L_0x01cc:
        r16 = 0;
        r0 = r2.height;
        r17 = r0;
        if (r17 < 0) goto L_0x01de;
    L_0x01d4:
        r0 = r2.height;
        r17 = r0;
        r0 = r17;
        r10 = java.lang.Math.min(r0, r10);
    L_0x01de:
        r1 = r10 - r1;
        r0 = r16;
        r16 = java.lang.Math.max(r0, r1);
        r1 = r2.width;
        r10 = -2;
        if (r1 == r10) goto L_0x0348;
    L_0x01eb:
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
    L_0x01ed:
        r17 = 0;
        r10 = r2.width;
        if (r10 < 0) goto L_0x034c;
    L_0x01f3:
        r10 = r2.width;
        r10 = java.lang.Math.min(r10, r7);
    L_0x01f9:
        r10 = r10 - r8;
        r0 = r17;
        r10 = java.lang.Math.max(r0, r10);
        if (r11 == 0) goto L_0x034f;
    L_0x0202:
        r11 = r11.f738a;
    L_0x0204:
        r11 = r11 & 7;
        r17 = 1;
        r0 = r17;
        if (r11 != r0) goto L_0x03a5;
    L_0x020c:
        r2 = r2.width;
        r11 = -1;
        if (r2 != r11) goto L_0x03a5;
    L_0x0211:
        r2 = java.lang.Math.min(r6, r4);
        r2 = r2 * 2;
    L_0x0217:
        r1 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r1);
        r0 = r16;
        r2 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r9);
        r12.measure(r1, r2);
        r1 = r12.getMeasuredWidth();
        r1 = r1 + r8;
        r7 = r7 - r1;
    L_0x022a:
        r0 = r19;
        r1 = r0.f1125g;
        if (r1 != 0) goto L_0x0254;
    L_0x0230:
        if (r5 == 0) goto L_0x0254;
    L_0x0232:
        r0 = r19;
        r1 = r0.f1136r;
        r0 = r19;
        r2 = r0.f;
        r4 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r4);
        r4 = 0;
        r0 = r19;
        r0.m1892a(r1, r7, r2, r4);
        r1 = 0;
        r0 = r19;
        r2 = r0.f1136r;
        r2 = r2.getMeasuredWidth();
        r2 = r6 - r2;
        java.lang.Math.max(r1, r2);
    L_0x0254:
        r0 = r19;
        r1 = r0.f;
        if (r1 > 0) goto L_0x039c;
    L_0x025a:
        r2 = 0;
        r1 = 0;
        r3 = r1;
    L_0x025d:
        if (r3 >= r13) goto L_0x0353;
    L_0x025f:
        r0 = r19;
        r1 = r0.getChildAt(r3);
        r1 = r1.getMeasuredHeight();
        r1 = r1 + r15;
        if (r1 <= r2) goto L_0x03a2;
    L_0x026c:
        r2 = r3 + 1;
        r3 = r2;
        r2 = r1;
        goto L_0x025d;
    L_0x0271:
        r1 = android.view.View.MeasureSpec.getSize(r21);
        r3 = r1;
        goto L_0x00b7;
    L_0x0278:
        r0 = r19;
        r1 = r0.f1134p;
        goto L_0x00e1;
    L_0x027e:
        r2 = r2.width;
        r7 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r7);
        goto L_0x00f7;
    L_0x0288:
        r5 = 0;
        goto L_0x0187;
    L_0x028b:
        r0 = r19;
        r6 = r0.f1141w;
        if (r6 == 0) goto L_0x0194;
    L_0x0291:
        if (r5 == 0) goto L_0x02d0;
    L_0x0293:
        r0 = r19;
        r6 = r0.f1106C;
        r6 = r6 * 2;
    L_0x0299:
        r7 = 0;
        r2 = r2 - r6;
        r2 = java.lang.Math.max(r7, r2);
        r7 = 0;
        r1 = r1 - r6;
        r1 = java.lang.Math.max(r7, r1);
        r0 = r19;
        r6 = r0.f1141w;
        r7 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r7 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r7);
        r8 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r8 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r8);
        r6.measure(r7, r8);
        r0 = r19;
        r6 = r0.f1141w;
        r6 = r6.getMeasuredWidth();
        r7 = 0;
        r2 = r2 - r6;
        r2 = java.lang.Math.max(r7, r2);
        r7 = 0;
        r1 = r1 - r6;
        r1 = java.lang.Math.max(r7, r1);
        r6 = r1;
        r7 = r2;
        goto L_0x0196;
    L_0x02d0:
        r0 = r19;
        r6 = r0.f1106C;
        goto L_0x0299;
    L_0x02d5:
        r0 = r19;
        r6 = r0.f1142x;
        if (r6 == 0) goto L_0x0194;
    L_0x02db:
        if (r5 == 0) goto L_0x031a;
    L_0x02dd:
        r0 = r19;
        r6 = r0.f1106C;
        r6 = r6 * 2;
    L_0x02e3:
        r7 = 0;
        r2 = r2 - r6;
        r2 = java.lang.Math.max(r7, r2);
        r7 = 0;
        r1 = r1 - r6;
        r1 = java.lang.Math.max(r7, r1);
        r0 = r19;
        r6 = r0.f1142x;
        r7 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r7 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r7);
        r8 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r8 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r8);
        r6.measure(r7, r8);
        r0 = r19;
        r6 = r0.f1142x;
        r6 = r6.getMeasuredWidth();
        r7 = 0;
        r2 = r2 - r6;
        r2 = java.lang.Math.max(r7, r2);
        r7 = 0;
        r1 = r1 - r6;
        r1 = java.lang.Math.max(r7, r1);
        r6 = r1;
        r7 = r2;
        goto L_0x0196;
    L_0x031a:
        r0 = r19;
        r6 = r0.f1106C;
        goto L_0x02e3;
    L_0x031f:
        r0 = r19;
        r2 = r0.f1128j;
        r2 = r2 & 16;
        if (r2 == 0) goto L_0x03a8;
    L_0x0327:
        r0 = r19;
        r2 = r0.f1143y;
        if (r2 == 0) goto L_0x03a8;
    L_0x032d:
        r0 = r19;
        r1 = r0.f1143y;
        r12 = r1;
        goto L_0x01a2;
    L_0x0334:
        r1 = 0;
        r11 = r1;
        goto L_0x01b6;
    L_0x0338:
        r9 = r2.height;
        r16 = -2;
        r0 = r16;
        if (r9 == r0) goto L_0x0344;
    L_0x0340:
        r9 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        goto L_0x01cc;
    L_0x0344:
        r9 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        goto L_0x01cc;
    L_0x0348:
        r1 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        goto L_0x01ed;
    L_0x034c:
        r10 = r7;
        goto L_0x01f9;
    L_0x034f:
        r11 = 19;
        goto L_0x0204;
    L_0x0353:
        r0 = r19;
        r0.setMeasuredDimension(r14, r2);
    L_0x0358:
        r0 = r19;
        r1 = r0.f1116M;
        if (r1 == 0) goto L_0x0369;
    L_0x035e:
        r0 = r19;
        r1 = r0.f1116M;
        r2 = r19.getMeasuredHeight();
        r1.setContentHeight(r2);
    L_0x0369:
        r0 = r19;
        r1 = r0.f1144z;
        if (r1 == 0) goto L_0x0044;
    L_0x036f:
        r0 = r19;
        r1 = r0.f1144z;
        r1 = r1.getVisibility();
        r2 = 8;
        if (r1 == r2) goto L_0x0044;
    L_0x037b:
        r0 = r19;
        r1 = r0.f1144z;
        r0 = r19;
        r2 = r0.f1105B;
        r2 = r2 * 2;
        r2 = r14 - r2;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r3);
        r3 = r19.getMeasuredHeight();
        r4 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r3 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r4);
        r1.measure(r2, r3);
        goto L_0x0044;
    L_0x039c:
        r0 = r19;
        r0.setMeasuredDimension(r14, r3);
        goto L_0x0358;
    L_0x03a2:
        r1 = r2;
        goto L_0x026c;
    L_0x03a5:
        r2 = r10;
        goto L_0x0217;
    L_0x03a8:
        r12 = r1;
        goto L_0x01a2;
    L_0x03ab:
        r1 = r4;
        r2 = r5;
        goto L_0x0116;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.internal.widget.ActionBarView.onMeasure(int, int):void");
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (!(savedState.f1099a == 0 || this.f1121R == null || this.f1115L == null)) {
            SupportMenuItem supportMenuItem = (SupportMenuItem) this.f1115L.findItem(savedState.f1099a);
            if (supportMenuItem != null) {
                supportMenuItem.expandActionView();
            }
        }
        if (savedState.f1100b) {
            m1940c();
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        if (!(this.f1121R == null || this.f1121R.f1102b == null)) {
            savedState.f1099a = this.f1121R.f1102b.getItemId();
        }
        savedState.f1100b = m1942e();
        return savedState;
    }

    public void setCallback(C0036b c0036b) {
        this.f1119P = c0036b;
    }

    public void setCollapsable(boolean z) {
        this.f1113J = z;
    }

    public /* bridge */ /* synthetic */ void setContentHeight(int i) {
        super.setContentHeight(i);
    }

    public void setContextView(ActionBarContextView actionBarContextView) {
        this.f1116M = actionBarContextView;
    }

    public void setCustomNavigationView(View view) {
        Object obj = (this.f1128j & 16) != 0 ? 1 : null;
        if (!(this.f1143y == null || obj == null)) {
            removeView(this.f1143y);
        }
        this.f1143y = view;
        if (this.f1143y != null && obj != null) {
            addView(this.f1143y);
        }
    }

    public void setDisplayOptions(int i) {
        int i2 = 8;
        int i3 = -1;
        boolean z = true;
        if (this.f1128j != -1) {
            i3 = this.f1128j ^ i;
        }
        this.f1128j = i;
        if ((i3 & 31) != 0) {
            boolean z2;
            boolean z3 = (i & 2) != 0;
            int i4 = (z3 && this.f1125g == null) ? 0 : 8;
            this.f1134p.setVisibility(i4);
            if ((i3 & 4) != 0) {
                z2 = (i & 4) != 0;
                this.f1134p.m1913a(z2);
                if (z2) {
                    setHomeButtonEnabled(true);
                }
            }
            if ((i3 & 1) != 0) {
                z2 = (this.f1132n == null || (i & 1) == 0) ? false : true;
                this.f1134p.m1912a(z2 ? this.f1132n : this.f1131m);
            }
            if ((i3 & 8) != 0) {
                if ((i & 8) != 0) {
                    m1937p();
                } else {
                    removeView(this.f1136r);
                }
            }
            if (!(this.f1136r == null || (i3 & 6) == 0)) {
                z2 = (this.f1128j & 4) != 0;
                View view = this.f1139u;
                if (!z3) {
                    i2 = z2 ? 0 : 4;
                }
                view.setVisibility(i2);
                LinearLayout linearLayout = this.f1136r;
                if (z3 || !z2) {
                    z = false;
                }
                linearLayout.setEnabled(z);
            }
            if (!((i3 & 16) == 0 || this.f1143y == null)) {
                if ((i & 16) != 0) {
                    addView(this.f1143y);
                } else {
                    removeView(this.f1143y);
                }
            }
            requestLayout();
        } else {
            invalidate();
        }
        if (!this.f1134p.isEnabled()) {
            this.f1134p.setContentDescription(null);
        } else if ((i & 4) != 0) {
            this.f1134p.setContentDescription(this.f1133o.getResources().getText(R.abc_action_bar_up_description));
        } else {
            this.f1134p.setContentDescription(this.f1133o.getResources().getText(R.abc_action_bar_home_description));
        }
    }

    public void setDropdownAdapter(SpinnerAdapter spinnerAdapter) {
        this.f1118O = spinnerAdapter;
        if (this.f1140v != null) {
            this.f1140v.m2093a(spinnerAdapter);
        }
    }

    public void setDropdownSelectedPosition(int i) {
        this.f1140v.m1883a(i);
    }

    public void setEmbeddedTabView(ScrollingTabContainerView scrollingTabContainerView) {
        if (this.f1142x != null) {
            removeView(this.f1142x);
        }
        this.f1142x = scrollingTabContainerView;
        this.f1112I = scrollingTabContainerView != null;
        if (this.f1112I && this.f1127i == 2) {
            addView(this.f1142x);
            ViewGroup.LayoutParams layoutParams = this.f1142x.getLayoutParams();
            layoutParams.width = -2;
            layoutParams.height = -1;
            scrollingTabContainerView.setAllowCollapse(true);
        }
    }

    public void setHomeAsUpIndicator(int i) {
        this.f1134p.m1911a(i);
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        this.f1134p.m1914b(drawable);
    }

    public void setHomeButtonEnabled(boolean z) {
        this.f1134p.setEnabled(z);
        this.f1134p.setFocusable(z);
        if (!z) {
            this.f1134p.setContentDescription(null);
        } else if ((this.f1128j & 4) != 0) {
            this.f1134p.setContentDescription(this.f1133o.getResources().getText(R.abc_action_bar_up_description));
        } else {
            this.f1134p.setContentDescription(this.f1133o.getResources().getText(R.abc_action_bar_home_description));
        }
    }

    public void setIcon(int i) {
        setIcon(this.f1133o.getResources().getDrawable(i));
    }

    public void setIcon(Drawable drawable) {
        this.f1131m = drawable;
        if (drawable != null && ((this.f1128j & 1) == 0 || this.f1132n == null)) {
            this.f1134p.m1912a(drawable);
        }
        if (this.f1125g != null) {
            this.f1135q.m1912a(this.f1131m.getConstantState().newDrawable(getResources()));
        }
    }

    public void setLogo(int i) {
        setLogo(this.f1133o.getResources().getDrawable(i));
    }

    public void setLogo(Drawable drawable) {
        this.f1132n = drawable;
        if (drawable != null && (this.f1128j & 1) != 0) {
            this.f1134p.m1912a(drawable);
        }
    }

    public void setMenu(SupportMenu supportMenu, MenuPresenter.MenuPresenter menuPresenter) {
        if (supportMenu != this.f1115L) {
            ActionMenuView actionMenuView;
            if (this.f1115L != null) {
                this.f1115L.m1776b(this.b);
                this.f1115L.m1776b(this.f1121R);
            }
            MenuBuilder menuBuilder = (MenuBuilder) supportMenu;
            this.f1115L = menuBuilder;
            if (this.a != null) {
                ViewGroup viewGroup = (ViewGroup) this.a.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(this.a);
                }
            }
            if (this.b == null) {
                this.b = new ActionMenuPresenter(this.f1133o);
                this.b.m1663a(menuPresenter);
                this.b.m1670b(R.action_menu_presenter);
                this.f1121R = new C0049a();
            }
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
            ViewGroup viewGroup2;
            if (this.d) {
                this.b.m1691b(false);
                this.b.m1681a(getContext().getResources().getDisplayMetrics().widthPixels, true);
                this.b.m1680a(Integer.MAX_VALUE);
                layoutParams.width = -1;
                m1923a(menuBuilder);
                actionMenuView = (ActionMenuView) this.b.m1678a((ViewGroup) this);
                if (this.c != null) {
                    viewGroup2 = (ViewGroup) actionMenuView.getParent();
                    if (!(viewGroup2 == null || viewGroup2 == this.c)) {
                        viewGroup2.removeView(actionMenuView);
                    }
                    actionMenuView.setVisibility(m1938a());
                    this.c.addView(actionMenuView, layoutParams);
                } else {
                    actionMenuView.setLayoutParams(layoutParams);
                }
            } else {
                this.b.m1691b(getResources().getBoolean(R.abc_action_bar_expanded_action_views_exclusive));
                m1923a(menuBuilder);
                actionMenuView = (ActionMenuView) this.b.m1678a((ViewGroup) this);
                actionMenuView.m1712a(menuBuilder);
                viewGroup2 = (ViewGroup) actionMenuView.getParent();
                if (!(viewGroup2 == null || viewGroup2 == this)) {
                    viewGroup2.removeView(actionMenuView);
                }
                addView(actionMenuView, layoutParams);
            }
            this.a = actionMenuView;
        }
    }

    public void setNavigationMode(int i) {
        int i2 = this.f1127i;
        if (i != i2) {
            switch (i2) {
                case VideoSize.CIF /*1*/:
                    if (this.f1141w != null) {
                        removeView(this.f1141w);
                        break;
                    }
                    break;
                case VideoSize.HVGA /*2*/:
                    if (this.f1142x != null && this.f1112I) {
                        removeView(this.f1142x);
                        break;
                    }
            }
            switch (i) {
                case VideoSize.CIF /*1*/:
                    if (this.f1140v == null) {
                        this.f1140v = new SpinnerICS(this.f1133o, null, R.actionDropDownStyle);
                        this.f1141w = (LinearLayout) LayoutInflater.from(this.f1133o).inflate(R.abc_action_bar_view_list_nav_layout, null);
                        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
                        layoutParams.gravity = 17;
                        this.f1141w.addView(this.f1140v, layoutParams);
                    }
                    if (this.f1140v.m1888d() != this.f1118O) {
                        this.f1140v.m2093a(this.f1118O);
                    }
                    this.f1140v.m1863a(this.f1122S);
                    addView(this.f1141w);
                    break;
                case VideoSize.HVGA /*2*/:
                    if (this.f1142x != null && this.f1112I) {
                        addView(this.f1142x);
                        break;
                    }
            }
            this.f1127i = i;
            requestLayout();
        }
    }

    public void setSplitActionBar(boolean z) {
        if (this.d != z) {
            if (this.a != null) {
                ViewGroup viewGroup = (ViewGroup) this.a.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(this.a);
                }
                if (z) {
                    if (this.c != null) {
                        this.c.addView(this.a);
                    }
                    this.a.getLayoutParams().width = -1;
                } else {
                    addView(this.a);
                    this.a.getLayoutParams().width = -2;
                }
                this.a.requestLayout();
            }
            if (this.c != null) {
                this.c.setVisibility(z ? 0 : 8);
            }
            if (this.b != null) {
                if (z) {
                    this.b.m1691b(false);
                    this.b.m1681a(getContext().getResources().getDisplayMetrics().widthPixels, true);
                    this.b.m1680a(Integer.MAX_VALUE);
                } else {
                    this.b.m1691b(getResources().getBoolean(R.abc_action_bar_expanded_action_views_exclusive));
                }
            }
            super.setSplitActionBar(z);
        }
    }

    public /* bridge */ /* synthetic */ void setSplitView(ActionBarContainer actionBarContainer) {
        super.setSplitView(actionBarContainer);
    }

    public /* bridge */ /* synthetic */ void setSplitWhenNarrow(boolean z) {
        super.setSplitWhenNarrow(z);
    }

    public void setSubtitle(CharSequence charSequence) {
        int i = 0;
        this.f1130l = charSequence;
        if (this.f1138t != null) {
            this.f1138t.setText(charSequence);
            this.f1138t.setVisibility(charSequence != null ? 0 : 8);
            int i2 = (this.f1125g != null || (this.f1128j & 8) == 0 || (TextUtils.isEmpty(this.f1129k) && TextUtils.isEmpty(this.f1130l))) ? 0 : 1;
            LinearLayout linearLayout = this.f1136r;
            if (i2 == 0) {
                i = 8;
            }
            linearLayout.setVisibility(i);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.f1111H = true;
        m1924a(charSequence);
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    public void setWindowCallback(Callback callback) {
        this.f1126h = callback;
    }

    public void setWindowTitle(CharSequence charSequence) {
        if (!this.f1111H) {
            m1924a(charSequence);
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
