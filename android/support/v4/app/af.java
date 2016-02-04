package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: TaskStackBuilder */
public class af implements Iterable<Intent> {
    private static final TaskStackBuilder f241a;
    private final ArrayList<Intent> f242b;
    private final Context f243c;

    /* renamed from: android.support.v4.app.af.a */
    public interface TaskStackBuilder {
        Intent m186a();
    }

    /* renamed from: android.support.v4.app.af.b */
    interface TaskStackBuilder {
    }

    /* renamed from: android.support.v4.app.af.c */
    static class TaskStackBuilder implements TaskStackBuilder {
        TaskStackBuilder() {
        }
    }

    /* renamed from: android.support.v4.app.af.d */
    static class TaskStackBuilder implements TaskStackBuilder {
        TaskStackBuilder() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            f241a = new TaskStackBuilder();
        } else {
            f241a = new TaskStackBuilder();
        }
    }

    private af(Context context) {
        this.f242b = new ArrayList();
        this.f243c = context;
    }

    public static af m187a(Context context) {
        return new af(context);
    }

    public af m188a(Activity activity) {
        Intent intent = null;
        if (activity instanceof TaskStackBuilder) {
            intent = ((TaskStackBuilder) activity).m186a();
        }
        Intent a = intent == null ? ab.m175a(activity) : intent;
        if (a != null) {
            ComponentName component = a.getComponent();
            if (component == null) {
                component = a.resolveActivity(this.f243c.getPackageManager());
            }
            m189a(component);
            m190a(a);
        }
        return this;
    }

    public af m189a(ComponentName componentName) {
        int size = this.f242b.size();
        try {
            Intent a = ab.m176a(this.f243c, componentName);
            while (a != null) {
                this.f242b.add(size, a);
                a = ab.m176a(this.f243c, a.getComponent());
            }
            return this;
        } catch (Throwable e) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e);
        }
    }

    public af m190a(Intent intent) {
        this.f242b.add(intent);
        return this;
    }

    public void m191a() {
        m192a(null);
    }

    public void m192a(Bundle bundle) {
        if (this.f242b.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] intentArr = (Intent[]) this.f242b.toArray(new Intent[this.f242b.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        if (!ContextCompat.m132a(this.f243c, intentArr, bundle)) {
            Intent intent = new Intent(intentArr[intentArr.length - 1]);
            intent.addFlags(268435456);
            this.f243c.startActivity(intent);
        }
    }

    public Iterator<Intent> iterator() {
        return this.f242b.iterator();
    }
}
