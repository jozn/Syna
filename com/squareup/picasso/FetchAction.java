package com.squareup.picasso;

import android.graphics.Bitmap;
import com.squareup.picasso.Picasso.LoadedFrom;

class FetchAction extends Action<Object> {
    private Callback callback;
    private final Object target;

    FetchAction(Picasso picasso, Request request, int i, int i2, Object obj, String str, Callback callback) {
        super(picasso, null, request, i, i2, 0, null, str, obj, false);
        this.target = new Object();
        this.callback = callback;
    }

    void cancel() {
        super.cancel();
        this.callback = null;
    }

    void complete(Bitmap bitmap, LoadedFrom loadedFrom) {
        if (this.callback != null) {
            this.callback.onSuccess();
        }
    }

    void error() {
        if (this.callback != null) {
            this.callback.onError();
        }
    }

    Object getTarget() {
        return this.target;
    }
}
