package mobi.mmdt.p041a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ThumbnailUtils;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.linphone.core.Privacy;

/* renamed from: mobi.mmdt.a.e */
public class ImageTools {
    public static Bitmap m4103a(Bitmap bitmap, int i, int i2) {
        return ThumbnailUtils.extractThumbnail(bitmap, i, i2);
    }

    public static Bitmap m4104a(String str, int i) {
        Options options = new Options();
        options.inTempStorage = new byte[Privacy.DEFAULT];
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        if (decodeFile == null) {
            return null;
        }
        int width = decodeFile.getWidth();
        int height = decodeFile.getHeight();
        float f = (float) (width * height);
        if (f > ((float) i)) {
            f = ((float) i) / f;
            width = (int) (((float) width) * f);
            height = (int) (((float) height) * f);
        }
        return ImageTools.m4103a(decodeFile, width, height);
    }

    public static byte[] m4105a(Bitmap bitmap, int i) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap m4106b(Bitmap bitmap, int i) {
        Options options = new Options();
        options.inTempStorage = new byte[Privacy.DEFAULT];
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = (float) (width * height);
        if (f > ((float) i)) {
            f = ((float) i) / f;
            width = (int) (((float) width) * f);
            height = (int) (((float) height) * f);
        }
        return ImageTools.m4103a(bitmap, width, height);
    }
}
