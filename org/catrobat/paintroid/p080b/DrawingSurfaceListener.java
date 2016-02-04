package org.catrobat.paintroid.p080b;

import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import java.util.EnumSet;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.p081c.Tool.Tool;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.Perspective;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.b.a */
public class DrawingSurfaceListener implements OnTouchListener {
    private final int f4260a;
    private final Perspective f4261b;
    private float f4262c;
    private PointF f4263d;
    private DrawingSurfaceListener f4264e;
    private long f4265f;
    private DrawingSurfaceListener f4266g;

    /* renamed from: org.catrobat.paintroid.b.a.a */
    private class DrawingSurfaceListener extends Thread {
        final /* synthetic */ DrawingSurfaceListener f4247a;
        private int f4248b;
        private boolean f4249c;
        private boolean f4250d;
        private float f4251e;
        private float f4252f;
        private int f4253g;
        private int f4254h;
        private long f4255i;
        private EnumSet<ToolType> f4256j;

        protected DrawingSurfaceListener(DrawingSurfaceListener drawingSurfaceListener) {
            boolean z = true;
            this.f4247a = drawingSurfaceListener;
            this.f4248b = 1;
            this.f4256j = EnumSet.of(ToolType.PIPETTE, new ToolType[]{ToolType.FILL, ToolType.CROP, ToolType.FLIP, ToolType.MOVE, ToolType.ZOOM});
            this.f4255i = System.nanoTime();
            if (this.f4256j.contains(PaintroidApplication.f4194d.m5510b())) {
                z = false;
            }
            this.f4249c = z;
            this.f4250d = false;
        }

        protected int m5489a(float f) {
            return (int) (8.0d / Math.pow((double) f, 0.0d));
        }

        protected void m5490a() {
            this.f4249c = false;
        }

        protected void m5491a(float f, float f2, int i, int i2) {
            this.f4251e = f;
            this.f4252f = f2;
            this.f4253g = i;
            this.f4254h = i2;
        }

        public void run() {
            while (this.f4249c) {
                Point a = PaintroidApplication.f4194d.m5503a(this.f4251e, this.f4252f, this.f4253g, this.f4254h);
                if (!(a.x == 0 && a.y == 0)) {
                    this.f4250d = true;
                    PaintroidApplication.f4195e.m5796a((float) (a.x * this.f4248b), (float) (a.y * this.f4248b));
                    PaintroidApplication.f4194d.m5512b(PaintroidApplication.f4195e.m5802b(new PointF(this.f4251e, this.f4252f)));
                }
                try {
                    DrawingSurfaceListener.sleep((long) m5489a(PaintroidApplication.f4195e.m5801b()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.f4250d = false;
            }
        }

        public synchronized void start() {
            if (this.f4253g == 0 || this.f4254h == 0) {
                throw new IllegalStateException("MoveThread could not be started. Illegal width and/or height values.");
            }
            super.start();
        }
    }

    /* renamed from: org.catrobat.paintroid.b.a.b */
    enum DrawingSurfaceListener {
        DRAW,
        PINCH
    }

    public DrawingSurfaceListener() {
        this.f4260a = 250000000;
        this.f4261b = PaintroidApplication.f4195e;
        this.f4263d = new PointF(0.0f, 0.0f);
        this.f4264e = DrawingSurfaceListener.DRAW;
    }

    private float m5492a(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    private void m5493a(MotionEvent motionEvent, PointF pointF) {
        pointF.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        PointF b = this.f4261b.m5802b(new PointF(motionEvent.getX(), motionEvent.getY()));
        switch (motionEvent.getAction()) {
            case VideoSize.QCIF /*0*/:
                PaintroidApplication.f4194d.m5509a(b);
                this.f4266g = new DrawingSurfaceListener(this);
                this.f4266g.m5491a(motionEvent.getX(), motionEvent.getY(), view.getWidth(), view.getHeight());
                this.f4266g.start();
                break;
            case VideoSize.CIF /*1*/:
            case Version.API03_CUPCAKE_15 /*3*/:
                if (this.f4266g != null) {
                    this.f4266g.m5490a();
                }
                this.f4266g = null;
                if (this.f4264e == DrawingSurfaceListener.DRAW) {
                    PaintroidApplication.f4194d.m5513c(b);
                } else {
                    PaintroidApplication.f4194d.m5508a(Tool.MOVE_CANCELED);
                }
                this.f4262c = 0.0f;
                this.f4263d.set(0.0f, 0.0f);
                break;
            case VideoSize.HVGA /*2*/:
                if (motionEvent.getPointerCount() == 1) {
                    if (System.nanoTime() >= this.f4265f + 250000000) {
                        this.f4264e = DrawingSurfaceListener.DRAW;
                        if (this.f4266g != null) {
                            this.f4266g.m5491a(motionEvent.getX(), motionEvent.getY(), view.getWidth(), view.getHeight());
                        }
                        PaintroidApplication.f4194d.m5512b(b);
                        break;
                    }
                }
                if (this.f4266g != null) {
                    if (!this.f4266g.f4250d || System.nanoTime() <= this.f4266g.f4255i + 250000000) {
                        this.f4266g.m5490a();
                        this.f4266g = null;
                    }
                }
                this.f4264e = DrawingSurfaceListener.PINCH;
                float f = this.f4262c;
                this.f4262c = m5492a(motionEvent);
                if (f > 0.0f) {
                    this.f4261b.m5803b(this.f4262c / f);
                }
                f = this.f4263d.x;
                float f2 = this.f4263d.y;
                m5493a(motionEvent, this.f4263d);
                if (f > 0.0f || f2 > 0.0f) {
                    this.f4261b.m5796a(this.f4263d.x - f, this.f4263d.y - f2);
                }
                this.f4265f = System.nanoTime();
                break;
                break;
        }
        return true;
    }
}
