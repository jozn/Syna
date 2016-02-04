package com.mmdt.syna.view.selectcontactmessage;

import android.view.inputmethod.InputMethodManager;

/* renamed from: com.mmdt.syna.view.selectcontactmessage.q */
class NewMessageContactListActivity implements Runnable {
    final /* synthetic */ NewMessageContactListActivity f2959a;

    NewMessageContactListActivity(NewMessageContactListActivity newMessageContactListActivity) {
        this.f2959a = newMessageContactListActivity;
    }

    public void run() {
        ((InputMethodManager) this.f2959a.getSystemService("input_method")).hideSoftInputFromWindow(this.f2959a.f2915u.getWindowToken(), 0);
    }
}
