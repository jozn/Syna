package mobi.mmdt.ott.core.logic.message.p032a;

import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;

/* renamed from: mobi.mmdt.ott.core.logic.message.a.b */
class AMRRecorder implements OnErrorListener {
    final /* synthetic */ AMRRecorder f3745a;

    AMRRecorder(AMRRecorder aMRRecorder) {
        this.f3745a = aMRRecorder;
    }

    public void onError(MediaRecorder mediaRecorder, int i, int i2) {
        if (this.f3745a.c != null) {
            this.f3745a.c.m3062a(this.f3745a.b, new Exception());
        }
    }
}
