package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.SubscriptionsExtension;

public class SubscriptionsProvider extends EmbeddedExtensionProvider<SubscriptionsExtension> {
    protected SubscriptionsExtension m5999a(String str, String str2, Map<String, String> map, List<? extends ExtensionElement> list) {
        return new SubscriptionsExtension((String) map.get("node"), list);
    }

    protected /* synthetic */ ExtensionElement createReturnExtension(String str, String str2, Map map, List list) {
        return m5999a(str, str2, map, list);
    }
}
