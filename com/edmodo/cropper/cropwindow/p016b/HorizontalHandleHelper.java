package com.edmodo.cropper.cropwindow.p016b;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.p015a.Edge;
import com.edmodo.cropper.p014a.AspectRatioUtil;

/* renamed from: com.edmodo.cropper.cropwindow.b.e */
class HorizontalHandleHelper extends HandleHelper {
    private Edge f1485a;

    HorizontalHandleHelper(Edge edge) {
        super(edge, null);
        this.f1485a = edge;
    }

    void m2287a(float f, float f2, float f3, Rect rect, float f4) {
        this.f1485a.m2271a(f, f2, rect, f4, f3);
        float a = Edge.LEFT.m2268a();
        float a2 = Edge.TOP.m2268a();
        float a3 = Edge.RIGHT.m2268a();
        a2 = (AspectRatioUtil.m2224a(a2, Edge.BOTTOM.m2268a(), f3) - (a3 - a)) / 2.0f;
        a -= a2;
        a2 += a3;
        Edge.LEFT.m2270a(a);
        Edge.RIGHT.m2270a(a2);
        if (Edge.LEFT.m2272a(rect, f4) && !this.f1485a.m2273a(Edge.LEFT, rect, f3)) {
            Edge.RIGHT.m2275b(-Edge.LEFT.m2269a(rect));
            this.f1485a.m2276c(f3);
        }
        if (Edge.RIGHT.m2272a(rect, f4) && !this.f1485a.m2273a(Edge.RIGHT, rect, f3)) {
            Edge.LEFT.m2275b(-Edge.RIGHT.m2269a(rect));
            this.f1485a.m2276c(f3);
        }
    }
}
