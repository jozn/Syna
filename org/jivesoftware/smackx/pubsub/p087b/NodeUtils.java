package org.jivesoftware.smackx.pubsub.p087b;

import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormNode;
import org.jivesoftware.smackx.pubsub.PubSubElementType;

/* renamed from: org.jivesoftware.smackx.pubsub.b.a */
public class NodeUtils {
    public static ConfigureForm m5987a(Stanza stanza, PubSubElementType pubSubElementType) {
        return new ConfigureForm(((FormNode) stanza.getExtension(pubSubElementType.getElementName(), pubSubElementType.getNamespace().getXmlns())).getForm());
    }
}
