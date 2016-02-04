package org.catrobat.paintroid;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint({"NewApi"})
/* renamed from: org.catrobat.paintroid.a */
public abstract class FileIO {
    private static File f4246a;

    static {
        f4246a = null;
    }

    public static Bitmap m5478a(Uri uri) {
        Options options = new Options();
        if (PaintroidApplication.f4196f) {
            try {
                InputStream openInputStream = PaintroidApplication.f4191a.getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream);
                openInputStream.close();
                return decodeStream.copy(Config.ARGB_8888, true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        options.inJustDecodeBounds = true;
        try {
            openInputStream = PaintroidApplication.f4191a.getContentResolver().openInputStream(uri);
            BitmapFactory.decodeStream(openInputStream, null, options);
            openInputStream.close();
            int i = options.outWidth;
            int i2 = options.outHeight;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Display defaultDisplay = ((WindowManager) PaintroidApplication.f4191a.getSystemService("window")).getDefaultDisplay();
            defaultDisplay.getMetrics(displayMetrics);
            int width = defaultDisplay.getWidth();
            int height = defaultDisplay.getHeight();
            int i3 = 1;
            int i4 = i2;
            i2 = i;
            while (true) {
                if (i2 > width || i4 > height) {
                    i2 /= 2;
                    i4 /= 2;
                    i3 *= 2;
                } else {
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = i3;
                    try {
                        InputStream openInputStream2 = PaintroidApplication.f4191a.getContentResolver().openInputStream(uri);
                        Bitmap decodeStream2 = BitmapFactory.decodeStream(openInputStream2, null, options);
                        openInputStream2.close();
                        i4 = decodeStream2.getWidth();
                        width = decodeStream2.getHeight();
                        int[] iArr = new int[(i4 * width)];
                        decodeStream2.getPixels(iArr, 0, i4, 0, 0, i4, width);
                        decodeStream2 = Bitmap.createBitmap(i4, width, Config.ARGB_8888);
                        decodeStream2.setPixels(iArr, 0, i4, 0, 0, i4, width);
                        return decodeStream2;
                    } catch (Exception e3) {
                        return null;
                    }
                }
            }
        } catch (Exception e4) {
            return null;
        }
    }

    public static Bitmap m5479a(File file) {
        Options options = new Options();
        if (PaintroidApplication.f4196f) {
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(file.getAbsolutePath(), options).copy(Config.ARGB_8888, true);
        }
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        int i = options.outWidth;
        int i2 = options.outHeight;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = ((WindowManager) PaintroidApplication.f4191a.getSystemService("window")).getDefaultDisplay();
        defaultDisplay.getMetrics(displayMetrics);
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        int i3 = 1;
        int i4 = i2;
        i2 = i;
        while (true) {
            if (i2 > width || i4 > height) {
                i2 /= 2;
                i4 /= 2;
                i3 *= 2;
            } else {
                options.inJustDecodeBounds = false;
                options.inSampleSize = i3;
                Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                i2 = decodeFile.getWidth();
                height = decodeFile.getHeight();
                int[] iArr = new int[(i2 * height)];
                decodeFile.getPixels(iArr, 0, i2, 0, 0, i2, height);
                decodeFile = Bitmap.createBitmap(i2, height, Config.ARGB_8888);
                decodeFile.setPixels(iArr, 0, i2, 0, 0, i2, height);
                return decodeFile;
            }
        }
    }

    public static Uri m5480a() {
        return Media.EXTERNAL_CONTENT_URI;
    }

    public static File m5481a(Context context) {
        return FileIO.m5482a(context, FileIO.m5485b());
    }

    public static File m5482a(Context context, String str) {
        if (!FileIO.m5486c()) {
            return null;
        }
        if (!str.toLowerCase().endsWith(".png".toLowerCase())) {
            str = new StringBuilder(String.valueOf(str)).append(".png").toString();
        }
        return new File(f4246a, str);
    }

    public static String m5483a(Context context, Bitmap bitmap) {
        return FileIO.m5484a(context, bitmap, null);
    }

    public static String m5484a(Context context, Bitmap bitmap, String str) {
        if (!FileIO.m5486c()) {
            return null;
        }
        CompressFormat compressFormat = CompressFormat.PNG;
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    OutputStream fileOutputStream;
                    File file;
                    String str2;
                    File file2;
                    if (str != null) {
                        file2 = new File(str);
                        fileOutputStream = new FileOutputStream(file2);
                        file = file2;
                        str2 = str;
                    } else if (PaintroidApplication.f4201k == null || PaintroidApplication.f4202l) {
                        file2 = FileIO.m5481a(context);
                        fileOutputStream = new FileOutputStream(file2);
                        file = file2;
                        str2 = file2.getPath();
                    } else {
                        file = null;
                        fileOutputStream = context.getContentResolver().openOutputStream(PaintroidApplication.f4201k);
                        str2 = str;
                    }
                    if (fileOutputStream != null) {
                        boolean compress = bitmap.compress(compressFormat, 100, fileOutputStream);
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (!compress) {
                            Log.e("PAINTROID", "ERROR writing image file. Bitmap compress didn't work. ");
                            return null;
                        } else if (file != null) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("_data", file.getAbsolutePath());
                            PaintroidApplication.f4201k = context.getContentResolver().insert(FileIO.m5480a(), contentValues);
                        }
                    }
                    return str2;
                }
            } catch (Throwable e2) {
                Log.e("PAINTROID", "ERROR writing image file. File not found. Path: " + str, e2);
                return null;
            }
        }
        Log.e("PAINTROID", "ERROR saving bitmap. ");
        return null;
    }

    public static String m5485b() {
        return new StringBuilder(String.valueOf(new SimpleDateFormat("yyyy_MM_dd_hhmmss").format(new Date()))).append(".png").toString();
    }

    private static boolean m5486c() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return false;
        }
        f4246a = new File(Environment.getExternalStorageDirectory() + File.separator + "SYNA" + File.separator + "Media" + File.separator + ".Doodle" + File.separator);
        return f4246a != null ? !f4246a.isDirectory() ? f4246a.mkdirs() : true : false;
    }
}
