package com.edmodo.cropper.cropwindow.p016b;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.p015a.Edge;
import com.edmodo.cropper.p014a.AspectRatioUtil;

/* renamed from: com.edmodo.cropper.cropwindow.b.f */
class VerticalHandleHelper extends HandleHelper {
    private Edge f1486a;

    VerticalHandleHelper(Edge edge) {
        super(null, edge);
        this.f1486a = edge;
    }

    void m2288a(float f, float f2, float f3, Rect rect, float f4) {
        this.f1486a.m2271a(f, f2, rect, f4, f3);
        float a = Edge.LEFT.m2268a();
        float a2 = Edge.TOP.m2268a();
        float a3 = Edge.RIGHT.m2268a();
        float a4 = Edge.BOTTOM.m2268a();
        a = (AspectRatioUtil.m2227b(a, a3, f3) - (a4 - a2)) / 2.0f;
        a2 -= a;
        a += a4;
        Edge.TOP.m2270a(a2);
        Edge.BOTTOM.m2270a(a);
        if (Edge.TOP.m2272a(rect, f4) && !this.f1486a.m2273a(Edge.TOP, rect, f3)) {
            Edge.BOTTOM.m2275b(-Edge.TOP.m2269a(rect));
            this.f1486a.m2276c(f3);
        }
        if (Edge.BOTTOM.m2272a(rect, f4) && !this.f1486a.m2273a(Edge.BOTTOM, rect, f3)) {
            Edge.TOP.m2275b(-Edge.BOTTOM.m2269a(rect));
            this.f1486a.m2276c(f3);
        }
    }
}
