package com.mmdt.syna.view.components.pulltorefresh;

import android.annotation.TargetApi;
import android.view.View;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0096b;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0101h;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0103j;
import org.linphone.core.VideoSize;

@TargetApi(9)
/* renamed from: com.mmdt.syna.view.components.pulltorefresh.c */
public final class OverscrollHelper {
    private static /* synthetic */ int[] f1920a;

    public static void m2866a(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, int i5, int i6, float f, boolean z) {
        int scrollX;
        switch (OverscrollHelper.m2870a()[pullToRefreshBase.m2812r().ordinal()]) {
            case VideoSize.HVGA /*2*/:
                scrollX = pullToRefreshBase.getScrollX();
                break;
            default:
                scrollX = pullToRefreshBase.getScrollY();
                i2 = i4;
                i = i3;
                break;
        }
        if (pullToRefreshBase.m2809o() && !pullToRefreshBase.m2810p()) {
            C0096b j = pullToRefreshBase.m2804j();
            if (j.m2843b() && !z && i != 0) {
                int i7 = i + i2;
                if (i7 < 0 - i6) {
                    if (j.m2844c()) {
                        if (scrollX == 0) {
                            pullToRefreshBase.m2792a(C0103j.OVERSCROLLING, new boolean[0]);
                        }
                        pullToRefreshBase.m2785a((int) (((float) (scrollX + i7)) * f));
                    }
                } else if (i7 > i5 + i6) {
                    if (j.m2845d()) {
                        if (scrollX == 0) {
                            pullToRefreshBase.m2792a(C0103j.OVERSCROLLING, new boolean[0]);
                        }
                        pullToRefreshBase.m2785a((int) (((float) ((scrollX + i7) - i5)) * f));
                    }
                } else if (Math.abs(i7) <= i6 || Math.abs(i7 - i5) <= i6) {
                    pullToRefreshBase.m2792a(C0103j.RESET, new boolean[0]);
                }
            } else if (z && C0103j.OVERSCROLLING == pullToRefreshBase.m2807m()) {
                pullToRefreshBase.m2792a(C0103j.RESET, new boolean[0]);
            }
        }
    }

    public static void m2867a(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, int i5, boolean z) {
        OverscrollHelper.m2866a(pullToRefreshBase, i, i2, i3, i4, i5, 0, 1.0f, z);
    }

    public static void m2868a(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, boolean z) {
        OverscrollHelper.m2867a(pullToRefreshBase, i, i2, i3, i4, 0, z);
    }

    static boolean m2869a(View view) {
        return view.getOverScrollMode() != 2;
    }

    static /* synthetic */ int[] m2870a() {
        int[] iArr = f1920a;
        if (iArr == null) {
            iArr = new int[C0101h.values().length];
            try {
                iArr[C0101h.HORIZONTAL.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[C0101h.VERTICAL.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            f1920a = iArr;
        }
        return iArr;
    }
}
