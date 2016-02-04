package com.edmodo.cropper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.edmodo.cropper.R.R;
import com.edmodo.cropper.cropwindow.CropOverlayView;
import com.edmodo.cropper.cropwindow.p015a.Edge;
import com.edmodo.cropper.p014a.ImageViewUtil;
import org.linphone.mediastream.Version;

public class CropImageView extends FrameLayout {
    private static final Rect f1425a;
    private ImageView f1426b;
    private CropOverlayView f1427c;
    private Bitmap f1428d;
    private int f1429e;
    private int f1430f;
    private int f1431g;
    private int f1432h;
    private boolean f1433i;
    private int f1434j;
    private int f1435k;
    private int f1436l;

    static {
        f1425a = new Rect();
    }

    public CropImageView(Context context) {
        super(context);
        this.f1429e = 0;
        this.f1432h = 1;
        this.f1433i = false;
        this.f1434j = 1;
        this.f1435k = 1;
        this.f1436l = 0;
        m2221a(context);
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1429e = 0;
        this.f1432h = 1;
        this.f1433i = false;
        this.f1434j = 1;
        this.f1435k = 1;
        this.f1436l = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.CropImageView, 0, 0);
        try {
            this.f1432h = obtainStyledAttributes.getInteger(R.CropImageView_guidelines, 1);
            this.f1433i = obtainStyledAttributes.getBoolean(R.CropImageView_fixAspectRatio, false);
            this.f1434j = obtainStyledAttributes.getInteger(R.CropImageView_aspectRatioX, 1);
            this.f1435k = obtainStyledAttributes.getInteger(R.CropImageView_aspectRatioY, 1);
            this.f1436l = obtainStyledAttributes.getResourceId(R.CropImageView_imageResource, 0);
            m2221a(context);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private static int m2220a(int i, int i2, int i3) {
        return i == 1073741824 ? i2 : i == Integer.MIN_VALUE ? Math.min(i3, i2) : i3;
    }

    private void m2221a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.crop_image_view, this, true);
        this.f1426b = (ImageView) inflate.findViewById(R.ImageView_image);
        setImageResource(this.f1436l);
        this.f1427c = (CropOverlayView) inflate.findViewById(R.CropOverlayView);
        this.f1427c.setDefaultPaddingInPercent(0.0f);
        this.f1427c.setInitialAttributeValues(this.f1432h, this.f1433i, this.f1434j, this.f1435k);
    }

    public Bitmap m2222a() {
        Rect a = ImageViewUtil.m2242a(this.f1428d, this.f1426b);
        float width = ((float) this.f1428d.getWidth()) / ((float) a.width());
        float height = ((float) this.f1428d.getHeight()) / ((float) a.height());
        float a2 = Edge.LEFT.m2268a() - ((float) a.left);
        float a3 = Edge.TOP.m2268a() - ((float) a.top);
        return Bitmap.createBitmap(this.f1428d, (int) (a2 * width), (int) (a3 * height), (int) (width * Edge.m2262b()), (int) (height * Edge.m2264c()));
    }

    public void m2223a(int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        this.f1428d = Bitmap.createBitmap(this.f1428d, 0, 0, this.f1428d.getWidth(), this.f1428d.getHeight(), matrix, true);
        setImageBitmap(this.f1428d);
        this.f1429e += i;
        this.f1429e %= 360;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f1430f > 0 && this.f1431g > 0) {
            LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = this.f1430f;
            layoutParams.height = this.f1431g;
            setLayoutParams(layoutParams);
        }
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (this.f1428d != null) {
            int width;
            int height;
            super.onMeasure(i, i2);
            if (size2 == 0) {
                size2 = this.f1428d.getHeight();
            }
            double d = Double.POSITIVE_INFINITY;
            double d2 = Double.POSITIVE_INFINITY;
            if (size < this.f1428d.getWidth()) {
                d = ((double) size) / ((double) this.f1428d.getWidth());
            }
            if (size2 < this.f1428d.getHeight()) {
                d2 = ((double) size2) / ((double) this.f1428d.getHeight());
            }
            if (d == Double.POSITIVE_INFINITY && d2 == Double.POSITIVE_INFINITY) {
                width = this.f1428d.getWidth();
                height = this.f1428d.getHeight();
            } else if (d <= d2) {
                height = (int) (d * ((double) this.f1428d.getHeight()));
                width = size;
            } else {
                width = (int) (((double) this.f1428d.getWidth()) * d2);
                height = size2;
            }
            width = m2220a(mode, size, width);
            size2 = m2220a(mode2, size2, height);
            this.f1430f = width;
            this.f1431g = size2;
            this.f1427c.setBitmapRect(ImageViewUtil.m2241a(this.f1428d.getWidth(), this.f1428d.getHeight(), this.f1430f, this.f1431g));
            setMeasuredDimension(this.f1430f, this.f1431g);
            return;
        }
        this.f1427c.setBitmapRect(f1425a);
        setMeasuredDimension(size, size2);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            if (this.f1428d != null) {
                this.f1429e = bundle.getInt("DEGREES_ROTATED");
                int i = this.f1429e;
                m2223a(this.f1429e);
                this.f1429e = i;
            }
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("DEGREES_ROTATED", this.f1429e);
        return bundle;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.f1428d != null) {
            this.f1427c.setBitmapRect(ImageViewUtil.m2242a(this.f1428d, this));
            return;
        }
        this.f1427c.setBitmapRect(f1425a);
    }

    public void setAspectRatio(int i, int i2) {
        this.f1434j = i;
        this.f1427c.setAspectRatioX(this.f1434j);
        this.f1435k = i2;
        this.f1427c.setAspectRatioY(this.f1435k);
    }

    public void setFixedAspectRatio(boolean z) {
        this.f1427c.setFixedAspectRatio(z);
    }

    public void setGuidelines(int i) {
        this.f1427c.setGuidelines(i);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.f1428d = bitmap;
        this.f1426b.setImageBitmap(this.f1428d);
        if (this.f1427c != null) {
            this.f1427c.m2259a();
        }
    }

    public void setImageBitmap(Bitmap bitmap, ExifInterface exifInterface) {
        if (bitmap != null) {
            if (exifInterface == null) {
                setImageBitmap(bitmap);
                return;
            }
            int i;
            Matrix matrix = new Matrix();
            switch (exifInterface.getAttributeInt("Orientation", 1)) {
                case Version.API03_CUPCAKE_15 /*3*/:
                    i = 180;
                    break;
                case Version.API06_ECLAIR_201 /*6*/:
                    i = 90;
                    break;
                case Version.API08_FROYO_22 /*8*/:
                    i = 270;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (i == -1) {
                setImageBitmap(bitmap);
                return;
            }
            matrix.postRotate((float) i);
            setImageBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true));
            bitmap.recycle();
        }
    }

    public void setImageResource(int i) {
        if (i != 0) {
            setImageBitmap(BitmapFactory.decodeResource(getResources(), i));
        }
    }
}
