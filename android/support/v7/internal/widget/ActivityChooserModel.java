package android.support.v7.internal.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.time.packet.Time;
import org.xmlpull.v1.XmlSerializer;

/* renamed from: android.support.v7.internal.widget.h */
public class ActivityChooserModel extends DataSetObservable {
    private static final String f1265a;
    private static final Object f1266b;
    private static final Map<String, ActivityChooserModel> f1267c;
    private final Object f1268d;
    private final List<ActivityChooserModel> f1269e;
    private final List<ActivityChooserModel> f1270f;
    private final Context f1271g;
    private final String f1272h;
    private Intent f1273i;
    private ActivityChooserModel f1274j;
    private int f1275k;
    private boolean f1276l;
    private boolean f1277m;
    private boolean f1278n;
    private boolean f1279o;
    private ActivityChooserModel f1280p;

    /* renamed from: android.support.v7.internal.widget.h.a */
    public final class ActivityChooserModel implements Comparable<ActivityChooserModel> {
        public final ResolveInfo f1258a;
        public float f1259b;
        final /* synthetic */ ActivityChooserModel f1260c;

        public ActivityChooserModel(ActivityChooserModel activityChooserModel, ResolveInfo resolveInfo) {
            this.f1260c = activityChooserModel;
            this.f1258a = resolveInfo;
        }

        public int m2047a(ActivityChooserModel activityChooserModel) {
            return Float.floatToIntBits(activityChooserModel.f1259b) - Float.floatToIntBits(this.f1259b);
        }

        public /* synthetic */ int compareTo(Object obj) {
            return m2047a((ActivityChooserModel) obj);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            return Float.floatToIntBits(this.f1259b) == Float.floatToIntBits(((ActivityChooserModel) obj).f1259b);
        }

        public int hashCode() {
            return Float.floatToIntBits(this.f1259b) + 31;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append("resolveInfo:").append(this.f1258a.toString());
            stringBuilder.append("; weight:").append(new BigDecimal((double) this.f1259b));
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    /* renamed from: android.support.v7.internal.widget.h.b */
    public interface ActivityChooserModel {
        void m2048a(Intent intent, List<ActivityChooserModel> list, List<ActivityChooserModel> list2);
    }

    /* renamed from: android.support.v7.internal.widget.h.c */
    public static final class ActivityChooserModel {
        public final ComponentName f1261a;
        public final long f1262b;
        public final float f1263c;

        public ActivityChooserModel(ComponentName componentName, long j, float f) {
            this.f1261a = componentName;
            this.f1262b = j;
            this.f1263c = f;
        }

        public ActivityChooserModel(String str, long j, float f) {
            this(ComponentName.unflattenFromString(str), j, f);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ActivityChooserModel activityChooserModel = (ActivityChooserModel) obj;
            if (this.f1261a == null) {
                if (activityChooserModel.f1261a != null) {
                    return false;
                }
            } else if (!this.f1261a.equals(activityChooserModel.f1261a)) {
                return false;
            }
            return this.f1262b != activityChooserModel.f1262b ? false : Float.floatToIntBits(this.f1263c) == Float.floatToIntBits(activityChooserModel.f1263c);
        }

        public int hashCode() {
            return (((((this.f1261a == null ? 0 : this.f1261a.hashCode()) + 31) * 31) + ((int) (this.f1262b ^ (this.f1262b >>> 32)))) * 31) + Float.floatToIntBits(this.f1263c);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append("; activity:").append(this.f1261a);
            stringBuilder.append("; time:").append(this.f1262b);
            stringBuilder.append("; weight:").append(new BigDecimal((double) this.f1263c));
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    /* renamed from: android.support.v7.internal.widget.h.d */
    public interface ActivityChooserModel {
        boolean m2049a(ActivityChooserModel activityChooserModel, Intent intent);
    }

    /* renamed from: android.support.v7.internal.widget.h.e */
    private final class ActivityChooserModel extends AsyncTask<Object, Void, Void> {
        final /* synthetic */ ActivityChooserModel f1264a;

        private ActivityChooserModel(ActivityChooserModel activityChooserModel) {
            this.f1264a = activityChooserModel;
        }

        public Void m2050a(Object... objArr) {
            int i = 0;
            List list = (List) objArr[0];
            String str = (String) objArr[1];
            try {
                OutputStream openFileOutput = this.f1264a.f1271g.openFileOutput(str, 0);
                XmlSerializer newSerializer = Xml.newSerializer();
                try {
                    newSerializer.setOutput(openFileOutput, null);
                    newSerializer.startDocument(StringUtils.UTF8, Boolean.valueOf(true));
                    newSerializer.startTag(null, "historical-records");
                    int size = list.size();
                    while (i < size) {
                        ActivityChooserModel activityChooserModel = (ActivityChooserModel) list.remove(0);
                        newSerializer.startTag(null, "historical-record");
                        newSerializer.attribute(null, "activity", activityChooserModel.f1261a.flattenToString());
                        newSerializer.attribute(null, Time.ELEMENT, String.valueOf(activityChooserModel.f1262b));
                        newSerializer.attribute(null, "weight", String.valueOf(activityChooserModel.f1263c));
                        newSerializer.endTag(null, "historical-record");
                        i++;
                    }
                    newSerializer.endTag(null, "historical-records");
                    newSerializer.endDocument();
                    this.f1264a.f1276l = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (Throwable e2) {
                    Log.e(ActivityChooserModel.f1265a, "Error writing historical recrod file: " + this.f1264a.f1272h, e2);
                    this.f1264a.f1276l = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e3) {
                        }
                    }
                } catch (Throwable e22) {
                    Log.e(ActivityChooserModel.f1265a, "Error writing historical recrod file: " + this.f1264a.f1272h, e22);
                    this.f1264a.f1276l = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e4) {
                        }
                    }
                } catch (Throwable e222) {
                    Log.e(ActivityChooserModel.f1265a, "Error writing historical recrod file: " + this.f1264a.f1272h, e222);
                    this.f1264a.f1276l = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e5) {
                        }
                    }
                } catch (Throwable th) {
                    this.f1264a.f1276l = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e6) {
                        }
                    }
                }
            } catch (Throwable e2222) {
                Log.e(ActivityChooserModel.f1265a, "Error writing historical recrod file: " + str, e2222);
            }
            return null;
        }

        public /* synthetic */ Object doInBackground(Object[] objArr) {
            return m2050a(objArr);
        }
    }

    static {
        f1265a = ActivityChooserModel.class.getSimpleName();
        f1266b = new Object();
        f1267c = new HashMap();
    }

    private boolean m2052a(ActivityChooserModel activityChooserModel) {
        boolean add = this.f1270f.add(activityChooserModel);
        if (add) {
            this.f1278n = true;
            m2063l();
            m2056e();
            m2060i();
            notifyChanged();
        }
        return add;
    }

    private void m2056e() {
        if (!this.f1277m) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        } else if (this.f1278n) {
            this.f1278n = false;
            if (!TextUtils.isEmpty(this.f1272h)) {
                if (VERSION.SDK_INT >= 11) {
                    m2058g();
                } else {
                    m2057f();
                }
            }
        }
    }

    private void m2057f() {
        new ActivityChooserModel().execute(new Object[]{new ArrayList(this.f1270f), this.f1272h});
    }

    private void m2058g() {
        new ActivityChooserModel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Object[]{new ArrayList(this.f1270f), this.f1272h});
    }

    private void m2059h() {
        int j = m2061j() | m2062k();
        m2063l();
        if (j != 0) {
            m2060i();
            notifyChanged();
        }
    }

    private boolean m2060i() {
        if (this.f1274j == null || this.f1273i == null || this.f1269e.isEmpty() || this.f1270f.isEmpty()) {
            return false;
        }
        this.f1274j.m2048a(this.f1273i, this.f1269e, Collections.unmodifiableList(this.f1270f));
        return true;
    }

    private boolean m2061j() {
        if (!this.f1279o || this.f1273i == null) {
            return false;
        }
        this.f1279o = false;
        this.f1269e.clear();
        List queryIntentActivities = this.f1271g.getPackageManager().queryIntentActivities(this.f1273i, 0);
        int size = queryIntentActivities.size();
        for (int i = 0; i < size; i++) {
            this.f1269e.add(new ActivityChooserModel(this, (ResolveInfo) queryIntentActivities.get(i)));
        }
        return true;
    }

    private boolean m2062k() {
        if (!this.f1276l || !this.f1278n || TextUtils.isEmpty(this.f1272h)) {
            return false;
        }
        this.f1276l = false;
        this.f1277m = true;
        m2064m();
        return true;
    }

    private void m2063l() {
        int size = this.f1270f.size() - this.f1275k;
        if (size > 0) {
            this.f1278n = true;
            for (int i = 0; i < size; i++) {
                ActivityChooserModel activityChooserModel = (ActivityChooserModel) this.f1270f.remove(0);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2064m() {
        /*
        r9 = this;
        r8 = 1;
        r0 = r9.f1271g;	 Catch:{ FileNotFoundException -> 0x00d2 }
        r1 = r9.f1272h;	 Catch:{ FileNotFoundException -> 0x00d2 }
        r1 = r0.openFileInput(r1);	 Catch:{ FileNotFoundException -> 0x00d2 }
        r2 = android.util.Xml.newPullParser();	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r0 = 0;
        r2.setInput(r1, r0);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r0 = 0;
    L_0x0012:
        if (r0 == r8) goto L_0x001c;
    L_0x0014:
        r3 = 2;
        if (r0 == r3) goto L_0x001c;
    L_0x0017:
        r0 = r2.next();	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        goto L_0x0012;
    L_0x001c:
        r0 = "historical-records";
        r3 = r2.getName();	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r0 = r0.equals(r3);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        if (r0 != 0) goto L_0x0051;
    L_0x0028:
        r0 = new org.xmlpull.v1.XmlPullParserException;	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r2 = "Share records file does not start with historical-records tag.";
        r0.<init>(r2);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        throw r0;	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
    L_0x0030:
        r0 = move-exception;
        r2 = f1265a;	 Catch:{ all -> 0x00c7 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c7 }
        r3.<init>();	 Catch:{ all -> 0x00c7 }
        r4 = "Error reading historical recrod file: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c7 }
        r4 = r9.f1272h;	 Catch:{ all -> 0x00c7 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c7 }
        r3 = r3.toString();	 Catch:{ all -> 0x00c7 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ all -> 0x00c7 }
        if (r1 == 0) goto L_0x0050;
    L_0x004d:
        r1.close();	 Catch:{ IOException -> 0x00ce }
    L_0x0050:
        return;
    L_0x0051:
        r0 = r9.f1270f;	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r0.clear();	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
    L_0x0056:
        r3 = r2.next();	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        if (r3 != r8) goto L_0x0064;
    L_0x005c:
        if (r1 == 0) goto L_0x0050;
    L_0x005e:
        r1.close();	 Catch:{ IOException -> 0x0062 }
        goto L_0x0050;
    L_0x0062:
        r0 = move-exception;
        goto L_0x0050;
    L_0x0064:
        r4 = 3;
        if (r3 == r4) goto L_0x0056;
    L_0x0067:
        r4 = 4;
        if (r3 == r4) goto L_0x0056;
    L_0x006a:
        r3 = r2.getName();	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r4 = "historical-record";
        r3 = r4.equals(r3);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        if (r3 != 0) goto L_0x00a1;
    L_0x0076:
        r0 = new org.xmlpull.v1.XmlPullParserException;	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r2 = "Share records file not well-formed.";
        r0.<init>(r2);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        throw r0;	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
    L_0x007e:
        r0 = move-exception;
        r2 = f1265a;	 Catch:{ all -> 0x00c7 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c7 }
        r3.<init>();	 Catch:{ all -> 0x00c7 }
        r4 = "Error reading historical recrod file: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c7 }
        r4 = r9.f1272h;	 Catch:{ all -> 0x00c7 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c7 }
        r3 = r3.toString();	 Catch:{ all -> 0x00c7 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ all -> 0x00c7 }
        if (r1 == 0) goto L_0x0050;
    L_0x009b:
        r1.close();	 Catch:{ IOException -> 0x009f }
        goto L_0x0050;
    L_0x009f:
        r0 = move-exception;
        goto L_0x0050;
    L_0x00a1:
        r3 = 0;
        r4 = "activity";
        r3 = r2.getAttributeValue(r3, r4);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r4 = 0;
        r5 = "time";
        r4 = r2.getAttributeValue(r4, r5);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r4 = java.lang.Long.parseLong(r4);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r6 = 0;
        r7 = "weight";
        r6 = r2.getAttributeValue(r6, r7);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r6 = java.lang.Float.parseFloat(r6);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r7 = new android.support.v7.internal.widget.h$c;	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r7.<init>(r3, r4, r6);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r0.add(r7);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        goto L_0x0056;
    L_0x00c7:
        r0 = move-exception;
        if (r1 == 0) goto L_0x00cd;
    L_0x00ca:
        r1.close();	 Catch:{ IOException -> 0x00d0 }
    L_0x00cd:
        throw r0;
    L_0x00ce:
        r0 = move-exception;
        goto L_0x0050;
    L_0x00d0:
        r1 = move-exception;
        goto L_0x00cd;
    L_0x00d2:
        r0 = move-exception;
        goto L_0x0050;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.internal.widget.h.m():void");
    }

    public int m2065a() {
        int size;
        synchronized (this.f1268d) {
            m2059h();
            size = this.f1269e.size();
        }
        return size;
    }

    public int m2066a(ResolveInfo resolveInfo) {
        synchronized (this.f1268d) {
            m2059h();
            List list = this.f1269e;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (((ActivityChooserModel) list.get(i)).f1258a == resolveInfo) {
                    return i;
                }
            }
            return -1;
        }
    }

    public ResolveInfo m2067a(int i) {
        ResolveInfo resolveInfo;
        synchronized (this.f1268d) {
            m2059h();
            resolveInfo = ((ActivityChooserModel) this.f1269e.get(i)).f1258a;
        }
        return resolveInfo;
    }

    public Intent m2068b(int i) {
        synchronized (this.f1268d) {
            if (this.f1273i == null) {
                return null;
            }
            m2059h();
            ActivityChooserModel activityChooserModel = (ActivityChooserModel) this.f1269e.get(i);
            ComponentName componentName = new ComponentName(activityChooserModel.f1258a.activityInfo.packageName, activityChooserModel.f1258a.activityInfo.name);
            Intent intent = new Intent(this.f1273i);
            intent.setComponent(componentName);
            if (this.f1280p != null) {
                if (this.f1280p.m2049a(this, new Intent(intent))) {
                    return null;
                }
            }
            m2052a(new ActivityChooserModel(componentName, System.currentTimeMillis(), 1.0f));
            return intent;
        }
    }

    public ResolveInfo m2069b() {
        synchronized (this.f1268d) {
            m2059h();
            if (this.f1269e.isEmpty()) {
                return null;
            }
            ResolveInfo resolveInfo = ((ActivityChooserModel) this.f1269e.get(0)).f1258a;
            return resolveInfo;
        }
    }

    public int m2070c() {
        int size;
        synchronized (this.f1268d) {
            m2059h();
            size = this.f1270f.size();
        }
        return size;
    }

    public void m2071c(int i) {
        synchronized (this.f1268d) {
            m2059h();
            ActivityChooserModel activityChooserModel = (ActivityChooserModel) this.f1269e.get(i);
            ActivityChooserModel activityChooserModel2 = (ActivityChooserModel) this.f1269e.get(0);
            m2052a(new ActivityChooserModel(new ComponentName(activityChooserModel.f1258a.activityInfo.packageName, activityChooserModel.f1258a.activityInfo.name), System.currentTimeMillis(), activityChooserModel2 != null ? (activityChooserModel2.f1259b - activityChooserModel.f1259b) + 5.0f : 1.0f));
        }
    }
}
