package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.AffiliationsExtension;

public class AffiliationsProvider extends EmbeddedExtensionProvider<AffiliationsExtension> {
    protected AffiliationsExtension m5989a(String str, String str2, Map<String, String> map, List<? extends ExtensionElement> list) {
        return new AffiliationsExtension(list);
    }

    protected /* synthetic */ ExtensionElement createReturnExtension(String str, String str2, Map map, List list) {
        return m5989a(str, str2, map, list);
    }
}
