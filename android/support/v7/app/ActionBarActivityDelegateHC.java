package android.support.v7.app;

/* renamed from: android.support.v7.app.d */
class ActionBarActivityDelegateHC extends ActionBarActivityDelegateBase {
    ActionBarActivityDelegateHC(ActionBarActivity actionBarActivity) {
        super(actionBarActivity);
    }

    public ActionBar m1531a() {
        m1530k();
        return new ActionBarImplHC(this.a, this.a);
    }
}
