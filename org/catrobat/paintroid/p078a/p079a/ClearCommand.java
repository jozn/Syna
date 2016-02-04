package org.catrobat.paintroid.p078a.p079a;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/* renamed from: org.catrobat.paintroid.a.a.c */
public class ClearCommand extends BaseCommand {
    protected int f4211d;

    public ClearCommand() {
        this.f4211d = 0;
    }

    public void m5444a(Canvas canvas, Bitmap bitmap) {
        if (bitmap != null) {
            bitmap.eraseColor(this.f4211d);
        }
    }
}
