package mobi.mmdt.ott.core.logic.message.p032a;

import android.net.Uri;
import java.util.TimerTask;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;
import mobi.mmdt.ott.core.model.database.p062c.Files;
import mobi.mmdt.ott.core.model.database.p065f.Messages;

/* renamed from: mobi.mmdt.ott.core.logic.message.a.g */
class Player extends TimerTask {
    final /* synthetic */ Player f3751a;

    Player(Player player) {
        this.f3751a = player;
    }

    public void run() {
        try {
            Uri c = Messages.m5112c(this.f3751a.e, Uri.parse(DatabaseContentProvider.m4929c(this.f3751a.e) + "/" + this.f3751a.b));
            if (this.f3751a.f3746a != null) {
                Files.m5027a(this.f3751a.e, c, this.f3751a.f3746a.getCurrentPosition());
            }
            if (this.f3751a.c != null) {
                this.f3751a.c.m3063b(this.f3751a.b, this.f3751a.f3746a.getCurrentPosition());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
