package com.edmodo.cropper.cropwindow.p016b;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.p015a.Edge;

/* renamed from: com.edmodo.cropper.cropwindow.b.c */
public enum Handle {
    TOP_LEFT(new CornerHandleHelper(Edge.TOP, Edge.LEFT)),
    TOP_RIGHT(new CornerHandleHelper(Edge.TOP, Edge.RIGHT)),
    BOTTOM_LEFT(new CornerHandleHelper(Edge.BOTTOM, Edge.LEFT)),
    BOTTOM_RIGHT(new CornerHandleHelper(Edge.BOTTOM, Edge.RIGHT)),
    LEFT(new VerticalHandleHelper(Edge.LEFT)),
    TOP(new HorizontalHandleHelper(Edge.TOP)),
    RIGHT(new VerticalHandleHelper(Edge.RIGHT)),
    BOTTOM(new HorizontalHandleHelper(Edge.BOTTOM)),
    CENTER(new CenterHandleHelper());
    
    private HandleHelper f1484j;

    private Handle(HandleHelper handleHelper) {
        this.f1484j = handleHelper;
    }

    public void m2285a(float f, float f2, float f3, Rect rect, float f4) {
        this.f1484j.m2280a(f, f2, f3, rect, f4);
    }

    public void m2286a(float f, float f2, Rect rect, float f3) {
        this.f1484j.m2281a(f, f2, rect, f3);
    }
}
