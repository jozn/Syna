package me.grantland.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.SingleLineTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.widget.TextView;
import com.mmdt.syna.R.R;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: me.grantland.widget.a */
public class AutofitHelper {
    private TextView f3218a;
    private TextPaint f3219b;
    private float f3220c;
    private int f3221d;
    private float f3222e;
    private float f3223f;
    private float f3224g;
    private boolean f3225h;
    private boolean f3226i;
    private ArrayList<AutofitHelper> f3227j;
    private TextWatcher f3228k;
    private OnLayoutChangeListener f3229l;

    /* renamed from: me.grantland.widget.a.c */
    public interface AutofitHelper {
        void m4052a(float f, float f2);
    }

    /* renamed from: me.grantland.widget.a.a */
    private class AutofitHelper implements OnLayoutChangeListener {
        final /* synthetic */ AutofitHelper f3216a;

        private AutofitHelper(AutofitHelper autofitHelper) {
            this.f3216a = autofitHelper;
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.f3216a.m4079c();
        }
    }

    /* renamed from: me.grantland.widget.a.b */
    private class AutofitHelper implements TextWatcher {
        final /* synthetic */ AutofitHelper f3217a;

        private AutofitHelper(AutofitHelper autofitHelper) {
            this.f3217a = autofitHelper;
        }

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.f3217a.m4079c();
        }
    }

    private AutofitHelper(TextView textView) {
        this.f3228k = new AutofitHelper();
        this.f3229l = new AutofitHelper();
        float f = textView.getContext().getResources().getDisplayMetrics().scaledDensity;
        this.f3218a = textView;
        this.f3219b = new TextPaint();
        m4068f(textView.getTextSize());
        this.f3221d = AutofitHelper.m4065b(textView);
        this.f3222e = f * 8.0f;
        this.f3223f = this.f3220c;
        this.f3224g = 0.5f;
    }

    private static float m4059a(CharSequence charSequence, TextPaint textPaint, float f, int i, float f2, float f3, float f4, DisplayMetrics displayMetrics) {
        float f5 = (f2 + f3) / 2.0f;
        int i2 = 1;
        StaticLayout staticLayout = null;
        textPaint.setTextSize(TypedValue.applyDimension(0, f5, displayMetrics));
        if (i != 1) {
            staticLayout = new StaticLayout(charSequence, textPaint, (int) f, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            i2 = staticLayout.getLineCount();
        }
        Log.d("FFF", "AutofitHelper    low=" + f2 + " high=" + f3 + " mid=" + f5 + " target=" + f + " maxLines=" + i + " lineCount=" + i2);
        if (i2 > i) {
            return AutofitHelper.m4059a(charSequence, textPaint, f, i, f2, f5, f4, displayMetrics);
        }
        if (i2 < i) {
            return AutofitHelper.m4059a(charSequence, textPaint, f, i, f5, f3, f4, displayMetrics);
        }
        float measureText;
        if (i == 1) {
            measureText = textPaint.measureText(charSequence, 0, charSequence.length());
        } else {
            measureText = 0.0f;
            for (int i3 = 0; i3 < i2; i3++) {
                if (staticLayout.getLineWidth(i3) > measureText) {
                    measureText = staticLayout.getLineWidth(i3);
                }
            }
        }
        return f3 - f2 >= f4 ? measureText > f ? AutofitHelper.m4059a(charSequence, textPaint, f, i, f2, f5, f4, displayMetrics) : measureText < f ? AutofitHelper.m4059a(charSequence, textPaint, f, i, f5, f3, f4, displayMetrics) : f5 : f2;
    }

    private static int m4060a(CharSequence charSequence, TextPaint textPaint, float f, float f2, DisplayMetrics displayMetrics) {
        textPaint.setTextSize(TypedValue.applyDimension(0, f, displayMetrics));
        return new StaticLayout(charSequence, textPaint, (int) f2, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true).getLineCount();
    }

    public static AutofitHelper m4061a(TextView textView) {
        return AutofitHelper.m4062a(textView, null, 0);
    }

    public static AutofitHelper m4062a(TextView textView, AttributeSet attributeSet, int i) {
        boolean z;
        AutofitHelper autofitHelper = new AutofitHelper(textView);
        if (attributeSet != null) {
            Context context = textView.getContext();
            int b = (int) autofitHelper.m4075b();
            float a = autofitHelper.m4069a();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.AutofitTextView, i, 0);
            z = obtainStyledAttributes.getBoolean(2, true);
            b = obtainStyledAttributes.getDimensionPixelSize(0, b);
            float f = obtainStyledAttributes.getFloat(1, a);
            obtainStyledAttributes.recycle();
            autofitHelper.m4076b((float) b).m4070a(f);
        } else {
            z = true;
        }
        autofitHelper.m4074a(z);
        return autofitHelper;
    }

    private void m4063a(float f, float f2) {
        if (this.f3227j != null) {
            Iterator it = this.f3227j.iterator();
            while (it.hasNext()) {
                ((AutofitHelper) it.next()).m4052a(f, f2);
            }
        }
    }

    private static void m4064a(TextView textView, TextPaint textPaint, float f, float f2, int i, float f3) {
        if (i > 0 && i != Integer.MAX_VALUE) {
            int width = (textView.getWidth() - textView.getPaddingLeft()) - textView.getPaddingRight();
            if (width > 0) {
                float f4;
                CharSequence text = textView.getText();
                TransformationMethod transformationMethod = textView.getTransformationMethod();
                if (transformationMethod != null) {
                    text = transformationMethod.getTransformation(text, textView);
                }
                Context context = textView.getContext();
                Resources system = Resources.getSystem();
                if (context != null) {
                    system = context.getResources();
                }
                DisplayMetrics displayMetrics = system.getDisplayMetrics();
                textPaint.set(textView.getPaint());
                textPaint.setTextSize(f2);
                if ((i != 1 || textPaint.measureText(text, 0, text.length()) <= ((float) width)) && AutofitHelper.m4060a(text, textPaint, f2, (float) width, displayMetrics) <= i) {
                    f4 = f2;
                } else {
                    f4 = AutofitHelper.m4059a(text, textPaint, (float) width, i, 0.0f, f2, f3, displayMetrics);
                }
                if (f4 >= f) {
                    f = f4;
                }
                textView.setTextSize(0, f);
            }
        }
    }

    private static int m4065b(TextView textView) {
        TransformationMethod transformationMethod = textView.getTransformationMethod();
        return (transformationMethod == null || !(transformationMethod instanceof SingleLineTransformationMethod)) ? VERSION.SDK_INT >= 16 ? textView.getMaxLines() : -1 : 1;
    }

    private void m4066d(float f) {
        if (f != this.f3222e) {
            this.f3222e = f;
            m4079c();
        }
    }

    private void m4067e(float f) {
        if (f != this.f3223f) {
            this.f3223f = f;
            m4079c();
        }
    }

    private void m4068f(float f) {
        if (this.f3220c != f) {
            this.f3220c = f;
        }
    }

    public float m4069a() {
        return this.f3224g;
    }

    public AutofitHelper m4070a(float f) {
        if (this.f3224g != f) {
            this.f3224g = f;
            m4079c();
        }
        return this;
    }

    public AutofitHelper m4071a(int i) {
        if (this.f3221d != i) {
            this.f3221d = i;
            m4079c();
        }
        return this;
    }

    public AutofitHelper m4072a(int i, float f) {
        Context context = this.f3218a.getContext();
        Resources system = Resources.getSystem();
        if (context != null) {
            system = context.getResources();
        }
        m4066d(TypedValue.applyDimension(i, f, system.getDisplayMetrics()));
        return this;
    }

    public AutofitHelper m4073a(AutofitHelper autofitHelper) {
        if (this.f3227j == null) {
            this.f3227j = new ArrayList();
        }
        this.f3227j.add(autofitHelper);
        return this;
    }

    public AutofitHelper m4074a(boolean z) {
        if (this.f3225h != z) {
            this.f3225h = z;
            if (z) {
                this.f3218a.addTextChangedListener(this.f3228k);
                this.f3218a.addOnLayoutChangeListener(this.f3229l);
                m4079c();
            } else {
                this.f3218a.removeTextChangedListener(this.f3228k);
                this.f3218a.removeOnLayoutChangeListener(this.f3229l);
                this.f3218a.setTextSize(0, this.f3220c);
            }
        }
        return this;
    }

    public float m4075b() {
        return this.f3222e;
    }

    public AutofitHelper m4076b(float f) {
        return m4072a(2, f);
    }

    public AutofitHelper m4077b(int i, float f) {
        Context context = this.f3218a.getContext();
        Resources system = Resources.getSystem();
        if (context != null) {
            system = context.getResources();
        }
        m4067e(TypedValue.applyDimension(i, f, system.getDisplayMetrics()));
        return this;
    }

    public AutofitHelper m4078c(float f) {
        return m4077b(2, f);
    }

    public void m4079c() {
        float textSize = this.f3218a.getTextSize();
        this.f3226i = true;
        AutofitHelper.m4064a(this.f3218a, this.f3219b, this.f3222e, this.f3223f, this.f3221d, this.f3224g);
        this.f3226i = false;
        float textSize2 = this.f3218a.getTextSize();
        if (textSize2 != textSize) {
            m4063a(textSize2, textSize);
        }
    }

    public void m4080c(int i, float f) {
        if (!this.f3226i) {
            Context context = this.f3218a.getContext();
            Resources system = Resources.getSystem();
            if (context != null) {
                system = context.getResources();
            }
            m4068f(TypedValue.applyDimension(i, f, system.getDisplayMetrics()));
        }
    }
}
