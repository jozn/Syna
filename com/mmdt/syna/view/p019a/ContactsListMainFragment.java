package com.mmdt.syna.view.p019a;

import android.accounts.NetworkErrorException;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.core.p051a.Statics;
import org.p074b.JSONException;

/* renamed from: com.mmdt.syna.view.a.n */
class ContactsListMainFragment implements Runnable {
    final /* synthetic */ ContactsListMainFragment f1644a;

    ContactsListMainFragment(ContactsListMainFragment contactsListMainFragment) {
        this.f1644a = contactsListMainFragment;
    }

    public void run() {
        this.f1644a.aa = true;
        String j = AppSettings.m4867a(this.f1644a.m91h()).m4897j();
        String l = AppSettings.m4867a(this.f1644a.m91h()).m4901l();
        if (!(j == null || l == null)) {
            try {
                Statics.m4467g(this.f1644a.m91h());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Statics.m4461b(this.f1644a.m91h());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                Statics.m4465e(this.f1644a.m91h());
            } catch (GeneralSecurityException e3) {
                e3.printStackTrace();
                this.f1644a.m91h().runOnUiThread(new ContactsListMainFragment(this));
            } catch (JSONException e4) {
                e4.printStackTrace();
                this.f1644a.m91h().runOnUiThread(new ContactsListMainFragment(this));
            } catch (IOException e5) {
                e5.printStackTrace();
                this.f1644a.m91h().runOnUiThread(new ContactsListMainFragment(this));
            } catch (NetworkErrorException e6) {
                e6.printStackTrace();
                this.f1644a.m91h().runOnUiThread(new ContactsListMainFragment(this));
            } catch (NameNotFoundException e7) {
                e7.printStackTrace();
            }
        }
        try {
            if (this.f1644a.m91h() != null) {
                this.f1644a.m91h().runOnUiThread(new ContactsListMainFragment(this));
            }
        } catch (Exception e22) {
            e22.printStackTrace();
        }
        this.f1644a.aa = false;
    }
}
