package org.catrobat.paintroid.ui;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import org.catrobat.paintroid.PaintMainActivity;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.dialog.ToolsDialog;
import org.catrobat.paintroid.p081c.Tool;
import org.catrobat.paintroid.ui.TopBar.TopBar;

/* renamed from: org.catrobat.paintroid.ui.a */
public class BottomBar implements OnTouchListener {
    private ImageButton f4515a;
    private ImageButton f4516b;
    private ImageButton f4517c;
    private PaintMainActivity f4518d;

    public BottomBar(PaintMainActivity paintMainActivity) {
        this.f4518d = paintMainActivity;
        this.f4515a = (ImageButton) paintMainActivity.findViewById(R.btn_bottom_attribute1);
        this.f4515a.setOnTouchListener(this);
        this.f4516b = (ImageButton) paintMainActivity.findViewById(R.btn_bottom_attribute2);
        this.f4516b.setOnTouchListener(this);
        this.f4517c = (ImageButton) paintMainActivity.findViewById(R.btn_bottom_tools);
        this.f4517c.setOnTouchListener(this);
    }

    public void m5785a(Tool tool) {
        this.f4515a.setEnabled(true);
        this.f4515a.setImageResource(tool.m5501a(TopBar.BUTTON_ID_PARAMETER_BOTTOM_1));
        this.f4516b.setEnabled(true);
        this.f4516b.setImageResource(tool.m5501a(TopBar.BUTTON_ID_PARAMETER_BOTTOM_2));
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            view.setBackgroundResource(R.transparent);
            if (view.getId() == R.btn_bottom_attribute1) {
                if (PaintroidApplication.f4194d == null) {
                    return true;
                }
                PaintroidApplication.f4194d.m5511b(TopBar.BUTTON_ID_PARAMETER_BOTTOM_1);
                return true;
            } else if (view.getId() == R.btn_bottom_attribute2) {
                if (PaintroidApplication.f4194d == null) {
                    return true;
                }
                PaintroidApplication.f4194d.m5511b(TopBar.BUTTON_ID_PARAMETER_BOTTOM_2);
                return true;
            } else if (view.getId() != R.btn_bottom_tools) {
                return false;
            } else {
                ToolsDialog.m5768a().show();
                return true;
            }
        }
        if (motionEvent.getAction() == 0) {
            view.setBackgroundResource(R.holo_blue_bright);
        }
        return false;
    }
}
