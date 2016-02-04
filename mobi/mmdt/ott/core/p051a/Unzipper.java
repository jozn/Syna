package mobi.mmdt.ott.core.p051a;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

/* renamed from: mobi.mmdt.ott.core.a.o */
public class Unzipper {
    private static String f3533a;

    static {
        f3533a = Unzipper.class.getSimpleName();
    }

    public static void m4490a(InputStream inputStream, File file) throws ZipException, IOException {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            String str = file.getAbsolutePath() + "/";
            byte[] bArr = new byte[4096];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    zipInputStream.close();
                    Log.i(f3533a, "COMPLETED in " + ((System.currentTimeMillis() - currentTimeMillis) / 1000) + " seconds.");
                    return;
                } else if (nextEntry.isDirectory()) {
                    File file2 = new File(str, nextEntry.getName());
                    if (!file2.exists()) {
                        file2.mkdir();
                    }
                    Log.i(f3533a, "[DIR] " + nextEntry.getName());
                } else {
                    FileOutputStream fileOutputStream = new FileOutputStream(new StringBuilder(String.valueOf(str)).append(nextEntry.getName()).toString());
                    while (true) {
                        int read = zipInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.close();
                    Log.i(f3533a, "[FILE] " + nextEntry.getName());
                }
            }
        } catch (Exception e) {
            Log.e(f3533a, "FAILED");
        }
    }
}
