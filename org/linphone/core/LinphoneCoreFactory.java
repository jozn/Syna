package org.linphone.core;

public abstract class LinphoneCoreFactory {
    private static String factoryName;
    static LinphoneCoreFactory theLinphoneCoreFactory;

    static {
        factoryName = "org.linphone.core.LinphoneCoreFactoryImpl";
    }

    public static final synchronized LinphoneCoreFactory instance() {
        LinphoneCoreFactory linphoneCoreFactory;
        synchronized (LinphoneCoreFactory.class) {
            try {
                if (theLinphoneCoreFactory == null) {
                    theLinphoneCoreFactory = (LinphoneCoreFactory) Class.forName(factoryName).newInstance();
                }
            } catch (Exception e) {
                System.err.println("Cannot instanciate factory [" + factoryName + "]");
            }
            linphoneCoreFactory = theLinphoneCoreFactory;
        }
        return linphoneCoreFactory;
    }

    public static void setFactoryClassName(String str) {
        factoryName = str;
    }

    public abstract LinphoneAuthInfo createAuthInfo(String str, String str2, String str3, String str4);

    public abstract LinphoneAuthInfo createAuthInfo(String str, String str2, String str3, String str4, String str5, String str6);

    public abstract LinphoneAddress createLinphoneAddress(String str) throws LinphoneCoreException;

    public abstract LinphoneAddress createLinphoneAddress(String str, String str2, String str3);

    public abstract LinphoneContent createLinphoneContent(String str, String str2, String str3);

    public abstract LinphoneContent createLinphoneContent(String str, String str2, byte[] bArr, String str3);

    public abstract LinphoneCore createLinphoneCore(LinphoneCoreListener linphoneCoreListener, Object obj) throws LinphoneCoreException;

    public abstract LinphoneCore createLinphoneCore(LinphoneCoreListener linphoneCoreListener, String str, String str2, Object obj, Object obj2) throws LinphoneCoreException;

    public abstract LinphoneFriend createLinphoneFriend();

    public abstract LinphoneFriend createLinphoneFriend(String str);

    public abstract LpConfig createLpConfig(String str);

    public abstract PresenceActivity createPresenceActivity(PresenceActivityType presenceActivityType, String str);

    public abstract PresenceModel createPresenceModel();

    public abstract PresenceModel createPresenceModel(PresenceActivityType presenceActivityType, String str);

    public abstract PresenceModel createPresenceModel(PresenceActivityType presenceActivityType, String str, String str2, String str3);

    public abstract PresenceService createPresenceService(String str, PresenceBasicStatus presenceBasicStatus, String str2);

    public abstract LinphoneProxyConfig createProxyConfig(String str, String str2, String str3, boolean z) throws LinphoneCoreException;

    public abstract void setDebugMode(boolean z, String str);

    public abstract void setLogHandler(LinphoneLogHandler linphoneLogHandler);
}
