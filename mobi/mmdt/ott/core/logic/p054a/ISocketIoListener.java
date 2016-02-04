package mobi.mmdt.ott.core.logic.p054a;

import io.socket.IOAcknowledge;
import io.socket.SocketIOException;

/* renamed from: mobi.mmdt.ott.core.logic.a.a */
public interface ISocketIoListener {
    void m4513a();

    void m4514a(SocketIOException socketIOException);

    void m4515a(String str, IOAcknowledge iOAcknowledge, Object... objArr);

    void m4516a(PushListener pushListener);

    void m4517b();
}
