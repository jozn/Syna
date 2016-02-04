package com.squareup.picasso;

import android.graphics.Bitmap;

public interface Cache {
    public static final Cache NONE;

    /* renamed from: com.squareup.picasso.Cache.1 */
    final class C01201 implements Cache {
        C01201() {
        }

        public void clear() {
        }

        public void clearKeyUri(String str) {
        }

        public Bitmap get(String str) {
            return null;
        }

        public int maxSize() {
            return 0;
        }

        public void set(String str, Bitmap bitmap) {
        }

        public int size() {
            return 0;
        }
    }

    static {
        NONE = new C01201();
    }

    void clear();

    void clearKeyUri(String str);

    Bitmap get(String str);

    int maxSize();

    void set(String str, Bitmap bitmap);

    int size();
}
