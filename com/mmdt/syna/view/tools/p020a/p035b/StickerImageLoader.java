package com.mmdt.syna.view.tools.p020a.p035b;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import java.lang.ref.WeakReference;
import org.apache.http.HttpStatus;

/* renamed from: com.mmdt.syna.view.tools.a.b.d */
public class StickerImageLoader {
    private StickerImageCache f3126a;
    private Bitmap f3127b;
    private boolean f3128c;
    private boolean f3129d;
    private final Object f3130e;
    private int f3131f;
    private Resources f3132g;
    private int f3133h;
    private Context f3134i;

    /* renamed from: com.mmdt.syna.view.tools.a.b.d.a */
    private static class StickerImageLoader extends BitmapDrawable {
        private final WeakReference<StickerImageLoader> f3119a;

        public StickerImageLoader(Resources resources, Bitmap bitmap, StickerImageLoader stickerImageLoader) {
            super(resources, bitmap);
            this.f3119a = new WeakReference(stickerImageLoader);
        }

        public StickerImageLoader m3963a() {
            return (StickerImageLoader) this.f3119a.get();
        }
    }

    /* renamed from: com.mmdt.syna.view.tools.a.b.d.b */
    private class StickerImageLoader extends AsyncTask<Object, Void, StickerImageHolder> {
        final /* synthetic */ StickerImageLoader f3120a;
        private String f3121b;
        private String f3122c;
        private String f3123d;
        private String f3124e;
        private final WeakReference<ImageView> f3125f;

        public StickerImageLoader(StickerImageLoader stickerImageLoader, ImageView imageView) {
            this.f3120a = stickerImageLoader;
            this.f3125f = new WeakReference(imageView);
        }

        private ImageView m3964a() {
            ImageView imageView = (ImageView) this.f3125f.get();
            return this == StickerImageLoader.m3976b(imageView) ? imageView : null;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected com.mmdt.syna.view.tools.p020a.p035b.StickerImageHolder m3968a(java.lang.Object... r11) {
            /*
            r10 = this;
            r1 = 0;
            r0 = 0;
            r0 = r11[r0];
            r0 = (java.lang.String) r0;
            r10.f3121b = r0;
            r0 = 1;
            r0 = r11[r0];
            r0 = (java.lang.String) r0;
            r10.f3122c = r0;
            r0 = 2;
            r0 = r11[r0];
            r0 = (java.lang.String) r0;
            r10.f3123d = r0;
            r0 = 3;
            r0 = r11[r0];
            r0 = (java.lang.String) r0;
            r10.f3124e = r0;
            r0 = r10.isCancelled();
            if (r0 != 0) goto L_0x0210;
        L_0x0023:
            r0 = r10.m3964a();
            if (r0 == 0) goto L_0x0210;
        L_0x0029:
            r0 = r10.f3120a;
            r0 = r0.f3134i;
            r2 = r10.f3121b;
            r2 = java.lang.Integer.parseInt(r2);
            r3 = r10.f3122c;
            r3 = java.lang.Integer.parseInt(r3);
            r2 = mobi.mmdt.ott.core.model.database.p068h.Stickers.m5149b(r0, r2, r3);
            if (r2 == 0) goto L_0x00d9;
        L_0x0041:
            r0 = r10.f3120a;
            r3 = r0.f3130e;
            monitor-enter(r3);
        L_0x0048:
            r0 = r10.f3120a;	 Catch:{ all -> 0x00d6 }
            r0 = r0.f3129d;	 Catch:{ all -> 0x00d6 }
            if (r0 == 0) goto L_0x0056;
        L_0x0050:
            r0 = r10.isCancelled();	 Catch:{ all -> 0x00d6 }
            if (r0 == 0) goto L_0x00c8;
        L_0x0056:
            monitor-exit(r3);	 Catch:{ all -> 0x00d6 }
            r0 = r10.f3120a;
            r0 = r0.f3134i;
            r0 = mobi.mmdt.ott.core.p051a.FileManager.m4437a(r0);
            r3 = r10.f3121b;
            r4 = r2.m5139b();
            r0 = r0.m4443b(r3, r4);
            r9 = r2;
            r2 = r0;
            r0 = r9;
        L_0x006e:
            if (r2 == 0) goto L_0x020d;
        L_0x0070:
            r3 = r10.f3120a;
            r3 = r3.f3126a;
            if (r3 == 0) goto L_0x020d;
        L_0x0078:
            r2 = android.graphics.BitmapFactory.decodeFile(r2);
            if (r2 == 0) goto L_0x020d;
        L_0x007e:
            if (r0 != 0) goto L_0x0096;
        L_0x0080:
            r0 = r10.f3120a;
            r0 = r0.f3134i;
            r1 = r10.f3121b;
            r1 = java.lang.Integer.parseInt(r1);
            r3 = r10.f3122c;
            r3 = java.lang.Integer.parseInt(r3);
            r0 = mobi.mmdt.ott.core.model.database.p068h.Stickers.m5149b(r0, r1, r3);
        L_0x0096:
            r1 = r0.m5143f();
            r3 = r0.m5138a();
            r0 = new com.mmdt.syna.view.tools.a.b.c;
            r0.<init>(r2, r1, r3);
            r1 = r10.f3120a;
            r1 = r1.f3126a;
            r2 = new java.lang.StringBuilder;
            r3 = r10.f3121b;
            r3 = java.lang.String.valueOf(r3);
            r2.<init>(r3);
            r3 = "_";
            r2 = r2.append(r3);
            r3 = r10.f3122c;
            r2 = r2.append(r3);
            r2 = r2.toString();
            r1.m3958a(r2, r0);
        L_0x00c7:
            return r0;
        L_0x00c8:
            r0 = r10.f3120a;	 Catch:{ InterruptedException -> 0x00d3 }
            r0 = r0.f3130e;	 Catch:{ InterruptedException -> 0x00d3 }
            r0.wait();	 Catch:{ InterruptedException -> 0x00d3 }
            goto L_0x0048;
        L_0x00d3:
            r0 = move-exception;
            goto L_0x0048;
        L_0x00d6:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x00d6 }
            throw r0;
        L_0x00d9:
            r0 = mobi.mmdt.ott.core.p051a.CountryTools.m4416a();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r3 = r10.f3120a;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r3 = r3.f3134i;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r4 = r10.f3124e;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r0.m4425c(r3, r4);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r3 = r10.f3120a;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r3 = r3.f3134i;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r4 = r10.f3120a;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r4 = r4.f3134i;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r4 = mobi.mmdt.ott.core.model.p058a.AppSettings.m4867a(r4);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r4 = r4.m4915y();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r5 = r10.f3120a;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r5 = r5.f3134i;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r5 = mobi.mmdt.ott.core.model.p058a.AppSettings.m4867a(r5);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r5 = r5.m4897j();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = r10.f3120a;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = r6.f3134i;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = mobi.mmdt.ott.core.model.p058a.AppSettings.m4867a(r6);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = r6.m4901l();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r7 = r10.f3120a;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r7 = r7.f3134i;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r7 = mobi.mmdt.ott.core.model.p058a.AppSettings.m4867a(r7);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r7 = r7.m4889f();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r3 = mobi.mmdt.ott.p043b.p044a.p045a.StickerWebservices.m4305a(r3, r4, r5, r6, r7);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r4 = r10.f3123d;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r5 = r10.f3121b;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = r10.f3122c;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r3 = r3.m4308a(r0, r4, r5, r6);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r10.f3120a;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r0.f3134i;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r0.getResources();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r0.getDisplayMetrics();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r0.densityDpi;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            switch(r0) {
                case 120: goto L_0x01c0;
                case 160: goto L_0x01c9;
                case 240: goto L_0x01d3;
                case 320: goto L_0x01dd;
                case 480: goto L_0x01e7;
                case 640: goto L_0x01f1;
                default: goto L_0x0148;
            };	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
        L_0x0148:
            r0 = r3.m4280b();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r0.m4299e();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
        L_0x0150:
            if (r0 == 0) goto L_0x01ff;
        L_0x0152:
            r4 = r10.f3121b;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r4 = java.lang.Integer.parseInt(r4);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r5 = new java.lang.StringBuilder;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = "v";
            r5.<init>(r6);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = r3.m4281c();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r5 = r5.append(r6);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = "_";
            r5 = r5.append(r6);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = r10.f3121b;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r5 = r5.append(r6);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = "_";
            r5 = r5.append(r6);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = r10.f3122c;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r5 = r5.append(r6);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = "_orig.png";
            r5 = r5.append(r6);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r5 = r5.toString();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = r10.f3120a;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = r6.f3134i;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = mobi.mmdt.ott.core.p051a.FileManager.m4437a(r6);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r7 = r10.f3121b;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r6 = r6.m4443b(r7, r5);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r7 = r10.f3120a;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r7 = r7.f3134i;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r7 = mobi.mmdt.ott.core.logic.p034c.StickerDownloader.m4646a(r7);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r8 = new com.mmdt.syna.view.tools.a.b.e;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r8.<init>(r10, r4, r3, r5);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r7.m4648a(r4, r0, r6, r8);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r10.f3120a;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r0.f3134i;	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = mobi.mmdt.ott.core.logic.p034c.StickerDownloader.m4646a(r0);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r0.m4649b(r4);	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0.m4645a();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r2;
            r2 = r1;
            goto L_0x006e;
        L_0x01c0:
            r0 = r3.m4280b();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r0.m4295a();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            goto L_0x0150;
        L_0x01c9:
            r0 = r3.m4280b();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r0.m4296b();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            goto L_0x0150;
        L_0x01d3:
            r0 = r3.m4280b();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r0.m4299e();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            goto L_0x0150;
        L_0x01dd:
            r0 = r3.m4280b();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r0.m4300f();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            goto L_0x0150;
        L_0x01e7:
            r0 = r3.m4280b();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r0.m4298d();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            goto L_0x0150;
        L_0x01f1:
            r0 = r3.m4280b();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            r0 = r0.m4297c();	 Catch:{ NumberFormatException -> 0x0203, NameNotFoundException -> 0x0207, GeneralSecurityException -> 0x0209, b -> 0x01fb, IOException -> 0x0205, b -> 0x020b }
            goto L_0x0150;
        L_0x01fb:
            r0 = move-exception;
        L_0x01fc:
            r0.printStackTrace();
        L_0x01ff:
            r0 = r2;
            r2 = r1;
            goto L_0x006e;
        L_0x0203:
            r0 = move-exception;
            goto L_0x01fc;
        L_0x0205:
            r0 = move-exception;
            goto L_0x01fc;
        L_0x0207:
            r0 = move-exception;
            goto L_0x01fc;
        L_0x0209:
            r0 = move-exception;
            goto L_0x01fc;
        L_0x020b:
            r0 = move-exception;
            goto L_0x01fc;
        L_0x020d:
            r0 = r1;
            goto L_0x00c7;
        L_0x0210:
            r0 = r1;
            r2 = r1;
            goto L_0x006e;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mmdt.syna.view.tools.a.b.d.b.a(java.lang.Object[]):com.mmdt.syna.view.tools.a.b.c");
        }

        protected void m3969a(StickerImageHolder stickerImageHolder) {
            if (isCancelled()) {
                stickerImageHolder = null;
            }
            ImageView a = m3964a();
            if (stickerImageHolder != null && a != null) {
                this.f3120a.m3973a(a, stickerImageHolder.m3960a(), stickerImageHolder.m3962c(), stickerImageHolder.m3961b());
            }
        }

        @SuppressLint({"NewApi"})
        protected void m3970b(StickerImageHolder stickerImageHolder) {
            if (VERSION.SDK_INT > 11) {
                super.onCancelled(stickerImageHolder);
            }
            synchronized (this.f3120a.f3130e) {
                this.f3120a.f3130e.notifyAll();
            }
        }

        protected /* synthetic */ Object doInBackground(Object... objArr) {
            return m3968a(objArr);
        }

        protected /* synthetic */ void onCancelled(Object obj) {
            m3970b((StickerImageHolder) obj);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m3969a((StickerImageHolder) obj);
        }
    }

    public StickerImageLoader(Context context, int i) {
        this.f3128c = false;
        this.f3129d = false;
        this.f3130e = new Object();
        this.f3134i = context;
        this.f3132g = context.getResources();
        this.f3131f = i;
        this.f3133h = this.f3134i.getResources().getDisplayMetrics().densityDpi;
    }

    private void m3973a(ImageView imageView, Bitmap bitmap, int i, int i2) {
        int i3;
        int i4;
        if (this.f3128c) {
            Drawable transitionDrawable = new TransitionDrawable(new Drawable[]{new ColorDrawable(17170445), new BitmapDrawable(this.f3132g, bitmap)});
            imageView.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(HttpStatus.SC_OK);
        } else {
            imageView.setImageBitmap(bitmap);
        }
        bitmap.getHeight();
        bitmap.getWidth();
        switch (this.f3133h) {
            case 120:
                i3 = (int) (48.0d * ((double) i));
                i4 = (int) (48.0d * ((double) i2));
                break;
            case 160:
                i3 = (int) (64.0d * ((double) i));
                i4 = (int) (64.0d * ((double) i2));
                break;
            case 240:
                i3 = (int) (96.0d * ((double) i));
                i4 = (int) (96.0d * ((double) i2));
                break;
            case 320:
                i3 = (int) (128.0d * ((double) i));
                i4 = (int) (128.0d * ((double) i2));
                break;
            case 480:
                i3 = (int) (192.0d * ((double) i));
                i4 = (int) (192.0d * ((double) i2));
                break;
            case 640:
                i3 = (int) (256.0d * ((double) i));
                i4 = (int) (256.0d * ((double) i2));
                break;
            default:
                i3 = (int) (96.0d * ((double) i));
                i4 = (int) (96.0d * ((double) i2));
                break;
        }
        imageView.setLayoutParams(new LayoutParams(i4, i3));
    }

    public static boolean m3975a(String str, String str2, ImageView imageView) {
        StickerImageLoader b = StickerImageLoader.m3976b(imageView);
        if (b == null) {
            return true;
        }
        String a = b.f3121b;
        String b2 = b.f3122c;
        if (a != null && a.equals(str) && b2 != null && b2.equals(str2)) {
            return false;
        }
        b.cancel(true);
        return true;
    }

    private static StickerImageLoader m3976b(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof StickerImageLoader) {
                return ((StickerImageLoader) drawable).m3963a();
            }
        }
        return null;
    }

    public void m3980a(int i) {
        try {
            this.f3127b = BitmapFactory.decodeResource(this.f3132g, i);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }

    public void m3981a(FragmentManager fragmentManager, float f) {
        this.f3126a = StickerImageCache.m3955a(fragmentManager, f);
    }

    public void m3982a(String str, String str2, String str3, String str4, ImageView imageView) {
        if (str == null || str2 == null) {
            imageView.setImageBitmap(this.f3127b);
            return;
        }
        StickerImageHolder stickerImageHolder = null;
        String stringBuilder = new StringBuilder(String.valueOf(str)).append("_").append(str2).toString();
        if (this.f3126a != null) {
            stickerImageHolder = this.f3126a.m3957a(String.valueOf(stringBuilder));
        }
        if (stickerImageHolder != null) {
            m3973a(imageView, stickerImageHolder.m3960a(), stickerImageHolder.m3962c(), stickerImageHolder.m3961b());
        } else if (StickerImageLoader.m3975a(str, str2, imageView)) {
            StickerImageLoader stickerImageLoader = new StickerImageLoader(this, imageView);
            imageView.setImageDrawable(new StickerImageLoader(this.f3132g, this.f3127b, stickerImageLoader));
            stickerImageLoader.execute(new Object[]{str, str2, str3, str4});
        }
    }

    public void m3983a(boolean z) {
        synchronized (this.f3130e) {
            this.f3129d = z;
            if (!this.f3129d) {
                this.f3130e.notifyAll();
            }
        }
    }
}
