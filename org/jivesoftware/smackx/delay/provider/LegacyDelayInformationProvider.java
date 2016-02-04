package org.jivesoftware.smackx.delay.provider;

import java.text.ParseException;
import java.util.Date;
import org.p075c.p076a.XmppDateTime;

public class LegacyDelayInformationProvider extends AbstractDelayInformationProvider {
    protected Date parseDate(String str) throws ParseException {
        return XmppDateTime.m5391b(str);
    }
}
