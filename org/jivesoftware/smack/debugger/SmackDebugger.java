package org.jivesoftware.smack.debugger;

import java.io.Reader;
import java.io.Writer;
import org.jivesoftware.smack.StanzaListener;

/* renamed from: org.jivesoftware.smack.debugger.a */
public interface SmackDebugger {
    StanzaListener getReaderListener();

    StanzaListener getWriterListener();

    Reader newConnectionReader(Reader reader);

    Writer newConnectionWriter(Writer writer);

    void userHasLogged(String str);
}
