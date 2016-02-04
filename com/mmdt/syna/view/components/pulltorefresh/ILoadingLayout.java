package com.mmdt.syna.view.components.pulltorefresh;

import android.graphics.drawable.Drawable;

/* renamed from: com.mmdt.syna.view.components.pulltorefresh.a */
public interface ILoadingLayout {
    void setLastUpdatedLabel(CharSequence charSequence);

    void setLoadingDrawable(Drawable drawable);

    void setPullLabel(CharSequence charSequence);

    void setRefreshingLabel(CharSequence charSequence);

    void setReleaseLabel(CharSequence charSequence);
}
