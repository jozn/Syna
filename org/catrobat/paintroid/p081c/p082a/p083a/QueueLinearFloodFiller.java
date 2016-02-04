package org.catrobat.paintroid.p081c.p082a.p083a;

import android.graphics.Color;
import android.graphics.Point;

/* renamed from: org.catrobat.paintroid.c.a.a.c */
public class QueueLinearFloodFiller {
    private static FloodFillRangeQueue f4280a;
    private static int f4281b;
    private static int f4282c;
    private static boolean[] f4283d;
    private static int[] f4284e;
    private static int f4285f;
    private static int f4286g;
    private static double f4287h;

    private static void m5497a(int i, int i2) {
        int i3 = (f4281b * i2) + i;
        int i4 = i;
        do {
            f4284e[(f4281b * i2) + i4] = f4286g;
            f4283d[i3] = true;
            i4--;
            i3--;
        } while (QueueLinearFloodFiller.m5499b(i4, i2));
        i4++;
        i3 = (f4281b * i2) + i;
        do {
            f4284e[(f4281b * i2) + i] = f4286g;
            f4283d[i3] = true;
            i++;
            i3++;
        } while (QueueLinearFloodFiller.m5499b(i, i2));
        f4280a.m5495a(new FloodFillRange(i4, i - 1, i2));
    }

    public static void m5498a(int[] iArr, int i, int i2, Point point, int i3, int i4, double d) {
        f4284e = iArr;
        f4286g = i4;
        f4285f = i3;
        f4287h = d;
        f4281b = i;
        f4282c = i2;
        f4283d = new boolean[(f4282c * f4281b)];
        f4280a = new FloodFillRangeQueue(f4281b + f4282c);
        QueueLinearFloodFiller.m5497a(point.x, point.y);
        while (f4280a.m5494a() > 0) {
            FloodFillRange b = f4280a.m5496b();
            int i5 = b.f4276c - 1;
            int i6 = b.f4276c + 1;
            for (int i7 = b.f4274a; i7 <= b.f4275b; i7++) {
                if (QueueLinearFloodFiller.m5499b(i7, i5)) {
                    QueueLinearFloodFiller.m5497a(i7, i5);
                }
                if (QueueLinearFloodFiller.m5499b(i7, i6)) {
                    QueueLinearFloodFiller.m5497a(i7, i6);
                }
            }
        }
    }

    private static boolean m5499b(int i, int i2) {
        return i >= 0 && i < f4281b && i2 >= 0 && i2 < f4282c && !f4283d[(f4281b * i2) + i] && QueueLinearFloodFiller.m5500c(i, i2);
    }

    private static boolean m5500c(int i, int i2) {
        int i3 = f4284e[(f4281b * i2) + i];
        int red = Color.red(f4285f);
        int red2 = Color.red(i3);
        int blue = Color.blue(f4285f);
        int blue2 = Color.blue(i3);
        int green = Color.green(f4285f);
        int green2 = Color.green(i3);
        return Math.sqrt(Math.pow((double) (Color.alpha(i3) - Color.alpha(f4285f)), 2.0d) + (Math.pow((double) (blue2 - blue), 2.0d) + (Math.pow((double) (red2 - red), 2.0d) + Math.pow((double) (green2 - green), 2.0d)))) < f4287h;
    }
}
