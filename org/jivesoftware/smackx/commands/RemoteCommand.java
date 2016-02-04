package org.jivesoftware.smackx.commands;

import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.commands.AdHocCommand.Action;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData;
import org.jivesoftware.smackx.xdata.Form;

public class RemoteCommand extends AdHocCommand {
    private XMPPConnection connection;
    private String jid;
    private String sessionID;

    protected RemoteCommand(XMPPConnection xMPPConnection, String str, String str2) {
        this.connection = xMPPConnection;
        this.jid = str2;
        setNode(str);
    }

    private void executeAction(Action action) throws NoResponseException, XMPPErrorException, NotConnectedException {
        executeAction(action, null);
    }

    private void executeAction(Action action, Form form) throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ adHocCommandData = new AdHocCommandData();
        adHocCommandData.setType(Type.set);
        adHocCommandData.setTo(getOwnerJID());
        adHocCommandData.setNode(getNode());
        adHocCommandData.setSessionID(this.sessionID);
        adHocCommandData.setAction(action);
        if (form != null) {
            adHocCommandData.setForm(form.getDataFormToSend());
        }
        AdHocCommandData adHocCommandData2 = (AdHocCommandData) this.connection.createPacketCollectorAndSend(adHocCommandData).nextResultOrThrow();
        this.sessionID = adHocCommandData2.getSessionID();
        super.setData(adHocCommandData2);
    }

    public void cancel() throws NoResponseException, XMPPErrorException, NotConnectedException {
        executeAction(Action.cancel);
    }

    public void complete(Form form) throws NoResponseException, XMPPErrorException, NotConnectedException {
        executeAction(Action.complete, form);
    }

    public void execute() throws NoResponseException, XMPPErrorException, NotConnectedException {
        executeAction(Action.execute);
    }

    public void execute(Form form) throws NoResponseException, XMPPErrorException, NotConnectedException {
        executeAction(Action.execute, form);
    }

    public String getOwnerJID() {
        return this.jid;
    }

    public void next(Form form) throws NoResponseException, XMPPErrorException, NotConnectedException {
        executeAction(Action.next, form);
    }

    public void prev() throws NoResponseException, XMPPErrorException, NotConnectedException {
        executeAction(Action.prev);
    }
}
