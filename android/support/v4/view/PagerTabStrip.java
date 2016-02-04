package android.support.v4.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import org.linphone.core.VideoSize;

public class PagerTabStrip extends PagerTitleStrip {
    private int f450f;
    private int f451g;
    private int f452h;
    private int f453i;
    private int f454j;
    private int f455k;
    private final Paint f456l;
    private final Rect f457m;
    private int f458n;
    private boolean f459o;
    private boolean f460p;
    private int f461q;
    private boolean f462r;
    private float f463s;
    private float f464t;
    private int f465u;

    public PagerTabStrip(Context context) {
        this(context, null);
    }

    public PagerTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f456l = new Paint();
        this.f457m = new Rect();
        this.f458n = 255;
        this.f459o = false;
        this.f460p = false;
        this.f450f = this.e;
        this.f456l.setColor(this.f450f);
        float f = context.getResources().getDisplayMetrics().density;
        this.f451g = (int) ((3.0f * f) + 0.5f);
        this.f452h = (int) ((6.0f * f) + 0.5f);
        this.f453i = (int) (64.0f * f);
        this.f455k = (int) ((16.0f * f) + 0.5f);
        this.f461q = (int) ((1.0f * f) + 0.5f);
        this.f454j = (int) ((f * 32.0f) + 0.5f);
        this.f465u = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(m525b());
        setWillNotDraw(false);
        this.b.setFocusable(true);
        this.b.setOnClickListener(new PagerTabStrip(this));
        this.d.setFocusable(true);
        this.d.setOnClickListener(new PagerTabStrip(this));
        if (getBackground() == null) {
            this.f459o = true;
        }
    }

    int m526a() {
        return Math.max(super.m521a(), this.f454j);
    }

    void m527a(int i, float f, boolean z) {
        Rect rect = this.f457m;
        int height = getHeight();
        int i2 = height - this.f451g;
        rect.set(this.c.getLeft() - this.f455k, i2, this.c.getRight() + this.f455k, height);
        super.m522a(i, f, z);
        this.f458n = (int) ((Math.abs(f - 0.5f) * 2.0f) * 255.0f);
        rect.union(this.c.getLeft() - this.f455k, i2, this.c.getRight() + this.f455k, height);
        invalidate(rect);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int left = this.c.getLeft() - this.f455k;
        int right = this.c.getRight() + this.f455k;
        int i = height - this.f451g;
        this.f456l.setColor((this.f458n << 24) | (this.f450f & 16777215));
        canvas.drawRect((float) left, (float) i, (float) right, (float) height, this.f456l);
        if (this.f459o) {
            this.f456l.setColor(-16777216 | (this.f450f & 16777215));
            canvas.drawRect((float) getPaddingLeft(), (float) (height - this.f461q), (float) (getWidth() - getPaddingRight()), (float) height, this.f456l);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0 && this.f462r) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (action) {
            case VideoSize.QCIF /*0*/:
                this.f463s = x;
                this.f464t = y;
                this.f462r = false;
                break;
            case VideoSize.CIF /*1*/:
                if (x >= ((float) (this.c.getLeft() - this.f455k))) {
                    if (x > ((float) (this.c.getRight() + this.f455k))) {
                        this.a.setCurrentItem(this.a.m599c() + 1);
                        break;
                    }
                }
                this.a.setCurrentItem(this.a.m599c() - 1);
                break;
                break;
            case VideoSize.HVGA /*2*/:
                if (Math.abs(x - this.f463s) > ((float) this.f465u) || Math.abs(y - this.f464t) > ((float) this.f465u)) {
                    this.f462r = true;
                    break;
                }
        }
        return true;
    }

    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
        if (!this.f460p) {
            this.f459o = (-16777216 & i) == 0;
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (!this.f460p) {
            this.f459o = drawable == null;
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (!this.f460p) {
            this.f459o = i == 0;
        }
    }

    public void setDrawFullUnderline(boolean z) {
        this.f459o = z;
        this.f460p = true;
        invalidate();
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        if (i4 < this.f452h) {
            i4 = this.f452h;
        }
        super.setPadding(i, i2, i3, i4);
    }

    public void setTabIndicatorColor(int i) {
        this.f450f = i;
        this.f456l.setColor(this.f450f);
        invalidate();
    }

    public void setTabIndicatorColorResource(int i) {
        setTabIndicatorColor(getContext().getResources().getColor(i));
    }

    public void setTextSpacing(int i) {
        if (i < this.f453i) {
            i = this.f453i;
        }
        super.setTextSpacing(i);
    }
}
