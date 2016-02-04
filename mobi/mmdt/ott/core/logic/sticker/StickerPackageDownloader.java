package mobi.mmdt.ott.core.logic.sticker;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import mobi.mmdt.ott.core.UiRequests;
import mobi.mmdt.ott.core.logic.p034c.LocalTransmitterListener;
import mobi.mmdt.ott.core.logic.p034c.StickerDownloader;
import mobi.mmdt.ott.core.logic.p055d.p057a.SDCardNotAvailableException;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.core.p051a.FileManager;
import mobi.mmdt.ott.p043b.p044a.AmirWebserviceException;
import mobi.mmdt.ott.p043b.p044a.p045a.StickerWebservices;
import mobi.mmdt.ott.p043b.p044a.p045a.p046a.StickerPackageAddressResult;
import org.p074b.JSONException;

/* renamed from: mobi.mmdt.ott.core.logic.sticker.g */
public class StickerPackageDownloader {
    private Context f3836a;
    private LocalTransmitterListener f3837b;
    private String f3838c;
    private String f3839d;

    public StickerPackageDownloader(Context context, String str, LocalTransmitterListener localTransmitterListener) {
        this.f3836a = context;
        this.f3837b = localTransmitterListener;
        this.f3839d = str;
        this.f3838c = FileManager.m4437a(this.f3836a).m4449h(new StringBuilder(String.valueOf(str)).append(".zip").toString());
    }

    public void m4865a() throws NameNotFoundException, JSONException, IOException, AmirWebserviceException, SDCardNotAvailableException, NumberFormatException, GeneralSecurityException {
        String a;
        StickerPackageAddressResult b = StickerWebservices.m4305a(this.f3836a, UiRequests.m4500b(), AppSettings.m4867a(this.f3836a).m4897j(), AppSettings.m4867a(this.f3836a).m4901l(), AppSettings.m4867a(this.f3836a).m4889f()).m4313b(this.f3839d);
        switch (this.f3836a.getResources().getDisplayMetrics().densityDpi) {
            case 120:
                a = b.m4289a().m4295a();
                break;
            case 160:
                a = b.m4289a().m4296b();
                break;
            case 240:
                a = b.m4289a().m4299e();
                break;
            case 320:
                a = b.m4289a().m4300f();
                break;
            case 480:
                a = b.m4289a().m4298d();
                break;
            case 640:
                a = b.m4289a().m4297c();
                break;
            default:
                a = b.m4289a().m4299e();
                break;
        }
        if (a != null) {
            int parseInt = Integer.parseInt(this.f3839d);
            StickerDownloader.m4646a(this.f3836a).m4648a(parseInt, a, this.f3838c, this.f3837b);
            StickerDownloader.m4646a(this.f3836a).m4649b(parseInt).m4645a();
        }
    }

    public String m4866b() {
        return this.f3838c;
    }
}
