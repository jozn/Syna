package org.jivesoftware.smackx.carbons.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.forward.packet.Forwarded;

public class CarbonExtension implements ExtensionElement {
    public static final String NAMESPACE = "urn:xmpp:carbons:2";
    private final Direction dir;
    private final Forwarded fwd;

    public enum Direction {
        received,
        sent
    }

    public static class Private implements ExtensionElement {
        public static final String ELEMENT = "private";
        public static final Private INSTANCE;

        static {
            INSTANCE = new Private();
        }

        private Private() {
        }

        public static void addTo(Message message) {
            message.addExtension(INSTANCE);
        }

        public String getElementName() {
            return ELEMENT;
        }

        public String getNamespace() {
            return CarbonExtension.NAMESPACE;
        }

        public String toXML() {
            return "<private xmlns='urn:xmpp:carbons:2'/>";
        }
    }

    public CarbonExtension(Direction direction, Forwarded forwarded) {
        this.dir = direction;
        this.fwd = forwarded;
    }

    public static CarbonExtension from(Message message) {
        CarbonExtension carbonExtension = (CarbonExtension) message.getExtension(Direction.received.name(), NAMESPACE);
        return carbonExtension == null ? (CarbonExtension) message.getExtension(Direction.sent.name(), NAMESPACE) : carbonExtension;
    }

    @Deprecated
    public static CarbonExtension getFrom(Message message) {
        return from(message);
    }

    public Direction getDirection() {
        return this.dir;
    }

    public String getElementName() {
        return this.dir.name();
    }

    public Forwarded getForwarded() {
        return this.fwd;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
        xmlStringBuilder.rightAngleBracket();
        xmlStringBuilder.append(this.fwd.toXML());
        xmlStringBuilder.closeElement((NamedElement) this);
        return xmlStringBuilder;
    }
}
