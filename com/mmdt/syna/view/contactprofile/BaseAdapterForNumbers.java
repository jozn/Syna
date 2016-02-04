package com.mmdt.syna.view.contactprofile;

import android.view.View;
import android.view.View.OnClickListener;
import com.mmdt.syna.view.tools.CallAndMessageHandler;

/* renamed from: com.mmdt.syna.view.contactprofile.c */
class BaseAdapterForNumbers implements OnClickListener {
    final /* synthetic */ BaseAdapterForNumbers f1973a;
    private final /* synthetic */ String f1974b;

    BaseAdapterForNumbers(BaseAdapterForNumbers baseAdapterForNumbers, String str) {
        this.f1973a = baseAdapterForNumbers;
        this.f1974b = str;
    }

    public void onClick(View view) {
        if (this.f1974b != null) {
            CallAndMessageHandler.m4020b(this.f1971a.f1968c, this.f1974b);
        }
    }
}
;
        }
    }
}
