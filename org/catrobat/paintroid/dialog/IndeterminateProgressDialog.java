package org.catrobat.paintroid.dialog;

import android.content.Context;
import org.catrobat.paintroid.PaintMainActivity;
import org.catrobat.paintroid.R.R;

/* renamed from: org.catrobat.paintroid.dialog.d */
public final class IndeterminateProgressDialog extends BaseDialog {
    private static IndeterminateProgressDialog f4479a;

    private IndeterminateProgressDialog(Context context) {
        super(context);
        requestWindowFeature(1);
        setContentView(R.custom_progress_dialog);
        setCancelable(false);
    }

    public static IndeterminateProgressDialog m5764a() {
        if (f4479a != null) {
            return f4479a;
        }
        throw new IllegalStateException("IndeterminateProgressDialog has not been initialized. Call init() first!");
    }

    public static void m5765a(PaintMainActivity paintMainActivity) {
        f4479a = new IndeterminateProgressDialog(paintMainActivity);
    }
}
