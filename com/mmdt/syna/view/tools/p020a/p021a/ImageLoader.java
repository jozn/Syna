package com.mmdt.syna.view.tools.p020a.p021a;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.widget.ImageView;
import java.io.FileDescriptor;
import java.lang.ref.WeakReference;
import org.apache.http.HttpStatus;

/* renamed from: com.mmdt.syna.view.tools.a.a.c */
public abstract class ImageLoader {
    private ImageCache f1615a;
    private Bitmap f1616b;
    private boolean f1617c;
    private boolean f1618d;
    private final Object f1619e;
    private int f1620f;
    private Resources f1621g;

    /* renamed from: com.mmdt.syna.view.tools.a.a.c.a */
    private static class ImageLoader extends BitmapDrawable {
        private final WeakReference<ImageLoader> f3109a;

        public ImageLoader(Resources resources, Bitmap bitmap, ImageLoader imageLoader) {
            super(resources, bitmap);
            this.f3109a = new WeakReference(imageLoader);
        }

        public ImageLoader m3944a() {
            return (ImageLoader) this.f3109a.get();
        }
    }

    /* renamed from: com.mmdt.syna.view.tools.a.a.c.b */
    private class ImageLoader extends AsyncTask<Object, Void, Bitmap> {
        final /* synthetic */ ImageLoader f3110a;
        private Object f3111b;
        private final WeakReference<ImageView> f3112c;

        public ImageLoader(ImageLoader imageLoader, ImageView imageView) {
            this.f3110a = imageLoader;
            this.f3112c = new WeakReference(imageView);
        }

        private ImageView m3945a() {
            ImageView imageView = (ImageView) this.f3112c.get();
            return this == ImageLoader.m2476b(imageView) ? imageView : null;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected android.graphics.Bitmap m3947a(java.lang.Object... r6) {
            /*
            r5 = this;
            r4 = 0;
            r0 = r6[r4];
            r5.f3111b = r0;
            r0 = r5.f3111b;
            r1 = java.lang.String.valueOf(r0);
            r0 = 0;
            r2 = r5.f3110a;
            r2 = r2.f1619e;
            monitor-enter(r2);
        L_0x0013:
            r3 = r5.f3110a;	 Catch:{ all -> 0x0056 }
            r3 = r3.f1618d;	 Catch:{ all -> 0x0056 }
            if (r3 == 0) goto L_0x0021;
        L_0x001b:
            r3 = r5.isCancelled();	 Catch:{ all -> 0x0056 }
            if (r3 == 0) goto L_0x004a;
        L_0x0021:
            monitor-exit(r2);	 Catch:{ all -> 0x0056 }
            r2 = r5.isCancelled();
            if (r2 != 0) goto L_0x0036;
        L_0x0028:
            r2 = r5.m3945a();
            if (r2 == 0) goto L_0x0036;
        L_0x002e:
            r0 = r5.f3110a;
            r2 = r6[r4];
            r0 = r0.m2481a(r2);
        L_0x0036:
            if (r0 == 0) goto L_0x0049;
        L_0x0038:
            r2 = r5.f3110a;
            r2 = r2.f1615a;
            if (r2 == 0) goto L_0x0049;
        L_0x0040:
            r2 = r5.f3110a;
            r2 = r2.f1615a;
            r2.m3942a(r1, r0);
        L_0x0049:
            return r0;
        L_0x004a:
            r3 = r5.f3110a;	 Catch:{ InterruptedException -> 0x0054 }
            r3 = r3.f1619e;	 Catch:{ InterruptedException -> 0x0054 }
            r3.wait();	 Catch:{ InterruptedException -> 0x0054 }
            goto L_0x0013;
        L_0x0054:
            r3 = move-exception;
            goto L_0x0013;
        L_0x0056:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0056 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mmdt.syna.view.tools.a.a.c.b.a(java.lang.Object[]):android.graphics.Bitmap");
        }

        protected void m3948a(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }
            ImageView a = m3945a();
            if (bitmap != null && a != null) {
                this.f3110a.m2474a(a, bitmap);
            }
        }

        @SuppressLint({"NewApi"})
        protected void m3949b(Bitmap bitmap) {
            if (VERSION.SDK_INT > 11) {
                super.onCancelled(bitmap);
            }
            synchronized (this.f3110a.f1619e) {
                this.f3110a.f1619e.notifyAll();
            }
        }

        protected /* synthetic */ Object doInBackground(Object... objArr) {
            return m3947a(objArr);
        }

        protected /* synthetic */ void onCancelled(Object obj) {
            m3949b((Bitmap) obj);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m3948a((Bitmap) obj);
        }
    }

    protected ImageLoader(Context context, int i) {
        this.f1617c = true;
        this.f1618d = false;
        this.f1619e = new Object();
        this.f1621g = context.getResources();
        this.f1620f = i;
    }

    public static int m2470a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        i3 = options.outWidth;
        return 1;
    }

    public static Bitmap m2471a(FileDescriptor fileDescriptor, int i, int i2) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = ImageLoader.m2470a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    private void m2474a(ImageView imageView, Bitmap bitmap) {
        if (this.f1617c) {
            Drawable transitionDrawable = new TransitionDrawable(new Drawable[]{new ColorDrawable(17170445), new BitmapDrawable(this.f1621g, bitmap)});
            imageView.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(HttpStatus.SC_OK);
            return;
        }
        imageView.setImageBitmap(bitmap);
    }

    private static ImageLoader m2476b(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof ImageLoader) {
                return ((ImageLoader) drawable).m3944a();
            }
        }
        return null;
    }

    public static boolean m2478b(Object obj, ImageView imageView) {
        ImageLoader b = ImageLoader.m2476b(imageView);
        if (b == null) {
            return true;
        }
        Object a = b.f3111b;
        if (a != null && a.equals(obj)) {
            return false;
        }
        b.cancel(true);
        return true;
    }

    public int m2480a() {
        return this.f1620f;
    }

    protected abstract Bitmap m2481a(Object obj);

    public void m2482a(int i) {
        try {
            this.f1616b = BitmapFactory.decodeResource(this.f1621g, i);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }

    public void m2483a(FragmentManager fragmentManager, float f) {
        this.f1615a = ImageCache.m3939a(fragmentManager, f);
    }

    public void m2484a(Object obj, ImageView imageView) {
        if (obj == null) {
            imageView.setImageBitmap(this.f1616b);
            return;
        }
        Bitmap bitmap = null;
        if (this.f1615a != null) {
            bitmap = this.f1615a.m3941a(String.valueOf(obj));
        }
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else if (ImageLoader.m2478b(obj, imageView)) {
            ImageLoader imageLoader = new ImageLoader(this, imageView);
            imageView.setImageDrawable(new ImageLoader(this.f1621g, this.f1616b, imageLoader));
            imageLoader.execute(new Object[]{obj});
        }
    }

    public void m2485a(boolean z) {
        this.f1617c = z;
    }

    public void m2486b(boolean z) {
        synchronized (this.f1619e) {
            this.f1618d = z;
            if (!this.f1618d) {
                this.f1619e.notifyAll();
            }
        }
    }
}
