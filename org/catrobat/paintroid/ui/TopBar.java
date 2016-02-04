package org.catrobat.paintroid.ui;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Observable;
import org.catrobat.paintroid.PaintMainActivity;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.dialog.colorpicker.ColorPickerDialog;
import org.catrobat.paintroid.p078a.UndoRedoManager;
import org.catrobat.paintroid.p081c.Tool;
import org.catrobat.paintroid.p081c.ToolFactory;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.p081c.p084b.DrawTool;

/* renamed from: org.catrobat.paintroid.ui.d */
public class TopBar extends Observable implements OnTouchListener {
    protected DrawingSurface f4549a;
    protected Tool f4550b;
    protected PaintMainActivity f4551c;
    private ImageButton f4552d;
    private ImageButton f4553e;
    private ImageButton f4554f;
    private ImageButton f4555g;
    private Tool f4556h;
    private Toast f4557i;
    private boolean f4558j;
    private boolean f4559k;

    /* renamed from: org.catrobat.paintroid.ui.d.a */
    public enum TopBar {
        BUTTON_ID_TOOL,
        BUTTON_ID_PARAMETER_TOP,
        BUTTON_ID_PARAMETER_BOTTOM_1,
        BUTTON_ID_PARAMETER_BOTTOM_2
    }

    public TopBar(PaintMainActivity paintMainActivity, boolean z) {
        this.f4551c = paintMainActivity;
        this.f4550b = new DrawTool(paintMainActivity, ToolType.BRUSH);
        PaintroidApplication.f4194d = this.f4550b;
        this.f4552d = (ImageButton) paintMainActivity.findViewById(R.btn_top_undo);
        this.f4552d.setOnTouchListener(this);
        this.f4553e = (ImageButton) paintMainActivity.findViewById(R.btn_top_redo);
        this.f4553e.setOnTouchListener(this);
        this.f4554f = (ImageButton) paintMainActivity.findViewById(R.btn_top_color);
        this.f4554f.setOnTouchListener(this);
        this.f4555g = (ImageButton) paintMainActivity.findViewById(R.btn_top_toolswitch);
        this.f4555g.setOnTouchListener(this);
        m5811c(R.icon_menu_move);
        this.f4549a = (DrawingSurface) paintMainActivity.findViewById(R.drawingSurfaceView);
        UndoRedoManager.m5474a().m5477a(this);
    }

    private void m5808a(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            if (!this.f4558j) {
                this.f4552d.setBackgroundResource(R.holo_blue_bright);
            }
            PaintroidApplication.f4193c.m5451e();
        } else if (motionEvent.getAction() == 1) {
            this.f4552d.setBackgroundResource(0);
        }
    }

    private void m5810b(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            if (!this.f4559k) {
                this.f4553e.setBackgroundResource(R.holo_blue_bright);
            }
            PaintroidApplication.f4193c.m5452f();
        } else if (motionEvent.getAction() == 1) {
            this.f4553e.setBackgroundResource(0);
        }
    }

    private void m5811c(int i) {
        Drawable bitmapDrawable = new BitmapDrawable(this.f4551c.getResources(), BitmapFactory.decodeResource(this.f4551c.getResources(), i));
        bitmapDrawable.setAlpha(50);
        this.f4555g.setBackgroundDrawable(bitmapDrawable);
    }

    private void m5812c(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return;
        }
        if (this.f4556h != null) {
            this.f4551c.m5433a(this.f4556h);
        } else {
            this.f4551c.m5434a(ToolType.MOVE);
        }
    }

    private void m5813d(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && this.f4550b.m5510b().m5719c()) {
            ColorPickerDialog.m5753a().show();
            ColorPickerDialog.m5753a().m5759a(this.f4550b.m5502a().getColor());
        }
    }

    private void m5814e() {
        if (this.f4557i != null) {
            this.f4557i.cancel();
        }
        this.f4557i = Toast.makeText(this.f4551c, this.f4551c.getString(this.f4550b.m5510b().m5716a()), 0);
        this.f4557i.setGravity(53, 0, 75);
        this.f4557i.show();
    }

    public void m5815a() {
        this.f4558j = false;
    }

    public void m5816a(int i) {
        this.f4551c.runOnUiThread(new TopBar(this, i));
    }

    public void m5817a(Tool tool) {
        if (tool.m5510b() != this.f4550b.m5510b() || tool.m5510b() == ToolType.STAMP) {
            if ((tool.m5510b() == ToolType.MOVE || tool.m5510b() == ToolType.ZOOM) && this.f4550b.m5510b() != ToolType.MOVE && this.f4550b.m5510b() != ToolType.ZOOM) {
                this.f4556h = this.f4550b;
                m5811c(this.f4556h.m5501a(TopBar.BUTTON_ID_TOOL));
            } else if (!((tool.m5510b() == ToolType.MOVE && this.f4550b.m5510b() == ToolType.ZOOM) || (tool.m5510b() == ToolType.ZOOM && this.f4550b.m5510b() == ToolType.MOVE))) {
                this.f4556h = null;
                m5811c(R.icon_menu_move);
            }
            if (this.f4556h == null && (tool.m5510b() == ToolType.MOVE || tool.m5510b() == ToolType.ZOOM)) {
                this.f4550b = ToolFactory.m5714a(this.f4551c, ToolType.BRUSH);
            } else {
                this.f4550b = tool;
            }
            this.f4555g.setAnimation(AnimationUtils.loadAnimation(this.f4551c, R.fade_in));
            this.f4555g.setImageResource(this.f4550b.m5501a(TopBar.BUTTON_ID_TOOL));
            m5814e();
            super.setChanged();
            super.notifyObservers();
        }
    }

    public void m5818b() {
        this.f4558j = true;
    }

    public void m5819b(int i) {
        this.f4551c.runOnUiThread(new TopBar(this, i));
    }

    public void m5820c() {
        this.f4559k = false;
    }

    public void m5821d() {
        this.f4559k = true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() == R.btn_top_undo) {
            m5808a(motionEvent);
            return true;
        } else if (view.getId() == R.btn_top_redo) {
            m5810b(motionEvent);
            return true;
        } else if (view.getId() == R.btn_top_toolswitch) {
            m5812c(motionEvent);
            return true;
        } else if (view.getId() != R.btn_top_color) {
            return false;
        } else {
            m5813d(motionEvent);
            return true;
        }
    }
}
