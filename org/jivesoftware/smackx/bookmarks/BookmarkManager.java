package org.jivesoftware.smackx.bookmarks;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smackx.bookmarks.Bookmarks.Provider;
import org.jivesoftware.smackx.iqprivate.PrivateDataManager;

public class BookmarkManager {
    private static final Map<XMPPConnection, BookmarkManager> bookmarkManagerMap;
    private final Object bookmarkLock;
    private Bookmarks bookmarks;
    private PrivateDataManager privateDataManager;

    static {
        bookmarkManagerMap = new WeakHashMap();
        PrivateDataManager.addPrivateDataProvider(Bookmarks.ELEMENT, Bookmarks.NAMESPACE, new Provider());
    }

    private BookmarkManager(XMPPConnection xMPPConnection) throws XMPPException, SmackException {
        this.bookmarkLock = new Object();
        this.privateDataManager = PrivateDataManager.getInstanceFor(xMPPConnection);
        bookmarkManagerMap.put(xMPPConnection, this);
    }

    public static synchronized BookmarkManager getBookmarkManager(XMPPConnection xMPPConnection) throws XMPPException, SmackException {
        BookmarkManager bookmarkManager;
        synchronized (BookmarkManager.class) {
            bookmarkManager = (BookmarkManager) bookmarkManagerMap.get(xMPPConnection);
            if (bookmarkManager == null) {
                bookmarkManager = new BookmarkManager(xMPPConnection);
            }
        }
        return bookmarkManager;
    }

    private Bookmarks retrieveBookmarks() throws NoResponseException, XMPPErrorException, NotConnectedException {
        Bookmarks bookmarks;
        synchronized (this.bookmarkLock) {
            if (this.bookmarks == null) {
                this.bookmarks = (Bookmarks) this.privateDataManager.getPrivateData(Bookmarks.ELEMENT, Bookmarks.NAMESPACE);
            }
            bookmarks = this.bookmarks;
        }
        return bookmarks;
    }

    public void addBookmarkedConference(String str, String str2, boolean z, String str3, String str4) throws NoResponseException, XMPPErrorException, NotConnectedException {
        retrieveBookmarks();
        BookmarkedConference bookmarkedConference = new BookmarkedConference(str, str2, z, str3, str4);
        List bookmarkedConferences = this.bookmarks.getBookmarkedConferences();
        if (bookmarkedConferences.contains(bookmarkedConference)) {
            bookmarkedConference = (BookmarkedConference) bookmarkedConferences.get(bookmarkedConferences.indexOf(bookmarkedConference));
            if (bookmarkedConference.isShared()) {
                throw new IllegalArgumentException("Cannot modify shared bookmark");
            }
            bookmarkedConference.setAutoJoin(z);
            bookmarkedConference.setName(str);
            bookmarkedConference.setNickname(str3);
            bookmarkedConference.setPassword(str4);
        } else {
            this.bookmarks.addBookmarkedConference(bookmarkedConference);
        }
        this.privateDataManager.setPrivateData(this.bookmarks);
    }

    public void addBookmarkedURL(String str, String str2, boolean z) throws NoResponseException, XMPPErrorException, NotConnectedException {
        retrieveBookmarks();
        BookmarkedURL bookmarkedURL = new BookmarkedURL(str, str2, z);
        List bookmarkedURLS = this.bookmarks.getBookmarkedURLS();
        if (bookmarkedURLS.contains(bookmarkedURL)) {
            bookmarkedURL = (BookmarkedURL) bookmarkedURLS.get(bookmarkedURLS.indexOf(bookmarkedURL));
            if (bookmarkedURL.isShared()) {
                throw new IllegalArgumentException("Cannot modify shared bookmarks");
            }
            bookmarkedURL.setName(str2);
            bookmarkedURL.setRss(z);
        } else {
            this.bookmarks.addBookmarkedURL(bookmarkedURL);
        }
        this.privateDataManager.setPrivateData(this.bookmarks);
    }

    public List<BookmarkedConference> getBookmarkedConferences() throws NoResponseException, XMPPErrorException, NotConnectedException {
        retrieveBookmarks();
        return Collections.unmodifiableList(this.bookmarks.getBookmarkedConferences());
    }

    public List<BookmarkedURL> getBookmarkedURLs() throws NoResponseException, XMPPErrorException, NotConnectedException {
        retrieveBookmarks();
        return Collections.unmodifiableList(this.bookmarks.getBookmarkedURLS());
    }

    public void removeBookmarkedConference(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        retrieveBookmarks();
        Iterator it = this.bookmarks.getBookmarkedConferences().iterator();
        while (it.hasNext()) {
            BookmarkedConference bookmarkedConference = (BookmarkedConference) it.next();
            if (bookmarkedConference.getJid().equalsIgnoreCase(str)) {
                if (bookmarkedConference.isShared()) {
                    throw new IllegalArgumentException("Conference is shared and can't be removed");
                }
                it.remove();
                this.privateDataManager.setPrivateData(this.bookmarks);
                return;
            }
        }
    }

    public void removeBookmarkedURL(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        retrieveBookmarks();
        Iterator it = this.bookmarks.getBookmarkedURLS().iterator();
        while (it.hasNext()) {
            BookmarkedURL bookmarkedURL = (BookmarkedURL) it.next();
            if (bookmarkedURL.getURL().equalsIgnoreCase(str)) {
                if (bookmarkedURL.isShared()) {
                    throw new IllegalArgumentException("Cannot delete a shared bookmark.");
                }
                it.remove();
                this.privateDataManager.setPrivateData(this.bookmarks);
                return;
            }
        }
    }
}
