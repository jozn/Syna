package com.mmdt.syna.view.more.credit;

/* renamed from: com.mmdt.syna.view.more.credit.z */
class CreditsActivity implements Runnable {
    final /* synthetic */ CreditsActivity f2619a;

    CreditsActivity(CreditsActivity creditsActivity) {
        this.f2619a = creditsActivity;
    }

    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.f2619a.runOnUiThread(new aa(this));
    }
}
