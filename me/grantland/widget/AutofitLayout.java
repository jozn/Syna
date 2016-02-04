package me.grantland.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.mmdt.syna.R.R;
import java.util.WeakHashMap;

public class AutofitLayout extends FrameLayout {
    private boolean f3211a;
    private float f3212b;
    private float f3213c;
    private WeakHashMap<View, AutofitHelper> f3214d;

    public AutofitLayout(Context context) {
        super(context);
        this.f3214d = new WeakHashMap();
        m4056a(context, null, 0);
    }

    public AutofitLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3214d = new WeakHashMap();
        m4056a(context, attributeSet, 0);
    }

    public AutofitLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3214d = new WeakHashMap();
        m4056a(context, attributeSet, i);
    }

    private void m4056a(Context context, AttributeSet attributeSet, int i) {
        boolean z;
        int i2 = -1;
        float f = -1.0f;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.AutofitTextView, i, 0);
            z = obtainStyledAttributes.getBoolean(2, true);
            i2 = obtainStyledAttributes.getDimensionPixelSize(0, -1);
            f = obtainStyledAttributes.getFloat(1, -1.0f);
            obtainStyledAttributes.recycle();
        } else {
            z = true;
        }
        this.f3211a = z;
        this.f3212b = (float) i2;
        this.f3213c = f;
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        TextView textView = (TextView) view;
        AutofitHelper a = AutofitHelper.m4061a(textView).m4074a(this.f3211a);
        if (this.f3213c > 0.0f) {
            a.m4070a(this.f3213c);
        }
        if (this.f3212b > 0.0f) {
            a.m4072a(0, this.f3212b);
        }
        this.f3214d.put(textView, a);
    }
}
