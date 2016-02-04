package org.jivesoftware.smack.util;

import java.util.Collection;
import org.jivesoftware.smack.packet.ExtensionElement;

/* renamed from: org.jivesoftware.smack.util.g */
public class PacketUtil {
    public static <PE extends ExtensionElement> PE m5848a(Collection<ExtensionElement> collection, String str, String str2) {
        for (ExtensionElement extensionElement : collection) {
            if ((str == null || extensionElement.getElementName().equals(str)) && extensionElement.getNamespace().equals(str2)) {
                return extensionElement;
            }
        }
        return null;
    }
}
