package org.p039a.p071d;

import java.nio.ByteBuffer;
import org.p039a.p070c.InvalidDataException;
import org.p039a.p071d.Framedata.Framedata;

/* renamed from: org.a.d.c */
public interface FrameBuilder extends Framedata {
    void m5258a(ByteBuffer byteBuffer) throws InvalidDataException;

    void m5259a(Framedata framedata);

    void m5260a(boolean z);

    void m5261b(boolean z);
}
