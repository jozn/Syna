package mobi.mmdt.ott.core.logic.p055d;

import android.net.Uri;
import mobi.mmdt.ott.core.model.database.p065f.Messages;

/* renamed from: mobi.mmdt.ott.core.logic.d.b */
class MessageManager implements Runnable {
    final /* synthetic */ MessageManager f3700a;
    private final /* synthetic */ String f3701b;

    MessageManager(MessageManager messageManager, String str) {
        this.f3700a = messageManager;
        this.f3701b = str;
    }

    public void run() {
        Uri a = Messages.m5098a(this.f3700a.f3697b, this.f3701b);
        if (a != null) {
            Messages.m5094a(this.f3700a.f3697b, a, 0);
        }
    }
}
