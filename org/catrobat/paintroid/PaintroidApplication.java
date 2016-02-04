package org.catrobat.paintroid;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.view.Menu;
import org.catrobat.paintroid.p078a.CommandManager;
import org.catrobat.paintroid.p078a.p079a.CommandManagerImplementation;
import org.catrobat.paintroid.p081c.Tool;
import org.catrobat.paintroid.ui.DrawingSurface;
import org.catrobat.paintroid.ui.Perspective;

public class PaintroidApplication extends Application {
    public static Context f4191a;
    public static DrawingSurface f4192b;
    public static CommandManager f4193c;
    public static Tool f4194d;
    public static Perspective f4195e;
    public static boolean f4196f;
    public static String f4197g;
    public static boolean f4198h;
    public static Menu f4199i;
    public static boolean f4200j;
    public static Uri f4201k;
    public static boolean f4202l;

    static {
        f4196f = false;
        f4198h = true;
        f4200j = true;
        f4201k = null;
        f4202l = false;
    }

    public static void m5437a(Context context) {
        f4191a = context;
        f4193c = new CommandManagerImplementation();
    }

    public void onCreate() {
        super.onCreate();
        f4191a = getApplicationContext();
        f4193c = new CommandManagerImplementation();
    }
}
