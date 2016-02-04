package org.jivesoftware.smack.sasl.provided;

import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.util.StringUtils;
import org.p075c.p076a.XmppStringUtils;

public class SASLExternalMechanism extends SASLMechanism {
    public static final String NAME = "EXTERNAL";

    protected void authenticateInternal(CallbackHandler callbackHandler) throws SmackException {
    }

    public void checkIfSuccessfulOrThrow() throws SmackException {
    }

    protected byte[] getAuthenticationText() throws SmackException {
        return StringUtils.isNullOrEmpty(this.authenticationId) ? null : SASLMechanism.toBytes(XmppStringUtils.m5399a(this.authenticationId, this.serviceName));
    }

    public String getName() {
        return NAME;
    }

    public int getPriority() {
        return 510;
    }

    protected SASLMechanism newInstance() {
        return new SASLExternalMechanism();
    }
}
