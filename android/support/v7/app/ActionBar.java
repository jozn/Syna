package android.support.v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.p010a.R.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

public abstract class ActionBar {

    public static class LayoutParams extends MarginLayoutParams {
        public int f738a;

        public LayoutParams(int i) {
            this(-2, -1, i);
        }

        public LayoutParams(int i, int i2, int i3) {
            super(i, i2);
            this.f738a = -1;
            this.f738a = i3;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f738a = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.ActionBarLayout);
            this.f738a = obtainStyledAttributes.getInt(0, -1);
            obtainStyledAttributes.recycle();
        }
    }

    /* renamed from: android.support.v7.app.ActionBar.a */
    interface C0035a {
    }

    /* renamed from: android.support.v7.app.ActionBar.b */
    public interface C0036b {
        boolean m1450a(int i, long j);
    }

    /* renamed from: android.support.v7.app.ActionBar.c */
    public static abstract class C0037c {
        public abstract Drawable m1451a();

        public abstract CharSequence m1452b();

        public abstract View m1453c();

        public abstract void m1454d();

        public abstract CharSequence m1455e();
    }

    public abstract int m1456a();

    public abstract void m1457a(boolean z);

    public Context m1458b() {
        return null;
    }

    public void m1459b(boolean z) {
    }
}
