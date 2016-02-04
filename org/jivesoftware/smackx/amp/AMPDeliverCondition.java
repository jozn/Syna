package org.jivesoftware.smackx.amp;

import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Condition;

public class AMPDeliverCondition implements Condition {
    public static final String NAME = "deliver";
    private final Value value;

    public enum Value {
        direct,
        forward,
        gateway,
        none,
        stored
    }

    public AMPDeliverCondition(Value value) {
        if (value == null) {
            throw new NullPointerException("Can't create AMPDeliverCondition with null value");
        }
        this.value = value;
    }

    public static boolean isSupported(XMPPConnection xMPPConnection) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return AMPManager.m5853a(xMPPConnection, NAME);
    }

    public String getName() {
        return NAME;
    }

    public String getValue() {
        return this.value.toString();
    }
}
