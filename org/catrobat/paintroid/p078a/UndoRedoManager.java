package org.catrobat.paintroid.p078a;

import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.ui.TopBar;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.a.c */
public final class UndoRedoManager {
    private static UndoRedoManager f4243a;
    private static /* synthetic */ int[] f4244c;
    private TopBar f4245b;

    /* renamed from: org.catrobat.paintroid.a.c.a */
    public enum UndoRedoManager {
        ENABLE_UNDO,
        DISABLE_UNDO,
        ENABLE_REDO,
        DISABLE_REDO
    }

    private UndoRedoManager() {
    }

    public static UndoRedoManager m5474a() {
        if (f4243a == null) {
            f4243a = new UndoRedoManager();
        }
        return f4243a;
    }

    static /* synthetic */ int[] m5475b() {
        int[] iArr = f4244c;
        if (iArr == null) {
            iArr = new int[UndoRedoManager.values().length];
            try {
                iArr[UndoRedoManager.DISABLE_REDO.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[UndoRedoManager.DISABLE_UNDO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[UndoRedoManager.ENABLE_REDO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[UndoRedoManager.ENABLE_UNDO.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            f4244c = iArr;
        }
        return iArr;
    }

    public void m5476a(UndoRedoManager undoRedoManager) {
        switch (UndoRedoManager.m5475b()[undoRedoManager.ordinal()]) {
            case VideoSize.CIF /*1*/:
                this.f4245b.m5816a(R.icon_menu_undo);
                this.f4245b.m5815a();
            case VideoSize.HVGA /*2*/:
                this.f4245b.m5816a(R.icon_menu_undo_disabled);
                this.f4245b.m5818b();
            case Version.API03_CUPCAKE_15 /*3*/:
                this.f4245b.m5819b(R.icon_menu_redo);
                this.f4245b.m5820c();
            case Version.API04_DONUT_16 /*4*/:
                this.f4245b.m5819b(R.icon_menu_redo_disabled);
                this.f4245b.m5821d();
            default:
        }
    }

    public void m5477a(TopBar topBar) {
        this.f4245b = topBar;
    }
}
