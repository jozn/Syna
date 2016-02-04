package android.support.v4.content;

import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* renamed from: android.support.v4.content.l */
class ModernAsyncTask extends FutureTask<Result> {
    final /* synthetic */ ModernAsyncTask f432a;

    ModernAsyncTask(ModernAsyncTask modernAsyncTask, Callable callable) {
        this.f432a = modernAsyncTask;
        super(callable);
    }

    protected void done() {
        try {
            this.f432a.m453c(get());
        } catch (Throwable e) {
            Log.w("AsyncTask", e);
        } catch (ExecutionException e2) {
            throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
        } catch (CancellationException e3) {
            this.f432a.m453c(null);
        } catch (Throwable e4) {
            RuntimeException runtimeException = new RuntimeException("An error occured while executing doInBackground()", e4);
        }
    }
}
