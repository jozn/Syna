package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.p008c.SimpleArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

public class FragmentActivity extends Activity {
    final Handler f177a;
    final FragmentManager f178b;
    final FragmentManager f179c;
    boolean f180d;
    boolean f181e;
    boolean f182f;
    boolean f183g;
    boolean f184h;
    boolean f185i;
    boolean f186j;
    boolean f187k;
    SimpleArrayMap<String, aa> f188l;
    aa f189m;

    /* renamed from: android.support.v4.app.FragmentActivity.a */
    static class C0001a {
        public static final int[] f171a;

        static {
            f171a = new int[]{16842755, 16842960, 16842961};
        }
    }

    /* renamed from: android.support.v4.app.FragmentActivity.b */
    static final class C0002b {
        Object f172a;
        Object f173b;
        SimpleArrayMap<String, Object> f174c;
        ArrayList<Fragment> f175d;
        SimpleArrayMap<String, aa> f176e;

        C0002b() {
        }
    }

    public FragmentActivity() {
        this.f177a = new FragmentActivity(this);
        this.f178b = new FragmentManager();
        this.f179c = new FragmentActivity(this);
    }

    private static String m113a(View view) {
        char c = 'F';
        char c2 = '.';
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append(view.getClass().getName());
        stringBuilder.append('{');
        stringBuilder.append(Integer.toHexString(System.identityHashCode(view)));
        stringBuilder.append(' ');
        switch (view.getVisibility()) {
            case VideoSize.QCIF /*0*/:
                stringBuilder.append('V');
                break;
            case Version.API04_DONUT_16 /*4*/:
                stringBuilder.append('I');
                break;
            case Version.API08_FROYO_22 /*8*/:
                stringBuilder.append('G');
                break;
            default:
                stringBuilder.append('.');
                break;
        }
        stringBuilder.append(view.isFocusable() ? 'F' : '.');
        stringBuilder.append(view.isEnabled() ? 'E' : '.');
        stringBuilder.append(view.willNotDraw() ? '.' : 'D');
        stringBuilder.append(view.isHorizontalScrollBarEnabled() ? 'H' : '.');
        stringBuilder.append(view.isVerticalScrollBarEnabled() ? 'V' : '.');
        stringBuilder.append(view.isClickable() ? 'C' : '.');
        stringBuilder.append(view.isLongClickable() ? 'L' : '.');
        stringBuilder.append(' ');
        if (!view.isFocused()) {
            c = '.';
        }
        stringBuilder.append(c);
        stringBuilder.append(view.isSelected() ? 'S' : '.');
        if (view.isPressed()) {
            c2 = 'P';
        }
        stringBuilder.append(c2);
        stringBuilder.append(' ');
        stringBuilder.append(view.getLeft());
        stringBuilder.append(',');
        stringBuilder.append(view.getTop());
        stringBuilder.append('-');
        stringBuilder.append(view.getRight());
        stringBuilder.append(',');
        stringBuilder.append(view.getBottom());
        int id = view.getId();
        if (id != -1) {
            stringBuilder.append(" #");
            stringBuilder.append(Integer.toHexString(id));
            Resources resources = view.getResources();
            if (!(id == 0 || resources == null)) {
                String str;
                switch (-16777216 & id) {
                    case 16777216:
                        str = "android";
                        break;
                    case 2130706432:
                        str = "app";
                        break;
                    default:
                        try {
                            str = resources.getResourcePackageName(id);
                            break;
                        } catch (NotFoundException e) {
                            break;
                        }
                }
                String resourceTypeName = resources.getResourceTypeName(id);
                String resourceEntryName = resources.getResourceEntryName(id);
                stringBuilder.append(" ");
                stringBuilder.append(str);
                stringBuilder.append(":");
                stringBuilder.append(resourceTypeName);
                stringBuilder.append("/");
                stringBuilder.append(resourceEntryName);
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private void m114a(String str, PrintWriter printWriter, View view) {
        printWriter.print(str);
        if (view == null) {
            printWriter.println("null");
            return;
        }
        printWriter.println(m113a(view));
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            if (childCount > 0) {
                String str2 = str + "  ";
                for (int i = 0; i < childCount; i++) {
                    m114a(str2, printWriter, viewGroup.getChildAt(i));
                }
            }
        }
    }

    aa m115a(String str, boolean z, boolean z2) {
        if (this.f188l == null) {
            this.f188l = new SimpleArrayMap();
        }
        aa aaVar = (aa) this.f188l.get(str);
        if (aaVar != null) {
            aaVar.m150a(this);
            return aaVar;
        } else if (!z2) {
            return aaVar;
        } else {
            aaVar = new aa(str, this, z);
            this.f188l.put(str, aaVar);
            return aaVar;
        }
    }

    public void m116a(Fragment fragment) {
    }

    public void m117a(Fragment fragment, Intent intent, int i) {
        if (i == -1) {
            super.startActivityForResult(intent, -1);
        } else if ((-65536 & i) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        } else {
            super.startActivityForResult(intent, ((fragment.f159o + 1) << 16) + (InBandBytestreamManager.MAXIMUM_BLOCK_SIZE & i));
        }
    }

    void m118a(String str) {
        if (this.f188l != null) {
            aa aaVar = (aa) this.f188l.get(str);
            if (aaVar != null && !aaVar.f238g) {
                aaVar.m161h();
                this.f188l.remove(str);
            }
        }
    }

    void m119a(boolean z) {
        if (!this.f183g) {
            this.f183g = true;
            this.f184h = z;
            this.f177a.removeMessages(1);
            g_();
        }
    }

    protected boolean m120a(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    protected void a_() {
        this.f178b.m308m();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2;
        if (VERSION.SDK_INT >= 11) {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.f180d);
            printWriter.print("mResumed=");
            printWriter.print(this.f181e);
            printWriter.print(" mStopped=");
            printWriter.print(this.f182f);
            printWriter.print(" mReallyStopped=");
            printWriter.println(this.f183g);
            printWriter.print(str2);
            printWriter.print("mLoadersStarted=");
            printWriter.println(this.f187k);
        } else {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.f180d);
            printWriter.print("mResumed=");
            printWriter.print(this.f181e);
            printWriter.print(" mStopped=");
            printWriter.print(this.f182f);
            printWriter.print(" mReallyStopped=");
            printWriter.println(this.f183g);
            printWriter.print(str2);
            printWriter.print("mLoadersStarted=");
            printWriter.println(this.f187k);
        }
        if (this.f189m != null) {
            printWriter.print(str);
            printWriter.print("Loader Manager ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this.f189m)));
            printWriter.println(":");
            this.f189m.m152a(str + "  ", fileDescriptor, printWriter, strArr);
        }
        this.f178b.m278a(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.println("View Hierarchy:");
        m114a(str + "  ", printWriter, getWindow().getDecorView());
    }

    public FragmentManager m121e() {
        return this.f178b;
    }

    public void e_() {
        if (VERSION.SDK_INT >= 11) {
            ActivityCompatHoneycomb.m193a(this);
        } else {
            this.f185i = true;
        }
    }

    public Object f_() {
        return null;
    }

    void g_() {
        if (this.f187k) {
            this.f187k = false;
            if (this.f189m != null) {
                if (this.f184h) {
                    this.f189m.m157d();
                } else {
                    this.f189m.m156c();
                }
            }
        }
        this.f178b.m311p();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.f178b.m304i();
        int i3 = i >> 16;
        if (i3 != 0) {
            i3--;
            if (this.f178b.f293f == null || i3 < 0 || i3 >= this.f178b.f293f.size()) {
                Log.w("FragmentActivity", "Activity result fragment index out of range: 0x" + Integer.toHexString(i));
                return;
            }
            Fragment fragment = (Fragment) this.f178b.f293f.get(i3);
            if (fragment == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for index: 0x" + Integer.toHexString(i));
                return;
            } else {
                fragment.m55a(InBandBytestreamManager.MAXIMUM_BLOCK_SIZE & i, i2, intent);
                return;
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (!this.f178b.m292c()) {
            finish();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f178b.m270a(configuration);
    }

    protected void onCreate(Bundle bundle) {
        this.f178b.m276a(this, this.f179c, null);
        if (getLayoutInflater().getFactory() == null) {
            getLayoutInflater().setFactory(this);
        }
        super.onCreate(bundle);
        C0002b c0002b = (C0002b) getLastNonConfigurationInstance();
        if (c0002b != null) {
            this.f188l = c0002b.f176e;
        }
        if (bundle != null) {
            this.f178b.m272a(bundle.getParcelable("android:support:fragments"), c0002b != null ? c0002b.f175d : null);
        }
        this.f178b.m305j();
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        return VERSION.SDK_INT >= 11 ? super.onCreatePanelMenu(i, menu) | this.f178b.m281a(menu, getMenuInflater()) : true;
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        int i = 0;
        Fragment fragment = null;
        if (!"fragment".equals(str)) {
            return super.onCreateView(str, context, attributeSet);
        }
        String attributeValue = attributeSet.getAttributeValue(fragment, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0001a.f171a);
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(0);
        }
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (!Fragment.m48b((Context) this, attributeValue)) {
            return super.onCreateView(str, context, attributeSet);
        }
        if (fragment != null) {
            i = fragment.getId();
        }
        if (i == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        if (resourceId != -1) {
            fragment = this.f178b.m261a(resourceId);
        }
        if (fragment == null && string != null) {
            fragment = this.f178b.m263a(string);
        }
        if (fragment == null && i != -1) {
            fragment = this.f178b.m261a(i);
        }
        if (FragmentManager.f287a) {
            Log.v("FragmentActivity", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + attributeValue + " existing=" + fragment);
        }
        if (fragment == null) {
            Fragment a = Fragment.m46a((Context) this, attributeValue);
            a.f168x = true;
            a.f135F = resourceId != 0 ? resourceId : i;
            a.f136G = i;
            a.f137H = string;
            a.f169y = true;
            a.f131B = this.f178b;
            a.m58a((Activity) this, attributeSet, a.f157m);
            this.f178b.m275a(a, true);
            fragment = a;
        } else if (fragment.f169y) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(i) + " with another fragment for " + attributeValue);
        } else {
            fragment.f169y = true;
            if (!fragment.f141L) {
                fragment.m58a((Activity) this, attributeSet, fragment.f157m);
            }
            this.f178b.m290c(fragment);
        }
        if (fragment.f147R == null) {
            throw new IllegalStateException("Fragment " + attributeValue + " did not create a view.");
        }
        if (resourceId != 0) {
            fragment.f147R.setId(resourceId);
        }
        if (fragment.f147R.getTag() == null) {
            fragment.f147R.setTag(string);
        }
        return fragment.f147R;
    }

    protected void onDestroy() {
        super.onDestroy();
        m119a(false);
        this.f178b.m313r();
        if (this.f189m != null) {
            this.f189m.m161h();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (VERSION.SDK_INT >= 5 || i != 4 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        onBackPressed();
        return true;
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.f178b.m314s();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        switch (i) {
            case VideoSize.QCIF /*0*/:
                return this.f178b.m282a(menuItem);
            case Version.API06_ECLAIR_201 /*6*/:
                return this.f178b.m289b(menuItem);
            default:
                return false;
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.f178b.m304i();
    }

    public void onPanelClosed(int i, Menu menu) {
        switch (i) {
            case VideoSize.QCIF /*0*/:
                this.f178b.m287b(menu);
                break;
        }
        super.onPanelClosed(i, menu);
    }

    protected void onPause() {
        super.onPause();
        this.f181e = false;
        if (this.f177a.hasMessages(2)) {
            this.f177a.removeMessages(2);
            a_();
        }
        this.f178b.m309n();
    }

    protected void onPostResume() {
        super.onPostResume();
        this.f177a.removeMessages(2);
        a_();
        this.f178b.m298e();
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0 || menu == null) {
            return super.onPreparePanel(i, view, menu);
        }
        if (this.f185i) {
            this.f185i = false;
            menu.clear();
            onCreatePanelMenu(i, menu);
        }
        return m120a(view, menu) | this.f178b.m280a(menu);
    }

    protected void onResume() {
        super.onResume();
        this.f177a.sendEmptyMessage(2);
        this.f181e = true;
        this.f178b.m298e();
    }

    public final Object onRetainNonConfigurationInstance() {
        int i = 0;
        if (this.f182f) {
            m119a(true);
        }
        Object f_ = f_();
        ArrayList g = this.f178b.m302g();
        int i2;
        if (this.f188l != null) {
            int size = this.f188l.size();
            aa[] aaVarArr = new aa[size];
            for (int i3 = size - 1; i3 >= 0; i3--) {
                aaVarArr[i3] = (aa) this.f188l.m385c(i3);
            }
            i2 = 0;
            while (i < size) {
                aa aaVar = aaVarArr[i];
                if (aaVar.f238g) {
                    i2 = true;
                } else {
                    aaVar.m161h();
                    this.f188l.remove(aaVar.f235d);
                }
                i++;
            }
        } else {
            i2 = 0;
        }
        if (g == null && r0 == 0 && f_ == null) {
            return null;
        }
        C0002b c0002b = new C0002b();
        c0002b.f172a = null;
        c0002b.f173b = f_;
        c0002b.f174c = null;
        c0002b.f175d = g;
        c0002b.f176e = this.f188l;
        return c0002b;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Parcelable h = this.f178b.m303h();
        if (h != null) {
            bundle.putParcelable("android:support:fragments", h);
        }
    }

    protected void onStart() {
        super.onStart();
        this.f182f = false;
        this.f183g = false;
        this.f177a.removeMessages(1);
        if (!this.f180d) {
            this.f180d = true;
            this.f178b.m306k();
        }
        this.f178b.m304i();
        this.f178b.m298e();
        if (!this.f187k) {
            this.f187k = true;
            if (this.f189m != null) {
                this.f189m.m155b();
            } else if (!this.f186j) {
                this.f189m = m115a("(root)", this.f187k, false);
                if (!(this.f189m == null || this.f189m.f237f)) {
                    this.f189m.m155b();
                }
            }
            this.f186j = true;
        }
        this.f178b.m307l();
        if (this.f188l != null) {
            int size = this.f188l.size();
            aa[] aaVarArr = new aa[size];
            for (int i = size - 1; i >= 0; i--) {
                aaVarArr[i] = (aa) this.f188l.m385c(i);
            }
            for (int i2 = 0; i2 < size; i2++) {
                aa aaVar = aaVarArr[i2];
                aaVar.m158e();
                aaVar.m160g();
            }
        }
    }

    protected void onStop() {
        super.onStop();
        this.f182f = true;
        this.f177a.sendEmptyMessage(1);
        this.f178b.m310o();
    }

    public void startActivityForResult(Intent intent, int i) {
        if (i == -1 || (-65536 & i) == 0) {
            super.startActivityForResult(intent, i);
            return;
        }
        throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
    }
}
