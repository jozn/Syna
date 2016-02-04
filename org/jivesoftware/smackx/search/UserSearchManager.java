package org.jivesoftware.smackx.search;

import java.util.List;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.xdata.Form;

public class UserSearchManager {
    private XMPPConnection con;
    private UserSearch userSearch;

    public UserSearchManager(XMPPConnection xMPPConnection) {
        this.con = xMPPConnection;
        this.userSearch = new UserSearch();
    }

    public Form getSearchForm(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return this.userSearch.getSearchForm(this.con, str);
    }

    public ReportedData getSearchResults(Form form, String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return this.userSearch.sendSearchForm(this.con, form, str);
    }

    public List<String> getSearchServices() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return ServiceDiscoveryManager.getInstanceFor(this.con).findServices(UserSearch.NAMESPACE, false, false);
    }
}
