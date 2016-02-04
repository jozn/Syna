package org.catrobat.paintroid.dialog;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.ContextThemeWrapper;
import org.catrobat.paintroid.R.R;

/* renamed from: org.catrobat.paintroid.dialog.c */
public class CustomAlertDialogBuilder extends Builder {
    public CustomAlertDialogBuilder(Context context) {
        super(new ContextThemeWrapper(context, R.CustomPaintroidDialog));
    }
}
