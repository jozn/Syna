package com.mmdt.syna.view.tools.p020a.p036c;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.widget.TextView;
import com.mmdt.syna.view.tools.Linkifier;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* renamed from: com.mmdt.syna.view.tools.a.c.c */
public class TextLoader {
    private static TextLoader f3149a;
    private TextCache f3150b;
    private final Spannable f3151c;
    private boolean f3152d;
    private final Object f3153e;
    private Context f3154f;

    /* renamed from: com.mmdt.syna.view.tools.a.c.c.a */
    private static class TextLoader {
        private final WeakReference<TextLoader> f3142a;

        public TextLoader(TextLoader textLoader) {
            this.f3142a = new WeakReference(textLoader);
        }

        public TextLoader m3998a() {
            return (TextLoader) this.f3142a.get();
        }
    }

    /* renamed from: com.mmdt.syna.view.tools.a.c.c.b */
    private class TextLoader extends AsyncTask<Object, Void, Spannable> {
        final /* synthetic */ TextLoader f3143a;
        private String f3144b;
        private boolean f3145c;
        private boolean f3146d;
        private byte[] f3147e;
        private final WeakReference<TextView> f3148f;

        public TextLoader(TextLoader textLoader, TextView textView) {
            this.f3143a = textLoader;
            this.f3148f = new WeakReference(textView);
        }

        private TextView m3999a() {
            TextView textView = (TextView) this.f3148f.get();
            return this == TextLoader.m4010b(textView) ? textView : null;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected android.text.Spannable m4001a(java.lang.Object... r7) {
            /*
            r6 = this;
            r0 = java.lang.Thread.currentThread();
            r1 = 10;
            r0.setPriority(r1);
            r0 = 0;
            r0 = r7[r0];
            r0 = (java.lang.String) r0;
            r6.f3144b = r0;
            r0 = 1;
            r0 = r7[r0];
            r0 = (byte[]) r0;
            r6.f3147e = r0;
            r0 = 2;
            r0 = r7[r0];
            r0 = (java.lang.Boolean) r0;
            r0 = r0.booleanValue();
            r6.f3145c = r0;
            r0 = 3;
            r0 = r7[r0];
            r0 = (java.lang.Boolean) r0;
            r0 = r0.booleanValue();
            r6.f3146d = r0;
            r1 = 0;
            r0 = r6.f3143a;
            r2 = r0.f3153e;
            monitor-enter(r2);
        L_0x0035:
            r0 = r6.f3143a;	 Catch:{ all -> 0x0091 }
            r0 = r0.f3152d;	 Catch:{ all -> 0x0091 }
            if (r0 == 0) goto L_0x0043;
        L_0x003d:
            r0 = r6.isCancelled();	 Catch:{ all -> 0x0091 }
            if (r0 == 0) goto L_0x0085;
        L_0x0043:
            monitor-exit(r2);	 Catch:{ all -> 0x0091 }
            r0 = r6.isCancelled();
            if (r0 != 0) goto L_0x0098;
        L_0x004a:
            r0 = r6.m3999a();
            if (r0 == 0) goto L_0x0098;
        L_0x0050:
            r0 = r6.f3143a;	 Catch:{ UnsupportedEncodingException -> 0x0094 }
            r0 = r0.f3154f;	 Catch:{ UnsupportedEncodingException -> 0x0094 }
            r0 = com.mmdt.syna.view.tools.p037b.EmoticonManager.m4022a(r0);	 Catch:{ UnsupportedEncodingException -> 0x0094 }
            r2 = r6.f3143a;	 Catch:{ UnsupportedEncodingException -> 0x0094 }
            r2 = r2.f3154f;	 Catch:{ UnsupportedEncodingException -> 0x0094 }
            r3 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x0094 }
            r4 = r6.f3147e;	 Catch:{ UnsupportedEncodingException -> 0x0094 }
            r5 = "UTF-8";
            r3.<init>(r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x0094 }
            r4 = r6.f3145c;	 Catch:{ UnsupportedEncodingException -> 0x0094 }
            r0 = r0.m4024a(r2, r3, r4);	 Catch:{ UnsupportedEncodingException -> 0x0094 }
        L_0x006f:
            if (r0 == 0) goto L_0x0084;
        L_0x0071:
            r1 = r6.f3143a;
            r1 = r1.f3150b;
            if (r1 == 0) goto L_0x0084;
        L_0x0079:
            r1 = r6.f3143a;
            r1 = r1.f3150b;
            r2 = r6.f3144b;
            r1.m3996a(r2, r0);
        L_0x0084:
            return r0;
        L_0x0085:
            r0 = r6.f3143a;	 Catch:{ InterruptedException -> 0x008f }
            r0 = r0.f3153e;	 Catch:{ InterruptedException -> 0x008f }
            r0.wait();	 Catch:{ InterruptedException -> 0x008f }
            goto L_0x0035;
        L_0x008f:
            r0 = move-exception;
            goto L_0x0035;
        L_0x0091:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0091 }
            throw r0;
        L_0x0094:
            r0 = move-exception;
            r0.printStackTrace();
        L_0x0098:
            r0 = r1;
            goto L_0x006f;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mmdt.syna.view.tools.a.c.c.b.a(java.lang.Object[]):android.text.Spannable");
        }

        protected void m4002a(Spannable spannable) {
            if (isCancelled()) {
                spannable = null;
            }
            TextView a = m3999a();
            if (a != null && spannable != null) {
                this.f3143a.m4007a(a, spannable, this.f3146d);
            }
        }

        @SuppressLint({"NewApi"})
        protected void m4003b(Spannable spannable) {
            if (VERSION.SDK_INT > 11) {
                super.onCancelled(spannable);
            }
            synchronized (this.f3143a.f3153e) {
                this.f3143a.f3153e.notifyAll();
            }
        }

        protected /* synthetic */ Object doInBackground(Object... objArr) {
            return m4001a(objArr);
        }

        protected /* synthetic */ void onCancelled(Object obj) {
            m4003b((Spannable) obj);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m4002a((Spannable) obj);
        }
    }

    private TextLoader(Context context) {
        this.f3151c = new SpannableStringBuilder("");
        this.f3152d = false;
        this.f3153e = new Object();
        this.f3154f = context;
    }

    public static TextLoader m4005a(Context context) {
        if (f3149a == null) {
            f3149a = new TextLoader(context);
        }
        return f3149a;
    }

    private void m4007a(TextView textView, Spannable spannable, boolean z) {
        textView.setText(spannable);
        if (z) {
            Linkifier.m4027a(textView);
        }
    }

    public static boolean m4009a(byte[] bArr, TextView textView) {
        TextLoader b = TextLoader.m4010b(textView);
        if (b == null) {
            return true;
        }
        byte[] a = b.f3147e;
        if (a != null && Arrays.equals(a, bArr)) {
            return false;
        }
        b.cancel(true);
        return true;
    }

    private static TextLoader m4010b(TextView textView) {
        if (textView != null) {
            Object tag = textView.getTag();
            if (tag instanceof TextLoader) {
                return ((TextLoader) tag).m3998a();
            }
        }
        return null;
    }

    public void m4014a(FragmentManager fragmentManager, float f) {
        if (this.f3150b == null) {
            this.f3150b = TextCache.m3994a(fragmentManager, f);
        }
    }

    public void m4015a(String str, Spannable spannable) {
        if (this.f3150b != null) {
            this.f3150b.m3996a(str, spannable);
        }
    }

    public void m4016a(String str, byte[] bArr, TextView textView, boolean z, boolean z2) {
        Spannable spannable = null;
        if (bArr == null) {
            textView.setText("");
            return;
        }
        if (this.f3150b != null) {
            spannable = this.f3150b.m3995a(str);
        }
        if (spannable != null) {
            m4007a(textView, spannable, z2);
        } else if (TextLoader.m4009a(bArr, textView)) {
            TextLoader textLoader = new TextLoader(this, textView);
            TextLoader textLoader2 = new TextLoader(textLoader);
            m4007a(textView, this.f3151c, z2);
            textView.setTag(textLoader2);
            textLoader.execute(new Object[]{str, bArr, Boolean.valueOf(z), Boolean.valueOf(z2)});
        }
    }

    public void m4017a(boolean z) {
        synchronized (this.f3153e) {
            this.f3152d = z;
            if (!this.f3152d) {
                this.f3153e.notifyAll();
            }
        }
    }
}
