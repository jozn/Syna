package com.mmdt.syna.view.components.pulltorefresh;

import android.graphics.drawable.Drawable;
import com.mmdt.syna.view.components.pulltorefresh.internal.LoadingLayout;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: com.mmdt.syna.view.components.pulltorefresh.b */
public class LoadingLayoutProxy implements ILoadingLayout {
    private final HashSet<LoadingLayout> f1919a;

    LoadingLayoutProxy() {
        this.f1919a = new HashSet();
    }

    public void m2865a(LoadingLayout loadingLayout) {
        if (loadingLayout != null) {
            this.f1919a.add(loadingLayout);
        }
    }

    public void setLastUpdatedLabel(CharSequence charSequence) {
        Iterator it = this.f1919a.iterator();
        while (it.hasNext()) {
            ((LoadingLayout) it.next()).setLastUpdatedLabel(charSequence);
        }
    }

    public void setLoadingDrawable(Drawable drawable) {
        Iterator it = this.f1919a.iterator();
        while (it.hasNext()) {
            ((LoadingLayout) it.next()).setLoadingDrawable(drawable);
        }
    }

    public void setPullLabel(CharSequence charSequence) {
        Iterator it = this.f1919a.iterator();
        while (it.hasNext()) {
            ((LoadingLayout) it.next()).setPullLabel(charSequence);
        }
    }

    public void setRefreshingLabel(CharSequence charSequence) {
        Iterator it = this.f1919a.iterator();
        while (it.hasNext()) {
            ((LoadingLayout) it.next()).setRefreshingLabel(charSequence);
        }
    }

    public void setReleaseLabel(CharSequence charSequence) {
        Iterator it = this.f1919a.iterator();
        while (it.hasNext()) {
            ((LoadingLayout) it.next()).setReleaseLabel(charSequence);
        }
    }
}
