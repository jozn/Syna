package org.catrobat.paintroid;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import java.io.File;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.dialog.BrushPickerDialog;
import org.catrobat.paintroid.dialog.CustomAlertDialogBuilder;
import org.catrobat.paintroid.dialog.IndeterminateProgressDialog;
import org.catrobat.paintroid.dialog.InfoDialog;
import org.catrobat.paintroid.dialog.ToolsDialog;
import org.catrobat.paintroid.dialog.colorpicker.ColorPickerDialog;
import org.catrobat.paintroid.p080b.DrawingSurfaceListener;
import org.catrobat.paintroid.p081c.Tool;
import org.catrobat.paintroid.p081c.ToolFactory;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.BottomBar;
import org.catrobat.paintroid.ui.DrawingSurface;
import org.catrobat.paintroid.ui.Perspective;
import org.catrobat.paintroid.ui.TopBar;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

public class PaintMainActivity extends OptionsMenuActivity {
    private static /* synthetic */ int[] f4185t;
    protected DrawingSurfaceListener f4186o;
    protected TopBar f4187p;
    protected BottomBar f4188q;
    protected boolean f4189r;
    private Menu f4190s;

    public PaintMainActivity() {
        this.f4189r = true;
        this.f4190s = null;
    }

    private void m5426b(boolean z) {
        PaintroidApplication.f4195e.m5800a(z);
        if (z) {
            getActionBar().hide();
            ((LinearLayout) findViewById(R.main_bottom_bar)).setVisibility(8);
            this.f4189r = false;
            getWindow().addFlags(1024);
            getWindow().clearFlags(2048);
            return;
        }
        getActionBar().show();
        ((LinearLayout) findViewById(R.main_bottom_bar)).setVisibility(0);
        this.f4189r = true;
        getWindow().addFlags(2048);
        getWindow().clearFlags(1024);
    }

    static /* synthetic */ int[] m5427j() {
        int[] iArr = f4185t;
        if (iArr == null) {
            iArr = new int[ToolType.values().length];
            try {
                iArr[ToolType.BRUSH.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ToolType.CROP.ordinal()] = 13;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ToolType.CURSOR.ordinal()] = 11;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ToolType.ELLIPSE.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ToolType.ERASER.ordinal()] = 14;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ToolType.FILL.ordinal()] = 8;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ToolType.FLIP.ordinal()] = 15;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[ToolType.IMPORTPNG.ordinal()] = 12;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[ToolType.LINE.ordinal()] = 10;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[ToolType.MOVE.ordinal()] = 17;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[ToolType.NONE.ordinal()] = 7;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[ToolType.PIPETTE.ordinal()] = 3;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[ToolType.RECT.ordinal()] = 16;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[ToolType.REDO.ordinal()] = 6;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[ToolType.ROTATE.ordinal()] = 18;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[ToolType.STAMP.ordinal()] = 9;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[ToolType.UNDO.ordinal()] = 5;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[ToolType.ZOOM.ordinal()] = 2;
            } catch (NoSuchFieldError e18) {
            }
            f4185t = iArr;
        }
        return iArr;
    }

    private void m5428k() {
        getActionBar().setCustomView(R.top_bar);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowCustomEnabled(true);
        if (VERSION.SDK_INT < 14) {
            Bitmap createBitmap = Bitmap.createBitmap(1, 1, Config.ARGB_8888);
            createBitmap.eraseColor(getResources().getColor(R.custom_background_color));
            Drawable bitmapDrawable = new BitmapDrawable(getResources(), createBitmap);
            getActionBar().setBackgroundDrawable(bitmapDrawable);
            getActionBar().setSplitBackgroundDrawable(bitmapDrawable);
        }
    }

    private void m5429l() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.setFlags(524288);
        startActivityForResult(intent, 1);
    }

    private void m5430m() {
        if (PaintroidApplication.f4200j || (!PaintroidApplication.f4193c.m5446a() && PaintroidApplication.f4198h)) {
            finish();
            return;
        }
        Builder customAlertDialogBuilder = new CustomAlertDialogBuilder(this);
        if (PaintroidApplication.f4196f) {
            customAlertDialogBuilder.setTitle(R.closing_catroid_security_question_title);
            customAlertDialogBuilder.setMessage(R.closing_security_question);
            customAlertDialogBuilder.setPositiveButton(R.save_button_text, new PaintMainActivity(this));
            customAlertDialogBuilder.setNegativeButton(R.discard_button_text, new PaintMainActivity(this));
        } else {
            customAlertDialogBuilder.setTitle(R.closing_security_question_title);
            customAlertDialogBuilder.setMessage(R.closing_security_question);
            customAlertDialogBuilder.setPositiveButton(R.save_button_text, new PaintMainActivity(this));
            customAlertDialogBuilder.setNegativeButton(R.discard_button_text, new PaintMainActivity(this));
        }
        customAlertDialogBuilder.setCancelable(true);
        customAlertDialogBuilder.create().show();
    }

    private void m5431n() {
        m5422g();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m5432o() {
        /*
        r4 = this;
        r0 = org.catrobat.paintroid.R.R.temp_picture_name;
        r1 = r4.getString(r0);
        r0 = org.catrobat.paintroid.PaintroidApplication.f4197g;
        if (r0 == 0) goto L_0x0036;
    L_0x000a:
        r0 = org.catrobat.paintroid.PaintroidApplication.f4197g;
    L_0x000c:
        r1 = new android.content.Intent;
        r1.<init>();
        r2 = org.catrobat.paintroid.PaintroidApplication.f4192b;
        r2 = r2.m5781b();
        r2 = org.catrobat.paintroid.FileIO.m5484a(r4, r2, r0);
        if (r2 == 0) goto L_0x005b;
    L_0x001d:
        r2 = new android.os.Bundle;
        r2.<init>();
        r3 = org.catrobat.paintroid.R.R.extra_picture_path_catroid;
        r3 = r4.getString(r3);
        r2.putString(r3, r0);
        r1.putExtras(r2);
        r0 = -1;
        r4.setResult(r0, r1);
    L_0x0032:
        r4.finish();
        return;
    L_0x0036:
        r0 = r4.getIntent();
        r0 = r0.getExtras();
        if (r0 == 0) goto L_0x0060;
    L_0x0040:
        r2 = org.catrobat.paintroid.R.R.extra_picture_name_catroid;
        r2 = r4.getString(r2);
        r0 = r0.getString(r2);
        if (r0 == 0) goto L_0x0060;
    L_0x004c:
        r2 = r0.length();
        if (r2 <= 0) goto L_0x0060;
    L_0x0052:
        r0 = org.catrobat.paintroid.FileIO.m5482a(r4, r0);
        r0 = r0.getAbsolutePath();
        goto L_0x000c;
    L_0x005b:
        r0 = 0;
        r4.setResult(r0, r1);
        goto L_0x0032;
    L_0x0060:
        r0 = r1;
        goto L_0x0052;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.catrobat.paintroid.PaintMainActivity.o():void");
    }

    public synchronized void m5433a(Tool tool) {
        Paint paint = new Paint(PaintroidApplication.f4194d.m5502a());
        if (tool != null) {
            this.f4187p.m5817a(tool);
            this.f4188q.m5785a(tool);
            PaintroidApplication.f4194d = tool;
            PaintroidApplication.f4194d.m5507a(paint);
        }
    }

    public synchronized void m5434a(ToolType toolType) {
        switch (m5427j()[toolType.ordinal()]) {
            case Version.API05_ECLAIR_20 /*5*/:
                PaintroidApplication.f4193c.m5451e();
                break;
            case Version.API06_ECLAIR_201 /*6*/:
                PaintroidApplication.f4193c.m5452f();
                break;
            case Version.API12_HONEYCOMB_MR1_31X /*12*/:
                m5429l();
                break;
            default:
                m5433a(ToolFactory.m5714a(this, toolType));
                break;
        }
    }

    protected void m5435b(String str) {
        Intent intent = new Intent();
        intent.putExtra("PaintMainActivity.DOODLE_PATH", str);
        setResult(-1, intent);
        finish();
    }

    public void m5436i() {
        if (this.n) {
            this.n = false;
            new InfoDialog(InfoDialog.InfoDialog.WARNING, R.dialog_loading_image_failed_title, R.dialog_loading_image_failed_text).m229a(m121e(), "loadbitmapdialogerror");
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1) {
            Log.d("PAINTROID", "onActivityResult: result not ok, most likely a dialog hast been canceled");
            return;
        }
        switch (i) {
            case VideoSize.CIF /*1*/:
                Uri data = intent.getData();
                m5433a(ToolFactory.m5714a(this, ToolType.IMPORTPNG));
                m5419a(data, new PaintMainActivity(this, this));
            case Version.API03_CUPCAKE_15 /*3*/:
                finish();
            default:
                super.onActivityResult(i, i2, intent);
        }
    }

    public void onBackPressed() {
        if (!this.f4189r) {
            m5426b(false);
        } else if (PaintroidApplication.f4194d.m5510b() == ToolType.BRUSH) {
            m5430m();
        } else {
            m5434a(ToolType.BRUSH);
        }
    }

    public void onCreate(Bundle bundle) {
        String string;
        PaintroidApplication.m5437a(this);
        ColorPickerDialog.m5754a((Context) this);
        BrushPickerDialog.m5722a((Context) this);
        ToolsDialog.m5769a(this);
        IndeterminateProgressDialog.m5765a(this);
        super.onCreate(bundle);
        setContentView(R.main);
        m5428k();
        PaintroidApplication.f4197g = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            string = extras.getString(getString(R.extra_picture_path_catroid));
            if (extras.containsKey("EXTRA_MAIN_BOTTOM_BAR_COLOR")) {
                ((LinearLayout) findViewById(R.main_bottom_bar)).setBackgroundColor(extras.getInt("EXTRA_MAIN_BOTTOM_BAR_COLOR"));
            }
            Log.d("PAINTROID", "catroidPicturePath: " + string);
        } else {
            string = null;
        }
        if (string != null) {
            PaintroidApplication.f4196f = true;
            if (!string.equals("")) {
                PaintroidApplication.f4197g = string;
            }
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setDisplayShowHomeEnabled(true);
        } else {
            PaintroidApplication.f4196f = false;
        }
        PaintroidApplication.f4192b = (DrawingSurface) findViewById(R.drawingSurfaceView);
        PaintroidApplication.f4195e = new Perspective(this, PaintroidApplication.f4192b.getHolder());
        this.f4186o = new DrawingSurfaceListener();
        this.f4187p = new TopBar(this, PaintroidApplication.f4196f);
        this.f4188q = new BottomBar(this);
        PaintroidApplication.f4192b.setOnTouchListener(this.f4186o);
        if (!PaintroidApplication.f4196f || string == null || string.length() <= 0) {
            m5423h();
        } else {
            m5419a(Uri.fromFile(new File(string)), new PaintMainActivity(this, this));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        this.f4190s = menu;
        PaintroidApplication.f4199i = this.f4190s;
        MenuInflater menuInflater = getMenuInflater();
        if (PaintroidApplication.f4196f) {
            menuInflater.inflate(R.main_menu_opened_from_catroid, menu);
        } else {
            menuInflater.inflate(R.main_menu, menu);
        }
        return true;
    }

    protected void onDestroy() {
        PaintroidApplication.f4193c.m5449c();
        PaintroidApplication.f4192b.m5778a();
        ColorPickerDialog.m5753a().m5759a(getResources().getColor(R.color_chooser_black));
        PaintroidApplication.f4194d.m5506a(Cap.ROUND);
        PaintroidApplication.f4194d.m5504a(25);
        PaintroidApplication.f4198h = true;
        PaintroidApplication.f4201k = null;
        PaintroidApplication.f4202l = false;
        ToolsDialog.m5768a().dismiss();
        IndeterminateProgressDialog.m5764a().dismiss();
        ColorPickerDialog.m5753a().dismiss();
        super.onDestroy();
    }

    public void onDetachedFromWindow() {
        IndeterminateProgressDialog.m5764a().dismiss();
        super.onDetachedFromWindow();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.menu_item_back_to_catroid) {
            m5430m();
            return true;
        } else if (menuItem.getItemId() == R.menu_item_hide_menu) {
            m5426b(this.f4189r);
            return true;
        } else if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            if (!PaintroidApplication.f4196f) {
                return true;
            }
            m5430m();
            return true;
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.f4189r) {
            return super.onPrepareOptionsMenu(menu);
        }
        m5426b(false);
        return true;
    }

    protected void onResume() {
        super.onResume();
        m5436i();
    }
}
