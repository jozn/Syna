package org.p074b;

/* renamed from: org.b.b */
public class JSONException extends Exception {
    private Throwable f4125a;

    public JSONException(String str) {
        super(str);
    }

    public JSONException(Throwable th) {
        super(th.getMessage());
        this.f4125a = th;
    }

    public Throwable getCause() {
        return this.f4125a;
    }
}
