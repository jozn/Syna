package mobi.mmdt.p041a;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.webkit.MimeTypeMap;

/* renamed from: mobi.mmdt.a.c */
public class FileUtility {
    public static String m4092a(Context context, Uri uri) {
        String string;
        Exception e;
        Throwable th;
        Cursor query;
        try {
            query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
                query.moveToFirst();
                string = query.getString(columnIndexOrThrow);
                if (query != null) {
                    query.close();
                }
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    if (query != null) {
                        query.close();
                    }
                    string = uri.getPath();
                    if (query != null) {
                        query.close();
                    }
                    return string;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            e.printStackTrace();
            if (query != null) {
                query.close();
            }
            string = uri.getPath();
            if (query != null) {
                query.close();
            }
            return string;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return string;
    }

    public static boolean m4093a(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if ((fileExtensionFromUrl == null || fileExtensionFromUrl.equals("")) && str.split("\\.").length - 1 > -1) {
            fileExtensionFromUrl = str.split("\\.")[str.split("\\.").length - 1];
        }
        if (fileExtensionFromUrl != null) {
            fileExtensionFromUrl = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
            if (fileExtensionFromUrl != null && fileExtensionFromUrl.startsWith("video/")) {
                return true;
            }
        }
        return false;
    }

    public static boolean m4094b(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if ((fileExtensionFromUrl == null || fileExtensionFromUrl.equals("")) && str.split("\\.").length - 1 > -1) {
            fileExtensionFromUrl = str.split("\\.")[str.split("\\.").length - 1];
        }
        if (fileExtensionFromUrl != null) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
            if (mimeTypeFromExtension != null && mimeTypeFromExtension.startsWith("video/") && (fileExtensionFromUrl.equals("mp4") || fileExtensionFromUrl.equals("3gp"))) {
                return true;
            }
        }
        return false;
    }

    public static String m4095c(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        return ((fileExtensionFromUrl == null || fileExtensionFromUrl.equals("")) && str.split("\\.").length - 1 > -1) ? str.split("\\.")[str.split("\\.").length - 1] : fileExtensionFromUrl;
    }

    public static boolean m4096d(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if ((fileExtensionFromUrl == null || fileExtensionFromUrl.equals("")) && str.split("\\.").length - 1 > -1) {
            fileExtensionFromUrl = str.split("\\.")[str.split("\\.").length - 1];
        }
        if (fileExtensionFromUrl != null) {
            fileExtensionFromUrl = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
            if (fileExtensionFromUrl != null && fileExtensionFromUrl.startsWith("audio/")) {
                return true;
            }
        }
        return false;
    }

    public static boolean m4097e(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if ((fileExtensionFromUrl == null || fileExtensionFromUrl.equals("")) && str.split("\\.").length - 1 > -1) {
            fileExtensionFromUrl = str.split("\\.")[str.split("\\.").length - 1];
        }
        if (fileExtensionFromUrl != null) {
            fileExtensionFromUrl = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
            if (fileExtensionFromUrl != null && fileExtensionFromUrl.startsWith("image/")) {
                return true;
            }
        }
        return false;
    }
}
