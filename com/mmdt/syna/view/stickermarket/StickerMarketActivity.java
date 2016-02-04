package com.mmdt.syna.view.stickermarket;

import android.content.pm.PackageManager.NameNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.p043b.p044a.AmirWebserviceException;
import mobi.mmdt.ott.p043b.p044a.p045a.StickerWebservices;
import org.p074b.JSONException;

/* renamed from: com.mmdt.syna.view.stickermarket.n */
class StickerMarketActivity implements Runnable {
    final /* synthetic */ StickerMarketActivity f3058a;

    StickerMarketActivity(StickerMarketActivity stickerMarketActivity) {
        this.f3058a = stickerMarketActivity;
    }

    public void run() {
        try {
            StickerWebservices.m4305a(this.f3058a.getApplicationContext(), AppSettings.m4867a(this.f3058a.getApplicationContext()).m4915y(), AppSettings.m4867a(this.f3058a.getApplicationContext()).m4897j(), AppSettings.m4867a(this.f3058a.getApplicationContext()).m4901l(), AppSettings.m4867a(this.f3058a.getApplicationContext()).m4889f());
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (AmirWebserviceException e4) {
            e4.printStackTrace();
        } catch (NumberFormatException e5) {
            e5.printStackTrace();
        } catch (GeneralSecurityException e6) {
            e6.printStackTrace();
        }
    }
}
