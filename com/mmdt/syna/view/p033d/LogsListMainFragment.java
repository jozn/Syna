package com.mmdt.syna.view.p033d;

import mobi.mmdt.ott.core.model.database.p064e.Logs;

/* renamed from: com.mmdt.syna.view.d.l */
class LogsListMainFragment implements Runnable {
    final /* synthetic */ LogsListMainFragment f2388a;

    LogsListMainFragment(LogsListMainFragment logsListMainFragment) {
        this.f2388a = logsListMainFragment;
    }

    public void run() {
        Logs.m5070b(this.f2388a.f2386a.m91h());
    }
}
