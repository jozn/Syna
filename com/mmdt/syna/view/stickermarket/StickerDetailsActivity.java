package com.mmdt.syna.view.stickermarket;

import android.content.pm.PackageManager.NameNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import mobi.mmdt.ott.core.logic.p055d.p057a.SDCardNotAvailableException;
import mobi.mmdt.ott.p043b.p044a.AmirWebserviceException;
import org.p074b.JSONException;

/* renamed from: com.mmdt.syna.view.stickermarket.e */
class StickerDetailsActivity implements Runnable {
    final /* synthetic */ StickerDetailsActivity f3037a;

    StickerDetailsActivity(StickerDetailsActivity stickerDetailsActivity) {
        this.f3037a = stickerDetailsActivity;
    }

    public void run() {
        try {
            this.f3037a.f2998q.m4865a();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            this.f3037a.f2996o.m3905c();
        } catch (NameNotFoundException e2) {
            e2.printStackTrace();
            this.f3037a.f2996o.m3905c();
        } catch (JSONException e3) {
            e3.printStackTrace();
            this.f3037a.f2996o.m3905c();
        } catch (IOException e4) {
            e4.printStackTrace();
            this.f3037a.f2996o.m3905c();
        } catch (AmirWebserviceException e5) {
            e5.printStackTrace();
            this.f3037a.f2996o.m3905c();
        } catch (SDCardNotAvailableException e6) {
            e6.printStackTrace();
            this.f3037a.f2996o.m3905c();
        } catch (NumberFormatException e7) {
            e7.printStackTrace();
            this.f3037a.f2996o.m3905c();
        }
    }
}
