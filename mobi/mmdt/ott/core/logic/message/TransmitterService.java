package mobi.mmdt.ott.core.logic.message;

import android.net.Uri;
import java.io.File;
import java.util.Iterator;
import mobi.mmdt.ott.core.logic.p034c.LocalTransmitterListener;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;
import mobi.mmdt.ott.core.model.database.p062c.FileRow;
import mobi.mmdt.ott.core.model.database.p062c.Files;
import mobi.mmdt.ott.core.model.database.p065f.MessageRow;
import mobi.mmdt.ott.core.model.database.p065f.Messages;
import mobi.mmdt.ott.core.p051a.FileManager;
import mobi.mmdt.ott.core.p051a.Statics;
import mobi.mmdt.p041a.FileUtility;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: mobi.mmdt.ott.core.logic.message.x */
class TransmitterService implements LocalTransmitterListener {
    final /* synthetic */ TransmitterService f3810a;

    TransmitterService(TransmitterService transmitterService) {
        this.f3810a = transmitterService;
    }

    public void m4825a(long j) {
        MessageRow messageRow = (MessageRow) this.f3810a.f3736f.get((int) j);
        if (messageRow != null) {
            Messages.m5094a(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4929c(this.f3810a.getApplicationContext()) + "/" + j), 5);
            Files.m5037d(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), 2);
            Files.m5035c(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), 0);
            FileRow c = Files.m5036c(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()));
            this.f3810a.f3737g.put((int) messageRow.m5091h(), c);
            Iterator it = this.f3810a.f3735e.iterator();
            while (it.hasNext()) {
                ((TransmitterListener) it.next()).m4824a(messageRow.m5091h(), 0, c);
            }
        }
    }

    public void m4826a(long j, int i) {
        MessageRow messageRow = (MessageRow) this.f3810a.f3736f.get((int) j);
        if (messageRow != null) {
            Files.m5035c(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), i);
            FileRow fileRow = (FileRow) this.f3810a.f3737g.get((int) messageRow.m5091h());
            Iterator it = this.f3810a.f3735e.iterator();
            while (it.hasNext()) {
                ((TransmitterListener) it.next()).m4824a(messageRow.m5091h(), i, fileRow);
            }
        }
    }

    public void m4827a(long j, Exception exception) {
        exception.printStackTrace();
        MessageRow messageRow = (MessageRow) this.f3810a.f3736f.get((int) j);
        if (messageRow != null) {
            Messages.m5094a(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4929c(this.f3810a.getApplicationContext()) + "/" + j), 1);
            Files.m5037d(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), 4);
            Files.m5035c(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), 0);
            FileRow c = Files.m5036c(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()));
            this.f3810a.f3737g.put((int) messageRow.m5091h(), c);
            Iterator it = this.f3810a.f3735e.iterator();
            while (it.hasNext()) {
                ((TransmitterListener) it.next()).m4824a(messageRow.m5091h(), 0, c);
            }
            this.f3810a.f3737g.remove((int) messageRow.m5091h());
            this.f3810a.f3736f.remove((int) j);
            this.f3810a.f3734d.remove((int) messageRow.m5091h());
            this.f3810a.m4765a();
        }
    }

    public void m4828a(String str, long j) {
        MessageRow messageRow = (MessageRow) this.f3810a.f3736f.get((int) j);
        if (messageRow != null) {
            Files.m5037d(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), 3);
            Files.m5035c(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), 100);
            Messages.m5094a(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4929c(this.f3810a.getApplicationContext()) + "/" + messageRow.m5091h()), 2);
            FileRow c = Files.m5036c(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()));
            if (messageRow.m5087d() == 4 || messageRow.m5087d() == 2) {
                String a = FileManager.m4437a(this.f3810a.getApplicationContext()).m4440a(c.m5025f(), Uri.parse(c.m5023d()).getPath());
                if (a != null) {
                    Files.m5028a(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), Uri.fromFile(new File(a)));
                }
            }
            int i = -1;
            if (FileUtility.m4096d(Uri.parse(c.m5023d()).getPath())) {
                i = Statics.m4452a(this.f3810a.getApplicationContext(), Uri.parse(c.m5023d()).getPath());
            }
            Files.m5038e(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), i);
            Files.m5029a(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), str);
            FileRow c2 = Files.m5036c(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()));
            this.f3810a.f3737g.put((int) messageRow.m5091h(), c2);
            Iterator it = this.f3810a.f3735e.iterator();
            while (it.hasNext()) {
                ((TransmitterListener) it.next()).m4824a(messageRow.m5091h(), 100, c2);
            }
            this.f3810a.f3737g.remove((int) messageRow.m5091h());
            this.f3810a.f3736f.remove((int) j);
            this.f3810a.f3734d.remove((int) messageRow.m5091h());
            this.f3810a.m4765a();
            try {
                switch (messageRow.m5087d()) {
                    case VideoSize.HVGA /*2*/:
                        MessageService.m4799a(this.f3810a.getApplicationContext()).m4812a(new String(messageRow.m5090g()), messageRow.m5086c(), c2.m5022c(), c2.m5024e(), c2.m5025f(), "", messageRow.m5085b(), Uri.parse(DatabaseContentProvider.m4929c(this.f3810a.getApplicationContext()) + "/" + messageRow.m5091h()), messageRow.m5084a(), messageRow.m5089f());
                        return;
                    case Version.API03_CUPCAKE_15 /*3*/:
                        MessageService.m4799a(this.f3810a.getApplicationContext()).m4811a(new String(messageRow.m5090g()), messageRow.m5086c(), c2.m5022c(), c2.m5024e(), c2.m5025f(), i, messageRow.m5085b(), Uri.parse(DatabaseContentProvider.m4929c(this.f3810a.getApplicationContext()) + "/" + messageRow.m5091h()), messageRow.m5084a(), messageRow.m5089f());
                        return;
                    case Version.API04_DONUT_16 /*4*/:
                        MessageService.m4799a(this.f3810a.getApplicationContext()).m4819b(new String(messageRow.m5090g()), messageRow.m5086c(), c2.m5022c(), c2.m5024e(), c2.m5025f(), "", messageRow.m5085b(), Uri.parse(DatabaseContentProvider.m4929c(this.f3810a.getApplicationContext()) + "/" + messageRow.m5091h()), messageRow.m5084a(), messageRow.m5089f());
                        return;
                    default:
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Messages.m5094a(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4929c(this.f3810a.getApplicationContext()) + "/" + messageRow.m5091h()), 5);
            }
            e.printStackTrace();
            Messages.m5094a(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4929c(this.f3810a.getApplicationContext()) + "/" + messageRow.m5091h()), 5);
        }
    }

    public void m4829b(long j) {
        MessageRow messageRow = (MessageRow) this.f3810a.f3736f.get((int) j);
        if (messageRow != null) {
            Files.m5037d(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), 3);
            Files.m5035c(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), 100);
            Messages.m5094a(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4929c(this.f3810a.getApplicationContext()) + "/" + messageRow.m5091h()), 4);
            FileRow c = Files.m5036c(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()));
            if (messageRow.m5087d() == 4 || messageRow.m5087d() == 2) {
                String a = FileManager.m4437a(this.f3810a.getApplicationContext()).m4440a(c.m5025f(), Uri.parse(c.m5023d()).getPath());
                if (a != null) {
                    Files.m5028a(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), Uri.fromFile(new File(a)));
                }
            }
            int i = -1;
            if (FileUtility.m4096d(Uri.parse(c.m5023d()).getPath())) {
                i = Statics.m4452a(this.f3810a.getApplicationContext(), Uri.parse(c.m5023d()).getPath());
            }
            Files.m5038e(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), i);
            c = Files.m5036c(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()));
            this.f3810a.f3737g.put((int) messageRow.m5091h(), c);
            Iterator it = this.f3810a.f3735e.iterator();
            while (it.hasNext()) {
                ((TransmitterListener) it.next()).m4824a(messageRow.m5091h(), 100, c);
            }
            this.f3810a.f3737g.remove((int) messageRow.m5091h());
            this.f3810a.f3736f.remove((int) j);
            this.f3810a.f3734d.remove((int) messageRow.m5091h());
            this.f3810a.m4765a();
        }
    }

    public void m4830c(long j) {
        MessageRow messageRow = (MessageRow) this.f3810a.f3736f.get((int) j);
        if (messageRow != null) {
            Messages.m5094a(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4929c(this.f3810a.getApplicationContext()) + "/" + j), 1);
            Files.m5037d(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), 5);
            Files.m5035c(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()), 0);
            FileRow c = Files.m5036c(this.f3810a.getApplicationContext(), Uri.parse(DatabaseContentProvider.m4930d(this.f3810a.getApplicationContext()) + "/" + messageRow.m5088e()));
            this.f3810a.f3737g.put((int) messageRow.m5091h(), c);
            Iterator it = this.f3810a.f3735e.iterator();
            while (it.hasNext()) {
                ((TransmitterListener) it.next()).m4824a(messageRow.m5091h(), 0, c);
            }
            this.f3810a.f3737g.remove((int) messageRow.m5091h());
            this.f3810a.f3736f.remove((int) j);
            this.f3810a.f3734d.remove((int) messageRow.m5091h());
            this.f3810a.m4765a();
        }
    }
}
