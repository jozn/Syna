package org.catrobat.paintroid.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.dialog.IndeterminateProgressDialog;
import org.catrobat.paintroid.p078a.Command;
import org.catrobat.paintroid.p081c.Tool.Tool;
import org.catrobat.paintroid.p081c.p084b.BaseTool;

public class DrawingSurface extends SurfaceView implements Callback {
    protected boolean f4508a;
    private DrawingSurfaceThread f4509b;
    private Bitmap f4510c;
    private Rect f4511d;
    private Canvas f4512e;
    private Paint f4513f;
    private Paint f4514g;

    /* renamed from: org.catrobat.paintroid.ui.DrawingSurface.a */
    private class C0144a implements Runnable {
        final /* synthetic */ DrawingSurface f4507a;

        private C0144a(DrawingSurface drawingSurface) {
            this.f4507a = drawingSurface;
        }

        public void run() {
            SurfaceHolder holder = this.f4507a.getHolder();
            if (VERSION.SDK_INT >= 18) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Log.w("PAINTROID", "DrawingSurface: sleeping thread was interrupted");
                }
            }
            synchronized (holder) {
                try {
                    Canvas lockCanvas = holder.lockCanvas();
                    if (lockCanvas != null && this.f4507a.f4508a) {
                        this.f4507a.m5774a(lockCanvas);
                    }
                    if (lockCanvas != null) {
                        holder.unlockCanvasAndPost(lockCanvas);
                    }
                } catch (Throwable th) {
                    if (null != null) {
                        holder.unlockCanvasAndPost(null);
                    }
                }
            }
        }
    }

    public DrawingSurface(Context context) {
        super(context);
        m5776f();
    }

    public DrawingSurface(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m5776f();
    }

    private synchronized void m5774a(Canvas canvas) {
        try {
            if (!(this.f4511d == null || canvas == null || this.f4510c == null || this.f4512e == null || this.f4510c.isRecycled())) {
                PaintroidApplication.f4195e.m5797a(canvas);
                canvas.drawColor(-3355444);
                canvas.drawRect(this.f4511d, BaseTool.f4288a);
                canvas.drawRect(this.f4511d, this.f4513f);
                while (this.f4508a) {
                    Command d = PaintroidApplication.f4193c.m5450d();
                    if (d == null) {
                        break;
                    }
                    d.m5439a(this.f4512e, this.f4510c);
                    canvas.drawBitmap(this.f4510c, 0.0f, 0.0f, null);
                    PaintroidApplication.f4194d.m5508a(Tool.RESET_INTERNAL_STATE);
                    if (!PaintroidApplication.f4193c.m5448b()) {
                        IndeterminateProgressDialog.m5764a().dismiss();
                    }
                }
                if (!(this.f4510c == null || this.f4510c.isRecycled() || !this.f4508a)) {
                    canvas.drawBitmap(this.f4510c, 0.0f, 0.0f, null);
                    PaintroidApplication.f4194d.m5505a(canvas);
                }
            }
        } catch (Exception e) {
            Log.e("PAINTROID", "DrawingSurface:" + e.getMessage() + "\r\n" + e.toString());
            e.printStackTrace();
        }
    }

    private void m5776f() {
        getHolder().addCallback(this);
        this.f4511d = new Rect();
        this.f4512e = new Canvas();
        this.f4513f = new Paint();
        this.f4513f.setColor(-16777216);
        this.f4513f.setStyle(Style.STROKE);
        this.f4514g = new Paint();
        this.f4514g.setColor(0);
        this.f4514g.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
    }

    public int m5777a(PointF pointF) {
        try {
            if (!(this.f4510c == null || this.f4510c.isRecycled())) {
                return this.f4510c.getPixel((int) pointF.x, (int) pointF.y);
            }
        } catch (IllegalArgumentException e) {
            Log.w("PAINTROID", "getBitmapColor coordinate out of bounds");
        }
        return 0;
    }

    public synchronized void m5778a() {
        if (this.f4510c != null) {
            this.f4510c.recycle();
        }
    }

    public synchronized void m5779a(Bitmap bitmap) {
        PaintroidApplication.f4193c.m5449c();
        PaintroidApplication.f4193c.m5445a(bitmap);
        setBitmap(bitmap);
        PaintroidApplication.f4195e.m5794a();
        if (this.f4508a) {
            this.f4509b.m5788a();
        }
    }

    public void m5780a(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
        if (this.f4510c != null && !this.f4510c.isRecycled()) {
            this.f4510c.getPixels(iArr, i, i2, i3, i4, i5, i6);
        }
    }

    public synchronized Bitmap m5781b() {
        Bitmap createBitmap;
        createBitmap = (this.f4510c == null || this.f4510c.isRecycled()) ? null : Bitmap.createBitmap(this.f4510c);
        return createBitmap;
    }

    public synchronized boolean m5782c() {
        boolean z;
        z = (this.f4510c == null || this.f4510c.isRecycled() || !this.f4508a) ? false : true;
        return z;
    }

    public int m5783d() {
        return this.f4510c == null ? -1 : this.f4510c.getWidth();
    }

    public int m5784e() {
        return this.f4510c == null ? -1 : this.f4510c.getHeight();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            PaintroidApplication.f4195e = (Perspective) bundle.getSerializable("BUNDLE_PERSPECTIVE");
            super.onRestoreInstanceState(bundle.getParcelable("BUNDLE_INSTANCE_STATE"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("BUNDLE_INSTANCE_STATE", super.onSaveInstanceState());
        bundle.putSerializable("BUNDLE_PERSPECTIVE", PaintroidApplication.f4195e);
        return bundle;
    }

    public synchronized void setBitmap(Bitmap bitmap) {
        if (!(this.f4510c == null || bitmap == null)) {
            this.f4510c.recycle();
        }
        if (bitmap != null) {
            this.f4510c = bitmap;
            this.f4512e.setBitmap(bitmap);
            this.f4511d.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.f4508a = true;
        Log.w("PAINTROID", "DrawingSurfaceView.surfaceChanged");
        PaintroidApplication.f4195e.m5799a(surfaceHolder);
        if (this.f4510c != null && this.f4509b != null) {
            this.f4509b.m5788a();
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.w("PAINTROID", "DrawingSurfaceView.surfaceCreated");
        this.f4509b = new DrawingSurfaceThread(new C0144a());
    }

    public synchronized void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.f4508a = false;
        Log.w("PAINTROID", "DrawingSurfaceView.surfaceDestroyed");
        if (this.f4509b != null) {
            this.f4509b.m5789b();
        }
    }
}
