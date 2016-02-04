package org.catrobat.paintroid.p081c.p084b;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Observable;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.dialog.IndeterminateProgressDialog;
import org.catrobat.paintroid.p078a.Command;
import org.catrobat.paintroid.p078a.p079a.BaseCommand.BaseCommand;
import org.catrobat.paintroid.p078a.p079a.CropCommand;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.TopBar.TopBar;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b.f */
public class CropTool extends BaseToolWithRectangleShape {
    private static CropTool f4358L;
    private static /* synthetic */ int[] f4359M;
    private float f4360C;
    private float f4361D;
    private float f4362E;
    private float f4363F;
    private int f4364G;
    private int f4365H;
    private int f4366I;
    private int f4367J;
    private boolean f4368K;

    /* renamed from: org.catrobat.paintroid.c.b.f.a */
    protected class CropTool extends AsyncTask<Void, Integer, Void> {
        final /* synthetic */ CropTool f4354a;
        private int f4355b;
        private int f4356c;
        private final int f4357d;

        CropTool(CropTool cropTool) {
            this.f4354a = cropTool;
            this.f4355b = -1;
            this.f4356c = -1;
            this.f4357d = 0;
            cropTool.m5592o();
            this.f4355b = PaintroidApplication.f4192b.m5783d();
            this.f4356c = PaintroidApplication.f4192b.m5784e();
            cropTool.B = new Paint();
            cropTool.B.setDither(true);
            cropTool.B.setStyle(Style.STROKE);
            cropTool.B.setStrokeJoin(Join.ROUND);
        }

        private void m5568a() {
            try {
                if (PaintroidApplication.f4192b.m5782c()) {
                    m5570b();
                    m5572c();
                    m5573d();
                    m5574e();
                }
            } catch (Exception e) {
                Log.e("PAINTROID", "ERROR: Cropping->" + e.getMessage());
            }
        }

        private void m5569a(int[] iArr, int i) {
            PaintroidApplication.f4192b.m5780a(iArr, 0, this.f4355b, 0, i, this.f4355b, 1);
        }

        private void m5570b() {
            int[] iArr = new int[this.f4355b];
            this.f4354a.f4366I = 0;
            while (this.f4354a.f4366I < this.f4356c) {
                m5569a(iArr, this.f4354a.f4366I);
                this.f4354a.m5578a(new RectF((float) this.f4354a.f4364G, (float) this.f4354a.f4366I, (float) this.f4354a.f4365H, (float) this.f4354a.f4367J));
                for (int i = 0; i < this.f4355b; i++) {
                    if (iArr[i] != 0) {
                        this.f4354a.m5577a(i, this.f4354a.f4366I);
                        return;
                    }
                }
                CropTool cropTool = this.f4354a;
                cropTool.f4366I = cropTool.f4366I + 1;
            }
        }

        private void m5571b(int[] iArr, int i) {
            PaintroidApplication.f4192b.m5780a(iArr, 0, 1, i, 0, 1, this.f4356c);
        }

        private void m5572c() {
            int[] iArr = new int[this.f4356c];
            this.f4354a.f4364G = 0;
            while (this.f4354a.f4364G < this.f4355b) {
                m5571b(iArr, this.f4354a.f4364G);
                this.f4354a.m5578a(new RectF((float) this.f4354a.f4364G, (float) this.f4354a.f4366I, (float) this.f4354a.f4365H, (float) this.f4354a.f4367J));
                for (int b = this.f4354a.f4366I; b < this.f4356c; b++) {
                    if (iArr[b] != 0) {
                        this.f4354a.m5577a(this.f4354a.f4364G, b);
                        return;
                    }
                }
                CropTool cropTool = this.f4354a;
                cropTool.f4364G = cropTool.f4364G + 1;
            }
        }

        private void m5573d() {
            int[] iArr = new int[this.f4355b];
            this.f4354a.f4367J = this.f4356c - 1;
            while (this.f4354a.f4367J >= 0) {
                m5569a(iArr, this.f4354a.f4367J);
                this.f4354a.m5578a(new RectF((float) this.f4354a.f4364G, (float) this.f4354a.f4366I, (float) this.f4354a.f4365H, (float) this.f4354a.f4367J));
                for (int c = this.f4354a.f4364G; c < this.f4355b; c++) {
                    if (iArr[c] != 0) {
                        this.f4354a.m5577a(c, this.f4354a.f4367J);
                        return;
                    }
                }
                CropTool cropTool = this.f4354a;
                cropTool.f4367J = cropTool.f4367J - 1;
            }
        }

        private void m5574e() {
            int[] iArr = new int[this.f4356c];
            this.f4354a.f4365H = this.f4355b - 1;
            while (this.f4354a.f4365H >= 0) {
                m5571b(iArr, this.f4354a.f4365H);
                this.f4354a.m5578a(new RectF((float) this.f4354a.f4364G, (float) this.f4354a.f4366I, (float) this.f4354a.f4365H, (float) this.f4354a.f4367J));
                for (int b = this.f4354a.f4366I; b <= this.f4354a.f4367J; b++) {
                    if (iArr[b] != 0) {
                        this.f4354a.m5577a(this.f4354a.f4365H, b);
                        return;
                    }
                }
                CropTool cropTool = this.f4354a;
                cropTool.f4365H = cropTool.f4365H - 1;
            }
        }

        protected Void m5575a(Void... voidArr) {
            if (PaintroidApplication.f4192b.m5782c()) {
                m5568a();
            }
            return null;
        }

        protected void m5576a(Void voidR) {
            this.f4354a.f4368K = true;
            this.f4354a.m5600l();
        }

        protected /* synthetic */ Object doInBackground(Object... objArr) {
            return m5575a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m5576a((Void) obj);
        }
    }

    static {
        f4358L = null;
    }

    public CropTool(Context context, ToolType toolType) {
        super(context, toolType);
        this.f4361D = 0.0f;
        this.f4363F = 0.0f;
        this.f4368K = false;
        m5562b(false);
        m5559a(true);
        m5565c(false);
        f4358L = new CropTool(this);
        f4358L.execute(new Void[0]);
        this.o = (float) PaintroidApplication.f4192b.m5784e();
        this.n = (float) PaintroidApplication.f4192b.m5783d();
        this.A.x = this.n / 2.0f;
        this.A.y = this.o / 2.0f;
    }

    private void m5577a(int i, int i2) {
        this.f4360C = Math.min((float) i, this.f4360C);
        this.f4361D = Math.max((float) i, this.f4361D);
        this.f4362E = Math.min((float) i2, this.f4362E);
        this.f4363F = Math.max((float) i2, this.f4363F);
        m5578a(new RectF(this.f4360C, this.f4362E, this.f4361D, this.f4363F));
    }

    private void m5578a(RectF rectF) {
        this.n = (rectF.right - rectF.left) + 1.0f;
        this.o = (rectF.bottom - rectF.top) + 1.0f;
        this.A.x = rectF.left + (this.n / 2.0f);
        this.A.y = rectF.top + (this.o / 2.0f);
    }

    static /* synthetic */ int[] m5591n() {
        int[] iArr = f4359M;
        if (iArr == null) {
            iArr = new int[TopBar.values().length];
            try {
                iArr[TopBar.BUTTON_ID_PARAMETER_BOTTOM_1.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TopBar.BUTTON_ID_PARAMETER_BOTTOM_2.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[TopBar.BUTTON_ID_PARAMETER_TOP.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[TopBar.BUTTON_ID_TOOL.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            f4359M = iArr;
        }
        return iArr;
    }

    private void m5592o() {
        this.f4368K = false;
        this.f4361D = 0.0f;
        this.f4363F = 0.0f;
        this.f4360C = (float) PaintroidApplication.f4192b.m5783d();
        this.f4362E = (float) PaintroidApplication.f4192b.m5784e();
        this.f4364G = 0;
        this.f4365H = PaintroidApplication.f4192b.m5783d();
        this.f4366I = 0;
        this.f4367J = PaintroidApplication.f4192b.m5784e();
        PaintroidApplication.f4195e.m5794a();
        PaintroidApplication.f4195e.m5795a(PaintroidApplication.f4195e.m5804c() * 0.95f);
    }

    private boolean m5593p() {
        return (this.f4361D < this.f4360C || this.f4362E > this.f4363F) ? false : this.f4360C > 0.0f || this.f4361D < Float.valueOf((float) PaintroidApplication.f4192b.m5783d()).floatValue() || this.f4362E > 0.0f || this.f4363F < Float.valueOf((float) PaintroidApplication.f4192b.getHeight()).floatValue();
    }

    private void m5594q() {
        this.f4360C = Math.max(0.0f, this.A.x - (this.n / 2.0f));
        this.f4362E = Math.max(0.0f, this.A.y - (this.o / 2.0f));
        this.f4361D = Math.min((float) PaintroidApplication.f4192b.m5783d(), this.A.x + (this.n / 2.0f));
        this.f4363F = Math.min((float) PaintroidApplication.f4192b.m5784e(), this.A.y + (this.o / 2.0f));
    }

    public int m5595a(TopBar topBar) {
        switch (CropTool.m5591n()[topBar.ordinal()]) {
            case VideoSize.HVGA /*2*/:
                return b;
            case Version.API03_CUPCAKE_15 /*3*/:
                return R.icon_menu_crop_adjust;
            case Version.API04_DONUT_16 /*4*/:
                return R.icon_menu_crop_cut;
            default:
                return super.m5516a(topBar);
        }
    }

    public void m5596b(TopBar topBar) {
        switch (CropTool.m5591n()[topBar.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
                if (f4358L.getStatus() != Status.RUNNING) {
                    f4358L = new CropTool(this);
                    f4358L.execute(new Void[0]);
                }
            case Version.API04_DONUT_16 /*4*/:
                m5601m();
            default:
                super.m5525b(topBar);
        }
    }

    protected void m5597c(Canvas canvas) {
        if (this.f4368K) {
            this.B.setColor(this.y);
            this.B.setStrokeWidth(this.t * 2.0f);
            m5594q();
            PointF pointF = new PointF((-this.n) / 2.0f, (-this.o) / 2.0f);
            for (int i = 0; i < 4; i++) {
                float f = this.o / 10.0f;
                float f2 = this.n / 10.0f;
                canvas.drawLine(pointF.x - (this.t / 2.0f), pointF.y, pointF.x + f2, pointF.y, this.B);
                canvas.drawLine(pointF.x, pointF.y - (this.t / 2.0f), pointF.x, pointF.y + f, this.B);
                canvas.drawLine((pointF.x + (this.n / 2.0f)) - f2, pointF.y, (pointF.x + (this.n / 2.0f)) + f2, pointF.y, this.B);
                canvas.rotate(90.0f);
                float f3 = pointF.x;
                pointF.x = pointF.y;
                pointF.y = f3;
                f3 = this.o;
                this.o = this.n;
                this.n = f3;
            }
        }
    }

    public void m5598f() {
        if (f4358L.getStatus() != Status.RUNNING) {
            f4358L = new CropTool(this);
            f4358L.execute(new Void[0]);
        }
    }

    protected void m5599i() {
        m5601m();
    }

    protected void m5600l() {
        LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) this.f.getSystemService("layout_inflater")).inflate(R.image_toast_layout, (ViewGroup) ((Activity) this.f).findViewById(R.image_toast_layout_root));
        if (!m5593p()) {
            ((ImageView) linearLayout.findViewById(R.toast_image)).setVisibility(8);
            ((TextView) linearLayout.findViewById(R.toast_text)).setText(this.f.getText(R.crop_nothing_to_corp));
        }
        Toast toast = new Toast(this.f);
        toast.setDuration(0);
        toast.setView(linearLayout);
        toast.show();
    }

    protected void m5601m() {
        if (this.f4368K) {
            this.f4368K = false;
            m5594q();
            if (m5593p()) {
                IndeterminateProgressDialog.m5764a().show();
                Command cropCommand = new CropCommand((int) this.f4360C, (int) this.f4362E, (int) Math.floor((double) this.f4361D), (int) Math.floor((double) this.f4363F));
                ((CropCommand) cropCommand).addObserver(this);
                PaintroidApplication.f4193c.m5447a(cropCommand);
                return;
            }
            this.f4368K = true;
            m5600l();
        }
    }

    public void update(Observable observable, Object obj) {
        super.update(observable, obj);
        if (!(obj instanceof BaseCommand)) {
            return;
        }
        if (BaseCommand.COMMAND_DONE == obj || BaseCommand.COMMAND_FAILED == obj) {
            m5592o();
            this.f4361D = Float.valueOf((float) PaintroidApplication.f4192b.m5783d()).floatValue() - 1.0f;
            this.f4363F = Float.valueOf((float) PaintroidApplication.f4192b.m5784e()).floatValue() - 1.0f;
            this.f4360C = 0.0f;
            this.f4362E = 0.0f;
            m5578a(new RectF(this.f4360C, this.f4362E, this.f4361D, this.f4363F));
            this.f4368K = true;
        }
    }
}
