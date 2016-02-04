package mobi.mmdt.p041a;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.StatFs;

/* renamed from: mobi.mmdt.a.f */
public class SDCardTools {
    @SuppressLint({"NewApi"})
    public static long m4107a() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        if (HasVersion.m4100c()) {
            return statFs.getAvailableBytes() / 1048576;
        }
        return (long) ((((double) statFs.getBlockSize()) / 1048576.0d) * ((double) statFs.getAvailableBlocks()));
    }
}
