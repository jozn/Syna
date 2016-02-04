package org.p039a.p070c;

/* renamed from: org.a.c.a */
public class IncompleteHandshakeException extends RuntimeException {
    private int f4053a;

    public IncompleteHandshakeException() {
        this.f4053a = 0;
    }

    public IncompleteHandshakeException(int i) {
        this.f4053a = i;
    }

    public int m5247a() {
        return this.f4053a;
    }
}
