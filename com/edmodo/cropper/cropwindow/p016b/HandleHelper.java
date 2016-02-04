package com.edmodo.cropper.cropwindow.p016b;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.p015a.Edge;
import com.edmodo.cropper.cropwindow.p015a.EdgePair;
import com.edmodo.cropper.p014a.AspectRatioUtil;

/* renamed from: com.edmodo.cropper.cropwindow.b.d */
abstract class HandleHelper {
    private Edge f1471a;
    private Edge f1472b;
    private EdgePair f1473c;

    HandleHelper(Edge edge, Edge edge2) {
        this.f1471a = edge;
        this.f1472b = edge2;
        this.f1473c = new EdgePair(this.f1471a, this.f1472b);
    }

    private float m2277a(float f, float f2) {
        float a = this.f1472b == Edge.LEFT ? f : Edge.LEFT.m2268a();
        float a2 = this.f1471a == Edge.TOP ? f2 : Edge.TOP.m2268a();
        if (this.f1472b != Edge.RIGHT) {
            f = Edge.RIGHT.m2268a();
        }
        if (this.f1471a != Edge.BOTTOM) {
            f2 = Edge.BOTTOM.m2268a();
        }
        return AspectRatioUtil.m2225a(a, a2, f, f2);
    }

    EdgePair m2278a() {
        return this.f1473c;
    }

    EdgePair m2279a(float f, float f2, float f3) {
        if (m2277a(f, f2) > f3) {
            this.f1473c.f1469a = this.f1472b;
            this.f1473c.f1470b = this.f1471a;
        } else {
            this.f1473c.f1469a = this.f1471a;
            this.f1473c.f1470b = this.f1472b;
        }
        return this.f1473c;
    }

    abstract void m2280a(float f, float f2, float f3, Rect rect, float f4);

    void m2281a(float f, float f2, Rect rect, float f3) {
        EdgePair a = m2278a();
        Edge edge = a.f1469a;
        Edge edge2 = a.f1470b;
        if (edge != null) {
            edge.m2271a(f, f2, rect, f3, 1.0f);
        }
        if (edge2 != null) {
            edge2.m2271a(f, f2, rect, f3, 1.0f);
        }
    }
}
