package com.mmdt.syna.view.conversation.conversationpage.pushtotalk;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import com.mmdt.syna.R.R;

public class ProgressWheel extends View {
    private RectF f2214A;
    private RectF f2215B;
    private RectF f2216C;
    private int f2217D;
    private int f2218E;
    private Handler f2219F;
    private String f2220G;
    private String[] f2221H;
    int f2222a;
    boolean f2223b;
    private int f2224c;
    private int f2225d;
    private int f2226e;
    private int f2227f;
    private int f2228g;
    private int f2229h;
    private int f2230i;
    private int f2231j;
    private float f2232k;
    private int f2233l;
    private int f2234m;
    private int f2235n;
    private int f2236o;
    private int f2237p;
    private int f2238q;
    private int f2239r;
    private int f2240s;
    private int f2241t;
    private Paint f2242u;
    private Paint f2243v;
    private Paint f2244w;
    private Paint f2245x;
    private Paint f2246y;
    private RectF f2247z;

    public ProgressWheel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2224c = 0;
        this.f2225d = 0;
        this.f2226e = 100;
        this.f2227f = 80;
        this.f2228g = 60;
        this.f2229h = 20;
        this.f2230i = 20;
        this.f2231j = 20;
        this.f2232k = 0.0f;
        this.f2233l = 5;
        this.f2234m = 5;
        this.f2235n = 5;
        this.f2236o = 5;
        this.f2237p = 0;
        this.f2238q = 0;
        this.f2239r = 0;
        this.f2240s = -1428300323;
        this.f2241t = -16777216;
        this.f2242u = new Paint();
        this.f2243v = new Paint();
        this.f2244w = new Paint();
        this.f2245x = new Paint();
        this.f2246y = new Paint();
        this.f2247z = new RectF();
        this.f2214A = new RectF();
        this.f2215B = new RectF();
        this.f2216C = new RectF();
        this.f2217D = 2;
        this.f2218E = 0;
        this.f2219F = new ProgressWheel(this);
        this.f2222a = 0;
        this.f2223b = false;
        this.f2220G = "";
        this.f2221H = new String[0];
        m3203a(context.obtainStyledAttributes(attributeSet, R.ProgressWheel));
    }

    private void m3203a(TypedArray typedArray) {
        this.f2229h = (int) typedArray.getDimension(10, (float) this.f2229h);
        this.f2230i = (int) typedArray.getDimension(5, (float) this.f2230i);
        this.f2217D = (int) typedArray.getDimension(6, (float) this.f2217D);
        this.f2218E = typedArray.getInteger(7, this.f2218E);
        if (this.f2218E < 0) {
            this.f2218E = 0;
        }
        this.f2237p = typedArray.getColor(3, this.f2237p);
        this.f2228g = (int) typedArray.getDimension(11, (float) this.f2228g);
        this.f2231j = (int) typedArray.getDimension(2, (float) this.f2231j);
        this.f2241t = typedArray.getColor(1, this.f2241t);
        if (typedArray.hasValue(0)) {
            setText(typedArray.getString(0));
        }
        this.f2240s = typedArray.getColor(4, this.f2240s);
        this.f2239r = typedArray.getColor(8, this.f2239r);
        this.f2238q = typedArray.getColor(12, this.f2238q);
        this.f2232k = typedArray.getDimension(13, this.f2232k);
        typedArray.recycle();
    }

    private void m3205b() {
        this.f2242u.setColor(this.f2237p);
        this.f2242u.setAntiAlias(true);
        this.f2242u.setStyle(Style.STROKE);
        this.f2242u.setStrokeWidth((float) this.f2229h);
        this.f2244w.setColor(this.f2240s);
        this.f2244w.setAntiAlias(true);
        this.f2244w.setStyle(Style.STROKE);
        this.f2244w.setStrokeWidth((float) this.f2230i);
        this.f2243v.setColor(this.f2239r);
        this.f2243v.setAntiAlias(true);
        this.f2243v.setStyle(Style.FILL);
        this.f2245x.setColor(this.f2241t);
        this.f2245x.setStyle(Style.FILL);
        this.f2245x.setAntiAlias(true);
        this.f2245x.setTextSize((float) this.f2231j);
        this.f2246y.setColor(this.f2238q);
        this.f2246y.setAntiAlias(true);
        this.f2246y.setStyle(Style.STROKE);
        this.f2246y.setStrokeWidth(this.f2232k);
    }

    private void m3207c() {
        int min = Math.min(this.f2225d, this.f2224c);
        int i = this.f2225d - min;
        min = this.f2224c - min;
        this.f2233l = getPaddingTop() + (min / 2);
        this.f2234m = (min / 2) + getPaddingBottom();
        this.f2235n = getPaddingLeft() + (i / 2);
        this.f2236o = getPaddingRight() + (i / 2);
        this.f2247z = new RectF((float) this.f2235n, (float) this.f2233l, (float) (getLayoutParams().width - this.f2236o), (float) (getLayoutParams().height - this.f2234m));
        this.f2214A = new RectF((float) (this.f2235n + this.f2229h), (float) (this.f2233l + this.f2229h), (float) ((getLayoutParams().width - this.f2236o) - this.f2229h), (float) ((getLayoutParams().height - this.f2234m) - this.f2229h));
        this.f2216C = new RectF((this.f2214A.left + (((float) this.f2230i) / 2.0f)) + (this.f2232k / 2.0f), (this.f2214A.top + (((float) this.f2230i) / 2.0f)) + (this.f2232k / 2.0f), (this.f2214A.right - (((float) this.f2230i) / 2.0f)) - (this.f2232k / 2.0f), (this.f2214A.bottom - (((float) this.f2230i) / 2.0f)) - (this.f2232k / 2.0f));
        this.f2215B = new RectF((this.f2214A.left - (((float) this.f2230i) / 2.0f)) - (this.f2232k / 2.0f), (this.f2214A.top - (((float) this.f2230i) / 2.0f)) - (this.f2232k / 2.0f), (this.f2214A.right + (((float) this.f2230i) / 2.0f)) + (this.f2232k / 2.0f), (this.f2214A.bottom + (((float) this.f2230i) / 2.0f)) + (this.f2232k / 2.0f));
        this.f2226e = ((getLayoutParams().width - this.f2236o) - this.f2229h) / 2;
        this.f2227f = (this.f2226e - this.f2229h) + 1;
    }

    public void m3208a() {
        this.f2222a = 0;
        setText("0%");
        invalidate();
    }

    public int getPaddingBottom() {
        return this.f2234m;
    }

    public int getPaddingLeft() {
        return this.f2235n;
    }

    public int getPaddingRight() {
        return this.f2236o;
    }

    public int getPaddingTop() {
        return this.f2233l;
    }

    protected void onDraw(Canvas canvas) {
        int i = 0;
        super.onDraw(canvas);
        canvas.drawArc(this.f2214A, 360.0f, 360.0f, false, this.f2244w);
        canvas.drawArc(this.f2215B, 360.0f, 360.0f, false, this.f2246y);
        canvas.drawArc(this.f2216C, 360.0f, 360.0f, false, this.f2246y);
        if (this.f2223b) {
            canvas.drawArc(this.f2214A, (float) (this.f2222a - 90), (float) this.f2228g, false, this.f2242u);
        } else {
            canvas.drawArc(this.f2214A, -90.0f, (float) this.f2222a, false, this.f2242u);
        }
        canvas.drawCircle(((this.f2214A.width() / 2.0f) + ((float) this.f2230i)) + ((float) this.f2235n), ((this.f2214A.height() / 2.0f) + ((float) this.f2230i)) + ((float) this.f2233l), (float) this.f2227f, this.f2243v);
        float descent = ((this.f2245x.descent() - this.f2245x.ascent()) / 2.0f) - this.f2245x.descent();
        String[] strArr = this.f2221H;
        int length = strArr.length;
        while (i < length) {
            String str = strArr[i];
            canvas.drawText(str, ((float) (getWidth() / 2)) - (this.f2245x.measureText(str) / 2.0f), ((float) (getHeight() / 2)) + descent, this.f2245x);
            i++;
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f2225d = i;
        this.f2224c = i2;
        m3207c();
        m3205b();
        invalidate();
    }

    public void setBarColor(int i) {
        this.f2237p = i;
    }

    public void setBarLength(int i) {
        this.f2228g = i;
    }

    public void setBarWidth(int i) {
        this.f2229h = i;
    }

    public void setCircleColor(int i) {
        this.f2239r = i;
    }

    public void setCircleRadius(int i) {
        this.f2227f = i;
    }

    public void setDelayMillis(int i) {
        this.f2218E = i;
    }

    public void setPaddingBottom(int i) {
        this.f2234m = i;
    }

    public void setPaddingLeft(int i) {
        this.f2235n = i;
    }

    public void setPaddingRight(int i) {
        this.f2236o = i;
    }

    public void setPaddingTop(int i) {
        this.f2233l = i;
    }

    public void setProgress(int i) {
        this.f2223b = false;
        this.f2222a = i;
        this.f2219F.sendEmptyMessage(0);
    }

    public void setRimColor(int i) {
        this.f2240s = i;
    }

    public void setRimShader(Shader shader) {
        this.f2244w.setShader(shader);
    }

    public void setRimWidth(int i) {
        this.f2230i = i;
    }

    public void setSpinSpeed(int i) {
        this.f2217D = i;
    }

    public void setText(String str) {
        this.f2220G = str;
        this.f2221H = this.f2220G.split("\n");
    }

    public void setTextColor(int i) {
        this.f2241t = i;
    }

    public void setTextSize(int i) {
        this.f2231j = i;
    }
}
