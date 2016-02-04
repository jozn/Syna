package org.p039a.p071d;

import java.nio.ByteBuffer;

/* renamed from: org.a.d.d */
public interface Framedata {

    /* renamed from: org.a.d.d.a */
    public enum Framedata {
        CONTINUOUS,
        TEXT,
        BINARY,
        PING,
        PONG,
        CLOSING
    }

    ByteBuffer m5252c();

    boolean m5253d();

    boolean m5254e();

    Framedata m5255f();
}
