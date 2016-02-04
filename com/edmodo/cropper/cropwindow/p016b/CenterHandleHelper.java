package com.edmodo.cropper.cropwindow.p016b;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.p015a.Edge;

/* renamed from: com.edmodo.cropper.cropwindow.b.a */
class CenterHandleHelper extends HandleHelper {
    CenterHandleHelper() {
        super(null, null);
    }

    void m2282a(float f, float f2, float f3, Rect rect, float f4) {
        m2283a(f, f2, rect, f4);
    }

    void m2283a(float f, float f2, Rect rect, float f3) {
        float a = Edge.LEFT.m2268a();
        float a2 = Edge.TOP.m2268a();
        a = f - ((a + Edge.RIGHT.m2268a()) / 2.0f);
        a2 = f2 - ((a2 + Edge.BOTTOM.m2268a()) / 2.0f);
        Edge.LEFT.m2275b(a);
        Edge.TOP.m2275b(a2);
        Edge.RIGHT.m2275b(a);
        Edge.BOTTOM.m2275b(a2);
        if (Edge.LEFT.m2272a(rect, f3)) {
            Edge.RIGHT.m2275b(Edge.LEFT.m2269a(rect));
        } else if (Edge.RIGHT.m2272a(rect, f3)) {
            Edge.LEFT.m2275b(Edge.RIGHT.m2269a(rect));
        }
        if (Edge.TOP.m2272a(rect, f3)) {
            Edge.BOTTOM.m2275b(Edge.TOP.m2269a(rect));
        } else if (Edge.BOTTOM.m2272a(rect, f3)) {
            Edge.TOP.m2275b(Edge.BOTTOM.m2269a(rect));
        }
    }
}
