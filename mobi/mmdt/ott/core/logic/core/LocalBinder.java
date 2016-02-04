package mobi.mmdt.ott.core.logic.core;

import android.os.Binder;
import java.lang.ref.WeakReference;

/* renamed from: mobi.mmdt.ott.core.logic.core.i */
public class LocalBinder<S> extends Binder {
    private final WeakReference<S> f3679a;

    public LocalBinder(S s) {
        this.f3679a = new WeakReference(s);
    }
}
