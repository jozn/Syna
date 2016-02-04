package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.XMPPConnection;

public class LeafNode extends Node {
    LeafNode(XMPPConnection xMPPConnection, String str) {
        super(xMPPConnection, str);
    }
}
