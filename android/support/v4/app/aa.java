package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.content.Loader.Loader;
import android.support.v4.p008c.DebugUtils;
import android.support.v4.p008c.SparseArrayCompat;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

/* compiled from: LoaderManager */
class aa extends LoaderManager {
    static boolean f232a;
    final SparseArrayCompat<LoaderManager> f233b;
    final SparseArrayCompat<LoaderManager> f234c;
    final String f235d;
    FragmentActivity f236e;
    boolean f237f;
    boolean f238g;
    boolean f239h;

    /* renamed from: android.support.v4.app.aa.a */
    final class LoaderManager implements Loader<Object> {
        final int f217a;
        final Bundle f218b;
        android.support.v4.app.LoaderManager.LoaderManager<Object> f219c;
        android.support.v4.content.Loader<Object> f220d;
        boolean f221e;
        boolean f222f;
        Object f223g;
        boolean f224h;
        boolean f225i;
        boolean f226j;
        boolean f227k;
        boolean f228l;
        boolean f229m;
        LoaderManager f230n;
        final /* synthetic */ aa f231o;

        public LoaderManager(aa aaVar, int i, Bundle bundle, android.support.v4.app.LoaderManager.LoaderManager<Object> loaderManager) {
            this.f231o = aaVar;
            this.f217a = i;
            this.f218b = bundle;
            this.f219c = loaderManager;
        }

        void m135a() {
            if (this.f225i && this.f226j) {
                this.f224h = true;
            } else if (!this.f224h) {
                this.f224h = true;
                if (aa.f232a) {
                    Log.v("LoaderManager", "  Starting: " + this);
                }
                if (this.f220d == null && this.f219c != null) {
                    this.f220d = this.f219c.m370a(this.f217a, this.f218b);
                }
                if (this.f220d == null) {
                    return;
                }
                if (!this.f220d.getClass().isMemberClass() || Modifier.isStatic(this.f220d.getClass().getModifiers())) {
                    if (!this.f229m) {
                        this.f220d.m470a(this.f217a, this);
                        this.f229m = true;
                    }
                    this.f220d.m483o();
                    return;
                }
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + this.f220d);
            }
        }

        public void m136a(android.support.v4.content.Loader<Object> loader, Object obj) {
            if (aa.f232a) {
                Log.v("LoaderManager", "onLoadComplete: " + this);
            }
            if (this.f228l) {
                if (aa.f232a) {
                    Log.v("LoaderManager", "  Ignoring load complete -- destroyed");
                }
            } else if (this.f231o.f233b.m425a(this.f217a) == this) {
                LoaderManager loaderManager = this.f230n;
                if (loaderManager != null) {
                    if (aa.f232a) {
                        Log.v("LoaderManager", "  Switching to pending loader: " + loaderManager);
                    }
                    this.f230n = null;
                    this.f231o.f233b.m429b(this.f217a, null);
                    m143f();
                    this.f231o.m151a(loaderManager);
                    return;
                }
                if (!(this.f223g == obj && this.f221e)) {
                    this.f223g = obj;
                    this.f221e = true;
                    if (this.f224h) {
                        m139b(loader, obj);
                    }
                }
                loaderManager = (LoaderManager) this.f231o.f234c.m425a(this.f217a);
                if (!(loaderManager == null || loaderManager == this)) {
                    loaderManager.f222f = false;
                    loaderManager.m143f();
                    this.f231o.f234c.m431c(this.f217a);
                }
                if (this.f231o.f236e != null && !this.f231o.m153a()) {
                    this.f231o.f236e.f178b.m293d();
                }
            } else if (aa.f232a) {
                Log.v("LoaderManager", "  Ignoring load complete -- not active");
            }
        }

        public void m137a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.f217a);
            printWriter.print(" mArgs=");
            printWriter.println(this.f218b);
            printWriter.print(str);
            printWriter.print("mCallbacks=");
            printWriter.println(this.f219c);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.f220d);
            if (this.f220d != null) {
                this.f220d.m472a(str + "  ", fileDescriptor, printWriter, strArr);
            }
            if (this.f221e || this.f222f) {
                printWriter.print(str);
                printWriter.print("mHaveData=");
                printWriter.print(this.f221e);
                printWriter.print("  mDeliveredData=");
                printWriter.println(this.f222f);
                printWriter.print(str);
                printWriter.print("mData=");
                printWriter.println(this.f223g);
            }
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.f224h);
            printWriter.print(" mReportNextStart=");
            printWriter.print(this.f227k);
            printWriter.print(" mDestroyed=");
            printWriter.println(this.f228l);
            printWriter.print(str);
            printWriter.print("mRetaining=");
            printWriter.print(this.f225i);
            printWriter.print(" mRetainingStarted=");
            printWriter.print(this.f226j);
            printWriter.print(" mListenerRegistered=");
            printWriter.println(this.f229m);
            if (this.f230n != null) {
                printWriter.print(str);
                printWriter.println("Pending Loader ");
                printWriter.print(this.f230n);
                printWriter.println(":");
                this.f230n.m137a(str + "  ", fileDescriptor, printWriter, strArr);
            }
        }

        void m138b() {
            if (aa.f232a) {
                Log.v("LoaderManager", "  Retaining: " + this);
            }
            this.f225i = true;
            this.f226j = this.f224h;
            this.f224h = false;
            this.f219c = null;
        }

        void m139b(android.support.v4.content.Loader<Object> loader, Object obj) {
            String str;
            if (this.f219c != null) {
                if (this.f231o.f236e != null) {
                    String str2 = this.f231o.f236e.f178b.f308u;
                    this.f231o.f236e.f178b.f308u = "onLoadFinished";
                    str = str2;
                } else {
                    str = null;
                }
                try {
                    if (aa.f232a) {
                        Log.v("LoaderManager", "  onLoadFinished in " + loader + ": " + loader.m474c(obj));
                    }
                    this.f219c.m372a((android.support.v4.content.Loader) loader, obj);
                    this.f222f = true;
                } finally {
                    if (this.f231o.f236e != null) {
                        this.f231o.f236e.f178b.f308u = str;
                    }
                }
            }
        }

        void m140c() {
            if (this.f225i) {
                if (aa.f232a) {
                    Log.v("LoaderManager", "  Finished Retaining: " + this);
                }
                this.f225i = false;
                if (!(this.f224h == this.f226j || this.f224h)) {
                    m142e();
                }
            }
            if (this.f224h && this.f221e && !this.f227k) {
                m139b(this.f220d, this.f223g);
            }
        }

        void m141d() {
            if (this.f224h && this.f227k) {
                this.f227k = false;
                if (this.f221e) {
                    m139b(this.f220d, this.f223g);
                }
            }
        }

        void m142e() {
            if (aa.f232a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.f224h = false;
            if (!this.f225i && this.f220d != null && this.f229m) {
                this.f229m = false;
                this.f220d.m471a(this);
                this.f220d.m485q();
            }
        }

        void m143f() {
            String str;
            android.support.v4.app.LoaderManager.LoaderManager loaderManager = null;
            if (aa.f232a) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.f228l = true;
            boolean z = this.f222f;
            this.f222f = false;
            if (this.f219c != null && this.f220d != null && this.f221e && z) {
                if (aa.f232a) {
                    Log.v("LoaderManager", "  Reseting: " + this);
                }
                if (this.f231o.f236e != null) {
                    String str2 = this.f231o.f236e.f178b.f308u;
                    this.f231o.f236e.f178b.f308u = "onLoaderReset";
                    str = str2;
                } else {
                    str = null;
                }
                try {
                    this.f219c.m371a(this.f220d);
                } finally {
                    loaderManager = this.f231o.f236e;
                    if (loaderManager != null) {
                        loaderManager = this.f231o.f236e.f178b;
                        loaderManager.f308u = str;
                    }
                }
            }
            this.f219c = loaderManager;
            this.f223g = loaderManager;
            this.f221e = false;
            if (this.f220d != null) {
                if (this.f229m) {
                    this.f229m = false;
                    this.f220d.m471a(this);
                }
                this.f220d.m488t();
            }
            if (this.f230n != null) {
                this.f230n.m143f();
            }
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append("LoaderInfo{");
            stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringBuilder.append(" #");
            stringBuilder.append(this.f217a);
            stringBuilder.append(" : ");
            DebugUtils.m419a(this.f220d, stringBuilder);
            stringBuilder.append("}}");
            return stringBuilder.toString();
        }
    }

    static {
        f232a = false;
    }

    aa(String str, FragmentActivity fragmentActivity, boolean z) {
        this.f233b = new SparseArrayCompat();
        this.f234c = new SparseArrayCompat();
        this.f235d = str;
        this.f236e = fragmentActivity;
        this.f237f = z;
    }

    private LoaderManager m147c(int i, Bundle bundle, android.support.v4.app.LoaderManager.LoaderManager<Object> loaderManager) {
        LoaderManager loaderManager2 = new LoaderManager(this, i, bundle, loaderManager);
        loaderManager2.f220d = loaderManager.m370a(i, bundle);
        return loaderManager2;
    }

    private LoaderManager m148d(int i, Bundle bundle, android.support.v4.app.LoaderManager.LoaderManager<Object> loaderManager) {
        try {
            this.f239h = true;
            LoaderManager c = m147c(i, bundle, loaderManager);
            m151a(c);
            return c;
        } finally {
            this.f239h = false;
        }
    }

    public <D> android.support.v4.content.Loader<D> m149a(int i, Bundle bundle, android.support.v4.app.LoaderManager.LoaderManager<D> loaderManager) {
        if (this.f239h) {
            throw new IllegalStateException("Called while creating a loader");
        }
        LoaderManager loaderManager2 = (LoaderManager) this.f233b.m425a(i);
        if (f232a) {
            Log.v("LoaderManager", "initLoader in " + this + ": args=" + bundle);
        }
        if (loaderManager2 == null) {
            loaderManager2 = m148d(i, bundle, loaderManager);
            if (f232a) {
                Log.v("LoaderManager", "  Created new loader " + loaderManager2);
            }
        } else {
            if (f232a) {
                Log.v("LoaderManager", "  Re-using existing loader " + loaderManager2);
            }
            loaderManager2.f219c = loaderManager;
        }
        if (loaderManager2.f221e && this.f237f) {
            loaderManager2.m139b(loaderManager2.f220d, loaderManager2.f223g);
        }
        return loaderManager2.f220d;
    }

    void m150a(FragmentActivity fragmentActivity) {
        this.f236e = fragmentActivity;
    }

    void m151a(LoaderManager loaderManager) {
        this.f233b.m429b(loaderManager.f217a, loaderManager);
        if (this.f237f) {
            loaderManager.m135a();
        }
    }

    public void m152a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i = 0;
        if (this.f233b.m427b() > 0) {
            printWriter.print(str);
            printWriter.println("Active Loaders:");
            String str2 = str + "    ";
            for (int i2 = 0; i2 < this.f233b.m427b(); i2++) {
                LoaderManager loaderManager = (LoaderManager) this.f233b.m433e(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.f233b.m432d(i2));
                printWriter.print(": ");
                printWriter.println(loaderManager.toString());
                loaderManager.m137a(str2, fileDescriptor, printWriter, strArr);
            }
        }
        if (this.f234c.m427b() > 0) {
            printWriter.print(str);
            printWriter.println("Inactive Loaders:");
            String str3 = str + "    ";
            while (i < this.f234c.m427b()) {
                loaderManager = (LoaderManager) this.f234c.m433e(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.f234c.m432d(i));
                printWriter.print(": ");
                printWriter.println(loaderManager.toString());
                loaderManager.m137a(str3, fileDescriptor, printWriter, strArr);
                i++;
            }
        }
    }

    public boolean m153a() {
        int b = this.f233b.m427b();
        boolean z = false;
        for (int i = 0; i < b; i++) {
            LoaderManager loaderManager = (LoaderManager) this.f233b.m433e(i);
            int i2 = (!loaderManager.f224h || loaderManager.f222f) ? 0 : 1;
            z |= i2;
        }
        return z;
    }

    public <D> android.support.v4.content.Loader<D> m154b(int i, Bundle bundle, android.support.v4.app.LoaderManager.LoaderManager<D> loaderManager) {
        if (this.f239h) {
            throw new IllegalStateException("Called while creating a loader");
        }
        LoaderManager loaderManager2 = (LoaderManager) this.f233b.m425a(i);
        if (f232a) {
            Log.v("LoaderManager", "restartLoader in " + this + ": args=" + bundle);
        }
        if (loaderManager2 != null) {
            LoaderManager loaderManager3 = (LoaderManager) this.f234c.m425a(i);
            if (loaderManager3 == null) {
                if (f232a) {
                    Log.v("LoaderManager", "  Making last loader inactive: " + loaderManager2);
                }
                loaderManager2.f220d.m486r();
                this.f234c.m429b(i, loaderManager2);
            } else if (loaderManager2.f221e) {
                if (f232a) {
                    Log.v("LoaderManager", "  Removing last inactive loader: " + loaderManager2);
                }
                loaderManager3.f222f = false;
                loaderManager3.m143f();
                loaderManager2.f220d.m486r();
                this.f234c.m429b(i, loaderManager2);
            } else if (loaderManager2.f224h) {
                if (loaderManager2.f230n != null) {
                    if (f232a) {
                        Log.v("LoaderManager", "  Removing pending loader: " + loaderManager2.f230n);
                    }
                    loaderManager2.f230n.m143f();
                    loaderManager2.f230n = null;
                }
                if (f232a) {
                    Log.v("LoaderManager", "  Enqueuing as new pending loader");
                }
                loaderManager2.f230n = m147c(i, bundle, loaderManager);
                return loaderManager2.f230n.f220d;
            } else {
                if (f232a) {
                    Log.v("LoaderManager", "  Current loader is stopped; replacing");
                }
                this.f233b.m429b(i, null);
                loaderManager2.m143f();
            }
        }
        return m148d(i, bundle, loaderManager).f220d;
    }

    void m155b() {
        if (f232a) {
            Log.v("LoaderManager", "Starting in " + this);
        }
        if (this.f237f) {
            Throwable runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w("LoaderManager", "Called doStart when already started: " + this, runtimeException);
            return;
        }
        this.f237f = true;
        for (int b = this.f233b.m427b() - 1; b >= 0; b--) {
            ((LoaderManager) this.f233b.m433e(b)).m135a();
        }
    }

    void m156c() {
        if (f232a) {
            Log.v("LoaderManager", "Stopping in " + this);
        }
        if (this.f237f) {
            for (int b = this.f233b.m427b() - 1; b >= 0; b--) {
                ((LoaderManager) this.f233b.m433e(b)).m142e();
            }
            this.f237f = false;
            return;
        }
        Throwable runtimeException = new RuntimeException("here");
        runtimeException.fillInStackTrace();
        Log.w("LoaderManager", "Called doStop when not started: " + this, runtimeException);
    }

    void m157d() {
        if (f232a) {
            Log.v("LoaderManager", "Retaining in " + this);
        }
        if (this.f237f) {
            this.f238g = true;
            this.f237f = false;
            for (int b = this.f233b.m427b() - 1; b >= 0; b--) {
                ((LoaderManager) this.f233b.m433e(b)).m138b();
            }
            return;
        }
        Throwable runtimeException = new RuntimeException("here");
        runtimeException.fillInStackTrace();
        Log.w("LoaderManager", "Called doRetain when not started: " + this, runtimeException);
    }

    void m158e() {
        if (this.f238g) {
            if (f232a) {
                Log.v("LoaderManager", "Finished Retaining in " + this);
            }
            this.f238g = false;
            for (int b = this.f233b.m427b() - 1; b >= 0; b--) {
                ((LoaderManager) this.f233b.m433e(b)).m140c();
            }
        }
    }

    void m159f() {
        for (int b = this.f233b.m427b() - 1; b >= 0; b--) {
            ((LoaderManager) this.f233b.m433e(b)).f227k = true;
        }
    }

    void m160g() {
        for (int b = this.f233b.m427b() - 1; b >= 0; b--) {
            ((LoaderManager) this.f233b.m433e(b)).m141d();
        }
    }

    void m161h() {
        int b;
        if (!this.f238g) {
            if (f232a) {
                Log.v("LoaderManager", "Destroying Active in " + this);
            }
            for (b = this.f233b.m427b() - 1; b >= 0; b--) {
                ((LoaderManager) this.f233b.m433e(b)).m143f();
            }
            this.f233b.m430c();
        }
        if (f232a) {
            Log.v("LoaderManager", "Destroying Inactive in " + this);
        }
        for (b = this.f234c.m427b() - 1; b >= 0; b--) {
            ((LoaderManager) this.f234c.m433e(b)).m143f();
        }
        this.f234c.m430c();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("LoaderManager{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" in ");
        DebugUtils.m419a(this.f236e, stringBuilder);
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }
}
