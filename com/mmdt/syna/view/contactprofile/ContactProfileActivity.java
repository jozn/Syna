package com.mmdt.syna.view.contactprofile;

import android.net.Uri;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.core.p051a.CountryTools;
import mobi.mmdt.ott.p043b.p044a.AmirWebservice;

/* renamed from: com.mmdt.syna.view.contactprofile.d */
class ContactProfileActivity implements Runnable {
    final /* synthetic */ ContactProfileActivity f1975a;
    private final /* synthetic */ Uri f1976b;

    ContactProfileActivity(ContactProfileActivity contactProfileActivity, Uri uri) {
        this.f1975a = contactProfileActivity;
        this.f1976b = uri;
    }

    public void run() {
        try {
            if (!SynaContacts.m5004a(this.f1975a.getApplicationContext(), this.f1975a.f1956s.m4976d())) {
                if (AppSettings.m4867a(this.f1975a.getApplicationContext()).m4889f().equals("fa")) {
                    AmirWebservice.m4314a(this.f1975a.getApplicationContext(), AppSettings.m4867a(this.f1975a.getApplicationContext()).m4914x(), AppSettings.m4867a(this.f1975a.getApplicationContext()).m4897j(), AppSettings.m4867a(this.f1975a.getApplicationContext()).m4901l()).m4316a("fa", CountryTools.m4416a().m4425c(this.f1975a.getApplicationContext(), this.f1975a.f1956s.m4976d()));
                } else {
                    AmirWebservice.m4314a(this.f1975a.getApplicationContext(), AppSettings.m4867a(this.f1975a.getApplicationContext()).m4914x(), AppSettings.m4867a(this.f1975a.getApplicationContext()).m4897j(), AppSettings.m4867a(this.f1975a.getApplicationContext()).m4901l()).m4316a("en", CountryTools.m4416a().m4425c(this.f1975a.getApplicationContext(), this.f1975a.f1956s.m4976d()));
                }
                SynaContacts.m5011c(this.f1975a.getApplicationContext(), this.f1976b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
