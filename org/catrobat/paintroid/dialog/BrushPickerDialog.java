package org.catrobat.paintroid.dialog;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import org.catrobat.paintroid.R.R;

@SuppressLint({"ValidFragment"})
/* renamed from: org.catrobat.paintroid.dialog.b */
public final class BrushPickerDialog extends DialogFragment implements OnClickListener, View.OnClickListener {
    private static BrushPickerDialog f4435Y;
    private ArrayList<BrushPickerDialog> f4436Z;
    private Paint aa;
    private Context ab;
    private TextView ac;
    private SeekBar ad;
    private RadioButton ae;
    private RadioButton af;

    /* renamed from: org.catrobat.paintroid.dialog.b.a */
    public interface BrushPickerDialog {
        void m5532a(int i);

        void m5533a(Cap cap);
    }

    /* renamed from: org.catrobat.paintroid.dialog.b.b */
    public class BrushPickerDialog implements OnSeekBarChangeListener {
        final /* synthetic */ BrushPickerDialog f4434a;

        public BrushPickerDialog(BrushPickerDialog brushPickerDialog) {
            this.f4434a = brushPickerDialog;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (i < 1) {
                seekBar.setProgress(1);
                i = 1;
            }
            this.f4434a.m5725b(i);
            this.f4434a.ac.setText(i);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    @SuppressLint({"ValidFragment"})
    private BrushPickerDialog(Context context) {
        this.f4436Z = new ArrayList();
        this.ab = context;
    }

    public static BrushPickerDialog m5720D() {
        if (f4435Y != null) {
            return f4435Y;
        }
        throw new IllegalStateException("BrushPickerDialog has not been initialized. Call init() first!");
    }

    public static void m5722a(Context context) {
        f4435Y = new BrushPickerDialog(context);
    }

    private void m5723a(Cap cap) {
        Iterator it = this.f4436Z.iterator();
        while (it.hasNext()) {
            BrushPickerDialog brushPickerDialog = (BrushPickerDialog) it.next();
            if (brushPickerDialog == null) {
                this.f4436Z.remove(brushPickerDialog);
            }
            brushPickerDialog.m5533a(cap);
        }
    }

    private void m5725b(int i) {
        Iterator it = this.f4436Z.iterator();
        while (it.hasNext()) {
            BrushPickerDialog brushPickerDialog = (BrushPickerDialog) it.next();
            if (brushPickerDialog == null) {
                this.f4436Z.remove(brushPickerDialog);
            }
            brushPickerDialog.m5532a(i);
        }
    }

    public void m5726a(Paint paint) {
        this.aa = paint;
        m5723a(paint.getStrokeCap());
        m5725b((int) paint.getStrokeWidth());
    }

    public void m5727a(BrushPickerDialog brushPickerDialog) {
        this.f4436Z.add(brushPickerDialog);
    }

    @TargetApi(11)
    public Dialog m5728c(Bundle bundle) {
        LayoutInflater layoutInflater = m91h().getLayoutInflater();
        Builder customAlertDialogBuilder = new CustomAlertDialogBuilder(this.ab);
        customAlertDialogBuilder.setTitle(R.stroke_title);
        View inflate = layoutInflater.inflate(R.dialog_stroke, null);
        ((ImageButton) inflate.findViewById(R.stroke_ibtn_circle)).setOnClickListener(this);
        ((ImageButton) inflate.findViewById(R.stroke_ibtn_rect)).setOnClickListener(this);
        this.ae = (RadioButton) inflate.findViewById(R.stroke_rbtn_circle);
        this.ae.setOnClickListener(this);
        this.af = (RadioButton) inflate.findViewById(R.stroke_rbtn_rect);
        this.af.setOnClickListener(this);
        this.ad = (SeekBar) inflate.findViewById(R.stroke_width_seek_bar);
        this.ad.setOnSeekBarChangeListener(new BrushPickerDialog(this));
        this.ac = (TextView) inflate.findViewById(R.stroke_width_width_text);
        customAlertDialogBuilder.setView(inflate);
        customAlertDialogBuilder.setNeutralButton(R.done, this);
        return customAlertDialogBuilder.create();
    }

    public void m5729d() {
        super.m235d();
        if (this.aa.getStrokeCap() == Cap.ROUND) {
            this.ae.setChecked(true);
        } else {
            this.af.setChecked(true);
        }
        this.ad.setProgress((int) this.aa.getStrokeWidth());
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case -3:
                m226a();
            default:
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.stroke_ibtn_circle) {
            m5723a(Cap.ROUND);
            this.ae.setChecked(true);
        } else if (view.getId() == R.stroke_ibtn_rect) {
            m5723a(Cap.SQUARE);
            this.af.setChecked(true);
        } else if (view.getId() == R.stroke_rbtn_circle) {
            m5723a(Cap.ROUND);
        } else if (view.getId() == R.stroke_rbtn_rect) {
            m5723a(Cap.SQUARE);
        }
    }
}
