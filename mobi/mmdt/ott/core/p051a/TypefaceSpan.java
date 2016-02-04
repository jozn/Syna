package mobi.mmdt.ott.core.p051a;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.util.LruCache;

/* renamed from: mobi.mmdt.ott.core.a.n */
public class TypefaceSpan extends MetricAffectingSpan {
    private static LruCache<String, Typeface> f3531a;
    private Typeface f3532b;

    static {
        f3531a = new LruCache(12);
    }

    public TypefaceSpan(Context context, String str) {
        this.f3532b = (Typeface) f3531a.get(str);
        if (this.f3532b == null) {
            this.f3532b = Typeface.createFromAsset(context.getApplicationContext().getAssets(), String.format("fonts/%s", new Object[]{str}));
            f3531a.put(str, this.f3532b);
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setTypeface(this.f3532b);
        textPaint.setFlags(textPaint.getFlags() | 128);
    }

    public void updateMeasureState(TextPaint textPaint) {
        textPaint.setTypeface(this.f3532b);
        textPaint.setFlags(textPaint.getFlags() | 128);
    }
}
