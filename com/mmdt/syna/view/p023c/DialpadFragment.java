package com.mmdt.syna.view.p023c;

/* renamed from: com.mmdt.syna.view.c.p */
class DialpadFragment implements Runnable {
    final /* synthetic */ DialpadFragment f1740a;

    DialpadFragment(DialpadFragment dialpadFragment) {
        this.f1740a = dialpadFragment;
    }

    public void run() {
        this.f1740a.f1737a.f1709c.getViewTreeObserver().removeGlobalOnLayoutListener(this.f1740a.f1738b);
    }
}
