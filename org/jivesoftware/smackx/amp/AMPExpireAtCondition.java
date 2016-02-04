package org.jivesoftware.smackx.amp;

import java.util.Date;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Condition;
import org.p075c.p076a.XmppDateTime;

public class AMPExpireAtCondition implements Condition {
    public static final String NAME = "expire-at";
    private final String value;

    public AMPExpireAtCondition(String str) {
        if (str == null) {
            throw new NullPointerException("Can't create AMPExpireAtCondition with null value");
        }
        this.value = str;
    }

    public AMPExpireAtCondition(Date date) {
        if (date == null) {
            throw new NullPointerException("Can't create AMPExpireAtCondition with null value");
        }
        this.value = XmppDateTime.m5384a(date);
    }

    public static boolean isSupported(XMPPConnection xMPPConnection) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return AMPManager.m5853a(xMPPConnection, NAME);
    }

    public String getName() {
        return NAME;
    }

    public String getValue() {
        return this.value;
    }
}
