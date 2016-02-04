package org.catrobat.paintroid.p081c.p084b;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.util.Log;
import android.widget.ImageButton;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.dialog.IndeterminateProgressDialog;
import org.catrobat.paintroid.p078a.Command;
import org.catrobat.paintroid.p078a.p079a.StampCommand;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.TopBar.TopBar;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b.s */
public class StampTool extends BaseToolWithRectangleShape {
    protected static StampTool f4394C;
    private static /* synthetic */ int[] f4395G;
    protected boolean f4396D;
    protected ImageButton f4397E;
    protected ImageButton f4398F;

    /* renamed from: org.catrobat.paintroid.c.b.s.a */
    protected class StampTool extends AsyncTask<Void, Integer, Void> {
        final /* synthetic */ StampTool f4406a;

        protected StampTool(StampTool stampTool) {
            this.f4406a = stampTool;
        }

        protected Void m5712a(Void... voidArr) {
            Log.e("PAINTROID", "------------doInBackground");
            if (PaintroidApplication.f4192b.m5782c()) {
                this.f4406a.m5674m();
            }
            return null;
        }

        protected void m5713a(Void voidR) {
            IndeterminateProgressDialog.m5764a().dismiss();
        }

        protected /* synthetic */ Object doInBackground(Object... objArr) {
            return m5712a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m5713a((Void) obj);
        }

        protected void onPreExecute() {
            IndeterminateProgressDialog.m5764a().show();
            super.onPreExecute();
        }
    }

    static {
        f4394C = null;
    }

    public StampTool(Activity activity, ToolType toolType) {
        super(activity, toolType);
        this.f4396D = false;
        this.f4397E = (ImageButton) activity.findViewById(R.btn_bottom_attribute1);
        this.f4398F = (ImageButton) activity.findViewById(R.btn_bottom_attribute2);
        this.f4396D = false;
        this.f4398F.setEnabled(false);
        m5562b(true);
        m5559a(false);
        this.x = Bitmap.createBitmap((int) this.n, (int) this.o, Config.ARGB_8888);
        f4394C = new StampTool(this);
    }

    private void m5663l() {
        float f = this.p;
        while (((double) f) < 0.0d) {
            f += 90.0f;
        }
        while (f > 90.0f) {
            f -= 90.0f;
        }
        double toRadians = Math.toRadians((double) f);
        double sin = (((double) this.n) * Math.sin(toRadians)) + (((double) this.o) * Math.cos(toRadians));
        toRadians = (Math.sin(toRadians) * ((double) this.o)) + (((double) this.n) * Math.cos(toRadians));
        if (sin < 0.0d) {
            sin = -sin;
        }
        if (toRadians < 0.0d) {
            toRadians = -toRadians;
        }
        toRadians = Math.sqrt(Math.pow((toRadians / 2.0d) + ((double) this.A.y), 2.0d) + Math.pow((sin / 2.0d) + ((double) this.A.x), 2.0d));
        Bitmap createBitmap = Bitmap.createBitmap(((int) toRadians) * 2, ((int) toRadians) * 2, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Rect rect = new Rect(((int) this.A.x) - ((int) toRadians), ((int) this.A.y) - ((int) toRadians), ((int) this.A.x) + ((int) toRadians), ((int) this.A.y) + ((int) toRadians));
        Rect rect2 = new Rect(0, 0, ((int) toRadians) * 2, ((int) toRadians) * 2);
        canvas.save();
        canvas.rotate(-this.p, (float) toRadians, (float) toRadians);
        Bitmap b = PaintroidApplication.f4192b.m5781b();
        if (b != null && !b.isRecycled()) {
            canvas.drawBitmap(b, rect, rect2, null);
            b.recycle();
            canvas.restore();
            if (m5667q()) {
                this.x = Bitmap.createBitmap((int) this.n, (int) this.o, Config.ARGB_8888);
            }
            double d = toRadians - ((double) (this.n / 2.0f));
            double d2 = toRadians - ((double) (this.o / 2.0f));
            new Canvas(this.x).drawBitmap(createBitmap, new Rect((int) d, (int) d2, (int) ((toRadians * 2.0d) - d), (int) ((toRadians * 2.0d) - d2)), new Rect(0, 0, (int) this.n, (int) this.o), null);
            createBitmap.recycle();
            this.f4396D = true;
            System.gc();
        }
    }

    static /* synthetic */ int[] m5664n() {
        int[] iArr = f4395G;
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
            f4395G = iArr;
        }
        return iArr;
    }

    private void m5665o() {
        if (f4394C.getStatus() != Status.RUNNING) {
            f4394C = new StampTool(this);
            f4394C.execute(new Void[0]);
        }
        this.f4397E.setImageResource(R.icon_menu_stamp_paste);
        if (!this.f4398F.isEnabled()) {
            this.f4398F.setEnabled(true);
        }
        this.f4398F.setImageResource(R.icon_menu_stamp_clear);
    }

    private void m5666p() {
        Command stampCommand = new StampCommand(this.x, new Point((int) this.A.x, (int) this.A.y), this.n, this.o, this.p);
        ((StampCommand) stampCommand).addObserver(this);
        IndeterminateProgressDialog.m5764a().show();
        PaintroidApplication.f4193c.m5447a(stampCommand);
    }

    private boolean m5667q() {
        if (this.x == null || this.x.isRecycled() || this.x.getWidth() != ((int) this.n) || this.x.getHeight() != ((int) this.o)) {
            return true;
        }
        this.x.eraseColor(0);
        return false;
    }

    public int m5668a(TopBar topBar) {
        switch (StampTool.m5664n()[topBar.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
                return this.f4396D ? R.icon_menu_stamp_paste : R.icon_menu_stamp_copy;
            case Version.API04_DONUT_16 /*4*/:
                if (this.f4396D) {
                    return R.icon_menu_stamp_clear;
                }
                this.f4398F.setEnabled(false);
                return R.icon_menu_stamp_clear_disabled;
            default:
                return super.m5516a(topBar);
        }
    }

    public void m5669b(Bitmap bitmap) {
        super.m5557a(bitmap);
        this.f4396D = true;
    }

    public void m5670b(TopBar topBar) {
        switch (StampTool.m5664n()[topBar.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
                if (this.f4396D) {
                    m5666p();
                } else {
                    m5665o();
                }
            case Version.API04_DONUT_16 /*4*/:
                if (this.f4396D) {
                    this.f4397E.setImageResource(R.icon_menu_stamp_copy);
                    this.f4398F.setImageResource(R.icon_menu_stamp_clear_disabled);
                    this.f4398F.setEnabled(false);
                    this.x = Bitmap.createBitmap((int) this.n, (int) this.o, Config.ARGB_8888);
                    f4394C = new StampTool(this);
                    this.f4396D = false;
                }
            default:
        }
    }

    protected void m5671c(Canvas canvas) {
    }

    public void m5672f() {
    }

    protected void m5673i() {
        if (!this.f4396D) {
            m5665o();
        } else if (this.x != null && !this.x.isRecycled()) {
            m5666p();
        }
    }

    protected void m5674m() {
        if (((double) this.p) != 0.0d) {
            m5663l();
            return;
        }
        if (m5667q()) {
            this.x = Bitmap.createBitmap((int) this.n, (int) this.o, Config.ARGB_8888);
        }
        Log.d("PAINTROID", "clip bitmap");
        Point point = new Point(((int) this.A.x) - (((int) this.n) / 2), ((int) this.A.y) - (((int) this.o) / 2));
        Point point2 = new Point(((int) this.A.x) + (((int) this.n) / 2), ((int) this.A.y) + (((int) this.o) / 2));
        try {
            Canvas canvas = new Canvas(this.x);
            Rect rect = new Rect(point.x, point.y, point.x + ((int) this.n), point.y + ((int) this.o));
            Rect rect2 = new Rect(0, 0, point2.x - point.x, point2.y - point.y);
            Bitmap b = PaintroidApplication.f4192b.m5781b();
            if (b != null && !b.isRecycled()) {
                canvas.drawBitmap(b, rect, rect2, null);
                b.recycle();
                this.f4396D = true;
                Log.d("PAINTROID", "created bitmap");
            }
        } catch (Exception e) {
            Log.e("PAINTROID", "error stamping bitmap " + e.getMessage());
            if (this.x != null) {
                this.x.recycle();
                this.x = null;
                System.gc();
            }
        }
    }
}
