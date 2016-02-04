package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smackx.xdata.Form;

public class SubscribeForm extends Form {
    public SubscribeForm(Form form) {
        super(form.getDataFormToSend());
    }
}
