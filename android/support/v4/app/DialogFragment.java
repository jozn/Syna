package android.support.v4.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: android.support.v4.app.f */
public class DialogFragment extends Fragment implements OnCancelListener, OnDismissListener {
    int f272a;
    int f273b;
    boolean f274c;
    boolean f275d;
    int f276e;
    Dialog f277f;
    boolean f278g;
    boolean f279h;
    boolean f280i;

    public DialogFragment() {
        this.f272a = 0;
        this.f273b = 0;
        this.f274c = true;
        this.f275d = true;
        this.f276e = -1;
    }

    public void m226a() {
        m230a(false);
    }

    public void m227a(Activity activity) {
        super.m57a(activity);
        if (!this.f280i) {
            this.f279h = false;
        }
    }

    public void m228a(Bundle bundle) {
        super.m61a(bundle);
        this.f275d = this.G == 0;
        if (bundle != null) {
            this.f272a = bundle.getInt("android:style", 0);
            this.f273b = bundle.getInt("android:theme", 0);
            this.f274c = bundle.getBoolean("android:cancelable", true);
            this.f275d = bundle.getBoolean("android:showsDialog", this.f275d);
            this.f276e = bundle.getInt("android:backStackId", -1);
        }
    }

    public void m229a(FragmentManager fragmentManager, String str) {
        this.f279h = false;
        this.f280i = true;
        FragmentTransaction a = fragmentManager.m249a();
        a.m200a((Fragment) this, str);
        a.m195a();
    }

    void m230a(boolean z) {
        if (!this.f279h) {
            this.f279h = true;
            this.f280i = false;
            if (this.f277f != null) {
                this.f277f.dismiss();
                this.f277f = null;
            }
            this.f278g = true;
            if (this.f276e >= 0) {
                m95j().m250a(this.f276e, 1);
                this.f276e = -1;
                return;
            }
            FragmentTransaction a = m95j().m249a();
            a.m199a((Fragment) this);
            if (z) {
                a.m201b();
            } else {
                a.m195a();
            }
        }
    }

    public int m231b() {
        return this.f273b;
    }

    public LayoutInflater m232b(Bundle bundle) {
        if (!this.f275d) {
            return super.m69b(bundle);
        }
        this.f277f = m233c(bundle);
        switch (this.f272a) {
            case VideoSize.CIF /*1*/:
            case VideoSize.HVGA /*2*/:
                break;
            case Version.API03_CUPCAKE_15 /*3*/:
                this.f277f.getWindow().addFlags(24);
                break;
        }
        this.f277f.requestWindowFeature(1);
        return this.f277f != null ? (LayoutInflater) this.f277f.getContext().getSystemService("layout_inflater") : (LayoutInflater) this.C.getSystemService("layout_inflater");
    }

    public Dialog m233c(Bundle bundle) {
        return new Dialog(m91h(), m231b());
    }

    public void m234c() {
        super.m75c();
        if (!this.f280i && !this.f279h) {
            this.f279h = true;
        }
    }

    public void m235d() {
        super.m79d();
        if (this.f277f != null) {
            this.f278g = false;
            this.f277f.show();
        }
    }

    public void m236d(Bundle bundle) {
        super.m80d(bundle);
        if (this.f275d) {
            View o = m101o();
            if (o != null) {
                if (o.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                this.f277f.setContentView(o);
            }
            this.f277f.setOwnerActivity(m91h());
            this.f277f.setCancelable(this.f274c);
            this.f277f.setOnCancelListener(this);
            this.f277f.setOnDismissListener(this);
            if (bundle != null) {
                Bundle bundle2 = bundle.getBundle("android:savedDialogState");
                if (bundle2 != null) {
                    this.f277f.onRestoreInstanceState(bundle2);
                }
            }
        }
    }

    public void m237e() {
        super.m84e();
        if (this.f277f != null) {
            this.f277f.hide();
        }
    }

    public void m238e(Bundle bundle) {
        super.m85e(bundle);
        if (this.f277f != null) {
            Bundle onSaveInstanceState = this.f277f.onSaveInstanceState();
            if (onSaveInstanceState != null) {
                bundle.putBundle("android:savedDialogState", onSaveInstanceState);
            }
        }
        if (this.f272a != 0) {
            bundle.putInt("android:style", this.f272a);
        }
        if (this.f273b != 0) {
            bundle.putInt("android:theme", this.f273b);
        }
        if (!this.f274c) {
            bundle.putBoolean("android:cancelable", this.f274c);
        }
        if (!this.f275d) {
            bundle.putBoolean("android:showsDialog", this.f275d);
        }
        if (this.f276e != -1) {
            bundle.putInt("android:backStackId", this.f276e);
        }
    }

    public void m239f() {
        super.m87f();
        if (this.f277f != null) {
            this.f278g = true;
            this.f277f.dismiss();
            this.f277f = null;
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.f278g) {
            m230a(true);
        }
    }
}
