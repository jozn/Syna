package org.catrobat.paintroid.p078a.p079a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.dialog.IndeterminateProgressDialog;
import org.catrobat.paintroid.p078a.Command;
import org.catrobat.paintroid.p078a.CommandManager;
import org.catrobat.paintroid.p078a.UndoRedoManager;
import org.catrobat.paintroid.p078a.p079a.BaseCommand.BaseCommand;

/* renamed from: org.catrobat.paintroid.a.a.d */
public class CommandManagerImplementation implements Observer, CommandManager {
    private final LinkedList<Command> f4212a;
    private int f4213b;
    private int f4214c;
    private Bitmap f4215d;

    public CommandManagerImplementation() {
        this.f4212a = new LinkedList();
        this.f4212a.add(new ClearCommand());
        this.f4213b = 1;
        this.f4214c = 1;
    }

    private synchronized void m5454b(Command command) {
        ((BaseCommand) this.f4212a.remove(this.f4212a.indexOf(command))).m5440a();
        this.f4213b--;
        this.f4214c--;
        if (this.f4213b == 1) {
            UndoRedoManager.m5474a().m5476a(UndoRedoManager.UndoRedoManager.DISABLE_UNDO);
        }
    }

    public void m5455a(Bitmap bitmap) {
        this.f4215d = bitmap.copy(Config.ARGB_8888, true);
        ((Command) this.f4212a.removeFirst()).m5438a();
        this.f4212a.addFirst(new BitmapCommand(this.f4215d, false));
    }

    public boolean m5456a() {
        return this.f4213b > 1;
    }

    public synchronized boolean m5457a(Command command) {
        boolean z;
        if (this.f4213b < this.f4212a.size()) {
            for (int size = this.f4212a.size(); size > this.f4213b; size--) {
                ((Command) this.f4212a.removeLast()).m5438a();
            }
            UndoRedoManager.m5474a().m5476a(UndoRedoManager.UndoRedoManager.DISABLE_REDO);
        }
        if (this.f4213b == 512) {
            z = false;
        } else {
            this.f4213b++;
            UndoRedoManager.m5474a().m5476a(UndoRedoManager.UndoRedoManager.ENABLE_UNDO);
            ((BaseCommand) command).addObserver(this);
            PaintroidApplication.f4200j = false;
            z = this.f4212a.add(command);
        }
        return z;
    }

    public synchronized boolean m5458b() {
        return this.f4214c < this.f4213b;
    }

    public synchronized void m5459c() {
        if (!(this.f4215d == null || this.f4215d.isRecycled())) {
            this.f4215d.recycle();
            this.f4215d = null;
        }
        for (int i = 0; i < this.f4212a.size(); i++) {
            ((Command) this.f4212a.get(i)).m5438a();
        }
        this.f4212a.clear();
        this.f4212a.add(new ClearCommand());
        this.f4213b = 1;
        this.f4214c = 1;
        UndoRedoManager.m5474a().m5476a(UndoRedoManager.UndoRedoManager.DISABLE_REDO);
        UndoRedoManager.m5474a().m5476a(UndoRedoManager.UndoRedoManager.DISABLE_UNDO);
    }

    public synchronized Command m5460d() {
        Command command;
        if (this.f4214c < this.f4213b) {
            LinkedList linkedList = this.f4212a;
            int i = this.f4214c;
            this.f4214c = i + 1;
            command = (Command) linkedList.get(i);
        } else {
            command = null;
        }
        return command;
    }

    public synchronized void m5461e() {
        if (this.f4213b > 1) {
            IndeterminateProgressDialog.m5764a().show();
            this.f4213b--;
            this.f4214c = 0;
            UndoRedoManager.m5474a().m5476a(UndoRedoManager.UndoRedoManager.ENABLE_REDO);
            if (this.f4213b <= 1) {
                UndoRedoManager.m5474a().m5476a(UndoRedoManager.UndoRedoManager.DISABLE_UNDO);
            }
        }
    }

    public synchronized void m5462f() {
        if (this.f4213b < this.f4212a.size()) {
            IndeterminateProgressDialog.m5764a().show();
            this.f4214c = this.f4213b;
            this.f4213b++;
            UndoRedoManager.m5474a().m5476a(UndoRedoManager.UndoRedoManager.ENABLE_UNDO);
            if (this.f4213b == this.f4212a.size()) {
                UndoRedoManager.m5474a().m5476a(UndoRedoManager.UndoRedoManager.DISABLE_REDO);
            }
        }
    }

    public int m5463g() {
        return this.f4213b;
    }

    public void update(Observable observable, Object obj) {
        if ((obj instanceof BaseCommand) && BaseCommand.COMMAND_FAILED == obj && (observable instanceof Command)) {
            m5454b((Command) observable);
        }
    }
}
