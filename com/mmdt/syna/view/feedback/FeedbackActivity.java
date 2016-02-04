package com.mmdt.syna.view.feedback;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.mmdt.syna.view.feedback.a */
class FeedbackActivity implements OnClickListener {
    final /* synthetic */ FeedbackActivity f2399a;
    private final /* synthetic */ Dialog f2400b;

    FeedbackActivity(FeedbackActivity feedbackActivity, Dialog dialog) {
        this.f2399a = feedbackActivity;
        this.f2400b = dialog;
    }

    public void onClick(View view) {
        this.f2400b.dismiss();
    }
}
