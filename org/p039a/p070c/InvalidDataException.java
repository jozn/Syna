package org.p039a.p070c;

/* renamed from: org.a.c.b */
public class InvalidDataException extends Exception {
    private int f4054a;

    public InvalidDataException(int i) {
        this.f4054a = i;
    }

    public InvalidDataException(int i, String str) {
        super(str);
        this.f4054a = i;
    }

    public InvalidDataException(int i, Throwable th) {
        super(th);
        this.f4054a = i;
    }

    public int m5248a() {
        return this.f4054a;
    }
}
