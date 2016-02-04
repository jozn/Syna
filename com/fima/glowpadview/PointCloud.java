package com.fima.glowpadview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.FloatMath;
import android.util.Log;
import java.util.ArrayList;

public class PointCloud {
    private static final int INNER_POINTS = 8;
    private static final float MAX_POINT_SIZE = 4.0f;
    private static final float MIN_POINT_SIZE = 2.0f;
    private static final float PI = 3.1415927f;
    private static final String TAG = "PointCloud";
    GlowManager glowManager;
    private float mCenterX;
    private float mCenterY;
    private Drawable mDrawable;
    private float mOuterRadius;
    private Paint mPaint;
    private ArrayList<Point> mPointCloud;
    private float mScale;
    WaveManager waveManager;

    public class GlowManager {
        private float alpha;
        private float radius;
        private float f1576x;
        private float f1577y;

        public GlowManager() {
            this.radius = 0.0f;
            this.alpha = 0.0f;
        }

        public float getAlpha() {
            return this.alpha;
        }

        public float getRadius() {
            return this.radius;
        }

        public float getX() {
            return this.f1576x;
        }

        public float getY() {
            return this.f1577y;
        }

        public void setAlpha(float f) {
            this.alpha = f;
        }

        public void setRadius(float f) {
            this.radius = f;
        }

        public void setX(float f) {
            this.f1576x = f;
        }

        public void setY(float f) {
            this.f1577y = f;
        }
    }

    class Point {
        float radius;
        float f1578x;
        float f1579y;

        public Point(float f, float f2, float f3) {
            this.f1578x = f;
            this.f1579y = f2;
            this.radius = f3;
        }
    }

    public class WaveManager {
        private float alpha;
        private float radius;
        private float width;

        public WaveManager() {
            this.radius = 50.0f;
            this.width = 200.0f;
            this.alpha = 0.0f;
        }

        public float getAlpha() {
            return this.alpha;
        }

        public float getRadius() {
            return this.radius;
        }

        public void setAlpha(float f) {
            this.alpha = f;
        }

        public void setRadius(float f) {
            this.radius = f;
        }
    }

    public PointCloud(Drawable drawable) {
        this.mPointCloud = new ArrayList();
        this.mScale = 1.0f;
        this.waveManager = new WaveManager();
        this.glowManager = new GlowManager();
        this.mPaint = new Paint();
        this.mPaint.setFilterBitmap(true);
        this.mPaint.setColor(Color.rgb(255, 255, 255));
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mDrawable = drawable;
        if (this.mDrawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }

    private static float hypot(float f, float f2) {
        return FloatMath.sqrt((f * f) + (f2 * f2));
    }

    private float interp(float f, float f2, float f3) {
        return ((f2 - f) * f3) + f;
    }

    private static float max(float f, float f2) {
        return f > f2 ? f : f2;
    }

    public void draw(Canvas canvas) {
        ArrayList arrayList = this.mPointCloud;
        canvas.save(1);
        canvas.scale(this.mScale, this.mScale, this.mCenterX, this.mCenterY);
        for (int i = 0; i < arrayList.size(); i++) {
            Point point = (Point) arrayList.get(i);
            float interp = interp(MAX_POINT_SIZE, MIN_POINT_SIZE, point.radius / this.mOuterRadius);
            float f = point.f1578x + this.mCenterX;
            float f2 = point.f1579y + this.mCenterY;
            int alphaForPoint = getAlphaForPoint(point);
            if (alphaForPoint != 0) {
                if (this.mDrawable != null) {
                    canvas.save(1);
                    float intrinsicWidth = ((float) this.mDrawable.getIntrinsicWidth()) * 0.5f;
                    float intrinsicHeight = ((float) this.mDrawable.getIntrinsicHeight()) * 0.5f;
                    interp /= MAX_POINT_SIZE;
                    canvas.scale(interp, interp, f, f2);
                    canvas.translate(f - intrinsicWidth, f2 - intrinsicHeight);
                    this.mDrawable.setAlpha(alphaForPoint);
                    this.mDrawable.draw(canvas);
                    canvas.restore();
                } else {
                    this.mPaint.setAlpha(alphaForPoint);
                    canvas.drawCircle(f, f2, interp, this.mPaint);
                }
            }
        }
        canvas.restore();
    }

    public int getAlphaForPoint(Point point) {
        float f = 0.0f;
        float hypot = hypot(this.glowManager.f1576x - point.f1578x, this.glowManager.f1577y - point.f1579y);
        if (hypot < this.glowManager.radius) {
            hypot = FloatMath.cos((hypot * 0.7853982f) / this.glowManager.radius);
            hypot = max(0.0f, (float) Math.pow((double) hypot, 10.0d)) * this.glowManager.alpha;
        } else {
            hypot = 0.0f;
        }
        float hypot2 = hypot(point.f1578x, point.f1579y) - this.waveManager.radius;
        if (hypot2 < this.waveManager.width * 0.5f && hypot2 < 0.0f) {
            hypot2 = FloatMath.cos((hypot2 * 0.7853982f) / this.waveManager.width);
            f = max(0.0f, (float) Math.pow((double) hypot2, 20.0d)) * this.waveManager.alpha;
        }
        return (int) (max(hypot, f) * 255.0f);
    }

    public float getScale() {
        return this.mScale;
    }

    public void makePointCloud(float f, float f2) {
        if (f == 0.0f) {
            Log.w(TAG, "Must specify an inner radius");
            return;
        }
        this.mOuterRadius = f2;
        this.mPointCloud.clear();
        float f3 = f2 - f;
        float f4 = (6.2831855f * f) / 8.0f;
        int round = Math.round(f3 / f4);
        float f5 = f3 / ((float) round);
        for (int i = 0; i <= round; i++) {
            int i2 = (int) ((6.2831855f * f) / f4);
            float f6 = 1.5707964f;
            float f7 = 6.2831855f / ((float) i2);
            for (int i3 = 0; i3 < i2; i3++) {
                float cos = FloatMath.cos(f6) * f;
                float sin = FloatMath.sin(f6) * f;
                f6 += f7;
                this.mPointCloud.add(new Point(cos, sin, f));
            }
            f += f5;
        }
    }

    public void setCenter(float f, float f2) {
        this.mCenterX = f;
        this.mCenterY = f2;
    }

    public void setScale(float f) {
        this.mScale = f;
    }
}
