package org.jivesoftware.smackx.delay.provider;

import java.text.ParseException;
import java.util.Date;
import org.p075c.p076a.XmppDateTime;

public class DelayInformationProvider extends AbstractDelayInformationProvider {
    public static final DelayInformationProvider INSTANCE;

    static {
        INSTANCE = new DelayInformationProvider();
    }

    protected Date parseDate(String str) throws ParseException {
        return XmppDateTime.m5388a(str);
    }
}
