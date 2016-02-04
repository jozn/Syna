package org.catrobat.paintroid.dialog;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import org.catrobat.paintroid.R.R;

@SuppressLint({"ValidFragment"})
/* renamed from: org.catrobat.paintroid.dialog.e */
public class InfoDialog extends DialogFragment implements OnClickListener {
    private InfoDialog f4485Y;
    private int f4486Z;
    private int aa;

    /* renamed from: org.catrobat.paintroid.dialog.e.a */
    public enum InfoDialog {
        INFO(17301659, R.help_title),
        WARNING(17301543, 17039380);
        
        private int f4483c;
        private int f4484d;

        private InfoDialog(int i, int i2) {
            this.f4483c = i;
            this.f4484d = i2;
        }

        public int m5766a() {
            return this.f4483c;
        }
    }

    public InfoDialog(InfoDialog infoDialog, int i, int i2) {
        this.f4485Y = infoDialog;
        this.f4486Z = i;
        this.aa = i2;
    }

    @TargetApi(11)
    public Dialog m5767c(Bundle bundle) {
        Builder customAlertDialogBuilder = new CustomAlertDialogBuilder(m91h());
        if (this.aa != 0) {
            customAlertDialogBuilder.setTitle(this.aa);
        }
        int a = this.f4485Y.m5766a();
        if (a != 0) {
            customAlertDialogBuilder.setIcon(a);
        }
        if (this.f4486Z != 0) {
            customAlertDialogBuilder.setMessage(this.f4486Z);
        }
        customAlertDialogBuilder.setNeutralButton(17039370, this);
        return customAlertDialogBuilder.create();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
