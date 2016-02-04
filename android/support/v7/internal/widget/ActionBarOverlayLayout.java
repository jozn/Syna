package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.p010a.R.R;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class ActionBarOverlayLayout extends FrameLayout {
    static final int[] f1090a;
    private int f1091b;
    private ActionBar f1092c;
    private final Rect f1093d;

    static {
        f1090a = new int[]{R.actionBarSize};
    }

    public ActionBarOverlayLayout(Context context) {
        super(context);
        this.f1093d = new Rect(0, 0, 0, 0);
        m1909a(context);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1093d = new Rect(0, 0, 0, 0);
        m1909a(context);
    }

    private void m1909a(Context context) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f1090a);
        this.f1091b = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.recycle();
    }

    public void setActionBar(ActionBar actionBar) {
        this.f1092c = actionBar;
    }
}
