package org.catrobat.paintroid.p078a.p079a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Observable;
import java.util.Random;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.p078a.Command;

/* renamed from: org.catrobat.paintroid.a.a.a */
public abstract class BaseCommand extends Observable implements Command {
    protected Paint f4207a;
    protected Bitmap f4208b;
    protected File f4209c;

    /* renamed from: org.catrobat.paintroid.a.a.a.a */
    public enum BaseCommand {
        COMMAND_STARTED,
        COMMAND_DONE,
        COMMAND_FAILED
    }

    public BaseCommand(Paint paint) {
        if (paint != null) {
            this.f4207a = new Paint(paint);
            return;
        }
        Log.w("PAINTROID", "Object is null falling back to default object in " + toString());
        this.f4207a = new Paint();
        this.f4207a.setColor(-16777216);
        this.f4207a.setStrokeWidth(1.0f);
        this.f4207a.setStrokeCap(Cap.SQUARE);
    }

    public void m5440a() {
        if (!(this.f4208b == null || this.f4208b.isRecycled())) {
            this.f4208b.recycle();
            this.f4208b = null;
        }
        if (this.f4209c != null && this.f4209c.exists()) {
            this.f4209c.delete();
        }
    }

    protected void m5441a(BaseCommand baseCommand) {
        setChanged();
        notifyObservers(baseCommand);
    }

    public final void m5442b() {
        File cacheDir = PaintroidApplication.f4191a.getCacheDir();
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        this.f4209c = new File(cacheDir.getAbsolutePath(), Long.toString(random.nextLong()));
        try {
            OutputStream fileOutputStream = new FileOutputStream(this.f4209c);
            this.f4208b.compress(CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Throwable e) {
            Log.e("PAINTROID", "Cannot store bitmap. ", e);
        }
        this.f4208b.recycle();
        this.f4208b = null;
    }
}
