package com.mmdt.syna.view.tools.p020a.p021a;

import android.graphics.Bitmap;
import android.util.LruCache;

/* renamed from: com.mmdt.syna.view.tools.a.a.b */
class ImageCache extends LruCache<String, Bitmap> {
    final /* synthetic */ ImageCache f3108a;

    ImageCache(ImageCache imageCache, int i) {
        this.f3108a = imageCache;
        super(i);
    }

    protected int m3943a(String str, Bitmap bitmap) {
        int a = ImageCache.m3937a(bitmap) / 1024;
        return a == 0 ? 1 : a;
    }

    protected /* synthetic */ int sizeOf(Object obj, Object obj2) {
        return m3943a((String) obj, (Bitmap) obj2);
    }
}
