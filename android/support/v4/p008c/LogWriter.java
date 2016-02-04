package android.support.v4.p008c;

import android.util.Log;
import java.io.Writer;

/* renamed from: android.support.v4.c.e */
public class LogWriter extends Writer {
    private final String f358a;
    private StringBuilder f359b;

    public LogWriter(String str) {
        this.f359b = new StringBuilder(128);
        this.f358a = str;
    }

    private void m420a() {
        if (this.f359b.length() > 0) {
            Log.d(this.f358a, this.f359b.toString());
            this.f359b.delete(0, this.f359b.length());
        }
    }

    public void close() {
        m420a();
    }

    public void flush() {
        m420a();
    }

    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                m420a();
            } else {
                this.f359b.append(c);
            }
        }
    }
}
