package mobi.mmdt.ott.core.logic.p054a;

import io.socket.IOAcknowledge;

/* renamed from: mobi.mmdt.ott.core.logic.a.h */
class SocketIoManager implements Runnable {
    final /* synthetic */ SocketIoManager f3547a;
    private final /* synthetic */ String f3548b;
    private final /* synthetic */ IOAcknowledge f3549c;
    private final /* synthetic */ Object[] f3550d;

    SocketIoManager(SocketIoManager socketIoManager, String str, IOAcknowledge iOAcknowledge, Object[] objArr) {
        this.f3547a = socketIoManager;
        this.f3548b = str;
        this.f3549c = iOAcknowledge;
        this.f3550d = objArr;
    }

    public void run() {
        if (this.f3547a.f3544e != null) {
            this.f3547a.f3544e.m4515a(this.f3548b, this.f3549c, this.f3550d);
        }
    }
}
