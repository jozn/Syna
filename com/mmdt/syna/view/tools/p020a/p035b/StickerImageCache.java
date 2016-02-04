package com.mmdt.syna.view.tools.p020a.p035b;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import mobi.mmdt.p041a.HasVersion;

/* renamed from: com.mmdt.syna.view.tools.a.b.a */
public class StickerImageCache {
    private LruCache<String, StickerImageHolder> f3114a;

    /* renamed from: com.mmdt.syna.view.tools.a.b.a.a */
    public static class StickerImageCache extends Fragment {
        private Object f3113a;

        public Object m3950a() {
            return this.f3113a;
        }

        public void m3951a(Object obj) {
            this.f3113a = obj;
        }

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setRetainInstance(true);
        }
    }

    private StickerImageCache(float f) {
        m3956b(f);
    }

    public static int m3952a(float f) {
        if (f >= 0.05f && f <= 0.8f) {
            return Math.round((((float) Runtime.getRuntime().maxMemory()) * f) / 1024.0f);
        }
        throw new IllegalArgumentException("setMemCacheSizePercent - percent must be between 0.05 and 0.8 (inclusive)");
    }

    @TargetApi(12)
    public static int m3953a(Bitmap bitmap) {
        return HasVersion.m4099b() ? bitmap.getByteCount() : bitmap.getRowBytes() * bitmap.getHeight();
    }

    public static StickerImageCache m3954a(FragmentManager fragmentManager) {
        StickerImageCache stickerImageCache = (StickerImageCache) fragmentManager.findFragmentByTag("StickerImageCache");
        if (stickerImageCache != null) {
            return stickerImageCache;
        }
        Fragment stickerImageCache2 = new StickerImageCache();
        fragmentManager.beginTransaction().add(stickerImageCache2, "StickerImageCache").commitAllowingStateLoss();
        return stickerImageCache2;
    }

    public static StickerImageCache m3955a(FragmentManager fragmentManager, float f) {
        StickerImageCache a = StickerImageCache.m3954a(fragmentManager);
        StickerImageCache stickerImageCache = (StickerImageCache) a.m3950a();
        if (stickerImageCache != null) {
            return stickerImageCache;
        }
        stickerImageCache = new StickerImageCache(f);
        a.m3951a(stickerImageCache);
        return stickerImageCache;
    }

    private void m3956b(float f) {
        int a = StickerImageCache.m3952a(f);
        Log.d("StickerImageCache", "Memory cache created (size = " + a + ")");
        this.f3114a = new StickerImageCache(this, a);
    }

    public StickerImageHolder m3957a(String str) {
        if (this.f3114a != null) {
            StickerImageHolder stickerImageHolder = (StickerImageHolder) this.f3114a.get(str);
            if (stickerImageHolder != null) {
                return stickerImageHolder;
            }
        }
        return null;
    }

    public void m3958a(String str, StickerImageHolder stickerImageHolder) {
        if (str != null && stickerImageHolder != null && this.f3114a != null && this.f3114a.get(str) == null) {
            this.f3114a.put(str, stickerImageHolder);
        }
    }
}
