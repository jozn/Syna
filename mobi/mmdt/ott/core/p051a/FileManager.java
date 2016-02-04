package mobi.mmdt.ott.core.p051a;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import mobi.mmdt.ott.p043b.p050d.WebservicesTools;
import mobi.mmdt.p041a.FileUtility;
import mobi.mmdt.p041a.ThumbnailMaker;

/* renamed from: mobi.mmdt.ott.core.a.d */
public class FileManager {
    private static final String f3502a;
    private static final String f3503b;
    private static final String f3504c;
    private static final String f3505d;
    private static final String f3506e;
    private static final String f3507f;
    private static final String f3508g;
    private static final String f3509h;
    private static final String f3510i;
    private static final String f3511j;
    private static FileManager f3512k;
    private Context f3513l;

    static {
        f3502a = Environment.getExternalStorageDirectory() + File.separator + "SYNA" + File.separator + "Media";
        f3503b = f3502a + File.separator + "Syna Videos";
        f3504c = f3502a + File.separator + ".PushToTalks";
        f3505d = f3502a + File.separator + "Syna Images";
        f3506e = f3502a + File.separator + ".Thumbnails";
        f3507f = f3502a + File.separator + "Others";
        f3508g = f3502a + File.separator + ".Syna Avatars";
        f3509h = f3502a + File.separator + ".Syna Group Avatars";
        f3510i = f3502a + File.separator + ".StickerFiles";
        f3511j = f3502a + File.separator + ".Temp";
    }

    private FileManager(Context context) {
        this.f3513l = context;
    }

    public static String m4436a() {
        return f3502a;
    }

    public static FileManager m4437a(Context context) {
        if (f3512k == null) {
            f3512k = new FileManager(context);
            Thread thread = new Thread(new FileManager());
            thread.setPriority(1);
            thread.start();
        }
        return f3512k;
    }

    public String m4439a(String str) throws IOException {
        String str2 = f3508g;
        if (!new File(str2).exists()) {
            new File(str2).mkdirs();
        }
        return new StringBuilder(String.valueOf(str2)).append(File.separator).append("Avatar_").append(str).toString();
    }

    public String m4440a(String str, String str2) {
        Bitmap b;
        try {
            if (FileUtility.m4097e(str2)) {
                b = ThumbnailMaker.m4110b(str2);
            } else if (!FileUtility.m4093a(str2)) {
                return null;
            } else {
                b = ThumbnailMaker.m4109a(str2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            b = null;
        }
        if (b == null) {
            return null;
        }
        try {
            String g = m4448g(new StringBuilder(String.valueOf(str.split("\\.")[str.split("\\.").length - 2])).append(".png").toString());
            OutputStream fileOutputStream = new FileOutputStream(g);
            b.compress(CompressFormat.PNG, 90, fileOutputStream);
            fileOutputStream.close();
            return g;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean m4441a(String str, byte[] bArr) throws IOException {
        boolean z = true;
        File file = new File(str);
        if (!file.exists()) {
            z = file.createNewFile();
        }
        if (z) {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            byte[] bArr2 = new byte[10240];
            while (true) {
                int read = byteArrayInputStream.read(bArr2);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr2, 0, read);
            }
            fileOutputStream.close();
            m4451j(str);
        }
        return z;
    }

    public String m4442b(String str) throws IOException {
        String str2 = f3509h;
        if (!new File(str2).exists()) {
            new File(str2).mkdirs();
        }
        return new StringBuilder(String.valueOf(str2)).append(File.separator).append("Group_Avatar_").append(str).toString();
    }

    public String m4443b(String str, String str2) {
        if (!new File(f3510i).exists()) {
            new File(f3510i).mkdirs();
        }
        String a = WebservicesTools.m4392a(this.f3513l);
        if (!new File(f3510i + File.separator + str + File.separator + str + "_" + a).exists()) {
            new File(f3510i + File.separator + str + File.separator + str + "_" + a).mkdirs();
        }
        return new File(f3510i + File.separator + str + File.separator + str + "_" + a + File.separator + str2).getPath();
    }

    public String m4444c(String str) throws IOException {
        String str2 = f3503b;
        if (!new File(str2).exists()) {
            new File(str2).mkdirs();
        }
        return new StringBuilder(String.valueOf(str2)).append(File.separator).append("Video_").append(System.currentTimeMillis()).append("_").append(str).toString();
    }

    public String m4445d(String str) throws IOException {
        String str2 = f3507f;
        if (!new File(str2).exists()) {
            new File(str2).mkdirs();
        }
        return new StringBuilder(String.valueOf(str2)).append(File.separator).append("Other_").append(System.currentTimeMillis()).append("_").append(str).toString();
    }

    public String m4446e(String str) throws IOException {
        String str2 = f3504c;
        if (!new File(str2).exists()) {
            new File(str2).mkdirs();
        }
        return new StringBuilder(String.valueOf(str2)).append(File.separator).append("PushToTalk_").append(System.currentTimeMillis()).append("_").append(str).toString();
    }

    public String m4447f(String str) throws IOException {
        String str2 = f3505d;
        if (!new File(str2).exists()) {
            new File(str2).mkdirs();
        }
        return new StringBuilder(String.valueOf(str2)).append(File.separator).append("Image_").append(System.currentTimeMillis()).append("_").append(str).toString();
    }

    public String m4448g(String str) throws IOException {
        String str2 = f3506e;
        if (!new File(str2).exists()) {
            new File(str2).mkdirs();
        }
        return new StringBuilder(String.valueOf(str2)).append(File.separator).append("Thumbnail_").append(System.currentTimeMillis()).append("_").append(str).toString();
    }

    public String m4449h(String str) {
        if (!new File(f3511j).exists()) {
            new File(f3511j).mkdirs();
        }
        return f3511j + File.separator + str;
    }

    public String m4450i(String str) {
        if (!new File(f3510i).exists()) {
            new File(f3510i).mkdirs();
        }
        File file = new File(f3510i + File.separator + str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getPath();
    }

    public void m4451j(String str) {
        this.f3513l.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(f3502a))));
        this.f3513l.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(f3505d))));
        this.f3513l.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(f3507f))));
        this.f3513l.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(f3503b))));
        if (str != null) {
            this.f3513l.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(str))));
        }
    }
}
