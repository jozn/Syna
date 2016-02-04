package org.jivesoftware.smack.sasl.provided;

import javax.security.auth.callback.CallbackHandler;
import org.apache.http.HttpStatus;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.util.ByteUtils;

public class SASLPlainMechanism extends SASLMechanism {
    public static final String NAME = "PLAIN";

    protected void authenticateInternal(CallbackHandler callbackHandler) throws SmackException {
        throw new UnsupportedOperationException("CallbackHandler not (yet) supported");
    }

    public void checkIfSuccessfulOrThrow() throws SmackException {
    }

    protected byte[] getAuthenticationText() throws SmackException {
        return ByteUtils.m5845a(SASLMechanism.toBytes('\u0000' + this.authenticationId), SASLMechanism.toBytes('\u0000' + this.password));
    }

    public String getName() {
        return NAME;
    }

    public int getPriority() {
        return HttpStatus.SC_GONE;
    }

    public SASLPlainMechanism newInstance() {
        return new SASLPlainMechanism();
    }
}
