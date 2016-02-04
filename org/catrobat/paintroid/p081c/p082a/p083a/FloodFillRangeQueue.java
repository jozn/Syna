package org.catrobat.paintroid.p081c.p082a.p083a;

/* renamed from: org.catrobat.paintroid.c.a.a.b */
public class FloodFillRangeQueue {
    private FloodFillRange[] f4277a;
    private int f4278b;
    private int f4279c;

    public FloodFillRangeQueue(int i) {
        this.f4277a = new FloodFillRange[i];
        this.f4278b = 0;
        this.f4279c = 0;
    }

    public int m5494a() {
        return this.f4279c;
    }

    public void m5495a(FloodFillRange floodFillRange) {
        if (this.f4279c + this.f4278b == this.f4277a.length) {
            Object obj = new FloodFillRange[(this.f4277a.length * 2)];
            System.arraycopy(this.f4277a, this.f4278b, obj, 0, this.f4279c);
            this.f4277a = obj;
            this.f4278b = 0;
        }
        FloodFillRange[] floodFillRangeArr = this.f4277a;
        int i = this.f4278b;
        int i2 = this.f4279c;
        this.f4279c = i2 + 1;
        floodFillRangeArr[i + i2] = floodFillRange;
    }

    public FloodFillRange m5496b() {
        if (this.f4279c <= 0) {
            return null;
        }
        FloodFillRange floodFillRange = this.f4277a[this.f4278b];
        this.f4277a[this.f4278b] = null;
        this.f4278b++;
        this.f4279c--;
        return floodFillRange;
    }
}
