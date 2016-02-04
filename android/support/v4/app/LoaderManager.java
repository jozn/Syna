package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.content.Loader;

/* renamed from: android.support.v4.app.z */
public abstract class LoaderManager {

    /* renamed from: android.support.v4.app.z.a */
    public interface LoaderManager<D> {
        Loader<D> m370a(int i, Bundle bundle);

        void m371a(Loader<D> loader);

        void m372a(Loader<D> loader, D d);
    }

    public abstract <D> Loader<D> m144a(int i, Bundle bundle, LoaderManager<D> loaderManager);

    public boolean m145a() {
        return false;
    }

    public abstract <D> Loader<D> m146b(int i, Bundle bundle, LoaderManager<D> loaderManager);
}
