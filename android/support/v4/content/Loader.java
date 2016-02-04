package android.support.v4.content;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.support.v4.p008c.DebugUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: android.support.v4.content.h */
public class Loader<D> {
    int f400m;
    Loader<D> f401n;
    Context f402o;
    boolean f403p;
    boolean f404q;
    boolean f405r;
    boolean f406s;
    boolean f407t;

    /* renamed from: android.support.v4.content.h.b */
    public interface Loader<D> {
        void m134a(Loader<D> loader, D d);
    }

    /* renamed from: android.support.v4.content.h.a */
    public final class Loader extends ContentObserver {
        final /* synthetic */ Loader f421a;

        public Loader(Loader loader) {
            this.f421a = loader;
            super(new Handler());
        }

        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean z) {
            this.f421a.m492x();
        }
    }

    public Loader(Context context) {
        this.f403p = false;
        this.f404q = false;
        this.f405r = true;
        this.f406s = false;
        this.f407t = false;
        this.f402o = context.getApplicationContext();
    }

    protected void m469a() {
    }

    public void m470a(int i, Loader<D> loader) {
        if (this.f401n != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.f401n = loader;
        this.f400m = i;
    }

    public void m471a(Loader<D> loader) {
        if (this.f401n == null) {
            throw new IllegalStateException("No listener register");
        } else if (this.f401n != loader) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        } else {
            this.f401n = null;
        }
    }

    public void m472a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.f400m);
        printWriter.print(" mListener=");
        printWriter.println(this.f401n);
        if (this.f403p || this.f406s || this.f407t) {
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.f403p);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.f406s);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.f407t);
        }
        if (this.f404q || this.f405r) {
            printWriter.print(str);
            printWriter.print("mAbandoned=");
            printWriter.print(this.f404q);
            printWriter.print(" mReset=");
            printWriter.println(this.f405r);
        }
    }

    public void m473b(D d) {
        if (this.f401n != null) {
            this.f401n.m134a(this, d);
        }
    }

    public String m474c(D d) {
        StringBuilder stringBuilder = new StringBuilder(64);
        DebugUtils.m419a(d, stringBuilder);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    protected void m475g() {
    }

    protected void m476h() {
    }

    protected void m477i() {
    }

    public Context m478j() {
        return this.f402o;
    }

    public int m479k() {
        return this.f400m;
    }

    public boolean m480l() {
        return this.f403p;
    }

    public boolean m481m() {
        return this.f404q;
    }

    public boolean m482n() {
        return this.f405r;
    }

    public final void m483o() {
        this.f403p = true;
        this.f405r = false;
        this.f404q = false;
        m475g();
    }

    public void m484p() {
        m469a();
    }

    public void m485q() {
        this.f403p = false;
        m476h();
    }

    public void m486r() {
        this.f404q = true;
        m487s();
    }

    protected void m487s() {
    }

    public void m488t() {
        m477i();
        this.f405r = true;
        this.f403p = false;
        this.f404q = false;
        this.f406s = false;
        this.f407t = false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        DebugUtils.m419a(this, stringBuilder);
        stringBuilder.append(" id=");
        stringBuilder.append(this.f400m);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public boolean m489u() {
        boolean z = this.f406s;
        this.f406s = false;
        this.f407t |= z;
        return z;
    }

    public void m490v() {
        this.f407t = false;
    }

    public void m491w() {
        if (this.f407t) {
            this.f406s = true;
        }
    }

    public void m492x() {
        if (this.f403p) {
            m484p();
        } else {
            this.f406s = true;
        }
    }
}
