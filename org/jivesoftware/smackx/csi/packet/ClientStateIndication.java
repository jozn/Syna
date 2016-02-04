package org.jivesoftware.smackx.csi.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.FullStreamElement;

public class ClientStateIndication {
    public static final String NAMESPACE = "urn:xmpp:csi:0";

    public static class Active extends FullStreamElement {
        public static final String ELEMENT = "active";
        public static final Active INSTANCE;

        static {
            INSTANCE = new Active();
        }

        private Active() {
        }

        public String getElementName() {
            return ELEMENT;
        }

        public String getNamespace() {
            return ClientStateIndication.NAMESPACE;
        }

        public String toXML() {
            return "<active xmlns='urn:xmpp:csi:0'/>";
        }
    }

    public static class Feature implements ExtensionElement {
        public static final String ELEMENT = "csi";
        public static final Feature INSTANCE;

        static {
            INSTANCE = new Feature();
        }

        private Feature() {
        }

        public String getElementName() {
            return ELEMENT;
        }

        public String getNamespace() {
            return ClientStateIndication.NAMESPACE;
        }

        public String toXML() {
            return "<csi xmlns='urn:xmpp:csi:0'/>";
        }
    }

    public static class Inactive extends FullStreamElement {
        public static final String ELEMENT = "inactive";
        public static final Inactive INSTANCE;

        static {
            INSTANCE = new Inactive();
        }

        private Inactive() {
        }

        public String getElementName() {
            return ELEMENT;
        }

        public String getNamespace() {
            return ClientStateIndication.NAMESPACE;
        }

        public String toXML() {
            return "<inactive xmlns='urn:xmpp:csi:0'/>";
        }
    }
}
