package mobi.mmdt.ott.core.model.database.p060a;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import mobi.mmdt.p041a.HasVersion;

/* renamed from: mobi.mmdt.ott.core.model.database.a.c */
public interface AndroidContactsQuery {
    public static final Uri f3887a;
    public static final Uri f3888b;
    @SuppressLint({"InlinedApi"})
    public static final String f3889c;
    @SuppressLint({"InlinedApi"})
    public static final String f3890d;
    @SuppressLint({"InlinedApi"})
    public static final String[] f3891e;

    static {
        f3887a = Contacts.CONTENT_URI;
        f3888b = Contacts.CONTENT_FILTER_URI;
        f3889c = new StringBuilder(String.valueOf(HasVersion.m4098a() ? "display_name" : "display_name")).append("<>''").append(" AND ").append("in_visible_group").append("=1").toString();
        f3890d = HasVersion.m4098a() ? "sort_key" : "display_name";
        String[] strArr = new String[6];
        strArr[0] = "_id";
        strArr[1] = "lookup";
        strArr[2] = HasVersion.m4098a() ? "display_name" : "display_name";
        strArr[3] = HasVersion.m4098a() ? "photo_thumb_uri" : "_id";
        strArr[4] = f3890d;
        strArr[5] = "starred";
        f3891e = strArr;
    }
}
