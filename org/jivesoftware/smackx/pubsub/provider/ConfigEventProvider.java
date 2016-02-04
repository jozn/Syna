package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.ConfigurationEvent;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.xdata.packet.DataForm;

public class ConfigEventProvider extends EmbeddedExtensionProvider<ConfigurationEvent> {
    protected ConfigurationEvent m5990a(String str, String str2, Map<String, String> map, List<? extends ExtensionElement> list) {
        return list.size() == 0 ? new ConfigurationEvent((String) map.get("node")) : new ConfigurationEvent((String) map.get("node"), new ConfigureForm((DataForm) list.iterator().next()));
    }

    protected /* synthetic */ ExtensionElement createReturnExtension(String str, String str2, Map map, List list) {
        return m5990a(str, str2, map, list);
    }
}