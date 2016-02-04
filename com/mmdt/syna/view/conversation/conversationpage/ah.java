package com.mmdt.syna.view.conversation.conversationpage;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: TextBoxFragment */
class ah implements OnClickListener {
    final /* synthetic */ TextBoxFragment f2116a;

    ah(TextBoxFragment textBoxFragment) {
        this.f2116a = textBoxFragment;
    }

    public void onClick(View view) {
        try {
            String editable = this.f2116a.f2284d.getText().toString();
            if (editable != null && editable.length() > 0) {
                do {
                    String str = "";
                    if (editable.length() > 1024) {
                        int i;
                        str = editable.substring(0, 1024);
                        int length = str.length() - 1;
                        int i2 = -1;
                        while (length > 0) {
                            if (str.charAt(length) != '.' && str.charAt(length) != ';' && str.charAt(length) != ',') {
                                i = str.charAt(length) == ' ' ? length + 1 : i2;
                                if (length < 980 && i != -1) {
                                    i2 = i;
                                    i = -1;
                                    break;
                                }
                                length--;
                                i2 = i;
                            } else {
                                i = length + 1;
                                break;
                            }
                        }
                        i = -1;
                        if (i != -1) {
                            str = editable.substring(0, i);
                            editable = editable.substring(i, editable.length());
                        } else if (i2 != -1) {
                            str = editable.substring(0, i2);
                            editable = editable.substring(i2, editable.length());
                        }
                    } else {
                        String str2 = editable;
                        editable = "";
                        str = str2;
                    }
                    while (true) {
                        if (str.charAt(0) == '\n' || str.charAt(0) == '\u00a0' || str.charAt(0) == '\r') {
                            str = str.substring(1, str.length());
                        } else {
                            this.f2116a.f2282b.m2976c(str);
                            this.f2116a.m91h().runOnUiThread(new ai(this));
                        }
                    }
                } while (editable.length() > 0);
            }
        } catch (Exception e) {
            Log.d("*****ERROR*****", "TextBoxFragment init UI");
        }
    }
}
