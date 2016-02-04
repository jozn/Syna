package mobi.mmdt.p041a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ThumbnailUtils;

/* renamed from: mobi.mmdt.a.g */
public class ThumbnailMaker {
    public static synchronized Bitmap m4108a(Bitmap bitmap) {
        Bitmap extractThumbnail;
        synchronized (ThumbnailMaker.class) {
            extractThumbnail = ThumbnailUtils.extractThumbnail(bitmap, 120, 120);
        }
        return extractThumbnail;
    }

    public static synchronized Bitmap m4109a(String str) {
        Bitmap createVideoThumbnail;
        synchronized (ThumbnailMaker.class) {
            createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(str, 3);
        }
        return createVideoThumbnail;
    }

    public static synchronized Bitmap m4110b(String str) {
        Bitmap a;
        synchronized (ThumbnailMaker.class) {
            Options options = new Options();
            options.inTempStorage = new byte[24576];
            options.inJustDecodeBounds = false;
            options.inSampleSize = 2;
            a = ThumbnailMaker.m4108a(BitmapFactory.decodeFile(str, options));
        }
        return a;
    }
}
