package mobi.mmdt.p041a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* renamed from: mobi.mmdt.a.a */
public class Connectivity {
    public static NetworkInfo m4081a(Context context) {
        ConnectivityManager connectivityManager;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        } catch (Exception e) {
            e.printStackTrace();
            connectivityManager = null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    public static boolean m4082b(Context context) {
        NetworkInfo networkInfo = null;
        try {
            networkInfo = Connectivity.m4081a(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean m4083c(Context context) {
        NetworkInfo a = Connectivity.m4081a(context);
        return a != null && a.isConnected() && a.getType() == 0;
    }
}
