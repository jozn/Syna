package com.edmodo.cropper.cropwindow.p016b;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.p015a.Edge;
import com.edmodo.cropper.cropwindow.p015a.EdgePair;

/* renamed from: com.edmodo.cropper.cropwindow.b.b */
class CornerHandleHelper extends HandleHelper {
    CornerHandleHelper(Edge edge, Edge edge2) {
        super(edge, edge2);
    }

    void m2284a(float f, float f2, float f3, Rect rect, float f4) {
        EdgePair a = m2279a(f, f2, f3);
        Edge edge = a.f1469a;
        Edge edge2 = a.f1470b;
        edge.m2271a(f, f2, rect, f4, f3);
        edge2.m2276c(f3);
        if (edge2.m2272a(rect, f4)) {
            edge2.m2269a(rect);
            edge.m2276c(f3);
        }
    }
}
