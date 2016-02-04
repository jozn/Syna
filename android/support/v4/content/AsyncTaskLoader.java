package android.support.v4.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.p008c.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;

/* renamed from: android.support.v4.content.a */
public abstract class AsyncTaskLoader<D> extends Loader<D> {
    volatile AsyncTaskLoader f408a;
    volatile AsyncTaskLoader f409b;
    long f410c;
    long f411d;
    Handler f412e;

    /* renamed from: android.support.v4.content.a.a */
    final class AsyncTaskLoader extends ModernAsyncTask<Void, Void, D> implements Runnable {
        D f396a;
        boolean f397b;
        final /* synthetic */ AsyncTaskLoader f398c;
        private CountDownLatch f399e;

        AsyncTaskLoader(AsyncTaskLoader asyncTaskLoader) {
            this.f398c = asyncTaskLoader;
            this.f399e = new CountDownLatch(1);
        }

        protected D m466a(Void... voidArr) {
            this.f396a = this.f398c.m501e();
            return this.f396a;
        }

        protected void m467a() {
            try {
                this.f398c.m494a(this, this.f396a);
            } finally {
                this.f399e.countDown();
            }
        }

        protected void m468a(D d) {
            try {
                this.f398c.m497b(this, d);
            } finally {
                this.f399e.countDown();
            }
        }

        public void run() {
            this.f397b = false;
            this.f398c.m499c();
        }
    }

    public AsyncTaskLoader(Context context) {
        super(context);
        this.f411d = -10000;
    }

    protected void m493a() {
        super.m469a();
        m498b();
        this.f408a = new AsyncTaskLoader(this);
        m499c();
    }

    void m494a(AsyncTaskLoader asyncTaskLoader, D d) {
        m495a(d);
        if (this.f409b == asyncTaskLoader) {
            m491w();
            this.f411d = SystemClock.uptimeMillis();
            this.f409b = null;
            m499c();
        }
    }

    public void m495a(D d) {
    }

    public void m496a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.m472a(str, fileDescriptor, printWriter, strArr);
        if (this.f408a != null) {
            printWriter.print(str);
            printWriter.print("mTask=");
            printWriter.print(this.f408a);
            printWriter.print(" waiting=");
            printWriter.println(this.f408a.f397b);
        }
        if (this.f409b != null) {
            printWriter.print(str);
            printWriter.print("mCancellingTask=");
            printWriter.print(this.f409b);
            printWriter.print(" waiting=");
            printWriter.println(this.f409b.f397b);
        }
        if (this.f410c != 0) {
            printWriter.print(str);
            printWriter.print("mUpdateThrottle=");
            TimeUtils.m438a(this.f410c, printWriter);
            printWriter.print(" mLastLoadCompleteTime=");
            TimeUtils.m437a(this.f411d, SystemClock.uptimeMillis(), printWriter);
            printWriter.println();
        }
    }

    void m497b(AsyncTaskLoader asyncTaskLoader, D d) {
        if (this.f408a != asyncTaskLoader) {
            m494a(asyncTaskLoader, d);
        } else if (m481m()) {
            m495a(d);
        } else {
            m490v();
            this.f411d = SystemClock.uptimeMillis();
            this.f408a = null;
            m473b(d);
        }
    }

    public boolean m498b() {
        boolean z = false;
        if (this.f408a != null) {
            if (this.f409b != null) {
                if (this.f408a.f397b) {
                    this.f408a.f397b = false;
                    this.f412e.removeCallbacks(this.f408a);
                }
                this.f408a = null;
            } else if (this.f408a.f397b) {
                this.f408a.f397b = false;
                this.f412e.removeCallbacks(this.f408a);
                this.f408a = null;
            } else {
                z = this.f408a.m460a(false);
                if (z) {
                    this.f409b = this.f408a;
                }
                this.f408a = null;
            }
        }
        return z;
    }

    void m499c() {
        if (this.f409b == null && this.f408a != null) {
            if (this.f408a.f397b) {
                this.f408a.f397b = false;
                this.f412e.removeCallbacks(this.f408a);
            }
            if (this.f410c <= 0 || SystemClock.uptimeMillis() >= this.f411d + this.f410c) {
                this.f408a.m456a(ModernAsyncTask.f390d, (Object[]) (Void[]) null);
                return;
            }
            this.f408a.f397b = true;
            this.f412e.postAtTime(this.f408a, this.f411d + this.f410c);
        }
    }

    public abstract D m500d();

    protected D m501e() {
        return m500d();
    }
}
