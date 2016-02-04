package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;

/* renamed from: android.support.v4.widget.h */
public class EdgeEffectCompat {
    private static final EdgeEffectCompat f688b;
    private Object f689a;

    /* renamed from: android.support.v4.widget.h.c */
    interface EdgeEffectCompat {
        Object m1317a(Context context);

        void m1318a(Object obj, int i, int i2);

        boolean m1319a(Object obj);

        boolean m1320a(Object obj, float f);

        boolean m1321a(Object obj, Canvas canvas);

        void m1322b(Object obj);

        boolean m1323c(Object obj);
    }

    /* renamed from: android.support.v4.widget.h.a */
    static class EdgeEffectCompat implements EdgeEffectCompat {
        EdgeEffectCompat() {
        }

        public Object m1324a(Context context) {
            return null;
        }

        public void m1325a(Object obj, int i, int i2) {
        }

        public boolean m1326a(Object obj) {
            return true;
        }

        public boolean m1327a(Object obj, float f) {
            return false;
        }

        public boolean m1328a(Object obj, Canvas canvas) {
            return false;
        }

        public void m1329b(Object obj) {
        }

        public boolean m1330c(Object obj) {
            return false;
        }
    }

    /* renamed from: android.support.v4.widget.h.b */
    static class EdgeEffectCompat implements EdgeEffectCompat {
        EdgeEffectCompat() {
        }

        public Object m1331a(Context context) {
            return EdgeEffectCompatIcs.m1344a(context);
        }

        public void m1332a(Object obj, int i, int i2) {
            EdgeEffectCompatIcs.m1345a(obj, i, i2);
        }

        public boolean m1333a(Object obj) {
            return EdgeEffectCompatIcs.m1346a(obj);
        }

        public boolean m1334a(Object obj, float f) {
            return EdgeEffectCompatIcs.m1347a(obj, f);
        }

        public boolean m1335a(Object obj, Canvas canvas) {
            return EdgeEffectCompatIcs.m1348a(obj, canvas);
        }

        public void m1336b(Object obj) {
            EdgeEffectCompatIcs.m1349b(obj);
        }

        public boolean m1337c(Object obj) {
            return EdgeEffectCompatIcs.m1350c(obj);
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            f688b = new EdgeEffectCompat();
        } else {
            f688b = new EdgeEffectCompat();
        }
    }

    public EdgeEffectCompat(Context context) {
        this.f689a = f688b.m1317a(context);
    }

    public void m1338a(int i, int i2) {
        f688b.m1318a(this.f689a, i, i2);
    }

    public boolean m1339a() {
        return f688b.m1319a(this.f689a);
    }

    public boolean m1340a(float f) {
        return f688b.m1320a(this.f689a, f);
    }

    public boolean m1341a(Canvas canvas) {
        return f688b.m1321a(this.f689a, canvas);
    }

    public void m1342b() {
        f688b.m1322b(this.f689a);
    }

    public boolean m1343c() {
        return f688b.m1323c(this.f689a);
    }
}
