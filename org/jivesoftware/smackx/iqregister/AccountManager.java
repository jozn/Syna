package org.jivesoftware.smackx.iqregister;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Logger;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.filter.StanzaIdFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.disco.packet.DiscoverItems.Item;
import org.jivesoftware.smackx.iqregister.packet.Registration;
import org.p075c.p076a.XmppStringUtils;

public class AccountManager extends Manager {
    private static final Map<XMPPConnection, AccountManager> INSTANCES;
    private static final Logger LOGGER;
    private static boolean allowSensitiveOperationOverInsecureConnectionDefault;
    private boolean accountCreationSupported;
    private boolean allowSensitiveOperationOverInsecureConnection;
    private Registration info;

    static {
        LOGGER = Logger.getLogger(AccountManager.class.getName());
        INSTANCES = new WeakHashMap();
        allowSensitiveOperationOverInsecureConnectionDefault = false;
    }

    private AccountManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.allowSensitiveOperationOverInsecureConnection = allowSensitiveOperationOverInsecureConnectionDefault;
        this.info = null;
        this.accountCreationSupported = false;
    }

    private PacketCollector createPacketCollectorAndSend(IQ iq) throws NotConnectedException {
        return connection().createPacketCollectorAndSend(new StanzaIdFilter(iq.getStanzaId()), iq);
    }

    public static synchronized AccountManager getInstance(XMPPConnection xMPPConnection) {
        AccountManager accountManager;
        synchronized (AccountManager.class) {
            accountManager = (AccountManager) INSTANCES.get(xMPPConnection);
            if (accountManager == null) {
                accountManager = new AccountManager(xMPPConnection);
                INSTANCES.put(xMPPConnection, accountManager);
            }
        }
        return accountManager;
    }

    private synchronized void getRegistrationInfo() throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ registration = new Registration();
        registration.setTo(connection().getServiceName());
        this.info = (Registration) createPacketCollectorAndSend(registration).nextResultOrThrow();
    }

    public static void sensitiveOperationOverInsecureConnectionDefault(boolean z) {
        allowSensitiveOperationOverInsecureConnectionDefault = z;
    }

    public void changePassword(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        if (!(connection().isSecureConnection() || this.allowSensitiveOperationOverInsecureConnection)) {
            LOGGER.warning("Changing password over insecure connection. This will throw an exception in future versions of Smack if AccountManager.sensitiveOperationOverInsecureConnection(true) is not set");
        }
        Map hashMap = new HashMap();
        hashMap.put("username", XmppStringUtils.m5398a(connection().getUser()));
        hashMap.put("password", str);
        IQ registration = new Registration(hashMap);
        registration.setType(Type.set);
        registration.setTo(connection().getServiceName());
        createPacketCollectorAndSend(registration).nextResultOrThrow();
    }

    public void createAccount(String str, String str2) throws NoResponseException, XMPPErrorException, NotConnectedException {
        Map hashMap = new HashMap();
        for (String put : getAccountAttributes()) {
            hashMap.put(put, "");
        }
        createAccount(str, str2, hashMap);
    }

    public void createAccount(String str, String str2, Map<String, String> map) throws NoResponseException, XMPPErrorException, NotConnectedException {
        if (!(connection().isSecureConnection() || this.allowSensitiveOperationOverInsecureConnection)) {
            LOGGER.warning("Creating account over insecure connection. This will throw an exception in future versions of Smack if AccountManager.sensitiveOperationOverInsecureConnection(true) is not set");
        }
        map.put("username", str);
        map.put("password", str2);
        IQ registration = new Registration(map);
        registration.setType(Type.set);
        registration.setTo(connection().getServiceName());
        createPacketCollectorAndSend(registration).nextResultOrThrow();
    }

    public void deleteAccount() throws NoResponseException, XMPPErrorException, NotConnectedException {
        Map hashMap = new HashMap();
        hashMap.put(Item.REMOVE_ACTION, "");
        IQ registration = new Registration(hashMap);
        registration.setType(Type.set);
        registration.setTo(connection().getServiceName());
        createPacketCollectorAndSend(registration).nextResultOrThrow();
    }

    public String getAccountAttribute(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        if (this.info == null) {
            getRegistrationInfo();
        }
        return (String) this.info.getAttributes().get(str);
    }

    public Set<String> getAccountAttributes() throws NoResponseException, XMPPErrorException, NotConnectedException {
        if (this.info == null) {
            getRegistrationInfo();
        }
        Map attributes = this.info.getAttributes();
        return attributes != null ? Collections.unmodifiableSet(attributes.keySet()) : Collections.emptySet();
    }

    public String getAccountInstructions() throws NoResponseException, XMPPErrorException, NotConnectedException {
        if (this.info == null) {
            getRegistrationInfo();
        }
        return this.info.getInstructions();
    }

    public void sensitiveOperationOverInsecureConnection(boolean z) {
        this.allowSensitiveOperationOverInsecureConnection = z;
    }

    void setSupportsAccountCreation(boolean z) {
        this.accountCreationSupported = z;
    }

    public boolean supportsAccountCreation() throws NoResponseException, XMPPErrorException, NotConnectedException {
        boolean z = true;
        if (this.accountCreationSupported) {
            return true;
        }
        if (this.info == null) {
            getRegistrationInfo();
            if (this.info.getType() == Type.error) {
                z = false;
            }
            this.accountCreationSupported = z;
        }
        return this.accountCreationSupported;
    }
}
