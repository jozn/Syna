package com.mmdt.syna.view.more.settings.profile;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.net.MalformedURLException;
import mobi.mmdt.ott.core.logic.p055d.VCardProfile;
import mobi.mmdt.ott.core.logic.p055d.p057a.IXmppException;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.core.p051a.Statics;

/* compiled from: ProfileHandler */
public class ad {
    private Context f2718a;

    public ad(Context context) {
        this.f2718a = context;
    }

    public Bitmap m3628a(String str) {
        return BitmapFactory.decodeFile(str);
    }

    public Uri m3629a() {
        return AppSettings.m4867a(this.f2718a).m4887e();
    }

    public String m3630a(Context context, Uri uri) {
        Throwable th;
        Cursor query;
        try {
            query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
                query.moveToFirst();
                String string = query.getString(columnIndexOrThrow);
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
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public boolean m3631a(VCardProfile vCardProfile) throws IXmppException, MalformedURLException {
        return Statics.m4460a(this.f2718a, vCardProfile);
    }

    public String m3632b() {
        return AppSettings.m4867a(this.f2718a).m4884d();
    }
}
